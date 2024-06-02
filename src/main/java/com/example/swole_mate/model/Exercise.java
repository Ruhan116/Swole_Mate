package com.example.swole_mate.model;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private int id;
    private String name;
    private String difficulty;
    private List<String> bodyParts;

    public Exercise(int id, String name, String difficulty) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.bodyParts = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<String> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public void addBodyPart(String bodyPart) {
        this.bodyParts.add(bodyPart);
    }
}
