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
public class CourseTable {
    
    private int id;
    private String name;
    private String type;
    private String major_id;
    private String level_number;
    private String semester;
    private String course_number;
    private int credit_hours ;
    private int actual_hours;
    private String can_devide;

    public CourseTable(int id, String name, String type, String major_id, String level_number, String semester, String course_number, int credit_hours, int actual_hours, String can_devide) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.major_id = major_id;
        this.level_number = level_number;
        this.semester = semester;
        this.course_number = course_number;
        this.credit_hours = credit_hours;
        this.actual_hours = actual_hours;
        this.can_devide = can_devide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getLevel_number() {
        return level_number;
    }

    public void setLevel_number(String level_number) {
        this.level_number = level_number;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse_number() {
        return course_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }

    public int getCredit_hours() {
        return credit_hours;
    }

    public void setCredit_hours(int credit_hours) {
        this.credit_hours = credit_hours;
    }

    public int getActual_hours() {
        return actual_hours;
    }

    public void setActual_hours(int actual_hours) {
        this.actual_hours = actual_hours;
    }

    public String getCan_devide() {
        return can_devide;
    }

    public void setCan_devide(String can_devide) {
        this.can_devide = can_devide;
    }
    
}
