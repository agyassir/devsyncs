package com.example.devs.Entity;

import com.example.devs.Entity.Enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCompleted;
    @ManyToOne
    private User assignedTo;




    public Task(String title, String description, Status status, LocalDate dueDate, LocalDate startDate, LocalDate endDate, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCompleted = isCompleted;
    }

    @ManyToMany
    @JoinTable(
            name = "task_tag",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )


    private List<Tag> tags;

    public Task() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dueDate=" + dueDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isCompleted=" + isCompleted +
                ", assignedTo=" + assignedTo +
                '}';
    }
}
