
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.antifreeze.gui;

import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.antifreeze.gui.AF_ViewMgr.AFIcon;
import com.wms.util.gui.GUILogInterface.GLogLevel;
import com.wms.util.gui.message.WMSEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;

/**
 *
 * @author Matt
 */
public interface AFViewMgrInterface {

    public enum AFColor {

        OutterPanel,
        InnerPanel,
        LabelBack,
        LabelFore,
        HeaderBack,
        HeaderFore,
        TicketBack,
        TicketFore,
        MenuBack,
        MenuFore,
        ToolBack,
        ToolFore,
        ListFore,
        ListBack,
        fieldBack,
        fieldFore,
        TitleBack,
        TitleFore;
    }

    public enum AFFont {
        AFTicketFontSmall,
        AFTicketFontMed,
        AFTicketFontLrg;

    }

    public enum AFAction
    {
        AFActionUnknown,
        AFSearch,
        AFCreate,
        AFUpdate,
        AFDelete;

        public String getAction( String classType )
        {
            String retStr = null;
            switch( this )
            {
                case AFCreate:
                    retStr = "Create " + classType ;
                    break;
                case AFSearch:
                    retStr = "Search" + classType;
                    break;
                case AFUpdate:
                    retStr = "Update " + classType;
                    break;
                case AFDelete:
                    retStr = "Delete " + classType;
                    break;
            }

            return retStr;
        }
    }

    public boolean showPopupChoice(WMSEvent evt);

    public boolean showPopupChoice(WMSEvent evt, Icon icon, String choiceOne, String choiceTwo);

    public void showPopup(WMSEvent evt);

    public AFTicketInf getCurrentTicket();
    
    public void setCurrentTicket( AFTicketInf currTicket );

    public void showCustDetail(AFAction afAction);

    public Icon getIcon(AFIcon icon);

    public void sendMessage(WMSEvent evt);

    public Color getPrimaryColor();

    public Color getSecondaryColor();

    public Color getColorFromAFColor( AFColor targetAFColor );

    public Font getFontFromAFFont( AFFont targetAFFont );

    public void writeToGui(String msg, GLogLevel testLevel);
}
