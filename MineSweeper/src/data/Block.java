package data;

import javax.swing.ImageIcon;

public class Block {
    String name;
    int aroundMineNumber;               //number of mines around the block
    ImageIcon mineIcon;                 //Icon of the mine
    public boolean isMine = false;      //The block is mine or not
    boolean isMark = false;             //The block is marked or not
    boolean isOpen = false;             //The block is excavated or not
    ViewForBlock blockView;

    public void setName(String name) {
        this.name = name;
    }

    public void setAroundMineNumber(int aroundMineNumber) {
        this.aroundMineNumber = aroundMineNumber;
    }

    public void setMineIcon(ImageIcon mineIcon) {
        this.mineIcon = mineIcon;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public void setMark(boolean mark) {
        isMark = mark;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setBlockView(ViewForBlock blockView) {
        this.blockView = blockView;
        blockView.acceptBlock(this);
    }

    public String getName() {
        return name;
    }

    public int getAroundMineNumber() {
        return aroundMineNumber;
    }

    public ImageIcon getMineIcon() {
        return mineIcon;
    }

    public ViewForBlock getBlockView() {
        return blockView;
    }

    public boolean isMine(){ return isMine; }
    public boolean isOpen(){ return isOpen; }
    public boolean isMark(){ return isMark; }

}
