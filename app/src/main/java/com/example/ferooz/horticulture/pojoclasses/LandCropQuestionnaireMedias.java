package com.example.ferooz.horticulture.pojoclasses;

/**
 * Created by admin on 4/3/2017.
 */

public class LandCropQuestionnaireMedias {

    String User_Id;
    String Crop_Id;
    String Land_Id;
    String Question_id;
    String Media_type;
    String Media_url;
    int MediadId;
    String Media_help_text;
    String Coordinates;
    String UploadedStatus;

    public String getUploadedStatus() {
        return UploadedStatus;
    }

    public void setUploadedStatus(String uploadedStatus) {
        UploadedStatus = uploadedStatus;
    }

    public String getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(String coordinates) {
        Coordinates = coordinates;
    }

    public int getMediadId() {
        return MediadId;
    }

    public void setMediadId(int mediadId) {
        MediadId = mediadId;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getCrop_Id() {
        return Crop_Id;
    }

    public void setCrop_Id(String crop_Id) {
        Crop_Id = crop_Id;
    }

    public String getLand_Id() {
        return Land_Id;
    }

    public void setLand_Id(String land_Id) {
        Land_Id = land_Id;
    }

    public String getQuestion_id() {
        return Question_id;
    }

    public void setQuestion_id(String question_id) {
        Question_id = question_id;
    }

    public String getMedia_type() {
        return Media_type;
    }

    public void setMedia_type(String media_type) {
        Media_type = media_type;
    }

    public String getMedia_url() {
        return Media_url;
    }

    public void setMedia_url(String media_url) {
        Media_url = media_url;
    }

    public String getMedia_help_text() {
        return Media_help_text;
    }

    public void setMedia_help_text(String media_help_text) {
        Media_help_text = media_help_text;
    }
}
