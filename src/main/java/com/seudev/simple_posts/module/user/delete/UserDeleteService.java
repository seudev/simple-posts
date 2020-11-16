package com.seudev.simple_posts.module.user.delete;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public interface UserDeleteService {

    public void delete(@NotNull UUID id);

}
