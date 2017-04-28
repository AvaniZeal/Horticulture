package com.example.ferooz.horticulture.pojoclasses;

import java.io.Serializable;

/**
 * Created by user on 31/03/2017.
 */

public class ExpertDetails implements Serializable {
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    int SlNo;
    String QueryTitle;
    String Query;
    String PhotoLoaction;
    String VideoLocation;
    String AudioLocation;
    String Status;

    public int getSlNo() {
        return SlNo;
    }

    public void setSlNo(int slNo) {
        SlNo = slNo;
    }

    public String getQueryTitle() {
        return QueryTitle;
    }

    public void setQueryTitle(String queryTitle) {
        QueryTitle = queryTitle;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public String getPhotoLoaction() {
        return PhotoLoaction;
    }

    public void setPhotoLoaction(String photoLoaction) {
        PhotoLoaction = photoLoaction;
    }

    public String getVideoLocation() {
        return VideoLocation;
    }

    public void setVideoLocation(String videoLocation) {
        VideoLocation = videoLocation;
    }

    public String getAudioLocation() {
        return AudioLocation;
    }

    public void setAudioLocation(String audioLocation) {
        AudioLocation = audioLocation;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }




}
