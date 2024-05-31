package com.example.swole_mate.Util;

public class OneRepMaxCalculator {
    public static double calculateOneRepMax(double weightKg, int reps) {
        return weightKg * (1 + reps / 30.0);
    }
}
