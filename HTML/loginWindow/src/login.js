/* login.js */

const { ipcRenderer } = require('electron');

let close = document.getElementById("close");
if(close){
    close.addEventListener('click', () => {
        ipcRenderer.send('close');
    })
}