/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze.entity;

import com.wms.antifreeze.AFMgr;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author Matt
 */
//@Entity
//@Table(name = "estimate", catalog = "AntiFrieze", schema = "")
//@NamedQueries({@NamedQuery(name = "AFEstimateEntity.findAll", query = "SELECT a FROM AFEstimateEntity a"), @NamedQuery(name = "AFEstimateEntity.findByEstNum", query = "SELECT a FROM AFEstimateEntity a WHERE a.estNum = :estNum"), @NamedQuery(name = "AFEstimateEntity.findByEstDesc", query = "SELECT a FROM AFEstimateEntity a WHERE a.estDesc = :estDesc"), @NamedQuery(name = "AFEstimateEntity.findByEstDate", query = "SELECT a FROM AFEstimateEntity a WHERE a.estDate = :estDate"), @NamedQuery(name = "AFEstimateEntity.findByMake", query = "SELECT a FROM AFEstimateEntity a WHERE a.make = :make"), @NamedQuery(name = "AFEstimateEntity.findByModel", query = "SELECT a FROM AFEstimateEntity a WHERE a.model = :model"), @NamedQuery(name = "AFEstimateEntity.findByModId", query = "SELECT a FROM AFEstimateEntity a WHERE a.modId = :modId"), @NamedQuery(name = "AFEstimateEntity.findByModYear", query = "SELECT a FROM AFEstimateEntity a WHERE a.modYear = :modYear"), @NamedQuery(name = "AFEstimateEntity.findByPayType", query = "SELECT a FROM AFEstimateEntity a WHERE a.payType = :payType"), @NamedQuery(name = "AFEstimateEntity.findBySalesTaxAmt", query = "SELECT a FROM AFEstimateEntity a WHERE a.salesTaxAmt = :salesTaxAmt"), @NamedQuery(name = "AFEstimateEntity.findBySalesTaxTypeId", query = "SELECT a FROM AFEstimateEntity a WHERE a.salesTaxTypeId = :salesTaxTypeId"), @NamedQuery(name = "AFEstimateEntity.findByCarDesc", query = "SELECT a FROM AFEstimateEntity a WHERE a.carDesc = :carDesc"), @NamedQuery(name = "AFEstimateEntity.findByCarNotInSys", query = "SELECT a FROM AFEstimateEntity a WHERE a.carNotInSys = :carNotInSys"), @NamedQuery(name = "AFEstimateEntity.findByEngDesc", query = "SELECT a FROM AFEstimateEntity a WHERE a.engDesc = :engDesc"), @NamedQuery(name = "AFEstimateEntity.findByEngCode", query = "SELECT a FROM AFEstimateEntity a WHERE a.engCode = :engCode"), @NamedQuery(name = "AFEstimateEntity.findByTag", query = "SELECT a FROM AFEstimateEntity a WHERE a.tag = :tag"), @NamedQuery(name = "AFEstimateEntity.findByMileage", query = "SELECT a FROM AFEstimateEntity a WHERE a.mileage = :mileage"), @NamedQuery(name = "AFEstimateEntity.findByVin", query = "SELECT a FROM AFEstimateEntity a WHERE a.vin = :vin"), @NamedQuery(name = "AFEstimateEntity.findByTruck", query = "SELECT a FROM AFEstimateEntity a WHERE a.truck = :truck"), @NamedQuery(name = "AFEstimateEntity.findByMgrID", query = "SELECT a FROM AFEstimateEntity a WHERE a.mgrID = :mgrID"), @NamedQuery(name = "AFEstimateEntity.findByCusID", query = "SELECT a FROM AFEstimateEntity a WHERE a.cusID = :cusID"), @NamedQuery(name = "AFEstimateEntity.findByTaxExempt", query = "SELECT a FROM AFEstimateEntity a WHERE a.taxExempt = :taxExempt"), @NamedQuery(name = "AFEstimateEntity.findByTotal", query = "SELECT a FROM AFEstimateEntity a WHERE a.total = :total"), @NamedQuery(name = "AFEstimateEntity.findByPartsTotal", query = "SELECT a FROM AFEstimateEntity a WHERE a.partsTotal = :partsTotal"), @NamedQuery(name = "AFEstimateEntity.findByLaborTotal", query = "SELECT a FROM AFEstimateEntity a WHERE a.laborTotal = :laborTotal"), @NamedQuery(name = "AFEstimateEntity.findByMiscTotal", query = "SELECT a FROM AFEstimateEntity a WHERE a.miscTotal = :miscTotal"), @NamedQuery(name = "AFEstimateEntity.findByTaxMisc", query = "SELECT a FROM AFEstimateEntity a WHERE a.taxMisc = :taxMisc"), @NamedQuery(name = "AFEstimateEntity.findByCarId", query = "SELECT a FROM AFEstimateEntity a WHERE a.carId = :carId"), @NamedQuery(name = "AFEstimateEntity.findByUserDesc", query = "SELECT a FROM AFEstimateEntity a WHERE a.userDesc = :userDesc"), @NamedQuery(name = "AFEstimateEntity.findByLastName", query = "SELECT a FROM AFEstimateEntity a WHERE a.lastName = :lastName"), @NamedQuery(name = "AFEstimateEntity.findByFirstName", query = "SELECT a FROM AFEstimateEntity a WHERE a.firstName = :firstName"), @NamedQuery(name = "AFEstimateEntity.findByMi", query = "SELECT a FROM AFEstimateEntity a WHERE a.mi = :mi"), @NamedQuery(name = "AFEstimateEntity.findByStreet1", query = "SELECT a FROM AFEstimateEntity a WHERE a.street1 = :street1"), @NamedQuery(name = "AFEstimateEntity.findByStreet2", query = "SELECT a FROM AFEstimateEntity a WHERE a.street2 = :street2"), @NamedQuery(name = "AFEstimateEntity.findByCity", query = "SELECT a FROM AFEstimateEntity a WHERE a.city = :city"), @NamedQuery(name = "AFEstimateEntity.findByState", query = "SELECT a FROM AFEstimateEntity a WHERE a.state = :state"), @NamedQuery(name = "AFEstimateEntity.findByZip", query = "SELECT a FROM AFEstimateEntity a WHERE a.zip = :zip"), @NamedQuery(name = "AFEstimateEntity.findByHome", query = "SELECT a FROM AFEstimateEntity a WHERE a.home = :home"), @NamedQuery(name = "AFEstimateEntity.findByWork", query = "SELECT a FROM AFEstimateEntity a WHERE a.work = :work"), @NamedQuery(name = "AFEstimateEntity.findByCell", query = "SELECT a FROM AFEstimateEntity a WHERE a.cell = :cell"), @NamedQuery(name = "AFEstimateEntity.findByFax", query = "SELECT a FROM AFEstimateEntity a WHERE a.fax = :fax"), @NamedQuery(name = "AFEstimateEntity.findByPrintedEstNum", query = "SELECT a FROM AFEstimateEntity a WHERE a.printedEstNum = :printedEstNum"), @NamedQuery(name = "AFEstimateEntity.findByUseFLFooter", query = "SELECT a FROM AFEstimateEntity a WHERE a.useFLFooter = :useFLFooter"), @NamedQuery(name = "AFEstimateEntity.findByTicketType", query = "SELECT a FROM AFEstimateEntity a WHERE a.ticketType = :ticketType"), @NamedQuery(name = "AFEstimateEntity.findByAECarId", query = "SELECT a FROM AFEstimateEntity a WHERE a.aECarId = :aECarId"), @NamedQuery(name = "AFEstimateEntity.findByAECusId", query = "SELECT a FROM AFEstimateEntity a WHERE a.aECusId = :aECusId"), @NamedQuery(name = "AFEstimateEntity.findByExtra", query = "SELECT a FROM AFEstimateEntity a WHERE a.extra = :extra")})
public class AFEstimateEntity implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "EstNum", nullable = false)
    private Integer estNum;
//    @Column(name = "EstDesc", length = 50)
    private String estDesc;
//    @Column(name = "EstDate")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date estDate;
//    @Column(name = "Make", length = 50)
    private String make;
//    @Column(name = "Model", length = 50)
    private String model;
//    @Column(name = "ModId", length = 8)
    private String modId;
//    @Column(name = "ModYear", length = 4)
    private String modYear;
//    @Column(name = "PayType", length = 20)
    private String payType;
//    @Column(name = "SalesTaxAmt", precision = 19, scale = 4)
    private BigDecimal salesTaxAmt;
//    @Column(name = "SalesTaxTypeId")
    private Integer salesTaxTypeId;
//    @Column(name = "CarDesc", length = 65)
    private String carDesc;
//    @Column(name = "CarNotInSys")
    private Integer carNotInSys;
//    @Column(name = "EngDesc", length = 50)
    private String engDesc;
//    @Column(name = "EngCode", length = 8)
    private String engCode;
//    @Column(name = "Tag", length = 20)
    private String tag;
//    @Column(name = "Mileage")
    private Integer mileage;
//    @Column(name = "VIN", length = 50)
    private String vin;
//    @Column(name = "Truck", length = 14)
    private String truck;
//    @Column(name = "MgrID")
    private Integer mgrID;
//    @Column(name = "CusID")
    private Integer cusID;
//    @Column(name = "TaxExempt")
    private Short taxExempt;
//    @Column(name = "Total", precision = 19, scale = 4)
    private BigDecimal total;
//    @Column(name = "PartsTotal", precision = 19, scale = 4)
    private BigDecimal partsTotal;
//    @Column(name = "LaborTotal", precision = 19, scale = 4)
    private BigDecimal laborTotal;
//    @Column(name = "MiscTotal", precision = 19, scale = 4)
    private BigDecimal miscTotal;
//    @Column(name = "TaxMisc", precision = 19, scale = 4)
    private BigDecimal taxMisc;
//    @Column(name = "CarId")
    private Integer carId;
//    @Column(name = "UserDesc", length = 60)
    private String userDesc;
//    @Column(name = "LastName", length = 50)
    private String lastName;
//    @Column(name = "FirstName", length = 25)
    private String firstName;
//    @Column(name = "MI", length = 3)
    private String mi;
//    @Column(name = "Street1", length = 50)
    private String street1;
//    @Column(name = "Street2", length = 50)
    private String street2;
//    @Column(name = "City", length = 35)
    private String city;
//    @Column(name = "State", length = 3)
    private String state;
//    @Column(name = "Zip", length = 20)
    private String zip;
//    @Column(name = "Home", length = 50)
    private String home;
//    @Column(name = "Work", length = 50)
    private String work;
//    @Column(name = "Cell", length = 50)
    private String cell;
//    @Column(name = "Fax", length = 50)
    private String fax;
//    @Column(name = "PrintedEstNum")
    private Integer printedEstNum;
//    @Column(name = "UseFLFooter")
    private Integer useFLFooter;
//    @Column(name = "TicketType", length = 15)
    private String ticketType;
//    @Column(name = "AE_CarId")
    private Integer aECarId;
//    @Column(name = "AE_CusId")
    private Integer aECusId;
//    @Lob
//    @Column(name = "Notes", length = 2147483647)
    private String notes;
//    @Column(name = "Extra")
    private Integer extra;

    // stuff for the JDBTK
    private static String assocTableName = "estimate";
    private static VTable estimateTable;
    private static AFMgr afMgr;

    static
    {
        afMgr = AFMgr.callMgr();
        estimateTable = afMgr.getVTable(assocTableName);
    }

    public AFEstimateEntity() {
    }

    public AFEstimateEntity(Integer estNum) {
        this.estNum = estNum;
    }

   private void checkPlaceHolder() {
        if (estimateTable != null && estimateTable.getRowCount() > 0) {
            return;
        }

        // the following record is used as a place holder for the sake of some foreign keys
        String pattern = "INSERT INTO ticket (ticketNum, carId, cusId, estId,comment) VALUES (%d,%d,%d,%d,%s)";
        String r_ticketNum = "1";
        String r_cusId = "222";
        String r_carId = "250";
        String r_estId = "770";
        String r_comment = "Place holder do not remove";

        int recNumReserved = -1;

        VRecord reservedRec = estimateTable.getEmptyRecord();

        if (reservedRec != null) {
            int PHRecKey = getNextTicketNumber();
            reservedRec.setValue("ticketNum", r_ticketNum);
            reservedRec.setValue("carId", r_carId);
            reservedRec.setValue("cusId", r_cusId);
            reservedRec.setValue("estId", r_estId);
            reservedRec.setValue("comment", r_comment);

            int numRowsInserted = estimateTable.insertRecord(reservedRec, pattern);
            estimateTable.sync();
            System.out.println("Inserted: (" + numRowsInserted + ") rows into Table car");
        }
    }

    static public int getNextTicketNumber()
    {
        int nextKey = -1;
        String currKey = estimateTable.getMaxKeyValue();
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

    public Integer getEstNum() {
        return estNum;
    }

    public void setEstNum(Integer estNum) {
        this.estNum = estNum;
    }

    public String getEstDesc() {
        return estDesc;
    }

    public void setEstDesc(String estDesc) {
        this.estDesc = estDesc;
    }

    public Date getEstDate() {
        return estDate;
    }

    public void setEstDate(Date estDate) {
        this.estDate = estDate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModYear() {
        return modYear;
    }

    public void setModYear(String modYear) {
        this.modYear = modYear;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getSalesTaxAmt() {
        return salesTaxAmt;
    }

    public void setSalesTaxAmt(BigDecimal salesTaxAmt) {
        this.salesTaxAmt = salesTaxAmt;
    }

    public Integer getSalesTaxTypeId() {
        return salesTaxTypeId;
    }

    public void setSalesTaxTypeId(Integer salesTaxTypeId) {
        this.salesTaxTypeId = salesTaxTypeId;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public Integer getCarNotInSys() {
        return carNotInSys;
    }

    public void setCarNotInSys(Integer carNotInSys) {
        this.carNotInSys = carNotInSys;
    }

    public String getEngDesc() {
        return engDesc;
    }

    public void setEngDesc(String engDesc) {
        this.engDesc = engDesc;
    }

    public String getEngCode() {
        return engCode;
    }

    public void setEngCode(String engCode) {
        this.engCode = engCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public Integer getMgrID() {
        return mgrID;
    }

    public void setMgrID(Integer mgrID) {
        this.mgrID = mgrID;
    }

    public Integer getCusID() {
        return cusID;
    }

    public void setCusID(Integer cusID) {
        this.cusID = cusID;
    }

    public Short getTaxExempt() {
        return taxExempt;
    }

    public void setTaxExempt(Short taxExempt) {
        this.taxExempt = taxExempt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPartsTotal() {
        return partsTotal;
    }

    public void setPartsTotal(BigDecimal partsTotal) {
        this.partsTotal = partsTotal;
    }

    public BigDecimal getLaborTotal() {
        return laborTotal;
    }

    public void setLaborTotal(BigDecimal laborTotal) {
        this.laborTotal = laborTotal;
    }

    public BigDecimal getMiscTotal() {
        return miscTotal;
    }

    public void setMiscTotal(BigDecimal miscTotal) {
        this.miscTotal = miscTotal;
    }

    public BigDecimal getTaxMisc() {
        return taxMisc;
    }

    public void setTaxMisc(BigDecimal taxMisc) {
        this.taxMisc = taxMisc;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getPrintedEstNum() {
        return printedEstNum;
    }

    public void setPrintedEstNum(Integer printedEstNum) {
        this.printedEstNum = printedEstNum;
    }

    public Integer getUseFLFooter() {
        return useFLFooter;
    }

    public void setUseFLFooter(Integer useFLFooter) {
        this.useFLFooter = useFLFooter;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Integer getAECarId() {
        return aECarId;
    }

    public void setAECarId(Integer aECarId) {
        this.aECarId = aECarId;
    }

    public Integer getAECusId() {
        return aECusId;
    }

    public void setAECusId(Integer aECusId) {
        this.aECusId = aECusId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estNum != null ? estNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AFEstimateEntity)) {
            return false;
        }
        AFEstimateEntity other = (AFEstimateEntity) object;
        if ((this.estNum == null && other.estNum != null) || (this.estNum != null && !this.estNum.equals(other.estNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wms.antifrieze.entity.AFEstimateEntity[estNum=" + estNum + "]";
    }

}
