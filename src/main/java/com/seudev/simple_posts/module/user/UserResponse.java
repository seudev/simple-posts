package com.seudev.simple_posts.module.user;

import static com.seudev.simple_posts.module.api.ApiSchemas.CREATED_AT_REF;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.json.bind.annotation.JsonbPropertyOrder;

import com.seudev.simple_posts.util.Identifiable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@JsonbPropertyOrder({ "id", "name", "createdAt" })
@Schema(
    description = "The user schama.",
    example = "{ "
              + "\"id\": \"d216e7a1-6dd2-4b1b-b281-c0d86c190913\", "
              + "\"name\": \"Thomás Sousa Silva\", "
              + "\"createdAt\": \"2020-11-15T19:19:31.003115\""
              + " }")
public class UserResponse implements Identifiable<UUID> {

    private UUID id;

    @Schema(description = "user name")
    private String name;

    @Schema(ref = CREATED_AT_REF)
    private LocalDateTime createdAt;

    public UserResponse(UUID id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return Identifiable.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return Identifiable.equals(UserResponse.class, this, obj);
    }

}
