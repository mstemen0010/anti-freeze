/*
 * AntiFriezeAboutBox.java
 */
package com.wms.antifreeze.gui;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import com.wms.antifreeze.AFMgrInf;
import com.wms.antifreeze.AFMgrInf.ManagerType;
import com.wms.antifreeze.AFMgr;
import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.antifreeze.gui.AFViewMgrInterface.AFAction;
import com.wms.antifreeze.gui.AF_ViewMgr.AFIcon;
import com.wms.antifreeze.view.AFCustomerView;
import com.wms.jdbtk3.VDatabase;
import com.wms.util.gui.GUILogInterface;
import com.wms.util.gui.canvas.HyperCanvas;
import com.wms.util.xmltools.WMSNodeParser;
import java.awt.Graphics;
import java.io.File;
import java.util.HashMap;
import javax.swing.ImageIcon;
import org.jdesktop.application.Action;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.wms.util.gui.canvas.PolyImage;
import com.wms.util.gui.canvas.Polygon;
import com.wms.util.gui.message.WMSEvent;
import com.wms.util.gui.message.WMSMessage;
import java.awt.Point;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import org.w3c.dom.NamedNodeMap;

public class AFTicketWizard extends javax.swing.JDialog implements GUILogInterface, AF_JobNavInterface, AFTicketInf
{

    private static final long serialVersionUID = -2615761412980073167L;
    private GUILogInterface log;
    HyperCanvas engineCanvas = null;
    private HashMap<String, PolyImage> imageMap = new HashMap<String, PolyImage>();
    private ImageIcon engineOverImage = null;
    VDatabase afDb = null;
    AFMgr afMgr = null;
    String yearValue = null;
    String makeValue = null;
    String modelValue = null;
    AFCustomer afCus = null;
    boolean modelFound = false;
    AFMgrInf myMgr = null;
    AFCustomerView customerView = null;
    StringBuilder firstDisplayName = new StringBuilder();
    StringBuilder lastDisplayName = new StringBuilder();

    public void startSectionToGui(String sectionId, GLogLevel writeLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void endSectionToGui(String sectionId, GLogLevel writeLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFAction getAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getTicketNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void processTicketForward() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showCustIndicator(boolean show) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showCarIndicator(boolean show) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void showEstIndicator(boolean show) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void displayCustName(String firstName, String lastName) {
        this.custFirstName.setText( firstName );
        this.CusLastName.setText(lastName);
        this.CusFirstName.validate();
        this.CusLastName.validate();
        this.customerNamePanel.repaint();
    }

    public void displayMakeModelYear(String make, String model, String year )
    {
        this.carMake.setText(make);
        this.carMake.validate();
        this.carModel.setText(model);
        this.carModel.validate();
        this.carYear.setText(year);
        this.carYear.validate();
    }

    public void displayInvoiceNumber(String invoiceNum) {
        this.AFTicketNumber.setText(invoiceNum);
        this.AFTicketNumber.validate();
        this.AFTicketNumber.repaint();
    }

    public void displayTicketInfo(String invoiceNum, String first, String last, String make, String model, String year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTicketCus(AFCustomer afCus) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void createNewTicket(String make, String model, String year, AFCustomer afCus) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCusId(int cusId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCarId(int carId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketEstId(int estId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private enum TicketStage
    {
        MakeModelSelect,
        CustomerInfoSelect,
        JobSelect,
        EstimateSelect,
        Close;
    }

    private AFViewMgrInterface afViewInf = null;
    private TicketStage ticketStage = TicketStage.MakeModelSelect;

    public AFTicketWizard(GUILogInterface log, java.awt.Frame parent, AFViewMgrInterface afViewMgr, AFMgr afMgr )
    {
        super(parent);        
        this.log = log;
        this.afMgr = afMgr;
        this.afViewInf = afViewMgr;

        //TODO: move Log Level when done
        //log.setLevel(GLogLevel.GUI_Trace);
        log.setLevel(GLogLevel.GUI_Trace);

        initComponents();
        // enginePanel.add(this.navGraphics);
        getRootPane().setDefaultButton(closeButton);        // engineMainIcon.setIcon(resourceMap.getIcon("engineMainIcon.icon")); // NOI18N
        // this.engineOverImage = new ImageIcon(this.engineMainIcon.getIcon());
        // read the xml description of the HyperCanvas
        // File xsdFile = new File("src/com/wms/antifrieze/gui/resources/PolyImageMapXmlSchema.xsd");
        // File engineXmlFile = new File("src/com/wms/antifrieze/gui/resources/polymap/engine/EngineJobsPolyMap.xml");
        // for testing only


//        engineCanvas = new HyperCanvas(this.enginePanel.getGraphics(), this, engineIm.getImage());
//        engineCanvas.setCreateMode();
//        this.enginePanel.add(engineCanvas);
//        engineCanvas.refresh();
        myMgr = afMgr.getManagerForType(ManagerType.Ticket);
        customerView = new AFCustomerView(AFAction.AFCreate);
        customerInfoPanel.removeAll();
        customerInfoPanel.add( customerView.getPanel() );
        displayTicketTab();
    }

    public void resetTicket()
    {
        ticketStage = TicketStage.MakeModelSelect;
    }
    private void displayTicketTab()
    {
        switch( ticketStage )
        {
            case CustomerInfoSelect:
                this.afJobWizardTabs.setSelectedIndex(0);
                afJobWizardTabs.setSelectedIndex(TicketStage.CustomerInfoSelect.ordinal());
                break;
            case MakeModelSelect:
                this.afJobWizardTabs.setSelectedIndex(1);
                afJobWizardTabs.setSelectedIndex(TicketStage.MakeModelSelect.ordinal());
                showYearMakeChoices();
                break;
            case JobSelect:
                this.afJobWizardTabs.setSelectedIndex(2);
                afJobWizardTabs.setSelectedIndex(TicketStage.JobSelect.ordinal());
                showJobChoices();
                break;
        }
    }

    private void enableNext()
    {
        this.nextInTicket.setEnabled(modelFound);        
    }

    private void showModelForYearAndMake()
    {
        Iterator<String> i = afMgr.getModelForYearAndMake(yearValue, makeValue);
        DefaultListModel modelListModel = new DefaultListModel();
        if( i == null )
            return;
        while( i.hasNext() )
        {
            String modelStr = i.next();
            modelListModel.addElement(modelStr);

        }
        if( modelListModel != null && modelListModel.getSize() > 0 )
        {
            this.modelFound = true;
        }
        else
        {
            this.modelFound = false;
        }
        this.activateEnterNewVechicle();
        modelList.removeAll();
        modelList.setModel(modelListModel);
    }
    private void showYearMakeChoices()
    {
        Iterator<String> i = afMgr.getYears();
        Iterator<String> in = afMgr.getMakes();
        DefaultListModel yearListModel = new DefaultListModel();
        DefaultListModel makeListModel = new DefaultListModel();
        while( i.hasNext() )
        {
            String yearStr = i.next();
            yearListModel.addElement(yearStr);
        }
        while( in.hasNext())
        {
            String makeStr = in.next();      
            makeListModel.addElement(makeStr);
        }

        
        yearList.removeAll();
        makeList.removeAll();
        makeList.setModel(makeListModel);
        yearList.setModel(yearListModel);
    }

    private void showJobChoices()
    {
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(AFTicketWizard.class);

        ImageIcon engineIm = resourceMap.getImageIcon("engineBaseImage.icon");
        File xsdFile = new File("src/com/wms/antifrieze/gui/resources/PolyMapSchema.xsd");

        File engineXmlFile = new File("src/com/wms/antifrieze/gui/resources/PolyMapSchema.xml");
        NodeList engineList = null;
        NodeList parsedList = null;

        WMSNodeParser nodeParser = new WMSNodeParser(null, engineXmlFile, "PolyMapGroups");
        NodeList polyMapGroupList = null;
        polyMapGroupList = nodeParser.getList(NodeStructure.PolyMapGroup.name());

        int cCount = polyMapGroupList.getLength();

        engineCanvas = new HyperCanvas(this.enginePanel.getGraphics(), this, engineIm.getImage());
        engineCanvas.setCreateMode();
        this.enginePanel.add(engineCanvas);
        engineCanvas.refresh();

        if (polyMapGroupList != null) {
            writeToGui("Processing (" + cCount + ") Nodes", GLogLevel.Debug);
            buildPolyMap(polyMapGroupList);

        }

    }
    private void buildPolyMap(NodeList pmNodes)
    {
        for (int i = 0; i < pmNodes.getLength(); i++) {
            Node polyGroup = pmNodes.item(i);
            int nc = polyGroup.getChildNodes().getLength();
            NodeList children = polyGroup.getChildNodes();
//            Node n1 = children.item(0);
//            Node n2 = children.item(1);
//            Node n3 = children.item(2);
//            Node n4 = children.item(3);
//            Node n5 = children.item(4);
//            Node n6 = children.item(5);
//            Node n7 = children.item(6);
            DeferredElementImpl ci = null;
            // DeferredElementImpl
            String polyName = null;
            PolyImage pm = null;
            for (int j = 0; j < nc; j++) {
                Node n = children.item(j);
                if (DeferredElementImpl.class.isInstance(n)) {

                    ci = DeferredElementImpl.class.cast(n);

                    String tn = ci.getTagName();
                    if (tn.equalsIgnoreCase("GroupName")) {
                        polyName = ci.getTextContent();
                        writeToGui("-------------------------------", GLogLevel.Trace);
                        writeToGui("Found new PolyGroup: " + polyName, GLogLevel.Trace);

                    }
                    else if (tn.equalsIgnoreCase("GroupArea")) {
                        String s = ci.getTextContent().replace("\n", ",");
                        NodeList points = ci.getChildNodes();
                        if (points != null) {
                            Polygon pg = new Polygon();
                            for (int k = 0; k < points.getLength(); k++) {
                                Node no = points.item(k);
                                if( DeferredElementImpl.class.isInstance(no))
                                {
                                    NamedNodeMap nm = no.getAttributes();
                                    Node xn = nm.getNamedItem("x");
                                    Node yn = nm.getNamedItem("y");
                                    String xs = xn.getTextContent();
                                    String ys = yn.getTextContent();
                                    writeToGui("Got Point: " + xs + "," + ys, GLogLevel.Trace);
                                    int x = Integer.parseInt(xs);
                                    int y = Integer.parseInt(ys);
                                    Point p = new Point(x,y);
                                    pg.add(p);
                                }
                            }
                            pm = new PolyImage(polyName, pg);
                            pm.closePolygon();
                            this.imageMap.put(polyName, pm);
                            engineCanvas.setImageMap(pm);

                            writeToGui("GroupArea is:" + pm.toString(), GLogLevel.Trace);
                        }                                                
                    }
                    else if (tn.equalsIgnoreCase("GroupImage")) {
                        String s = ci.getTextContent();
                        pm.setImageName(s);
                        writeToGui("GroupImage is:" + s, GLogLevel.Trace);
                        writeToGui("-------------------------------\n", GLogLevel.Trace);

                    }
                }
            }

            if (polyGroup != null) {
                Node child = polyGroup.getFirstChild();
                if (child != null) {
                    Node c2 = child.getNextSibling();
                    if (c2 != null) {
                        String s = c2.getTextContent();
                        writeToGui("Text is: " + s, GLogLevel.Trace);
                    }
                }
            }
        }
    }

    private Node findInTreeByData(NodeList tree, String targetData)
    {
        Node retNode = null;
        Node foundNode = null;
        int nodeIndex = 0;
        String d = null;

        while (nodeIndex < tree.getLength()) {
            foundNode = tree.item(nodeIndex);
            if (foundNode != null) {
                d = foundNode.getTextContent();
            }
        }


        return retNode;
    }

    private Node walkToNextPopNode(NodeList listToWalk)
    {
        Node popNode = null;
        String popNodeName = null;
        if (listToWalk == null) {
            nodeWalkCount = -1;
            return null;
        }

        if (nodeWalkCount == -1) {
            nodeWalkCount = 0;
        }
        else if (nodeWalkCount > listToWalk.getLength()) {
            nodeWalkCount = -1;
            return null;
        }
        while (popNodeName == null) {
            popNode = listToWalk.item(nodeWalkCount);

            if (popNode != null) {
                String ts = popNode.getNodeName();
                if (ts != null && ts.length() > 0) {
                    popNodeName = popNode.getNodeName();
                }
            }
            nodeWalkCount++;
        }
        return popNode;
    }

    public void writeToGui(String msg, GLogLevel testLevel)
    {
        log.writeToGui(msg, testLevel);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if( engineCanvas != null )
            engineCanvas.refresh();
    }

    @Action
    public void closeAboutBox()
    {
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    private void testCancelTicket()
    {
       if( afMgr.ticketStarted())
        {
            boolean reply = afViewInf.showPopupChoice(new WMSEvent( this, WMSMessage.POPYESNO, "Do you wish to cancel this Ticket?"), afViewInf.getIcon(AFIcon.CloseIcon), "Yes", "No");
            if(reply)
            {
               afMgr.stopTicket();
               resetTicket();
               afViewInf.sendMessage(new WMSEvent(this, WMSMessage.RESETBUTTON, "TicketStartButton"));
               displayTicketTab();
               this.dispose();
            }
        }
    }

    private void activateEnterNewVechicle()
    {
        this.customVechicle.setEnabled(! modelFound);
        this.newMakeLabel.setEnabled(! modelFound);
        this.makeField.setEnabled(! modelFound);
        this.newYearLabel.setEnabled(! modelFound);
        this.yearField.setEnabled(! modelFound);
        this.newModelLabel.setEnabled(! modelFound);
        this.modelField.setEnabled(! modelFound);
    }

 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        engineTransmission = new javax.swing.JLabel();
        engineBaseImage = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        ticketInfoPanel = new javax.swing.JPanel();
        ticketNumberPanel = new javax.swing.JPanel();
        invoiceNumber = new javax.swing.JLabel();
        AFTicketNumber = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        carIMakeModelPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        carMake = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        carModel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        carYear = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        customerNamePanel = new javax.swing.JPanel();
        custFirstName = new javax.swing.JLabel();
        custLastName = new javax.swing.JLabel();
        ticketPanel = new javax.swing.JPanel();
        afJobWizardTabs = new javax.swing.JTabbedPane();
        customerInfoPanel = new javax.swing.JPanel();
        AFCustomerViewPort = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        CusFirstName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CusLastName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        afCityField = new javax.swing.JTextField();
        afZipField = new javax.swing.JTextField();
        afStreetAddrField1 = new javax.swing.JTextField();
        afStreetAddrField2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        actionButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        afPhoneField = new javax.swing.JFormattedTextField();
        afCellField = new javax.swing.JFormattedTextField();
        afFaxField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        CustID = new javax.swing.JTextField();
        yearMakeSelectPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        nextInTicket = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        modelList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        makeList = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        yearList = new javax.swing.JList();
        customVechicle = new javax.swing.JPanel();
        newModelLabel = new javax.swing.JLabel();
        modelField = new javax.swing.JTextField();
        newYearLabel = new javax.swing.JLabel();
        yearField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        newMakeLabel = new javax.swing.JLabel();
        makeField = new javax.swing.JTextField();
        jobSelectPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        worksPanel = new javax.swing.JPanel();
        generalJobsLabel = new javax.swing.JLabel();
        enginePanel = new javax.swing.JPanel();
        engineJobsLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        cancelTicketButton = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(AFTicketWizard.class);
        engineTransmission.setIcon(resourceMap.getIcon("engineTransmission.icon")); // NOI18N
        engineTransmission.setText(resourceMap.getString("engineTransmission.text")); // NOI18N
        engineTransmission.setName("engineTransmission"); // NOI18N

        engineBaseImage.setIcon(resourceMap.getIcon("engineBaseImage.icon")); // NOI18N
        engineBaseImage.setText(resourceMap.getString("engineBaseImage.text")); // NOI18N
        engineBaseImage.setName("engineBaseImage"); // NOI18N

        jDialog1.setName("jDialog1"); // NOI18N

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setTitle(resourceMap.getString("title")); // NOI18N
        setModal(true);
        setName("jobSelect"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        ticketInfoPanel.setBackground(resourceMap.getColor("ticketInfoPanel.background")); // NOI18N
        ticketInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ticketInfoPanel.setMinimumSize(new java.awt.Dimension(800, 20));
        ticketInfoPanel.setName("ticketInfoPanel"); // NOI18N
        ticketInfoPanel.setPreferredSize(new java.awt.Dimension(800, 20));
        ticketInfoPanel.setLayout(new java.awt.GridBagLayout());

        ticketNumberPanel.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.TicketBack));
        ticketNumberPanel.setMinimumSize(new java.awt.Dimension(190, 31));
        ticketNumberPanel.setName("ticketNumberPanel"); // NOI18N
        ticketNumberPanel.setPreferredSize(new java.awt.Dimension(190, 31));
        ticketNumberPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        invoiceNumber.setFont(resourceMap.getFont("invoiceNumber.font")); // NOI18N
        invoiceNumber.setText(resourceMap.getString("invoiceNumber.text")); // NOI18N
        invoiceNumber.setName("invoiceNumber"); // NOI18N
        ticketNumberPanel.add(invoiceNumber);

        AFTicketNumber.setFont(resourceMap.getFont("AFTicketNumber.font")); // NOI18N
        AFTicketNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AFTicketNumber.setText(resourceMap.getString("AFTicketNumber.text")); // NOI18N
        AFTicketNumber.setName("AFTicketNumber"); // NOI18N
        ticketNumberPanel.add(AFTicketNumber);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        ticketInfoPanel.add(ticketNumberPanel, gridBagConstraints);

        jSeparator1.setBackground(resourceMap.getColor("jSeparator1.background")); // NOI18N
        jSeparator1.setForeground(resourceMap.getColor("jSeparator1.foreground")); // NOI18N
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setMinimumSize(new java.awt.Dimension(10, 30));
        jSeparator1.setName("jSeparator1"); // NOI18N
        jSeparator1.setPreferredSize(new java.awt.Dimension(10, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        ticketInfoPanel.add(jSeparator1, gridBagConstraints);

        carIMakeModelPanel.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.TicketBack));
        carIMakeModelPanel.setMinimumSize(new java.awt.Dimension(400, 27));
        carIMakeModelPanel.setName("carIMakeModelPanel"); // NOI18N
        carIMakeModelPanel.setPreferredSize(new java.awt.Dimension(400, 31));

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        carIMakeModelPanel.add(jLabel5);

        carMake.setText(resourceMap.getString("carMake.text")); // NOI18N
        carMake.setMaximumSize(new java.awt.Dimension(50, 14));
        carMake.setMinimumSize(new java.awt.Dimension(50, 14));
        carMake.setName("carMake"); // NOI18N
        carMake.setPreferredSize(new java.awt.Dimension(50, 14));
        carIMakeModelPanel.add(carMake);

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        carIMakeModelPanel.add(jLabel6);

        carModel.setText(resourceMap.getString("carModel.text")); // NOI18N
        carModel.setMaximumSize(new java.awt.Dimension(50, 14));
        carModel.setMinimumSize(new java.awt.Dimension(50, 14));
        carModel.setName("carModel"); // NOI18N
        carModel.setPreferredSize(new java.awt.Dimension(50, 14));
        carIMakeModelPanel.add(carModel);

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        carIMakeModelPanel.add(jLabel8);

        carYear.setText(resourceMap.getString("text")); // NOI18N
        carYear.setMaximumSize(new java.awt.Dimension(50, 14));
        carYear.setMinimumSize(new java.awt.Dimension(50, 14));
        carYear.setName(""); // NOI18N
        carYear.setPreferredSize(new java.awt.Dimension(50, 21));
        carIMakeModelPanel.add(carYear);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        ticketInfoPanel.add(carIMakeModelPanel, gridBagConstraints);

        jSeparator2.setBackground(resourceMap.getColor("jSeparator2.background")); // NOI18N
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setMinimumSize(new java.awt.Dimension(10, 30));
        jSeparator2.setName("jSeparator2"); // NOI18N
        jSeparator2.setPreferredSize(new java.awt.Dimension(10, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        ticketInfoPanel.add(jSeparator2, gridBagConstraints);

        customerNamePanel.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.TicketBack));
        customerNamePanel.setMinimumSize(new java.awt.Dimension(190, 24));
        customerNamePanel.setName("customerNamePanel"); // NOI18N
        customerNamePanel.setPreferredSize(new java.awt.Dimension(190, 56));
        customerNamePanel.setLayout(new java.awt.GridLayout(1, 0));

        custFirstName.setText(resourceMap.getString("custFirstName.text")); // NOI18N
        custFirstName.setMinimumSize(new java.awt.Dimension(75, 14));
        custFirstName.setName("custFirstName"); // NOI18N
        custFirstName.setPreferredSize(new java.awt.Dimension(75, 14));
        customerNamePanel.add(custFirstName);

        custLastName.setText(resourceMap.getString("custLastName.text")); // NOI18N
        custLastName.setMaximumSize(new java.awt.Dimension(75, 14));
        custLastName.setMinimumSize(new java.awt.Dimension(75, 14));
        custLastName.setName("custLastName"); // NOI18N
        custLastName.setPreferredSize(new java.awt.Dimension(75, 14));
        customerNamePanel.add(custLastName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        ticketInfoPanel.add(customerNamePanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        getContentPane().add(ticketInfoPanel, gridBagConstraints);

        ticketPanel.setMaximumSize(new java.awt.Dimension(800, 600));
        ticketPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        ticketPanel.setName("ticketPanel"); // NOI18N
        ticketPanel.setOpaque(false);
        ticketPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        ticketPanel.setLayout(new java.awt.GridLayout(1, 0));

        afJobWizardTabs.setBackground(resourceMap.getColor("afJobWizardTabs.background")); // NOI18N
        afJobWizardTabs.setMinimumSize(new java.awt.Dimension(800, 600));
        afJobWizardTabs.setName("afJobWizardTabs"); // NOI18N
        afJobWizardTabs.setOpaque(true);
        afJobWizardTabs.setPreferredSize(new java.awt.Dimension(800, 600));

        customerInfoPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        customerInfoPanel.setName("customerInfoPanel"); // NOI18N
        customerInfoPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        customerInfoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        AFCustomerViewPort.setBackground(resourceMap.getColor("AFCustomerViewPort.background")); // NOI18N
        AFCustomerViewPort.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("AFCustomerViewPort.border.highlightColor"), resourceMap.getColor("AFCustomerViewPort.border.shadowColor"))); // NOI18N
        AFCustomerViewPort.setForeground(afViewInf.getColorFromAFColor(AFViewMgrInterface.AFColor.InnerPanel));
        AFCustomerViewPort.setMinimumSize(new java.awt.Dimension(535, 300));
        AFCustomerViewPort.setName("AFCustomerViewPort"); // NOI18N
        AFCustomerViewPort.setPreferredSize(new java.awt.Dimension(535, 300));
        AFCustomerViewPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AFCustomerViewPortKeyTyped(evt);
            }
        });
        AFCustomerViewPort.setLayout(new java.awt.GridBagLayout());

        jLabel12.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 5);
        AFCustomerViewPort.add(jLabel12, gridBagConstraints);

        CusFirstName.setMinimumSize(new java.awt.Dimension(120, 20));
        CusFirstName.setName("CusFirstName"); // NOI18N
        CusFirstName.setPreferredSize(new java.awt.Dimension(120, 20));
        CusFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusFirstNameActionPerformed(evt);
            }
        });
        CusFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                captureFirstName(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 10, 0);
        AFCustomerViewPort.add(CusFirstName, gridBagConstraints);

        jLabel1.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 10, 0);
        AFCustomerViewPort.add(jLabel1, gridBagConstraints);

        CusLastName.setMinimumSize(new java.awt.Dimension(120, 20));
        CusLastName.setName("CusLastName"); // NOI18N
        CusLastName.setPreferredSize(new java.awt.Dimension(120, 20));
        CusLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusLastNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 10, 0);
        AFCustomerViewPort.add(CusLastName, gridBagConstraints);

        jLabel13.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 5);
        AFCustomerViewPort.add(jLabel13, gridBagConstraints);

        jLabel15.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 5);
        AFCustomerViewPort.add(jLabel15, gridBagConstraints);

        jLabel16.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 5);
        AFCustomerViewPort.add(jLabel16, gridBagConstraints);

        jLabel17.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel17.setFont(resourceMap.getFont("jLabel17.font")); // NOI18N
        jLabel17.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 5);
        AFCustomerViewPort.add(jLabel17, gridBagConstraints);

        afCityField.setName("afCityField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(afCityField, gridBagConstraints);

        afZipField.setName("afZipField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(afZipField, gridBagConstraints);

        afStreetAddrField1.setName("afStreetAddrField1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        AFCustomerViewPort.add(afStreetAddrField1, gridBagConstraints);

        afStreetAddrField2.setName("afStreetAddrField2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        AFCustomerViewPort.add(afStreetAddrField2, gridBagConstraints);

        jLabel18.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(jLabel18, gridBagConstraints);

        jLabel19.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(jLabel19, gridBagConstraints);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getActionMap(AFTicketWizard.class, this);
        actionButton.setAction(actionMap.get("customerAction")); // NOI18N
        actionButton.setText(resourceMap.getString("actionButton.text")); // NOI18N
        actionButton.setName("actionButton"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        AFCustomerViewPort.add(actionButton, gridBagConstraints);

        jLabel20.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(jLabel20, gridBagConstraints);

        jComboBox1.setMaximumRowCount(50);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AL", "AK", "AR", "TX" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(20, 22));
        jComboBox1.setMinimumSize(new java.awt.Dimension(20, 22));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(20, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(jComboBox1, gridBagConstraints);

        afPhoneField.setColumns(15);
        afPhoneField.setToolTipText(resourceMap.getString("afPhoneField.toolTipText")); // NOI18N
        afPhoneField.setName("afPhoneField"); // NOI18N
        afPhoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                afPhoneFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(afPhoneField, gridBagConstraints);

        afCellField.setToolTipText(resourceMap.getString("afCellField.toolTipText")); // NOI18N
        afCellField.setName("afCellField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(afCellField, gridBagConstraints);

        afFaxField.setToolTipText(resourceMap.getString("afFaxField.toolTipText")); // NOI18N
        afFaxField.setName("afFaxField"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(afFaxField, gridBagConstraints);

        jLabel2.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        jLabel2.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
        AFCustomerViewPort.add(jLabel2, gridBagConstraints);

        CustID.setMinimumSize(new java.awt.Dimension(50, 20));
        CustID.setName("CustID"); // NOI18N
        CustID.setPreferredSize(new java.awt.Dimension(50, 20));
        CustID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustIDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        AFCustomerViewPort.add(CustID, gridBagConstraints);

        customerInfoPanel.add(AFCustomerViewPort);

        afJobWizardTabs.addTab(resourceMap.getString("customerInfoPanel.TabConstraints.tabTitle"), customerInfoPanel); // NOI18N

        yearMakeSelectPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        yearMakeSelectPanel.setName("yearMakeSelectPanel"); // NOI18N
        yearMakeSelectPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel4.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.OutterPanel));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel4.border.titleFont"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        nextInTicket.setAction(actionMap.get("createNewTicket")); // NOI18N
        nextInTicket.setText(resourceMap.getString("nextInTicket.text")); // NOI18N
        nextInTicket.setName("nextInTicket"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        modelList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("modelList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("modelList.border.titleFont"))); // NOI18N
        modelList.setName("modelList"); // NOI18N
        modelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                modelListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(modelList);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        makeList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("makeList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("makeList.border.titleFont"))); // NOI18N
        makeList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        makeList.setName("makeList"); // NOI18N
        makeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                makeListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(makeList);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        yearList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("yearList.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("yearList.border.titleFont"))); // NOI18N
        yearList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        yearList.setName("yearList"); // NOI18N
        yearList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                yearListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(yearList);

        customVechicle.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.InnerPanel));
        customVechicle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        customVechicle.setEnabled(false);
        customVechicle.setName("customVechicle"); // NOI18N

        newModelLabel.setFont(resourceMap.getFont("newModelLabel.font")); // NOI18N
        newModelLabel.setText(resourceMap.getString("newModelLabel.text")); // NOI18N
        newModelLabel.setEnabled(false);
        newModelLabel.setName("newModelLabel"); // NOI18N

        modelField.setText(resourceMap.getString("modelField.text")); // NOI18N
        modelField.setEnabled(false);
        modelField.setName("modelField"); // NOI18N

        newYearLabel.setFont(resourceMap.getFont("newYearLabel.font")); // NOI18N
        newYearLabel.setText(resourceMap.getString("newYearLabel.text")); // NOI18N
        newYearLabel.setEnabled(false);
        newYearLabel.setName("newYearLabel"); // NOI18N

        yearField.setText(resourceMap.getString("yearField.text")); // NOI18N
        yearField.setEnabled(false);
        yearField.setName("yearField"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        newMakeLabel.setFont(resourceMap.getFont("newMakeLabel.font")); // NOI18N
        newMakeLabel.setText(resourceMap.getString("newMakeLabel.text")); // NOI18N
        newMakeLabel.setEnabled(false);
        newMakeLabel.setName("newMakeLabel"); // NOI18N

        makeField.setText(resourceMap.getString("makeField.text")); // NOI18N
        makeField.setEnabled(false);
        makeField.setName("makeField"); // NOI18N

        org.jdesktop.layout.GroupLayout customVechicleLayout = new org.jdesktop.layout.GroupLayout(customVechicle);
        customVechicle.setLayout(customVechicleLayout);
        customVechicleLayout.setHorizontalGroup(
            customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(customVechicleLayout.createSequentialGroup()
                .add(customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(customVechicleLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(modelField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(newModelLabel)))
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 295, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(customVechicleLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(newYearLabel)
                            .add(yearField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(38, 38, 38)
                        .add(customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(makeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(newMakeLabel))))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        customVechicleLayout.setVerticalGroup(
            customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(customVechicleLayout.createSequentialGroup()
                .add(jLabel4)
                .add(12, 12, 12)
                .add(newModelLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(modelField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(newYearLabel)
                    .add(newMakeLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(customVechicleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(yearField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(makeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(nextInTicket)
                        .add(customVechicle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createSequentialGroup()
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(customVechicle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)))
                .add(18, 18, 18)
                .add(nextInTicket)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout yearMakeSelectPanelLayout = new org.jdesktop.layout.GroupLayout(yearMakeSelectPanel);
        yearMakeSelectPanel.setLayout(yearMakeSelectPanelLayout);
        yearMakeSelectPanelLayout.setHorizontalGroup(
            yearMakeSelectPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(yearMakeSelectPanelLayout.createSequentialGroup()
                .add(35, 35, 35)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        yearMakeSelectPanelLayout.setVerticalGroup(
            yearMakeSelectPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(yearMakeSelectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        afJobWizardTabs.addTab(resourceMap.getString("yearMakeSelectPanel.TabConstraints.tabTitle"), yearMakeSelectPanel); // NOI18N

        jobSelectPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        jobSelectPanel.setName("jobSelectPanel"); // NOI18N
        jobSelectPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        jobSelectPanel.setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        worksPanel.setBackground(resourceMap.getColor("worksPanel.background")); // NOI18N
        worksPanel.setName("worksPanel"); // NOI18N
        worksPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        generalJobsLabel.setIcon(resourceMap.getIcon("generalJobsLabel.icon")); // NOI18N
        generalJobsLabel.setName("generalJobsLabel"); // NOI18N
        worksPanel.add(generalJobsLabel);

        jSplitPane1.setLeftComponent(worksPanel);

        enginePanel.setBackground(resourceMap.getColor("enginePanel.background")); // NOI18N
        enginePanel.setName("enginePanel"); // NOI18N
        enginePanel.setPreferredSize(new java.awt.Dimension(300, 300));
        enginePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                enginePanelMouseMoved(evt);
            }
        });
        enginePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        engineJobsLabel.setIcon(resourceMap.getIcon("engineJobsLabel.icon")); // NOI18N
        engineJobsLabel.setText(resourceMap.getString("engineJobsLabel.text")); // NOI18N
        engineJobsLabel.setName("engineJobsLabel"); // NOI18N
        enginePanel.add(engineJobsLabel);

        jSplitPane1.setRightComponent(enginePanel);

        jobSelectPanel.add(jSplitPane1);

        afJobWizardTabs.addTab(resourceMap.getString("jobSelectPanel.TabConstraints.tabTitle"), jobSelectPanel); // NOI18N

        ticketPanel.add(afJobWizardTabs);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(ticketPanel, gridBagConstraints);

        closeButton.setAction(actionMap.get("closeAboutBox")); // NOI18N
        closeButton.setIconTextGap(0);
        closeButton.setLabel(resourceMap.getString("closeButton.label")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N
        closeButton.setOpaque(false);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeButtonMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        getContentPane().add(closeButton, gridBagConstraints);

        cancelTicketButton.setAction(actionMap.get("closeAboutBox")); // NOI18N
        cancelTicketButton.setIconTextGap(0);
        cancelTicketButton.setName("cancelTicketButton"); // NOI18N
        cancelTicketButton.setOpaque(false);
        cancelTicketButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelTicketButtonMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        getContentPane().add(cancelTicketButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enginePanelMouseMoved(java.awt.event.MouseEvent evt)//GEN-FIRST:event_enginePanelMouseMoved
    {//GEN-HEADEREND:event_enginePanelMouseMoved

        if (this.engineCanvas != null) {
            engineCanvas.processPoint(evt);
            engineCanvas.repaint();
        }
    }//GEN-LAST:event_enginePanelMouseMoved

    private void yearListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_yearListValueChanged
        // TODO add your handling code here:
        yearValue = (String) yearList.getSelectedValue();
        this.yearField.setText( yearValue );

    }//GEN-LAST:event_yearListValueChanged

    private void makeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_makeListValueChanged
        // TODO add your handling code here:
        makeValue = (String) makeList.getSelectedValue();
        this.makeField.setText(makeValue);
        this.showModelForYearAndMake();
    }//GEN-LAST:event_makeListValueChanged

    private void closeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMousePressed
        // TODO add your handling code here:
        testCancelTicket();
    }//GEN-LAST:event_closeButtonMousePressed

    private void modelListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_modelListValueChanged
        // TODO add your handling code here:
        modelValue = (String) modelList.getSelectedValue();
        this.modelField.setText(modelValue);
        this.ticketStage = TicketStage.JobSelect;
        this.enableNext();
    }//GEN-LAST:event_modelListValueChanged

    private void CusFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CusFirstNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CusFirstNameActionPerformed

    private void CusLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CusLastNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CusLastNameActionPerformed

    private void afPhoneFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afPhoneFieldKeyTyped
        // TODO add your handling code here:
//        assignMask(evt, afPhoneField);
}//GEN-LAST:event_afPhoneFieldKeyTyped

    private void CustIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustIDActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CustIDActionPerformed

    private void AFCustomerViewPortKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AFCustomerViewPortKeyTyped
        // TODO add your handling code here:
}//GEN-LAST:event_AFCustomerViewPortKeyTyped

    private void captureFirstName(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_captureFirstName
//        // TODO add your handling code here:
//        char kc = evt.getKeyChar();
//        Character cha = new Character(kc);
//        String fs = cha.toString();


    }//GEN-LAST:event_captureFirstName

    private void cancelTicketButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelTicketButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelTicketButtonMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AFCustomerViewPort;
    private javax.swing.JLabel AFTicketNumber;
    private javax.swing.JTextField CusFirstName;
    private javax.swing.JTextField CusLastName;
    private javax.swing.JTextField CustID;
    private javax.swing.JButton actionButton;
    private javax.swing.JFormattedTextField afCellField;
    private javax.swing.JTextField afCityField;
    private javax.swing.JFormattedTextField afFaxField;
    private javax.swing.JTabbedPane afJobWizardTabs;
    private javax.swing.JFormattedTextField afPhoneField;
    private javax.swing.JTextField afStreetAddrField1;
    private javax.swing.JTextField afStreetAddrField2;
    private javax.swing.JTextField afZipField;
    private javax.swing.JButton cancelTicketButton;
    private javax.swing.JPanel carIMakeModelPanel;
    private javax.swing.JLabel carMake;
    private javax.swing.JLabel carModel;
    private javax.swing.JLabel carYear;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel custFirstName;
    private javax.swing.JLabel custLastName;
    private javax.swing.JPanel customVechicle;
    private javax.swing.JPanel customerInfoPanel;
    private javax.swing.JPanel customerNamePanel;
    private javax.swing.JLabel engineBaseImage;
    private javax.swing.JLabel engineJobsLabel;
    private javax.swing.JPanel enginePanel;
    private javax.swing.JLabel engineTransmission;
    private javax.swing.JLabel generalJobsLabel;
    private javax.swing.JLabel invoiceNumber;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel jobSelectPanel;
    private javax.swing.JTextField makeField;
    private javax.swing.JList makeList;
    private javax.swing.JTextField modelField;
    private javax.swing.JList modelList;
    private javax.swing.JLabel newMakeLabel;
    private javax.swing.JLabel newModelLabel;
    private javax.swing.JLabel newYearLabel;
    private javax.swing.JButton nextInTicket;
    private javax.swing.JPanel ticketInfoPanel;
    private javax.swing.JPanel ticketNumberPanel;
    private javax.swing.JPanel ticketPanel;
    private javax.swing.JPanel worksPanel;
    private javax.swing.JTextField yearField;
    private javax.swing.JList yearList;
    private javax.swing.JPanel yearMakeSelectPanel;
    // End of variables declaration//GEN-END:variables
    private Node engineItemNode;
    private Node engineItemNodeName;
    private Node engineItemNodeArea;
    private Document docRef;
    private int nodeWalkCount = -1;

    public void setLevel(GLogLevel newLevel)
    {
        log.setLevel(newLevel);
    }

    public GLogLevel getLevel()
    {
        return log.getLevel();
    }

    public boolean moreVerbose(GLogLevel testLevel)
    {
        return log.moreVerbose(testLevel);
    }

    public boolean lessVerbose(GLogLevel testLevel)
    {
        return log.lessVerbose(testLevel);
    }

    @Action
    public void createNewTicket() {

        String make = this.makeValue;
        String year = this.yearValue;
        String model = this.modelField.getText();
        myMgr.createTicket(make, model, year);
        displayTicketTab();
        showJobChoices();
    }
}
