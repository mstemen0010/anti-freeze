/*
 * SCPWorkerJobInterface.java
 *
 * Created on October 26, 2005, 12:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.wms.antifreeze.view;

/**
 *
 * @author mstemen
 */
public interface SCPJobInterface {
    public abstract SCPJobInterface process();   
    public abstract Object getWorkResults();
    public abstract void setWorkResults( Object workResults );
    public abstract boolean isDone();
    public abstract boolean checkDone();
    public abstract void stop();
    public abstract void setPanel( SCPProgressPanel panel );
}
