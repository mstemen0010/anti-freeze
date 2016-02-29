/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze;

import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.entity.customer.AFCustomerEntity;
import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.antifreeze.AFShopWorkflow.TicketStage;
import com.wms.antifreeze.entity.car.AFCarEntity;
import com.wms.antifreeze.entity.ticket.AFTicket;
import com.wms.antifreeze.entity.ticket.AFTicketEntity;
import com.wms.antifreeze.gui.AFTicketBox;
import com.wms.antifreeze.view.AFTicketView;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import com.wms.util.gui.GUILogInterface;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Matt
 */
public class AFTicketMgr implements AFMgrInf {

        
    private ManagerType myMgrKey;
    private HashMap<Integer, AFTicketInf> afTickets = new HashMap<Integer, AFTicketInf>();
    private AFMgr afMgr = null;
    GUILogInterface gLog = null;
    AFTicketBox afTicketBox = null; // source of and visual tracking of all tickets.
    AFTicketInf wizardInf;

    public AFTicketMgr( AFTicketInf wizardInf, GUILogInterface log )
    {
        gLog = log;
        afMgr = AFMgr.callMgr();
        myMgrKey = afMgr.registerMgrForType(this, ManagerType.Ticket);
        this.wizardInf = wizardInf;
    }

   public void loadOutStandingTickets() {

        gLog.startSectionToGui("LOADING TICKETS", GUILogInterface.GLogLevel.Message);
        gLog.writeToGui("Loading existing Tickets from previous day...", GUILogInterface.GLogLevel.Message);
        VRecord tickRec;
        VTable vt = afMgr.getVTable("ticket");
        int ticketLoadedCount = 0;
        if (vt != null) {
            // Vector<VRecord> recs = vt.getRows();
            Iterator<VRecord> recs = vt.iterator();
            vt.setOrderByFieldName("ticketNum");
            // for( int i = 0; i < recs.size(); i++ )
            while (recs.hasNext()) {
                tickRec = recs.next();
                // tickRec = recs.elementAt(i);
                String stageStr = tickRec.getFieldData("stage");
                int stage = Integer.parseInt(stageStr);
                String rowKey = tickRec.getFieldData("ticketNum");
                String carcomment = tickRec.getFieldData("carcomment");
                int rowId = Integer.parseInt(rowKey);
                if (stage < TicketStage.Closed.ordinal() && rowId != 1) {
                    afTicketBox.addTicket(rowId, carcomment);
                    ticketLoadedCount++;
                }
            }
        }
        gLog.writeToGui("Loaded: " + ticketLoadedCount++ + " from previous days", GUILogInterface.GLogLevel.Critical);
        gLog.endSectionToGui("LOADING TICKETS", GUILogInterface.GLogLevel.Message);
    }

    /**
     *
     * @param carEntity
     * @return
     */
    public AFTicketInf createTicket(AFCarEntity carEntity, AFCustomer afCus) {
        // int ticketNum = AFTicketEntity.getNextTicketNumber();

        ManagerOp requestedOp = ManagerOp.CreateTicket;
        AFTicketInf newAfTicket = null;
        if (requestedOp.isAllowed(myMgrKey)) {
            AFTicketEntity ticketEntity = new AFTicketEntity();
            int ticketNum = ticketEntity.getTicketNum();
            AFTicketView ticketView = new AFTicketView(ticketEntity, ticketNum, carEntity, afCus);
            // Component ticketComp = ticketView.getComponent();
            newAfTicket = new AFTicket(ticketEntity, ticketView);
            // ticketTabs.addTab(TOOL_TIP_TEXT_KEY, ticketTabIcon.getIcon(), ticketComp, TOOL_TIP_TEXT_KEY);
            // int newTabIndex = getAvailableTabIndex();
            afTickets.put(newAfTicket.getTicketNumber(), newAfTicket);
            ticketView.fillInCarInfo();

            // ticketTabs.insertTab(ticketView.getTabTitle(), null, ticketComp, TOOL_TIP_TEXT_KEY, newTabIndex);
            // colorTab(newTabIndex);
            // ticketTabs.addTab(newTicket.getTabTitle(), ticketTabIcon.getIcon(), ticketComp, TOOL_TIP_TEXT_KEY);
            //ticketTabs.setIconAt(tabIndex, ticketTabIcon.getIcon());
            // Component c = ticketTabs.getTabComponentAt(newTabIndex);            
            // displayPaperTicket(newTabIndex, ticketView, ticketComp);
        }
        return newAfTicket;
    }

    public AFTicketInf createTicket(String make, String model, String year, AFCustomer afCus) {
        AFTicketInf newAfTicket = null;
        ManagerOp requestedOp = ManagerOp.CreateTicket;
        if (requestedOp.isAllowed(myMgrKey)) {
            //first get rid of the "stub" ticket, the default one

//            int newTicketNum = AFTicketEntity.getNextTicketNumber();
            AFCarEntity carEntity = new AFCarEntity(make, model, year);
            AFTicketEntity ticketEntity = new AFTicketEntity();

            if (ticketEntity != null) {
                ticketEntity.createNewTicket(make, model, year);
            }
            int newTicketNum = ticketEntity.getTicketNum();
            AFTicketView ticketView = new AFTicketView(ticketEntity, newTicketNum, carEntity, afCus);
            newAfTicket = new AFTicket(ticketEntity, ticketView);
            afTickets.put(newAfTicket.getTicketNumber(), newAfTicket);
            // Component ticketComp = ticketView.getComponent();
            ticketView.fillInCarInfo();
            // insert it in the "Ticket Book"
            {
                // int newTabIndex = getAvailableTabIndex();
                // ticketTabs.insertTab(ticketView.getTabTitle(), null, ticketComp, TOOL_TIP_TEXT_KEY, newTabIndex);
                // colorTab(newTabIndex);
                // ticketTabs.addTab(newTicket.getTabTitle(), ticketTabIcon.getIcon(), ticketComp, TOOL_TIP_TEXT_KEY);
                //ticketTabs.setIconAt(tabIndex, ticketTabIcon.getIcon());
                // Component c = ticketTabs.getTabComponentAt(newTabIndex);
                // this.currentTicket = newAfTicket;
                // displayPaperTicket(newTabIndex, ticketView, ticketComp);
            }
        }

        return newAfTicket;
    }

    public AFTicketInf getTicket(Integer ticketNumber) {
        AFTicketInf retAFTicket = null;

        ManagerOp requestedOp = ManagerOp.CreateTicket;
        if (requestedOp.isAllowed(myMgrKey)) {
            retAFTicket = this.afTickets.get(ticketNumber);
            if (retAFTicket == null) {
                //TODO: look up the ticket in the database
            }
        }

        return retAFTicket;
    }


  
    public ManagerType registerMgrForType(AFMgrInf inf, ManagerType typeToRegister) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFMgrInf getManagerForType(ManagerType targetMgr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTicketBox(AFTicketBox afticketBox) {
        this.afTicketBox = afticketBox;
    }

    public AFCustomer getCustomer(Integer cusID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getNextCustomerId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFCustomerEntity createCustomer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addCustomer(AFCustomerEntity newCustomner) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFTicketInf createTicket(AFCarEntity carEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFTicketInf createTicket(String make, String model, String year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCus(Integer ticketNum, int cusId) {
        AFTicketInf ticket = getTicket( ticketNum );
        if( ticket != null )
        {
            
        }
    }

    public void updateTicketCar(Integer ticketNum, int carId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketEst(Integer ticketNum, int estId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
