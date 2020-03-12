package view;

import java.awt.*;
import javax.swing.*;
import data.*;

public class BlockView extends JPanel implements ViewForBlock{
    JLabel under;                                   //name or icon of block
    JButton cover;                                  // to cover the under name or icon
    CardLayout card;                                //卡片式布局-card view
    Block block;
    BlockView(){
        card = new CardLayout();
        setLayout(card);                            //from JPanel
        under = new JLabel("",JLabel.CENTER);       //居中对齐
        under.setHorizontalTextPosition(AbstractButton.CENTER);        //水平文本
        under.setVerticalTextPosition(AbstractButton.CENTER);          //垂直文本
        cover = new JButton();
        add("cover",cover);
        add("view",under);
    }

    public void acceptBlock(Block block){
        this.block = block;
    }
    public void setDataOnView(){
        if(block.isMine()){
            under.setText(block.getName());
            under.setIcon(block.getMineIcon());
        }
        else{
            if(block.getAroundMineNumber() > 0){
                under.setText(""+block.getAroundMineNumber());          //显示周围“雷”的数量
            }else{
                under.setText("");                                      //周围无雷则不显示
            }
        }
    }
    public void showUnder(){
        card.show(this,"view");
        validate();
    }
    public void showCover(){
        card.show(this,"cover");
        validate();
    }
    public JButton getCover(){
        return cover;
    }
}
