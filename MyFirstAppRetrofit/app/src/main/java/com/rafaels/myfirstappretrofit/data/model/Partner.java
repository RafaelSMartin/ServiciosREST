package com.rafaels.myfirstappretrofit.data.model;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partner {

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

}
