package com.example.swole_mate.model;

public class CalorieCalculator {
    public static double calculateCaloriesBurned(double weightKg, double durationHours, double MET) {
        return MET * weightKg * durationHours;
    }
}

