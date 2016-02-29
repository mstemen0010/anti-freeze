/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.antifreeze.entity;

import com.wms.jdbtk3.VDatabase;
import com.wms.jdbtk3.VRecord;
import com.wms.jdbtk3.VTable;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;


/**
 *
 * @author Matt
 */
//@Entity
//@Table(name = "af_shopinfo", catalog = "AntiFrieze", schema = "")
//@NamedQueries({@NamedQuery(name = "AfShopinfo.findAll", query = "SELECT a FROM AfShopinfo a"), @NamedQuery(name = "AfShopinfo.findByAfshopId", query = "SELECT a FROM AfShopinfo a WHERE a.afshopId = :afshopId"), @NamedQuery(name = "AfShopinfo.findByAfShopPhone1", query = "SELECT a FROM AfShopinfo a WHERE a.afShopPhone1 = :afShopPhone1"), @NamedQuery(name = "AfShopinfo.findByAfShopPhone2", query = "SELECT a FROM AfShopinfo a WHERE a.afShopPhone2 = :afShopPhone2"), @NamedQuery(name = "AfShopinfo.findByAfshopFax", query = "SELECT a FROM AfShopinfo a WHERE a.afshopFax = :afshopFax"), @NamedQuery(name = "AfShopinfo.findByAfShopCell", query = "SELECT a FROM AfShopinfo a WHERE a.afShopCell = :afShopCell"), @NamedQuery(name = "AfShopinfo.findByAfshopCity", query = "SELECT a FROM AfShopinfo a WHERE a.afshopCity = :afshopCity"), @NamedQuery(name = "AfShopinfo.findByAfshopCountryCode", query = "SELECT a FROM AfShopinfo a WHERE a.afshopCountryCode = :afshopCountryCode"), @NamedQuery(name = "AfShopinfo.findByAfLocale", query = "SELECT a FROM AfShopinfo a WHERE a.afLocale = :afLocale")})
public class AFShopEntity implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "af_shopId")
    private Integer afshopId;
//    @Lob
//    @Column(name = "af_shopName")
    private String afshopName;
//    @Lob
//    @Column(name = "af_shopAddr1")
    private String afshopAddr1;
//    @Lob
//    @Column(name = "af_shopAddr2")
    private String afshopAddr2;
//    @Column(name = "af_ShopPhone1")
    private String afShopPhone1;
//    @Column(name = "af_ShopPhone2")
    private String afShopPhone2;
//    @Column(name = "af_shopFax")
    private String afshopFax;
//    @Column(name = "af_ShopCell")
    private String afShopCell;
//    @Column(name = "af_shopCity")
    private String afshopCity;
//    @Column(name = "af_shopCountryCode")
    private String afshopCountryCode;
//    @Column(name = "af_locale")

    private String afLocale;
    private VDatabase afDb = null;
    private VRecord afShopRec = null;
    private boolean needsCreate = false;

    VTable afShopInfoTable = null;
    public AFShopEntity() {
    }

    public AFShopEntity(VDatabase afDb) {
        this.afDb = afDb;
    }


    public void map() {
        if (afDb != null) {
            afShopInfoTable = afDb.getTable("af_shopinfo");
            afShopInfoTable.setSynced(true);
            Vector<VRecord> recs = afShopInfoTable.getRows();
            if( recs.size() == 0 )
            {
                needsCreate = true;
                return;
            }
            Iterator<VRecord> it = recs.iterator();
            while (it.hasNext()) {

                VRecord rec = it.next();
                afShopRec = rec;
                afshopName = rec.getFieldData("af_shopName");
                if (afshopName != null) {
                    this.setAfshopName(afshopName, false);
                }
                afshopAddr1 = rec.getFieldData("af_shopAddr1");
                if (afshopAddr1 != null) {
                    this.setAfshopAddr1(afshopAddr1, false );
                }
                afshopAddr2 = rec.getFieldData("af_shopAddr2");
                if (afshopAddr2 != null) {
                    this.setAfshopAddr2(afshopAddr2, false );
                }
                afshopCity = rec.getFieldData("af_shopCity");
                if (afshopCity != null) {
                    this.setAfshopCity(afshopCity, false );
                }
                this.afLocale = rec.getFieldData("af_locale");
                if (afLocale != null) {
                    this.setAfLocale(afLocale, false );
                }
                this.afShopPhone1 = rec.getFieldData("af_ShopPhone_1");
                if (afShopPhone1 != null) {
                    this.setAfShopPhone1(afShopPhone1, false );
                }
                this.afShopPhone2 = rec.getFieldData("af_ShopPhone_2");
                if (afShopPhone2 != null) {
                    this.setAfShopPhone1(afShopPhone2, false );
                }
                this.afShopCell = rec.getFieldData("af_ShopCell");
                if( afShopCell != null )
                {
                    this.setAfShopCell(afShopCell, false
                            );
                }
                this.afshopFax = rec.getFieldData("af_shopFax");
                if( afshopFax != null )
                {
                    this.setAfshopFax(afshopFax, false );
                }
                this.afshopCountryCode = rec.getFieldData("af_shopCountryCode");
                if( afshopCountryCode != null )
                {
                    this.setAfshopCountryCode(afshopCountryCode, false );
                }

            }





        }
    }

    private void updateShopRec()
    {
        if(this.needsCreate)
        {
            this.afShopRec = afDb.getTable("af_shopinfo").getEmptyRecord();
        }
            if( afShopRec != null )
            {
                 this.afShopRec.setValue("af_shopName", this.afshopName);
                 this.afShopRec.setValue("af_shopAddr1", this.afshopAddr1);
                 this.afShopRec.setValue("af_shopAddr2", this.afshopAddr2 );
                 this.afShopRec.setValue("af_shopCity", this.afshopCity );
                 this.afShopRec.setValue("af_locale", this.afLocale);
                 this.afShopRec.setValue("af_shopCountryCode", this.afshopCountryCode);
                 this.afShopRec.setValue("af_ShopFax", this.afshopFax);
                 this.afShopRec.setValue("ShopPhone1", this.afShopPhone1);
                 this.afShopRec.setValue("af_ShopCell", this.afShopCell);
            }
            afDb.getTable("af_shopinfo").commit();
    }

    public AFShopEntity(VDatabase afDb, Integer afshopId) {
        this.afshopId = afshopId;
        this.afDb = afDb;
    }

    public Integer getAfshopId() {
        return afshopId;
    }

    public void setAfshopId(Integer afshopId, boolean update) {
        this.afshopId = afshopId;
    }

    public String getAfshopName() {
        return afshopName;
    }

    public void setAfshopName(String afshopName, boolean update) {
        this.afshopName = afshopName;
        if( update )
            updateShopRec();
    }

    public String getAfshopAddr1() {
        return afshopAddr1;
    }

    public void setAfshopAddr1(String afshopAddr1, boolean update) {
        this.afshopAddr1 = afshopAddr1;
        if( update )
            this.updateShopRec();
    }

    public String getAfshopAddr2() {
        return afshopAddr2;
    }

    public void setAfshopAddr2(String afshopAddr2, boolean update) {
        this.afshopAddr2 = afshopAddr2;
        if( update )
            this.updateShopRec();

    }

    public String getAfShopPhone1() {
        return afShopPhone1;
    }

    public void setAfShopPhone1(String afShopPhone1, boolean update) {
        this.afShopPhone1 = afShopPhone1;
        if( update )
            this.updateShopRec();

    }

    public String getAfShopPhone2() {
        return afShopPhone2;
    }

    public void setAfShopPhone2(String afShopPhone2, boolean update) {
        this.afShopPhone2 = afShopPhone2;
        if( update )
            this.updateShopRec();

    }

    public String getAfshopFax() {
        return afshopFax;
    }

    public void setAfshopFax(String afshopFax, boolean update) {
        this.afshopFax = afshopFax;
        if( update )
            this.updateShopRec();

    }

    public String getAfShopCell() {
        return afShopCell;
    }

    public void setAfShopCell(String afShopCell, boolean update) {
        this.afShopCell = afShopCell;
        if( update )
            this.updateShopRec();

    }

    public String getAfshopCity() {
        return afshopCity;
    }

    public void setAfshopCity(String afshopCity, boolean update) {
        this.afshopCity = afshopCity;
        if( update )
            this.updateShopRec();
    }

    public String getAfshopCountryCode() {
        return afshopCountryCode;
    }

    public void setAfshopCountryCode(String afshopCountryCode, boolean update) {
        this.afshopCountryCode = afshopCountryCode;
        if( update )
            this.updateShopRec();
    }

    public String getAfLocale() {
        return afLocale;
    }

    public void setAfLocale(String afLocale, boolean update) {
        this.afLocale = afLocale;
        if( update )
            this.updateShopRec();

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afshopId != null ? afshopId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AFShopEntity)) {
            return false;
        }
        AFShopEntity other = (AFShopEntity) object;
        if ((this.afshopId == null && other.afshopId != null) || (this.afshopId != null && !this.afshopId.equals(other.afshopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wms.antifrieze.AfShopinfo[afshopId=" + afshopId + "]";
    }
}
