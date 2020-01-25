package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import view.MineArea;

public class AppWindow extends JFrame implements MenuListener{
    JMenuBar bar;
    JMenu fileMenu;
    JMenu gradeOne,gradeTwo,gradeThree;
    MineArea mineArea = null;

    public AppWindow(){
        bar = new JMenuBar();
        fileMenu = new JMenu("扫雷");
        gradeOne = new JMenu("初级");
        gradeTwo = new JMenu("中级");
        gradeThree = new JMenu("高级");
        fileMenu.add(gradeOne);
        fileMenu.add(gradeTwo);
        fileMenu.add(gradeThree);
        bar.add(fileMenu);
        setJMenuBar(bar);
        gradeOne.addMenuListener(this);
        gradeTwo.addMenuListener(this);
        gradeThree.addMenuListener(this);
        mineArea = new MineArea(9,9,10,gradeOne.getText());
        add(mineArea,BorderLayout.CENTER);
        setBounds(300,100,500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    public void menuSelected(MenuEvent e){
        if(e.getSource() == gradeOne){
            mineArea.initMineArea(9,9,10,gradeOne.getText());
        }
        else if(e.getSource() == gradeTwo){
            mineArea.initMineArea(16,16,40,gradeTwo.getText());
        }
        else if(e.getSource() == gradeThree){
            mineArea.initMineArea(22,30,99,gradeThree.getText());
        }
        validate();
    }

    //不进行实现的方法
    public void menuCanceled(MenuEvent e) { }
    public void menuDeselected(MenuEvent e) { }
    public void actionPerformed(ActionEvent e) { }

    //main函数
    public static void main(String[] args){
        new AppWindow();
    }
}
