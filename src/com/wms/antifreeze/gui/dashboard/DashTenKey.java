/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DashTenKey.java
 *
 * Created on Sep 26, 2009, 10:26:22 PM
 */

package com.wms.antifreeze.gui.dashboard;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matt
 */
public class DashTenKey extends javax.swing.JFrame implements DashKeyInterface {

    /** Creates new form DashTenKey */
    public DashTenKey() {
        initComponents();
        keyPanel.addKeyListener(this);
        this.tenKeyPanel.addKeyListener(this);
        this.addKeyListener(this);
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

        keyPanel = new javax.swing.JPanel();
        tenKeyPanel = new java.awt.Panel();
        button9 = new java.awt.Button();
        button8 = new java.awt.Button();
        button7 = new java.awt.Button();
        button6 = new java.awt.Button();
        button5 = new java.awt.Button();
        button4 = new java.awt.Button();
        button3 = new java.awt.Button();
        button2 = new java.awt.Button();
        button1 = new java.awt.Button();
        button0 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        keyPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        keyPanel.setName("keyPanel"); // NOI18N
        keyPanel.setLayout(new java.awt.GridLayout());

        tenKeyPanel.setName("tenKeyPanel"); // NOI18N
        tenKeyPanel.setLayout(new java.awt.GridBagLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(DashTenKey.class);
        button9.setFont(resourceMap.getFont("button9.font")); // NOI18N
        button9.setLabel(resourceMap.getString("button9.label")); // NOI18N
        button9.setName("button9"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button9, gridBagConstraints);

        button8.setFont(resourceMap.getFont("button8.font")); // NOI18N
        button8.setLabel(resourceMap.getString("button8.label")); // NOI18N
        button8.setName("button8"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button8, gridBagConstraints);

        button7.setFont(resourceMap.getFont("button7.font")); // NOI18N
        button7.setLabel(resourceMap.getString("button7.label")); // NOI18N
        button7.setName("button7"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button7, gridBagConstraints);

        button6.setFont(resourceMap.getFont("button6.font")); // NOI18N
        button6.setLabel(resourceMap.getString("button6.label")); // NOI18N
        button6.setName("button6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button6, gridBagConstraints);

        button5.setFont(resourceMap.getFont("button5.font")); // NOI18N
        button5.setLabel(resourceMap.getString("button5.label")); // NOI18N
        button5.setName("button5"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button5, gridBagConstraints);

        button4.setFont(resourceMap.getFont("button4.font")); // NOI18N
        button4.setLabel(resourceMap.getString("button4.label")); // NOI18N
        button4.setName("button4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button4, gridBagConstraints);

        button3.setFont(resourceMap.getFont("button3.font")); // NOI18N
        button3.setLabel(resourceMap.getString("button3.label")); // NOI18N
        button3.setName("button3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button3, gridBagConstraints);

        button2.setFont(resourceMap.getFont("button2.font")); // NOI18N
        button2.setLabel(resourceMap.getString("button2.label")); // NOI18N
        button2.setName("button2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button2, gridBagConstraints);

        button1.setFont(resourceMap.getFont("button1.font")); // NOI18N
        button1.setLabel(resourceMap.getString("button1.label")); // NOI18N
        button1.setName("button1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button1, gridBagConstraints);

        button0.setFont(resourceMap.getFont("button0.font")); // NOI18N
        button0.setLabel(resourceMap.getString("button0.label")); // NOI18N
        button0.setName("button0"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        tenKeyPanel.add(button0, gridBagConstraints);

        keyPanel.add(tenKeyPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(keyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(keyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashTenKey().setVisible(true);
            }
        });
    }

    private void lightKey(KeyEvent e)
    {
        if( e == null )
            return;
        if( e.isConsumed() )
            return;
        Button buttonToLite;

        int kc = e.getKeyCode();
        char ch = e.getKeyChar();
        System.out.println("Got key: '" + ch + "' (" + kc + ")" );
        switch (e.getKeyCode() )
        {
            case KeyEvent.VK_NUMPAD0:
                flashButton( button0, 3 );
                break;
        }
        e.consume();
    }

    
    private void flashButton(Button button, int fCount )
    {
//        System.out.println("Flash!!!: " + button);
        int count = 0;
        while( count < fCount )
        {
            button.setEnabled(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DashTenKey.class.getName()).log(Level.SEVERE, null, ex);
            }
            button.setEnabled(false);
            count++;
        }
    }

    public void keyTyped(KeyEvent e) {
        this.lightKey(e);
    }

    public void keyPressed(KeyEvent e) {
        this.lightKey(e);
    }

    public void keyReleased(KeyEvent e) {
        this.lightKey(e);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button0;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private java.awt.Button button5;
    private java.awt.Button button6;
    private java.awt.Button button7;
    private java.awt.Button button8;
    private java.awt.Button button9;
    private javax.swing.JPanel keyPanel;
    private java.awt.Panel tenKeyPanel;
    // End of variables declaration//GEN-END:variables

    public KeyValue getKeyValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public KeyOp getKeyOp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
