package com.seudev.simple_posts.util.persistence;

import com.seudev.simple_posts.module.api.EntityNotFoundException;

public interface EntityChecker {

    public <T> boolean exists(Class<T> entityClass, Object id);

    public default void assertExists(Class<?> entityClass, Object id) {
        if (!exists(entityClass, id)) {
            throw new EntityNotFoundException(entityClass, id);
        }
    }

}
