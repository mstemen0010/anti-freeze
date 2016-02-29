/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wms.antifreeze.gui;

import java.io.Serializable;


/**
 *
 * @author Matt
 */
//@Entity
//@Table(name = "afviewsextents", catalog = "AFGUIEXTENTS", schema = "")
//@NamedQueries({@NamedQuery(name = "AfViewsExtents.findAll", query = "SELECT a FROM AfViewsExtents a"), @NamedQuery(name = "AfViewsExtents.findByIdAFViewsExtents", query = "SELECT a FROM AfViewsExtents a WHERE a.idAFViewsExtents = :idAFViewsExtents"), @NamedQuery(name = "AfViewsExtents.findByAFViewName", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewName = :aFViewName"), @NamedQuery(name = "AfViewsExtents.findByAFViewLength", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewLength = :aFViewLength"), @NamedQuery(name = "AfViewsExtents.findByAFViewWidth", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewWidth = :aFViewWidth"), @NamedQuery(name = "AfViewsExtents.findByAFViewPortImage", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewPortImage = :aFViewPortImage"), @NamedQuery(name = "AfViewsExtents.findByAFViewTitle", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewTitle = :aFViewTitle"), @NamedQuery(name = "AfViewsExtents.findByAFViewApplyButton", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewApplyButton = :aFViewApplyButton"), @NamedQuery(name = "AfViewsExtents.findByAFViewCancelButton", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewCancelButton = :aFViewCancelButton"), @NamedQuery(name = "AfViewsExtents.findByAFViewIsModal", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViewIsModal = :aFViewIsModal"), @NamedQuery(name = "AfViewsExtents.findByAFViiewShowSearch", query = "SELECT a FROM AfViewsExtents a WHERE a.aFViiewShowSearch = :aFViiewShowSearch")})
public class AfViewsExtents implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @Column(name = "idAFViewsExtents", nullable = false)
    private Integer idAFViewsExtents;
//    @Basic(optional = false)
//    @Column(name = "AFViewName", nullable = false, length = 45)
    private String aFViewName;
//    @Basic(optional = false)
//    @Column(name = "AFViewLength", nullable = false)
    private int aFViewLength;
//    @Basic(optional = false)
//    @Column(name = "AFViewWidth", nullable = false)
    private int aFViewWidth;
//    @Basic(optional = false)
//    @Column(name = "AFViewPortImage", nullable = false, length = 128)
    private String aFViewPortImage;
//    @Basic(optional = false)
//    @Column(name = "AFViewTitle", nullable = false, length = 45)
    private String aFViewTitle;
//    @Basic(optional = false)
//    @Column(name = "AFViewApplyButton", nullable = false)
    private boolean aFViewApplyButton;
//    @Basic(optional = false)
//    @Column(name = "AFViewCancelButton", nullable = false)
    private boolean aFViewCancelButton;
//    @Basic(optional = false)
//    @Column(name = "AFViewIsModal", nullable = false)
    private boolean aFViewIsModal;
//    @Basic(optional = false)
//    @Column(name = "AFViiewShowSearch", nullable = false)
    private boolean aFViiewShowSearch;

    public AfViewsExtents() {
    }

    public AfViewsExtents(Integer idAFViewsExtents) {
        this.idAFViewsExtents = idAFViewsExtents;
    }

    public AfViewsExtents(Integer idAFViewsExtents, String aFViewName, int aFViewLength, int aFViewWidth, String aFViewPortImage, String aFViewTitle, boolean aFViewApplyButton, boolean aFViewCancelButton, boolean aFViewIsModal, boolean aFViiewShowSearch) {
        this.idAFViewsExtents = idAFViewsExtents;
        this.aFViewName = aFViewName;
        this.aFViewLength = aFViewLength;
        this.aFViewWidth = aFViewWidth;
        this.aFViewPortImage = aFViewPortImage;
        this.aFViewTitle = aFViewTitle;
        this.aFViewApplyButton = aFViewApplyButton;
        this.aFViewCancelButton = aFViewCancelButton;
        this.aFViewIsModal = aFViewIsModal;
        this.aFViiewShowSearch = aFViiewShowSearch;
    }

    public Integer getIdAFViewsExtents() {
        return idAFViewsExtents;
    }

    public void setIdAFViewsExtents(Integer idAFViewsExtents) {
        this.idAFViewsExtents = idAFViewsExtents;
    }

    public String getAFViewName() {
        return aFViewName;
    }

    public void setAFViewName(String aFViewName) {
        this.aFViewName = aFViewName;
    }

    public int getAFViewLength() {
        return aFViewLength;
    }

    public void setAFViewLength(int aFViewLength) {
        this.aFViewLength = aFViewLength;
    }

    public int getAFViewWidth() {
        return aFViewWidth;
    }

    public void setAFViewWidth(int aFViewWidth) {
        this.aFViewWidth = aFViewWidth;
    }

    public String getAFViewPortImage() {
        return aFViewPortImage;
    }

    public void setAFViewPortImage(String aFViewPortImage) {
        this.aFViewPortImage = aFViewPortImage;
    }

    public String getAFViewTitle() {
        return aFViewTitle;
    }

    public void setAFViewTitle(String aFViewTitle) {
        this.aFViewTitle = aFViewTitle;
    }

    public boolean getAFViewApplyButton() {
        return aFViewApplyButton;
    }

    public void setAFViewApplyButton(boolean aFViewApplyButton) {
        this.aFViewApplyButton = aFViewApplyButton;
    }

    public boolean getAFViewCancelButton() {
        return aFViewCancelButton;
    }

    public void setAFViewCancelButton(boolean aFViewCancelButton) {
        this.aFViewCancelButton = aFViewCancelButton;
    }

    public boolean getAFViewIsModal() {
        return aFViewIsModal;
    }

    public void setAFViewIsModal(boolean aFViewIsModal) {
        this.aFViewIsModal = aFViewIsModal;
    }

    public boolean getAFViiewShowSearch() {
        return aFViiewShowSearch;
    }

    public void setAFViiewShowSearch(boolean aFViiewShowSearch) {
        this.aFViiewShowSearch = aFViiewShowSearch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAFViewsExtents != null ? idAFViewsExtents.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfViewsExtents)) {
            return false;
        }
        AfViewsExtents other = (AfViewsExtents) object;
        if ((this.idAFViewsExtents == null && other.idAFViewsExtents != null) || (this.idAFViewsExtents != null && !this.idAFViewsExtents.equals(other.idAFViewsExtents))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wms.antifrieze.gui.AfViewsExtents[idAFViewsExtents=" + idAFViewsExtents + "]";
    }

}
