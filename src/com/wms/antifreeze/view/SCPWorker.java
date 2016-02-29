/*
 * SCPWorker.java
 *
 * Created on October 26, 2005, 9:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.wms.antifreeze.view;

import javax.swing.SwingUtilities;
/**
 *
 * @author mstemen
 */

public class SCPWorker implements Runnable
{
    private SCPJob taskToRun;
    private Object value;  // see getValue(), setValue()    
    private boolean running = true;
    
    /** Creates a new instance of SCPWorker */
    public SCPWorker( SCPJob taskToRun ) {
        this.taskToRun = taskToRun;

        Thread t = new Thread(this);
        t.start();
        // threadVar = new ThreadVar(t);
        // threadVar.get().setName("WorkerThread" + hashCode() );        
    }
            
    public void run() {                    
        while( running )                
        {                    
            try 
            {

                setValue(runTask());
                Thread.sleep( 1000 );
                SCPWorker.this.taskToRun.checkDone();
                if( SCPWorker.this.taskToRun.isDone() )
                    running = false;                   
            }            
            catch( java.lang.InterruptedException e )             
            {
           
            }
           
        }        
        // threadVar.clear();                             
    }    
        
    public SCPJobInterface runTask()
    {
        // call the SCPJob
         return taskToRun.process();
    }
    

    /** 
     * Class to maintain reference to current worker thread
     * under separate synchronization control.
     */
    private static class ThreadVar {
        private Thread thread;
        ThreadVar(Thread t) { thread = t; }
        synchronized Thread get() { return thread; }
        synchronized void clear() { thread = null; }
    }

    private ThreadVar threadVar;

    /** 
     * Get the value produced by the worker thread, or null if it 
     * hasn't been constructed yet.
     */
    protected synchronized Object getValue() { 
        return value; 
    }

    /** 
     * Set the value produced by worker thread 
     */
    private synchronized void setValue(Object x) { 
        value = x; 
    }

    /**
     * Called on the event dispatching thread (not on the worker thread)
     * after the <code>construct</code> method has returned.
     */
    public void finished() {
    }

    /**
     * A new method that interrupts the worker thread.  Call this method
     * to force the worker to stop what it's doing.
     */
    public void interrupt() {
        Thread t = threadVar.get();
        if (t != null) {
            t.interrupt();
        }
        threadVar.clear();
    }

    /**
     * Return the value created by the <code>construct</code> method.  
     * Returns null if either the constructing thread or the current
     * thread was interrupted before a value was produced.
     * 
     * @return the value created by the <code>construct</code> method
     */
    public Object get() {
        while (true) {  
            Thread t = threadVar.get();
            if (t == null) {
                return getValue();
            }
            try {
                t.join();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // propagate
                return null;
            }
        }
    }


    /**
     * Start the worker thread.
     */
    public void start() {
        /*
        Thread t = threadVar.get();
        if (t != null) {
            t.start();
        }
         */
    }                  
}
