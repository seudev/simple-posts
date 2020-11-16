package com.seudev.simple_posts.module.user.delete.impl;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.AppUser;
import com.seudev.simple_posts.model.Post;
import com.seudev.simple_posts.module.user.delete.UserDeleteRepository;

import org.eclipse.microprofile.faulttolerance.Retry;

@Transactional
@ApplicationScoped
public class JpaUserDeleteRepository implements UserDeleteRepository {

    @Inject
    EntityManager entityManager;

    @Override
    @Retry(retryOn = PersistenceException.class)
    public void delete(UUID id) {
        entityManager.createNamedQuery(Post.QUERY_DELETE_POSTS_BY_AUTHOR)
            .setParameter("author", id)
            .executeUpdate();

        entityManager.createNamedQuery(AppUser.QUERY_DELETE_USER_BY_ID)
            .setParameter("id", id)
            .executeUpdate();
    }

}
