package com.pascalito.github.githubapi.service.impl;

import com.pascalito.github.githubapi.client.GitHubRestClient;
import com.pascalito.github.githubapi.client.model.UserDTO;
import com.pascalito.github.githubapi.repository.UserRepository;
import com.pascalito.github.githubapi.repository.entity.LanguageEntity;
import com.pascalito.github.githubapi.repository.entity.RepositoryEntity;
import com.pascalito.github.githubapi.repository.entity.UserEntity;
import com.pascalito.github.githubapi.service.GitHubImporterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class GitHubImporterServiceImpl implements GitHubImporterService {

    private static final String CODE_CENTRIC_ORGA = "codecentric";

    private final GitHubRestClient gitHubRestClient;
    private final UserRepository userRepository;


    @Transactional
    @Override
    public void importUsersFromGitHub() {
        var users = gitHubRestClient.getOrganizationUsers(CODE_CENTRIC_ORGA);
        users.stream()
                .map(this::buildUserEntity)
                .forEach(userRepository::save);
    }

    private UserEntity buildUserEntity(UserDTO user) {
        var userEntity = UserEntity.builder()
                .name(user.getLogin())
                .repos(buildRepoEntities(user))
                .build();
        userEntity.getRepos().forEach(repositoryEntity -> repositoryEntity.setUser(userEntity));
        return userEntity;
    }

    private List<RepositoryEntity> buildRepoEntities(UserDTO userDTO) {
        var repos = gitHubRestClient.getUserRepos(userDTO.getLogin());
        return repos.stream()
                .map(repo -> buildRepoEntity(userDTO.getLogin(), repo.getName()))
                .collect(Collectors.toList());
    }

    private RepositoryEntity buildRepoEntity(String userName, String repoName) {
        var repoEntity = RepositoryEntity.builder()
                .name(repoName)
                .languages(buildRepoLanguageEntities(userName, repoName))
                .build();
        repoEntity.getLanguages().forEach(languageEntity -> languageEntity.setRepository(repoEntity));
        return repoEntity;
    }

    private List<LanguageEntity> buildRepoLanguageEntities(String userName, String repoName) {
        var langs = gitHubRestClient.getRepoLanguages(userName, repoName);
        return langs.stream()
                .map(lang -> LanguageEntity.builder()
                        .name(lang.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
