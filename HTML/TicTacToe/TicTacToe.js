//第一次把HTML,CSS和JS一起使用
//用来做一个井字棋的小游戏

var count = 0;          //已经下的棋数
var squares = document.getElementsByClassName("square");
var gameOver = false;
var turn = true;        //true表示X的回合，false表示O的回合

var setMessageBox = function(caption){
    var box = document.getElementById("messageBox");
    box.innerHTML = caption;
}

var verifyWin = function(){
    var board = [];         //存储当前棋盘的值
    var value = 0;
    var inner;
    for(var i = 0; i < 9; i++){
        inner = document.getElementById(i).innerHTML;
        if(inner == "X"){
            value = 1;
        }
        else if(inner == "O"){
            value = -1;
        }
        board.push(value);
        value = 0;
    }
    for(var i = 0; i < 3; i++){
        value = board[3*i] + board[3*i+1] + board[3*i+2];   //横向
        if(value == 3){
            return 1;
        }
        else if(value == -3){
            return -1;
        }
        value = board[i] + board[i+3] + board[i+6];         //纵向
        if(value == 3){
            return 1;
        }
        else if(value == -3){
            return -1;
        }
    }
    value = board[0] + board[4] + board[8];             //斜向
    if(value == 3){
        return 1;
    }
    else if(value == -3){
        return -1;
    }
    value = board[2] + board[4] + board[6];
    if(value == 3){
        return 1;
    }
    else if(value == -3){
        return -1;
    }
    return 0;               //return 0表示暂时无人取胜
}

var restart = function(){
    for(var i = 0; i < 9; i++){
        var square = document.getElementById(i);
        square.innerHTML = "";
    }
    count = 0;
    gameOver = false;
    turn = true;
    setMessageBox("轮到X了");
}

var opera = function(){     //用来处理每次玩家的点击操作
    if(!gameOver){
        var id = this.getAttribute("id");
        var square = document.getElementById(id);
        if(validClick(square)){
            if(turn){
                square.innerHTML = "X";
            }
            else{
                square.innerHTML = "O";
            }
            var result = verifyWin();
            if(result != 0){        //游戏已经结束
                gameOver = true;
                if(result == 1){
                    setMessageBox("X赢了！");
                }else{              //result = -1
                    setMessageBox("O赢了！");
                }
            }
            else{                   //游戏尚未结束
                count++;
                if(count == 9){
                    draw();
                }
                else{
                    switchTurn();
                }
            }

        } 
        //如果是已经被填充的格子，则不进行任何操作（认为是误触）
    }   //end if(!gameOver)
}

var validClick = function(square){
    var inner = square.innerHTML;
    return (inner != "X" && inner != "O");
}

var switchTurn = function(){        //转换回合
    turn = !turn;
    if(turn){
        setMessageBox("轮到X了");
    }
    else{
        setMessageBox("轮到O了");
    }
}

var draw = function(){
    setMessageBox("平局！");
    gameOver = true;
}


for(var i = 0; i < 9; i++){
    squares[i].addEventListener("click", opera);
}