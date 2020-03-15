/* login.js */
// 渲染登录界面

const { ipcRenderer } = require('electron');

let close = document.getElementById("close");
if(close){
    close.addEventListener('click', () => {
        ipcRenderer.send('loginWin-close');
    })
}

let min = document.getElementById("min");
if(min){
    min.addEventListener('click',() => {
        ipcRenderer.send('loginWin-min');
    })
}

let loginButton = document.getElementById("loginButton");
if(loginButton){
    loginButton.addEventListener('click',() => {
        let account = document.getElementById("account").value;
        if(isEmpty(account)){
            setMessageBar('emptyAccount');
            return;
        }
        let password = document.getElementById("password").value;
        if(isEmpty(password)){
            setMessageBar('emptyPassword');
            return;
        }
        let checkResult = check(account, password);
        if(checkResult){
            ipcRenderer.send('login');          //由主进程处理登录成功后的页面切换
        }
        else{
            setMessageBar('wrongPassword');
        }
    })
}

function isEmpty(value){
    if(value === ''){
        return true;
    }
    let re = new RegExp("^[ ]+$");              //只输入空格也算作空
    return re.test(value);
}

function check(account, password){              //检验账号密码是否正确
    return true;                                //暂时不添加检验，一律return true
}

function setMessageBar(arg){
    let messageBar = document.getElementById("message");
    switch(arg){
        case 'emptyAccount':{                   //未输入账号
            messageBar.innerHTML = "请输入账号后再登录！";
            break;
        }
        case 'emptyPassword':{                  //未输入密码
            messageBar.innerHTML = "请输入密码后再登录！";
            break;
        }
        case 'wrongPassword':{                  //账号或密码错误
            messageBar.innerHTML = "账号或密码错误，请重试。";
            let passwordBar = document.getElementById("password");
            passwordBar.value = "";             //清空账号密码输入框
            break;
        }
    }
}