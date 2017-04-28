package com.example.ferooz.horticulture.pojoclasses;

/**
 * Created by admin on 4/5/2017.
 */

public class LandCropCenterOfTheFarm {

    String LandId;
    String User_Id;
    int CropCode;
    int Corner_ID;
    int CornerType;
    String Coordinates;
    String first_image_url;
    String second_image_url;
    String third_image_url;
    String fourth_image_url;
    String center_video_url;

    public String getLandId() {
        return LandId;
    }

    public void setLandId(String landId) {
        LandId = landId;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public int getCropCode() {
        return CropCode;
    }

    public void setCropCode(int cropCode) {
        CropCode = cropCode;
    }

    public int getCorner_ID() {
        return Corner_ID;
    }

    public void setCorner_ID(int corner_ID) {
        Corner_ID = corner_ID;
    }

    public int getCornerType() {
        return CornerType;
    }

    public void setCornerType(int cornerType) {
        CornerType = cornerType;
    }

    public String getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(String coordinates) {
        Coordinates = coordinates;
    }

    public String getFirst_image_url() {
        return first_image_url;
    }

    public void setFirst_image_url(String first_image_url) {
        this.first_image_url = first_image_url;
    }

    public String getSecond_image_url() {
        return second_image_url;
    }

    public void setSecond_image_url(String second_image_url) {
        this.second_image_url = second_image_url;
    }

    public String getThird_image_url() {
        return third_image_url;
    }

    public void setThird_image_url(String third_image_url) {
        this.third_image_url = third_image_url;
    }

    public String getFourth_image_url() {
        return fourth_image_url;
    }

    public void setFourth_image_url(String fourth_image_url) {
        this.fourth_image_url = fourth_image_url;
    }

    public String getCenter_video_url() {
        return center_video_url;
    }

    public void setCenter_video_url(String center_video_url) {
        this.center_video_url = center_video_url;
    }
}
