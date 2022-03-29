package com.pascalito.github.githubapi.controller;

import com.pascalito.github.githubapi.service.GitHubImporterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GitHubImportController {

    private final GitHubImporterService gitHubImporterService;

    @GetMapping("/import")
    public ResponseEntity importGitHubUsers() {
        gitHubImporterService.importUsersFromGitHub();
        return ResponseEntity.ok("Import done.");
    }
}
