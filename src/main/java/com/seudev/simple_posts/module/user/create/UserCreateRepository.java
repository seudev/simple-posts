package com.seudev.simple_posts.module.user.create;

import com.seudev.simple_posts.model.AppUser;

import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

// Metrics
@Timed(name = "UserCreateRepository.time")
@Metered(name = "UserCreateRepository.frequency")
@ConcurrentGauge(name = "UserCreateRepository.concurrent_count")
public interface UserCreateRepository {

    public void create(AppUser user);

    public boolean isUniqueName(String name);

}
