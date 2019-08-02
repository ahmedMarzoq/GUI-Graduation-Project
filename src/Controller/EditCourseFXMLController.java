/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CourseTable;
import Model.DbCall;
import Model.Levels;
import Model.Majors;
import Model.sectionTable;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Flash Tech
 */
public class EditCourseFXMLController implements Initializable {

    /**
     * VARIABLES
     */
    DbCall call = DbCall.getDbCall();
    ArrayList<Levels> levelsList = new ArrayList<>();
    ArrayList<Majors> majorsList = new ArrayList<>();
    ObservableList<CourseTable> row;
    private int majorValue, levelValue;

    @FXML
    Label majorLabel;
    @FXML
    Label levelLabel;
    @FXML
    TextField courseName;
    @FXML
    TextField courseNumber;
    @FXML
    ComboBox courseType;
    @FXML
    TextField courseCridetHourse;
    @FXML
    TextField courseAhoures;
    @FXML
    ComboBox courseDevide;
    @FXML
    ComboBox courseSemester;

    /**
     * EXIT BUTTON ACTION
     */
    @FXML
    private void exitButtonAction(ActionEvent event) throws SQLException {
        System.out.println("exit");
        final Stage stage = (Stage) courseSemester.getScene().getWindow();
        stage.close();
    }

    /**
     * EDIT BUTTON ACTION
     */
    @FXML
    private void editButtonAction(ActionEvent event) throws SQLException {
        int i = call.getExecuteUpdate("UPDATE `courses` SET `name`= '" + courseName.getText()
                + "',`type`= '" + courseType.getSelectionModel().getSelectedIndex()
                + "',`semester` = '" + (courseSemester.getSelectionModel().getSelectedIndex() + 1)
                + "', `course_number` = '" + courseNumber.getText()
                + "', `credit_hours` = '" + Integer.parseInt(courseCridetHourse.getText())
                + "', `actual_hours` = '" + Integer.parseInt(courseAhoures.getText())
                + "', `can_devide` = '" + (courseDevide.getSelectionModel().getSelectedIndex() == 0 ? 1 : 0)
                + "' WHERE `id` = '" + row.get(0).getId()
                + "'");
        if (i == -1) {
            new Alert(Alert.AlertType.WARNING, "حصل خطا !").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "تمت العملية بنجاح").show();
            final Stage stage = (Stage) courseSemester.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * FILL MAJOR LIST
     */
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

    /**
     * FILL LEVEL LIST
     */
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

    /**
     * GET INFORMATION FROM MAIN SCREEN
     */
    void setRow(int major, int level, ObservableList<CourseTable> row) {
        this.row = row;
        int canDevide;
        this.majorLabel.setText(majorsList.get(major).getNickName());
        this.levelLabel.setText(levelsList.get(level).getNickName());
        this.majorValue = major;
        this.levelValue = level;
        ObservableList<String> courseTypeData = FXCollections.observableArrayList("عملي", "نظري");
        courseType.setItems(courseTypeData);
        ObservableList<String> devideData = FXCollections.observableArrayList("نعم", "لا");
        courseDevide.setItems(devideData);
        ObservableList<String> courseSemesterData = FXCollections.observableArrayList("الفصل الاول", "الفصل الثاني");
        courseSemester.setItems(courseSemesterData);
        courseName.setText(row.get(0).getName());
        courseNumber.setText(row.get(0).getCourse_number());
        courseType.getSelectionModel().select(row.get(0).getType());
        courseCridetHourse.setText(String.valueOf(row.get(0).getCredit_hours()));
        courseAhoures.setText(String.valueOf(row.get(0).getActual_hours()));
        courseDevide.getSelectionModel().select(row.get(0).getCan_devide());
        courseSemester.getSelectionModel().select(row.get(0).getSemester());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getMajors(call);
            getLevels(call);
        } catch (SQLException ex) {
            Logger.getLogger(AddCourseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
