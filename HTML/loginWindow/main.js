/* main.js */

"use strict";

const { app, BrowserWindow, Menu, ipcMain} = require('electron');

let mainWindow;

function createWindow(){
    mainWindow = new BrowserWindow({
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
    mainWindow.loadFile('index.html');
    mainWindow.webContents.openDevTools();
}

Menu.setApplicationMenu(null);

app.whenReady().then(createWindow);

app.on('active', () => {
    if(BrowserWindow.getAllWindows().length === 0){
        createWindow();
    }
});

ipcMain.on('close', () => {
    app.quit();
})