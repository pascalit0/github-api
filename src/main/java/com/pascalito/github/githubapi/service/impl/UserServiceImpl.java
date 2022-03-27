package com.pascalito.github.githubapi.service.impl;

import com.pascalito.github.githubapi.controller.builder.UserResultBuilder;
import com.pascalito.github.githubapi.controller.model.UserResultDTO;
import com.pascalito.github.githubapi.repository.UserRepository;
import com.pascalito.github.githubapi.repository.entity.UserEntity;
import com.pascalito.github.githubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public List<UserResultDTO> getAllUsers() {
        var users = userRepository.findAll();
        return buildResult(users);
    }

    @Override
    public List<UserResultDTO> getQualifiedUsersByLang(String lang) {
        var users = userRepository.findByLanguage(lang);
        return buildResult(users);
    }

    private List<UserResultDTO> buildResult(List<UserEntity> users) {
        return users.stream()
                .map(user -> UserResultBuilder.buildUserDTO(user,
                        getLangCount(user)))
                .collect(Collectors.toList());
    }

    private Map<String, Long> getLangCount(UserEntity user) {
        var repos = user.getRepos();
        return repos.stream()
                .flatMap(repo -> repo.getLanguages().stream())
                .collect(Collectors.groupingBy(lang -> lang.getName(), Collectors.counting()));
    }
}
