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
public class TeacherTable {

    int id, houres;
    String name;

    public TeacherTable(int id, int houres, String name) {
        this.id = id;
        this.houres = houres;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHoures() {
        return houres;
    }

    public void setHoures(int houres) {
        this.houres = houres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
