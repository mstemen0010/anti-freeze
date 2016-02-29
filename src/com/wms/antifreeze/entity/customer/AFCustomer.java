/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze.entity.customer;

import java.io.Serializable;


/**
 *
 * @author mstemen
 */
//@Entity
//@Table(name = "AF_Cust")
//@NamedQueries({@NamedQuery(name = "AFCust.findAll", query = "SELECT a FROM AFCust a"), @NamedQuery(name = "AFCust.findByAFCustID", query = "SELECT a FROM AFCust a WHERE a.aFCustID = :aFCustID")})
public class AFCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    AFCustomerEntity myEntity = null;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "AF_Cust_ID")
    private Integer aFCustID;

    public AFCustomer()
    {
    }

    public AFCustomer(AFCustomerEntity entity )
    {
        this.myEntity = entity;
        this.aFCustID = entity.getCusID();
    }

    public AFCustomer(Integer aFCustID)
    {
        this.aFCustID = aFCustID;
    }

    public Integer getAFCustID()
    {
        return aFCustID;
    }

    public void setAFCustID(Integer aFCustID)
    {
        this.aFCustID = aFCustID;
    }

    public AFCustomerEntity getEntity()
    {
        return this.myEntity;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (aFCustID != null ? aFCustID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AFCustomer)) {
            return false;
        }
        AFCustomer other = (AFCustomer) object;
        if ((this.aFCustID == null && other.aFCustID != null) || (this.aFCustID != null && !this.aFCustID.equals(other.aFCustID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "antifrieze.AFCust[aFCustID=" + aFCustID + "]";
    }

}
