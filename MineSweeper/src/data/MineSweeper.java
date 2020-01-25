package data;

import java.util.Stack;

public class MineSweeper {
    public Block [][] block;                //用一个二维数组来表示雷区的全部方块
    Stack<Block> notMineBlock;              //用一个栈来存放一个方块周围区域全部不是雷的方块
    int m,n;                                //方块索引下标
    int row,column;                         //雷区的行列数
    int mineCount;                          //雷的数目

    public MineSweeper(){
        notMineBlock = new Stack<Block>();
    }

    public void setBlock(Block [][] block,int mineCount){
        this.block = block;
        this.mineCount = mineCount;
        row = block.length;
        column = block[0].length;
    }

    public Stack<Block> getNotMineBlock(Block b){           //得到方块b周围的所有不是雷的方块
        notMineBlock.clear();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(b==block[i][j]){                         //找到方块b
                    m=i;
                    n=j;
                    break;
                }
            }
        }
        if(!b.isMine()){
            getIn(m,n);
        }
        return notMineBlock;
    }

    public void getIn(int m,int n){
        if(block[m][n].getAroundMineNumber()>0&&block[m][n].isOpen()==false){           //周围有雷（显示数字）
            block[m][n].setOpen(true);
            notMineBlock.push(block[m][n]);
            return;
        }
        else if(block[m][n].getAroundMineNumber()==0&&block[m][n].isOpen()==false){     //周围都没有雷
            block[m][n].setOpen(true);
            notMineBlock.push(block[m][n]);
            for(int i = Math.max(m-1,0);i<=Math.min(m+1,row-1);i++){
                for(int j = Math.max(n-1,0);j<=Math.min(n+1,column-1);j++){
                    getIn(i,j);                                                 //recursion
                }
            }
        }
    }

    public boolean verifyWin(){
        int number = 0;                 //用以记录尚未点击的方块数量
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(block[i][j].isOpen()==false){
                    number++;
                }
            }
        }
        if(number == mineCount){           //剩下的全是雷
            return true;
        }
        else{
            return false;
        }
    }
}
