/*
 * AntiFreezeApp.java
 */
package com.wms.antifreeze;

import com.wms.antifreeze.gui.AF_ViewMgr;
import com.wms.antifreeze.view.AFJob;
import com.wms.antifreeze.view.SplashFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class AntiFreezeApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */


    static private AFJob startUpJob;
//    static private SplashFrame splashFrame = null;

    @SuppressWarnings("static-access")
    protected void startup() {

        try {
//            splashFrame = new SplashFrame();
            AF_ViewMgr afViewMgr = new AF_ViewMgr( this );
//            afViewMgr.setProgressJob(startUpJob);
            if( startUpJob != null )
            {
                startUpJob.start();
            }
            
            afViewMgr.initAntiFreezeSystem();
            show(afViewMgr);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AntiFreezeApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AntiFreezeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static protected void setStatusJob( AFJob startupStatusJob)
    {
        startUpJob = startupStatusJob;
    }

    static protected void killStatusJob()
    {
        if( startUpJob != null )
        {
            startUpJob.stop();
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     * @param root
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of AntiFreezeApp
     */
    public static AntiFreezeApp getApplication() {
        return Application.getInstance(AntiFreezeApp.class);
    }

    /**
     * Main method launching the application.
     * @param args
     */
    public static void main(String[] args) {

//        try {
//            java.awt.EventQueue.invokeAndWait(new Runnable() {
//                public void run() {
//                    final AFJob progressJob = new AFJob("Starting Anti-Frieze Manager. Please Wait...", 100, true) {
//
//                        public boolean checkDone() {
//                            if (getCurrent() == 100) {
//                                complete();
//                                return true;
//                            }
//                            return false;
//                        }
//
//                        @Override
//                        public AFJobInterface process(AFJob job) {
//                            return job;
//                        }
//                    };
//                    AntiFreezeApp.setStatusJob(progressJob);
//                    AntiFreezeApp.killStatusJob();
//                }
//            });
//        } catch (java.lang.Exception e) {
//            System.err.println("Got exception showing startup progress: " + e.getMessage());
//            e.printStackTrace();
//        }
                
        launch(AntiFreezeApp.class, args);

    }
}
