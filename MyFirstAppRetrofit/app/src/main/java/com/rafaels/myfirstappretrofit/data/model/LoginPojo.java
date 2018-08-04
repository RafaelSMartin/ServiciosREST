package com.rafaels.myfirstappretrofit.data.model;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginPojo {

    //Para q Gson mapee las llavees JSON a campos de objeto Java
    @SerializedName("employeePath")
    // Indica q el miembro de la clase deberia ser expuesto para
    // la serializacion o deserializacion JSON
    @Expose
    private String employeePath;
    @SerializedName("employeePath45")
    @Expose
    private String employeePath45;
    @SerializedName("employeeInfo")
    @Expose
    private List<EmployeeInfo> employeeInfo = null;
    @SerializedName("partners")
    @Expose
    private List<Partner> partners = null;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("members")
    @Expose
    private List<Member> members = null;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("response")
    @Expose
    private Response response;

    public String getEmployeePath() {
        return employeePath;
    }

    public void setEmployeePath(String employeePath) {
        this.employeePath = employeePath;
    }

    public String getEmployeePath45() {
        return employeePath45;
    }

    public void setEmployeePath45(String employeePath45) {
        this.employeePath45 = employeePath45;
    }

    public List<EmployeeInfo> getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(List<EmployeeInfo> employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "LoginPojo{" +
                "employeePath='" + employeePath + '\'' +
                ", employeePath45='" + employeePath45 + '\'' +
                ", employeeInfo='" + employeeInfo + '\'' +
                ", partners='" + partners + '\'' +
                ", teams='" + teams + '\'' +
                ", members" + members + '\'' +
                ", messages" + messages + '\'' +
                ", response" + response + '\'' +
                '}';
    }

}
