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
import Model.finalTableView;
import Model.sectionTable;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Flash Tech
 */
public class FXMLDocumentController implements Initializable {

    private int level, spech, semester, genderV;
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
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private TableView mainTable;
    @FXML
    private TableColumn sectionNumber;
    @FXML
    private TableColumn courseId;
    @FXML
    private TableColumn courseName;
    @FXML
    private TableColumn teacherName;
    @FXML
    private TableColumn gender;
    @FXML
    private TableColumn size;
    @FXML
    private ComboBox semesterComboBox;
    @FXML
    private ComboBox genderComboBox;
    @FXML
    private Button refreshButton;
    @FXML
    private Button emptyButton;

    /**
     * DELETE button
     *
     * @param event
     */
    @FXML
    private void deleteButtonAction(ActionEvent event) {
        ObservableList<sectionTable> s = mainTable.getSelectionModel().getSelectedItems();
        if (!s.isEmpty()) {
            System.out.println(s.get(0).getId());
            int id = s.get(0).getId();
            mainTable.getItems().removeAll(mainTable.getSelectionModel().getSelectedItems());
            int f = call.getExecuteUpdate("DELETE FROM sections WHERE id = '" + id + "'");
            if (f == -1) {
                new Alert(AlertType.WARNING, "حصل خطا !").show();
            } else {
                new Alert(AlertType.INFORMATION, "تمت العملية بنجاح").show();
            }
        } else {
            new Alert(AlertType.WARNING, "يجب أن نختار صفا !!").show();
        }
    }

    /**
     * EDIT button
     */
    @FXML
    private void editButtonAction(ActionEvent event) throws SQLException {
        ObservableList<sectionTable> row = mainTable.getSelectionModel().getSelectedItems();
        if (!row.isEmpty()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/EditSectionFXML.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                EditSectionFXMLController controller = fxmlLoader.getController();
                controller.setSpechLevel(level, spech, semester, row);
                stage.setScene(new Scene(root));
                stage.setMaximized(false);
                stage.setResizable(false);
                stage.setTitle("تعديل الشعب");
                stage.show();
            } catch (IOException ex) {
                System.out.println("EDIT SECTION VIEW ERROR");
            }
        } else {
            new Alert(AlertType.WARNING, "يجب أن نختار صفا !!").show();
        }
    }

    /**
     * Run Algorithm
     *
     * @param event
     */
    @FXML
    private void algorithmButtonAction(ActionEvent event) {
        new Alert(AlertType.INFORMATION, "Name, Role and Email fields cannot be empty!").show();
        System.out.println("Algorithm");
    }
//========================================================================================

    /**
     * ِADD button
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void addButtonAction(ActionEvent event) throws SQLException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/addSectionFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            AddSectionFXMLController con = fxmlLoader.getController();
            con.setSpechLevel(level, spech, semester);
            stage.setScene(new Scene(root));
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setTitle("إضافة شعبة");
            stage.show();
        } catch (IOException ex) {
            System.out.println("Errore........");
        }
    }

    /**
     * ِADD button
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void refreshButtonAction(ActionEvent event) throws SQLException {
        setValuesMainTable(level, spech, semester,genderV);
    }

    @FXML
    private void emptyButtonAction(ActionEvent event) throws SQLException {
        setValuesMainTable(0, 0, 0,0);
        speCombobox.getSelectionModel().select(-1);
        levelsComboBox.getSelectionModel().select(-1);
        semesterComboBox.getSelectionModel().select(-1);
        genderComboBox.getSelectionModel().select(-1);
        levelsComboBox.setDisable(true);
        semesterComboBox.setDisable(true);
        genderComboBox.setDisable(true);
        refreshButton.setDisable(true);
        emptyButton.setDisable(true);
    }

    @FXML
    private void speComboBoxAction(ActionEvent event) throws SQLException {
        levelsComboBox.setDisable(false);
        spech = speCombobox.getSelectionModel().getSelectedIndex();
        if ((levelsComboBox.getSelectionModel().getSelectedIndex() != -1)
                && (semesterComboBox.getSelectionModel().getSelectedIndex() != -1)
                && (genderComboBox.getSelectionModel().getSelectedIndex() != -1)) {
            setValuesMainTable(level, spech, semester, genderV);
        }
        System.out.println(spech);
    }

    @FXML
    private void levelsComboBoxAction(ActionEvent event) throws SQLException {
        semesterComboBox.setDisable(false);
        level = levelsComboBox.getSelectionModel().getSelectedIndex();
        if ((speCombobox.getSelectionModel().getSelectedIndex() != -1)
                && (semesterComboBox.getSelectionModel().getSelectedIndex() != -1)
                && (genderComboBox.getSelectionModel().getSelectedIndex() != -1)) {
            setValuesMainTable(level, spech, semester, genderV);
        }
        System.out.println("SEMESTER" + semesterComboBox.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void semesterComboBoxAction(ActionEvent event) throws SQLException {
        addButton.setDisable(false);
        scedButton.setDisable(false);
        genderComboBox.setDisable(false);
        semester = semesterComboBox.getSelectionModel().getSelectedIndex();
        if ((speCombobox.getSelectionModel().getSelectedIndex() != -1)
                && (levelsComboBox.getSelectionModel().getSelectedIndex() != -1)
                && (genderComboBox.getSelectionModel().getSelectedIndex() != -1)) {
            setValuesMainTable(level, spech, semester, genderV);
        }
        System.out.println(level);
    }

    @FXML
    private void genderComboBoxAction(ActionEvent event) throws SQLException {
        addButton.setDisable(false);
        scedButton.setDisable(false);
        deleteButton.setDisable(false);
        editButton.setDisable(false);
        refreshButton.setDisable(false);
        emptyButton.setDisable(false);
        genderV = genderComboBox.getSelectionModel().getSelectedIndex();
        setValuesMainTable(level, spech, semester,genderV);
    }

    protected void refresh(int level, int spech, int semester) throws SQLException {
        setValuesMainTable(level, spech, semester,genderV);
    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setOptionsSpeCombobox();
            setOptionslevelsCombobox();
            setOptionsSemesterComboBox();
            setOptionsGenderComboBox();
            mainTable.setPlaceholder(new Label("لا يوجد شعب مضافة"));
            mainTableSectionsTab.setPlaceholder(new Label("لا يوجد شعب مجدولة"));

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
        spechComboBoxSectionsTab.setItems(data);
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
        levelComboBoxSectionsTab.setItems(data);
    }

    private void setOptionsSemesterComboBox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList("الفصل الاول", "الفصل الثاني"); //List of String
        semesterComboBox.setItems(data);
        semesterComboBoxSectionsTab.setItems(data);
    }

    private void setOptionsGenderComboBox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList("ذكر", "أنثى"); //List of String
        // semesterComboBox.setItems(data);
        genderComboBox.setItems(data);
        genderComboBoxSectionsTab.setItems(data);
    }

    protected void setValuesMainTable(int level, int major, int semester, int genderV) throws SQLException {
        level++;
        major++;
        semester++;
        genderV++;
        System.out.println("smester : " + semester);
        String g = "ذكر";
        ObservableList<sectionTable> data = FXCollections.observableArrayList();
        ResultSet resultSet;
        resultSet = call.getExecuteQuery(
                "SELECT sections.id,sections.section_number ,courses.name,courses.course_number,teachers.name,sections.size,sections.gender_type FROM sections INNER JOIN courses ON sections.course_id = courses.id INNER JOIN teachers ON sections.teacher_id = teachers.id where courses.level_number = '" + level + "' and courses.major_id = '" + major + "' and courses.semester = '" + semester + "' and sections.gender_type = '" + genderV + "' ");
//        ResultSetMetaData rsmd = resultSet.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
//            System.out.println(resultSet.getInt(1)+resultSet.getString(2)+resultSet.getString(3)+resultSet.getString(4)+resultSet.getInt(5)+resultSet.getInt(6));
            if (resultSet.getInt(7) == 1) {
                g = "ذكر";
            } else {
                g = "انثى";
            }
            sectionTable s = new sectionTable(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(6), resultSet.getString(4), resultSet.getString(3), resultSet.getString(5), g);
            data.add(s);
        }
        mainTable.setItems(data);
        sectionNumber.setCellValueFactory(new PropertyValueFactory<>("sectionNum"));
        courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        teacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("studentGender"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    /**
     *
     * Final Table Tab
     *
     */
    @FXML
    TableView mainTableSectionsTab;
    @FXML
    TableColumn sectionNumberSectionsTab;
    @FXML
    TableColumn courseNumberSectionsTab;
    @FXML
    TableColumn courseNameSectionsTab;
    @FXML
    TableColumn teacherNameSectionsTab;
    @FXML
    TableColumn saturdaySectionsTab;
    @FXML
    TableColumn sundaySectionsTab;
    @FXML
    TableColumn mondaySectionsTab;
    @FXML
    TableColumn tusdaySectionsTab;
    @FXML
    TableColumn wednesdaySectionsTab;
    @FXML
    ComboBox spechComboBoxSectionsTab;
    @FXML
    ComboBox levelComboBoxSectionsTab;
    @FXML
    ComboBox semesterComboBoxSectionsTab;
    @FXML
    ComboBox genderComboBoxSectionsTab;
    @FXML
    Button refreshButtonFinalTableTab;
    @FXML
    Button emptyButtonFinalTableTab;
    private int levelSectionsTab, spechSectionsTab, semesterSectionsTab, genderSectionsTab;

    /*
    private void selectTableLevel() {

    }

    private void selectTableSpech(int index) {

    }

    private void selectTableSemeste() {

    }

    private void selectTableGender() {

    }

    private void selectTableLevelandSpech() {

    }

    private void selectTableLevelandSpechandSemester() {

    }

    private void selectTableLevelandSpechandSemesterandGender() {

    }

    private void selectTableSpechandGender() {

    }

    private void selectTableSpechandSemester() {

    }

    private void selectTableSpechandSemesterandGender() {

    }

    private void selectTableLevelandSemesterandGender() {

    }

    private void selectTableLevelandGender() {

    }

    private void selectTableLevelandSemester() {

    }

    private void selectTableLevelandSpechandGender() {

    }

    private void selectTableSemesterandGender() {

    }
     */
    @FXML
    private void spechComboBoxSectionsTabAction(ActionEvent event) throws SQLException {
        levelComboBoxSectionsTab.setDisable(false);
        spechSectionsTab = spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        levelSectionsTab = levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        semesterSectionsTab = semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        genderSectionsTab = genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        if (levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1) {
            finalTableView(spechSectionsTab, levelSectionsTab, semesterSectionsTab, genderSectionsTab);
        }
        System.out.println("Spec : " + spech);
    }

    @FXML
    private void levelComboBoxSectionsTabAction(ActionEvent event) throws SQLException {
        semesterComboBoxSectionsTab.setDisable(false);
        spechSectionsTab = spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        levelSectionsTab = levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        semesterSectionsTab = semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        genderSectionsTab = genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        if (spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1) {
            finalTableView(spechSectionsTab, levelSectionsTab, semesterSectionsTab, genderSectionsTab);
        }
    }

    @FXML
    private void semesterComboBoxSectionsTabAction(ActionEvent event) throws SQLException {
        genderComboBoxSectionsTab.setDisable(false);
        spechSectionsTab = spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        levelSectionsTab = levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        semesterSectionsTab = semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        genderSectionsTab = genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        if (spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1) {
            finalTableView(spechSectionsTab, levelSectionsTab, semesterSectionsTab, genderSectionsTab);
        }
    }

    @FXML
    private void genderComboBoxSectionsTabAction(ActionEvent event) throws SQLException {
        spechSectionsTab = spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        levelSectionsTab = levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        semesterSectionsTab = semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        genderSectionsTab = genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        if (spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1) {
            finalTableView(spechSectionsTab, levelSectionsTab, semesterSectionsTab, genderSectionsTab);
        }
        refreshButtonFinalTableTab.setDisable(false);
        emptyButtonFinalTableTab.setDisable(false);
        finalTableView(spechSectionsTab, levelSectionsTab, semesterSectionsTab, genderSectionsTab);
    }

    @FXML
    private void refreshButtonFinalTableTabAction(ActionEvent event) throws SQLException {
        spechSectionsTab = spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        levelSectionsTab = levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        semesterSectionsTab = semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        genderSectionsTab = genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex();
        if (spechComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && levelComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && semesterComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1
                && genderComboBoxSectionsTab.getSelectionModel().getSelectedIndex() != -1) {
            finalTableView(spechSectionsTab, levelSectionsTab, semesterSectionsTab, genderSectionsTab);
        }
    }

    @FXML
    private void emptyButtonFinalTableTabAction(ActionEvent event) throws SQLException {
        spechComboBoxSectionsTab.getSelectionModel().select(-1);
        levelComboBoxSectionsTab.getSelectionModel().select(-1);
        semesterComboBoxSectionsTab.getSelectionModel().select(-1);
        genderComboBoxSectionsTab.getSelectionModel().select(-1);
        levelComboBoxSectionsTab.setDisable(true);
        semesterComboBoxSectionsTab.setDisable(true);
        genderComboBoxSectionsTab.setDisable(true);
        refreshButtonFinalTableTab.setDisable(true);
        emptyButtonFinalTableTab.setDisable(true);
        finalTableView(0, 0, 0, 0);

    }

    private void finalTableView(int spech, int level, int semester, int gender) throws SQLException {
        ObservableList<finalTableView> data = FXCollections.observableArrayList();
        spech++;
        level++;
        semester++;
        gender++;
        System.out.println(spech + " | " + level + " | " + semester + " | " + gender);
        ResultSet resultSet;
        resultSet = call.getExecuteQuery(
                "SELECT final_table.id,courses.name,final_table.timeslots_day_id,final_table.start,final_table.end,sections.section_number,courses.course_number,teachers.name FROM final_table INNER JOIN sections ON sections.id = final_table.section_id INNER JOIN courses ON sections.course_id = courses.id INNER JOIN teachers ON teachers.id = final_table.teacher_id WHERE final_table.major_id = '" + spech + "' and final_table.level_number = '" + level + "' and final_table.semester = '" + semester + "' and sections.gender_type='" + gender + "'"
        );

        while (resultSet.next()) {
            System.out.println("ID : " + resultSet.getInt(1)
                    + " | Course Name : " + resultSet.getString(2)
                    + " | timeslot : " + resultSet.getInt(3)
                    + " | start : " + resultSet.getDouble(4)
                    + " | end : " + resultSet.getDouble(5)
                    + " | section number : " + resultSet.getInt(6)
                    + " | course number : " + resultSet.getString(7)
                    + " | teacher name : " + resultSet.getString(8));
            finalTableView row;
            switch (resultSet.getInt(3)) {
                case 1:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)));
                    data.add(row);
                    break;
                case 2:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ");
                    data.add(row);
                    break;
                case 3:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ");
                    data.add(row);
                    break;
                case 4:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)));
                    data.add(row);
                    break;
                case 5:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)));
                    data.add(row);
                    break;
                case 6:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ", " ", " ");
                    data.add(row);
                    break;
                case 7:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ", " ");
                    data.add(row);
                    break;
                case 8:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ");
                    data.add(row);
                    break;
                case 9:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ");
                    data.add(row);
                    break;
                case 10:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)));
                    data.add(row);
                    break;
                case 12:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ", " ", " ");
                    data.add(row);
                    break;
                case 13:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ", " ");
                    data.add(row);
                    break;
                case 14:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ", " ");
                    data.add(row);
                    break;
                case 15:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)), " ");
                    data.add(row);
                    break;
                case 16:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", " ", " ", (resultSet.getDouble(4) + " - " + resultSet.getDouble(5)));
                    data.add(row);
                    break;
                case 17:
                    row = new finalTableView(resultSet.getInt(1), resultSet.getInt(6), resultSet.getString(2), resultSet.getString(7), resultSet.getString(8), " ", " ", " ", " ", " ");
                    data.add(row);
                    break;
            }
        }
        mainTableSectionsTab.setItems(data);
        sectionNumberSectionsTab.setCellValueFactory(new PropertyValueFactory<>("sectionNumber"));
        courseNumberSectionsTab.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));
        courseNameSectionsTab.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        teacherNameSectionsTab.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        saturdaySectionsTab.setCellValueFactory(new PropertyValueFactory<>("saturday"));
        sundaySectionsTab.setCellValueFactory(new PropertyValueFactory<>("sunday"));
        mondaySectionsTab.setCellValueFactory(new PropertyValueFactory<>("monday"));
        tusdaySectionsTab.setCellValueFactory(new PropertyValueFactory<>("tusday"));
        wednesdaySectionsTab.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        saturdaySectionsTab.setStyle("-fx-alignment: CENTER;");
        sundaySectionsTab.setStyle("-fx-alignment: CENTER;");
        mondaySectionsTab.setStyle("-fx-alignment: CENTER;");
        tusdaySectionsTab.setStyle("-fx-alignment: CENTER;");
        wednesdaySectionsTab.setStyle("-fx-alignment: CENTER;");
    }
}
