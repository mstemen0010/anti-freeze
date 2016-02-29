/*
 * WMSProgessPanel.java
 *
 * Created on October 26, 2005, 12:55 PM
 */

package com.wms.antifreeze.view;

import com.wms.util.gui.progress.WMSJob;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author  mstemen
 */
public class AFProgressPanel extends javax.swing.JPanel {
    
    private Timer timer;
    private AFJob job;
    private int jobCheckInterval = 1000;
    private boolean jobSet = false;
    private int defHeight = 480;
    private int defWidth = 640;
    private String version = System.getProperty("java.vm.version");
    private String jreName = System.getProperty("java.vm.name");
    // create a timer
    
    /** Creates new form WMSProgessPanel */
    public AFProgressPanel( int min, int max ) {
        initComponents();
        Dimension defDim = new Dimension(defHeight, defWidth);
        this.setSize( defDim );
        progressMessage.setText( "Starting to Monitor Start-up" );
        progressNote.setText( "Init Progress System..." );
        jobProgressBar.setMinimum( min );
        jobProgressBar.setMaximum( max );      
        progJavaVersion.setText( jreName + " " + version );
        
    }
    
    public void start()
    {
        if( ! jobSet )
            return;
        
        timer = new Timer( jobCheckInterval, new ActionListener()
        
        {
           public void actionPerformed( ActionEvent e )
           {
               jobProgressBar.setValue( job.getCurrent() );
               progressNote.setText( job.getNote());
               if( job.isDone())
               {
                   timer.stop();
                   jobProgressBar.setValue( jobProgressBar.getMinimum() );
               }
               validate();
           }
        });
        timer.setInitialDelay( 0 );
        timer.start();
        
    }
    
    public void stop()
    {
        if( timer != null )
            timer.stop();       
    }
            
    public void setJob( AFJob job )
    {
        this.job = job;
        job.setPanel( this );
        progressMessage.setText( job.getMessage() );
        jobSet = true;
    }
    
    public void setNote( String note )
    {
        progressNote.setText( note );
        validate();
    }
    
    public void setMessage( String message )
    {
        progressMessage.setText( message );        
        validate();
    }
    
    @Override
    public void validate()
    {
        jobProgressBar.validate();
        progressMessage.validate();
        progressNote.validate();
        jobProgressBar.repaint();
        super.validate();                        
        repaint();        

    }
    
    public void setMinimum( int min )
    {
        jobProgressBar.setMinimum( min );
    }
    
    public void setMaximum( int max )
    {
        jobProgressBar.setMaximum( max );
    }
    
    public void showLogo( boolean flag )
    {
        wmsProductLogo.setVisible( flag );
    }
    public void showVersion( boolean flag )
    {
        progJavaVersion.setVisible( flag );
    }
    
    public void setValue( int value )
    {
        // if( ParallaxMgr.getMgr() != null )
        //    ParallaxMgr.getMgr().getLog().write( "Setting done value to: " + value );
        jobProgressBar.setValue( value );
        validate();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        afSplashInfoPanel = new javax.swing.JPanel();
        wmsProductLogo = new javax.swing.JLabel();
        jobProgressBar = new javax.swing.JProgressBar();
        progressMessage = new javax.swing.JLabel();
        progressNote = new javax.swing.JLabel();
        javaVerLabel = new javax.swing.JLabel();
        progJavaVersion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        bgSplashPanel = new javax.swing.JPanel();
        afSplashBgImage = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 0), new java.awt.Color(255, 255, 0), new java.awt.Color(0, 102, 102), new java.awt.Color(0, 255, 255)));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));
        setLayout(new java.awt.GridLayout(1, 0));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(640, 480));
        jLayeredPane1.setRequestFocusEnabled(false);

        afSplashInfoPanel.setBackground(new java.awt.Color(0, 0, 0));
        afSplashInfoPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        afSplashInfoPanel.setMinimumSize(new java.awt.Dimension(550, 225));
        afSplashInfoPanel.setOpaque(false);
        afSplashInfoPanel.setPreferredSize(new java.awt.Dimension(550, 225));

        wmsProductLogo.setForeground(new java.awt.Color(0, 0, 0));
        wmsProductLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wmsProductLogo.setText("Copyright © 2009 Wintermute Studio - All Rights Reserved."); // NOI18N

        jobProgressBar.setBackground(new java.awt.Color(0, 153, 153));
        jobProgressBar.setForeground(new java.awt.Color(255, 0, 0));
        jobProgressBar.setOpaque(false);
        jobProgressBar.setStringPainted(true);

        progressMessage.setBackground(new java.awt.Color(0, 0, 0));
        progressMessage.setForeground(new java.awt.Color(0, 204, 204));
        progressMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        progressMessage.setText("Connecting to Database");
        progressMessage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(204, 0, 0), new java.awt.Color(255, 255, 0), new java.awt.Color(0, 255, 255), new java.awt.Color(0, 102, 102)));
        progressMessage.setDoubleBuffered(true);

        progressNote.setBackground(new java.awt.Color(0, 0, 0));
        progressNote.setForeground(new java.awt.Color(0, 204, 204));
        progressNote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        progressNote.setText("Initializing Antifieze");
        progressNote.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 51, 0), new java.awt.Color(255, 255, 0), new java.awt.Color(0, 153, 153), new java.awt.Color(51, 255, 255)));

        javaVerLabel.setFont(new java.awt.Font("Tahoma", 0, 10));
        javaVerLabel.setForeground(new java.awt.Color(0, 51, 153));
        javaVerLabel.setText("Java Version: ");

        progJavaVersion.setFont(new java.awt.Font("Tahoma", 2, 10));
        progJavaVersion.setForeground(new java.awt.Color(0, 51, 153));

        jTextPane1.setBackground(new java.awt.Color(153, 0, 0));
        jTextPane1.setBorder(new javax.swing.border.MatteBorder(null));
        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("Trebuchet MS", 0, 10));
        jTextPane1.setForeground(new java.awt.Color(255, 204, 0));
        jTextPane1.setText("The Anti-Frieze suite (SMS) is \"free\" to test and try.\nIf you wish to use it to actually run your shop--Please donate what you think\nAnti - Frieze is worth to you"); // NOI18N
        jScrollPane1.setViewportView(jTextPane1);

        org.jdesktop.layout.GroupLayout afSplashInfoPanelLayout = new org.jdesktop.layout.GroupLayout(afSplashInfoPanel);
        afSplashInfoPanel.setLayout(afSplashInfoPanelLayout);
        afSplashInfoPanelLayout.setHorizontalGroup(
            afSplashInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(afSplashInfoPanelLayout.createSequentialGroup()
                .add(154, 154, 154)
                .add(progressMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 316, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
            .add(afSplashInfoPanelLayout.createSequentialGroup()
                .add(172, 172, 172)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 273, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
            .add(afSplashInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(javaVerLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(progJavaVersion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 188, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, afSplashInfoPanelLayout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .add(progressNote, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(182, 182, 182))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, afSplashInfoPanelLayout.createSequentialGroup()
                .add(afSplashInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(afSplashInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(wmsProductLogo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 381, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(afSplashInfoPanelLayout.createSequentialGroup()
                        .add(129, 129, 129)
                        .add(jobProgressBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)))
                .add(104, 104, 104))
        );
        afSplashInfoPanelLayout.setVerticalGroup(
            afSplashInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, afSplashInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(progressMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jobProgressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(progressNote, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 178, Short.MAX_VALUE)
                .add(afSplashInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, javaVerLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, progJavaVersion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(wmsProductLogo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16, 16, 16))
        );

        afSplashInfoPanel.setBounds(10, 10, 620, 450);
        jLayeredPane1.add(afSplashInfoPanel, javax.swing.JLayeredPane.DRAG_LAYER);

        bgSplashPanel.setBackground(new java.awt.Color(0, 0, 0));

        afSplashBgImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        afSplashBgImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/wms/antifreeze/gui/resources/AFSplash_Shaker_3_1_600_400.png"))); // NOI18N
        afSplashBgImage.setAlignmentY(0.0F);

        org.jdesktop.layout.GroupLayout bgSplashPanelLayout = new org.jdesktop.layout.GroupLayout(bgSplashPanel);
        bgSplashPanel.setLayout(bgSplashPanelLayout);
        bgSplashPanelLayout.setHorizontalGroup(
            bgSplashPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(afSplashBgImage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 640, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        bgSplashPanelLayout.setVerticalGroup(
            bgSplashPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(afSplashBgImage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 480, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        bgSplashPanel.setBounds(0, 0, 640, 480);
        jLayeredPane1.add(bgSplashPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        add(jLayeredPane1);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel afSplashBgImage;
    public javax.swing.JPanel afSplashInfoPanel;
    public javax.swing.JPanel bgSplashPanel;
    public javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextPane jTextPane1;
    public javax.swing.JLabel javaVerLabel;
    public javax.swing.JProgressBar jobProgressBar;
    public javax.swing.JLabel progJavaVersion;
    public javax.swing.JLabel progressMessage;
    public javax.swing.JLabel progressNote;
    public javax.swing.JLabel wmsProductLogo;
    // End of variables declaration//GEN-END:variables
    
}
