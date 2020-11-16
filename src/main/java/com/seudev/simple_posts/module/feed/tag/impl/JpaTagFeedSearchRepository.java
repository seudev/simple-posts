package com.seudev.simple_posts.module.feed.tag.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.Post;
import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.feed.tag.TagFeedSearchParams;
import com.seudev.simple_posts.module.feed.tag.TagFeedSearchRepository;
import com.seudev.simple_posts.module.post.PostResponse;

@Transactional
@ApplicationScoped
public class JpaTagFeedSearchRepository implements TagFeedSearchRepository {

    @Inject
    EntityManager entityManager;

    @Override
    public SearchResult<PostResponse> search(TagFeedSearchParams params) {
        List<PostResponse> list = entityManager.createNamedQuery(Post.QUERY_POSTS_BY_TAG, Post.class)
            .setParameter("tag", params.getTag())
            .setFirstResult(params.getOffset())
            .setMaxResults(params.getLimit())
            .getResultStream()
            .map(PostResponse::new)
            .collect(toList());

        Long count = entityManager.createNamedQuery(Post.QUERY_COUNT_POSTS_OF_TAG, Long.class)
            .setParameter("tag", params.getTag())
            .getSingleResult();

        return SearchResult.from(list, count);
    }

}
