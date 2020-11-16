package com.seudev.simple_posts.module.post.create;

import java.util.SortedSet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(
    description = "The request schema to create a blog post.",
    example = "{ "
              + "\"message\": \"Do you want create a beautiful API? Send a email to thomas@seudev.com, perhaps I can help you!\", "
              + "\"tags\": [ \"api\", \"rest\", \"seudev\", \"ThomasSousa96\" ] "
              + " }")
public class PostCreateRequest {

    @NotBlank
    @Size(max = 150)
    @Schema(description = "blog post content")
    private String message;

    @Size(max = 5)
    @Schema(description = "blog post tags")
    private SortedSet<@Pattern(regexp = "\\w{2,30}") String> tags;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SortedSet<String> getTags() {
        return tags;
    }

    public void setTags(SortedSet<String> tags) {
        this.tags = tags;
    }

}
