package com.seudev.simple_posts.model;

import static com.seudev.simple_posts.model.AppUser.QUERY_DELETE_USER_BY_ID;
import static com.seudev.simple_posts.model.AppUser.QUERY_IS_UNIQUE_NAME;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.seudev.simple_posts.util.Identifiable;

@Entity
@Table(name = "app_user")
@NamedQuery(name = QUERY_IS_UNIQUE_NAME, query = "SELECT COUNT(u) = 0 FROM AppUser u WHERE LOWER(u.name) = :name")
@NamedQuery(name = QUERY_DELETE_USER_BY_ID, query = "DELETE FROM AppUser u WHERE u.id = :id")
public class AppUser implements Serializable, Identifiable<UUID> {

    private static final long serialVersionUID = 1L;

    public static final String QUERY_IS_UNIQUE_NAME = "AppUser.QUERY_IS_UNIQUE_NAME";

    public static final String QUERY_DELETE_USER_BY_ID = "AppUser.QUERY_DELETE_USER_BY_ID";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public AppUser() {}

    public AppUser(UUID id) {
        this.id = id;
    }

    public AppUser(String name) {
        this.name = name;
    }

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
        return Identifiable.equals(AppUser.class, this, obj);
    }

}
