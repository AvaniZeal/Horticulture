package com.example.ferooz.horticulture.pojoclasses;

import java.io.Serializable;

/**
 * Created by user on 07/04/2017.
 */

public class ViewAdvisory implements Serializable {

    String queryId;
    String cropcode;
    String farmerId;
    String landId;
    String queryCategory;
    String subCategory;
    String QueryStatus;
    String expertAdvice;
    String advisoryVideoPath;
    String advisoryAudioPath;
    String advisoryImagePath;
    String createdDate;
    String cropExpertId;
    String farmerRatings;
    String createdBy;
    String modifiedDate;
    String modifiedBy;
    String farmerQuery;

    public String getFarmerQuery() {
        return farmerQuery;
    }

    public void setFarmerQuery(String farmerQuery) {
        this.farmerQuery = farmerQuery;
    }

    public String getAdvisoryAudioPath() {
        return advisoryAudioPath;
    }

    public void setAdvisoryAudioPath(String advisoryAudioPath) {
        this.advisoryAudioPath = advisoryAudioPath;
    }

    public String getAdvisoryImagePath() {
        return advisoryImagePath;
    }

    public void setAdvisoryImagePath(String advisoryImagePath) {
        this.advisoryImagePath = advisoryImagePath;
    }




    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getCropcode() {
        return cropcode;
    }

    public void setCropcode(String cropcode) {
        this.cropcode = cropcode;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getLandId() {
        return landId;
    }

    public void setLandId(String landId) {
        this.landId = landId;
    }

    public String getQueryCategory() {
        return queryCategory;
    }

    public void setQueryCategory(String queryCategory) {
        this.queryCategory = queryCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getQueryStatus() {
        return QueryStatus;
    }

    public void setQueryStatus(String queryStatus) {
        QueryStatus = queryStatus;
    }

    public String getExpertAdvice() {
        return expertAdvice;
    }

    public void setExpertAdvice(String expertAdvice) {
        this.expertAdvice = expertAdvice;
    }

    public String getAdvisoryVideoPath() {
        return advisoryVideoPath;
    }

    public void setAdvisoryVideoPath(String advisoryVideoPath) {
        this.advisoryVideoPath = advisoryVideoPath;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCropExpertId() {
        return cropExpertId;
    }

    public void setCropExpertId(String cropExpertId) {
        this.cropExpertId = cropExpertId;
    }

    public String getFarmerRatings() {
        return farmerRatings;
    }

    public void setFarmerRatings(String farmerRatings) {
        this.farmerRatings = farmerRatings;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }



}