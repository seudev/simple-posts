package com.seudev.simple_posts.model;

import static com.seudev.simple_posts.model.Post.QUERY_DELETE_POSTS_BY_AUTHOR;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.seudev.simple_posts.util.Identifiable;

@Entity
@Table(name = "post")
@NamedQuery(name = QUERY_DELETE_POSTS_BY_AUTHOR, query = "DELETE FROM Post p WHERE p.author.id = :author")
public class Post implements Serializable, Identifiable<UUID> {

    private static final long serialVersionUID = 1L;

    public static final String QUERY_DELETE_POSTS_BY_AUTHOR = "Post.QUERY_DELETE_POSTS_BY_AUTHOR";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id")
    private AppUser author;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OrderBy("ASC")
    @Column(name = "tag")
    @ElementCollection(fetch = LAZY)
    @CollectionTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"))
    private SortedSet<String> tags;

    public Post() {}

    public Post(AppUser author, String message, SortedSet<String> tags) {
        this.author = author;
        this.message = message;
        this.tags = tags;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }

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

    @Override
    public int hashCode() {
        return Identifiable.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return Identifiable.equals(Post.class, this, obj);
    }

}
