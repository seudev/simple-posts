package com.seudev.simple_posts.module.user.create.impl;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.AppUser;
import com.seudev.simple_posts.module.api.ValidationException;
import com.seudev.simple_posts.module.api.model.ErrorMessage;
import com.seudev.simple_posts.module.user.UserResponse;
import com.seudev.simple_posts.module.user.create.UserCreateRepository;
import com.seudev.simple_posts.module.user.create.UserCreateService;
import com.seudev.simple_posts.module.user.create.UserCreateRequest;

@Transactional
@ApplicationScoped
public class DefaultUserCreateService implements UserCreateService {

    @Inject
    UserCreateRepository userCreateRepository;

    @Override
    public UserResponse create(UserCreateRequest request) {
        validate(request);
        AppUser user = mapRequestToUser(request);
        userCreateRepository.create(user);
        return mapUserToResponse(user);
    }

    private void validate(UserCreateRequest request) {
        if (!userCreateRepository.isUniqueName(request.getName())) {
            throw new ValidationException(ErrorMessage.propertyError("/name", "must be unique"));
        }
    }

    private AppUser mapRequestToUser(UserCreateRequest request) {
        AppUser user = new AppUser(request.getName());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    private UserResponse mapUserToResponse(AppUser user) {
        return new UserResponse(user.getId(), user.getName(), user.getCreatedAt());
    }

}
