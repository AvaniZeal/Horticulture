package com.example.ferooz.horticulture.pojoclasses;

/**
 * Created by admin on 4/1/2017.
 */

public class Farmer_Registered_Crop {


    String UserID;
    int CropCode;
    String CreatedDate;
    String CreatedBy;
    String ModifiedDate;
    String ModifiedBy;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getCropCode() {
        return CropCode;
    }

    public void setCropCode(int cropCode) {
        CropCode = cropCode;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }
}
