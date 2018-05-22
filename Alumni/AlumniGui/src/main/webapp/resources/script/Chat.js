/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var websocket;

window.onload = function () {
    //invokeConnection();
}

function invokeConnection() {
    console.log("open WebSocket: " + obtainUri());
    websocket = new WebSocket(obtainUri());
    websocket.onerror = function (evt) {
        onError(evt);
    };
    websocket.onmessage = function (evt) {
        onMessage(evt);
    };
    return true;
}

function obtainUri() {
    return "ws://" + document.location.host + "/Alumni/classroomchat";
}

function onError(evt) {
    console.log("error: " + evt.data);
}


function onMessage(evt) {
    console.log("received: " + evt.data);
    element = document.getElementById("messages");
    if (element.value.length === 0) {
        element.value = evt.data;
    } else {
        oldTexts = element.value.split("\n").slice(-19);
        element.value = oldTexts.join("\n") + "\n" + evt.data;
        element.scrollTop = element.scrollHeight;
    }
    return;
}

function acceptValue(element) {
    msg = element.value.replace("\n", "");
    websocket.send(msg);
    element.value = "";
    return true;
}

