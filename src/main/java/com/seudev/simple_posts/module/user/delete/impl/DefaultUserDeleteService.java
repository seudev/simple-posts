package com.seudev.simple_posts.module.user.delete.impl;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.AppUser;
import com.seudev.simple_posts.module.user.delete.UserDeleteRepository;
import com.seudev.simple_posts.module.user.delete.UserDeleteService;
import com.seudev.simple_posts.util.persistence.EntityChecker;

@Transactional
@ApplicationScoped
public class DefaultUserDeleteService implements UserDeleteService {

    @Inject
    UserDeleteRepository userDeleteRepository;

    @Inject
    EntityChecker entityChecker;

    @Override
    public void delete(UUID id) {
        entityChecker.assertExists(AppUser.class, id);
        userDeleteRepository.delete(id);
    }

}
