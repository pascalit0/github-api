package com.pascalito.github.githubapi.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepoDTO {
    private String id;
    private String name;
    private String languages_url;
}
