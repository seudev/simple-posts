package com.seudev.simple_posts.module.feed.user.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.Post;
import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.feed.user.UserFeedSearchParams;
import com.seudev.simple_posts.module.feed.user.UserFeedSearchRepository;
import com.seudev.simple_posts.module.post.PostResponse;

@Transactional
@ApplicationScoped
public class JpaUserFeedSearchRepository implements UserFeedSearchRepository {

    @Inject
    EntityManager entityManager;

    @Override
    public SearchResult<PostResponse> search(UserFeedSearchParams params) {
        List<PostResponse> list = entityManager.createNamedQuery(Post.QUERY_POSTS_BY_AUTHOR, Post.class)
            .setParameter("author", params.getUserId())
            .setFirstResult(params.getOffset())
            .setMaxResults(params.getLimit())
            .getResultStream()
            .map(PostResponse::new)
            .collect(toList());

        Long count = entityManager.createNamedQuery(Post.QUERY_COUNT_POSTS_OF_AUTHOR, Long.class)
            .setParameter("author", params.getUserId())
            .getSingleResult();

        return SearchResult.from(list, count);
    }

}
