package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;
import data.*;

public class MineArea extends JPanel implements ActionListener, MouseListener {

    JButton restart;
    Block [][] block;
    BlockView [][] blockView;
    LayMines lay;
    MineSweeper mineSweeper;

    int row,column;             //雷区的行列数
    int mineCount, markMount;   //地雷个数与用户给出的标记数
    int spendTime = 0;          //用时

    ImageIcon mark;             //标记
    String grade;               //游戏难度级别
    JPanel pCenter,pNorth;      //布局用的面板
    JTextField showTime;        //显示用时
    JTextField showMarkedCount; //显示标记个数
    Timer time;                 //计时器

    public MineArea(int row,int column,int mineCount,String grade){
        restart = new JButton("重新开始");
        mark = new ImageIcon("mark.png");
        time = new Timer(1000,this);        //每秒记录一次
        showTime = new JTextField(5);
        showTime.setHorizontalAlignment(JTextField.CENTER);              //设置对齐
        showTime.setFont(new Font("Arial",Font.BOLD,16));   //设置字体
        showMarkedCount = new JTextField(5);
        showMarkedCount.setHorizontalAlignment(JTextField.CENTER);
        showMarkedCount.setFont(new Font("Arial",Font.BOLD,16));
        pCenter = new JPanel();
        pNorth = new JPanel();
        lay = new LayMines();
        mineSweeper = new MineSweeper();
        initMineArea(row,column,mineCount,grade);
        restart.addActionListener(this);
        pNorth.add(new JLabel("剩余雷数："));
        pNorth.add(showMarkedCount);
        pNorth.add(restart);
        pNorth.add(new JLabel("用时："));
        pNorth.add(showTime);
        setLayout(new BorderLayout());
        add(pNorth,BorderLayout.NORTH);
        add(pCenter,BorderLayout.CENTER);
    }

    public void initMineArea(int row,int column,int mineCount,String grade){
        pCenter.removeAll();
        spendTime = 0;
        markMount = mineCount;
        this.row = row;
        this.column = column;
        this.mineCount = mineCount;
        block = new Block[row][column];
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                block[i][j] = new Block();
            }
        }
        lay.layMines(block,mineCount);
        mineSweeper.setBlock(block,mineCount);
        blockView = new BlockView[row][column];
        pCenter.setLayout(new GridLayout(row,column));
        for(int i=0;i<row;i++){                             //set block view
            for(int j=0;j<column;j++){
                blockView[i][j] = new BlockView();
                block[i][j].setBlockView(blockView[i][j]);
                blockView[i][j].setDataOnView();
                pCenter.add(blockView[i][j]);
                blockView[i][j].getCover().addActionListener(this);
                blockView[i][j].getCover().addMouseListener(this);
                blockView[i][j].showCover();
                blockView[i][j].getCover().setEnabled(true);
                blockView[i][j].getCover().setIcon(null);
            }
        }
        showMarkedCount.setText(""+markMount);
        repaint();
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() != restart && e.getSource() != time){
            time.start();
            int m=-1,n=-1;
            for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    if(e.getSource() == blockView[i][j].getCover()){
                        m=i;
                        n=j;
                        break;
                    }
                }
            }
            if(block[m][n].isMine()){               //点到雷
                for(int i=0;i<row;i++){
                    for(int j=0;j<column;j++){
                        blockView[i][j].getCover().setEnabled(false);       //使用户单击无效
                        if(block[i][j].isMine()){
                            blockView[i][j].showUnder();
                        }
                    }
                }
                time.stop();
                //恢复初始值
                spendTime = 0;
                markMount = mineCount;
            }
            else{
                Stack<Block> notMineBlock = mineSweeper.getNotMineBlock(block[m][n]);
                while(!notMineBlock.empty()){
                    Block b = notMineBlock.pop();
                    ViewForBlock viewForBlock = b.getBlockView();
                    viewForBlock.showUnder();
                }
            }
        }
        if(e.getSource() == restart){
            initMineArea(row,column,mineCount,grade);
            repaint();
            validate();
        }
        if(e.getSource() == time){
            spendTime++;
            showTime.setText(""+spendTime);
        }
        if(mineSweeper.verifyWin()){
            time.stop();
        }
    }

    public void mousePressed(MouseEvent e){                 //标记方块
        JButton source = (JButton)e.getSource();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(e.getModifiers()==InputEvent.BUTTON3_MASK &&
                source == blockView[i][j].getCover()){
                    if(block[i][j].isMark()){
                        source.setIcon(null);
                        block[i][j].setMark(false);
                        markMount++;
                        showMarkedCount.setText(""+markMount);
                    }
                    else{
                        source.setIcon(mark);
                        block[i][j].setMark(true);
                        markMount--;
                        showMarkedCount.setText(""+markMount);
                    }
                }
            }
        }
    }

    //不进行实现的方法
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}
