package com.seudev.simple_posts.module.api.model;

public interface PagedSearchParams {

    public int getOffset();

    public int getLimit();

    public void setOffset(int offset);

    public void setLimit(int limit);

}
