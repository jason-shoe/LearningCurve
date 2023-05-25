package com.example.LearningCurve.models;

public enum DifficultyLevel {
    FIRST_GRADE("1st Grade"),
    SECOND_GRADE("2nd Grade"),
    THIRD_GRADE("3rd Grade"),
    FOURTH_GRADE("4th Grade"),
    FIFTH_GRADE("5th Grade"),
    SIXTH_GRADE("6th Grade"),
    SEVENTH_GRADE("7th Grade"),
    EIGTH_GRADE("8th Grade"),
    NINTH_GRADE("9th Grade"),
    TENTH_GRADE("10th Grade"),
    ELEVENTH_GRADE("11th Grade"),
    TWELFTH_GRADE("12th Grade");

    public final String label;

    private DifficultyLevel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
