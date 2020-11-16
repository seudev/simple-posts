package com.seudev.simple_posts.module.post.create;

import com.seudev.simple_posts.model.Post;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

// Metrics
@Timed(name = "CreatePostRepository.time")
@Metered(name = "CreatePostRepository.frequency")
@ConcurrentGauge(name = "CreatePostRepository.concurrent_count")
public interface PostCreateRepository {

    public void create(Post post);

}
