/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    private int player = 2;
    private int row = 3;
    private int col = 3;
    private int[][] board = new int[row][col];
    
    
    private boolean isDraw = false;
    
    private int victoryCon = 3;
    private int turn = 0;
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        
        if(player == 2) player = 1;
        else if (player == 1) player = 2;
        turn++;
        
        return player;
        }
    
    

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
            
    {
        
        board[col][row] = getPlayer();
        
        for (int i = 0; i < 3; i++) {
            System.out.println("");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[j][i]);
                
        
               
                
            }
            
        }
        
        
        return true;
    }

    public boolean isGameOver()
    {
         short rowCount = 0;
         short colCOunt = 0;
         short diagonalCount = 1;
         short diagonalCount2 = 1;
        for (int i = 0; i < row; i++) {
            
            colCOunt=0;
            for (int j = 0; j < col; j++) {
                if(board[i][j] == player){
                
                colCOunt++;
                    System.out.println("In " +j+ ". col there is: " + colCOunt);
                                        }  
                else{
                    colCOunt =0;
                    }
                }
            
            if(colCOunt==victoryCon){
                return true;
            }
            }
            
            
            
            for (int i = 0; i < row; i++) {
                
                rowCount = 0;
                for (int j = 0; j < col; j++) {
                if(board[j][i] == player){
                
                rowCount++;
                 System.out.println("In " +j+ ". row there is: " + rowCount);                       }
                else{
                    rowCount =0;
                    }
                }
                if(rowCount==victoryCon){
                return true;
                
            }
            }
            
            

            
            for (int i = 0; i < row; i++) 
   {
               
                
                for (int j = 0; j < col; j++) 
        {
                            if(board[i][j] == player){
                                if(i+1 < row && j+1 < col){
                                    if(board[i+1][j+1] == player){
                                
                                    
                                
                                diagonalCount++;
                                if(diagonalCount==victoryCon)
            {
                                    return true;
            }
}
                                else{
                    diagonalCount =1;
                    }}
               
        }
    }}
            
             for (int i = 0; i < col; i++) 
   {
               
                
                for (int j = row-1; j > 0; j--)
        {
                            if(board[i][j] == player){
                                if(i+1 < row && j-1 >= 0){
                                    if(board[i+1][j-1] == player){
                                
                                    
                                
                                diagonalCount2++;
                                if(diagonalCount2==victoryCon)
            {
                                    return true;
            }
}
                                    else{
                                        diagonalCount2=1;
                                    }}
               
        }
    }}

        
        /*for (int i = 0; i < board.length-2; i++) {
            for (int j = 0; j < board.length-2; j++) {
                if(
                           ((board[i][j] == player) && (board[i+1][j] == player) && (board[i+2][j] == player)) 
                        || ((board[i][j] == player) && (board[i][j+1] == player) && (board[i][j+2] == player)) 
                        || ((board[i][j] == player) && (board[i+1][j+1] == player) && (board[i+2][j+2] == player))
                        
                         
                        
                         ){
                    
                   return true;
                } 
                if(i==2){
                    if((board[i][j] == player) && (board[i-1][j+1] == player) && (board[i-2][j+2] == player))
                        return true;
                }
                if(turn == 9){
                isDraw = true;
                return true;
            }
            
            }
            
        }*/
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        if(isDraw){
            return -1;
        }
        else return player;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        turn=0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
                
            }
        }
       player = 2;
    }

    @Override
    public int getPlayer() {
        return player;
    }

    @Override
    public void setCols(int col) {
        this.col = col;
    }

    @Override
    public void setRows(int row) {
        this.row = row;
    }

    

}
