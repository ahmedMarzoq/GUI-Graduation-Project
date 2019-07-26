/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Flash Tech
 */
public class finalTableView {
    int id,sectionNumber;
    String courseName,courseNumber,teacherName,saturday,sunday,monday,tusday,wednesday;

    public finalTableView(int id, int sectionNumber, String courseName, String courseNumber, String teacherName, String saturday, String sunday, String monday, String tusday, String wednesday) {
        this.id = id;
        this.sectionNumber = sectionNumber;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.teacherName = teacherName;
        this.saturday = saturday;
        this.sunday = sunday;
        this.monday = monday;
        this.tusday = tusday;
        this.wednesday = wednesday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTusday() {
        return tusday;
    }

    public void setTusday(String tusday) {
        this.tusday = tusday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }
    
    
}
