package com.seudev.simple_posts.module.post.create;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.seudev.simple_posts.module.post.PostResponse;

public interface PostCreateService {

    public PostResponse create(@NotNull UUID userId, @Valid @NotNull PostCreateRequest request);

}
