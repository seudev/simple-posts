package com.seudev.simple_posts.module.user.delete;

import java.util.UUID;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

// Metrics
@Timed(name = "UserDeleteRepository.time")
@Metered(name = "UserDeleteRepository.frequency")
@ConcurrentGauge(name = "UserDeleteRepository.concurrent_count")
public interface UserDeleteRepository {

    public void delete(UUID id);

}
