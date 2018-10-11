/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;

/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable
{

    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;
    
    
    private boolean lastTurn = true;
    private MenuWindowController menuWindow;
    private IGameModel game;
    private String firstName;
    private String secondName;
    private int AI;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Button btn = (Button) event.getSource();
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            System.out.println("row " + row);
            System.out.println("col " + col);
            System.out.println("");
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getPlayer();
            if(btn.getText()!=("X") && btn.getText()!=("O")){
                if (game.play(c, r) )
                {
                    if (!game.isGameOver() )
                    {


                        String xOrO = player == 1 ? "X" : "O";
                        
                        btn.setText(xOrO);
                        setPlayer();
                    }
                    else

                    {
                        if(lastTurn){

                            String xOrO = player == 1 ? "X" : "O";
                            btn.setText(xOrO);
                            lastTurn = false;
                        }
                        int winner = game.getWinner();
                        displayWinner(winner);
                        

                    }
                }
            }
            
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
        lastTurn = true;
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lblPlayer.setText(firstName+" turn.");
        game = new GameBoard();
        setPlayer();
        game.setCols(3);
        game.setRows(3);
    }

    private void setPlayer()
    {
        String message = " ";
        switch (game.getNextPlayer())
        {
            
            case 1:
                message = firstName+" turn.";
                break;
            case 2:
                message = secondName+ " turn.";
                break;
           
        }
        lblPlayer.setText(message);
    }
    public void setMainWindow(MenuWindowController window)
    {
        menuWindow = window;
    }

    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            case 1:
                message = firstName+" wins!!!";
                break;
            case 2:
                message = secondName+ " wins!!!";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }

    

    void setInfo(String FirstName, String SecondName, int AI) {
        this.firstName= FirstName;
        this.secondName = SecondName;
        this.AI = AI;
    }

}
