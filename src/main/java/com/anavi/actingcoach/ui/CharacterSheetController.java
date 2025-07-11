package com.anavi.actingcoach.ui;

import com.anavi.actingcoach.Actor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CharacterSheetController {

    @FXML
    private TextField characterNameField;
    @FXML
    private ComboBox<Actor> actorComboBox;
    @FXML
    private TextArea personalityTraitsArea;
    @FXML
    private TextArea physicalTraitsArea;
    @FXML
    private TextArea backgroundArea;
    @FXML
    private TextArea motivationArea;
    @FXML
    private TextArea notesArea;

    @FXML
    private void handleSave() {
        String name = characterNameField.getText();

        // Replace with real save logic later
        System.out.println("Saving character: " + name);
    }

    @FXML
    private void handleClear() {
        characterNameField.clear();
        actorComboBox.getSelectionModel().clearSelection();
        personalityTraitsArea.clear();
        physicalTraitsArea.clear();
        backgroundArea.clear();
        motivationArea.clear();
        notesArea.clear();
    }

}
