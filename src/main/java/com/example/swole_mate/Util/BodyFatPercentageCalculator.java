package com.example.swole_mate.Util;

public class BodyFatPercentageCalculator {
    public static double calculateBodyFatPercentage(double waistCm, double hipCm, double neckCm, double heightCm, String gender) {
        double ratio;
        if (gender.equalsIgnoreCase("male")) {
            ratio = waistCm / heightCm;
        } else {
            ratio = (waistCm + hipCm - neckCm) / heightCm;
        }
        return (495 / (1.0324 - 0.19077 * Math.log10(ratio) + 0.15456 * Math.log10(heightCm))) - 450;
    }
}
