/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AFTicketView.java
 *
 * Created on Aug 20, 2009, 9:14:06 AM
 */

package com.wms.antifreeze.view;

import com.wms.antifreeze.AFMgrInf;
import com.wms.antifreeze.AFMgrInf.ManagerType;
import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.antifreeze.AFMgr;
import com.wms.antifreeze.entity.car.AFCarEntity;
import com.wms.antifreeze.entity.customer.AFCustomerEntity;
import com.wms.antifreeze.entity.AFEstimateEntity;
import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.entity.ticket.AFTicketEntity;
import com.wms.antifreeze.entity.ticket.AFTicketInf.TicketIndicator;
import com.wms.antifreeze.gui.AFViewMgrInterface;
import com.wms.antifreeze.gui.AFViewMgrInterface.AFAction;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import org.jdesktop.application.Action;

/**
 *
 * @author Matt
 */
public class AFTicketView extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;

    private AFCarEntity ticketCarEntity;
    private AFTicketEntity ticketEntity;
    private AFCustomerEntity primaryTicketCustomer;
    private AFCarEntity primaryTicketCar;
    private AFEstimateEntity primaryTicketEstimate;

    private static AFMgr afMgr = null;
    private static AFMgrInf myMgr = null;

    private AFViewMgrInterface afViewInf = null;
    private AFTicketInf afTicket = null;

    private int ticketNum = -1;
    /** Creates new form AFTicketView
     * @param ticketNumber
     * @param carEnt
     */


    static
    {
        afMgr = AFMgr.callMgr();
        myMgr = afMgr.getManagerForType(ManagerType.Ticket);
    }

    public AFTicketView(AFTicketEntity ticketEntity, int ticketNumber, AFCarEntity carEnt, AFCustomer afCus) {
 
        afViewInf = afMgr.getViewMr();
        initComponents();

        this.ticketNum = ticketNumber;
        String paddedNum = ticketNumAsString(ticketNumber);
        this.ticketEntity = ticketEntity;
        this.AFTicketNumber.setText( String.valueOf(paddedNum) );
        this.ticketCarEntity = carEnt;
        this.initTicket();
    }

    private void initTicket()
    {
        if( this.ticketEntity != null )
        {
            int ticketNumber = ticketEntity.getTicketNumber();
            this.fillInCarInfo(ticketNumber);
            this.setTicketDate();
        }
    }


    public void showTicketIndicator( TicketIndicator indicator, boolean show )
    {
       switch( indicator )
       {
           case Car:
               break;
           case Customer:
               break;
           case Estimate:
               break;
           default:
               break;
       }
    }

    private String ticketNumAsString( int ticketNumber )
    {        
        String tickAsStr = String.valueOf( ticketNumber );
        this.ticketNum = ticketNumber;
        // number should be left padded wtih 0's
        StringBuilder sb = new StringBuilder();
        int tickNumLen = tickAsStr.length();
        //TODO: make the maxWidth config driven
        int maxWidth = 6; // standard for AF
        int widthDiff = maxWidth - tickNumLen;
        for( int i = 0; i < widthDiff; i++)
        {
            sb.append("0");
        }
        sb.append(tickAsStr);



        return sb.toString();
    }

    public void fillInCarInfo()
    {
        this.fillInCarInfo(this.ticketNum);
    }

    private void fillInCarInfo(int ticketNumber, AFCarEntity carEnt )
    {
        String year = carEnt.getModYear();
        String make = carEnt.getMake();
        String model = carEnt.getModel();

        this.carYear.setText(year);
        this.carMake.setText(make);
        this.carModel.setText(model);
    }

    private void fillInCarInfo( int ticketNumber )
    {
        // AFTicketInf afTicket = myMgr.getTicket(ticketNumber);
        String carInfo = ticketEntity.getCarComment();
        // parse the make model year from the comment
        String make = "";
        String model = "";
        String year = "";
        if (carInfo != null) {
            StringTokenizer st = new StringTokenizer(carInfo, "/");
            make = st.nextToken();
            model = st.nextToken();
            year = st.nextToken();
        }
        this.carYear.setText(year);
        this.carMake.setText(make);
        this.carModel.setText(model);
    }

    private void setTicketDate()
    {
        // we know we have a ticketEntity at this point
        Date ticketDate = this.ticketEntity.getTicketDate();
        if( ticketDate != null )
        {
            String ticketDateFormat = "MM/dd/yy - HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyLocalizedPattern(ticketDateFormat);
            String formattedDate = sdf.format(ticketDate);
            this.TicketDate.setText(formattedDate);
        }
    }

    public void setCarEntity( AFCarEntity carEnt )
    {
        ticketCarEntity = carEnt;
    }

    public String getTabTitle()
    {
        StringBuilder sb = new StringBuilder( ticketCarEntity.getModYear() ).append("- ");
        sb.append( ticketCarEntity.getMake()).append(",");
        sb.append(ticketCarEntity.getModel());

        return sb.toString();
    }

    public Component getComponent()
    {
        return this;
    }

    public JPanel getPaperTicket()
    {
        return this.AFPaperTicketPanel;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paperTicket = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AFPaperTicketPanel = new javax.swing.JPanel();
        DateBox = new javax.swing.JPanel();
        TicketDate = new javax.swing.JLabel();
        ShopInfoBox = new javax.swing.JPanel();
        ShopName = new javax.swing.JLabel();
        ShopStreet1 = new javax.swing.JLabel();
        ShopCityStateZip = new javax.swing.JLabel();
        ShopMainPhone = new javax.swing.JLabel();
        ShopMainSlogan = new javax.swing.JLabel();
        HardTopHeader = new javax.swing.JPanel();
        ticketNeedIndicator = new javax.swing.JPanel();
        carIndicator = new javax.swing.JButton();
        cusIndicator = new javax.swing.JButton();
        workIndicator = new javax.swing.JButton();
        TicketNumBox = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        AFTicketNumber = new javax.swing.JLabel();
        HardLeftMargin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        carInfoBox = new javax.swing.JPanel();
        carYear = new javax.swing.JLabel();
        carMake = new javax.swing.JLabel();
        carModel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jobsLineItemList = new javax.swing.JList();
        cusInfoPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cusFirstName = new javax.swing.JLabel();
        cusLastName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cusHome = new javax.swing.JLabel();
        cusWork = new javax.swing.JLabel();
        cusCell = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        paperTicket.setName("paperTicket"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(AFTicketView.class);
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout paperTicketLayout = new javax.swing.GroupLayout(paperTicket);
        paperTicket.setLayout(paperTicketLayout);
        paperTicketLayout.setHorizontalGroup(
            paperTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(paperTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paperTicketLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        paperTicketLayout.setVerticalGroup(
            paperTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(paperTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paperTicketLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridLayout(1, 0));

        AFPaperTicketPanel.setBackground(resourceMap.getColor("AFPaperTicketPanel.background")); // NOI18N
        AFPaperTicketPanel.setName("AFPaperTicketPanel"); // NOI18N

        DateBox.setBackground(resourceMap.getColor("DateBox.background")); // NOI18N
        DateBox.setName("DateBox"); // NOI18N

        TicketDate.setFont(resourceMap.getFont("TicketDate.font")); // NOI18N
        TicketDate.setText(resourceMap.getString("TicketDate.text")); // NOI18N
        TicketDate.setName("TicketDate"); // NOI18N

        javax.swing.GroupLayout DateBoxLayout = new javax.swing.GroupLayout(DateBox);
        DateBox.setLayout(DateBoxLayout);
        DateBoxLayout.setHorizontalGroup(
            DateBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DateBoxLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(TicketDate)
                .addGap(23, 23, 23))
        );
        DateBoxLayout.setVerticalGroup(
            DateBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DateBoxLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(TicketDate)
                .addContainerGap())
        );

        ShopInfoBox.setBackground(resourceMap.getColor("ShopInfoBox.background")); // NOI18N
        ShopInfoBox.setMinimumSize(new java.awt.Dimension(300, 120));
        ShopInfoBox.setName("ShopInfoBox"); // NOI18N
        ShopInfoBox.setPreferredSize(new java.awt.Dimension(300, 120));
        ShopInfoBox.setLayout(new java.awt.GridLayout(5, 1, 2, 2));

        ShopName.setBackground(resourceMap.getColor("ShopName.background")); // NOI18N
        ShopName.setFont(resourceMap.getFont("ShopName.font")); // NOI18N
        ShopName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShopName.setText(resourceMap.getString("ShopName.text")); // NOI18N
        ShopName.setFocusable(false);
        ShopName.setName("ShopName"); // NOI18N
        ShopName.setOpaque(true);
        ShopInfoBox.add(ShopName);

        ShopStreet1.setFont(resourceMap.getFont("ShopStreet1.font")); // NOI18N
        ShopStreet1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShopStreet1.setText(resourceMap.getString("ShopStreet1.text")); // NOI18N
        ShopStreet1.setFocusable(false);
        ShopStreet1.setName("ShopStreet1"); // NOI18N
        ShopInfoBox.add(ShopStreet1);

        ShopCityStateZip.setFont(resourceMap.getFont("ShopCityStateZip.font")); // NOI18N
        ShopCityStateZip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShopCityStateZip.setText(resourceMap.getString("ShopCityStateZip.text")); // NOI18N
        ShopCityStateZip.setFocusable(false);
        ShopCityStateZip.setName("ShopCityStateZip"); // NOI18N
        ShopInfoBox.add(ShopCityStateZip);

        ShopMainPhone.setFont(resourceMap.getFont("ShopMainPhone.font")); // NOI18N
        ShopMainPhone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShopMainPhone.setText(resourceMap.getString("ShopMainPhone.text")); // NOI18N
        ShopMainPhone.setFocusable(false);
        ShopMainPhone.setName("ShopMainPhone"); // NOI18N
        ShopInfoBox.add(ShopMainPhone);

        ShopMainSlogan.setBackground(resourceMap.getColor("ShopMainSlogan.background")); // NOI18N
        ShopMainSlogan.setFont(resourceMap.getFont("ShopMainSlogan.font")); // NOI18N
        ShopMainSlogan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShopMainSlogan.setText(resourceMap.getString("ShopMainSlogan.text")); // NOI18N
        ShopMainSlogan.setFocusable(false);
        ShopMainSlogan.setName("ShopMainSlogan"); // NOI18N
        ShopMainSlogan.setOpaque(true);
        ShopInfoBox.add(ShopMainSlogan);

        HardTopHeader.setBackground(resourceMap.getColor("HardTopHeader.background")); // NOI18N
        HardTopHeader.setMinimumSize(new java.awt.Dimension(600, 67));
        HardTopHeader.setName("HardTopHeader"); // NOI18N

        ticketNeedIndicator.setBackground(resourceMap.getColor("ticketNeedIndicator.background")); // NOI18N
        ticketNeedIndicator.setMinimumSize(new java.awt.Dimension(96, 32));
        ticketNeedIndicator.setName("ticketNeedIndicator"); // NOI18N
        ticketNeedIndicator.setPreferredSize(new java.awt.Dimension(96, 32));
        ticketNeedIndicator.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getActionMap(AFTicketView.class, this);
        carIndicator.setAction(actionMap.get("createCusCar")); // NOI18N
        carIndicator.setBackground(resourceMap.getColor("carIndicator.background")); // NOI18N
        carIndicator.setText(resourceMap.getString("carIndicator.text")); // NOI18N
        carIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        carIndicator.setDisabledIcon(resourceMap.getIcon("carIndicator.disabledIcon")); // NOI18N
        carIndicator.setMaximumSize(new java.awt.Dimension(32, 32));
        carIndicator.setMinimumSize(new java.awt.Dimension(32, 32));
        carIndicator.setName("carIndicator"); // NOI18N
        carIndicator.setPreferredSize(new java.awt.Dimension(32, 32));
        ticketNeedIndicator.add(carIndicator);

        cusIndicator.setAction(actionMap.get("createCustomer")); // NOI18N
        cusIndicator.setIcon(resourceMap.getIcon("cusIndicator.icon")); // NOI18N
        cusIndicator.setText(resourceMap.getString("cusIndicator.text")); // NOI18N
        cusIndicator.setBorder(null);
        cusIndicator.setDisabledIcon(resourceMap.getIcon("cusIndicator.disabledIcon")); // NOI18N
        cusIndicator.setMaximumSize(new java.awt.Dimension(32, 32));
        cusIndicator.setMinimumSize(new java.awt.Dimension(32, 32));
        cusIndicator.setName("cusIndicator"); // NOI18N
        cusIndicator.setPreferredSize(new java.awt.Dimension(32, 32));
        ticketNeedIndicator.add(cusIndicator);

        workIndicator.setAction(actionMap.get("addJob")); // NOI18N
        workIndicator.setBackground(resourceMap.getColor("workIndicator.background")); // NOI18N
        workIndicator.setIcon(resourceMap.getIcon("workIndicator.icon")); // NOI18N
        workIndicator.setText(resourceMap.getString("workIndicator.text")); // NOI18N
        workIndicator.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        workIndicator.setDisabledIcon(resourceMap.getIcon("workIndicator.disabledIcon")); // NOI18N
        workIndicator.setMaximumSize(new java.awt.Dimension(32, 32));
        workIndicator.setMinimumSize(new java.awt.Dimension(32, 32));
        workIndicator.setName("workIndicator"); // NOI18N
        workIndicator.setPreferredSize(new java.awt.Dimension(32, 32));
        ticketNeedIndicator.add(workIndicator);

        javax.swing.GroupLayout HardTopHeaderLayout = new javax.swing.GroupLayout(HardTopHeader);
        HardTopHeader.setLayout(HardTopHeaderLayout);
        HardTopHeaderLayout.setHorizontalGroup(
            HardTopHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HardTopHeaderLayout.createSequentialGroup()
                .addContainerGap(792, Short.MAX_VALUE)
                .addComponent(ticketNeedIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
        );
        HardTopHeaderLayout.setVerticalGroup(
            HardTopHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HardTopHeaderLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(ticketNeedIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TicketNumBox.setBackground(resourceMap.getColor("TicketNumBox.background")); // NOI18N
        TicketNumBox.setName("TicketNumBox"); // NOI18N
        TicketNumBox.setPreferredSize(new java.awt.Dimension(160, 100));

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        AFTicketNumber.setFont(resourceMap.getFont("AFTicketNumber.font")); // NOI18N
        AFTicketNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AFTicketNumber.setText(resourceMap.getString("AFTicketNumber.text")); // NOI18N
        AFTicketNumber.setName("AFTicketNumber"); // NOI18N

        javax.swing.GroupLayout TicketNumBoxLayout = new javax.swing.GroupLayout(TicketNumBox);
        TicketNumBox.setLayout(TicketNumBoxLayout);
        TicketNumBoxLayout.setHorizontalGroup(
            TicketNumBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TicketNumBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AFTicketNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(90, 90, 90))
        );
        TicketNumBoxLayout.setVerticalGroup(
            TicketNumBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TicketNumBoxLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(TicketNumBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AFTicketNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        HardLeftMargin.setBackground(resourceMap.getColor("HardLeftMargin.background")); // NOI18N
        HardLeftMargin.setName("HardLeftMargin"); // NOI18N

        javax.swing.GroupLayout HardLeftMarginLayout = new javax.swing.GroupLayout(HardLeftMargin);
        HardLeftMargin.setLayout(HardLeftMarginLayout);
        HardLeftMarginLayout.setHorizontalGroup(
            HardLeftMarginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 61, Short.MAX_VALUE)
        );
        HardLeftMarginLayout.setVerticalGroup(
            HardLeftMarginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 704, Short.MAX_VALUE)
        );

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        carInfoBox.setBackground(resourceMap.getColor("carInfoBox.background")); // NOI18N
        carInfoBox.setName("carInfoBox"); // NOI18N

        carYear.setFont(resourceMap.getFont("carYear.font")); // NOI18N
        carYear.setText(resourceMap.getString("carYear.text")); // NOI18N
        carYear.setName("carYear"); // NOI18N

        carMake.setFont(resourceMap.getFont("carMake.font")); // NOI18N
        carMake.setText(resourceMap.getString("carMake.text")); // NOI18N
        carMake.setName("carMake"); // NOI18N

        carModel.setFont(resourceMap.getFont("carModel.font")); // NOI18N
        carModel.setText(resourceMap.getString("carModel.text")); // NOI18N
        carModel.setName("carModel"); // NOI18N

        javax.swing.GroupLayout carInfoBoxLayout = new javax.swing.GroupLayout(carInfoBox);
        carInfoBox.setLayout(carInfoBoxLayout);
        carInfoBoxLayout.setHorizontalGroup(
            carInfoBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carInfoBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(carYear)
                .addGap(18, 18, 18)
                .addComponent(carMake, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carModel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        carInfoBoxLayout.setVerticalGroup(
            carInfoBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carInfoBoxLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(carInfoBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carYear)
                    .addComponent(carMake)
                    .addComponent(carModel))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jobsLineItemList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Job 1" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jobsLineItemList.setName("jobsLineItemList"); // NOI18N
        jScrollPane1.setViewportView(jobsLineItemList);

        cusInfoPanel.setBackground(resourceMap.getColor("cusInfoPanel.background")); // NOI18N
        cusInfoPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cusInfoPanel.setMinimumSize(new java.awt.Dimension(100, 108));
        cusInfoPanel.setName("cusInfoPanel"); // NOI18N
        cusInfoPanel.setPreferredSize(new java.awt.Dimension(100, 108));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        cusFirstName.setText(resourceMap.getString("cusFirstName.text")); // NOI18N
        cusFirstName.setMaximumSize(new java.awt.Dimension(65, 14));
        cusFirstName.setMinimumSize(new java.awt.Dimension(65, 14));
        cusFirstName.setName("cusFirstName"); // NOI18N
        cusFirstName.setPreferredSize(new java.awt.Dimension(65, 14));

        cusLastName.setText(resourceMap.getString("cusLastName.text")); // NOI18N
        cusLastName.setMaximumSize(new java.awt.Dimension(90, 14));
        cusLastName.setMinimumSize(new java.awt.Dimension(90, 14));
        cusLastName.setName("cusLastName"); // NOI18N
        cusLastName.setPreferredSize(new java.awt.Dimension(90, 14));

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        cusHome.setText(resourceMap.getString("cusHome.text")); // NOI18N
        cusHome.setMaximumSize(new java.awt.Dimension(125, 14));
        cusHome.setMinimumSize(new java.awt.Dimension(125, 14));
        cusHome.setName("cusHome"); // NOI18N
        cusHome.setPreferredSize(new java.awt.Dimension(125, 14));

        cusWork.setText(resourceMap.getString("cusWork.text")); // NOI18N
        cusWork.setMaximumSize(new java.awt.Dimension(125, 14));
        cusWork.setMinimumSize(new java.awt.Dimension(125, 14));
        cusWork.setName("cusWork"); // NOI18N
        cusWork.setPreferredSize(new java.awt.Dimension(125, 14));

        cusCell.setText(resourceMap.getString("cusCell.text")); // NOI18N
        cusCell.setMaximumSize(new java.awt.Dimension(125, 14));
        cusCell.setMinimumSize(new java.awt.Dimension(125, 14));
        cusCell.setName("cusCell"); // NOI18N
        cusCell.setPreferredSize(new java.awt.Dimension(125, 14));

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        javax.swing.GroupLayout cusInfoPanelLayout = new javax.swing.GroupLayout(cusInfoPanel);
        cusInfoPanel.setLayout(cusInfoPanelLayout);
        cusInfoPanelLayout.setHorizontalGroup(
            cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cusInfoPanelLayout.createSequentialGroup()
                .addGroup(cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cusInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cusInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusHome, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                            .addGroup(cusInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cusWork, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                            .addGroup(cusInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cusCell, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))))
                    .addGroup(cusInfoPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(cusInfoPanelLayout.createSequentialGroup()
                                .addComponent(cusFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(cusLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        cusInfoPanelLayout.setVerticalGroup(
            cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cusInfoPanelLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(12, 12, 12)
                .addGroup(cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cusLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cusInfoPanelLayout.createSequentialGroup()
                        .addComponent(cusHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cusWork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cusInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cusCell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AFPaperTicketPanelLayout = new javax.swing.GroupLayout(AFPaperTicketPanel);
        AFPaperTicketPanel.setLayout(AFPaperTicketPanelLayout);
        AFPaperTicketPanelLayout.setHorizontalGroup(
            AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                .addComponent(HardLeftMargin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(HardTopHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                                .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(cusInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(141, 141, 141)
                                .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                                        .addComponent(ShopInfoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(93, 93, 93)
                                        .addComponent(TicketNumBox, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(93, Short.MAX_VALUE))
                                    .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                                        .addComponent(carInfoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AFPaperTicketPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AFPaperTicketPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addGap(934, 934, 934))))))
        );
        AFPaperTicketPanelLayout.setVerticalGroup(
            AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                .addComponent(HardTopHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ShopInfoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TicketNumBox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                        .addComponent(DateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cusInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AFPaperTicketPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(AFPaperTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)))
                    .addComponent(carInfoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
            .addComponent(HardLeftMargin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(AFPaperTicketPanel);
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void createCustomer() {
        this.afViewInf.showCustDetail(AFAction.AFUpdate);
    }
    @Action
    public void searchCustomer()
    {
        this.afViewInf.showCustDetail(AFAction.AFSearch);
    }
    @Action
    public void createCusCar() {
    }

    @Action
    public void addJob() {
    }

    public AFTicketInf getAFTicket()
    {
        return this.afTicket;
    }
    
    public void setAFTicket( AFTicketInf  afTicket )
    {
        this.afTicket = afTicket;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AFPaperTicketPanel;
    private javax.swing.JLabel AFTicketNumber;
    private javax.swing.JPanel DateBox;
    private javax.swing.JPanel HardLeftMargin;
    private javax.swing.JPanel HardTopHeader;
    private javax.swing.JLabel ShopCityStateZip;
    private javax.swing.JPanel ShopInfoBox;
    private javax.swing.JLabel ShopMainPhone;
    private javax.swing.JLabel ShopMainSlogan;
    private javax.swing.JLabel ShopName;
    private javax.swing.JLabel ShopStreet1;
    private javax.swing.JLabel TicketDate;
    private javax.swing.JPanel TicketNumBox;
    private javax.swing.JButton carIndicator;
    private javax.swing.JPanel carInfoBox;
    private javax.swing.JLabel carMake;
    private javax.swing.JLabel carModel;
    private javax.swing.JLabel carYear;
    private javax.swing.JLabel cusCell;
    private javax.swing.JLabel cusFirstName;
    private javax.swing.JLabel cusHome;
    private javax.swing.JButton cusIndicator;
    private javax.swing.JPanel cusInfoPanel;
    private javax.swing.JLabel cusLastName;
    private javax.swing.JLabel cusWork;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList jobsLineItemList;
    private javax.swing.JPanel paperTicket;
    private javax.swing.JPanel ticketNeedIndicator;
    private javax.swing.JButton workIndicator;
    // End of variables declaration//GEN-END:variables

}
