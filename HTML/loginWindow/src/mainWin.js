/* mainWin.js */
// 为登录后的用户界面进行渲染

const { ipcRenderer } = require('electron');

let close = document.getElementById("close");
if(close){
    close.addEventListener('click', () => {
        ipcRenderer.send('mainWin-close');
    })
}

let min = document.getElementById("min");
if(min){
    close.addEventListener('click', () => {
        ipcRenderer.send('mainWin-min');
    })
}

