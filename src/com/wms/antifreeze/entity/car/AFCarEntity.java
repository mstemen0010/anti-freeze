/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze.entity.car;

import com.wms.antifreeze.AFMgr;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import java.io.Serializable;


/**
 *
 * @author Matt
 */
//@Entity
//@Table(name = "car", catalog = "AntiFrieze", schema = "")
//@NamedQueries({@NamedQuery(name = "AFCar.findAll", query = "SELECT a FROM AFCar a"), @NamedQuery(name = "AFCar.findByCarId", query = "SELECT a FROM AFCar a WHERE a.carId = :carId"), @NamedQuery(name = "AFCar.findByCusId", query = "SELECT a FROM AFCar a WHERE a.cusId = :cusId"), @NamedQuery(name = "AFCar.findByModId", query = "SELECT a FROM AFCar a WHERE a.modId = :modId"), @NamedQuery(name = "AFCar.findByModYear", query = "SELECT a FROM AFCar a WHERE a.modYear = :modYear"), @NamedQuery(name = "AFCar.findByTag", query = "SELECT a FROM AFCar a WHERE a.tag = :tag"), @NamedQuery(name = "AFCar.findByMileage", query = "SELECT a FROM AFCar a WHERE a.mileage = :mileage"), @NamedQuery(name = "AFCar.findByVin", query = "SELECT a FROM AFCar a WHERE a.vin = :vin"), @NamedQuery(name = "AFCar.findByTruck", query = "SELECT a FROM AFCar a WHERE a.truck = :truck"), @NamedQuery(name = "AFCar.findByCarDesc", query = "SELECT a FROM AFCar a WHERE a.carDesc = :carDesc"), @NamedQuery(name = "AFCar.findByEngDesc", query = "SELECT a FROM AFCar a WHERE a.engDesc = :engDesc"), @NamedQuery(name = "AFCar.findByEngCode", query = "SELECT a FROM AFCar a WHERE a.engCode = :engCode"), @NamedQuery(name = "AFCar.findByCarNotInSys", query = "SELECT a FROM AFCar a WHERE a.carNotInSys = :carNotInSys"), @NamedQuery(name = "AFCar.findByMake", query = "SELECT a FROM AFCar a WHERE a.make = :make"), @NamedQuery(name = "AFCar.findByModel", query = "SELECT a FROM AFCar a WHERE a.model = :model"), @NamedQuery(name = "AFCar.findByAECusId", query = "SELECT a FROM AFCar a WHERE a.aECusId = :aECusId"), @NamedQuery(name = "AFCar.findByAECarId", query = "SELECT a FROM AFCar a WHERE a.aECarId = :aECarId")})
public class AFCarEntity implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "CarId", nullable = false)
    private Integer carId;
//    @Column(name = "CusId")
    private Integer cusId;
//    @Column(name = "ModId", length = 8)
    private String modId;
//    @Column(name = "ModYear", length = 4)
    private String modYear;
//    @Column(name = "Tag", length = 20)
    private String tag;
//    @Column(name = "Mileage")
    private Integer mileage;
//    @Column(name = "VIN", length = 50)
    private String vin;
//    @Column(name = "Truck", length = 14)
    private String truck;
//    @Column(name = "CarDesc", length = 65)
    private String carDesc;
//    @Column(name = "EngDesc", length = 50)
    private String engDesc;
//    @Column(name = "EngCode", length = 8)
    private String engCode;
//    @Column(name = "CarNotInSys")
    private Integer carNotInSys;
//    @Column(name = "Make", length = 50)
    private String make;
//    @Column(name = "Model", length = 50)
    private String model;
//    @Column(name = "AE_CusId")
    private Integer aECusId;
//    @Column(name = "AE_CarId")
    private Integer aECarId;

    // stuff for the JDBTK
    private static String assocTableName = "car";
    private static VTable carTable;
    private static AFMgr afMgr;

    static {
        afMgr = AFMgr.callMgr();
        carTable = afMgr.getVTable(assocTableName);
    }

    public AFCarEntity() {
    }

    public AFCarEntity(String make, String model, String year) {
        this.setMake(make);
        this.setModel(model);
        this.setModYear(year);
    }

    public AFCarEntity(Integer carId) {
        this.carId = carId;
    }

   private void checkPlaceHolder() {
        if (carTable != null && carTable.getRowCount() > 0) {
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

        VRecord reservedRec = carTable.getEmptyRecord();

        if (reservedRec != null) {
            int PHRecKey = getNextTicketNumber();
            reservedRec.setValue("ticketNum", r_ticketNum);
            reservedRec.setValue("carId", r_carId);
            reservedRec.setValue("cusId", r_cusId);
            reservedRec.setValue("estId", r_estId);
            reservedRec.setValue("comment", r_comment);

            int numRowsInserted = carTable.insertRecord(reservedRec, pattern);
            carTable.sync();
            System.out.println("Inserted: (" + numRowsInserted + ") rows into Table car");
        }
    }

    static public int getNextTicketNumber()
    {
        int nextKey = -1;
        String currKey = carTable.getMaxKeyValue();
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


    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
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

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
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

    public Integer getCarNotInSys() {
        return carNotInSys;
    }

    public void setCarNotInSys(Integer carNotInSys) {
        this.carNotInSys = carNotInSys;
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

    public Integer getAECusId() {
        return aECusId;
    }

    public void setAECusId(Integer aECusId) {
        this.aECusId = aECusId;
    }

    public Integer getAECarId() {
        return aECarId;
    }

    public void setAECarId(Integer aECarId) {
        this.aECarId = aECarId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AFCarEntity)) {
            return false;
        }
        AFCarEntity other = (AFCarEntity) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wms.antifrieze.entity.AFCar[carId=" + carId + "]";
    }

}
