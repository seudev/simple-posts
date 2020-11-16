package com.seudev.simple_posts.module.user.create.impl;

import static javax.persistence.FlushModeType.COMMIT;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.AppUser;
import com.seudev.simple_posts.module.user.create.UserCreateRepository;

import org.eclipse.microprofile.faulttolerance.Retry;

@Transactional
@ApplicationScoped
public class JpaUserCreateRepository implements UserCreateRepository {

    @Inject
    EntityManager entityManager;

    @Override
    @Retry(abortOn = EntityExistsException.class)
    public void create(AppUser user) {
        entityManager.persist(user);
    }

    @Override
    @Retry(retryOn = PersistenceException.class)
    public boolean isUniqueName(String name) {
        return entityManager.createNamedQuery(AppUser.QUERY_IS_UNIQUE_NAME, Boolean.class)
            .setParameter("name", name.toLowerCase())
            .setFlushMode(COMMIT)
            .getSingleResult();
    }

}
