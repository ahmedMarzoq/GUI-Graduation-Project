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
public class sectionTable {
    int sectionNum,size;
    String courseId,courseName,teacherName,studentGender;

    public sectionTable(int sectionNum, int size, String courseId, String courseName, String teacherName, String studentGender) {
        this.sectionNum = sectionNum;
        this.size = size;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.studentGender = studentGender;
    }

    
    public int getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(int sectionNum) {
        this.sectionNum = sectionNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
    
}
