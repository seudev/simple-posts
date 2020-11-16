package com.seudev.simple_posts.module.post.create.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.AppUser;
import com.seudev.simple_posts.model.Post;
import com.seudev.simple_posts.module.post.PostResponse;
import com.seudev.simple_posts.module.post.create.PostCreateRepository;
import com.seudev.simple_posts.module.post.create.PostCreateService;
import com.seudev.simple_posts.util.persistence.EntityChecker;
import com.seudev.simple_posts.module.post.create.PostCreateRequest;

@Transactional
@ApplicationScoped
public class DefaultPostCreateService implements PostCreateService {

    @Inject
    PostCreateRepository postCreateRepository;

    @Inject
    EntityChecker entityChecker;

    @Override
    public PostResponse create(UUID userId, PostCreateRequest request) {
        entityChecker.assertExists(AppUser.class, userId);
        Post post = mapRequestToPost(userId, request);
        postCreateRepository.create(post);
        return new PostResponse(post);
    }

    private Post mapRequestToPost(UUID userId, PostCreateRequest request) {
        AppUser user = new AppUser(userId);
        Post post = new Post(user, request.getMessage(), request.getTags());
        post.setCreatedAt(LocalDateTime.now());
        return post;
    }

}
