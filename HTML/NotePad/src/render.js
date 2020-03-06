/* render.js */

const { remote, ipcRender } = require('electron');
const { Menu, MenuItem } = remote;
const dialog = remote.dialog;

let textEditor = document.getElementById("textEditor");
let saved = true;
let verifyQuit = false;
let currFile = null;

//动态创建右击菜单
const menu = new Menu();
menu.append(new MenuItem({
    label: '复制',
    role: 'copy'
}));

menu.append(new MenuItem({
    label: '剪切',
    role: 'cut'
}));

menu.append(new MenuItem({
    lebel: '粘贴',
    role: 'paste'
}))

menu.append(new MenuItem({ type:'seperator' }));

menu.append(new MenuItem({
    label: '全选',
    role: 'selectAll'
}));

textEditor.addEventListener('contextmenu', (e) => {
    e.preventDefault();
    menu.popup({ textEditor:remote.getCurrentWindow()}, false);
});

//检查是否对文档进行了修改
textEditor.oninput = () => {
    if(saved){
        document.title += " *";
    }
    saved = false;
}

//实现菜单操作
ipcRender.on('action',(event,arg) => {
    switch(arg){
        case 'new':{
            checkSaved();
            if(verifyQuit){
                initText();
            }
            break;
        }
        case 'open':{
            checkSaved();
            if(verifyQuit){
                openFile();
            }
            break;
        }
        case 'save':{
            save();
            break;
        }
        case 'exit':{
            checkSaved();
            if(verifyQuit){
                ipcRender.sendSync('quit');
            }
            break;
        }
    }
});

function checkSaved(){
    if(saved){ return; }    //若已保存, 则不做提示
    //showMessageBox 返回值为用户选项的index值
    let selection = dialog.showMessageBox(remote.getCurrentWindow(),{
        type = 'question',
        buttons = [
            '是','否','返回'
        ],
        message = "您的文档尚未保存，是否进行保存？"
    });
    switch(selection){
        case 0:{
            save();
            verifyQuit = true;
            break;
        }
        case 1:{
            verifyQuit = true;
            break;
        }
        case 2:{
            verifyQuit = false;
            break;
        }
    }
}

function save(){
    if(!currFile){  //是新文件，则需选择存储路径
        const path = dialog.showSaveDialogSync(remote.getCurrentWindow(),{
            title: "保存文件",
            filters:[
                { name:'文本文件', extentions:['txt','md'] },
                { name: '所有文件', extensions: ['*'] }
            ],
            properties:['dontAddToRecent']
        });
        if(path){
            currFile = path;
        }
    }
    if(currFile){
        const textContent = textEditor.value;
        const fs = require('fs');
        fs.writeFileSync(currFile, textContent);
        saved = true;
        document.title = currFile;
    }
}

function initText(){
    currFile = null;
    textEditor.value = "";
    document.title = "";
    saved = true;
}

function openFile(){
    const file = dialog.showOpenDialogSync(remote.getCurrentWindow(),{
        title: "打开文件",
        filters: [{ name:'文本文件', extentions:['txt','md'] }],
        properties:['openFile']
    });
    if(file){
        const fs = require('fs');
        currFile = file[0];     //dialog.showOpenDialogSync()的返回值为选择文件的路径数组
        const textContent = fs.readFileSync(currFile,'utf8');
        textEditor.value = textContent;
        document.title = currFile;
        saved = true;
    }
}

/** Render Process中运用了remote, ipcRender等模块, 
 * 文档可见下方链接：
 * https://www.electronjs.org/docs/api/remote
 * https://www.electronjs.org/docs/api/ipc-renderer
 * https://www.electronjs.org/docs/api/menu-item
 * https://www.electronjs.org/docs/api/dialog
*/

/**
 * 版本记录：
 * ver 0.1 2020/3/6 18:13 =>
 * 已经可以通过npm start指令工作，且具有基本的复制粘贴等功能，
 * 但保存功能以及右键单击功能暂时无效，有待修复。
 */