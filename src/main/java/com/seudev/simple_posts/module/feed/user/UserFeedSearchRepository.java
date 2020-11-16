package com.seudev.simple_posts.module.feed.user;

import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.post.PostResponse;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

// Metrics
@Timed(name = "UserFeedSearchRepository.time")
@Metered(name = "UserFeedSearchRepository.frequency")
@ConcurrentGauge(name = "UserFeedSearchRepository.concurrent_count")
public interface UserFeedSearchRepository {

    public SearchResult<PostResponse> search(UserFeedSearchParams params);

}
