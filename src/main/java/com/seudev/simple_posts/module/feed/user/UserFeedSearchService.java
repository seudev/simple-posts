package com.seudev.simple_posts.module.feed.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.post.PostResponse;

public interface UserFeedSearchService {

    public SearchResult<PostResponse> search(@Valid @NotNull UserFeedSearchParams params);

}
