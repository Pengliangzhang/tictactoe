import java.util.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 */

public class TicTacToe implements ActionListener
{
   public static final String PLAYER_X = "X"; // player using "X"
   public static final String PLAYER_O = "O"; // player using "O"
   public static final String EMPTY = " ";  // empty cell
   public static final String TIE = "T"; // game ended in a tie
   private int row, col;
   
   private int count_X=0, count_O=0, count_TIE=0;
   
   private JMenuBar menuBar;
   private JMenu menu;
   private JMenuItem newGame, newRound, quitItem;
 
   private String player = PLAYER_X;   // current player (PLAYER_X or PLAYER_O)

   private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

   private int numFreeSquares; // number of squares still free
   
   private String board[][]; // 3x3 array representing the board
   
   private JTextArea status; // text area to print game status
   
   
   private JTextField resultDisplay;
   
   private JButton upRightCorner;
   private JButton upMid;
   private JButton upLeftCorner;
   
   private JButton midRightCorner;
   private JButton Mid;
   private JButton midLeftCorner;
   
   private JButton downRightCorner;
   private JButton downMid;
   private JButton downLeftCorner;
   ImageIcon X = new ImageIcon(getClass().getResource("x.jpg"));
   ImageIcon O = new ImageIcon(getClass().getResource("o.jpg"));
   //X.setSize(150, 150);
   
   /** 
    * Constructs a new Tic-Tac-Toe board.
    */
   public TicTacToe()
   {
      board = new String[3][3];
      JFrame frame = new JFrame("Tic Tac Toe");
      
      
      Container contentPane = frame.getContentPane();
      //contentPane.setLayout(new GridLayout(2, 1));
       
       resultDisplay = new JTextField();
       resultDisplay.setEditable(false);
       resultDisplay.setFont(new Font(null, Font.BOLD, 16));
       resultDisplay.setPreferredSize(new Dimension(100,50));
       resultDisplay.setHorizontalAlignment(JTextField.CENTER);
       
       contentPane.add(resultDisplay);
       resultDisplay.setText("Player_X Wins: 0" + " " +"Player_O Wins: 0" + "   "+ "Game Tied: 0");
       
       
       
       menuBar = new JMenuBar();
       menu = new JMenu("options");
       newGame = new JMenuItem("Start New Game");
       newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reStart(); 
            }
        });
       newRound = new JMenuItem("Start New Round");
       quitItem = new JMenuItem("Quit");
       quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
       newRound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newRound(); 
            }
        });
        menu.add(quitItem);
       menu.add(newGame);
       menu.add(newRound);
       menuBar.add(menu);
       final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
       quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
       
       resultDisplay.setText("" + showResult());
       
       JPanel midPanel = new JPanel();
       midPanel.setLayout(new GridLayout(3,3));
       midPanel.setSize(100,100);
       
       /*-------------------------------------------------------------------- up button -----------------------------------------------------------------------------*/
       /*Set up right corner button*/
      upRightCorner = new JButton("");
      //upRightCorner = board[0][0];
      midPanel.add(upRightCorner);
      
      /*Set up mid button*/
      upMid = new JButton("");
      midPanel.add(upMid);
      
      /*Set up left corner button*/
      upLeftCorner = new JButton("");
      midPanel.add(upLeftCorner);
      
     /*-------------------------------------------------------------------- mid button -----------------------------------------------------------------------------*/
      /*Set mid right corner button*/
      midRightCorner = new JButton("");
      midPanel.add(midRightCorner);
      
      /*Set mid mid button*/
      Mid = new JButton("");
      midPanel.add(Mid);
      
      /*Set mid left corner button*/
      midLeftCorner = new JButton("");
      midPanel.add(midLeftCorner);
      
      /*-------------------------------------------------------------------- down button -----------------------------------------------------------------------------*/
      /*Set down right corner button*/
      downRightCorner = new JButton("");
      midPanel.add(downRightCorner);
      
      /*Set down mid button*/
      downMid = new JButton("");
      midPanel.add(downMid);
      
      /*Set mid left corner button*/
      downLeftCorner = new JButton("");
      midPanel.add(downLeftCorner);
      
      
      contentPane.add(midPanel);
      // register buttons as listeners
      upRightCorner.addActionListener(this); 
      upMid.addActionListener(this);
      upLeftCorner.addActionListener(this);
      
      midRightCorner.addActionListener(this); 
      Mid.addActionListener(this);
      midLeftCorner.addActionListener(this);
      
      downRightCorner.addActionListener(this); 
      downMid.addActionListener(this);
      downLeftCorner.addActionListener(this);
       
       //contentPane.add(scrollPane);//, BorderLayout.NORTH);
       frame.setJMenuBar(menuBar);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.pack();
       frame.setResizable(false);
       frame.setVisible(true);
       frame.setSize(400,450);
       clearBoard();
       //playGame();
   }

   /**
    * Sets everything up for a new game.  Marks all squares in the Tic Tac Toe board as empty,
    * and indicates no winner yet, 9 free squares and the current player is player X.
    */
   private void clearBoard()
   {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            board[i][j] = EMPTY;
         }
      }
      winner = EMPTY;
      numFreeSquares = 9;
      player = PLAYER_X;     // Player X always has the first turn.
   }


   /**
    * Plays one game of Tic Tac Toe.
    */

   public void playGame()
   {
      // loop until the game ends
      if (winner==EMPTY) { // game still in progress

         board[row][col] = player;        // fill in the square with player
         numFreeSquares--;            // decrement number of free squares

         // see if the game is over
         if (haveWinner(row,col)){ 
            winner = player; // must be the player who just went
            if(winner.equals(PLAYER_O)){
                count_O++;
            }else if(winner.equals(PLAYER_X)){
                count_X++;
            }
        }else if (numFreeSquares==0){
            winner = TIE; // board is full so it's a tie
            count_TIE++;
        }
         
         // change to other player (this won't do anything if game has ended)
         if (player==PLAYER_X) 
            player=PLAYER_O;
         else 
            player=PLAYER_X;
      }
      
      //If have a winner, set all button unable 
      if (winner!=EMPTY) {
          upRightCorner.setEnabled(false);
          upMid.setEnabled(false);
          upLeftCorner.setEnabled(false);
          
          midRightCorner.setEnabled(false);
          Mid.setEnabled(false);
          midLeftCorner.setEnabled(false);
          
          downRightCorner.setEnabled(false);
          downMid.setEnabled(false);
          downLeftCorner.setEnabled(false);
      }

   } 


   /**
    * Returns true if filling the given square gives us a winner, and false
    * otherwise.
    *
    * @param int row of square just set
    * @param int col of square just set
    * 
    * @return true if we have a winner, false otherwise
    */
   private boolean haveWinner(int row, int col) 
   {
       // unless at least 5 squares have been filled, we don't need to go any further
       // (the earliest we can have a winner is after player X's 3rd move).

       if (numFreeSquares>4) return false;

       // Note: We don't need to check all rows, columns, and diagonals, only those
       // that contain the latest filled square.  We know that we have a winner 
       // if all 3 squares are the same, as they can't all be blank (as the latest
       // filled square is one of them).

       // check row "row"
       if ( board[row][0].equals(board[row][1]) &&
            board[row][0].equals(board[row][2]) ) return true;
       
       // check column "col"
       if ( board[0][col].equals(board[1][col]) &&
            board[0][col].equals(board[2][col]) ) return true;

       // if row=col check one diagonal
       if (row==col)
          if ( board[0][0].equals(board[1][1]) &&
               board[0][0].equals(board[2][2]) ) return true;

       // if row=2-col check other diagonal
       if (row==2-col)
          if ( board[0][2].equals(board[1][1]) &&
               board[0][2].equals(board[2][0]) ) return true;

       // no winner yet
       return false;
   }

   
   /**
    * Prints the board to standard out using toString().
    */
    public void print() 
    {
        System.out.println(showResult());// something needs to be added here
    }
  
    
    /**
     * Return a string that show the winner
     * 
     * @return Return winner
     */
    public String showResult()
    {
        if(winner==null){
            return "";
        }else{
            if(winner.equals(TIE)){
                return "The game is tie!";
            }else{
                return winner + " wins";
            }
        }
    }
    
    /**
     * the method re start the game
     */
    
    public void reStart()
    {
        upRightCorner.setEnabled(true);
        upRightCorner.setIcon(null);
        upMid.setEnabled(true);
        upMid.setIcon(null);
        upLeftCorner.setEnabled(true);
        upLeftCorner.setIcon(null);
          
        midRightCorner.setEnabled(true);
        midRightCorner.setIcon(null);
        Mid.setEnabled(true);
        Mid.setIcon(null);
        midLeftCorner.setEnabled(true);
        midLeftCorner.setIcon(null);
          
        downRightCorner.setEnabled(true);
        downRightCorner.setIcon(null);
        downMid.setEnabled(true);
        downMid.setIcon(null);
        downLeftCorner.setEnabled(true);
        downLeftCorner.setIcon(null);
        clearBoard();
        resultDisplay.setText("Player_X Wins:" + count_X + " " +"Player_O Wins:" + count_O + " "+ "Game Tied:" + count_TIE);
    }
    
    /**
     * The method start a new round of the game
     */
    public void newRound(){
        reStart();
        count_O=0;
        count_X=0;
        count_TIE=0;
        resultDisplay.setText("Player_X Wins:" + count_X + " " +"Player_O Wins:" + count_O + " "+ "Game Tied:" + count_TIE);
    }
    
    /** This action listener is called when the user clicks on 
    * any of the GUI's buttons. 
    */
    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton)e.getSource();
        
        
        if(button == upRightCorner){
            row=0;
            col=0;
            if (player==PLAYER_X){ 
                upRightCorner.setIcon(X);
            }else{ 
                upRightCorner.setIcon(O);
            }
            playGame();
            upRightCorner.setEnabled(false);
            
        }else if(button == upMid){
            row=0;
            col=1;
            if (player==PLAYER_X){ 
                upMid.setIcon(X);
            }else{ 
                upMid.setIcon(O);
            }
            playGame();
            upMid.setEnabled(false);
        }else if(button == upLeftCorner){
            row=0;
            col=2;
            if (player==PLAYER_X){ 
                upLeftCorner.setIcon(X);
            }else{ 
                upLeftCorner.setIcon(O);
            }
            playGame();
            upLeftCorner.setEnabled(false);
        }else if(button == midRightCorner){
            row=1;
            col=0;
            if (player==PLAYER_X){ 
                midRightCorner.setIcon(X);
            }else{ 
                midRightCorner.setIcon(O);
            }
            playGame();
            midRightCorner.setEnabled(false);
        }else if(button == Mid){
            row=1;
            col=1;
            if (player==PLAYER_X){ 
                Mid.setIcon(X);
            }else{ 
                Mid.setIcon(O);
            }
            playGame();
            Mid.setEnabled(false);
        }else if(button == midLeftCorner){
            row=1;
            col=2;
            if (player==PLAYER_X){ 
                midLeftCorner.setIcon(X);
            }else{ 
                midLeftCorner.setIcon(O);
            }
            playGame();
            midLeftCorner.setEnabled(false);
        }else if(button == downRightCorner){
            row=2;
            col=0;
            if (player==PLAYER_X){ 
                downRightCorner.setIcon(X);
            }else{ 
                downRightCorner.setIcon(O);
            }
            playGame();
            downRightCorner.setEnabled(false);
        }else if(button == downMid){
            row=2;
            col=1;
            if (player==PLAYER_X){ 
                downMid.setIcon(X);
            }else{ 
                downMid.setIcon(O);
            }
            playGame();
            downMid.setEnabled(false);
        }else if(button == downLeftCorner){
            row=2;
            col=2;
            if (player==PLAYER_X){ 
                downLeftCorner.setIcon(X);
            }else{ 
                downLeftCorner.setIcon(O);
            }
            playGame();
            downLeftCorner.setEnabled(false);
        }
        
        resultDisplay.setText("Player_X Wins:" + count_X + " " +"Player_O Wins:" + count_O + " "+ "Game Tied:" + count_TIE);
        
    }
    
}

