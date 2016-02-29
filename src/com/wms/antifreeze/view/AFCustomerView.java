/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AFCustomerView.java
 *
 * Created on Sep 2, 2009, 6:37:32 PM
 */
package com.wms.antifreeze.view;

import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.AFMgrInf;
import com.wms.antifreeze.AFMgrInf.ManagerType;
import com.wms.antifreeze.AFMgr;
import com.wms.antifreeze.AFSearchAdaptor;
import com.wms.antifreeze.entity.customer.AFCustomerEntity;
import com.wms.antifreeze.gui.AFViewMgrInterface;
import com.wms.antifreeze.gui.AFViewMgrInterface.AFAction;
import com.wms.antifreeze.gui.AF_ViewMgr;
import com.wms.util.gui.GUILogInterface.GLogLevel;
import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Iterator;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.application.Action;

/**
 *
 * @author Matt
 */
public class AFCustomerView extends javax.swing.JPanel implements AFSearchAdaptor {

    private static AFMgr afMgr = null;
    private static AFMgrInf myMgr = null;
    private AFViewMgrInterface afViewInf = null;
    AF_ViewMgr afViewMgr = null;
    AFAction afAction = null;
    FocusTraversalPolicy ftp;

    class PhoneNumber extends InputVerifier {

        @Override
        public boolean verify(JComponent input) {
            boolean retVal = false;
            String t = ((JFormattedTextField) input).getText();
            Character ch = new Character(t.charAt(0));
            return Character.isDigit(ch);
        }
    }

    enum PhoneChar {

        OpenParen,
        CloseParen,
        Dash;
    }

    static {
        afMgr = AFMgr.callMgr();
        myMgr = afMgr.getManagerForType(ManagerType.Customer);
    }

    /** Creates new form AFCustomerView
     * @param action
     */
    public AFCustomerView(AFAction action) {
        afAction = AFAction.AFActionUnknown;
        afViewInf = afMgr.getViewMr();
        initComponents();
        this.afPhoneField.setInputVerifier(new PhoneNumber());
        afPhoneField.addKeyListener(null);
//        afPhoneField.addKeyListener(new java.awt.event.KeyAdapter() {
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                afPhoneFieldKeyPressed(evt);
//            }
//        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getActionMap(AFCustomerView.class, this);
        actionButton.setAction(actionMap.get("customerAction")); // NOI18N

        switch( action )
        {
            case AFUpdate:
                this.actionButton.setText(action.getAction("Customer"));
                this.actionButton.setVisible(true);
                break;

            case AFCreate:
                int nextId = myMgr.getNextCustomerId();
                String nextIdStr = String.valueOf(nextId);
                this.custIDField.setText(nextIdStr);
                this.custIDField.setEditable(false);
                this.custIDField.setEnabled(false);
                this.actionButton.setText(action.getAction("Customer"));
                this.actionButton.setVisible(true);

                actionButton.setAction(actionMap.get("createCustomer")); // NOI18N
                break;

            case AFSearch:
                this.actionButton.setVisible(false);
                break;


        }
        afAction = action;

    }

    public void blankEntity() {
        String blank = "";
        Component[] comps = this.AFCustomerViewPort.getComponents();
        for (int i = 0; i < comps.length; i++) {
            JComponent jc = (JComponent) comps[i];
            if (jc != null) {
                String key = jc.getName();
                if (key != null && key.length() > 0) {
                    if (JTextField.class.isInstance(jc)) {
                        JTextField tf = JTextField.class.cast(jc);
                        tf.setText(blank);
                    }
                }
            }
        }
    }

    public void displayEntity(AFCustomerEntity afCustEntity) {
        Iterator<String> it = afCustEntity.getEntityKeys();
        if (it == null) {
            return;
        }

        Integer n = afCustEntity.getCusID();
        String sid = String.valueOf(n);
        this.custIDField.setText(sid);
        this.CusFirstName.setText(afCustEntity.getCusFirstName());
        Component[] comps = this.AFCustomerViewPort.getComponents();
        this.afViewInf.writeToGui("******************************", GLogLevel.Message);
        for (int i = 0; i <
                comps.length; i++) {
            JComponent jc = (JComponent) comps[i];
            if (jc != null) {
                String key = jc.getName();
                if (key != null && key.length() > 0) {
                    String val = afCustEntity.valueForKey(key);
                    if (val != null) {
                        StringBuilder sb = new StringBuilder(key);
                        sb.append("=").append(val);
                        this.afViewInf.writeToGui(sb.toString(), GLogLevel.Message);
                        if (JTextField.class.isInstance(jc)) {

                            JTextField tf = JTextField.class.cast(jc);
                            tf.setText(val);
                        }
                    }

                }
            }
        }
        this.afViewInf.writeToGui("******************************\n", GLogLevel.Message);
    }

    public void setActionButton(AFAction action) {
        this.actionButton.setText(action.getAction("Customer"));
        afAction =
                action;
    }

    public JPanel getPanel() {
        return this;
    }

    private String stripPhone(String ph) {
        String retStr = ph.replace("(", "");
        retStr =
                retStr.replace(")", "");
        retStr =
                retStr.replace("-", "");

        return retStr;
    }

    private String formatPhoneNumber(KeyEvent evt) {

        String fs = null;
        String rs = afPhoneField.getText().trim();
        String cs = stripPhone(rs);
        StringBuilder scratch = new StringBuilder(rs);

        if (cs != null) {
            cs = cs.trim();
        }

        char c = evt.getKeyChar();
        int cCount = cs.length();
        int fWidth = 10; // "(512) 555 - 1212" or 512 555 1212
        int fDiff = fWidth - cCount;
        char fc[] = {'(', ')', '-'};

        if (cCount < 3) {
            fs = rs;
        } else if (cCount == 3) {
            scratch = new StringBuilder("(");
            scratch.append(cs);
            scratch.append(")");

            fs =
                    scratch.toString();
        } else if (cCount < 7) {
            scratch = new StringBuilder(rs);

        }
// ### - ####
// (###) ### - #####

        scratch.append(c);

        fs =
                scratch.toString();
        return fs;
    }

    private void convertTypedCharToPhone(KeyEvent evt) {

        String current = this.afPhoneField.getText();
        int fieldIndex;
        int fieldWidth = 14; // (512)-555-1212


        char[] formatterArray = new char[]{'(', ')', '-', '-'};
        StringBuilder sb = null;

        if (current != null && current.length() > 0) {
            sb = new StringBuilder(current);
        } else {
//           sb = new StringBuilder(formatterArray[PFPhoneFormatChar.OpenParen.ordinal()]);
        }

        int keyCodeTyped = evt.getKeyCode();

        if (evt.getKeyCode() > KeyEvent.VK_0 && evt.getKeyCode() <= KeyEvent.VK_9) {
            char c = evt.getKeyChar();
            sb.append(c);
            afPhoneField.setText(sb.toString());
        }

    }

    private synchronized void assignMask(java.awt.event.KeyEvent evt, JFormattedTextField formattedTextField) {
        System.out.println("######################################################################################################");
        if (evt.isConsumed()) {
            return;
        }

        evt.consume();

        StringBuilder sb = new StringBuilder();
        int keyCode = evt.getKeyCode();
        String keyPressed = KeyEvent.getKeyText(keyCode);
        char kc = evt.getKeyChar();
        System.out.println("Key Pressed: KeyCode= " + keyCode + " KeyChar=" + kc);
        if (!Character.isLetterOrDigit(kc)) {
            return;
        }

        String keep = formattedTextField.getText();
        keep =
                this.stripPhone(keep);

        int fLen = 0;
        if (keep != null) {
            fLen = keep.length();
        }

        try {
            if (fLen >= 0 && fLen < 3) {
                formattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)")));
                System.out.println("setting format to (###)");
            } else if (fLen >= 3 && fLen <= 6) {
                //afPhoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)")));
                formattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###")));
                System.out.println("setting format to (###)-###");
            } else if (fLen > 6) {
                formattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###-####")));
                System.out.println("setting format to (###)-###-####");
            }
// formattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###-####")));

        } catch (ParseException e) {
        }
        sb.append(keep.trim());
        sb.append(kc);
        sb.trimToSize();
        System.out.println("Setting " + formattedTextField.getName() + "'s text to: " + sb.toString());
        formattedTextField.setText(sb.toString());

//        afPhoneField.setText(sb.toString());
        System.out.println("######################################################################################################\n\n");
    }

    public void invokeSearch() {
      this.afAction = AFAction.AFSearch;
      customerAction();
    }

    @Action
    public void createCustomer( )
    {
        AFCustomerEntity newCust = myMgr.createCustomer();
        String value = this.CusFirstName.getText();
        newCust.setCusFirstName( value );
        value = this.CusLastName.getText();
        newCust.setCusLastName(value);
        value = this.custStreetAddrField1.getText();
        newCust.setCusStreet1(value);
        value = this.custStreetAddrField2.getText();
        newCust.setCusStreet2(value);
        value = this.custCityField.getText();
        newCust.setCusCity(value);


        myMgr.addCustomer(newCust);

    }

    @Action
    public void resetCustomer()
    {
        String blank = "";
        this.CusFirstName.setText(blank);
        this.CusLastName.setText(blank);
        this.custCityField.setText(blank);
        this.custZipField.setText(blank);
        this.custStreetAddrField1.setText(blank);
        this.custStreetAddrField2.setText(blank);
        
        
    }

    @Action
    public void customerAction() {
        switch (afAction) {
            case AFCreate:
                actionButton.setVisible(true);
                actionButton.setText("Add Customer");
                break;
            case AFUpdate:
                actionButton.setVisible(true);
                actionButton.setText("Update Customer");
                break;
            case AFSearch:
                AFCustomer afCust = afMgr.findCustomer();
                actionButton.setVisible(false);
                if (afCust != null) {
                    AFCustomerEntity afCustEnt = afCust.getEntity();
                    if (afCustEnt != null) {
                        this.displayEntity(afCustEnt);
                    }
                    else
                        this.blankEntity();

                }
                else
                {
                    this.blankEntity();
                }
                break;
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        AFCustomerViewPort = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        CusFirstName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CusLastName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        custCityField = new javax.swing.JTextField();
        custZipField = new javax.swing.JTextField();
        custStreetAddrField1 = new javax.swing.JTextField();
        custStreetAddrField2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        actionButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        afPhoneField = new javax.swing.JFormattedTextField();
        afCellField = new javax.swing.JFormattedTextField();
        afFaxField = new javax.swing.JFormattedTextField();
        custIdLabel = new javax.swing.JLabel();
        custIDField = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(525, 350));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(525, 350));
        setLayout(new java.awt.GridLayout(1, 0, 20, 20));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(AFCustomerView.class);
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

        CusFirstName.setFocusAccelerator(' ');
        CusFirstName.setMinimumSize(new java.awt.Dimension(120, 20));
        CusFirstName.setName("CusFirstName"); // NOI18N
        CusFirstName.setPreferredSize(new java.awt.Dimension(120, 20));
        CusFirstName.setRequestFocusEnabled(false);
        CusFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusFirstNameActionPerformed(evt);
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

        custCityField.setName("CusCity"); // NOI18N
        custCityField.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(custCityField, gridBagConstraints);

        custZipField.setName("CusZip"); // NOI18N
        custZipField.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(custZipField, gridBagConstraints);

        custStreetAddrField1.setName("CusStreet1"); // NOI18N
        custStreetAddrField1.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        AFCustomerViewPort.add(custStreetAddrField1, gridBagConstraints);

        custStreetAddrField2.setName("CusStreet2"); // NOI18N
        custStreetAddrField2.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        AFCustomerViewPort.add(custStreetAddrField2, gridBagConstraints);

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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getActionMap(AFCustomerView.class, this);
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
        try {
            afPhoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###-#### ")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        afPhoneField.setText(resourceMap.getString("CusHome.text")); // NOI18N
        afPhoneField.setToolTipText(resourceMap.getString("CusHome.toolTipText")); // NOI18N
        afPhoneField.setName("CusHome"); // NOI18N
        afPhoneField.setRequestFocusEnabled(false);
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

        afCellField.setText(resourceMap.getString("CusWork.text")); // NOI18N
        afCellField.setToolTipText(resourceMap.getString("CusWork.toolTipText")); // NOI18N
        afCellField.setName("CusWork"); // NOI18N
        afCellField.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(afCellField, gridBagConstraints);

        afFaxField.setText(resourceMap.getString("CusCell.text")); // NOI18N
        afFaxField.setToolTipText(resourceMap.getString("CusCell.toolTipText")); // NOI18N
        afFaxField.setName("CusCell"); // NOI18N
        afFaxField.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        AFCustomerViewPort.add(afFaxField, gridBagConstraints);

        custIdLabel.setBackground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelBack));
        custIdLabel.setForeground(afViewInf.getColorFromAFColor( AFViewMgrInterface.AFColor.LabelFore));
        custIdLabel.setText(resourceMap.getString("custIdLabel.text")); // NOI18N
        custIdLabel.setName("custIdLabel"); // NOI18N
        custIdLabel.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
        AFCustomerViewPort.add(custIdLabel, gridBagConstraints);

        custIDField.setText(resourceMap.getString("CustID.text")); // NOI18N
        custIDField.setMinimumSize(new java.awt.Dimension(50, 20));
        custIDField.setName("CustID"); // NOI18N
        custIDField.setPreferredSize(new java.awt.Dimension(50, 20));
        custIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custIDFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        AFCustomerViewPort.add(custIDField, gridBagConstraints);

        add(AFCustomerViewPort);
    }// </editor-fold>//GEN-END:initComponents

    private void CusFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CusFirstNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CusFirstNameActionPerformed

    private void CusLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CusLastNameActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CusLastNameActionPerformed

    private void AFCustomerViewPortKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AFCustomerViewPortKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AFCustomerViewPortKeyTyped

    private void afPhoneFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_afPhoneFieldKeyTyped
        // TODO add your handling code here:
        assignMask(evt, afPhoneField);
    }//GEN-LAST:event_afPhoneFieldKeyTyped

    private void custIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custIDFieldActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AFCustomerViewPort;
    private javax.swing.JTextField CusFirstName;
    private javax.swing.JTextField CusLastName;
    private javax.swing.JButton actionButton;
    private javax.swing.JFormattedTextField afCellField;
    private javax.swing.JFormattedTextField afFaxField;
    private javax.swing.JFormattedTextField afPhoneField;
    private javax.swing.JTextField custCityField;
    private javax.swing.JTextField custIDField;
    private javax.swing.JLabel custIdLabel;
    private javax.swing.JTextField custStreetAddrField1;
    private javax.swing.JTextField custStreetAddrField2;
    private javax.swing.JTextField custZipField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    // End of variables declaration//GEN-END:variables
}
