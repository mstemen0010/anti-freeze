/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.antifreeze.entity.ticket;

import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.gui.AFViewMgrInterface.AFAction;

/**
 *
 * @author Matt
 */
public interface AFTicketInf {

    public enum TicketIndicator
    {
        Unknown,
        Car,
        Customer,
        Estimate;
    }

    /**
     * 
     * @return
     */
    public AFAction getAction();

    public int getTicketNumber();

    public void createNewTicket(String make, String model, String year, AFCustomer afCus);

    public void updateTicketCusId(int cusId);

    public void updateTicketCarId(int carId );

    public void updateTicketEstId(int estId );

    public void setTicketCus( AFCustomer afCus );

    public void processTicketForward();

    public void showCustIndicator(boolean show);

    public void showCarIndicator(boolean show);

    public void showEstIndicator(boolean show);

    public void displayCustName( String firstName, String lastName );

     public void displayMakeModelYear(String make, String model, String year );

     public void displayInvoiceNumber( String invoiceNum );

     public void displayTicketInfo( String invoiceNum, String first, String last, String make, String model, String year );
}
