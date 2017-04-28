package com.example.ferooz.horticulture.pojoclasses;

/**
 * Created by admin on 4/4/2017.
 */

public class Master_Options {

    int LanguageId ;
    int QuestionId ;
    int OptionId ;
    String Option_Desc ;
    String Marks ;
    String CreatedOn ;
    String CreatedBy ;
    String UpdatedOn ;
    String UpdatedBy;


    public int getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(int languageId) {
        LanguageId = languageId;
    }

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public int getOptionId() {
        return OptionId;
    }

    public void setOptionId(int optionId) {
        OptionId = optionId;
    }

    public String getOption_Desc() {
        return Option_Desc;
    }

    public void setOption_Desc(String option_Desc) {
        Option_Desc = option_Desc;
    }

    public String getMarks() {
        return Marks;
    }

    public void setMarks(String marks) {
        Marks = marks;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        UpdatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }
}
