package Controller;

import Controller.AddSectionFXMLController;
import Model.Course;
import Model.DbCall;
import Model.FinalRaw;
import Model.Levels;
import Model.MajorTable;
import Model.Majors;
import Model.Section;
import Model.StudyTimes;
import Model.TimeslotDay;
import Model.finalTableView;
import Model.sectionTable;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

public class FXMLDocumentController implements Initializable {

    //------------------------------------------------------------------------------------------------------------------------
    //        ---------  START  ---------  NON SCHEDUALIED SECTIONS TABLE ---------
    //------------------------------------------------------------------------------------------------------------------------
    /**
     * VARIABLES
     */
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

    ArrayList<StudyTimes> dayList;
    ArrayList<StudyTimes> dayListMale;
    ArrayList<StudyTimes> dayListFemale;
    
    ArrayList<Course> courseList;
    ArrayList<Course> courseThreeList;
    ArrayList<Course> courseTwoList;
    
    ArrayList<Section> sectionListMale;
    ArrayList<Section> sectionListFemale;

    ArrayList<FinalRaw> finalRawAtherMajorsList;
    ArrayList<FinalRaw> practicalRowListMale;
    ArrayList<FinalRaw> practicalRowListFemale;
    
    int practicaCount = 0;
    
    int step = 0;
    int step2 = 0;
    
    /**
     * Run Algorithm
     *
     * @param event
     */
    @FXML
    private void algorithmButtonAction(ActionEvent event) throws SQLException {
        
        DbCall call = DbCall.getDbCall();

        MajorTable major = new MajorTable();
        MajorTable majorFemale = new MajorTable();

        dayListMale = new ArrayList<>();
        getAllStudyTimes();
        Collections.shuffle(dayList);
        Collections.shuffle(dayListFemale);

        practicalRowListMale = new ArrayList<>();
        practicalRowListFemale = new ArrayList<>();

        finalRawAtherMajorsList = new ArrayList<>();

        // can work  just for this -> 2 2 1 , 1 1 1
        courseList = getCourses(call, spech+1 , level+1 ,semester+1);

        Collections.shuffle(courseThreeList);
        Collections.shuffle(courseTwoList);
        Collections.shuffle(courseList);

        ArrayList<Section> sectionList = null;
        
        ArrayList<FinalRaw> rawTableDone = getRawTableDone(call , spech+1 /*major-id*/ , level+1 , semester+1);
            
        if (rawTableDone.size() > 0) {

            for (int i = 0; i < rawTableDone.size(); i++) {

                Section section = getSection(call, rawTableDone.get(i).getSection_id());

                if (section.getGender_type() == 1) {

                    major.getMajorTable().add(rawTableDone.get(i));

                    Course course = getCourse(call, section.getCourse_id());

                    if (course.getActual_hours() == 3 && course.getCan_devide() == 1) {
                        for (int j = 0; j < courseThreeList.size(); j++) {
                            if (course.getId() == courseThreeList.get(j).getId()) {
                                courseThreeList.remove(j);
                            }
                        }
                    }else if(course.getActual_hours() == 2 && course.getCan_devide() == 1){
                        for (int j = 0; j < courseTwoList.size(); j++) {
                            if (course.getId() == courseTwoList.get(j).getId()) {
                                courseTwoList.remove(j);
                            }
                        }
                    }else{
                        for (int j = 0; j < courseList.size(); j++) {
                            if (course.getId() == courseList.get(j).getId()) {
                                courseList.remove(j);
                            }
                        }
                    }
                }else if (section.getGender_type() == 2) {

                    majorFemale.getMajorTable().add(rawTableDone.get(i));

                    Course course = getCourse(call, section.getCourse_id());

                    if (course.getActual_hours() == 3 && course.getCan_devide() == 1) {
                        for (int j = 0; j < courseThreeList.size(); j++) {
                            if (course.getId() == courseThreeList.get(j).getId()) {
                                courseThreeList.remove(j);
                            }
                        }
                    }else if(course.getActual_hours() == 2 && course.getCan_devide() == 1){
                        for (int j = 0; j < courseTwoList.size(); j++) {
                            if (course.getId() == courseTwoList.get(j).getId()) {
                                courseTwoList.remove(j);
                            }
                        }
                    }else{
                        for (int j = 0; j < courseList.size(); j++) {
                            if (course.getId() == courseList.get(j).getId()) {
                                courseList.remove(j);
                            }
                        }
                    }
                }
                if (section.getGender_type() == 1) {

                    for (int j = 0; j < major.getMajorTable().size(); j++) {

                        FinalRaw raw =  major.getMajorTable().get(j);

                        TimeslotDay timeslotDay = getTimeslotDay(call, raw.getTimeslots_day_id());

                        StudyTimes studyTimes = new StudyTimes();
                        studyTimes.setDay_id(timeslotDay.getDay_id_o());
                        studyTimes.setTimeslot_id(timeslotDay.getTimeslot_id());
                        studyTimes.setStart(raw.getStartTime());
                        studyTimes.setEnd(raw.getEndTime());

                        for(int s = 0; s < dayList.size(); s++ ){

                            if (dayList.get(s).getTimeslot_id() == timeslotDay.getTimeslot_id()) {

                                if (dayList.get(s).getDay_id() == timeslotDay.getDay_id_o()){

                                    dayList.remove(s);

                                    if (timeslotDay.getDay_id_t() < 8) {

                                        for (int js = 0; js < dayList.size(); js++) {
                                            if (dayList.get(js).getDay_id() == timeslotDay.getDay_id_t() && dayList.get(js).getStart() < studyTimes.getEnd() ) {
                                                dayList.remove(js);
                                            }
                                        }

                                        if (timeslotDay.getDay_id_th() < 8) {
                                            for (int js = 0; js < dayList.size(); js++) {
                                                if (dayList.get(js).getDay_id() == timeslotDay.getDay_id_t() && dayList.get(js).getStart() < studyTimes.getEnd() ) {
                                                    dayList.remove(js);
                                                }
                                            }
                                        }
                                    }

                                    for (int js = 0; js < dayList.size(); js++) {
                                        if (dayList.get(js).getDay_id() == timeslotDay.getDay_id_t() && dayList.get(js).getStart() < studyTimes.getEnd() ) {
                                            dayList.remove(js);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }else if (section.getGender_type() == 2) {

                    for (int j = 0; j < majorFemale.getMajorTable().size(); j++) {

                        FinalRaw raw =  majorFemale.getMajorTable().get(j);

                        TimeslotDay timeslotDay = getTimeslotDay(call, raw.getTimeslots_day_id());

                        StudyTimes studyTimes = new StudyTimes();
                        studyTimes.setDay_id(timeslotDay.getDay_id_o());
                        studyTimes.setTimeslot_id(timeslotDay.getTimeslot_id());
                        studyTimes.setStart(raw.getStartTime());
                        studyTimes.setEnd(raw.getEndTime());

                        for(int s = 0; s < dayListFemale.size(); s++ ){

                            if (dayListFemale.get(s).getTimeslot_id() == timeslotDay.getTimeslot_id()) {

                                if (dayListFemale.get(s).getDay_id() == timeslotDay.getDay_id_o()){

                                    dayListFemale.remove(s);

                                    if (timeslotDay.getDay_id_t() < 8) {

                                        for (int js = 0; js < dayListFemale.size(); js++) {
                                            if (dayListFemale.get(js).getDay_id() == timeslotDay.getDay_id_t() && dayListFemale.get(js).getStart() < studyTimes.getEnd() ) {
                                                dayListFemale.remove(js);
                                            }
                                        }

                                        if (timeslotDay.getDay_id_th() < 8) {
                                            for (int js = 0; js < dayListFemale.size(); js++) {
                                                if (dayListFemale.get(js).getDay_id() == timeslotDay.getDay_id_t() && dayListFemale.get(js).getStart() < studyTimes.getEnd() ) {
                                                    dayListFemale.remove(js);
                                                }
                                            }
                                        }
                                    }

                                    for (int js = 0; js < dayListFemale.size(); js++) {
                                        if (dayListFemale.get(js).getDay_id() == timeslotDay.getDay_id_t() && dayListFemale.get(js).getStart() < studyTimes.getEnd() ) {
                                            dayListFemale.remove(js);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } 
        }
        if (courseThreeList != null && courseThreeList.size() > 0 ) {
                
            for(int i = 0; i < courseThreeList.size() ; i++){

                Course course = courseThreeList.get(i);

                sectionList = getSections(call, course.getId() , spech+1 );
                Collections.shuffle(sectionListMale);
                Collections.shuffle(sectionListFemale);

                for(int j = 0 ; j < sectionListMale.size(); j++){

                   Section section = sectionListMale.get(j);
                   FinalRaw raw = getRaw(call, course , section , 1);

                   if (raw.getTimeslots_day_id() == 0) {
                       raw.setTimeslots_day_id(17);
                   }
                   major.getMajorTable().add(raw);
                }

                for(int j = 0 ; j < sectionListFemale.size(); j++) {

                    if (j == 0) {
                        step = 1;
                    }

                    Section section = sectionListFemale.get(j);
                    FinalRaw raw = getRaw(call, course , section , 2);

                    if (raw.getTimeslots_day_id() == 0) {
                       raw.setTimeslots_day_id(17);
                    }

                    majorFemale.getMajorTable().add(raw);

                    if (j >= sectionListFemale.size()) {
                       if (step == 1) {
                            for (int t = 0; t < dayListMale.size(); t++) {
                                dayListFemale.add(dayListMale.get(i));
                                dayListMale.remove(i);
                            }
                        }
                    }
                }
            }
        }
        if (courseTwoList != null && courseTwoList.size() > 0 ) {
                
            for(int i = 0; i < courseTwoList.size() ; i++){

                Course course = courseTwoList.get(i);

                // 2  , 1
                sectionList = getSections(call, course.getId() , spech+1);
                Collections.shuffle(sectionListMale);
                Collections.shuffle(sectionListFemale);

                for(int j = 0 ; j < sectionListMale.size(); j++){

                    Section section = sectionListMale.get(j);
                    FinalRaw raw = getRaw(call, course , section,1);

                    if (raw.getTimeslots_day_id() == 0) {
                        raw.setTimeslots_day_id(17);
                    }

                    major.getMajorTable().add(raw);
                }

                for(int j = 0 ; j < sectionListFemale.size(); j++){

                    if (j == 0) {
                        step = 1;
                    }

                    Section section = sectionListFemale.get(j);
                    FinalRaw raw = getRaw(call, course , section , 2);

                    if (raw.getTimeslots_day_id() == 0) {
                       raw.setTimeslots_day_id(17);
                    }

                    majorFemale.getMajorTable().add(raw);

                    if (j >= sectionListFemale.size()) {
                       if (step == 1) {
                            for (int t = 0; t < dayListMale.size(); t++) {
                                dayListFemale.add(dayListMale.get(i));
                                dayListMale.remove(i);
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i < courseList.size() ; i++) {
                    
            Course course = courseList.get(i);

            // 2  , 1
            sectionList = getSections(call, course.getId() , spech+1);
            Collections.shuffle(sectionListMale);
            Collections.shuffle(sectionListFemale);

            for(int j = 0 ; j < sectionListMale.size(); j++) {

                Section section = sectionListMale.get(j);
                FinalRaw raw = getRaw(call, course , section, 1);

                if (course.getType() == 0 && step2 == 0) {
                    practicalRowListMale.add(raw);
                }
                
                if (raw.getTimeslots_day_id() == 0) {
                    raw.setTimeslots_day_id(17);
                }
                major.getMajorTable().add(raw);
            }

            for(int j = 0 ; j < sectionListFemale.size(); j++){

                if (j == 0) {
                    step = 1;
                }

                Section section = sectionListFemale.get(j);
                FinalRaw raw = getRaw(call, course , section , 2);

                if (course.getType() == 0 && step2 == 0) {
                    practicalRowListFemale.add(raw);
                }
                
                if (raw.getTimeslots_day_id() == 0) {
                   raw.setTimeslots_day_id(17);
                }

                majorFemale.getMajorTable().add(raw);

                if (j >= sectionListFemale.size()) {
                   if (step == 1) {
                        for (int t = 0; t < dayListMale.size(); t++) {
                            dayListFemale.add(dayListMale.get(i));
                            dayListMale.remove(i);
                        }
                    }
                }
            }
        }
        
        if (finalRawAtherMajorsList != null && finalRawAtherMajorsList.size() > 0) {
            for(int i = 0; i < finalRawAtherMajorsList.size() ; i++){
                FinalRaw finalRaw = finalRawAtherMajorsList.get(i);
                if (finalRaw.getTimeslots_day_id() == 0) {
                    finalRaw.setTimeslots_day_id(17);
                }
                majorFemale.getMajorTable().add(finalRaw);
            }
        }

        
        int rsMale =  insertFinalTable(call , major);
        int rsFemale =  insertFinalTable(call , majorFemale);

        if (rsMale > -1 && rsFemale > -1) {
            System.out.println("Done");
            new Alert(AlertType.INFORMATION, "It' Done!").show();
        }
    }

    
    /**
     * DELETE BUTTON
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
     * ADD BUTTON
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
     * REFRESH BUTTON
     */
    @FXML
    private void refreshButtonAction(ActionEvent event) throws SQLException {
        setValuesMainTable(level, spech, semester, genderV);
    }

    /**
     * EMPTING BUTTON
     */
    @FXML
    private void emptyButtonAction(ActionEvent event) throws SQLException {
        setValuesMainTable(0, 0, 0, 0);
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

    /**
     * MAJORES COMBOBOX
     */
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

    /**
     * LEVELS COMBOBOX
     */
    @FXML
    private void levelsComboBoxAction(ActionEvent event) throws SQLException {
        semesterComboBox.setDisable(false);
        level = levelsComboBox.getSelectionModel().getSelectedIndex();
        if ((speCombobox.getSelectionModel().getSelectedIndex() != -1)
                && (semesterComboBox.getSelectionModel().getSelectedIndex() != -1)
                && (genderComboBox.getSelectionModel().getSelectedIndex() != -1)) {
            setValuesMainTable(level, spech, semester, genderV);
        }
    }

    /**
     * SEMESTER COMBOBOX
     */
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
    }

    /**
     * GENDER COMBOBOX
     */
    @FXML
    private void genderComboBoxAction(ActionEvent event) throws SQLException {
        addButton.setDisable(false);
        scedButton.setDisable(false);
        deleteButton.setDisable(false);
        editButton.setDisable(false);
        refreshButton.setDisable(false);
        emptyButton.setDisable(false);
        genderV = genderComboBox.getSelectionModel().getSelectedIndex();
        setValuesMainTable(level, spech, semester, genderV);
    }

    /**
     * INITIALIZE METHODE
     */
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

    /**
     * GET MAJORS METHODE
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
     * GET LEVELS METHODE
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
     * SET OPTIONS TO MAJORS COMBOBOX
     */
    private void setOptionsSpeCombobox() throws SQLException {
        getMajors(call);
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i < majorsList.size(); i++) {
            System.out.println(majorsList.get(i));
            data.add(majorsList.get(i).getNickName());
        }
        speCombobox.setItems(data);
        spechComboBoxSectionsTab.setItems(data);
    }

    /**
     * SET OPTIONS TO LEVELS COMBOBOX
     */
    private void setOptionslevelsCombobox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList(); //List of String
        ArrayList<Levels> a = getLevels(call);
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
            data.add(a.get(i).getNickName());
        }
        levelsComboBox.setItems(data);
        levelComboBoxSectionsTab.setItems(data);
    }

    /**
     * SET OPTIONS TO SEMESTER COMBOBOX
     */
    private void setOptionsSemesterComboBox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList("الفصل الاول", "الفصل الثاني");
        semesterComboBox.setItems(data);
        semesterComboBoxSectionsTab.setItems(data);
    }

    /**
     * SET OPTIONS TO GENDER COMBOBOX
     */
    private void setOptionsGenderComboBox() throws SQLException {
        ObservableList<String> data = FXCollections.observableArrayList("ذكر", "أنثى");
        genderComboBox.setItems(data);
        genderComboBoxSectionsTab.setItems(data);
    }

    /**
     * SET VALUES TO NON SCHEDUALING SECTIONS TABLE
     */
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
        while (resultSet.next()) {
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

    //------------------------------------------------------------------------------------------------------------------------
    //        ---------  END  ---------  NON SCHEDUALED SECTIONS TABLE ---------
    //------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------
    //        ---------  START  ---------  SCHEDUALED SECTIONS TABLE ---------
    //------------------------------------------------------------------------------------------------------------------------
    /**
     * SCHEDUALIED SECTIONS TABLE
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
    /**
     * MAJORES COMBOBOX ACTION
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
    }

    /**
     * LEVEL COMBOBOX ACTION
     */
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

    /**
     * SEMESTER COMBOBOX ACTION
     */
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

    /**
     * GENDER COMBOBOX ACTION
     */
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

    /**
     * REFRESH BUTTON ACTION
     */
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

    /**
     * EMTING BUTTON ACTION
     */
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

    /**
     * TABLED SECTIONS VIEW
     */
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
//            System.out.println("ID : " + resultSet.getInt(1)
//                    + " | Course Name : " + resultSet.getString(2)
//                    + " | timeslot : " + resultSet.getInt(3)
//                    + " | start : " + resultSet.getDouble(4)
//                    + " | end : " + resultSet.getDouble(5)
//                    + " | section number : " + resultSet.getInt(6)
//                    + " | course number : " + resultSet.getString(7)
//                    + " | teacher name : " + resultSet.getString(8));
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

    //------------------------------------------------------------------------------------------------------------------------
    //        ---------  END  ---------  SCHEDUALED SECTIONS TABLE ---------
    //------------------------------------------------------------------------------------------------------------------------
    
    //------------------------------------------------------------------------------------------------------------------------
    //        ---------  START  ---------  COURSE TABLE ---------
    //------------------------------------------------------------------------------------------------------------------------
    
    /**
     * VARIABLES
     */
    @FXML
    TableView courseTableCourseTab;
    @FXML
    TableColumn courseNameCourseTab;
    @FXML
    TableColumn courseNumberCourseTab;
    @FXML
    TableColumn courseTypeCourseTab;
    @FXML
    TableColumn courseMajorCourseTab;
    @FXML
    TableColumn courseLevelCourseTab;
    @FXML
    TableColumn courseSemesterCourseTab;
    @FXML
    TableColumn courseCHouresCourseTab;
    @FXML
    TableColumn courseAHouresCourseTab;
    @FXML
    TableColumn courseADivideCourseTab;
    

    /**
     * VIEW ALL BUTTON ACTION
     */
    @FXML
    private void viewAllButtonAction(ActionEvent event) throws SQLException {
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

    //------------------------------------------------------------------------------------------------------------------------
    //        ---------  START  ---------  ALGORITHM METHODS ---------
    //------------------------------------------------------------------------------------------------------------------------
     /**
     *
     * Return spesfic Course
     */
    public  Course getCourse(DbCall call ,int id) throws SQLException {
      
        ResultSet resultSet;
        
        resultSet = call.getExecuteQuery("select * from courses where id=" + id);

        if (resultSet != null) {

            Course course = new Course();

            while(resultSet.next()){

                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setLevel_number(resultSet.getInt("level_number"));
                course.setSemester(resultSet.getInt("semester"));
                course.setCredit_hours(resultSet.getInt("credit_hours"));
                course.setActual_hours(resultSet.getInt("actual_hours"));
                course.setCan_devide(resultSet.getInt("can_devide"));
            }
            return course;
        }
        
        return null;
    }
     
    /**
     *
     * Return spesfic Section
     */
    public  Section getSection(DbCall call , int id) throws SQLException {
        
        ResultSet resultSet;
        
        resultSet = call.getExecuteQuery("select * from sections where id=" + id);

        if (resultSet != null) {

            Section section = new Section();

            while(resultSet.next()){
                
                section.setId(resultSet.getInt("id"));
                section.setCourse_id(resultSet.getInt("course_id"));
                section.setMajor_id(resultSet.getInt("major_id"));
                section.setTeacher_id(resultSet.getInt("teacher_id"));
                section.setGender_type(resultSet.getInt("gender_type"));
                section.setSection_number(resultSet.getInt("section_number"));
                section.setSize(resultSet.getInt("size"));
            }
            
            return section;
        }
        
        return null;
    }

    /**
     *
     * get All Courses for this Part
     * 
     */
    public  ArrayList getCourses(DbCall call, int major_id, int level_number, int semester) throws SQLException {
        
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from courses where major_id= "+ major_id + " AND level_number = "+ level_number +" AND semester = "+ semester);

        courseThreeList = new ArrayList<>();
        courseTwoList = new ArrayList<>();
        ArrayList<Course> courseList = new ArrayList<>();

        while(resultSet.next()) {

            Course course = new Course();

            course.setId(resultSet.getInt("id"));
            course.setName(resultSet.getString("name"));
            course.setType(resultSet.getInt("type"));
            course.setMajor_id(resultSet.getInt("major_id"));
            course.setLevel_number(resultSet.getInt("level_number"));
            course.setSemester(resultSet.getInt("semester"));
            course.setCredit_hours(resultSet.getInt("credit_hours"));
            course.setActual_hours(resultSet.getInt("actual_hours"));
            course.setCan_devide(resultSet.getInt("can_devide"));
            
            if (course.getActual_hours() == 3 && course.getCan_devide() == 1) {
                courseThreeList.add(course);
                continue;
            }else if(course.getActual_hours() == 2 && course.getCan_devide() == 1){
                courseTwoList.add(course);
                continue;
            }
            
            courseList.add(course);
        }
        return courseList;
    }
 
    /**
     * 
     * Get All Section For this Course
     *
     * @return 
     */
    public  ArrayList getSections(DbCall call, int courseId, int majorId) throws SQLException{

        sectionListMale = new ArrayList<>();
        sectionListFemale = new ArrayList<>();
        
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from sections where course_id=" + courseId + " AND major_id=" + majorId);

        if (resultSet == null) {
            System.out.println("Null");
        }
        
        ArrayList<Section> sectionList = new ArrayList<>();
        
        while(resultSet.next()){

            Section section = new Section();

            section.setId(resultSet.getInt("id"));
            section.setCourse_id(resultSet.getInt("course_id"));
            section.setMajor_id(resultSet.getInt("major_id"));
            section.setTeacher_id(resultSet.getInt("teacher_id"));
            section.setGender_type(resultSet.getInt("gender_type"));
            section.setSection_number(resultSet.getInt("section_number"));
            section.setSize(resultSet.getInt("size"));

            if (section.getGender_type() == 1) {
                sectionListMale.add(section);
            }else if (section.getGender_type() == 2) {
                sectionListFemale.add(section);   
            }
        }
        return null;
    }

    /**
     * 
     * Get All Majors For this Course
     *
     * @return 
     */
    public  ArrayList getCourseMajors(DbCall call, int course_id) throws SQLException {
        
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from courses_majors where course_id = " + course_id);

        ArrayList<Integer> coursesMajorsList = new ArrayList<>();

        while(resultSet.next()) {
            coursesMajorsList.add(resultSet.getInt("major_id"));
        }
        return coursesMajorsList;
    }
    
    /**
     * 
     * Get specific TimeslotDay
     *
     * @return 
     */
    public  TimeslotDay getTimeslotDay(DbCall call, int timeslot_day_id) throws SQLException{
        
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from timeslots_days where id = " + timeslot_day_id);

        TimeslotDay timeslotDay = new TimeslotDay();

        while(resultSet.next()) {
          
            timeslotDay.setId(resultSet.getInt("id"));
            timeslotDay.setTimeslot_id(resultSet.getInt("timeslot_id"));
            timeslotDay.setDay_id_o(resultSet.getInt("day_id_o"));
            timeslotDay.setDay_id_t(resultSet.getInt("day_id_t"));
            timeslotDay.setDay_id_th(resultSet.getInt("day_id_th"));
            timeslotDay.setDay_id_f(resultSet.getInt("day_id_f"));
            timeslotDay.setDay_id_fi(resultSet.getInt("day_id_fi"));
        }
        return timeslotDay;
    }
    
   
    
    /**
     *
     * Select Timeslot and Days for Course and Section
     *
     */
    public  ArrayList getTimeslotTwoHour(DbCall call) throws SQLException{
        
        ResultSet resultSet;
        
        resultSet = call.getExecuteQuery("select * from timeslots_days where timeslot_id = 1 AND day_id_o < 8 AND day_id_t < 8 AND day_id_th=8 AND day_id_f=8 AND day_id_fi=8");
        ArrayList<TimeslotDay> timeslotDayList = new ArrayList<>();

        while(resultSet.next()){

            TimeslotDay timeslotDay = new TimeslotDay();

            timeslotDay.setId(resultSet.getInt("id"));
            timeslotDay.setTimeslot_id(resultSet.getInt("timeslot_id"));
            timeslotDay.setDay_id_o(resultSet.getInt("day_id_o"));
            timeslotDay.setDay_id_t(resultSet.getInt("day_id_t"));
            timeslotDay.setDay_id_th(resultSet.getInt("day_id_th"));
            timeslotDay.setDay_id_f(resultSet.getInt("day_id_f"));
            timeslotDay.setDay_id_fi(resultSet.getInt("day_id_fi"));

            timeslotDayList.add(timeslotDay);
        }
        
        return timeslotDayList;
    }
    
    /**
     *
     * Select Timeslot and Days for Course and Section
     *
     */
    public  ArrayList getTimeslotThreeHour(DbCall call) throws SQLException{
        
        ResultSet resultSet;
        
        resultSet = call.getExecuteQuery("select * from timeslots_days where (timeslot_id = 1 AND day_id_o < 8 AND day_id_t < 8 AND day_id_th < 8 AND day_id_f = 8 AND day_id_fi = 8 ) OR timeslot_id = 2");
        ArrayList<TimeslotDay> timeslotDayList = new ArrayList<>();

        while(resultSet.next()){

            TimeslotDay timeslotDay = new TimeslotDay();

            timeslotDay.setId(resultSet.getInt("id"));
            timeslotDay.setTimeslot_id(resultSet.getInt("timeslot_id"));
            timeslotDay.setDay_id_o(resultSet.getInt("day_id_o"));
            timeslotDay.setDay_id_t(resultSet.getInt("day_id_t"));
            timeslotDay.setDay_id_th(resultSet.getInt("day_id_th"));
            timeslotDay.setDay_id_f(resultSet.getInt("day_id_f"));
            timeslotDay.setDay_id_fi(resultSet.getInt("day_id_fi"));

            timeslotDayList.add(timeslotDay);
        }
        
        return timeslotDayList;
    }
    
    /**
     *
     * Select Timeslot and Days for Course and Section
     *
     */
    public  ArrayList getTimeslotOneHourButTwoHourActual(DbCall call) throws SQLException{
        
        ResultSet resultSet;
        
        resultSet = call.getExecuteQuery("select * from timeslots_days where timeslot_id = 3");
        ArrayList<TimeslotDay> timeslotDayList = new ArrayList<>();

        while(resultSet.next()){

            TimeslotDay timeslotDay = new TimeslotDay();

            timeslotDay.setId(resultSet.getInt("id"));
            timeslotDay.setTimeslot_id(resultSet.getInt("timeslot_id"));
            timeslotDay.setDay_id_o(resultSet.getInt("day_id_o"));
            timeslotDay.setDay_id_t(resultSet.getInt("day_id_t"));
            timeslotDay.setDay_id_th(resultSet.getInt("day_id_th"));
            timeslotDay.setDay_id_f(resultSet.getInt("day_id_f"));
            timeslotDay.setDay_id_fi(resultSet.getInt("day_id_fi"));

            timeslotDayList.add(timeslotDay);
        }
        
        return timeslotDayList;
    }
    
        
    /**
     *
     *Return All Study Times 
     */
    public void getAllStudyTimes() throws SQLException{
        
        dayList = new ArrayList<>();
        dayListFemale = new ArrayList<>();
        
        DbCall call = DbCall.getDbCall();
        
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from studytimes");

        while(resultSet.next()) {

            StudyTimes studyTimes = new StudyTimes();

            studyTimes.setId(resultSet.getInt("id"));
            studyTimes.setDay_id(resultSet.getInt("day_id"));
            studyTimes.setStart(resultSet.getFloat("start"));
            studyTimes.setEnd(resultSet.getFloat("end"));
            studyTimes.setTimeslot_id(resultSet.getInt("timeslot_id"));

            dayList.add(studyTimes);
            dayListFemale.add(studyTimes);
        }
    }
   
    /**
     *
     * filter to All Study time to get one of them for spesfic timeslot 
     */
    public  StudyTimes getStudyTimesForTimeSlotDay(TimeslotDay timeslotDay, Course course, Section section, int sex) throws SQLException{
        
        DbCall call = DbCall.getDbCall();
        boolean flag = false;
        ArrayList<FinalRaw> teacherStudyTimeList =  getAllFinalTableForOneTeacher(call , section.getTeacher_id());

        if (sex == 1) {
        
            for(int i = 0 ; i < dayList.size() ; i++ ){
                
                if (dayList.get(i).getTimeslot_id() == timeslotDay.getTimeslot_id()) {

                    if (dayList.get(i).getDay_id() == timeslotDay.getDay_id_o()){

                        StudyTimes studyTimes = dayList.get(i);

                        if (teacherStudyTimeList.size() > 0) {
                            
                            for (int j = 0; j < teacherStudyTimeList.size(); j++) {
                                
                                if (teacherStudyTimeList.get(j).getTimeslots_day_id() != 0) {
                                    TimeslotDay timeslotDayForTeacher = getTimesloteForOneTeacher(call , teacherStudyTimeList.get(j).getTimeslots_day_id());

                                    if (dayList.get(i).getDay_id() == timeslotDayForTeacher.getDay_id_o() 
                                            && ((teacherStudyTimeList.get(j).getStartTime() == studyTimes.getStart()) 
                                            || (teacherStudyTimeList.get(j).getStartTime() == studyTimes.getStart() 
                                                && teacherStudyTimeList.get(j).getEndTime() == studyTimes.getEnd() ) 
                                            || (teacherStudyTimeList.get(j).getStartTime() < studyTimes.getStart() 
                                                && teacherStudyTimeList.get(j).getEndTime() > studyTimes.getStart()) 
                                            || (teacherStudyTimeList.get(j).getStartTime() < studyTimes.getEnd() 
                                                && teacherStudyTimeList.get(j).getEndTime() >= studyTimes.getEnd()) ) ){
                                        flag = true;
                                    }
                                }
                            }
                        }

                        if (flag == true) {
                            flag = false;
                            continue;
                        }

                        dayList.remove(i);

                        for (int j = 0; j < dayList.size(); j++) {
                            if (dayList.get(j).getDay_id() == timeslotDay.getDay_id_o() 
                                    && ((dayList.get(j).getStart() == studyTimes.getStart()) 
                                    || (dayList.get(j).getStart() == studyTimes.getStart() 
                                        && dayList.get(j).getEnd() == studyTimes.getEnd() ) 
                                    || (dayList.get(j).getStart() < studyTimes.getStart() 
                                        && dayList.get(j).getEnd() > studyTimes.getStart()) 
                                    || (dayList.get(j).getStart() < studyTimes.getEnd() 
                                        && dayList.get(j).getEnd() >= studyTimes.getEnd()) ) ) {
                                dayList.remove(j);
                            }
                        }

                        if (timeslotDay.getDay_id_t() < 8) {

                            for (int j = 0; j < dayList.size(); j++) { // && dayList.get(j).getStart() < studyTimes.getEnd()
                                if (dayList.get(j).getDay_id() == timeslotDay.getDay_id_t() && ((dayList.get(j).getStart() == studyTimes.getStart()) || (dayList.get(j).getStart() == studyTimes.getStart() && dayList.get(j).getEnd() == studyTimes.getEnd() ) || (dayList.get(j).getStart() < studyTimes.getStart() && dayList.get(j).getEnd() > studyTimes.getStart()) || (dayList.get(j).getStart() < studyTimes.getEnd() && dayList.get(j).getEnd() >= studyTimes.getEnd()) ) ) {
                                    dayList.remove(j);
                                }
                            }

                            if (timeslotDay.getDay_id_th() < 8) {
                                for (int j = 0; j < dayList.size(); j++) {
                                    if (dayList.get(j).getDay_id() == timeslotDay.getDay_id_th() && ((dayList.get(j).getStart() == studyTimes.getStart()) || (dayList.get(j).getStart() == studyTimes.getStart() && dayList.get(j).getEnd() == studyTimes.getEnd() ) || (dayList.get(j).getStart() < studyTimes.getStart() && dayList.get(j).getEnd() > studyTimes.getStart()) || (dayList.get(j).getStart() < studyTimes.getEnd() && dayList.get(j).getEnd() >= studyTimes.getEnd()) )) {
                                        dayList.remove(j);
                                    }
                                }
                            }
                        }
                        return studyTimes;
                    }
                }
            }
        }else if(sex == 2){
            
            for(int i = 0 ; i < dayListFemale.size() ; i++ ){
            
                if (dayListFemale.get(i).getTimeslot_id() == timeslotDay.getTimeslot_id()) {

                    if (dayListFemale.get(i).getDay_id() == timeslotDay.getDay_id_o()){

                        StudyTimes studyTimes = dayListFemale.get(i);
                        
                        if (teacherStudyTimeList.size() > 0) {
                            for (int j = 0; j < teacherStudyTimeList.size(); j++) {
                                
                                if (teacherStudyTimeList.get(j).getTimeslots_day_id() != 0) {
                                    
                                    TimeslotDay timeslotDayForTeacher = getTimesloteForOneTeacher(call , teacherStudyTimeList.get(j).getTimeslots_day_id());

                                    if (dayListFemale.get(i).getDay_id() == timeslotDayForTeacher.getDay_id_o() 
                                            && ((teacherStudyTimeList.get(j).getStartTime() == studyTimes.getStart()) 
                                            || (teacherStudyTimeList.get(j).getStartTime() == studyTimes.getStart() 
                                                && teacherStudyTimeList.get(j).getEndTime() == studyTimes.getEnd() ) 
                                            || (teacherStudyTimeList.get(j).getStartTime() < studyTimes.getStart() 
                                                && teacherStudyTimeList.get(j).getEndTime() > studyTimes.getStart()) 
                                            || (teacherStudyTimeList.get(j).getStartTime() < studyTimes.getEnd() 
                                                && teacherStudyTimeList.get(j).getEndTime() >= studyTimes.getEnd()) ) ){
                                        flag = true;
                                    }
                                }
                            }
                        }
                        
                        if (flag == true) {
                            flag = false;
                            continue;
                        }
                        
                        dayListFemale.remove(i);
                        
                        for (int j = 0; j < dayListFemale.size(); j++) {
                            if (dayListFemale.get(j).getDay_id() == timeslotDay.getDay_id_o() && ((dayListFemale.get(j).getStart() == studyTimes.getStart()) || (dayListFemale.get(j).getStart() == studyTimes.getStart() && dayListFemale.get(j).getEnd() == studyTimes.getEnd() ) || (dayListFemale.get(j).getStart() < studyTimes.getStart() && dayListFemale.get(j).getEnd() > studyTimes.getStart()) || (dayListFemale.get(j).getStart() < studyTimes.getEnd() && dayListFemale.get(j).getEnd() >= studyTimes.getEnd()) ) ) {
                                dayListFemale.remove(j);
                            }
                        }

                        if (timeslotDay.getDay_id_t() < 8) {

                            for (int j = 0; j < dayListFemale.size(); j++) {
                                if (dayListFemale.get(j).getDay_id() == timeslotDay.getDay_id_t() && ((dayListFemale.get(j).getStart() == studyTimes.getStart()) || (dayListFemale.get(j).getStart() == studyTimes.getStart() && dayListFemale.get(j).getEnd() == studyTimes.getEnd() ) || (dayListFemale.get(j).getStart() < studyTimes.getStart() && dayListFemale.get(j).getEnd() > studyTimes.getStart()) || (dayListFemale.get(j).getStart() < studyTimes.getEnd() && dayListFemale.get(j).getEnd() >= studyTimes.getEnd()) )) {
                                    dayListFemale.remove(j);
                                }
                            }

                            if (timeslotDay.getDay_id_th() < 8) {
                                for (int j = 0; j < dayListFemale.size(); j++) {
                                    if (dayListFemale.get(j).getDay_id() == timeslotDay.getDay_id_th() && ((dayListFemale.get(j).getStart() == studyTimes.getStart()) || (dayListFemale.get(j).getStart() == studyTimes.getStart() && dayListFemale.get(j).getEnd() == studyTimes.getEnd() ) || (dayListFemale.get(j).getStart() < studyTimes.getStart() && dayListFemale.get(j).getEnd() > studyTimes.getStart()) || (dayListFemale.get(j).getStart() < studyTimes.getEnd() && dayListFemale.get(j).getEnd() >= studyTimes.getEnd()) ) ) {
                                        dayListFemale.remove(j);
                                    }
                                }
                            }
                        }
                        return studyTimes;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 
     * Get all FinalRaw that done befor for this major
     *
     * @return 
     */
    public  ArrayList getRawTableDone(DbCall call, int major_id, int level_number, int semester) throws SQLException{
                
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from final_table where timeslots_day_id != " + 17 + " and major_id = " + major_id + " and semester = " + semester + " and level_number = " + level_number);

        ArrayList<FinalRaw> rawTableDoneList = new ArrayList<>();

        while(resultSet.next()) {
            
            FinalRaw raw = new FinalRaw();

            raw.setSection_id(resultSet.getInt("section_id"));
            raw.setRoom_id(resultSet.getInt("room_id"));
            raw.setTimeslots_day_id(resultSet.getInt("timeslots_day_id"));
            raw.setStartTime(resultSet.getDouble("start"));
            raw.setEndTime(resultSet.getDouble("end"));
            raw.setTeacher_id(resultSet.getInt("teacher_id"));
            raw.setMajor_id(resultSet.getInt("major_id"));
            raw.setLevel_number(resultSet.getInt("level_number"));
            raw.setSemester(resultSet.getInt("semester"));
            
            rawTableDoneList.add(raw);
        }
        
        if (rawTableDoneList.size() > 0) {
            int rs = -1;
            rs = call.getExecuteUpdate("DELETE FROM `final_table` where major_id = " + major_id);
        }
        return rawTableDoneList;
    }
    
     public ArrayList getAllFinalTableForOneTeacher(DbCall call, int teacher_id) throws SQLException {
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from final_table where teacher_id = " + teacher_id);

        ArrayList<FinalRaw> rawTableDoneForTeacherList = new ArrayList<>();

        while(resultSet.next()) {
            
            FinalRaw raw = new FinalRaw();

            raw.setSection_id(resultSet.getInt("section_id"));
            raw.setRoom_id(resultSet.getInt("room_id"));
            raw.setTimeslots_day_id(resultSet.getInt("timeslots_day_id"));
            raw.setStartTime(resultSet.getDouble("start"));
            raw.setEndTime(resultSet.getDouble("end"));
            raw.setTeacher_id(resultSet.getInt("teacher_id"));
            raw.setMajor_id(resultSet.getInt("major_id"));
            raw.setLevel_number(resultSet.getInt("level_number"));
            raw.setSemester(resultSet.getInt("semester"));
            
            rawTableDoneForTeacherList.add(raw);
        }
        
        return rawTableDoneForTeacherList;
    }
     
     public TimeslotDay getTimesloteForOneTeacher(DbCall call, int timeslots_day_id) throws SQLException {
         
        ResultSet resultSet;
        resultSet = call.getExecuteQuery("select * from timeslots_days where id = " + timeslots_day_id);

        TimeslotDay timeslotDay = new TimeslotDay();

        while(resultSet.next()) {
          
            timeslotDay.setId(resultSet.getInt("id"));
            timeslotDay.setTimeslot_id(resultSet.getInt("timeslot_id"));
            timeslotDay.setDay_id_o(resultSet.getInt("day_id_o"));
            timeslotDay.setDay_id_t(resultSet.getInt("day_id_t"));
            timeslotDay.setDay_id_th(resultSet.getInt("day_id_th"));
            timeslotDay.setDay_id_f(resultSet.getInt("day_id_f"));
            timeslotDay.setDay_id_fi(resultSet.getInt("day_id_fi"));
        }
        return timeslotDay;
    }
    
    /**
     *
     * Return Raw in Final Table
     * 
     */
    public  FinalRaw getRaw(DbCall call, Course course , Section section, int sex) throws SQLException{

        if (step == 1) {
            for (int i = 0; i < dayListMale.size(); i++) {
                for (int j = 0; j < dayListFemale.size(); j++) {
                    if (dayListMale.get(i).getId() == dayListFemale.get(j).getId()) {
                        dayListFemale.remove(j);
                    }
                }
            }
        }
        
        
        FinalRaw raw = new FinalRaw();
        raw.setSection_id(section.getId());

        /** 
         * get a raw for First Section
         * 
         */
        if(course.getCredit_hours() > 1 && course.getActual_hours() > 1 && course.getCan_devide() == 1){

                if (course.getActual_hours() == 2) {

                    ArrayList<TimeslotDay> timeslotDayList = getTimeslotTwoHour(call);
                    Collections.shuffle(timeslotDayList);

                    for(int m = 0 ; m < timeslotDayList.size() ; m++){
                        
                        TimeslotDay timeslotDay = timeslotDayList.get(m);
                        StudyTimes day = getStudyTimesForTimeSlotDay(timeslotDay, course , section,sex);

                        if (day == null) {
                            continue;
                        }
                        
                        if (step == 0) {
                            dayListMale.add(day);
                        }
                        
                        raw.setTimeslots_day_id(timeslotDay.getId());
                        raw.setStartTime(day.getStart());
                        raw.setEndTime(day.getEnd());
                        raw.setMajor_id(course.getMajor_id());
                        raw.setTeacher_id(section.getTeacher_id());
                        raw.setLevel_number(course.getLevel_number());
                        raw.setSemester(course.getSemester());
                        
                        ArrayList<Integer> coursesMajorsList = getCourseMajors(call,course.getId());

                        if(coursesMajorsList.size() > 0){
                            for(int i = 0 ; i < coursesMajorsList.size(); i++){
                                FinalRaw rawMajors = new FinalRaw();                                
                                rawMajors.setSection_id(section.getId());
                                rawMajors.setTimeslots_day_id(raw.getTimeslots_day_id());
                                rawMajors.setStartTime(raw.getStartTime());
                                rawMajors.setEndTime(raw.getEndTime());
                                rawMajors.setTeacher_id(section.getTeacher_id());
                                rawMajors.setMajor_id(coursesMajorsList.get(i));
                                rawMajors.setLevel_number(raw.getLevel_number());
                                rawMajors.setSemester(raw.getSemester());
                                finalRawAtherMajorsList.add(rawMajors);
                            }
                        }
                        break; 
                    }
                }
                if (course.getActual_hours() == 3) {

                    ArrayList<TimeslotDay> timeslotDayThreeList = getTimeslotThreeHour(call);
                    Collections.shuffle(timeslotDayThreeList);
                    
                    for(int m = 0 ; m < timeslotDayThreeList.size() ; m++){

                        TimeslotDay timeslotDay = timeslotDayThreeList.get(m);
                        StudyTimes day = getStudyTimesForTimeSlotDay(timeslotDay, course , section, sex);

                        if (day == null) {
                            continue;
                        }
                        
                        if (step == 0) {
                            dayListMale.add(day);
                        }
                        
                        raw.setTimeslots_day_id(timeslotDay.getId());
                        raw.setStartTime(day.getStart());
                        raw.setEndTime(day.getEnd());
                        raw.setTeacher_id(section.getTeacher_id());
                        raw.setMajor_id(course.getMajor_id());
                        raw.setLevel_number(course.getLevel_number());
                        raw.setSemester(course.getSemester());
                        
                        ArrayList<Integer> coursesMajorsList = getCourseMajors(call,course.getId());
                        
                        if(coursesMajorsList.size() > 0){
                            for(int i = 0 ; i < coursesMajorsList.size(); i++){
                                FinalRaw rawMajors = new FinalRaw();
                                rawMajors.setSection_id(section.getId());
                                rawMajors.setTimeslots_day_id(raw.getTimeslots_day_id());
                                rawMajors.setStartTime(raw.getStartTime());
                                rawMajors.setEndTime(raw.getEndTime());
                                rawMajors.setTeacher_id(section.getTeacher_id());
                                rawMajors.setMajor_id(coursesMajorsList.get(i));
                                rawMajors.setLevel_number(raw.getLevel_number());
                                rawMajors.setSemester(raw.getSemester());
                                finalRawAtherMajorsList.add(rawMajors);
                            }
                        }
                        break;
                    }
                }
            }else if (course.getCredit_hours() >= 0 && course.getActual_hours() > 1 && course.getCan_devide() == 0) {

                ArrayList<TimeslotDay> timeslotDayOneList = getTimeslotOneHourButTwoHourActual(call);
                Collections.shuffle(timeslotDayOneList);

                for(int m = 0 ; m < timeslotDayOneList.size() ; m++) {

                    TimeslotDay timeslotDay = timeslotDayOneList.get(m);
                    StudyTimes day = getStudyTimesForTimeSlotDay(timeslotDay, course , section, sex);

                    if (day == null) {
                        continue;
                    }
                    
                    if (step == 0) {
                        dayListMale.add(day);
                    }

                    raw.setTimeslots_day_id(timeslotDay.getId());
                    raw.setStartTime(day.getStart());
                    raw.setEndTime(day.getEnd());
                    raw.setTeacher_id(section.getTeacher_id());
                    raw.setMajor_id(course.getMajor_id());
                    raw.setLevel_number(course.getLevel_number());
                    raw.setSemester(course.getSemester());

                    ArrayList<Integer> coursesMajorsList = getCourseMajors(call,course.getId());

                    if(coursesMajorsList.size() > 0){
                        for(int i = 0 ; i < coursesMajorsList.size(); i++){
                            FinalRaw rawMajors = new FinalRaw();
                            rawMajors.setSection_id(section.getId());
                            rawMajors.setTimeslots_day_id(raw.getTimeslots_day_id());
                            rawMajors.setStartTime(raw.getStartTime());
                            rawMajors.setEndTime(raw.getEndTime());
                            rawMajors.setTeacher_id(section.getTeacher_id());
                            rawMajors.setMajor_id(coursesMajorsList.get(i));
                            rawMajors.setLevel_number(raw.getLevel_number());
                            rawMajors.setSemester(raw.getSemester());
                            finalRawAtherMajorsList.add(rawMajors);
                        }
                    }
                    break;
                }
            }
        return raw;
    }
    
    public  int insertFinalTable(DbCall call, MajorTable majorTable){
        
        // call.getExecuteUpdate("ALTER TABLE final_table AUTO_INCREMENT = 1");

        int resultSet = -1;
        for (int i = 0; i < majorTable.getMajorTable().size(); i++) {
            FinalRaw raw = majorTable.getMajorTable().get(i);
            resultSet = call.getExecuteUpdate("INSERT INTO `final_table`(`section_id`, `timeslots_day_id`, `start`, `end`, `teacher_id` , `major_id`, `level_number`, `semester`) VALUES (" + raw.getSection_id()  + "," + raw.getTimeslots_day_id()+ "," + raw.getStartTime() + "," + raw.getEndTime()+ "," + raw.getTeacher_id()+ "," + raw.getMajor_id() + "," + raw.getLevel_number() + "," + raw.getSemester()+")");
        }
        return resultSet;
    }
    //------------------------------------------------------------------------------------------------------------------------
    //        ---------  END  ---------  ALGORITHM METHODS ---------
    //------------------------------------------------------------------------------------------------------------------------
    
}
