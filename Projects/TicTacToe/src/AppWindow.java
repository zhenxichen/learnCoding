import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    Board board;

    public AppWindow(){
        board = new Board();
        add(board, BorderLayout.CENTER);
        setBounds(300,100,300,250);
        setTitle("Tic Tac Toe");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    public static void main(String[] args){
        new AppWindow();
    }
}
