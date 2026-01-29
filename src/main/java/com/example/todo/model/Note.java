package com.example.todo.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name ="note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Highly recommended for H2
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String title;
    
    private String description;
    
    private String date;

    // private String color;

    // Empty Constructor (Required by JPA)
    public Note() {}

    //  Constructor with no id
    public Note(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getDueStatus() {
        if (this.date == null) return "No date set";

        long days = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.parse(this.date));

        if (days == 0) return "Due today!";
        if (days > 0) return "Due in: " + days + (days == 1 ? " day" : " days");
        
        // Math.abs() works perfectly here in pure Java
        return "Overdue by: " + Math.abs(days) + (Math.abs(days) == 1 ? " day" : " days");
    }

    public String getNoteColor() {
        if (this.date == null) return "#FFF";

        long days = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.parse(this.date));

        if (days == 0) return "#ff8800"; // Orange for due today
        else if(days > 0 && days <=3) return "#FFFF00"; // Yellow for due soon
        else if (days > 3) return "#FFF"; // White for due in the future

        // Math.abs() works perfectly here in pure Java
        return "#FF0000"; // Red for overdue
    }

    // 3. Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // public String getColor() {
    //     return color;
    // }

    // public void setColor(String color) {
    //     this.color = color;
    // }
}
