package com.seudev.simple_posts.module.feed.tag;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.PathParam;

import com.seudev.simple_posts.module.feed.FeedPagedSearchParams;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

public class TagFeedSearchParams extends FeedPagedSearchParams {

    @NotNull
    @Pattern(regexp = "\\w{2,30}")
    @PathParam("tag")
    @Parameter(description = "a blog post tag")
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
