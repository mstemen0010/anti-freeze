/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SplashFrame.java
 *
 * Created on Feb 17, 2010, 11:44:16 AM
 */
package com.wms.antifreeze.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author Matt
 */
public class SplashFrame extends java.awt.Frame implements Runnable {

    /** Creates new form SplashFrame */
    Image splashBGImage;
    Graphics parentGraphics = null;
    int splashWidth = 640;
    int splashHeighth = 400;
    private String[] welcomeMessageStr = {
        "The Anti-Freeze Shop Management Suite (SMS)",
        "is \"free\" to test and try. If you wish to use it",
        "to actually run your shop--Please donate what",
        "you think Anti - Freeze is worth to you"};
    private int jobCheckInterval = 5000;
    private int defHeight = 480;
    private int defWidth = 640;
    private String version = System.getProperty("java.vm.version");
    private String jreName = System.getProperty("java.vm.name");
    private boolean running;
    private Color fontColor1 = null;
    private Color fontColor2 = null;
    private Color progBG = null;
    private Color fgColor = null;
    private Color bgColor = null;
    private Graphics afg;
    private int progressValue;
    private String progressMessage;
    private String progressNote;
    private StringBuilder versionSb = new StringBuilder("Java Version:");
    Dimension progPanelDim;
    Dimension messageDim;
    Dimension noteDim;
    Dimension progDim;
    String copyRightStr = "Copyright © 2010 Wintermute Studio - All Rights Reserved.";
    Font progFont;
    FontMetrics fm = null;
    int centerOfScreen = 0;
    int centerOfSplash = 0;

    int splashPicX = 35;
    int splashPicY = 35;
    
    private Component myComponent;



    public SplashFrame(Component c) {
//        parentGraphics = g;
        progressValue = 0;
        progressMessage = "No Progress Message yet...";
        progressNote = "No Progress Note yet...";
        versionSb.append(jreName).append("-").append(version);

        myComponent = c;

        initComponents();
        ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(SplashFrame.class);
        progFont = progressStatus.getFont();


        ImageIcon ic = null;
        ic = new javax.swing.ImageIcon(getClass().getResource("/com/wms/antifreeze/gui/resources/AFSplash_Shaker_3_1_600_400.png"));
        splashBGImage = ic.getImage();
        System.out.println("SplashImage is: " + splashBGImage.toString());
        progPanelDim = getSize();
        messageDim = new Dimension(200, 15);
        noteDim = new Dimension(250, 15);
        progDim = new Dimension(200, 20);
        progBG = Color.red.darker();


        // center us
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        centerOfScreen = screenSize.width / 2;
        splashPicX = (frameSize.width - splashBGImage.getWidth(this)) / 2;

        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;


        setLocation(x, y);

        centerOfSplash = centerOfScreen;

        // setup canvases
//        this.messageArea.setPreferredSize(messageDim);
//        this.progressBar.setPreferredSize(progDim);
//        progressBar.setBackground(Color.BLACK);
//        this.noteArea.setPreferredSize(noteDim);

        setVisible(true);

        afg = progressStatus.getGraphics();
        if (afg != null) {
            afg.drawImage(splashBGImage,splashPicX, splashPicY, 600, 400, this);
        }
        fm = afg.getFontMetrics(progFont);



    }

    public void paint(Graphics g) {
        drawAll();
    }

    public void update(Graphics g) {
        drawAll();
    }

    private void drawAll() {
        drawSplashBG();
        drawMessage();
        drawProgress();
        drawNote();
        drawVersion();
        drawCopyRight();
    }

    private void drawVersion() {
    }

    private void drawCopyRight() {
        afg.drawString(copyRightStr, 175, 400);
    }

    public void setProgress(int value) {
        progressValue = value;
        drawAll();
    }

    private void drawSplashBG() {
        afg.setColor(Color.ORANGE);

        afg.draw3DRect(0, 0, this.progPanelDim.width, progPanelDim.height, true);
        afg.drawImage(splashBGImage, splashPicX, splashPicY, 600, 400, this);
    }

    private void drawProgress() {
        int progXBase = 175;
        int progYBase = 125;
        afg.setFont(progFont);

        // Graphics g = progressBar.getGraphics();
        int progCenter = progDim.width / 2;

        int intComplete = 100 - progressValue;
        System.out.println("IntComplete: " + intComplete);
        double percentComplete = ((double) intComplete) / 100.00;
        System.out.println("amtDone; " + percentComplete);
        int amtLeft = (int) (progDim.width * percentComplete);
        System.out.println("amtLeft: " + amtLeft);
        int amtToDraw = progDim.width - amtLeft;
        System.out.println("amtToDraw: " + amtToDraw);



        System.out.println("Progress Graphics are: " + afg);
        afg.setColor(Color.darkGray);
        afg.draw3DRect(progXBase, progYBase, progDim.width, progDim.height, false);
        afg.setColor(progBG.brighter());
        afg.fillRect(progXBase, progYBase, amtToDraw, progDim.height);
        afg.setColor(Color.white);
        if (progressValue < 100) {

            afg.setColor(Color.black);
            afg.fillRect(progXBase, progYBase, progDim.width, progDim.height);
            afg.setColor(progBG.brighter());
            afg.fillRect(progXBase, progYBase, amtToDraw, progDim.height);
            afg.setColor(Color.white);
            afg.setFont(progFont);
            afg.drawString(String.valueOf(progressValue), progXBase + progCenter, progYBase + progDim.height / 2);
            afg.drawString("%", progXBase + progCenter + 20, progYBase + progDim.height / 2);
        } else {
            afg.setColor(Color.green);
            afg.fillRect(progXBase, progYBase, amtToDraw, progDim.height);
            afg.setColor(Color.white);
            afg.drawString("Complete!", progXBase + progCenter - 20, progYBase + progDim.height / 2);
        }




    }

    public void setNote(String note) {
        progressNote = note;
        System.out.println("AFStarterPanel::setValue - Setting note to: " + note);
        drawAll();
    }

    private void drawNote() {
        int noteXBase = 175;
        int noteYBase = 175;

        int noteCenter = progPanelDim.width / 2;
        // noteDim.width / 2;
        int noteWidth = fm.stringWidth(progressNote);
        int noteWidthOffset = noteWidth / 2;

        afg.setColor(Color.ORANGE);
        afg.draw3DRect(noteXBase, noteYBase, noteDim.width, noteDim.height, false);
        afg.setColor(Color.black);
        afg.fillRect(noteXBase, noteYBase, noteDim.width, noteDim.height);
        afg.setColor(Color.ORANGE);
        afg.drawString(progressNote, noteCenter - noteWidthOffset, noteYBase + noteDim.height / 2 + 5);
    }

    public void setMessage(String message) {
        progressMessage = message;
        System.out.println("AFStarterPanel::setValue - Setting message to: " + message);
        drawAll();
    }

    private void drawMessage() {
        int messXBase = 175;
        int messYBase = 70;



        int messCenter = progPanelDim.width / 2;
        //messageDim.width / 2;

        int messageWidth = fm.stringWidth(progressMessage);
        int messageWidthOffset = messageWidth / 2;
        afg.setColor(Color.ORANGE);
        afg.draw3DRect(messXBase, messYBase, messageDim.width, messageDim.height, false);
        afg.setColor(Color.black);
        afg.fillRect(messXBase, messYBase, messageDim.width, messageDim.height);
        afg.setColor(Color.ORANGE);
        afg.drawString(progressMessage, messCenter - messageWidthOffset, messYBase + messageDim.height / 2 + 5);
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                drawAll();
            } catch (InterruptedException ex) {
//                Logger.getLogger(AFSplashFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressStatus = new java.awt.Canvas();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.wms.antifreeze.AntiFreezeApp.class).getContext().getResourceMap(SplashFrame.class);
        setBackground(resourceMap.getColor("AFSplashForm.background")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusableWindowState(false);
        setFont(resourceMap.getFont("AFSplashForm.font")); // NOI18N
        setMinimumSize(new java.awt.Dimension(640, 480));
        setName("AFSplashForm"); // NOI18N
        setResizable(false);
        setTitle(resourceMap.getString("AFSplashForm.title")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new java.awt.GridLayout(1, 0));

        progressStatus.setBackground(resourceMap.getColor("progressStatus.background")); // NOI18N
        progressStatus.setFont(resourceMap.getFont("progressStatus.font")); // NOI18N
        progressStatus.setName("progressStatus"); // NOI18N
        add(progressStatus);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     *
     */
//    @Override
//    public void update(Graphics g)
//    {
//         g.drawImage(splashBGImage, 0, 0, this);
//         drawMessage();
//         drawProgress();
//         drawNote();
//
//
//    }
//
//    @Override
//    public void paint(Graphics g)
//    {
//         g.drawImage(splashBGImage, 0, 0, this);
//         drawMessage();
//         drawProgress();
//         drawNote();
//
//    }
    public void stop() {
        // sleep(null, 5000);
        running = false;
        setVisible(false);
    }

    public void showLogo(boolean flag) {
//        wmsProductLogo.setVisible(flag);
    }

    public void showVersion(boolean flag) {
//        progJavaVersion.setVisible(flag);
    }

    public void setValue(int value) {
        // if( ParallaxMgr.getMgr() != null )
        //    ParallaxMgr.getMgr().getLog().write( "Setting done value to: " + value );
        System.out.println("AFStarterPanel::setValue - Setting value to: " + value);
        setProgress(value);
    }

    public static void sleep(Thread t, long sTime) {
        try {
            Thread.sleep(sTime);
        } catch (InterruptedException ex) {
//            Logger.getLogger(AFSplashFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        Thread t = null;
//        SplashFrame afs = new SplashFrame();
//        t = new Thread(afs);
//        t.start();
//        afs.setMessage("Starting AfMgr");
//        afs.setValue(15);
//        afs.setNote("Initing sub-AfMgrs");
//        sleep(t, 5000);
//        afs.setMessage("Starting Memory ");
//        afs.setValue(20);
//        sleep(t, 5000);
//        afs.setMessage("Initalizig Ticketing System");
//        afs.setValue(35);
//        sleep(t, 5000);
//        afs.setValue(40);
//        sleep(t, 5000);
//        afs.setValue(50);
//        sleep(t, 5000);
//        afs.setValue(57);
//        sleep(t, 5000);
//        afs.setMessage("Setting up Messaging");
//        afs.setValue(60);
//        sleep(t, 5000);
//        afs.setValue(70);
//        sleep(t, 5000);
//        afs.setValue(80);
//        sleep(t, 5000);
//        afs.setMessage("Complete!");
//        afs.setValue(100);
//        sleep(t, 5000);
//
//        afs.stop();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas progressStatus;
    // End of variables declaration//GEN-END:variables
}
