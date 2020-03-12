package data;

public interface ViewForBlock {
    public void acceptBlock(Block block);   //确定视图对应的方块
    public void setDataOnView();            //设置视图上应显示的数据
    public void showUnder();                //显示方块下的内容
    public void showCover();                //显示负责遮挡的组件
    public Object getCover();               //得到视图上的遮挡组件
}
