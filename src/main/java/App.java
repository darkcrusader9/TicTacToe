public class App {
    public static void main(String[] args) {
        TicTacToeGame tc = new TicTacToeGame(3);
        tc.initializeGame();
        System.out.println("Result : " + tc.playGame());
    }
}
