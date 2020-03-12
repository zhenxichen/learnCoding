package data;

import java.util.LinkedList;
import javax.swing.ImageIcon;

public class LayMines {
    ImageIcon mineIcon;
    public LayMines(){
        mineIcon = new ImageIcon("mine.gif");
    }
    public void initBlock(Block [][] block){
        for(int i=0;i<block.length;i++){
            for(int j=0;j<block[i].length;j++){
                block[i][j].setMine(false);                 //全部初始化为无雷
            }
        }
    }

    public void layMines(Block [][] block,int mineCount){
        initBlock(block);
        int row = block.length;
        int column = block[0].length;
        int count = 0;
        LinkedList<Block> list = new LinkedList<Block>();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                list.add(block[i][j]);
            }
        }
        while(count < mineCount){                           //将设定数量的雷放入“雷区”中
            int randomIndex = (int)(Math.random()*list.size());
            Block b = list.get(randomIndex);
            b.setMine(true);
            b.setName("mine");
            b.setMineIcon(mineIcon);
            list.remove(randomIndex);
            count++;
        }
        for(int i=0;i<row;i++){                                //标记每个方块周围雷的数目
            for(int j=0;j<row;j++){
                if(!block[i][j].isMine()){                     //如果不是雷
                    int mineNumber = 0;                         //number of mines around
                    for(int k = Math.max(i-1,0);k<=Math.min(i+1,row-1);k++){            //遍历其周围
                        for(int t = Math.max(j-1,0);t<=Math.min(j+1,column-1);t++){
                            if(block[k][t].isMine()){
                                mineNumber++;
                            }
                        }
                    }
                    block[i][j].setName(""+mineNumber);
                    block[i][j].setAroundMineNumber(mineNumber);
                }
                //whether is mine or not
                block[i][j].setOpen(false);
                block[i][j].setMark(false);
            }
        }
    }
}
