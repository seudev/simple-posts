package com.seudev.simple_posts.module.post;

import static com.seudev.simple_posts.module.api.ApiSchemas.CREATED_AT_REF;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import javax.json.bind.annotation.JsonbPropertyOrder;

import com.seudev.simple_posts.model.Post;
import com.seudev.simple_posts.util.Identifiable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@JsonbPropertyOrder({ "id", "message", "tags", "createdAt" })
@Schema(
    description = "The blog post schema.",
    example = "{ "
              + "\"id\": \"d216e7a1-6dd2-4b1b-b281-c0d86c190913\", "
              + "\"message\": \"Do you want create a beautiful API? Send-me a email thomas@seudev.com perhaps I can help you!\", "
              + "\"tags\": [ \"api\", \"rest\", \"seudev\", \"ThomasSousa96\" ], "
              + "\"createdAt\": \"2020-11-15T19:19:31.003115\""
              + " }")
public class PostResponse implements Identifiable<UUID> {

    private UUID id;

    @Schema(description = "blog post content")
    private String message;

    @Schema(description = "blog post tags")
    private Collection<String> tags;

    @Schema(ref = CREATED_AT_REF)
    private LocalDateTime createdAt;

    public PostResponse(Post post) {
        this(post.getId(), post.getMessage(), post.getTags(), post.getCreatedAt());
    }

    public PostResponse(UUID id, String message, Collection<String> tags, LocalDateTime createdAt) {
        this.id = id;
        this.message = message;
        this.tags = tags;
        this.createdAt = createdAt;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
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
        return Identifiable.equals(PostResponse.class, this, obj);
    }

}
