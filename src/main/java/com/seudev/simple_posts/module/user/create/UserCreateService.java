package com.seudev.simple_posts.module.user.create;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.seudev.simple_posts.module.user.UserResponse;

public interface UserCreateService {

    public UserResponse create(@Valid @NotNull UserCreateRequest request);

}
