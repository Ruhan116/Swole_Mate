package com.example.swole_mate.Util;

public class BMRCalculator {
    public static double calculateBMR(double weightKg, double heightCm, int age, String gender) {
        double bmr;
        age = 25;
        if (gender.equalsIgnoreCase("male")) {
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age + 5;
        } else {
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age - 161;
        }
        return bmr;
    }
}
