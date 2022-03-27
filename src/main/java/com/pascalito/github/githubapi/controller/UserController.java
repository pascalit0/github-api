package com.pascalito.github.githubapi.controller;

import com.pascalito.github.githubapi.controller.model.UserResultDTO;
import com.pascalito.github.githubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/userprojects")
    public ResponseEntity<List<UserResultDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/userprojects/lang/{name}")
    public ResponseEntity<List<UserResultDTO>> getUserProjectsByLangName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getQualifiedUsersByLang(name));
    }
}
