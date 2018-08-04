package com.rafaels.myfirstappretrofit.data.model;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("teamId")
    @Expose
    private String teamId;
    @SerializedName("teamName")
    @Expose
    private String teamName;
    @SerializedName("maxultimo")
    @Expose
    private String maxultimo;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getMaxultimo() {
        return maxultimo;
    }

    public void setMaxultimo(String maxultimo) {
        this.maxultimo = maxultimo;
    }

}