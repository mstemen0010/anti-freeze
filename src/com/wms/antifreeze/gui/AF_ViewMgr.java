/*
 * AntiFreezeView.java
 */
package com.wms.antifreeze.gui;

import com.wms.antifreeze.AFMgr;
import com.wms.antifreeze.AFMgrInf;
import com.wms.antifreeze.AntiFreezeApp;
import com.wms.antifreeze.view.AFDetailViewer;
import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.util.gui.GUILogInterface;
import com.wms.antifreeze.view.AFJob;
import com.wms.antifreeze.view.AFJobWatcherInterface;
import com.wms.antifreeze.view.SCPJob;
import com.wms.antifreeze.view.SCPJobInterface;
import com.wms.antifreeze.view.SplashCanvas;
import com.wms.util.gui.MemoryMonitor;
import com.wms.util.gui.message.WMSEvent;
import com.wms.util.gui.message.WMSMessage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;

/**
 * The application's main frame.
 */
public class AF_ViewMgr extends FrameView implements AFListener, AFViewMgrInterface, GUILogInterface, AFJobWatcherInterface, Observer {

    private MemoryMonitor memoryMonitor = null;
    private String productName = "AntiFreeze 1.0";
    private String dbHostName = "LocalHost";
    private final static String LOOKANDFEEL = "Motif";
    private boolean appStarted = false;
//    private static AFJob appStartupJob;
    private static Thread jobThread;
    // main mgrs
    private AFTicketBox afTicketBox = null;
    private AFMgr afMgr = null;
    private AFTicketInf currentTicket = null;
//    private JStarter afStarter = null;
    private SplashCanvas afStarter = null;
    private Thread afStarterThread = null;
    int centerOfScreen = 0;
    int centerOfSplash = 0;

    public AFMgr getMgr() {
        return afMgr;
    }

    public AF_ViewMgr(SingleFrameApplication app) throws ClassNotFoundException, InstantiationException {
        super(app);
        initComponents();
        // center us

        progressJob = new SCPJob("Starting AF Manager on Domain:  . Please Wait...", 100, true) {

            public SCPJobInterface process() {
                return progressJob;
            }

            public boolean checkDone() {
                if (getCurrent() == 100) {
                    complete();
                    return true;
                }
                return false;
            }
        };
        progressJob.start();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getFrame().getSize();

        centerOfScreen = screenSize.width / 2;
        // splashPicX = (frameSize.width - splashBGImage.getWidth(this)) / 2;

        System.out.println("Screen width is: " + screenSize.width);
        System.out.println("Screen height is: " + screenSize.height);
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        System.out.println("Splash X is: " + x);
        System.out.println("Splash Y is: " + y);

        this.afSplashDialog.setLocation(x, y);

        this.afSplashDialog.setVisible(true);
        afStarter = new SplashCanvas(this.afSplashDialog);
        afStarterThread = new Thread(afStarter);
        afStarterThread.start();
        this.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void sendMessage(WMSEvent event) {
        // reset button
        WMSMessage msg = event.getMessage();
        if (msg == WMSMessage.RESETBUTTON) {
            String buttonName = event.getUserMessage();
            if (buttonName.equals(this.ticketStartButton.getName())) {
                this.ticketStartButton.setEnabled(!afMgr.ticketStarted());
            }
        }
    }

    public AFViewMgrInterface getInterface() {
        return this;
    }

    public void initTicketSystem() {
        JFrame mainFrame = AntiFreezeApp.getApplication().getMainFrame();
        if (afTicketBox == null) {
            afTicketBox = new AFTicketBox(this, mainFrame, this, afMgr);
        }

        AFMgrInf afTicketMgrInf = afMgr.getManagerForType(AFMgrInf.ManagerType.Ticket);
        afTicketMgrInf.setTicketBox(afTicketBox);
        mainWorkPanel.add(afTicketBox);
        afTicketMgrInf.loadOutStandingTickets();
    }

//    public AFTicketInf createNewTicket(AFMgr afMgr, AFCarEntity newCarEntity)
//    {
//        AFTicketInf newTicket = null;
//
//        newTicket = afTicketBox.createTicket( newCarEntity );
//
//        return newTicket;
//    }
    public Color getPrimaryColor() {
        return this.primaryColor;
    }

    public Color getSecondaryColor() {
        return this.secondaryColor;
    }

    public Color getColorFromAFColor(AFColor targetAFColor) {

        Color retColor = null;

        // AF Color names for GUI entites
//        OutterPanel,
//        InnerPanel,
//        LabelBack,
//        LabelFore,
//        HeaderBack,
//        HeaderFore,
//        TicketBack,
//        TicketFore;

        switch (targetAFColor) {
            case OutterPanel:
                retColor = new Color(15, 75, 150);
                break;
            case InnerPanel:
                retColor = new Color(0, 51, 51);
                break;
            case LabelBack:
                retColor = Color.BLACK;
                break;
            case LabelFore:
                retColor = new Color(0, 153, 153);
                break;
            case HeaderBack:
                retColor = Color.BLACK;
                break;
            case HeaderFore:
                retColor = new Color(0, 153, 153);
                break;
            case MenuFore:
                retColor = new Color(0, 204, 204);
                break;
            case MenuBack:
                retColor = Color.BLACK;
                break;
            case ToolFore:
                retColor = Color.BLACK;
                break;
            case ToolBack:
                break;
            case TicketBack:
                retColor = this.ticketSmall.getBackground();
                break;


            default:
                retColor = Color.BLACK;





        }

        return retColor;
    }

    public Font getFontFromAFFont(AFFont targetAFFont) {
        Font targetFont = null;
        switch (targetAFFont) {

        }

        return targetFont;
    }

    public AFTicketInf getCurrentTicket() {
        return this.currentTicket;
    }

    public void setCurrentTicket(AFTicketInf currTicket) {
        this.currentTicket = currTicket;
    }

    public void startSectionToGui(String sectionId, GLogLevel writeLevel) {
        this.writeToGui("VVV========================================================================VVV", writeLevel);

    }

    public void endSectionToGui(String sectionId, GLogLevel writeLevel) {
        this.writeToGui("^^^========================================================================^^^", writeLevel);
    }

    public enum AFIcon {

        MainIcon,
        JobIcon,
        CloseIcon;
    }

    public Icon getIcon(AFIcon icon) {
        Icon retIcon = null;
        switch (icon) {
            case MainIcon:
                retIcon = this.mainIcon.getIcon();
                break;

            case JobIcon:
                retIcon = this.addJobButton.getIcon();
                break;
            case CloseIcon:
                retIcon = this.closeTicketButton.getIcon();
                break;

        }
        return retIcon;
    }

    public void initAntiFreezeSystem() {
        df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        long t = System.currentTimeMillis();
        afDate = new Date(t);
        afCal = Calendar.getInstance();
        afCal.setTime(afDate);
        this.setLevel(GLogLevel.Trace);
        try {
//            progressJob = new AFJob( "Starting " + productName + " Manager on Domain: " + dbHostName + " . Please Wait...", 100, true) {
//
//                public AFJobInterface process(AFJob job) {
//                    return progressJob;
//                }
//
//                public boolean checkDone() {
//                    if (getCurrent() == 100) {
//                        complete();
//                        return true;
//                    }
//                    return false;
//                }
//            };
//            progressJob.start();
            setSplashStatus("Starting AfMgr");
            setSplashStatusValue(15);
            try {
                try {
                    this.afMgr = AFMgr.callMgr();
                    if (afMgr != null) {
                        afMgr.initAntiFreezeMgr(this, this);
                    }
                    this.appStarted = true;
                } catch (SQLException ex) {
                    Logger.getLogger(AF_ViewMgr.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (MalformedURLException ex) {
                // Logger.getLogger(AF_ViewMgr.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Calendar c = new Calender(df);
            parentFrame = this.getFrame();
            setSplashStatus("Starting Memory ");
            setSplashStatusValue(75);
            memoryMonitor = new MemoryMonitor();
            Thread th = new Thread(memoryMonitor);
            th.setName(MemoryMonitor.class.getName() + "::Memory Monitor Thread");
            th.start();
            JPanel memPanel = memoryMonitor.getMemoryMonitorPanel();
            memPanel.setSize(400, 80);
            memPanel.setBackground(debugPanel.getBackground());
            this.debugPanel.add(memPanel);
            // status bar initialization - message timeout, idle icon and busy animation, etc
            ResourceMap resourceMap = getResourceMap();
            int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
            Icon tc = resourceMap.getImageIcon("mainIcon.icon");
            mainIcon.setIcon(tc);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AF_ViewMgr.class.getName()).log(Level.SEVERE, null, ex);
            }

            setSplashStatus("Initalizig Ticketing System");
            setSplashStatusValue(77);
            initTicketSystem();

            setSplashStatus("Setting up Messaging");
            setSplashStatusValue(80);

            setSplashStatus("Complete!");
            setSplashStatusValue(100);
        } catch (RuntimeException e) {
            StringBuilder sb = new StringBuilder("Encountered Runtime Error: ").append(e.getClass()).append("\n");
            sb.append("Cause was: ").append(e.getCause()).append("\nClass msg was: ").append(e.getMessage());
            sb.append("\nLocal msg: ").append(e.getLocalizedMessage());
            sb.append("\nStack Trace: ");
            e.printStackTrace();
            writeToGui(sb.toString(), GLogLevel.Debug);
//            setSplashStatusValue(100);
        }
    }

    public synchronized void writeToGui(String msg, GLogLevel testLevel) {
        if (afLogLevel.testLessVerbose(testLevel)) {
            return;
        }
        String currText;
        long t = System.currentTimeMillis();
        currText = logPane.getText();
        //afDate.setTime(t);
        //afCal.setTime(afDate);
        //df.setCalendar(afCal);
        afDate = afCal.getTime();
        StringBuilder sb = new StringBuilder(currText);
        sb.append(df.format(afDate));
        sb.append("::").append(testLevel.toString()).append("-->");
        sb.append(msg).append("\n");//.append("\n");


        this.logPane.setText(sb.toString());
    }

    @Action
    public void searchCustomer() {
        showCustDetail(AFAction.AFSearch);
    }

    public void showCustDetail(AFAction afAction) {
        if (afCustDetail == null) {
            JFrame mainFrame = AntiFreezeApp.getApplication().getMainFrame();
            afCustDetail = new AFDetailViewer(afAction, this, mainFrame, this, afMgr);
            afCustDetail.setLocationRelativeTo(mainFrame);
            afCustDetail.setMode(afAction);
            AntiFreezeApp.getApplication().show(afCustDetail);

        } else {
            afCustDetail.setMode(afAction);
            AntiFreezeApp.getApplication().show(afCustDetail);
        }
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = AntiFreezeApp.getApplication().getMainFrame();
            aboutBox = new AF_About(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        AntiFreezeApp.getApplication().show(aboutBox);
    }

    @Action
    public void showShopInfo() {
        if (!appStarted) {
            return;
        }
        if (shopInfoBox == null) {
            JFrame mainFrame = AntiFreezeApp.getApplication().getMainFrame();
            shopInfoBox = new AFShopInfo(this, mainFrame, this, afMgr);
            shopInfoBox.setLocationRelativeTo(mainFrame);

        }
        AntiFreezeApp.getApplication().show(shopInfoBox);
    }

    @Action
    public void showPreferences() {
        if (!appStarted) {
            return;
        }
        if (afPreferences == null) {
            JFrame mainFrame = AntiFreezeApp.getApplication().getMainFrame();
            afPreferences = new AFPreferences(this, mainFrame, this, afMgr);
            afPreferences.setLocationRelativeTo(mainFrame);

        }
        AntiFreezeApp.getApplication().show(afPreferences);
    }

    private void sendStatusMessage(String message, GLogLevel testLevel) {
        if (afLogLevel.testLessVerbose(testLevel)) {
            return;
        }
        this.statusMessageLabel.setText("");
        this.statusMessageLabel.setText(message);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        topAFMgrSplit = new javax.swing.JSplitPane();
        mainWorkPanel = new javax.swing.JPanel();
        logPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logPane = new javax.swing.JTextPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        optionMenu = new javax.swing.JMenu();
        shopInfoItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        preferenceInfoItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        AFDebugPanel = new javax.swing.JToolBar();
        messagePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        statusMessageLabel = new javax.swing.JLabel();
        debugPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        mainButtonPanel = new javax.swing.JPanel();
        ticketStartButton = new javax.swing.JButton();
        addJobButton = new javax.swing.JButton();
        closeTicketButton = new javax.swing.JButton();
        dashPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        brandingPanel = new javax.swing.JPanel();
        AFLogoDefault = new javax.swing.JLabel();
        mainIcon = new javax.swing.JLabel();
        ticketSmall = new javax.swing.JLabel();
        ticketMed = new javax.swing.JLabel();
        ticketLrg = new javax.swing.JLabel();
        afSplashDialog = new javax.swing.JDialog();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(AF_ViewMgr.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 750));
        mainPanel.setLayout(new java.awt.GridLayout(1, 0));

        topAFMgrSplit.setDividerLocation(500);
        topAFMgrSplit.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        topAFMgrSplit.setMinimumSize(new java.awt.Dimension(700, 750));
        topAFMgrSplit.setName("topAFMgrSplit"); // NOI18N
        topAFMgrSplit.setPreferredSize(new java.awt.Dimension(700, 750));
        topAFMgrSplit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                topAFMgrSplitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topAFMgrSplitMouseEntered(evt);
            }
        });
        topAFMgrSplit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                topAFMgrSplitMouseMoved(evt);
            }
        });

        mainWorkPanel.setBackground(resourceMap.getColor("mainWorkPanel.background")); // NOI18N
        mainWorkPanel.setAlignmentX(0.0F);
        mainWorkPanel.setAlignmentY(0.0F);
        mainWorkPanel.setDoubleBuffered(false);
        mainWorkPanel.setFont(resourceMap.getFont("mainWorkPanel.font")); // NOI18N
        mainWorkPanel.setMinimumSize(new java.awt.Dimension(700, 500));
        mainWorkPanel.setName("mainWorkPanel"); // NOI18N
        mainWorkPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        mainWorkPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainWorkPanelMouseEntered(evt);
            }
        });
        mainWorkPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mainWorkPanelMouseMoved(evt);
            }
        });
        mainWorkPanel.setLayout(new java.awt.GridLayout(1, 0));
        topAFMgrSplit.setLeftComponent(mainWorkPanel);

        logPanel.setBackground(resourceMap.getColor("logPanel.background")); // NOI18N
        logPanel.setForeground(resourceMap.getColor("logPanel.foreground")); // NOI18N
        logPanel.setMinimumSize(new java.awt.Dimension(700, 0));
        logPanel.setName("logPanel"); // NOI18N
        logPanel.setPreferredSize(new java.awt.Dimension(700, 250));
        logPanel.setRequestFocusEnabled(false);
        logPanel.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(700, 0));
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 250));
        jScrollPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseMoved(evt);
            }
        });

        logPane.setBackground(resourceMap.getColor("logPane.background")); // NOI18N
        logPane.setForeground(resourceMap.getColor("logPane.foreground")); // NOI18N
        logPane.setMinimumSize(new java.awt.Dimension(700, 0));
        logPane.setName("logPane"); // NOI18N
        logPane.setPreferredSize(new java.awt.Dimension(700, 250));
        logPane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                logPaneMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(logPane);

        logPanel.add(jScrollPane1);

        topAFMgrSplit.setRightComponent(logPanel);

        mainPanel.add(topAFMgrSplit);

        menuBar.setBackground(resourceMap.getColor("menuBar.background")); // NOI18N
        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setBackground(getColorFromAFColor(AFViewMgrInterface.AFColor.LabelBack));
        fileMenu.setForeground(resourceMap.getColor("fileMenu.foreground")); // NOI18N
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getActionMap(AF_ViewMgr.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setBackground(resourceMap.getColor("helpMenu.background")); // NOI18N
        helpMenu.setForeground(resourceMap.getColor("helpMenu.foreground")); // NOI18N
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        optionMenu.setBackground(getColorFromAFColor(AFViewMgrInterface.AFColor.LabelBack));
        optionMenu.setForeground(getColorFromAFColor(AFViewMgrInterface.AFColor.LabelFore));
        optionMenu.setText(resourceMap.getString("optionMenu.text")); // NOI18N
        optionMenu.setName("optionMenu"); // NOI18N
        optionMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                optionMenuMousePressed(evt);
            }
        });
        optionMenu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optionMenuStateChanged(evt);
            }
        });
        optionMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                optionMenuItemStateChanged(evt);
            }
        });

        shopInfoItem.setAction(actionMap.get("showShopInfo")); // NOI18N
        shopInfoItem.setText(resourceMap.getString("shopInfoItem.text")); // NOI18N
        shopInfoItem.setName("shopInfoItem"); // NOI18N
        shopInfoItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                shopInfoItemItemStateChanged(evt);
            }
        });
        optionMenu.add(shopInfoItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        optionMenu.add(jSeparator1);

        preferenceInfoItem.setAction(actionMap.get("showPreferences")); // NOI18N
        preferenceInfoItem.setText(resourceMap.getString("preferenceInfoItem.text")); // NOI18N
        preferenceInfoItem.setName("preferenceInfoItem"); // NOI18N
        optionMenu.add(preferenceInfoItem);

        menuBar.add(optionMenu);

        statusPanel.setMinimumSize(new java.awt.Dimension(800, 45));
        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(800, 45));

        AFDebugPanel.setRollover(true);
        AFDebugPanel.setMinimumSize(new java.awt.Dimension(800, 39));
        AFDebugPanel.setName("AFDebugPanel"); // NOI18N
        AFDebugPanel.setPreferredSize(new java.awt.Dimension(800, 39));

        messagePanel.setBackground(resourceMap.getColor("messagePanel.background")); // NOI18N
        messagePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("messagePanel.border.highlightColor"), resourceMap.getColor("messagePanel.border.shadowColor"))); // NOI18N
        messagePanel.setForeground(resourceMap.getColor("messagePanel.foreground")); // NOI18N
        messagePanel.setMaximumSize(new java.awt.Dimension(300, 37));
        messagePanel.setMinimumSize(new java.awt.Dimension(450, 37));
        messagePanel.setName("messagePanel"); // NOI18N
        messagePanel.setPreferredSize(new java.awt.Dimension(450, 37));

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel1.setName("jLabel1"); // NOI18N

        statusMessageLabel.setFont(resourceMap.getFont("statusMessageLabel.font")); // NOI18N
        statusMessageLabel.setForeground(resourceMap.getColor("statusMessageLabel.foreground")); // NOI18N
        statusMessageLabel.setText(resourceMap.getString("statusMessageLabel.text")); // NOI18N
        statusMessageLabel.setMaximumSize(new java.awt.Dimension(25, 75));
        statusMessageLabel.setMinimumSize(new java.awt.Dimension(25, 75));
        statusMessageLabel.setName("statusMessageLabel"); // NOI18N
        statusMessageLabel.setPreferredSize(new java.awt.Dimension(25, 75));

        org.jdesktop.layout.GroupLayout messagePanelLayout = new org.jdesktop.layout.GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(messagePanelLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(statusMessageLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(messagePanelLayout.createSequentialGroup()
                .add(11, 11, 11)
                .add(messagePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(statusMessageLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE))
                .addContainerGap())
        );

        AFDebugPanel.add(messagePanel);

        debugPanel.setBackground(resourceMap.getColor("debugPanel.background")); // NOI18N
        debugPanel.setMaximumSize(new java.awt.Dimension(600, 37));
        debugPanel.setMinimumSize(new java.awt.Dimension(250, 37));
        debugPanel.setName("debugPanel"); // NOI18N
        debugPanel.setPreferredSize(new java.awt.Dimension(250, 37));
        debugPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                debugPanelMouseReleased(evt);
            }
        });
        debugPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        AFDebugPanel.add(debugPanel);

        org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(AFDebugPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelLayout.createSequentialGroup()
                .add(AFDebugPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        toolBar.setBackground(resourceMap.getColor("toolBar.background")); // NOI18N
        toolBar.setRollover(true);
        toolBar.setMinimumSize(new java.awt.Dimension(699, 65));
        toolBar.setName("toolBar"); // NOI18N
        toolBar.setPreferredSize(new java.awt.Dimension(847, 80));

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        mainButtonPanel.setBackground(resourceMap.getColor("mainButtonPanel.background")); // NOI18N
        mainButtonPanel.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainButtonPanel.setName("mainButtonPanel"); // NOI18N
        mainButtonPanel.setPreferredSize(new java.awt.Dimension(300, 80));
        mainButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        ticketStartButton.setBackground(resourceMap.getColor("TicketStartButton.background")); // NOI18N
        ticketStartButton.setForeground(resourceMap.getColor("TicketStartButton.foreground")); // NOI18N
        ticketStartButton.setIcon(resourceMap.getIcon("TicketStartButton.icon")); // NOI18N
        ticketStartButton.setText(resourceMap.getString("TicketStartButton.text")); // NOI18N
        ticketStartButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ticketStartButton.setDisabledIcon(resourceMap.getIcon("TicketStartButton.disabledIcon")); // NOI18N
        ticketStartButton.setDoubleBuffered(true);
        ticketStartButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ticketStartButton.setIconTextGap(-14);
        ticketStartButton.setName("TicketStartButton"); // NOI18N
        ticketStartButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ticketStartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ticketStartButtonMousePressed(evt);
            }
        });
        mainButtonPanel.add(ticketStartButton);

        addJobButton.setBackground(resourceMap.getColor("AddJobButton.background")); // NOI18N
        addJobButton.setForeground(resourceMap.getColor("AddJobButton.foreground")); // NOI18N
        addJobButton.setIcon(resourceMap.getIcon("AddJobButton.icon")); // NOI18N
        addJobButton.setText(resourceMap.getString("AddJobButton.text")); // NOI18N
        addJobButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addJobButton.setDisabledIcon(resourceMap.getIcon("AddJobButton.disabledIcon")); // NOI18N
        addJobButton.setDoubleBuffered(true);
        addJobButton.setEnabled(false);
        addJobButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addJobButton.setIconTextGap(-14);
        addJobButton.setName("AddJobButton"); // NOI18N
        addJobButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainButtonPanel.add(addJobButton);

        closeTicketButton.setBackground(resourceMap.getColor("CloseTicketButton.background")); // NOI18N
        closeTicketButton.setForeground(resourceMap.getColor("CloseTicketButton.foreground")); // NOI18N
        closeTicketButton.setIcon(resourceMap.getIcon("CloseTicketButton.icon")); // NOI18N
        closeTicketButton.setText(resourceMap.getString("CloseTicketButton.text")); // NOI18N
        closeTicketButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        closeTicketButton.setDisabledIcon(resourceMap.getIcon("CloseTicketButton.disabledIcon")); // NOI18N
        closeTicketButton.setEnabled(false);
        closeTicketButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeTicketButton.setIconTextGap(-14);
        closeTicketButton.setName("CloseTicketButton"); // NOI18N
        closeTicketButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainButtonPanel.add(closeTicketButton);

        jPanel1.add(mainButtonPanel);

        dashPanel.setBackground(resourceMap.getColor("dashPanel.background")); // NOI18N
        dashPanel.setName("dashPanel"); // NOI18N
        dashPanel.setPreferredSize(new java.awt.Dimension(264, 80));

        jButton1.setBackground(resourceMap.getColor("jButton1.background")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("jButton1.border.highlightColor"), resourceMap.getColor("jButton1.border.shadowColor"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(64, 64));
        jButton1.setMinimumSize(new java.awt.Dimension(64, 64));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(64, 64));
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton2.setBackground(resourceMap.getColor("jButton2.background")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorderPainted(false);
        jButton2.setMaximumSize(new java.awt.Dimension(64, 64));
        jButton2.setMinimumSize(new java.awt.Dimension(64, 64));
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(64, 64));
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jButton3.setBackground(resourceMap.getColor("jButton3.background")); // NOI18N
        jButton3.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton3.setForeground(resourceMap.getColor("jButton3.foreground")); // NOI18N
        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorderPainted(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(-1);
        jButton3.setMaximumSize(new java.awt.Dimension(64, 64));
        jButton3.setMinimumSize(new java.awt.Dimension(64, 64));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(64, 64));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        searchButton.setAction(actionMap.get("searchCustomer")); // NOI18N
        searchButton.setBackground(resourceMap.getColor("searchButton.background")); // NOI18N
        searchButton.setFont(resourceMap.getFont("searchButton.font")); // NOI18N
        searchButton.setForeground(resourceMap.getColor("searchButton.foreground")); // NOI18N
        searchButton.setIcon(resourceMap.getIcon("searchButton.icon")); // NOI18N
        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setAlignmentY(0.0F);
        searchButton.setBorderPainted(false);
        searchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchButton.setIconTextGap(-1);
        searchButton.setMaximumSize(new java.awt.Dimension(64, 64));
        searchButton.setMinimumSize(new java.awt.Dimension(64, 64));
        searchButton.setName("searchButton"); // NOI18N
        searchButton.setPreferredSize(new java.awt.Dimension(64, 64));
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchButtonFocusGained(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dashPanelLayout = new org.jdesktop.layout.GroupLayout(dashPanel);
        dashPanel.setLayout(dashPanelLayout);
        dashPanelLayout.setHorizontalGroup(
            dashPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dashPanelLayout.createSequentialGroup()
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(searchButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        dashPanelLayout.setVerticalGroup(
            dashPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .add(searchButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .add(jButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );

        jPanel1.add(dashPanel);

        brandingPanel.setBackground(resourceMap.getColor("brandingPanel.background")); // NOI18N
        brandingPanel.setForeground(getColorFromAFColor(AFViewMgrInterface.AFColor.LabelBack));
        brandingPanel.setMinimumSize(new java.awt.Dimension(80, 80));
        brandingPanel.setName("brandingPanel"); // NOI18N
        brandingPanel.setPreferredSize(new java.awt.Dimension(80, 80));
        brandingPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        AFLogoDefault.setBackground(resourceMap.getColor("AFLogoDefault.background")); // NOI18N
        AFLogoDefault.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AFLogoDefault.setIcon(resourceMap.getIcon("AFLogoDefault.icon")); // NOI18N
        AFLogoDefault.setAlignmentY(0.0F);
        AFLogoDefault.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        AFLogoDefault.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AFLogoDefault.setIconTextGap(0);
        AFLogoDefault.setMaximumSize(new java.awt.Dimension(280, 84));
        AFLogoDefault.setMinimumSize(new java.awt.Dimension(280, 84));
        AFLogoDefault.setName("AFLogoDefault"); // NOI18N
        AFLogoDefault.setPreferredSize(new java.awt.Dimension(280, 84));
        brandingPanel.add(AFLogoDefault);

        jPanel1.add(brandingPanel);

        toolBar.add(jPanel1);

        mainIcon.setIcon(resourceMap.getIcon("mainIcon.icon")); // NOI18N
        mainIcon.setText(resourceMap.getString("mainIcon.text")); // NOI18N
        mainIcon.setName("mainIcon"); // NOI18N

        ticketSmall.setBackground(resourceMap.getColor("ticketSmall.background")); // NOI18N
        ticketSmall.setFont(resourceMap.getFont("ticketSmall.font")); // NOI18N
        ticketSmall.setName("ticketSmall"); // NOI18N

        ticketMed.setBackground(resourceMap.getColor("ticketMed.background")); // NOI18N
        ticketMed.setFont(resourceMap.getFont("ticketMed.font")); // NOI18N
        ticketMed.setName("ticketMed"); // NOI18N

        ticketLrg.setBackground(resourceMap.getColor("ticketLrg.background")); // NOI18N
        ticketLrg.setFont(resourceMap.getFont("ticketLrg.font")); // NOI18N
        ticketLrg.setName("ticketLrg"); // NOI18N

        afSplashDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        afSplashDialog.setTitle(resourceMap.getString("afSplashDialog.title")); // NOI18N
        afSplashDialog.setMinimumSize(new java.awt.Dimension(700, 500));
        afSplashDialog.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        afSplashDialog.setName("afSplashDialog"); // NOI18N
        afSplashDialog.setResizable(false);
        afSplashDialog.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(toolBar);
    }// </editor-fold>//GEN-END:initComponents

    private void debugPanelMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_debugPanelMouseReleased
    {//GEN-HEADEREND:event_debugPanelMouseReleased
        // TODO add your handling code here:
        this.memoryMonitor.statMemory();
    }//GEN-LAST:event_debugPanelMouseReleased

    private void ticketStartButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_ticketStartButtonMousePressed
    {//GEN-HEADEREND:event_ticketStartButtonMousePressed
        // TODO add your handling code here:
        if (ticketWizard == null) {
            JFrame mainFrame = AntiFreezeApp.getApplication().getMainFrame();
            this.ticketStartButton.setEnabled(!afMgr.ticketStarted());
            ticketWizard = new AFTicketWizard(this, mainFrame, this, afMgr);
            ticketWizard.setLocationRelativeTo(mainFrame);
        }
        afMgr.setTicketWiardInf(ticketWizard);
        afMgr.startTicket();
        AntiFreezeApp.getApplication().show(ticketWizard);
    }//GEN-LAST:event_ticketStartButtonMousePressed

    private void optionMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionMenuMousePressed
        // TODO add your handling code here:

        if (shopInfoItem.isSelected()) {
            this.showShopInfo();
        }
    }//GEN-LAST:event_optionMenuMousePressed

    private void shopInfoItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_shopInfoItemItemStateChanged
        // TODO add your handling code here:
        if (evt.getItem().equals(this.shopInfoItem)) {
            this.showShopInfo();
        }

}//GEN-LAST:event_shopInfoItemItemStateChanged

    private void optionMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optionMenuItemStateChanged
        // TODO add your handling code here:
        if (evt.getItem().equals(this.shopInfoItem)) {
            this.showShopInfo();
        }
    }//GEN-LAST:event_optionMenuItemStateChanged

    private void optionMenuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optionMenuStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_optionMenuStateChanged

    private void reportMouse(java.awt.event.MouseEvent evt, String eventDesc) {
        int locX = evt.getX();
        int locY = evt.getY();
        Component repComp = evt.getComponent();

        String reporter = repComp.getName();
        StringBuilder sm = new StringBuilder(eventDesc);
        sm.append(reporter);
        sm.append("at - X:").append(locX);
        sm.append(" Y: ").append(locY);
        sendStatusMessage(sm.toString(), GLogLevel.Message);
    }

    private void topAFMgrSplitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAFMgrSplitMouseEntered
        // TODO add your handling code here:
        reportMouse(evt, "Mouse entered ");

    }//GEN-LAST:event_topAFMgrSplitMouseEntered

    private void topAFMgrSplitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAFMgrSplitMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        int upperSplitLocation = topAFMgrSplit.getDividerLocation();
        // int lowerSplitLocation = lowerUDTMgrSplit.getDividerLocation();
        int locX = evt.getX();
        int locY = evt.getY();
        reportMouse(evt, "Mouse clicked ");

        if (locY <= 115) {
            if (!upperIsCollaped) {
                topAFMgrSplit.setDividerLocation(0);
                topAFMgrSplit.setToolTipText("click here to expand ticket panel");
                upperIsCollaped = true;
            } else {
                topAFMgrSplit.setDividerLocation((int) mainWorkPanel.getPreferredSize().getHeight());
                topAFMgrSplit.setToolTipText("click here to collaspe ticket panel");
                upperIsCollaped = false;
            }
        } else {
            if (!lowerIsCollaped) {
                // lowerUDTMgrSplit.setDividerLocation(this.getHeight());
                topAFMgrSplit.setToolTipText("click here to expand the log panel");
                lowerIsCollaped = true;
            } else {
                // lowerUDTMgrSplit.setDividerLocation(0);
                topAFMgrSplit.setToolTipText("click here to collaspe the log panel");
                lowerIsCollaped = false;
            }
        }
    }//GEN-LAST:event_topAFMgrSplitMouseClicked

    private void topAFMgrSplitMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topAFMgrSplitMouseMoved
        // TODO add your handling code here:
        reportMouse(evt, "Mouse tracked in ");
    }//GEN-LAST:event_topAFMgrSplitMouseMoved

    private void jScrollPane1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseMoved
        // TODO add your handling code here:
        reportMouse(evt, "Mouse tracked in ");
    }//GEN-LAST:event_jScrollPane1MouseMoved

    private void mainWorkPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainWorkPanelMouseMoved
        // TODO add your handling code here:
        reportMouse(evt, "Mouse tracked in ");
    }//GEN-LAST:event_mainWorkPanelMouseMoved

    private void mainWorkPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainWorkPanelMouseEntered
        // TODO add your handling code here:
        reportMouse(evt, "Mouse entered ");
    }//GEN-LAST:event_mainWorkPanelMouseEntered

    private void logPaneMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logPaneMouseMoved
        // TODO add your handling code here:
        reportMouse(evt, "Mouse tracked in ");
    }//GEN-LAST:event_logPaneMouseMoved

    private void searchButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchButtonFocusGained
        // TODO add your handling code here:
        Color oldColor = searchButton.getForeground();
        searchButton.setForeground(Color.red);
    }//GEN-LAST:event_searchButtonFocusGained
    public boolean showPopupChoice(WMSEvent evt) {
        // try
        // and
        // see
        // if
        // the
        // evt
        // was
        // sent
        // by a
        // panel
        JPanel target = this.mainPanel;
        JPanel panel = null;
        boolean result = false;
        try {
            panel = JPanel.class.cast(evt.getSource());
        } catch (ClassCastException e) {
//            log.write("Exception: " + e.getMessage());
        }
        if (panel != null) {
            target = panel;
        }
        if (JOptionPane.showConfirmDialog(target, evt.getUserMessage(), "AntiFreeze " + " Question?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null) == 0) {
            result = true;
        }
        return result;
    }

    public boolean showPopupChoice(WMSEvent evt, Icon icon, String choiceOne, String choiceTwo) {
        // try
        // and
        // see
        // if
        // the
        // evt
        // was
        // sent
        // by a
        // panel
        JPanel target = mainPanel;
        JPanel panel = null;
        String[] options = new String[2];
        options[0] = choiceOne;
        options[1] = choiceTwo;

        boolean result = false;
        try {
            panel = JPanel.class.cast(evt.getSource());
        } catch (ClassCastException e) {
//            log.write("Exception: " + e.getMessage());
        }
        if (panel != null) {
            target = this.mainPanel;
        }

        if (JOptionPane.showOptionDialog(target, evt.getUserMessage(), afMgr.getProductName() + "Question ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, 0) == 0) {
            result = true;
        }
        return result;
    }

    public void showPopup(WMSEvent evt) {
        // try
        // and
        // see
        // if
        // the
        // evt
        // was
        // sent
        // by a
        // panel
        JPanel target = mainPanel;
        JPanel panel = null;
        try {
            panel = JPanel.class.cast(evt.getSource());
        } catch (ClassCastException e) {
//            log.write("Exception: " + e.getMessage());
        }
        if (panel != null) {
            target = panel;
        }
        if (evt.getMessage() == WMSMessage.POPERR) {
            JOptionPane.showMessageDialog(target, evt.getUserMessage(), afMgr.getProductName() + " Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (evt.getMessage() == WMSMessage.POPWARN) {
            JOptionPane.showMessageDialog(target, evt.getUserMessage(), afMgr.getProductName() + " Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else if (evt.getMessage() == WMSMessage.POPMESS) {
            JOptionPane.showMessageDialog(target, evt.getUserMessage(), afMgr.getProductName() + " Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (evt.getMessage() == WMSMessage.POPDEBUG) {
            if (getLevel().testMoreVerbose(level)) {
                JOptionPane.showMessageDialog(target, evt.getUserMessage(), afMgr.getProductName() + " Debug",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar AFDebugPanel;
    private javax.swing.JLabel AFLogoDefault;
    private javax.swing.JButton addJobButton;
    private javax.swing.JDialog afSplashDialog;
    private javax.swing.JPanel brandingPanel;
    private javax.swing.JButton closeTicketButton;
    private javax.swing.JPanel dashPanel;
    private javax.swing.JPanel debugPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane logPane;
    private javax.swing.JPanel logPanel;
    private javax.swing.JPanel mainButtonPanel;
    private javax.swing.JLabel mainIcon;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainWorkPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel messagePanel;
    private javax.swing.JMenu optionMenu;
    private javax.swing.JMenuItem preferenceInfoItem;
    private javax.swing.JButton searchButton;
    private javax.swing.JMenuItem shopInfoItem;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel ticketLrg;
    private javax.swing.JLabel ticketMed;
    private javax.swing.JLabel ticketSmall;
    private javax.swing.JButton ticketStartButton;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JSplitPane topAFMgrSplit;
    // End of variables declaration//GEN-END:variables
//    private final Timer messageTimer;
//    private final Timer busyIconTimer;
//    private final Icon idleIcon;
//    private final Icon[] busyIcons = new Icon[15];
//    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private AFTicketWizard ticketWizard;
    private AFDetailViewer afCustDetail;
    private AFShopInfo shopInfoBox;
    private AFPreferences afPreferences;
    private JFrame parentFrame;
    private GLogLevel afLogLevel = GLogLevel.NotSet;
    private DateFormat df = null;
    private Date afDate = null;
    private Calendar afCal = null;
    private ProgressMonitor progressMonitor;
    private ProgressMonitor splashMonitor;
//    private static AFJob progressJob;
//    private static AFProgressPanel initProgressPanel;
    private boolean upperIsCollaped = false;
    private boolean lowerIsCollaped = false;
    private Color primaryColor = Color.GRAY;
    private Color secondaryColor = Color.ORANGE;
    private static SCPJob progressJob;

    public void setLevel(GLogLevel newLevel) {
        afLogLevel = newLevel;
        GLogLevel.setLevel(newLevel);
    }

    public GLogLevel getLevel() {
        return GLogLevel.getLevel();
    }

    public boolean moreVerbose(GLogLevel testLevel) {
        return GLogLevel.testMoreVerbose(testLevel);
    }

    public boolean lessVerbose(GLogLevel testLevel) {
        return GLogLevel.testLessVerbose(testLevel);
    }

//    public static void setProgressJob(AFJob startupJob) {
////      appStartupJob = startupJob;
//        jobThread = new Thread(startupJob);
//        jobThread.start();
//    }
    public void setSplashStatusNote(String note) {
        afStarter.setNote(note);
    }

    public void setSplashStatus(String message) {
        // appStartupJob.setNote(message);
        afStarter.setMessage(message);
    }

    public void setSplashStatusValue(int value) {
//       afStarter.setValue( value );
        afStarter.setValue(value);
    }

    public void dismissSplash() {
        afStarter.stop();
        afStarter.setVisible(false);
        afSplashDialog.setVisible(false);
        afSplashDialog.dispose();
    }

    public void jobComplete() {
    }

    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
