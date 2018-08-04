package com.rafaels.myfirstappretrofit.data.model;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("messageId")
    @Expose
    private String messageId;
    @SerializedName("messageBody")
    @Expose
    private String messageBody;
    @SerializedName("messageCreated")
    @Expose
    private String messageCreated;
    @SerializedName("employeeId")
    @Expose
    private String employeeId;
    @SerializedName("employeeTo")
    @Expose
    private String employeeTo;
    @SerializedName("messageGroup")
    @Expose
    private String messageGroup;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;
    @SerializedName("employeeLastname")
    @Expose
    private String employeeLastname;
    @SerializedName("employeeImage")
    @Expose
    private String employeeImage;
    @SerializedName("read")
    @Expose
    private String read;
    @SerializedName("relid")
    @Expose
    private String relid;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageCreated() {
        return messageCreated;
    }

    public void setMessageCreated(String messageCreated) {
        this.messageCreated = messageCreated;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeTo() {
        return employeeTo;
    }

    public void setEmployeeTo(String employeeTo) {
        this.employeeTo = employeeTo;
    }

    public String getMessageGroup() {
        return messageGroup;
    }

    public void setMessageGroup(String messageGroup) {
        this.messageGroup = messageGroup;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastname() {
        return employeeLastname;
    }

    public void setEmployeeLastname(String employeeLastname) {
        this.employeeLastname = employeeLastname;
    }

    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getRelid() {
        return relid;
    }

    public void setRelid(String relid) {
        this.relid = relid;
    }

}
