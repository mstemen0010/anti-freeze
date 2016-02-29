/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.antifreeze.entity.customer;

import com.wms.antifreeze.entity.*;
import com.wms.antifreeze.entity.ticket.AFTicketEntity;
import com.wms.antifreeze.AFMgr;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

/**
 *
 * @author Matt
 */
//@Entity
//@Table(name = "cus", catalog = "AntiFrieze", schema = "")
//@NamedQueries({@NamedQuery(name = "AFCustomer.findAll", query = "SELECT a FROM AFCustomer a"), @NamedQuery(name = "AFCustomer.findByCusID", query = "SELECT a FROM AFCustomer a WHERE a.cusID = :cusID"), @NamedQuery(name = "AFCustomer.findByCusLastName", query = "SELECT a FROM AFCustomer a WHERE a.cusLastName = :cusLastName"), @NamedQuery(name = "AFCustomer.findByCusFirstName", query = "SELECT a FROM AFCustomer a WHERE a.cusFirstName = :cusFirstName"), @NamedQuery(name = "AFCustomer.findByCusMI", query = "SELECT a FROM AFCustomer a WHERE a.cusMI = :cusMI"), @NamedQuery(name = "AFCustomer.findByCusOldName", query = "SELECT a FROM AFCustomer a WHERE a.cusOldName = :cusOldName"), @NamedQuery(name = "AFCustomer.findByCusStreet1", query = "SELECT a FROM AFCustomer a WHERE a.cusStreet1 = :cusStreet1"), @NamedQuery(name = "AFCustomer.findByCusStreet2", query = "SELECT a FROM AFCustomer a WHERE a.cusStreet2 = :cusStreet2"), @NamedQuery(name = "AFCustomer.findByCusCity", query = "SELECT a FROM AFCustomer a WHERE a.cusCity = :cusCity"), @NamedQuery(name = "AFCustomer.findByCusState", query = "SELECT a FROM AFCustomer a WHERE a.cusState = :cusState"), @NamedQuery(name = "AFCustomer.findByCusZip", query = "SELECT a FROM AFCustomer a WHERE a.cusZip = :cusZip"), @NamedQuery(name = "AFCustomer.findByCusHome", query = "SELECT a FROM AFCustomer a WHERE a.cusHome = :cusHome"), @NamedQuery(name = "AFCustomer.findByCusWork", query = "SELECT a FROM AFCustomer a WHERE a.cusWork = :cusWork"), @NamedQuery(name = "AFCustomer.findByCusCell", query = "SELECT a FROM AFCustomer a WHERE a.cusCell = :cusCell"), @NamedQuery(name = "AFCustomer.findByCusFax", query = "SELECT a FROM AFCustomer a WHERE a.cusFax = :cusFax"), @NamedQuery(name = "AFCustomer.findByCusLastVisit", query = "SELECT a FROM AFCustomer a WHERE a.cusLastVisit = :cusLastVisit"), @NamedQuery(name = "AFCustomer.findByAECusId", query = "SELECT a FROM AFCustomer a WHERE a.aECusId = :aECusId"), @NamedQuery(name = "AFCustomer.findByMail", query = "SELECT a FROM AFCustomer a WHERE a.mail = :mail"), @NamedQuery(name = "AFCustomer.findByRemind", query = "SELECT a FROM AFCustomer a WHERE a.remind = :remind"), @NamedQuery(name = "AFCustomer.findByARNum", query = "SELECT a FROM AFCustomer a WHERE a.aRNum = :aRNum"), @NamedQuery(name = "AFCustomer.findByARStatus", query = "SELECT a FROM AFCustomer a WHERE a.aRStatus = :aRStatus"), @NamedQuery(name = "AFCustomer.findByARNow", query = "SELECT a FROM AFCustomer a WHERE a.aRNow = :aRNow"), @NamedQuery(name = "AFCustomer.findByAREver", query = "SELECT a FROM AFCustomer a WHERE a.aREver = :aREver"), @NamedQuery(name = "AFCustomer.findByARDate", query = "SELECT a FROM AFCustomer a WHERE a.aRDate = :aRDate"), @NamedQuery(name = "AFCustomer.findByBalanceDue", query = "SELECT a FROM AFCustomer a WHERE a.balanceDue = :balanceDue"), @NamedQuery(name = "AFCustomer.findByPreviousDue", query = "SELECT a FROM AFCustomer a WHERE a.previousDue = :previousDue"), @NamedQuery(name = "AFCustomer.findByAr30", query = "SELECT a FROM AFCustomer a WHERE a.ar30 = :ar30"), @NamedQuery(name = "AFCustomer.findByAr60", query = "SELECT a FROM AFCustomer a WHERE a.ar60 = :ar60"), @NamedQuery(name = "AFCustomer.findByAr90", query = "SELECT a FROM AFCustomer a WHERE a.ar90 = :ar90"), @NamedQuery(name = "AFCustomer.findByAr120", query = "SELECT a FROM AFCustomer a WHERE a.ar120 = :ar120"), @NamedQuery(name = "AFCustomer.findByContact", query = "SELECT a FROM AFCustomer a WHERE a.contact = :contact"), @NamedQuery(name = "AFCustomer.findByNonTaxable", query = "SELECT a FROM AFCustomer a WHERE a.nonTaxable = :nonTaxable"), @NamedQuery(name = "AFCustomer.findByLimit", query = "SELECT a FROM AFCustomer a WHERE a.limit = :limit"), @NamedQuery(name = "AFCustomer.findByCompany", query = "SELECT a FROM AFCustomer a WHERE a.company = :company"), @NamedQuery(name = "AFCustomer.findByMgrID", query = "SELECT a FROM AFCustomer a WHERE a.mgrID = :mgrID")})
public class AFCustomerEntity implements Serializable,AFEntityInterface {


    String fields = AFEntityInterface.AFField.getCusFields();
    String insertPattern = null;


    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "CusID", nullable = false)
    private Integer cusID;
//    @Column(name = "CusLastName", length = 50)
    private String cusLastName;
//    @Column(name = "CusFirstName", length = 20)
    private String cusFirstName;
//    @Column(name = "CusMI", length = 3)
    private String cusMI;
//    @Column(name = "CusOldName", length = 20)
    private String cusOldName;
//    @Column(name = "CusStreet1", length = 50)
    private String cusStreet1;
//    @Column(name = "CusStreet2", length = 50)
    private String cusStreet2;
//    @Column(name = "CusCity", length = 30)
    private String cusCity;
//    @Column(name = "CusState", length = 20)
    private String cusState;
//    @Column(name = "CusZip", length = 20)
    private String cusZip;
//    @Column(name = "CusHome", length = 30)
    private String cusHome;
//    @Column(name = "CusWork", length = 30)
    private String cusWork;
//    @Column(name = "CusCell", length = 30)
    private String cusCell;
//    @Column(name = "CusFax", length = 30)
    private String cusFax;
//    @Lob
//    @Column(name = "CusNotes", length = 2147483647)
    private String cusNotes;
//    @Column(name = "CusLastVisit")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date cusLastVisit;
//    @Column(name = "AE_CusId")
    private Short aECusId = 0;
//    @Basic(optional = false)
//    @Column(name = "Mail", nullable = false)
    private Short mail = 0;
//    @Column(name = "Remind")
    private Short remind = 0;
//    @Column(name = "ARNum", length = 25)
    private String aRNum;
//    @Column(name = "ARStatus", length = 25)
    private String aRStatus;
//    @Basic(optional = false)
//    @Column(name = "ARNow", nullable = false)
    private Short aRNow = 0;
//    @Basic(optional = false)
//    @Column(name = "AREver", nullable = false)
    private Short aREver = 0;
//    @Column(name = "ARDate")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date aRDate;
//    @Column(name = "BalanceDue", precision = 19, scale = 4)
    private BigDecimal balanceDue;
//    @Column(name = "PreviousDue", precision = 19, scale = 4)
    private BigDecimal previousDue;
//    @Column(name = "AR30", precision = 19, scale = 4)
    private BigDecimal ar30;
//    @Column(name = "AR60", precision = 19, scale = 4)
    private BigDecimal ar60;
//    @Column(name = "AR90", precision = 19, scale = 4)
    private BigDecimal ar90;
//    @Column(name = "AR120", precision = 19, scale = 4)
    private BigDecimal ar120;
//    @Column(name = "Contact", length = 20)
    private String contact;
//    @Basic(optional = false)
//    @Column(name = "NonTaxable", nullable = false)
    private Short nonTaxable = 0;
//    @Column(name = "CusLimit", precision = 19, scale = 4)
    private BigDecimal CusLimit;
//    @Basic(optional = false)
//    @Column(name = "Company", nullable = false)
    private Short company = 0;
//    @Column(name = "MgrID")
    private Integer mgrID;
//    @OneToMany(mappedBy = "cusId")
    private Collection<AFTicketEntity> aFBaseTicketCollection;
    // stuff for the JDBTK
    private static String assocTableName = "cus";
    //private static String tempTableName = "tickettemp";
    private static VTable customerTable;
    private static AFMgr afMgr;

    private HashMap<String,String> AFNamesValues = new HashMap<String,String>();

    private final static int defaultCus = 222;
    private final static int defaultCar = 250;
    private final static int defaultEst = 770;

    static {
        afMgr = AFMgr.callMgr();
        customerTable = afMgr.getVTable(assocTableName);
    }

    public AFCustomerEntity() {
        this.insertPattern = customerTable.getInsertStatement();
//        System.out.println("Insert statement is: " + insertPattern);
    }

    public AFCustomerEntity(VRecord vRec)
    {
       this.insertPattern = customerTable.getInsertStatement();
//        System.out.println("Insert statement is: " + insertPattern);
        this.syncToVRecord(vRec);
    }

    public AFCustomerEntity(Integer cusID) {
       this.insertPattern = customerTable.getInsertStatement();
//        System.out.println("Insert statement is: " + insertPattern);
        this.cusID = cusID;
    }

    public AFCustomerEntity(Integer cusID, Short mail, Short aRNow, Short aREver, Short nonTaxable, Short company) {
       this.insertPattern = customerTable.getInsertStatement();
//        System.out.println("Insert statement is: " + insertPattern);
        this.cusID = cusID;
        this.mail = mail;
        this.aRNow = aRNow;
        this.aREver = aREver;
        this.nonTaxable = nonTaxable;
        this.company = company;
    }

    public String valueForKey(String key) {
        return this.AFNamesValues.get(key);
    }

    public Iterator<String> getEntityKeys()
    {
        if( AFNamesValues == null || AFNamesValues.isEmpty())
            return null;

        return AFNamesValues.keySet().iterator();
    }

    public VRecord getVRecord() {

        String createTime = afMgr.getCurrentDate(null);
        String blank  = "";
        VRecord retRec = customerTable.getEmptyRecord();        
        retRec.setValue(AFEntityInterface.AFField.CusLastName.toString(), this.cusLastName); // 0
        retRec.setValue(AFEntityInterface.AFField.CusFirstName.toString(), this.cusFirstName);
        retRec.setValue(AFEntityInterface.AFField.CusMI.toString(), blank);
        retRec.setValue(AFEntityInterface.AFField.CusOldName.toString(), blank );
        retRec.setValue(AFEntityInterface.AFField.CusStreet1.toString(), this.cusStreet1);
        retRec.setValue(AFEntityInterface.AFField.CusStreet2.toString(), this.cusStreet2); // 5
        retRec.setValue( AFEntityInterface.AFField.CusCity.toString(), this.cusCity);
        retRec.setValue(AFEntityInterface.AFField.CusState.toString(), this.cusState );
        retRec.setValue(AFEntityInterface.AFField.CusZip.toString(), this.cusZip );
        retRec.setValue(AFEntityInterface.AFField.CusHome.toString(), this.cusHome );
        retRec.setValue(AFEntityInterface.AFField.CusWork.toString(), this.cusWork ); // 10
        retRec.setValue(AFEntityInterface.AFField.CusCell.toString(), this.cusCell );
        retRec.setValue(AFEntityInterface.AFField.CusFax.toString(), this.cusFax );
        retRec.setValue(AFEntityInterface.AFField.CusNotes.toString(), this.cusNotes );
        retRec.setValue(AFEntityInterface.AFField.CusLastVisit.toString(), createTime );
        retRec.setValue(AFEntityInterface.AFField.AE_CusId.toString(), String.valueOf(this.aECusId ));  // 15
        retRec.setValue(AFEntityInterface.AFField.Mail.toString(), "0");
        retRec.setValue(AFEntityInterface.AFField.Remind.toString(), String.valueOf(this.remind) );
        retRec.setValue(AFEntityInterface.AFField.ARNum.toString(), this.aRNum);
        retRec.setValue(AFEntityInterface.AFField.ARStatus.toString(), this.aRStatus);
        retRec.setValue(AFEntityInterface.AFField.ARNow.toString(), String.valueOf((this.aRNow)) );  // 20
        retRec.setValue(AFEntityInterface.AFField.AREver.toString(),String.valueOf((this.aREver)) );

        
        retRec.setValue(AFEntityInterface.AFField.ARDate.toString(), createTime);

        return retRec;
    }

    public void syncToVRecord(VRecord recToSyncTo) {
        Iterator<String> it = recToSyncTo.getColumnNames();
        while( it.hasNext() )
        {
            String colName = it.next();
            if( colName  != null )
            {                
               String value = recToSyncTo.getFieldData(colName);
               if (colName.equals("CusID"))
                   this.setCusId(value);
               else if( colName.equals("CusLastName"))
                   this.cusLastName = value;
               else if( colName.equals("CusFirstName"))
                   this.cusFirstName = value;
               else if( colName.equals("CusStreet1"))
                   this.cusStreet1 = value;
               else if( colName.equals("CusCity"))
                   this.cusCity = value;
               else if( colName.equals("CusState"))
                   this.cusState = value;
               else if( colName.equals("CusHome"))
                   this.cusHome = value;
               this.AFNamesValues.put( colName, value );
            }
        }
    }

   
    static public int getNextTicketNumber()
    {
        int nextKey = -1;
        String currKey = customerTable.getMaxKeyValue();
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

    public Integer getCusID() {
        return Integer.parseInt(this.AFNamesValues.get("CusID"));
        // return cusID;
    }

    public void setCusId( String value )
    {
        if( value == null )
            return;
        Integer i = Integer.parseInt(value);
        if( i != null )
        {
            this.cusID = i;
            this.AFNamesValues.put(AFEntityInterface.AFField.CusID.toString(), value );
        }
    }

    public void setCusID(Integer value) {
        this.cusID = value;
    }

    public String getCusLastName() {
        return AFNamesValues.get("CusLastName");
        // return cusLastName;
    }

    public void setCusLastName(String value) {
        this.cusLastName = value;
        this.AFNamesValues.put(AFEntityInterface.AFField.CusLastName.toString(), value);
    }

    public String getCusFirstName() {
        return AFNamesValues.get("CusFirstName");
        // return cusFirstName;
    }

    public void setCusFirstName(String value) {
        this.cusFirstName = value;
        this.AFNamesValues.put(AFEntityInterface.AFField.CusFirstName.toString(), value);
    }

    public String getCusMI() {
        return cusMI;
    }

    public void setCusMI(String value) {
        this.cusMI = value;
    }

    public String getCusOldName() {
        return cusOldName;
    }

    public void setCusOldName(String value) {
        this.cusOldName = value;
        this.AFNamesValues.put(AFEntityInterface.AFField.CusOldName.toString(), value);
    }

    public String getCusStreet1() {
        return cusStreet1;
    }

    public void setCusStreet1(String value) {
        this.cusStreet1 = value;
        this.AFNamesValues.put(AFEntityInterface.AFField.CusFirstName.toString(), value);
    }

    public String getCusStreet2() {
        return cusStreet2;
    }

    public void setCusStreet2(String value) {
        this.cusStreet2 = value;
    }

    public String getCusCity() {
        return cusCity;
    }

    public void setCusCity(String value) {
        this.cusCity = value;
    }

    public String getCusState() {
        return cusState;
    }

    public void setCusState(String value) {
        this.cusState = value;
    }

    public String getCusZip() {
        return cusZip;
    }

    public void setCusZip(String value) {
        this.cusZip = value;
    }

    public String getCusHome() {
        return cusHome;
    }

    public void setCusHome(String value) {
        this.cusHome = value;
    }

    public String getCusWork() {
        return cusWork;
    }

    public void setCusWork(String value) {
        this.cusWork = value;
    }

    public String getCusCell() {
        return cusCell;
    }

    public void setCusCell(String value) {
        this.cusCell = value;
    }

    public String getCusFax() {
        return cusFax;
    }

    public void setCusFax(String value) {
        this.cusFax = value;
    }

    public String getCusNotes() {
        return cusNotes;
    }

    public void setCusNotes(String value) {
        this.cusNotes = value;
    }

    public Date getCusLastVisit() {
        return cusLastVisit;
    }

    public void setCusLastVisit(Date value) {
        this.cusLastVisit = value;
    }

    public Short getAECusId() {
        return aECusId;
    }

    public void setAECusId(Short value) {
        this.aECusId = value;
    }

    public Short getMail() {
        return mail;
    }

    public void setMail(Short mail) {
        this.mail = mail;
    }

    public Short getRemind() {
        return remind;
    }

    public void setRemind(Short value) {
        this.remind = value;
    }

    public String getARNum() {
        return aRNum;
    }

    public void setARNum(String value) {
        this.aRNum = value;
    }

    public String getARStatus() {
        return aRStatus;
    }

    public void setARStatus(String value) {
        this.aRStatus = value;
    }

    public Short getARNow() {
        return aRNow;
    }

    public void setARNow(Short value) {
        this.aRNow = value;
    }

    public Short getAREver() {
        return aREver;
    }

    public void setAREver(Short value) {
        this.aREver = value;
    }

    public Date getARDate() {
        return aRDate;
    }

    public void setARDate(Date value) {
        this.aRDate = value;
    }

    public BigDecimal getBalanceDue() {
        return balanceDue;
    }

    public void setBalanceDue(BigDecimal value) {
        this.balanceDue = value;
    }

    public BigDecimal getPreviousDue() {
        return previousDue;
    }

    public void setPreviousDue(BigDecimal value) {
        this.previousDue = value;
    }

    public BigDecimal getAr30() {
        return ar30;
    }

    public void setAr30(BigDecimal value) {
        this.ar30 = value;
    }

    public BigDecimal getAr60() {
        return ar60;
    }

    public void setAr60(BigDecimal ar60) {
        this.ar60 = ar60;
    }

    public BigDecimal getAr90() {
        return ar90;
    }

    public void setAr90(BigDecimal ar90) {
        this.ar90 = ar90;
    }

    public BigDecimal getAr120() {
        return ar120;
    }

    public void setAr120(BigDecimal ar120) {
        this.ar120 = ar120;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Short getNonTaxable() {
        return nonTaxable;
    }

    public void setNonTaxable(Short nonTaxable) {
        this.nonTaxable = nonTaxable;
    }

    public BigDecimal getLimit() {
        return CusLimit;
    }

    public void setLimit(BigDecimal limit) {
        this.CusLimit = limit;
    }

    public Short getCompany() {
        return company;
    }

    public void setCompany(Short company) {
        this.company = company;
    }

    public Integer getMgrID() {
        return mgrID;
    }

    public void setMgrID(Integer mgrID) {
        this.mgrID = mgrID;
    }

    public Collection<AFTicketEntity> getAFBaseTicketCollection() {
        return aFBaseTicketCollection;
    }

    public void setAFBaseTicketCollection(Collection<AFTicketEntity> aFBaseTicketCollection) {
        this.aFBaseTicketCollection = aFBaseTicketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusID != null ? cusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AFCustomerEntity)) {
            return false;
        }
        AFCustomerEntity other = (AFCustomerEntity) object;
        if ((this.cusID == null && other.cusID != null) || (this.cusID != null && !this.cusID.equals(other.cusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wms.antifrieze.entity.AFCustomer[cusID=" + cusID + "]";
    }
}
