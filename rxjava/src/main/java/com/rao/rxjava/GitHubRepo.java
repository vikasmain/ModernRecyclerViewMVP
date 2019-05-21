package com.rao.rxjava;


public class GitHubRepo {
    private int id;
    final String name;
    private String htmlUrl;
    final String description;
    final String language;
    final int stargazersCount;

    public GitHubRepo(int id, String name, String htmlUrl, String description, String language,
                      int stargazersCount) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.language = language;
        this.stargazersCount = stargazersCount;
    }
}
