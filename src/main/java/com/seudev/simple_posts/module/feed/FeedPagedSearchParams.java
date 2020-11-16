package com.seudev.simple_posts.module.feed;

import static com.seudev.simple_posts.module.api.ApiParameters.LIMIT;
import static com.seudev.simple_posts.module.api.ApiParameters.LIMIT_REF;
import static com.seudev.simple_posts.module.api.ApiParameters.OFFSET;
import static com.seudev.simple_posts.module.api.ApiParameters.OFFSET_REF;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import com.seudev.simple_posts.module.api.model.PagedSearchParams;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

public class FeedPagedSearchParams implements PagedSearchParams {

    @Min(0)
    @DefaultValue("0")
    @QueryParam(OFFSET)
    @Parameter(ref = OFFSET_REF)
    private int offset;

    @Min(0)
    @Max(10)
    @DefaultValue("10")
    @QueryParam(LIMIT)
    @Parameter(ref = LIMIT_REF)
    private int limit;

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }

}
