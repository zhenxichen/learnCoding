import javax.swing.*;
import java.awt.*;

public class Block extends JPanel{
    JLabel under;               //用于显示X或O
    JButton cover;
    CardLayout card;
    boolean isMark;             //已经落子
    int piece;                  //表示放在格子上的棋（-1为空，0为O，1为X）
    public Block(){
        isMark = false;
        piece = -1;
        card = new CardLayout();
        setLayout(card);
        under = new JLabel();
        under.setHorizontalTextPosition(AbstractButton.CENTER);
        under.setVerticalTextPosition(AbstractButton.CENTER);
        under.setFont(new Font("Arial",Font.BOLD,16));
        cover = new JButton();
        add("under",under);                             //向JPanel中加入under组件
        add("cover",cover);
    }

    public void setWord(int n){                                 //n为1或0（通过当前步数%2获得）
        piece = n;
        if(n==1){
            under.setText("X");                                 //X先走
        }
        else{
            under.setText("O");
        }
    }

    public void showUnder(){
        card.show(this,"under");
        validate();
    }

    public void showCover(){
        card.show(this,"cover");
        validate();
    }

    public boolean isMark() {
        return isMark;
    }

    public void setMark(boolean mark) {
        isMark = mark;
    }

    public JButton getCover() {
        return cover;
    }

    public int getPiece() {
        return piece;
    }
}
