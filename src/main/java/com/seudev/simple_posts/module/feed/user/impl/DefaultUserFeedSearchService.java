package com.seudev.simple_posts.module.feed.user.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.seudev.simple_posts.model.AppUser;
import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.feed.user.UserFeedSearchParams;
import com.seudev.simple_posts.module.feed.user.UserFeedSearchRepository;
import com.seudev.simple_posts.module.feed.user.UserFeedSearchService;
import com.seudev.simple_posts.module.post.PostResponse;
import com.seudev.simple_posts.util.persistence.EntityChecker;

@Transactional
@ApplicationScoped
public class DefaultUserFeedSearchService implements UserFeedSearchService {

    @Inject
    UserFeedSearchRepository userFeedSearchRepository;

    @Inject
    EntityChecker entityChecker;

    @Override
    public SearchResult<PostResponse> search(UserFeedSearchParams params) {
        entityChecker.assertExists(AppUser.class, params.getUserId());
        return userFeedSearchRepository.search(params);
    }

}
