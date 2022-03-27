package com.pascalito.github.githubapi.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascalito.github.githubapi.client.model.LanguageDTO;
import com.pascalito.github.githubapi.client.model.RepoDTO;
import com.pascalito.github.githubapi.client.model.UserDTO;
import lombok.SneakyThrows;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GitHubRestClient {
    private static final String BASE_URL = "https://api.github.com";
    private static final UriTemplate ORG_URI_TPL = new UriTemplate(BASE_URL + "/orgs/{org}/members");
    private static final UriTemplate USER_URI_TPL = new UriTemplate(BASE_URL + "/users/{user}");
    private static final UriTemplate REPOS_URI_TPL = new UriTemplate(BASE_URL + "/users/{user}/repos");
    private static final UriTemplate LANGS_URI_TPL = new UriTemplate(BASE_URL + "/repos/{user}/{repo}/languages");

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;


    public GitHubRestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<UserDTO> getOrganizationUsers(String orgName) {
        return Arrays.asList(restTemplate.getForObject(ORG_URI_TPL.expand(orgName), UserDTO[].class));
    }

    public UserDTO getUser(String userName) {
        return restTemplate.getForObject(USER_URI_TPL.expand(userName), UserDTO.class);
    }

    public List<RepoDTO> getUserRepos(String userName) {
        return Arrays.asList(restTemplate.getForObject(REPOS_URI_TPL.expand(userName), RepoDTO[].class));
    }

    @SneakyThrows
    public List<LanguageDTO> getRepoLanguages(String userName, String repoName) {
        var jsonString = restTemplate.getForEntity(LANGS_URI_TPL.expand(userName, repoName), String.class);

        var languageJsonNodes = objectMapper.readTree(jsonString.getBody());
        return getLanguagesFromJsonNodes(languageJsonNodes);
    }

    private List<LanguageDTO> getLanguagesFromJsonNodes(JsonNode jsonNode) {
        var languages = new ArrayList<LanguageDTO>();
        var fieldNamesIterator = jsonNode.fieldNames();
        while(fieldNamesIterator.hasNext()) {
            languages.add(new LanguageDTO(fieldNamesIterator.next()));
        }
        return languages;
    }
}

