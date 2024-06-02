package com.example.swole_mate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DietTracker {
    @FXML
    private TextField searchBox;

    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] possibleSuggestions = {"Daal", "Chicken Fry", "Chicken Curry"};
    private Set<String> _possibleSuggestions = new HashSet<>(Arrays.asList(possibleSuggestions));

    @FXML
    public void initialize() {
        autoCompletionBinding = TextFields.bindAutoCompletion(searchBox, _possibleSuggestions);
    }
}
