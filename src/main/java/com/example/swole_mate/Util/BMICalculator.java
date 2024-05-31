package com.example.swole_mate.Util;

public class BMICalculator {
    public static double calculateBMI(double weightKg, double heightM) {
        return weightKg / (heightM * heightM);
    }
}

