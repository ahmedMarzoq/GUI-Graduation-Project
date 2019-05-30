/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Flash Tech
 */
public class FXMLDocumentController implements Initializable {
    private String level,spech;
    @FXML
    private ComboBox speCombobox;
    @FXML
    private ComboBox levelsComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button scedButton;

    @FXML
    private void addButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addSectionFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            AddSectionFXMLController con = fxmlLoader.getController();
            con.setSpechLevel(level,spech);
            stage.setScene(new Scene(root1));
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Errore........");
        }
    }

    @FXML
    private void speComboBoxAction(ActionEvent event) {
        levelsComboBox.setDisable(false);
        spech = speCombobox.getSelectionModel().getSelectedItem().toString();
        System.out.println(spech);
    }

    @FXML
    private void levelsComboBoxAction(ActionEvent event) {
        addButton.setDisable(false);
        scedButton.setDisable(false);
        level = levelsComboBox.getSelectionModel().getSelectedItem().toString();
        System.out.println(level);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setOptionsSpeCombobox();
        setOptionslevelsCombobox();
    }

    private void setOptionsSpeCombobox() {
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "علم حاسوب", "تطوير برمجيات",
                        "أنظمة معلومات", "ملتميديا", "حوسبة الهواتف"
                );
        speCombobox.setItems(options);
    }

    private void setOptionslevelsCombobox() {
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "المستوى الأول", "المستوى الثاني",
                        "المستوى الثالث", "المستوى الرابع"
                );
        levelsComboBox.setItems(options);
    }

}
