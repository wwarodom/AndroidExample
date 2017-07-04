package com.warodom.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wwaro on 7/4/2017.
 */

public class GitHubUser {

    @SerializedName("login")  // name indicated in JSON
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("company")
    private String company;

    @SerializedName("blog")
    private String blog;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "name: " + name + "\ncompany: " + company + "\nblog: " + blog;
    }
}
