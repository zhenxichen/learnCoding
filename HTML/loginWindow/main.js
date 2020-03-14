/* main.js */

"use strict";

const { app, BrowserWindow, Menu, ipcMain} = require('electron');

let loginWindow;        //登录界面
let mainWindow;         //登录后显示的用户界面

function createLoginWindow(){
    loginWindow = new BrowserWindow({
        width: 430,
        height: 330,
        minimizable: true,
        maximizable: false,
        frame: false,
        resizable: true,
        webPreferences: {
            nodeIntegration: true
        }
    });
    loginWindow.loadFile('index.html');
    //loginWindow.webContents.openDevTools();
}

Menu.setApplicationMenu(null);

app.whenReady().then(createLoginWindow);

app.on('active', () => {
    if(BrowserWindow.getAllWindows().length === 0){
        createLoginWindow();
    }
});

ipcMain.on('loginWin-close', () => {
    loginWindow.close();
    if(BrowserWindow.getAllWindows().length === 0){
        app.quit();
    }
});

ipcMain.on('loginWin-min',() => {
    loginWindow.minimize();
})


function createMainWindow(){
    mainWindow = new BrowserWindow({
        width: 1037,
        height: 690,
        frame: false,
        resizable: false,
        webPreferences: {
            nodeIntegration: true
        }
    });
    mainWindow.loadFile('mainWin.html');
    //mainWindow.webContents.openDevTools();
}

ipcMain.on('login',() => {
    //登录成功后，关闭登录界面，切换到用户界面
    loginWindow.hide();
    createMainWindow();
    loginWindow.close();    //先创建用户界面再关闭登录界面，防止触发app.quit()
})