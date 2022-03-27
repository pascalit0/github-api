package com.pascalito.github.githubapi;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHub;

import java.io.IOException;

class GitHubApiTest {

    private final String ORGANIZATION = "codecentric";

    @Test
    void testGitHubApi() throws IOException {

        GitHub github = GitHub.connectAnonymously();
        GHOrganization org = github.getOrganization(ORGANIZATION);

        // All Codecentric Members
        org.getMembers().get(0).getRepositories().values().stream()
                .map(repo -> repo.getLanguage())
                .forEach(System.out::println);


        System.out.println("\nPascalit0:\n");
        // Own repo with multiple langs
        github.getUser("pascalit0").getRepositories().values().stream()
                .map(repo -> repo.getLanguage())
                .forEach(System.out::println);
    }
}