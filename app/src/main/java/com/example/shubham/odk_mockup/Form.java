package com.example.shubham.odk_mockup;

/**
 * Created by shubham on 21/3/17.
 */

public class Form {
    String formName;
    String formStatus;
    String timestamp;

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Form(String formName, String formStatus, String timestamp) {

        this.formName = formName;
        this.formStatus = formStatus;
        this.timestamp = timestamp;
    }
}
