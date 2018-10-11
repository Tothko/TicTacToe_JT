/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tothko
 */
public class MenuWindowController implements Initializable {

    @FXML
    private Button startButton;
    @FXML
    private TextField firstPlayerName;
    @FXML
    private TextField secondPlayerName;
    @FXML
    private ChoiceBox<String> AISelection;

    boolean AI_Enable = false;
    @FXML
    private Button enableAISelections;
    
    @FXML
    private ChoiceBox<String> resolutionSelection;
    
    private int currentIA = -1;
    private int currentResolution = 0;
    String[] AvailableIA= new String[]{ "EzPz", "YourClone","TheGuySheTellsYouNotToWorryAbout" };
    int rezX = 700;
    int rezY = 700;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                populateOnce();
    }    

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        try {
        Stage stage;
        Parent root1;
        
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tictactoe/gui/views/TicTacView.fxml"));
            root1 = (Parent) fxmlLoader.load(); 
            TicTacViewController controller = fxmlLoader.<TicTacViewController>getController();    
            String FirstName = firstPlayerName.getText();
            String SecondName = secondPlayerName.getText();
            if(FirstName == null || FirstName.length() == 0){
                FirstName = "X";
                
            }
            if(SecondName == null || SecondName.length() == 0){
                SecondName = "O";
            }
            if(AI_Enable == true )controller.setInfo(FirstName,SecondName,currentIA);
            else controller.setInfo(FirstName,SecondName,-1);
            stage = (Stage) startButton.getScene().getWindow();
            stage.setTitle("TicTacToePlayground");
            stage.setScene(new Scene(root1, rezX, rezY));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        } 
    }

    @FXML
    private void AISelections(MouseEvent event) {
        if(AI_Enable==false)
        {           
            enableAISelections.setText("AI mode is selected");
            AISelection.setDisable(false);
            secondPlayerName.setDisable(true);  
            AI_Enable=true;
        }
        else
        {
             enableAISelections.setText("Versus mode is selected");
             secondPlayerName.setDisable(false);
             AISelection.setDisable(true);
             AI_Enable=false;
        }
    }

    private void populateOnce() {
        resolutionSelection.setItems(FXCollections.observableArrayList(
                  "800 x 640",  "1024 x 768", "1280 x 800","1280 x 1024","1366 x 768","1680 x 1050","1920 x 1080")
        );
        resolutionSelection.getSelectionModel().selectedIndexProperty()
            .addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        switch (newValue.intValue()) {
            case 0:  
                    rezX = 800;
                    rezY = 640;
                    break;
            case 1:  
                    rezX = 1024;
                    rezY = 768;
                     break;
            case 2:  
                    rezX = 1280;
                    rezY = 800;
                     break;
            case 3:  
                    rezX = 1280;
                    rezY = 1024;
                     break;
            case 4:  
                    rezX = 1366;
                    rezY = 768;
                     break;
            case 5:  
                    rezX = 1680;
                    rezY = 1050;
                     break;
            case 6:  
                    rezX = 1920;
                    rezY = 1080;
                     break;
            default: 
                    rezX = 700;
                    rezY = 700;
                     break;
        }
                }
        });
        AISelection.setItems(FXCollections.observableArrayList(
                    "EzPz", "YourClone","TheGuySheTellsYouNotToWorryAbout")
        );
        AISelection.getSelectionModel().selectedIndexProperty()
            .addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        currentIA = newValue.intValue();
                        if (newValue.intValue() !=-1){
                            secondPlayerName.setText(AvailableIA[newValue.intValue()]);
                        }
                }
        });
    }
}
