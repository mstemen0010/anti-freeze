/*/
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.antifreeze;

import com.wms.antifreeze.entity.customer.AFCustomer;
import com.wms.antifreeze.entity.customer.AFCustomerEntity;
import com.wms.antifreeze.entity.ticket.AFTicketInf;
import com.wms.antifreeze.AFShopWorkflow.TicketStage;
import com.wms.antifreeze.entity.car.AFCarEntity;
import com.wms.antifreeze.entity.AFShopEntity;
import com.wms.antifreeze.gui.AFTicketBox;
import com.wms.antifreeze.gui.AFViewMgrInterface;
import com.wms.antifreeze.gui.AF_ViewMgr;
import com.wms.jdbtk3.DBToolkit2;
import com.wms.jdbtk3.DBUrl;
import com.wms.jdbtk3.DBUser;
import com.wms.jdbtk3.VDBM;
import com.wms.jdbtk3.VDatabase;
import com.wms.jdbtk3.VDriver.DriverClass;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import com.wms.util.WMSLog;
import com.wms.util.gui.GUILogInterface;
import com.wms.util.gui.GUILogInterface.GLogLevel;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author mstemen
 */
public class AFMgr implements AFMgrInf, AFSearchInterface {

    private DBUrl dbUrl = null;
    private VDatabase afDb = null;
    // mysql = "jdbc:mysql://localhost/AntiFreeze"
    // derby = "jdbc:derby://localhost:1527/AntiFreeze"
    private final String dburl = "jdbc:mysql://localhost:3306/AntiFrieze";
    private DBUser dbUser = null;
    private VDBM vdbm = null;
    private WMSLog afMgrLog = null;
    private DBToolkit2 dbtk = null;
    private GUILogInterface log;
    private final DriverClass afDriverClass = DriverClass.MySql;
    private static AFMgr instance = null;
    private AF_ViewMgr afViewMgr;
    private AFShopEntity afShopInfo = null;
    private boolean ticketStarted = false;
    private ManagerType mgrKey;
    private static boolean fullyInit = false;
    private AFShopWorkflow shopWorkFlow;
    private final HashMap<ManagerType, AFMgrInf> registeredMgrs = new HashMap<ManagerType, AFMgrInf>();
    private AFMgrInf ticketMgr = null;
    private AFMgrInf customerMgr = null;
    private final AFMgrInf carMgr = null;
    private AFSearchInterface.AFSearchType afSearchType = AFSearchType.Unknown;
    private AFCustomerSearchField afCustomerSearchField = AFCustomerSearchField.CusLastName;
    private String AFSearchStr = "";
    private AFTicketInf afTicketWizard;

    private int currentCustomerId;
    private int currestCarId;
    private int currentEstId;

    /**
     *
     * @return
     */
    public static AFMgr callMgr() {
        if (fullyInit) {
            return instance; // this will really be the "this" pointer
        }
        if (instance == null) {
                fullyInit = true;
                instance = new AFMgr();
        }
        else
        {

            
        }
        return instance;
    }

    private AFMgr() {
 
    }

    public AFViewMgrInterface getViewMr() {
        return this.afViewMgr.getInterface();
    }

    public void initAntiFreezeMgr(GUILogInterface log, AF_ViewMgr afViewMgr) throws MalformedURLException, SQLException {
        mgrKey = this.registerMgrForType(this, ManagerType.Master);
        this.afViewMgr = afViewMgr;
////        serverName.setText(serverNameStr);
////        serverLocation.setText(serverLocationStr);
////        // serverName.setText( "dulcet" );
////        // serverLocation.setText( "jdbc:mysql://dulcet/vts" );
////        viewPanel.removeAll();
////        //	   UDTMgr.loadProperties();
////
////        serverName.setText(dbHostName);
////        // static String serverLocationStr = "jdbc:mysql://localhost/vts";
////        StringBuilder dbHostLocation = new StringBuilder("jdbc:mysql://");
////        serverNameStr = dbHostName;
////        dbHostLocation.append(dbHostName).append( "/").append( config.getProperty("SCPMgr.databasePref.dbName", "vts") );
////        serverLocationStr = dbHostLocation.toString();
////        serverLocation.setText(dbHostLocation.toString());
////        try {
////            dbUrl = new DBUrl(serverLocation.getText());
////        } catch(java.net.MalformedURLException e) {
////            log.write("DbMgr::DbMgr:  " + e.getMessage());
////        }
////        log.setLogLevel(config.getProperty("SCPMgr.guiPref.logLevel"), 0, 0);
////        debugLevel.setSelectedIndex(log.getLogLevel());
////        // DBToolkit dbtk = DBToolkit.getInstance();
////
////        setSplashStatus("Initializing Database Layer");
////        setSplashStatusValue( 20 );
////
////        DBToolkit2 dbtk = DBToolkit2.getInstance();
////        dbtk.setLog(log);
////        dbtk.setAutoBuild(true);
////        user = new DBUser( null );
////        user.setDbName(dbUrl.getDbName());
////        user.setDbUrl(dbUrl.getUrl());
////        user.setName(config.getProperty("SCPMgr.databasePref.dbUser"));
////        // user.setName( "root" );
////        user.setPasswd(config.getProperty("SCPMgr.databasePref.dbUserPass"));
////        // user.setName( "Parallax" );
////        // user.setPasswd( "" );
////        user.setServerName(dbUrl.getHost());
////        vdbm = dbtk.getDbm(user,log);
////        vdb = vdbm.createDatabase( dbUrl.getDbName(), dbUrl.getUrl());
////        vdb.suspendUpdates(true);
////        user.setVDB(vdb);
////        vdb.setDbUser( user );
////        // dbUrl = new DBUrl();
////
////
////
////
////        // vdbm = dbtk.getDbm( );
////        dbtk.setVDBM(vdbm);
////
////        vdb.setDbUser(use////
////        // vdb.build();
////        // loadTables();
////
////        setSplashStatus("Initializing Manager");
////        setSplashStatusValue( 30 );
////
////        ParallaxMgr = new SCPMgr(this,log,vdb);
        //derby driver: org.apache.derby.jdbc.ClientDriver
        this.log = log;
        // if the this manager is truely called by the instance of AF_ViewMgr--not the static (null, null) call, then we
        // want to initalize all the under managers

        // create a log and the virtual db elements
        this.afViewMgr.setSplashStatus("Connecting to AF Database");
        this.afViewMgr.setSplashStatusNote("Setting DB Logs");
        afMgrLog = new WMSLog("/tmp", "afMgr.log");
        afDb = new VDatabase(log, DriverClass.MySql);
        dbUrl = new DBUrl(dburl);

        dbUser = new DBUser(afDb);
        dbUser.setDbName(dbUrl.getDbName());
        dbUser.setDbUrl(dbUrl.getUrl());
        dbUser.setName("AFUser");
        dbUser.setPasswd("AFUser");
        dbUser.setServerName(dbUrl.getHost());
        dbtk = DBToolkit2.getInstance();
        dbtk.setAutoBuild(true);
        vdbm = dbtk.getDbm(dbUser, log);
        // vdbm = dbtk.getDbm( );
        dbtk.setVDBM(vdbm);
        // TODO: Set the Driver Class
        this.afViewMgr.setSplashStatusNote("Setting up AF Virtural DB Interface");
        afDb = vdbm.createDatabase(dbUrl.getDbName(), dbUrl.getUrl(), afDriverClass);

        afDb.setDbUser(dbUser);
        this.afViewMgr.setSplashStatus("Reading in Tables...");
        loadTables();
        Vector tableNames = afDb.getTableNames();
        this.afViewMgr.setSplashStatusNote("Done");

        if (tableNames != null) {
            if (log != null) {
                log.writeToGui("Found " + tableNames.size() + " Tables", GLogLevel.Trace);
            }
        }
        this.afViewMgr.setSplashStatus("Done! Reading in Tables...");
        instance = this;
        afShopInfo = new AFShopEntity(afDb);
        afShopInfo.map();
        fullyInit = false;

        // step up a test workflow
        /**
         *     public enum TicketStage
        {
        Initialized,
        Reserved,  // a "virtual ticket"
        Open,
        CheckIn, // Gregg
        KeyIn, // Gregg
        Dispatched,
        Assigned,
        Worked,
        Estimate,
        CusAuth,
        Amended,
        ReAuth,
        CheckOut, // Gregg
        Paid,
        PO,
        Closed;
        }
         */

        this.afViewMgr.setSplashStatus("Setting up Shop work flows");
        shopWorkFlow = new AFShopWorkflow();

        shopWorkFlow.push(TicketStage.Initialized);
        shopWorkFlow.push(TicketStage.Open);
        shopWorkFlow.push(TicketStage.Reserved);
        shopWorkFlow.push(TicketStage.CheckIn);
        shopWorkFlow.push(TicketStage.Dispatched);
        shopWorkFlow.push(TicketStage.Worked);
        shopWorkFlow.push(TicketStage.KeyIn);
        shopWorkFlow.push(TicketStage.CheckOut);
        shopWorkFlow.push(TicketStage.Paid);
        shopWorkFlow.push(TicketStage.Closed);
       if (this.afViewMgr != null && this.log != null) {
           this.afViewMgr.setSplashStatus("Setting up AF Sub Managers");
           this.afViewMgr.setSplashStatusValue(70);
            intializeAFSubManagers(log);
            fullyInit = true;
        }
    }

    public void setTicketWiardInf( AFTicketInf inf )
    {
        afTicketWizard = inf;
    }

    private void intializeAFSubManagers(GUILogInterface log) {
        this.afViewMgr.setSplashStatusNote("Setting up Ticket Mgr");
        ticketMgr = new AFTicketMgr(afTicketWizard, log);
        this.afViewMgr.setSplashStatusNote("Setting up Customer Mgr");
        customerMgr = new AFCustomerMgr(log);
        // this.afViewMgr.setSplashStatusNote("Setting up Ticket Mgr");
        this.ticketMgr = this.getManagerForType(ManagerType.Ticket);
        this.customerMgr = this.getManagerForType(ManagerType.Customer);
    }

    public void addWorkFlowStep(TicketStage flowStage) {
        this.shopWorkFlow.push(flowStage);
    }

    public TicketStage processFlowForwad(TicketStage fromStage) {
        TicketStage nextStage = TicketStage.Initialized;
        boolean targetIsNextStage = false;
        Iterator<TicketStage> flow = this.shopWorkFlow.iterator();
        while (flow.hasNext()) {
            TicketStage stagePeek = flow.next();
            if (stagePeek.equals(fromStage) && !targetIsNextStage) {
                targetIsNextStage = true;
                continue;
            } else if (targetIsNextStage) {
                nextStage = stagePeek;
            }
        }
        return nextStage;
    }

    public Long getCurrentTime() {
        Calendar afCalender = java.util.GregorianCalendar.getInstance();
        return afCalender.getTimeInMillis();
    }

    public String getCurrentDate(SimpleDateFormat dateFormatter) {
        Date current = null;
        String retDate = null;

        Calendar afCalender = java.util.GregorianCalendar.getInstance();
        current = afCalender.getTime();
        if (dateFormatter != null) {
            retDate = dateFormatter.format(current);
        } else {
            retDate = current.toString();
        }


        return retDate;
    }

    public Iterator<String> getYears() {
        Vector<String> years = new Vector<String>();
        VTable yearsTable = afDb.getTable("year");
        years = yearsTable.getValues("Year");

        return years.iterator();
    }

    public VTable getVTable(String tableName) {
        return afDb.getTable(tableName);
    }

    public String getProductName() {
        return "Anti-Freeze";
    }

    public Iterator<String> getModelForYearAndMake(String year, String make) {
        if (year == null || make == null) {
            return null;
        }

        VTable carsTable = afDb.getTable("car");

        Vector<String> matchedCars = new Vector<String>();
        Vector<VRecord> matchedRows = carsTable.getRecords("ModYear", year);
        if (matchedRows != null && matchedRows.size() > 0) {
            Iterator<VRecord> RIt = matchedRows.iterator();
            while (RIt.hasNext()) {
                VRecord vRec = RIt.next();
                String fMake = vRec.getFieldData("Make");
                if (vRec.getFieldData("Make").equals(make)) {
                    String fModel = vRec.getFieldData("Model");
                    matchedCars.add(vRec.getFieldData("Model"));
                }
            }
        } else {
            return null;
        }

        return matchedCars.iterator();
    }

    public void startTicket() {
        ticketStarted = true;
    }

    public void stopTicket() {
        ticketStarted = false;
    }

    public boolean ticketStarted() {
        return ticketStarted;
    }

//    public AFTicketInf createNewTicket(String make, String model, String year)
//    {
//
//        //TODO: need to create a customer and car to go along with a new ticket.
//
////        AFCarEntity newCarEnt = new AFCarEntity(make, model, year);
////        int ticketNum = getNextTicketNumber();
////
////        // create the new row in the ticket table
////        VTable ticketTable = afDb.getTable("ticket");
////        if(ticketTable != null )
////        {
////            String pattern = "INSERT INTO vtapes (Barcode , media_type , idreal_tape , idreal_tape_secondary, StoredData, CacheState) VALUES (%s,%s,%s,%s,%d,%d)";
////            if( ticketTable != null ) {
////                VRecord newTicketRecord = ticketTable.getEmptyRecord();
////            }
////        }
////
////        AFTicketView newTicket = afViewMgr.createNewTicket(this, newCarEnt);
////        AFTicketEntity ticketEntity = new AFTicketEntity(ticketNum);
////
////        return newTicket;
//    }
    public Iterator<String> getMakes() {
        Vector<String> makes = new Vector<String>();
        VTable makeTable = afDb.getTable("make");
        if (makeTable != null) {
            makes = makeTable.getValues("MAKE");
        }

        return makes.iterator();
    }

    public AFShopEntity getShopInfo() {
        return this.afShopInfo;
    }

    private void loadTables() {
        this.afViewMgr.setSplashStatusNote("Starting table load");
        this.afViewMgr.setSplashStatusValue(51);
        afDb.build();
        afDb.construct("ticket");
        this.afViewMgr.setSplashStatusNote("Loading Ticket Table");
        this.afViewMgr.setSplashStatusValue(52);
        afDb.construct("tickettemp");
        this.afViewMgr.setSplashStatusNote("Loading Estimates Table");
        this.afViewMgr.setSplashStatusValue(55);
        afDb.construct("estimate");


        this.afViewMgr.setSplashStatusNote("Loading Customer Cars Table");
        this.afViewMgr.setSplashStatusValue(57);
        afDb.construct("year");
        afDb.construct("make");
        afDb.construct("car");

        this.afViewMgr.setSplashStatusNote("Loading Customers Table");
        this.afViewMgr.setSplashStatusValue(60);
        afDb.construct("cus");
        afDb.construct("af_shopinfo");
    }

    public void displayCustName( String firstName, String lastName )
    {
        this.afTicketWizard.displayCustName(firstName, lastName);
    }

    public AFTicketInf createTicket(String make, String model, String year) {
        throw new UnsupportedOperationException("Wrong Manager Called: requires TicketMgr");
    }

    public AFCustomer createCustomer(String firstName, String lastName) {
        throw new UnsupportedOperationException("Wrong Manager Called: requires customMgr");
    }

    public ManagerType registerMgrForType(AFMgrInf inf, ManagerType typeToRegister) {
        ManagerType newMgrKey = mgrRegister.registerType(typeToRegister);
        // mgrKey will be null when AFMgr registers itself, this is ok as it does not need to register its own interface
        if (mgrKey != null && newMgrKey.compareTo(typeToRegister) == 0) {
            this.registeredMgrs.put(newMgrKey, inf);
        }
        return newMgrKey;
    }

    public AFMgrInf getManagerForType(ManagerType targetMgr) {
        AFMgrInf retInf = null;
        ManagerOp requestedOp = ManagerOp.ReferManager;
        if (requestedOp.isAllowed(mgrKey)) {
            retInf = registeredMgrs.get(targetMgr);
        }

        return retInf;
    }

    public AFTicketInf getTicket(Integer ticketNum) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFTicketInf createTicket(AFCarEntity carEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTicketBox(AFTicketBox aftickeBox) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void loadOutStandingTickets() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFTicketInf findTicketByAny(String searchValue, String delimeter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFTicketInf findTicketByAll(String searchValue, String delimeter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public AFCustomer findCustomerByAny(String searchValue, String delimeter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFCustomer findCustomerByAll(String searchValue, String delimeter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFCustomer getCustomer(Integer cusID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAFSearchType(AFSearchType afType) {
        this.afSearchType = afType;
    }

    public void setCustomerSearchField(AFCustomerSearchField afField) {
        this.afCustomerSearchField = afField;
    }

    public void setTicketSearchField(AFCustomerSearchField afField) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFCustomer findTicketById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFCustomer findCustomerById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AFCustomer findCustomer() {
        AFCustomer custFound = null;
        String searchCol = this.afCustomerSearchField.toString();
        VTable cusTable = this.getVTable("cus");
        VRecord cusRec = cusTable.findRecord(searchCol, this.AFSearchStr);
        if( cusRec != null )
        {
            String firstName = cusRec.getFieldData("CusFirstName");
            String lastName = cusRec.getFieldData("CusLastName");
            String cusId = cusRec.getFieldData("CusID");
            if( cusId != null )
            {
                custFound = this.customerMgr.getCustomer( Integer.parseInt(cusId));
            }
        }
        return custFound;
    }

    public void setSearchString(String srchStr) {
        this.AFSearchStr = srchStr;
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

    public void updateTicketCus(Integer ticketNum, int cusId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketCar(Integer ticketNum, int carId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTicketEst(Integer ticketNum, int estId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the currentCustomerId
     */
    public int getCurrentCustomerId() {
        return currentCustomerId;
    }

    /**
     * @param currentCustomerId the currentCustomerId to set
     */
    public void setCurrentCustomerId(int currentCustomerId) {
        this.currentCustomerId = currentCustomerId;
    }

    /**
     * @return the currestCarId
     */
    public int getCurrestCarId() {
        return currestCarId;
    }

    /**
     * @param currestCarId the currestCarId to set
     */
    public void setCurrestCarId(int currestCarId) {
        this.currestCarId = currestCarId;
    }

    /**
     * @return the currentEstId
     */
    public int getCurrentEstId() {
        return currentEstId;
    }

    /**
     * @param currentEstId the currentEstId to set
     */
    public void setCurrentEstId(int currentEstId) {
        this.currentEstId = currentEstId;
    }
}
