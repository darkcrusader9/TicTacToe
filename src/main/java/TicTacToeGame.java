
import model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> playerQueue;
    int size;
    GameBoard gameBoard;

    public TicTacToeGame(int size){
        this.size = size;
    }

    public void initializeGame() {
        playerQueue = new LinkedList<>();
        gameBoard = new GameBoard(size);

        PlayingPiece pieceX = new PlayingPieceX();
        Player a = new Player("A", pieceX);

        PlayingPiece pieceO = new PlayingPieceO();
        Player b = new Player("B", pieceO);

        playerQueue.add(a);
        playerQueue.add(b);
    }

    public String playGame(){
        boolean noWinner = true;
        while(noWinner){
            Player playerTurn = playerQueue.removeFirst();
            gameBoard.printBoard();
            List<Pair<Integer, Integer>> freeCells = gameBoard.getFreeCells();
            if(freeCells.isEmpty()){
                noWinner = false;
                continue;
            }

            //read the user input
            System.out.print("Player:" + playerTurn.getName() + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");

            int inputRow = Integer.parseInt(values[0]);
            int inputColumn = Integer.parseInt(values[1]);

            boolean canPlace = gameBoard.addPieceToBoard(inputRow, inputColumn, playerTurn.getPlayingPiece());
            if(!canPlace){
                //player can not insert the piece into this cell, player has to choose another cell
                System.out.println("Please enter valid rows and columns");
                playerQueue.addFirst(playerTurn);
                continue;
            }
            playerQueue.addLast(playerTurn);

            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.getPlayingPiece().pieceType);
            if(winner) {
                return playerTurn.getName();
            }


        }
        return "Tie";
    }

    private boolean isThereWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;


        for(int i = 0; i < size; i++){
            if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
                break;
            }
        }

        //need to check in column
        for(int i=0; i < size; i++) {

            if(gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i < size; i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j = size-1; i < size; i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
