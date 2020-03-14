/* main.js */

"use strict";

const { app, BrowserWindow, Menu, ipcMain} = require('electron');

let loginWindow;

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
    loginWindow.webContents.openDevTools();
}

Menu.setApplicationMenu(null);

app.whenReady().then(createLoginWindow);

app.on('active', () => {
    if(BrowserWindow.getAllWindows().length === 0){
        createLoginWindow();
    }
});

ipcMain.on('close', () => {
    app.quit();
});
