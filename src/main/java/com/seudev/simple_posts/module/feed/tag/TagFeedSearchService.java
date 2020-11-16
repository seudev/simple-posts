package com.seudev.simple_posts.module.feed.tag;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.post.PostResponse;

public interface TagFeedSearchService {

    public SearchResult<PostResponse> search(@Valid @NotNull TagFeedSearchParams params);

}
