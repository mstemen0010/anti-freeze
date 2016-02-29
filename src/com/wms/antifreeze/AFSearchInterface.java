/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze;

import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.entity.ticket.AFTicketInf;

/**
 *
 * @author Matt
 */
public interface AFSearchInterface {

    public enum AFSearchType
    {
        Unknown,
        Customer,
        Car,
        Ticket;
    }

    public enum AFCustomerSearchField
    {
        Unknown,
        CusID,
        CusLastName,
        CusFirstName,
        CusStreet1,
        CusCity,
        CusState,
        CusZip,
        CusHome,
        CusCar;
    }

    public void setAFSearchType( AFSearchType afType );

    public void setCustomerSearchField( AFCustomerSearchField afField );

    public void setTicketSearchField( AFCustomerSearchField afField );

    public AFCustomer findTicketById( String id );

    public AFCustomer findCustomer();

    public void setSearchString(String srchStr);

    public AFTicketInf findTicketByAny( String searchValue, String delimeter);

    public AFTicketInf findTicketByAll( String searchValue, String delimeter);

    public AFCustomer findCustomerById( String id );

    public AFCustomer findCustomerByAny( String searchValue, String delimeter);

    public AFCustomer findCustomerByAll( String searchValue, String delimeter);
    
}
