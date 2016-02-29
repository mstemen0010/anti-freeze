/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze.entity.ticket;

import com.wms.antifreeze.AFMgr;
import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.gui.AFViewMgrInterface.AFAction;
import com.wms.antifreeze.view.AFTicketView;
import com.wms.jdbtk3.VRecord;
import java.util.Date;

/**
 *
 * @author Matt
 *
 * a simple map of the Database Entity, respective view and all
 * other elements in the scope of a given entity
 */


public class AFTicket implements AFTicketInf {

    AFTicketEntity entity;
    AFTicketView view;
    AFAction ticketAction = AFAction.AFActionUnknown;
    Date ticketDate = null;
    AFMgr afMgr = null;
   
    
    public AFTicket(AFTicketEntity dbEntity, AFTicketView view )
    {
        this.entity = dbEntity;
        this.view = view;
        ticketDate = dbEntity.getTicketDate();
        afMgr = AFMgr.callMgr();
        if( ticketDate == null )
            ticketDate = new Date( afMgr.getCurrentTime() );
        
        view.setAFTicket(this);
    }
    
    public AFTicketView getView()
    {
        return view;
    }

    public AFTicketEntity getEntity()
    {
        return entity;
    }

    public void setAction( AFAction ticketAction )
    {
        this.ticketAction = ticketAction;
    }

    public AFAction getAction()
    {
        return this.entity.getAction();
    }

    public int getTicketNumber() {
         return entity.getTicketNum();
    }

    public void createNewTicket(String make, String model, String year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void processTicketForward() {
        if( this.entity != null)
        {
            entity.processTicketForward();
        }
    }

    public void showCustIndicator(boolean show) {
        if( this.view != null )
        {
            view.showTicketIndicator(TicketIndicator.Customer, show);
        }
    }

    public void showCarIndicator(boolean show) {
        if( this.view != null )
        {
            view.showTicketIndicator(TicketIndicator.Car, show);
        }
    }

    public void showEstIndicator(boolean show) {
        if( this.view != null )
        {
            view.showTicketIndicator(TicketIndicator.Estimate, show);
        }
    }

    public void displayCustName(String firstName, String lastName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void displayMakeModelYear(String make, String model, String year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void displayInvoiceNumber(String invoiceNum) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void displayTicketInfo(String invoiceNum, String first, String last, String make, String model, String year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void createNewTicket(String make, String model, String year, AFCustomer afCus) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTicketCus(AFCustomer afCus) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCusId(int cusId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCarId(int carId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketEstId(int estId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
