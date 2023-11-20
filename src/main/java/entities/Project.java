package entities;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class Project {
    private final @NotNull Integer id;
    private @NotNull String name;
    private @NotNull LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Project(
            @NotNull Integer id,
            @NotNull String name,
            @NotNull LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @NotNull
    public Integer getId() {
        return this.id;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(@NotNull LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return this.name;
    }
}