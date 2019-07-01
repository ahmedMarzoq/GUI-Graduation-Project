
package Model;

public class Course {
    
    private int id;
    private String name;
    private int level_number;
    private int semester;
    private int course_number;
    private int credit_hours ;
    private int actual_hours;
    private boolean can_devide;

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

    public int getLevel_number() {
        return level_number;
    }

    public void setLevel_number(int level_number) {
        this.level_number = level_number;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    public int getCourse_number() {
        return course_number;
    }

    public void setCourse_number(int course_number) {
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

    public boolean getCan_devide() {
        return can_devide;
    }

    public void setCan_devide(boolean can_devide) {
        this.can_devide = can_devide;
    }
     
}
