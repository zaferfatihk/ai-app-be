package com.ai_app_be.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Permission extends BaseModel {
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(length = 200)
    private String description;

    @Column
    private boolean enabled = true;

    @ManyToMany(mappedBy = "permissions")
    private java.util.Set<Role> roles = new java.util.HashSet<>();

    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }

}