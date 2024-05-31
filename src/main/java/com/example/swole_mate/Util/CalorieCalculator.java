package com.example.swole_mate.Util;

public class CalorieCalculator {
    public static double calculateCaloriesBurned(double weightKg, double durationHours, double MET) {
        return MET * weightKg * durationHours;
    }
}

