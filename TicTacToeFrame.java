import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 * 
 * @author Pengliang Zhang
 * @version November 8, 2012
 */

public class TicTacToeFrame extends TicTacToe implements ActionListener
{ 
   private JTextArea status; // text area to print game status
   JFrame frame = new JFrame("Tic Tac Toe");
   
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
   
   /** 
    * Constructs a new Tic-Tac-Toe board and sets up the basic
    * JFrame containing a JTextArea in a JScrollPane GUI.
    */
   public TicTacToeFrame()
   { 
       super();
       Container contentPane = frame.getContentPane();
       contentPane.setLayout(new GridLayout(1, 1));
       
       resultDisplay = new JTextField();
       resultDisplay.setEditable(false);
       resultDisplay.setFont(new Font(null, Font.BOLD, 18));
       resultDisplay.setHorizontalAlignment(JTextField.RIGHT);
       contentPane.add(resultDisplay);
       
       resultDisplay.setText("" + showResult());
       
       JPanel midPanel = new JPanel();
       midPanel.setLayout(new GridLayout(3,3));
       
       // /*-------------------------------------------------------------------- up button -----------------------------------------------------------------------------*/
       // /*Set up right corner button*/
      upRightCorner = new JButton("");
      upRightCorner = board[0][0];
      midPanel.add(upRightCorner);
      
      /*Set up mid button*/
      upMid = new JButton("");
      midPanel.add(upMid);
      
      /*Set up left corner button*/
      upLeftCorner = new JButton("");
      midPanel.add(upLeftCorner);
      
     // /*-------------------------------------------------------------------- mid button -----------------------------------------------------------------------------*/
      // /*Set mid right corner button*/
      midRightCorner = new JButton("");
      midPanel.add(midRightCorner);
      
      // /*Set mid mid button*/
      Mid = new JButton("");
      midPanel.add(Mid);
      
      // /*Set mid left corner button*/
      midLeftCorner = new JButton("");
      midPanel.add(midLeftCorner);
      
      // /*-------------------------------------------------------------------- down button -----------------------------------------------------------------------------*/
      // /*Set down right corner button*/
      downRightCorner = new JButton("");
      midPanel.add(downRightCorner);
      
      // /*Set down mid button*/
      downMid = new JButton("");
      midPanel.add(downMid);
      
      // /*Set mid left corner button*/
      downLeftCorner = new JButton("");
      midPanel.add(downLeftCorner);
      
      
      contentPane.add(midPanel);
      //register buttons as listeners
      upRightCorner.addActionListener(this); 
      upMid.addActionListener(this);
      upLeftCorner.addActionListener(this);
      
      midRightCorner.addActionListener(this); 
      Mid.addActionListener(this);
      midLeftCorner.addActionListener(this);
      
      downRightCorner.addActionListener(this); 
      downMid.addActionListener(this);
      downLeftCorner.addActionListener(this);
       
       status = new JTextArea();
       status.setEditable(false);
       
       JScrollPane scrollPane = new JScrollPane(status);
       
       contentPane.add(scrollPane);//, BorderLayout.NORTH);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.pack();
       frame.setResizable(true);
       frame.setVisible(true);
       
       status.setFont(new Font(null, Font.BOLD, 18));
       this.append(status);
       this.setLayout(new GridLayout(1, 1));
       status.setHorizontalAlignment(JTextField.RIGHT);
       // add the necessary code here
   }
   
   /**
    * Prints the board to the GUI using toString().
    */
    public void print() 
    {  
        
        status.append(super.toString());
        status.append("\n");
        //JOptionPane.showMessageDialog(null,"The result for the TicTacToe " + super.toString(), "TicTacToe", JOptionPane.PLAIN_MESSAGE);
        // add code here
    }
    
    /** This action listener is called when the user clicks on 
    * any of the GUI's buttons. 
    */
    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton)e.getSource();
        ImageIcon X = new ImageIcon(getClass().getResource("x.jpg"));
        ImageIcon O = new ImageIcon(getClass().getResource("o.jpg"));
        
        if(button == upRightCorner){
            // row=0;
            // col=0;
            upRightCorner.setIcon(X);
            upRightCorner.setEnabled(false);
        }else if(button == upMid){
            // row=0;
            // col=1;
            upMid.setIcon(X);
            upMid.setEnabled(false);
        }else if(button == upLeftCorner){
            // row=0;
            // col=2;
            upLeftCorner.setIcon(X);
            upLeftCorner.setEnabled(false);
        }else if(button == midRightCorner){
            // row=0;
            // col=0;
            midRightCorner.setIcon(X);
            midRightCorner.setEnabled(false);
        }else if(button == Mid){
            // row=0;
            // col=1;
            Mid.setIcon(X);
            Mid.setEnabled(false);
        }else if(button == midLeftCorner){
            // row=0;
            // col=2;
            midLeftCorner.setIcon(X);
            midLeftCorner.setEnabled(false);
        }else if(button == downRightCorner){
            // row=0;
            // col=0;
            downRightCorner.setIcon(X);
            downRightCorner.setEnabled(false);
        }else if(button == downMid){
            // row=0;
            // col=1;
            downMid.setIcon(X);
            downMid.setEnabled(false);
        }else if(button == downLeftCorner){
            // row=0;
            // col=2;
            downLeftCorner.setIcon(X);
            downLeftCorner.setEnabled(false);
        }
        
         // midRightCorner.setEnabled(true);
         // Mid.setEnabled(true);
         // midLeftCorner.setEnabled(true);
        
         // downRightCorner.setEnabled(true);
         // downRightCorner.setEnabled(true);
         // downRightCorner.setEnabled(true);
    }

}