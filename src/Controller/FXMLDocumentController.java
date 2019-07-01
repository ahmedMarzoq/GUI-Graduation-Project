/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.AddSectionFXMLController;
import Model.Course;
import Model.DbCall;
import Model.Levels;
import Model.Majors;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private int level,spech;
    DbCall call = DbCall.getDbCall();
    ArrayList<Levels> levelsList = new ArrayList<>();
    ArrayList<Majors> majorsList = new ArrayList<>();

    @FXML
    private ComboBox speCombobox;
    @FXML
    private ComboBox levelsComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button scedButton;

    @FXML
    private void addButtonAction(ActionEvent event) throws SQLException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/addSectionFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            AddSectionFXMLController con = fxmlLoader.getController();
            con.setSpechLevel(level, spech);
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
        spech = speCombobox.getSelectionModel().getSelectedIndex();
        System.out.println(spech);
    }

    @FXML
    private void levelsComboBoxAction(ActionEvent event) {
        addButton.setDisable(false);
        scedButton.setDisable(false);
        level = levelsComboBox.getSelectionModel().getSelectedIndex();
        System.out.println(level);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setOptionsSpeCombobox();
            setOptionslevelsCombobox();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//========================================================================================
//                               **** getMajors ****    
//========================================================================================
    private ArrayList getMajors(DbCall call) throws SQLException {

        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from majors");
        while (resultSet.next()) {

            Majors majors = new Majors();

            majors.setId(resultSet.getInt("id"));
            majors.setName(resultSet.getString("name"));
            majors.setNickName(resultSet.getString("nickName"));

            majorsList.add(majors);
        }
        return majorsList;
    }
//========================================================================================
//                               **** getLevels ****    
//========================================================================================
    private ArrayList getLevels(DbCall call) throws SQLException {

        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from levels");

        

        while (resultSet.next()) {

            Levels levels = new Levels();

            levels.setId(resultSet.getInt("id"));
            levels.setNickName(resultSet.getString("nickName"));

            levelsList.add(levels);
        }
        return levelsList;
    }

    private void setOptionsSpeCombobox() throws SQLException {
//        ObservableList<String> options
//                = FXCollections.observableArrayList(
//                        "علم حاسوب", "تطوير برمجيات",
//                        "أنظمة معلومات", "ملتميديا", "حوسبة الهواتف"
//                );
//        speCombobox.setItems(options);
        getMajors(call);
        ObservableList<String> data = FXCollections.observableArrayList(); //List of String
//        ArrayList<Majors> a = getMajors(call);
        for (int i = 0; i < majorsList.size(); i++) {
            System.out.println(majorsList.get(i));
            data.add(majorsList.get(i).getNickName());
        }
        speCombobox.setItems(data);
    }

    private void setOptionslevelsCombobox() throws SQLException {
//        ObservableList<String> options
//                = FXCollections.observableArrayList(
//                        "1", "2",
//                        "3", "4"
//                );
        ObservableList<String> data = FXCollections.observableArrayList(); //List of String
        ArrayList<Levels> a = getLevels(call);
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
            data.add(a.get(i).getNickName());
        }
        levelsComboBox.setItems(data);
    }

}
