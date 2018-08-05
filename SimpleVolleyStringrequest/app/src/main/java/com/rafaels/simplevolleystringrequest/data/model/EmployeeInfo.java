package com.rafaels.simplevolleystringrequest.data.model;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeInfo {

    @SerializedName("employeeId")
    @Expose
    private String employeeId;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;
    @SerializedName("employeeLastname")
    @Expose
    private String employeeLastname;
    @SerializedName("employeeImage")
    @Expose
    private String employeeImage;
    @SerializedName("jobName")
    @Expose
    private String jobName;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("tokenId")
    @Expose
    private String tokenId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

//    @Override
//    public String toString() {
//        return "EmployeeInfo{" +
//                "employeeId='" + employeeId + '\'' +
//                "employeeName='" + employeeName + '\'' +
//                "employeeLastname='" + employeeLastname + '\'' +
//                "employeeImage='" + employeeImage + '\'' +
//                "jobName='" + jobName + '\'' +
//                "userEmail='" + userEmail + '\'' +
//                "tokenId='" + tokenId+ '\'' +
//                '}';
//    }

}
