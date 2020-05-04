package com.menu.bean;

import com.github.pagehelper.PageInfo;
import com.menu.util.AbstractPageRequest;

import java.io.Serializable;
import java.util.Date;

public class WeatherInfoDO extends AbstractPageRequest implements Serializable {
    private Integer id;

    private String location;

    private String cid;

    private String lat;

    private String lon;

    private String parentCity;

    private String adminArea;

    private String cnty;

    private String tz;

    private String fl;

    private String tmp;

    private String condTxt;

    private String windDeg;

    private String windDir;

    private Byte isDelete;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon == null ? null : lon.trim();
    }

    public String getParentCity() {
        return parentCity;
    }

    public void setParentCity(String parentCity) {
        this.parentCity = parentCity == null ? null : parentCity.trim();
    }

    public String getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea == null ? null : adminArea.trim();
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty == null ? null : cnty.trim();
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz == null ? null : tz.trim();
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl == null ? null : fl.trim();
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp == null ? null : tmp.trim();
    }

    public String getCondTxt() {
        return condTxt;
    }

    public void setCondTxt(String condTxt) {
        this.condTxt = condTxt == null ? null : condTxt.trim();
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg == null ? null : windDeg.trim();
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir == null ? null : windDir.trim();
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WeatherInfoDO other = (WeatherInfoDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getParentCity() == null ? other.getParentCity() == null : this.getParentCity().equals(other.getParentCity()))
            && (this.getAdminArea() == null ? other.getAdminArea() == null : this.getAdminArea().equals(other.getAdminArea()))
            && (this.getCnty() == null ? other.getCnty() == null : this.getCnty().equals(other.getCnty()))
            && (this.getTz() == null ? other.getTz() == null : this.getTz().equals(other.getTz()))
            && (this.getFl() == null ? other.getFl() == null : this.getFl().equals(other.getFl()))
            && (this.getTmp() == null ? other.getTmp() == null : this.getTmp().equals(other.getTmp()))
            && (this.getCondTxt() == null ? other.getCondTxt() == null : this.getCondTxt().equals(other.getCondTxt()))
            && (this.getWindDeg() == null ? other.getWindDeg() == null : this.getWindDeg().equals(other.getWindDeg()))
            && (this.getWindDir() == null ? other.getWindDir() == null : this.getWindDir().equals(other.getWindDir()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getParentCity() == null) ? 0 : getParentCity().hashCode());
        result = prime * result + ((getAdminArea() == null) ? 0 : getAdminArea().hashCode());
        result = prime * result + ((getCnty() == null) ? 0 : getCnty().hashCode());
        result = prime * result + ((getTz() == null) ? 0 : getTz().hashCode());
        result = prime * result + ((getFl() == null) ? 0 : getFl().hashCode());
        result = prime * result + ((getTmp() == null) ? 0 : getTmp().hashCode());
        result = prime * result + ((getCondTxt() == null) ? 0 : getCondTxt().hashCode());
        result = prime * result + ((getWindDeg() == null) ? 0 : getWindDeg().hashCode());
        result = prime * result + ((getWindDir() == null) ? 0 : getWindDir().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", location=").append(location);
        sb.append(", cid=").append(cid);
        sb.append(", lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append(", parentCity=").append(parentCity);
        sb.append(", adminArea=").append(adminArea);
        sb.append(", cnty=").append(cnty);
        sb.append(", tz=").append(tz);
        sb.append(", fl=").append(fl);
        sb.append(", tmp=").append(tmp);
        sb.append(", condTxt=").append(condTxt);
        sb.append(", windDeg=").append(windDeg);
        sb.append(", windDir=").append(windDir);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}