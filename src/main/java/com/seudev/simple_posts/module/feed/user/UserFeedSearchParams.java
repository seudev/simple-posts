package com.seudev.simple_posts.module.feed.user;

import static com.seudev.simple_posts.module.api.ApiParameters.USER_ID;
import static com.seudev.simple_posts.module.api.ApiParameters.USER_ID_REF;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import com.seudev.simple_posts.module.feed.FeedPagedSearchParams;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

public class UserFeedSearchParams extends FeedPagedSearchParams {

    @NotNull
    @PathParam(USER_ID)
    @Parameter(ref = USER_ID_REF)
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
