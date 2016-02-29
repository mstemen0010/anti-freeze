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

/**
 *
 * @author Matt
 */
public interface AFMgrInf {

    /**
     *
     */
    public enum ManagerOp
    {
        None,
        ReferManager,
        CreateTicket,
        CreateCar,
        CreateCustomer,
        CreateEstimate;

        ManagerType allowedTypeForOp = ManagerType.Unassigned;

        /**
         *
         */
        public void setAllowedValues()
        {
            ManagerOp ops[] = values();
            for( int i = 0; i < ops.length; i++ )
            {
                if( ops[i] == CreateTicket)
                    allowedTypeForOp = ManagerType.Ticket;
                else if( ops[i] == CreateCar)
                    allowedTypeForOp = ManagerType.Car;
                else if( ops[i] == CreateCustomer )
                    allowedTypeForOp = ManagerType.Customer;
                else if( ops[i] == CreateEstimate )
                    allowedTypeForOp = ManagerType.Estimate;
                else if( ops[i] == ReferManager )
                    allowedTypeForOp = ManagerType.Master;

            }
      
        }
        /**
         *
         * @param typeToTest
         * @return
         */
        public boolean isAllowed( ManagerType typeToTest)
        {
            boolean opIsAllowed = false;
            switch( this )
            {
                case ReferManager:
                    opIsAllowed = typeToTest == ManagerType.Master;
                    break;
                case CreateTicket:
                    opIsAllowed = typeToTest == ManagerType.Ticket;
                    break;
                case CreateCar:
                    opIsAllowed = typeToTest == ManagerType.Car;
                    break;
                case CreateCustomer:
                    opIsAllowed = typeToTest == ManagerType.Customer;
                    break;
                case CreateEstimate:
                    opIsAllowed = typeToTest == ManagerType.Estimate;
                    break;
            }



            return opIsAllowed;
        }
    }

    /**
     *
     */
    public enum ManagerType
    {
        Unassigned,
        Master,
        Customer,
        Ticket,
        Car,
        Estimate;

        boolean MasterRegistered = false;
        boolean CustomerRegistered = false;
        boolean TicketRegistered = false;
        boolean CarRegistered = false;
        boolean EstimateRegistered = false;

        public ManagerType registerType( ManagerType type )
        {
            // one time, first in registeration system
            ManagerType newType = Unassigned;
            ManagerType kickedOut = Unassigned;
            switch( type )
            {
                case Master:
                    if( MasterRegistered )
                        return kickedOut;
                    newType = Master;
                    MasterRegistered = true;
                    break;
                case Customer:
                    if( CustomerRegistered )
                        return kickedOut;
                    newType = Customer;
                    CustomerRegistered = true;
                    break;
                case Ticket:
                    if( TicketRegistered )
                        return kickedOut;
                    newType = Ticket;
                    TicketRegistered = true;
                    break;
                case Car:
                    if( CarRegistered )
                        return kickedOut;
                    newType = Car;
                    CarRegistered = true;
                    break;
                case Estimate:
                    if( EstimateRegistered )
                        return kickedOut;
                    newType = Estimate;
                    EstimateRegistered = true;
                    break;
            }

            return newType;
        }
    }

    /**
     *
     */
    ManagerType mgrRegister = ManagerType.Unassigned;

    /**
     * 
     * @param make
     * @param model
     * @param year
     * @return
     */
    public AFTicketInf createTicket(String make, String model, String year);

    /**
     * 
     * @param carEntity
     * @return
     */
    public AFTicketInf createTicket(AFCarEntity carEntity);

    /**
     *
     * @param ticketNum
     * @param cusId
     */
    public void updateTicketCus( Integer ticketNum, int cusId );

    /**
     *
     * @param ticketNum
     * @param carId
     */
    public void updateTicketCar( Integer ticketNum, int carId );

    /**
     * 
     * @param ticketNum
     * @param estId
     */
    public void updateTicketEst( Integer ticketNum, int estId );

    /**
     * 
     * @param ticketNum
     * @return
     */
    public AFTicketInf getTicket(Integer ticketNum );


    /**
     * 
     * @param aftickeBox
     */
    public void setTicketBox( AFTicketBox aftickeBox );
    /**
     * 
     * @return
     */
    public AFCustomerEntity createCustomer();

    public void addCustomer(AFCustomerEntity newCustomner );
    /**
     * 
     * @param cusID
     * @return
     */
    public AFCustomer getCustomer( Integer cusID);

    /**
     *
     * @param inf
     * @param typeToRegister
     * @return
     */
    public ManagerType registerMgrForType(AFMgrInf inf, ManagerType typeToRegister);

    /**
     *
     * @param targetMgr
     * @return
     */
    public AFMgrInf getManagerForType( ManagerType targetMgr);


    /**
     *
     */
    public void loadOutStandingTickets();

    public int getNextCustomerId();
}
