/*
 * UDTBaseListener.java
 *
 * Created on January 24, 2005, 10:23 PM
 */

package com.wms.antifreeze.gui;

import com.wms.util.gui.message.WMSEvent;

/**
 *
 * @author  mstemen
 */
public interface AFListener {
    
    public void sendMessage( WMSEvent event );
    
}
