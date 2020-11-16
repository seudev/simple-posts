package com.seudev.simple_posts.util.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class JpaEntityChecker implements EntityChecker {

    @Inject
    EntityManager entityManager;

    @Override
    public <T> boolean exists(Class<T> entityClass, Object id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Boolean> q = cb.createQuery(Boolean.class);
        Root<T> root = q.from(entityClass);

        CriteriaQuery<Boolean> query = q.select(cb.greaterThan(cb.count(root), 0L))
            .where(cb.equal(root.get("id"), id));

        return entityManager.createQuery(query).getSingleResult();
    }

}
