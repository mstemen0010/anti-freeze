/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AFTicketPanel.java
 *
 * Created on Aug 20, 2009, 8:37:07 AM
 */
package com.wms.antifreeze.gui;


import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.antifreeze.AFMgr;
import com.wms.antifreeze.entity.car.AFCarEntity;
import com.wms.antifreeze.entity.ticket.AFTicket;
import com.wms.antifreeze.entity.ticket.AFTicketEntity;
import com.wms.util.gui.GUILogInterface;
import com.wms.antifreeze.view.AFTicketView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Matt
 */
public class AFTicketBox extends javax.swing.JPanel {

    private AFMgr afMgr;
    private AFViewMgrInterface afViewMgr;
    private GUILogInterface gLog;
    private int tabIndex;
    private boolean stubTabRemoved = false;
    private HashMap<Integer, AFTicketInf> afTickets = new HashMap<Integer, AFTicketInf>();
    private AFTicketInf currentTicket;
    private Color highLightColor = Color.YELLOW.brighter();
    private Color previousColor = Color.GRAY;
    int prevIndex = -1;
    /** Creates new form AFTicketPanel
     */
    int stubCarId = 250;
    int stubCusId = 222;
    int stubEstId = 770;

    public enum TicketType {

        Initialized,
        Estimate,
        Invoice,
        InHouse,
        NonCar;
    }

    public AFTicketBox(GUILogInterface log, java.awt.Frame parent, AFViewMgrInterface afViewMgr, AFMgr afMgr) {
        this.afMgr = afMgr;
        this.afViewMgr = afViewMgr;
        this.gLog = log;       
        initComponents();
        this.getStartupHTML();
    }

    /**
     * 
     * @param ticketNum
     * @param tabInfo
     * @return 
     */
//    public synchronized void loadOutStandingTickets() {
//
//        gLog.writeToGui("Loading existing Tickets from previous day...", GUILogInterface.GLogLevel.Message);
//        VRecord tickRec;
//        VTable vt = afMgr.getVTable("ticket");
//        if (vt != null) {
//            // Vector<VRecord> recs = vt.getRows();
//            Iterator<VRecord> recs = vt.iterator();
//            vt.setOrderByFieldName("ticketNum");
//            // for( int i = 0; i < recs.size(); i++ )
//            while (recs.hasNext()) {
//                tickRec = recs.next();
//                // tickRec = recs.elementAt(i);
//                String stageStr = tickRec.getFieldData("stage");
//                int stage = Integer.parseInt(stageStr);
//                String rowKey = tickRec.getFieldData("ticketNum");
//                String carcomment = tickRec.getFieldData("carcomment");
//                int rowId = Integer.parseInt(rowKey);
//                if (stage < TicketStage.Closed.ordinal() && rowId != 1) {
//                    addTicket(rowId, carcomment);
//                }
//            }
//        }
//    }

    public AFTicketInf addTicket(int ticketNum, String tabInfo) {
        AFTicketInf afTicket = null;
        gLog.writeToGui("Adding ticketNum: " + ticketNum, GUILogInterface.GLogLevel.Debug);
        // parse the make model year from the comment       
        String make = "";
        String model = "";
        String year = "";
        if (tabInfo != null) {
            StringTokenizer st = new StringTokenizer(tabInfo, "/");
            make = st.nextToken();
            model = st.nextToken();
            year = st.nextToken();
        }

        //first get rid of the "stub" ticket, the default one
        if (ticketTabs.getTabCount() == 1 && !stubTabRemoved) {
            ticketTabs.remove(0);
            stubTabRemoved = true;
        }
//            int newTicketNum = AFTicketEntity.getNextTicketNumber();
        AFCarEntity carEntity = new AFCarEntity(make, model, year);

        AFTicketEntity ticketEntity = new AFTicketEntity(ticketNum);
        //TODO: Fix the following by replacing the "null" wtih a real AFCustomer
        AFTicketView ticketView = new AFTicketView(ticketEntity, ticketNum, carEntity, null);

        afTicket = new AFTicket(ticketEntity, ticketView);
        afTickets.put(afTicket.getTicketNumber(), afTicket);
        ticketView.fillInCarInfo();
        Component ticketComp = ticketView.getComponent();
        // insert it in the "Ticket Book"
        {
            int newTabIndex = getAvailableTabIndex();
            Icon tabIcon = this.allRequired.getIcon();
            ticketTabs.insertTab(ticketView.getTabTitle(), null, ticketComp, TOOL_TIP_TEXT_KEY, newTabIndex);
            colorTab(newTabIndex);
            // ticketTabs.addTab(newTicket.getTabTitle(), ticketTabIcon.getIcon(), ticketComp, TOOL_TIP_TEXT_KEY);
            //ticketTabs.setIconAt(tabIndex, ticketTabIcon.getIcon());
            // Component c = ticketTabs.getTabComponentAt(newTabIndex);
            this.currentTicket = afTicket;
            displayPaperTicket(newTabIndex, ticketView, ticketComp);
        }
        return afTicket;
    }

    private void colorTab(int indexToColor) {
        boolean isOdd = (indexToColor % 2) > 0;
        if (isOdd) {
            ticketTabs.setBackgroundAt(indexToColor, this.afViewMgr.getColorFromAFColor(AFViewMgrInterface.AFColor.MenuFore));
        } else {
            ticketTabs.setBackgroundAt(indexToColor, Color.white.darker());
        }
    }

    private int getAvailableTabIndex() {
        int availTabIndex = 0;
        int tabCount = ticketTabs.getTabCount();
        availTabIndex = tabCount;
        return availTabIndex;
    }

    public void displayPaperTicket(int index, AFTicketView ticket, Component c) {
        JPanel paperTicket = ticket.getPaperTicket();
        this.afViewMgr.setCurrentTicket(currentTicket);
        ticketTabs.setComponentAt(index, c);


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ticketTabIcon = new javax.swing.JLabel();
        allRequired = new javax.swing.JLabel();
        ticketTabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        welcomePane = new javax.swing.JEditorPane();

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(AFTicketBox.class);
        ticketTabIcon.setIcon(resourceMap.getIcon("ticketTabIcon.icon")); // NOI18N
        ticketTabIcon.setLabelFor(ticketTabIcon);
        ticketTabIcon.setName("ticketTabIcon"); // NOI18N

        allRequired.setIcon(resourceMap.getIcon("allRequired.icon")); // NOI18N
        allRequired.setLabelFor(ticketTabIcon);
        allRequired.setName("allRequired"); // NOI18N

        setBackground(afViewMgr.getColorFromAFColor(AFViewMgrInterface.AFColor.OutterPanel));
        setName("Form"); // NOI18N
        setLayout(new java.awt.GridLayout(1, 0));

        ticketTabs.setBackground(afViewMgr.getColorFromAFColor(AFViewMgrInterface.AFColor.InnerPanel));
        ticketTabs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ticketTabs.setForeground(resourceMap.getColor("ticketTabs.foreground")); // NOI18N
        ticketTabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        ticketTabs.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        ticketTabs.setAlignmentX(0.0F);
        ticketTabs.setAlignmentY(0.0F);
        ticketTabs.setDoubleBuffered(true);
        ticketTabs.setName("ticketTabs"); // NOI18N
        ticketTabs.setOpaque(true);
        ticketTabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ticketTabsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ticketTabsMouseExited(evt);
            }
        });
        ticketTabs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ticketTabsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ticketTabsFocusLost(evt);
            }
        });

        jPanel2.setName("ticketPanel"); // NOI18N
        jPanel2.setVerifyInputWhenFocusTarget(false);

        jLabel1.setBackground(resourceMap.getColor("jLabel1.background")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        welcomePane.setEditable(false);
        welcomePane.setName("welcomePane"); // NOI18N
        jScrollPane1.setViewportView(welcomePane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1983, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        ticketTabs.addTab(resourceMap.getString("ticketPanel.TabConstraints.tabTitle"), resourceMap.getIcon("ticketPanel.TabConstraints.tabIcon"), jPanel2, ""); // NOI18N

        add(ticketTabs);
    }// </editor-fold>//GEN-END:initComponents

    private void tabFloatIn(MouseEvent evt)
    {
        int hi = this.ticketTabs.indexAtLocation(evt.getX(), evt.getY());
        if( hi < 0 )
            return;
        this.gLog.writeToGui("Focused gained at: " + hi , GUILogInterface.GLogLevel.Debug);
        previousColor = this.ticketTabs.getBackgroundAt(hi);
        prevIndex = hi;
        this.ticketTabs.setBackgroundAt(hi, highLightColor);
    }
    private void tabFloatOut(MouseEvent evt)
    {
        if( prevIndex < 0 )
            return;
        this.gLog.writeToGui("Focused Lost at: " + prevIndex , GUILogInterface.GLogLevel.Debug);
        this.ticketTabs.setBackgroundAt(prevIndex, this.previousColor);
    }
    private void ticketTabsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ticketTabsFocusGained
        // TODO add your handling code here:        
        
    }//GEN-LAST:event_ticketTabsFocusGained

    private void ticketTabsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ticketTabsFocusLost
        // TODO add your handling code here:        
    }//GEN-LAST:event_ticketTabsFocusLost

    private void ticketTabsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketTabsMouseEntered
        tabFloatIn(evt);
    }//GEN-LAST:event_ticketTabsMouseEntered

    private void ticketTabsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketTabsMouseExited
        // TODO add your handling code here:
        tabFloatOut(evt);
    }//GEN-LAST:event_ticketTabsMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allRequired;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ticketTabIcon;
    private javax.swing.JTabbedPane ticketTabs;
    private javax.swing.JEditorPane welcomePane;
    // End of variables declaration//GEN-END:variables

    public void getStartupHTML() {
        try {
            URL startupURL = new URL("file:///G:/dev/java/Projects/AntiFrieze/src/com/wms/antifrieze/gui/html/TicketBookWelcome.html");
            HTMLDocument welcomeDoc = new HTMLDocument();
            welcomeDoc.setBase(startupURL);
            HTMLEditorKit htmlEditKit = new HTMLEditorKit();
            welcomePane.setEditorKit(htmlEditKit);

//            HTMLEditorKit
        } catch (MalformedURLException ex) {
            Logger.getLogger(AFTicketBox.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
