package com.seudev.simple_posts.module.feed.tag.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.seudev.simple_posts.module.api.EntityNotFoundException;
import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.feed.tag.TagFeedSearchParams;
import com.seudev.simple_posts.module.feed.tag.TagFeedSearchRepository;
import com.seudev.simple_posts.module.feed.tag.TagFeedSearchService;
import com.seudev.simple_posts.module.post.PostResponse;

@Transactional
@ApplicationScoped
public class DefaultTagFeedSearchService implements TagFeedSearchService {

    @Inject
    TagFeedSearchRepository tagFeedSearchRepository;

    @Override
    public SearchResult<PostResponse> search(TagFeedSearchParams params) {
        SearchResult<PostResponse> result = tagFeedSearchRepository.search(params);

        if (result.getCount().get() > 0) {
            return result;
        }
        throw new EntityNotFoundException("tag", params.getTag());
    }

}
