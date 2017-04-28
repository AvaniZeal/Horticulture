package com.example.ferooz.horticulture.pojoclasses;

import java.io.Serializable;

/**
 * Created by ferooz on 12-04-2017.
 */

public class GenericAdvisoryList implements Serializable{



    String advisorid;
    String cropcode;
    String address;
    String createdDate;
    String category;
    String name;
    //   String Advisory_Video_Path;
    byte[] bitmap;
    String advice;

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String videoPath;
    String audioPath;
    String imagePath;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }



    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCropcode() {
        return cropcode;
    }

    public void setCropcode(String cropcode) {
        this.cropcode = cropcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvisorid() {
        return advisorid;
    }

    public void setAdvisorid(String advisorid) {
        this.advisorid = advisorid;
    }

}
