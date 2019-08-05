package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.Course;
import Model.DbCall;
import Model.Levels;
import Model.Majors;
import Model.Teacher;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Flash Tech
 */
public class AddSectionFXMLController implements Initializable {

    DbCall call = DbCall.getDbCall();
    private int spechValue, levelValue, semesterValue;
    ArrayList<Levels> levelsList = new ArrayList<>();
    ArrayList<Majors> majorsList = new ArrayList<>();
    ArrayList<Teacher> teachersList = new ArrayList<>();
    ArrayList<Course> courseList = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label spech;
    @FXML
    private Label level;
    @FXML
    private Label semester;
    @FXML
    private ComboBox courseComboBox, teacherComboBox, genderComboBox;
    @FXML
    private TextField sectionNumberTxt, sectionstdNumberTxt;

    @FXML
    private void courseComboBoxAction(ActionEvent event) throws SQLException {
        //setOptionsCourseComboBox();

        System.out.println("ahmed");
    }

    @FXML
    private void exitButtonAction(ActionEvent event) throws SQLException {
        System.out.println("exit");
        final Stage stage = (Stage) spech.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        try {
//            ArrayList<Course> a = getCourses(call, 2, 2);
            //System.out.println(Arrays.toString(a.toArray()));
            setSection(call);
        } catch (SQLException ex) {
            Logger.getLogger(AddSectionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// StartSpam -------------------------------------------------------------------------------------------

    private int getLevelId(String level) throws SQLException {
        DbCall call = DbCall.getDbCall();
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("SELECT * FROM `majors` where name='" + level + "'");
        int id = 1;
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            //id = resultSet.getInt("id");
        }
        return id;
    }
// spam -------------------------------------------------------------------------------------------

    private int getSpechId(String spech) throws SQLException {
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from majors where name= '" + spech + "'");
        //int id = resultSet.getInt("id");
        int id = 0;
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            id = resultSet.getInt("id");
        }
        return id;
    }
// EndSpam -------------------------------------------------------------------------------------------

    private void setOptionsTeacherComboBox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList(); //List of String
        teachersList = getTeachers(call);
        for (int i = 0; i < teachersList.size(); i++) {
            Teacher teacher = teachersList.get(i);
            System.out.println(teacher.getName());
            data.add(teacher.getName());
        }
        teacherComboBox.setItems(data);
        System.out.println(Arrays.asList(data).toString());
    }

    private void setOptionsCourseComboBox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList(); //List of String
        courseList = getCourses(call, spechValue + 1, levelValue + 1, semesterValue + 1);
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            System.out.println(course.getName());
            data.add(course.getName());
        }
        courseComboBox.setItems(data);
        System.out.println((spechValue + 1) + " List  " + (levelValue + 1));
    }

    private void setOptionsGenderComboBox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList("ذكر", "انثى"); //List of String
        genderComboBox.setItems(data);
    }

//========================================================================================
//                               **** Get Majors ****    
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
//                               **** Get Levels ****    
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

//========================================================================================
//                               **** Get Courses ****    
//========================================================================================
    private ArrayList getCourses(DbCall call, int major_id, int level_number, int semester) throws SQLException {

        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from courses where major_id= " + major_id + " AND level_number = " + level_number + " AND semester = " + semester);

        while (resultSet.next()) {

            Course course = new Course();

            course.setId(resultSet.getInt("id"));
            course.setName(resultSet.getString("name"));
            course.setLevel_number(resultSet.getInt("level_number"));
            course.setCredit_hours(resultSet.getInt("credit_hours"));
            course.setActual_hours(resultSet.getInt("actual_hours"));
            course.setCan_devide(resultSet.getInt("can_devide"));

            courseList.add(course);
        }
        return courseList;
    }

//========================================================================================
//                               **** Get Teachers ****    
//========================================================================================
    private ArrayList getTeachers(DbCall call) throws SQLException {

        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from teachers");

        while (resultSet.next()) {

            Teacher teacher = new Teacher();

            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));

            teachersList.add(teacher);
        }
        return teachersList;
    }

//========================================================================================
//                          **** Set Majores and Levels ****    
//========================================================================================
    void setSpechLevel(int level, int spech, int semester) throws SQLException {
        this.spech.setText(majorsList.get(spech).getNickName());
        this.level.setText(levelsList.get(level).getNickName());
        if ((semester + 1) == 1) {
            this.semester.setText("الفصل الاول");
        } else {
            this.semester.setText("الفصل الثاني");
        }
        spechValue = spech;
        levelValue = level;
        semesterValue = semester;
        System.out.println(spechValue + "    " + levelValue);
        setOptionsCourseComboBox();
        setOptionsTeacherComboBox();
        setOptionsGenderComboBox();
    }
//========================================================================================
//                               **** Initialize ****    
//========================================================================================

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            /*try {
            // TODO
            ArrayList<Course> a = getCourses(call, 2 , 2 , 1);
            Arrays.toString(a.toArray());
            } catch (SQLException ex) {
            Logger.getLogger(AddSectionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             */
            getMajors(call);
            getLevels(call);

        } catch (Exception ex) {
            Logger.getLogger(AddSectionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//========================================================================================
//                             **** Insert Section ****    
//========================================================================================
    private void setSection(DbCall call) throws SQLException {

        int majorId = majorsList.get(spechValue).getId();
        int courseId = courseComboBox.getSelectionModel().getSelectedIndex();
        int finalCourseId = courseList.get(courseId).getId();
        int teacherId = teacherComboBox.getSelectionModel().getSelectedIndex();
        int finalTeacherId = teachersList.get(teacherId).getId();
        int genderId = genderComboBox.getSelectionModel().getSelectedIndex();
        genderId++;
        int sectionNumber = Integer.parseInt(sectionNumberTxt.getText());
        int sectionSize = Integer.parseInt(sectionstdNumberTxt.getText());
        System.out.println(finalCourseId + " / " + finalTeacherId + " / " + (genderId + 1) + " / " + sectionNumber + " / " + sectionSize);

        int i = call.getExecuteUpdate("INSERT INTO `sections` (`course_id`, `major_id`, `teacher_id`, `gender_type`, `section_number`, `size`) VALUES ('"
                + finalCourseId + "','" + majorId
                + "','" + finalTeacherId + "','" + String.valueOf(genderId) + "','" + sectionNumber + "','" + sectionSize + "')");
//        int i = call.getExecuteUpdate("INSERT INTO `levels` (`id`, `nickName`) VALUES ("
//                + 5 + ",'" + "dflflk"
//               +"')");
        System.out.println("sql : " + i);
//        while (resultSet.next()) {
//
//            Teacher teacher = new Teacher();
//
//            teacher.setId(resultSet.getInt("id"));
//            teacher.setName(resultSet.getString("name"));
//
//            teachersList.add(teacher);
//        }
//        return teachersList;
        if (i != -1) {
            new Alert(Alert.AlertType.INFORMATION, "تمت الاضافة").show();
            final Stage stage = (Stage) spech.getScene().getWindow();
            stage.close();
        } else {
            new Alert(Alert.AlertType.ERROR, "حصل خطأ").show();
        }
    }
}
