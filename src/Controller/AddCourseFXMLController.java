/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DbCall;
import Model.Levels;
import Model.Majors;
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
public class AddCourseFXMLController implements Initializable {

    /**
     * VARIABLES
     */
    DbCall call = DbCall.getDbCall();
    ArrayList<Levels> levelsList = new ArrayList<>();
    ArrayList<Majors> majorsList = new ArrayList<>();
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
     * ADD BUTTON ACTION
     */
    @FXML
    private void addButtonAction(ActionEvent event) throws SQLException {
        add();
    }

    /**
     * ADD QUERY
     */
    private void add() {
        int canDevide;
        String name = courseName.getText();
        System.out.println("MAJOR :: " + majorValue
                + " | LEVEL :: " + levelValue
                + " | COURSE NAME :: " + courseName.getText()
                + " | COURSE NUMBER :: " + courseNumber.getText()
                + " | COURSE TYPE :: " + courseType.getSelectionModel().getSelectedIndex()
                + " | COURSE CRIDET :: " + courseCridetHourse.getText()
                + " | COURSE A :: " + courseAhoures.getText()
                + " | COURSE DEVIDE :: " + courseDevide.getSelectionModel().getSelectedIndex()
                + " | COURSE SEMESTER :: " + courseSemester.getSelectionModel().getSelectedIndex()
        );
        if (courseDevide.getSelectionModel().getSelectedIndex() == 0) {
            canDevide = 1;
        } else {
            canDevide = 0;
        }
        int i = call.getExecuteUpdate("INSERT INTO `courses` (`name`, `major_id`, `level_number`, `semester`, `course_number`, `credit_hours`,`actual_hours` , `can_devide`,`type`) VALUES ('"
                + name + "','"
                + (majorValue + 1) + "','"
                + (levelValue + 1) + "','"
                + (courseSemester.getSelectionModel().getSelectedIndex() + 1) + "','"
                + courseNumber.getText() + "','"
                + Integer.parseInt(courseCridetHourse.getText()) + "','"
                + Integer.parseInt(courseAhoures.getText()) + "','"
                + canDevide + "','"
                + courseType.getSelectionModel().getSelectedIndex()
                + "')");
        if (i == 1) {
            new Alert(Alert.AlertType.INFORMATION, "لقد تمت الإضافة بنجاح").show();
            final Stage stage = (Stage) courseSemester.getScene().getWindow();
            stage.close();
        } else {
            new Alert(Alert.AlertType.ERROR, "لقد حدث خطا").show();
        }
    }

    /**
     * GET MAJOR AND LEVEL FROM MAIN SCREEN
     */
    void getMajorAndLevel(int major, int level) {
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
