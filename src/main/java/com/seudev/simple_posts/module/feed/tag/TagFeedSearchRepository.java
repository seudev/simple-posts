package com.seudev.simple_posts.module.feed.tag;

import com.seudev.simple_posts.module.api.model.SearchResult;
import com.seudev.simple_posts.module.post.PostResponse;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

// Metrics
@Timed(name = "TagFeedSearchRepository.time")
@Metered(name = "TagFeedSearchRepository.frequency")
@ConcurrentGauge(name = "TagFeedSearchRepository.concurrent_count")
public interface TagFeedSearchRepository {

    public SearchResult<PostResponse> search(TagFeedSearchParams params);

}
