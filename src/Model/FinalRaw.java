
package Model;

public class FinalRaw {

    private int section_id;
    private int room_id;
    private int timeslots_day_id;
    private double startTime;
    private double endTime;
    private int teacher_id;
    private int major_id;
    private int level_number;
    private int semester;

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getTimeslots_day_id() {
        return timeslots_day_id;
    }

    public void setTimeslots_day_id(int timeslots_day_id) {
        this.timeslots_day_id = timeslots_day_id;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
    
    

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
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
}
