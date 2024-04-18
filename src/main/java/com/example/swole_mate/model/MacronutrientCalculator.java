package com.example.swole_mate.model;

public class MacronutrientCalculator {
    public static double[] calculateMacronutrientRatio(String goal) {
        double[] ratio = new double[3]; // Index 0: Carbs, Index 1: Proteins, Index 2: Fats
        if (goal.equalsIgnoreCase("weight loss")) {
            ratio[0] = 0.4; // 40% Carbs
            ratio[1] = 0.4; // 40% Proteins
            ratio[2] = 0.2; // 20% Fats
        } else if (goal.equalsIgnoreCase("muscle gain")) {
            ratio[0] = 0.5; // 50% Carbs
            ratio[1] = 0.3; // 30% Proteins
            ratio[2] = 0.2; // 20% Fats
        } else if (goal.equalsIgnoreCase("maintenance")) {
            ratio[0] = 0.45; // 45% Carbs
            ratio[1] = 0.3;  // 30% Proteins
            ratio[2] = 0.25; // 25% Fats
        }
        return ratio;
    }
}
