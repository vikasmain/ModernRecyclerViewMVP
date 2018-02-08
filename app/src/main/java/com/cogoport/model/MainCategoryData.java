package com.cogoport.model;

import java.util.List;

public class MainCategoryData {

    public  int id;
    public  String name;
    private String html_url;
    public  String description;
    public  String language;
    public  int stargazers_count;

    public MainCategoryData() {

    }

    public int getId() {
        return id;
    }
    List<Vikashumain> data2;
    public String getName() {
        return name;
    }

    public List<Vikashumain> getData2() {
        return data2;
    }

    public void setData2(List<Vikashumain> data2) {
        this.data2 = data2;
    }

    public void setId(int id) {
        this.id = id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHtmlUrl(String html_url) {
        this.html_url = html_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStargazersCount(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getHtmlUrl() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getStargazersCount() {
        return stargazers_count;
    }
    public MainCategoryData(int id, String name, String html_url, String description, String language, int stargazers_count) {
        this.id = id;
        this.name = name;
        this.html_url = html_url;
        this.description = description;
        this.language = language;
        this.stargazers_count = stargazers_count;
    }
}