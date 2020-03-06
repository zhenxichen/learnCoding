/* main.js */

const {app, BrowserWindow} = require('electron');
const path = require('path');
let mainWindow;

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

function createWindow(){
    mainWindow = new BrowserWindow({
        width = 800,
        height = 600,
        resizable = false,
        backgroundColor = '#e9e9e9',
        webPreferences:{
            nodeIntegration: true
        }
    });
    mainWindow.loadFile('index.html');
    
}
