package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class MajorTable {

    private ArrayList<FinalRaw> majorTable;
    
    private ArrayList<StudyTimes> day1;
    private ArrayList<StudyTimes> day2;
    private ArrayList<StudyTimes> day3;
    private ArrayList<StudyTimes> day4;
    private ArrayList<StudyTimes> day5;

    public MajorTable() throws SQLException {
        majorTable = new ArrayList<>();
    }

    public ArrayList<FinalRaw> getMajorTable() {
        return majorTable;
    }

    public void setMajorTable(ArrayList<FinalRaw> majorTable) {
        this.majorTable = majorTable;
    }

    public ArrayList<StudyTimes> getDay1() {
        return day1;
    }

    public void setDay1(ArrayList<StudyTimes> day1) {
        this.day1 = day1;
    }

    public ArrayList<StudyTimes> getDay2() {
        return day2;
    }

    public void setDay2(ArrayList<StudyTimes> day2) {
        this.day2 = day2;
    }

    public ArrayList<StudyTimes> getDay3() {
        return day3;
    }

    public void setDay3(ArrayList<StudyTimes> day3) {
        this.day3 = day3;
    }

    public ArrayList<StudyTimes> getDay4() {
        return day4;
    }

    public void setDay4(ArrayList<StudyTimes> day4) {
        this.day4 = day4;
    }

    public ArrayList<StudyTimes> getDay5() {
        return day5;
    }

    public void setDay5(ArrayList<StudyTimes> day5) {
        this.day5 = day5;
    }
   
}
