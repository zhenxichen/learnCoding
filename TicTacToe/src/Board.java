import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    Block [][] board;                  //棋盘
    JButton restart;                    //“重新开始”按钮
    JPanel pCenter,pNorth;
    JTextField result;                  //用以显示结果
    int count;                          //当前步数

    public Board(){
        restart = new JButton("重新开始");
        pCenter = new JPanel();
        pNorth = new JPanel();
        result = new JTextField();
        result.setFont(new Font("Arial",Font.BOLD,16));
        result.setHorizontalAlignment(JTextField.CENTER);
        result.setPreferredSize(new Dimension(100,50));
        initBoard();
        restart.addActionListener(this);
        pNorth.add(restart);
        pNorth.add(new JLabel("结果："));
        pNorth.add(result);
        setLayout(new BorderLayout());
        add(pNorth,BorderLayout.NORTH);
        add(pCenter,BorderLayout.CENTER);
    }

    public void initBoard(){
        board = new Block[3][3];        //新建一个3*3棋盘
        count = 0;
        pCenter.removeAll();
        pCenter.setLayout(new GridLayout(3,3));     //设置棋盘为3*3
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = new Block();
                pCenter.add(board[i][j]);
                board[i][j].getCover().addActionListener(this);     //响应鼠标点击
                board[i][j].showCover();
                board[i][j].setEnabled(true);
            }
        }
        result.setText("");
        repaint();
    }

    public int VerifyWin(){                         //判断获胜(1为X获胜，0为O获胜，-1为暂时无人获胜)
        for(int i = 0;i<3;i++){                     //先判断横向
            if(board[i][0].getPiece() == board[i][1].getPiece()&&
            board[i][0].getPiece() == board[i][2].getPiece()&&
            board[i][0].getPiece() != -1){
                return board[i][0].getPiece();
            }
        }
        for(int i=0;i<3;i++){                       //再判断纵向
            if(board[0][i].getPiece() == board[1][i].getPiece()&&
            board[0][i].getPiece() == board[2][i].getPiece()&&
            board[0][i].getPiece() != -1){
                return board[0][i].getPiece();
            }
        }
        if(board[0][0].getPiece() == board[1][1].getPiece()&&   //判断斜向
        board[0][0].getPiece() == board[2][2].getPiece()&&
        board[0][0].getPiece() != -1){
            return board[0][0].getPiece();
        }
        if(board[0][2].getPiece() == board[1][1].getPiece()&&
        board[0][2].getPiece() == board[2][0].getPiece()&&
        board[0][2].getPiece()  != -1){
            return board[0][2].getPiece();
        }
        return -1;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==restart){                 //用户点击重新开始按钮
            initBoard();
            repaint();
            validate();
        }
        else{                                       //用户点击方块
            int m=-1,n=-1;
            boolean find = false;
            for(int i=0;i<3;i++){                   //寻找用户所点击的按钮
                for(int j=0;j<3;j++){
                    find = false;
                    if(e.getSource() == board[i][j].getCover()){
                        m = i;
                        n = j;
                        find = true;
                        break;
                    }
                }
                if(find){ break; }
            }
            if(!board[m][n].isMark()){              //这个地方暂未落子
                count++;                            //记录当前步数
                board[m][n].setMark(true);
                board[m][n].setWord(count%2);
                board[m][n].showUnder();
            }
        }
        int win = VerifyWin();                      //避免多次运算同一函数，加快运行效率
        if(win == -1){
            if(count == 9){                            //无法再走子
                result.setText("draw!");
            }
        }
        else if(win == 1){
            result.setText("X win!");
            gameOver();
        }
        else if(win == 0){
            result.setText("O win!");
            gameOver();
        }
    }

    private void gameOver(){                           //游戏结束后将所有按钮设为无效
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j].getCover().setEnabled(false);
            }
        }
    }

}
