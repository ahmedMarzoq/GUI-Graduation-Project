package javafxapplication2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Flash Tech
 */
public class AddSectionFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label spech;

    @FXML
    private Label level;

    void setSpechLevel(String level, String spech) {
        this.spech.setText(spech);
        this.level.setText(level);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
