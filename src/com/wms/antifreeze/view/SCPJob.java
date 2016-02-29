/*
 * SCPJob.java
 *
 * Created on October 26, 2005, 12:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.wms.antifreeze.view;

import java.util.*;
import java.awt.*;
import javax.swing.*;


/**
 *
 * @author mstemen
 */
class SCPProgressFrame extends JFrame
{
    private String title;    
    
    public SCPProgressFrame( String title )
    {
        super( title );
        this.title = title;        
        setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );                                                    
        setDefaultLookAndFeelDecorated( true );
    }
    
    public void setTitle( String title )
    {
        super.setTitle( title );
    }
    
    public void close()
    {
        setVisible( false );
    }
}

public abstract class SCPJob implements SCPJobInterface {

    public enum JobTypes
    {
        Long,
        Init,
        Short
    }
    private Object workResults;
    private int lengthOfTask;
    private int current = 0;
    private boolean done = false;
    private boolean cancelled = false;
    private String jobStatement = "";
    private SCPProgressPanel panel = null;
    private String note = "";
    private Vector<SCPJobWatcherInterface> watchers = new Vector<SCPJobWatcherInterface>();  
    private SCPProgressFrame statusFrame;
    private Dimension frameSize = new Dimension( 650, 225 );
    private Dimension panelSize = new Dimension( 650, 225 );
   
    
    
    /** Creates a new instance of SCPJob */
    public SCPJob( String jobStatement, int lengthOfTask, boolean showProgress ) {
        
        this.jobStatement = jobStatement;         
        if( showProgress )         
        {
            statusFrame = new SCPProgressFrame("");                        
            statusFrame.setTitle( SCPJob.this.jobStatement );
            statusFrame.setMinimumSize( frameSize );
            statusFrame.setPreferredSize( frameSize );
            
            Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();                     
            Dimension frameSize = statusFrame.getSize ();       
            int x = (screenSize.width - frameSize.width) / 2;       
            int y = (screenSize.height - frameSize.height) / 2;       

            statusFrame.setLocation (x - 200, y );                                           
            statusFrame.validate();
            statusFrame.repaint();

            panel = new SCPProgressPanel( 0, 100 );              
            panel.setJob( SCPJob.this );                                
            statusFrame.add( panel );      
            statusFrame.pack();
            
            statusFrame.setVisible( true );     
        }                           
        
        /*
        if( showProgress )
        {
            
            try
            {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        statusFrame = new SCPProgressFrame();                        
                        statusFrame.setTitle( SCPJob.this.jobStatement );
                        statusFrame.setSize( new Dimension( 500, 200 ));
                        statusFrame.setVisible( true );
                        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();                     
                        Dimension frameSize = statusFrame.getSize ();       
                        int x = (screenSize.width - frameSize.width) / 2;       
                        int y = (screenSize.height - frameSize.height) / 2;       

                        statusFrame.setLocation (x, y);                               
                        statusFrame.validate();
                        statusFrame.repaint();
                    }
                });            
            }
            catch( Exception e )
            {
                
            }
        }
         */
    }
    
    public void start() {
        final SCPWorker worker = new SCPWorker( this );
        worker.start();                
    }
    
    public abstract SCPJobInterface process();
    
    public abstract boolean checkDone();
    
    public int getLengthOfTask() {
        return lengthOfTask;
    }
    
    public int getCurrent() {
        return current;
    }
    
    public Object getWorkResults()
    {
        return workResults;
    }
    
    public void setWorkResults( Object newWorkResults)
    {
        workResults = newWorkResults;
    }
    
    public void stop() {
        cancelled = true;
        jobStatement = null;
    }
    
    public boolean isDone(){ return done; }
    
    public void setMessage( String message ) {
        this.jobStatement = message;
    }
    
    public String getMessage() {
        return jobStatement;
    }
    
    public void setPanel( SCPProgressPanel panel ) {
        this.panel = panel;
        panel.setMessage( jobStatement );
    }
    
    public void setNote( String note ) {
        if( panel != null )
            panel.setNote( note );
    }
    public String getNote() {
        return note;
    }
    
    protected void complete() {
        done = true;
        if( statusFrame != null )
            statusFrame.setVisible( false );
        wakeAll();
    }
    
    public void addWatcher( SCPJobWatcherInterface watcher )
    {
        watchers.add( watcher );
    }
    
    public void removeWatcher( SCPJobWatcherInterface watcher )
    {
        if( watchers.contains( watcher ))
            watchers.remove( watcher );
    }
    
    private void wakeAll()
    {
        Iterator<SCPJobWatcherInterface> it = watchers.iterator();
        while( it.hasNext() )
        {
            it.next().jobComplete();
        }
    }   
    
    public void update( int newCurrent )
    {
        current = newCurrent;
        if( panel != null )
        {
            panel.setValue( newCurrent );
            panel.validate();
        }
        statusFrame.validate();
        statusFrame.repaint();                
    }   
}

