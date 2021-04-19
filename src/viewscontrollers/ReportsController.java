/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewscontrollers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author yamif
 */
public class ReportsController implements Initializable {

    @FXML
    private Tab contactTabController;
    @FXML
    private ComboBox<?> contactBox;
    @FXML
    private TableView<?> contactTable;
    @FXML
    private TableColumn<?, ?> AppointmentIDColumn;
    @FXML
    private TableColumn<?, ?> TitleColumn;
    @FXML
    private TableColumn<?, ?> TypeColumn;
    @FXML
    private TableColumn<?, ?> DescriptionColumn;
    @FXML
    private TableColumn<?, ?> startColumn;
    @FXML
    private TableColumn<?, ?> endColumn;
    @FXML
    private TableColumn<?, ?> customerColumn;
    @FXML
    private Tab ownTab;
    @FXML
    private Tab MonthTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void contactBoxController(ActionEvent event) {
    }
    
}
