package com.seudev.simple_posts.module.user.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "The request schema to create a user.", example = "{ \"name\": \"Thomás Sousa Silva\" }")
public class UserCreateRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    @Schema(description = "user name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
