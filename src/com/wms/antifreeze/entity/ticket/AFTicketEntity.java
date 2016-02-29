/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze.entity.ticket;

import com.wms.antifreeze.AFException;
import com.wms.antifreeze.AFShopWorkflow.TicketStage;
import com.wms.antifreeze.gui.AFTicketBox.TicketType;
import com.wms.antifreeze.AFMgr;
import com.wms.antifreeze.gui.AFViewMgrInterface.AFAction;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Matt
 */
//@Entity
//@Table(name = "ticket", catalog = "AntiFrieze", schema = "")
//@NamedQueries({@NamedQuery(name = "AFBaseTicket.findAll", query = "SELECT a FROM AFBaseTicket a"), @NamedQuery(name = "AFBaseTicket.findByTicketNum", query = "SELECT a FROM AFBaseTicket a WHERE a.ticketNum = :ticketNum")})
public class AFTicketEntity implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "ticketNum", nullable = false)
    private Integer ticketNum;
//    @JoinColumn(name = "estId", referencedColumnName = "EstNum")
//    @ManyToOne
    private String estId;
//    @JoinColumn(name = "carId", referencedColumnName = "CarId")
//    @ManyToOne
    private String carId;
//    @JoinColumn(name = "cusId", referencedColumnName = "CusID")
//    @ManyToOne
    private String cusId;

    private Integer reservedTicketNum;

    private static String assocTableName = "ticket";
    private static String tempTableName = "tickettemp";
    private static VTable ticketTable;
    private static VTable ticketTempTable;
    private VRecord entityRec = null;
    private static AFMgr afMgr;
    private String carcomment = null;
    private Timestamp ticketDate = null;

    private final static String defaultCus = "222";
    private final static String defaultCar = "250";
    private final static String defaultEst = "770";

    private TicketStage thisTicketStage = TicketStage.Initialized;
    private TicketType thisTicketType = TicketType.Initialized;

    static
    {
        afMgr = AFMgr.callMgr();
        ticketTable = afMgr.getVTable(assocTableName);
        ticketTempTable = afMgr.getVTable(tempTableName);
    }

    public AFTicketEntity()
    {

    }

    public AFTicketEntity(Integer ticketNum) {
        this.ticketNum = ticketNum;
        this.entityRec = ticketTable.getRecord(String.valueOf(ticketNum.intValue()));
        this.ticketDate = new Timestamp( afMgr.getCurrentTime());
    }

    public AFTicketEntity(AFMgr afMgr) {

        //assoc the Database table for this entity
        ticketTable = afMgr.getVTable(assocTableName);
        this.afMgr = afMgr;
        this.ticketDate = new Timestamp( afMgr.getCurrentTime());
    }

    private int getNextTempTicketNumber()
    {
        int nextKey = -1;
        String currKey = ticketTempTable.getMaxKeyValue();
        if( currKey != null && currKey.isEmpty())
        {
            nextKey = 1;
        }
        else
        {
            nextKey = Integer.parseInt(currKey) + 1;
        }
        return nextKey;
    }


    private int getNextTicketNumber()
    {
        int nextKey = -1;
        String currKey = ticketTable.getMaxKeyValue();
        if( currKey != null && currKey.isEmpty())
        {
            nextKey = 1;
        }
        else
        {
            nextKey = Integer.parseInt(currKey) + 1;
        }
        return nextKey;
    }



    public int getTicketNumber()
    {
        return ticketNum;
    }


    public void finalizeNewTicket()
    {
        if( ticketTable != null )
        {
            ticketTable.commit();
        }
    }

    public String getCarComment()
    {
        String retStr = this.carcomment;
        if( retStr == null )
        {
            retStr = this.entityRec.getFieldData("carcomment");
        }
        return retStr;
    }


    public String getAFCarId()
    {
        carId = this.entityRec.getFieldData("carId");
        return carId;
    }

    public void processTicketForward()
    {
        boolean updateDatabase = false;
        switch( thisTicketStage )
        {
            case Initialized:
                thisTicketStage = TicketStage.Open;
                updateDatabase = false;
                break;

            case Open:
                thisTicketStage = TicketStage.Reserved;
                ticketNum = reservedTicketNum;
                updateDatabase = true;
                break;
            case Reserved:
                thisTicketStage = TicketStage.Estimate;
                updateDatabase = true;
                break;

        }
        if( updateDatabase)
        {
            VRecord ticketRec = ticketTable.getRecord(String.valueOf(ticketNum.intValue()));
            if( ticketRec != null )
            {
                int colNum = ticketTable.getColumnNumberForName("stage");
                ticketRec.updateField(colNum, String.valueOf(thisTicketStage.ordinal()));                
                ticketTable.updateRecord(String.valueOf(ticketNum.intValue()), true);
            }
        }
    }

    private int createTempRecord()
    {
        reservedTicketNum = reserveTicketRecord();

        int newRecKey = -1;
        if( ticketTempTable != null && reservedTicketNum > 0)
        {
            String pattern = "INSERT INTO tickettemp (TempTicketNum, FinalTicketNum, ReservedTicketNum, comment) VALUES (%d,%d,%d,%s)";
            VRecord newTempRecord = ticketTempTable.getEmptyRecord();
            String r_comment = "ticket number: (" + reservedTicketNum + ") was reserved at: " + afMgr.getCurrentDate(null);
            if( newTempRecord != null )
            {
                newRecKey = getNextTempTicketNumber();
                newTempRecord.setValue("TempTicketNum", String.valueOf(newRecKey));
                newTempRecord.setValue("FinalTicketNum", "1");
                newTempRecord.setValue("ReservedTicketNum", String.valueOf(reservedTicketNum));
                newTempRecord.setValue("comment", r_comment);
                int numRowsInserted = ticketTempTable.insertRecord(newTempRecord, pattern);
                if( numRowsInserted > 0)
                    System.out.println("createTempRecord::Inserted: (" + numRowsInserted + ") rows into Table tickettemp");
            }
        }
        processTicketForward();

        return newRecKey;
    }

    // there are 3 "stub" records in the current database that are there to allow a tempory ticket to be created.
   // the cusId = 222, the carId = 250, and the estimateId = 770
    private int reserveTicketRecord()
    {
        String pattern = "INSERT INTO ticket (ticketNum,carId,cusId,estId,comment,carcomment,stage,type) VALUES (%d,%d,%d,%d,%s,%s,%d,%d)";
        String testPattern = ticketTable.getInsertStatement();
        String r_cusId = defaultCus;
        String r_carId = defaultCar;
        String r_estId = defaultEst;
        int reservedRecKey = getNextTicketNumber();
        String r_comment = "ticket number: (" + reservedRecKey + ") was reserved at: " + afMgr.getCurrentDate(null);

        int recNumReserved = -1;

        VRecord reservedRec = ticketTable.getEmptyRecord();

        if( reservedRec != null )
        {            
            reservedRec.setValue("ticketNum", String.valueOf(reservedRecKey));
            reservedRec.setValue("carId", r_carId);
            reservedRec.setValue("cusId", r_cusId);
            reservedRec.setValue("estId", r_estId);
            reservedRec.setValue("comment", r_comment);
            reservedRec.setValue("carcomment", carcomment);
            reservedRec.setValue("stage", String.valueOf(thisTicketStage.ordinal()));
            reservedRec.setValue("type", String.valueOf(thisTicketType.ordinal()));
            reservedRec.setValue("ticketDate", ticketDate.toString());

            int numRowsInserted = ticketTable.insertRecord(reservedRec, pattern);
            // ticketTable.sync();
            System.out.println("Inserted: (" + numRowsInserted + ") rows into Table ticket");
        }
        this.entityRec = reservedRec;
        return reservedRecKey;
    }
    public void createNewTicket(String make, String model, String year)
    {
        // set the ticket creaation date
        this.ticketDate = new Timestamp( afMgr.getCurrentTime() );
        // save off the car comment
        StringBuilder sb = new StringBuilder( make.trim() ).append("/").append(model.trim()).append("/").append(year.trim());
        carcomment = sb.toString();
        // first make sure the place holder record in ticket table is there
        checkPlaceHolder();
        // we will really create a tempory record first, at least until the: estimate, car, and customer are finalized
        if(ticketTable != null )
        {
            // create a tempory record to "hold our place" in the ticket table
            int tempRecKey = createTempRecord();
            if( tempRecKey > 0 )
            {
                System.out.println("A New Ticket has been started");
                processTicketForward();
            }
            else
            {
                System.out.println("Unable to Start new Record at this time...");
            }
        }
    }

    private void checkPlaceHolder()
    {
        if( ticketTable != null && ticketTable.getRowCount() > 0 )
            return;

        // the following record is used as a place holder for the sake of some foreign keys
        String pattern = "INSERT INTO ticket (ticketNum, carId, cusId, estId,comment,carcomment,stage,type) VALUES (%d,%d,%d,%d,%s,%s,%d,%d)";
        String r_ticketNum = "1";
        String r_cusId = defaultCus;
        String r_carId = defaultCar;
        String r_estId = defaultEst;
        String r_comment = "Place holder do not remove ";

        int recNumReserved = -1;

        VRecord reservedRec = ticketTable.getEmptyRecord();

        if( reservedRec != null )
        {
            int PHRecKey = getNextTicketNumber();
            reservedRec.setValue("ticketNum", r_ticketNum );
            reservedRec.setValue("carId", r_carId);
            reservedRec.setValue("cusId", r_cusId);
            reservedRec.setValue("estId", r_estId);
            reservedRec.setValue("comment", r_comment);
            reservedRec.setValue("carcomment", "N/A");
            reservedRec.setValue("stage", String.valueOf(this.thisTicketStage.ordinal()));
            reservedRec.setValue("type", String.valueOf(this.thisTicketType.ordinal()));
            reservedRec.setValue("ticketDate", ticketDate.toString());

            int numRowsInserted = ticketTable.insertRecord(reservedRec, pattern);
            // ticketTable.sync();
            System.out.println("Inserted: (" + numRowsInserted + ") rows into Table ticket");
        }
        this.entityRec = reservedRec;
    }


    public Integer getTicketNum() {
        return ticketNum;
    }

//    public void setTicketNum(Integer ticketNum) {
//        this.ticketNum = ticketNum;
//    }

    public Date getTicketDate()
    {
        return new Date(ticketDate.getTime());
    }


    public String getEstId() {
        return estId;
    }

    public void setEstId(String estId) {
        this.estId = estId;
    }

    public String getCarId() {
        return carId;
    }

    public void updateCusId( int cusId )
    {
        if( entityRec != null )
        {
            int fieldNum = entityRec.getColumnNumberByName("cusId");               
            String value = String.valueOf(cusId);
            entityRec.updateField(fieldNum, value );
            // entityRec.setValue("cusId", String.valueOf(cusId) );
        }
        // updateRecord();
    }

    public void updateCarId(int carId )
    {
        if( entityRec != null )
        {
            int fieldNum = entityRec.getColumnNumberByName("carId");
            String value = String.valueOf(carId);
            entityRec.updateField(fieldNum, value );
        }
    }

    public void updateEstId(int estId )
    {
        if( entityRec != null )
        {
            int fieldNum = entityRec.getColumnNumberByName("estId");
            entityRec.setValue("estId", String.valueOf(estId) );
        }
        updateRecord();
    }

    private void updateRecord()
    {
        if( entityRec != null )
        {

            String key = entityRec.getRowKey();
            ticketTable.updateRecord(key, true);
        }
    }

//    public void setCarId(AFCarEntity carId) {
//        this.carId = carId;
//    }

    public String getCusId() {
        return cusId;
    }

//    public void setCusId(AFCustomeEntity cusId) {
//        this.cusId = cusId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketNum != null ? ticketNum.hashCode() : 0);
        return hash;
    }

    boolean hasCustomer() throws AFException
    {
        if( entityRec == null )
            throw new AFException( "Null record found for ticket: " + this.getTicketNum());
        String curId = entityRec.getFieldData("cusId");
        return curId != null && ! curId.equals(defaultCus);
    }

    boolean hasCar() throws AFException
    {
        if( entityRec == null )
            throw new AFException( "Null record found for ticket: " + this.getTicketNum());
        String curId = entityRec.getFieldData("carId");
        return curId != null && ! curId.equals(defaultCar);
    }

    boolean hasEst() throws AFException
    {
        if( entityRec == null )
            throw new AFException( "Null record found for ticket: " + this.getTicketNum());
        String curId = entityRec.getFieldData("estId");
        return curId != null && ! curId.equals(defaultEst);

    }

    public AFAction getAction()
    {
        AFAction retAction = AFAction.AFActionUnknown;
        try {
            if (hasEst() && hasCar() && hasCustomer()) {
                retAction = AFAction.AFUpdate;
            }
            else if( ! hasEst() || ! hasCar() || ! hasCustomer() )
            {
                retAction = AFAction.AFCreate;
            }
        } catch (AFException ex) {
            Logger.getLogger(AFTicketEntity.class.getName()).log(Level.SEVERE, null, ex);

        }

        return retAction;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AFTicketEntity)) {
            return false;
        }
        AFTicketEntity other = (AFTicketEntity) object;
        if ((this.ticketNum == null && other.ticketNum != null) || (this.ticketNum != null && !this.ticketNum.equals(other.ticketNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wms.antifrieze.entity.AFBaseTicket[ticketNum=" + ticketNum + "]";
    }
}
