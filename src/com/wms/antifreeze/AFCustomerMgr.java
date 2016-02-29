/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze;

import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.antifreeze.entity.car.AFCarEntity;
import com.wms.antifreeze.entity.customer.AFCustomerEntity;
import com.wms.antifreeze.gui.AFTicketBox;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import com.wms.util.gui.GUILogInterface;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Matt
 */
public class AFCustomerMgr implements AFMgrInf{

    GUILogInterface log = null;
    AFMgr afMgr = null;
    private ManagerType myMgrKey;
    private HashMap<Integer, AFCustomer> afCustomers = new HashMap<Integer, AFCustomer>();

    public AFCustomerMgr( GUILogInterface log )
    {
        this.log = log;
        afMgr = AFMgr.callMgr();
        myMgrKey = afMgr.registerMgrForType(this, ManagerType.Customer);
        loadCusTable();
    }

    public void loadCusTable()
    {
        VTable cusTable = afMgr.getVTable("cus");
        cusTable.setIgnoreCase(true);
        int cusCount = 0;
        if( cusTable != null )
        {
            Iterator<VRecord> cusRecs = cusTable.getRows().iterator();
            while( cusRecs != null && cusRecs.hasNext())
            {
                VRecord cusRec = cusRecs.next();
                if( cusRec != null )
                {
                    AFCustomerEntity afEnt = new AFCustomerEntity(cusRec);
                    AFCustomer afCus = new AFCustomer(afEnt);
                    Integer cusId = afCus.getAFCustID();
                    afCustomers.put(cusId, afCus);
                }
            }
        }
    }

    public AFCustomerEntity createCustomer( )
    {
        AFCustomerEntity custEnt = new AFCustomerEntity();

        return custEnt;
    }

    public void addCustomer(AFCustomerEntity newCustomer )
    {
        VTable cusTable = afMgr.getVTable("cus");
        VRecord cusRec = newCustomer.getVRecord();
        String pattern = cusTable.getInsertStatement();
        String firstName = newCustomer.getCusFirstName();
        String lastName = newCustomer.getCusLastName();

        this.afMgr.displayCustName(firstName, lastName);

        String testPattern = "INSERT INTO cus (CusID,CusLastName,CusFirstName,CusMI,CusOldName,CusStreet1,CusStreet2) VALUES (%d,%s,%s,%s,%s,%s,%s)";
        int rowsInserted = cusTable.insertRecord(cusRec, testPattern);
        if( rowsInserted != 0 )
        {
            // report the new customerId to AFMgr
            int cusId = newCustomer.getCusID();
            afMgr.setCurrentCustomerId(cusId);
            System.out.println("added new Customer");
        }
    }

    public int getNextCustomerId()
    {
        int id = -1;
        VTable cusTable = afMgr.getVTable("cus");
        String nextId = cusTable.getMaxKeyValue();
        if( nextId != null )
            id = Integer.parseInt(nextId);
        return id + 1;
    }

    public AFCustomer getCustomer( Integer cusID)
    {
        AFCustomer cusFound = null;
        cusFound = afCustomers.get(cusID);
        return cusFound;
    }

    public AFTicketInf createTicket(String make, String model, String year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFTicketInf createTicket(AFCarEntity carEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFTicketInf getTicket(Integer ticketNum) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTicketBox(AFTicketBox aftickeBox) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFCustomer createCustomer(String firstName, String lastName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ManagerType registerMgrForType(AFMgrInf inf, ManagerType typeToRegister) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFMgrInf getManagerForType(ManagerType targetMgr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void loadOutStandingTickets() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCus(Integer ticketNum, int cusId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCar(Integer ticketNum, int carId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketEst(Integer ticketNum, int estId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
