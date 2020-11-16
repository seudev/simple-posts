package com.seudev.simple_posts.module.post.create.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.Post;
import com.seudev.simple_posts.module.post.create.PostCreateRepository;

import org.eclipse.microprofile.faulttolerance.Retry;

@Transactional
@ApplicationScoped
public class JpaPostCreateRepository implements PostCreateRepository {

    @Inject
    EntityManager entityManager;

    @Override
    @Retry(abortOn = EntityExistsException.class)
    public void create(Post post) {
        entityManager.persist(post);
    }

}
