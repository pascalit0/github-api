package com.pascalito.github.githubapi.service;

import com.pascalito.github.githubapi.controller.model.UserResultDTO;

import java.util.List;

public interface UserService {
    List<UserResultDTO> getAllUsers();
    List<UserResultDTO> getQualifiedUsersByLang(String lang);
}
