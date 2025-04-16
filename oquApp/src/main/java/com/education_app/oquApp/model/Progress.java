package com.education_app.oquApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "progress", schema = "Education")
@Getter
@Setter
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private int total_tasks;

    @Column(nullable = false)
    private int completed_tasks;

    @Column(nullable = false)
    private double avg_grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getTotal_tasks() {
        return total_tasks;
    }

    public void setTotal_tasks(int total_tasks) {
        this.total_tasks = total_tasks;
    }

    public int getCompleted_tasks() {
        return completed_tasks;
    }

    public void setCompleted_tasks(int completed_tasks) {
        this.completed_tasks = completed_tasks;
    }

    public double getAvg_grade() {
        return avg_grade;
    }

    public void setAvg_grade(double avg_grade) {
        this.avg_grade = avg_grade;
    }
}
