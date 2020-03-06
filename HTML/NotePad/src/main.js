/* main.js */

const {app, BrowserWindow} = require('electron');
const {ipcMain, Menu} = require('electron');
const path = require('path');
let mainWindow;

//Menu Set
//可见Electron文档Menu部分示例
const menuTemplate = [
    {
        label: '文件',
        submenu: [
            {
                label: '新建',
                click: function(){
                    mainWindow.webContents.send('action','new')
                }
            },
            {
                label: '打开',
                click(){ mainWindow.webContents.send('action','open') }
            },
            {
                label: '保存',
                click(){ mainWindow.webContents.send('action', 'save') }
            },
            {
                label: '退出',
                click(){ mainWindow.webContents.send('action', 'exit') }
            }
        ]
    },
    {
        label: '编辑',
        submenu: [
            { label:'剪切', role:'cut' },
            { label:'复制', role:'copy'},
            { label:'粘贴', role:'paste'}
        ]
    }
];

let menu = Menu.buildFromTemplate(menuTemplate);
Menu.setApplicationMenu(menu);

function createWindow(){
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        resizable: false,
        backgroundColor: '#e9e9e9',
        webPreferences:{
            nodeIntegration: true
        }
    });
    mainWindow.loadFile('index.html');
}

app.whenReady().then(createWindow);

app.on('window-all-closed',() => {
    if(process.platform !== "darwin"){  //!==为不严格相当
        app.quit();
    }
});

app.on('active',() => {
    if(mainWindow == null){
        createWindow();
    }
})

ipcMain.on('exit', () => {
    app.quit();
})


/** 运用了app, BrowserWindow, ipcMain, Menu四个MainProcess模块
 * 分别可见以下链接：
 * https://www.electronjs.org/docs/api/app
 * https://www.electronjs.org/docs/api/browser-window
 * https://www.electronjs.org/docs/api/ipc-main
 * https://www.electronjs.org/docs/api/menu
 */