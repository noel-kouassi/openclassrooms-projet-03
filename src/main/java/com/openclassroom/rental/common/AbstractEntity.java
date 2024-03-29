package com.openclassroom.rental.common;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import static java.lang.System.currentTimeMillis;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    public void onCreate() {
        Timestamp timestamp = new Timestamp(currentTimeMillis());
        this.createdAt = timestamp;
        this.updatedAt = timestamp;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Timestamp(currentTimeMillis());
    }
}
