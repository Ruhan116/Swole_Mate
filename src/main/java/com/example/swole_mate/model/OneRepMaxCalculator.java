package com.example.swole_mate.model;

public class OneRepMaxCalculator {
    public static double calculateOneRepMax(double weightKg, int reps) {
        return weightKg * (1 + reps / 30.0);
    }
}
