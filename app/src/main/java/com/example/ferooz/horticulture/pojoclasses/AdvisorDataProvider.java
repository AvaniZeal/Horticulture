package com.example.ferooz.horticulture.pojoclasses;

import java.io.Serializable;

/**
 * Created by Rajeshwari on 4/1/2017.
 */

public class AdvisorDataProvider implements Serializable {

   String advisor_image;
    String advisor_name;
    byte[] bitmap;
    String advised_date;

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

   public String getAdvisor_image() {
        return advisor_image;
    }

    public void setAdvisor_image(String advisor_image) {
        this.advisor_image = advisor_image;
    }


    public AdvisorDataProvider(String advisor_image, String advisor_name, String advised_date, byte[] bitmap) {

        this.setAdvisor_image(advisor_image);
        this.setAdvisor_name(advisor_name);
        this.setAdvised_date(advised_date);
        this.setBitmap(bitmap);
    }

    public AdvisorDataProvider(String name, String cropcode, String address, byte[] bitmap, String advisory_video_path) {

    }


    public String getAdvisor_name() {
        return advisor_name;
    }

    public void setAdvisor_name(String advisor_name) {
        this.advisor_name = advisor_name;
    }

    public String getAdvised_date() {
        return advised_date;
    }

    public void setAdvised_date(String advised_date) {
        this.advised_date = advised_date;
    }
}
