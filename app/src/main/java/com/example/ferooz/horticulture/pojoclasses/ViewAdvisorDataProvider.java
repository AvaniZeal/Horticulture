package com.example.ferooz.horticulture.pojoclasses;

import java.io.Serializable;

/**
 * Created by Rajeshwari on 4/1/2017.
 */

public class ViewAdvisorDataProvider implements Serializable {

    String queryId;
    String queryCategory;
    String place;


    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getQueryCategory() {
        return queryCategory;
    }

    public void setQueryCategory(String queryCategory) {
        this.queryCategory = queryCategory;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }




    public ViewAdvisorDataProvider(String queryId, String queryCategory, String place) {

        this.setQueryId(queryId);
        this.setQueryCategory(queryCategory);
        this.setPlace(place);

    }

    public ViewAdvisorDataProvider() {

    }


}
