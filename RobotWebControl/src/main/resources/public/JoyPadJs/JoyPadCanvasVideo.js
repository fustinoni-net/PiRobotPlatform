
"use strict";

window.requestAnimFrame = (function () {
    return window.requestAnimationFrame ||
    window.webkitRequestAnimationFrame ||
    window.mozRequestAnimationFrame ||
    window.oRequestAnimationFrame ||
    window.msRequestAnimationFrame ||
    function (callback) {
        window.setTimeout(callback, 1000 / 60);
    };
})();

var canvas,
c, // c is the canvas' context 2D
container,
halfWidth,
halfHeight,
leftPointerID = -1,
leftPointerPos = new Vector2(0, 0),
leftPointerStartPos = new Vector2(0, 0),
leftVector = new Vector2(0, 0);

var maxJoitickValue = 100;
var oldMotorX, oldMotorY;
var touches; // collections of pointers

var canvasBackgroundColor = "#111133";
var leftLed, distance, rightLed;

document.addEventListener("DOMContentLoaded", init);

window.onorientationchange = resetCanvas;
window.onresize = resetCanvas;

var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/robot/");
webSocket.onmessage = function (msg) { updateStatus(msg); };
webSocket.onclose = function () { alert("WebSocket connection closed"); };


//Send a message if it's not empty, then clear the input field
function sendMessage(message) {
    if (message !== "") {
        webSocket.send(message);
     }
}


//Update the chat-panel, and the list of connected users
function updateStatus(msg) {
    //canvas.style.backgroundColor = "green"
    var data = JSON.parse(msg.data);

    if (data.distanceFront){

        if (data.distanceFront < 300)
            distance.style.backgroundColor = "red";
        else
            distance.style.backgroundColor = "green";

        document.getElementById("distanceText").innerHTML = data.distanceFront;
//        var cc = document.getElementById("distance");
//        var cctx = cc.getContext("2d");
//        cctx.font = "30px Arial";
//        cctx.fillStyle = distance.style.backgroundColor;
////        cctx.fillStyle = distance.style.backgroundColor;
////        cctx.fillText(data.distanceFront,10,30);
//        cctx.fillStyle = "black";
//        cctx.fillText(data.distanceFront,10,30);
    }else if (data.ping){
        sendMessage("pong");        
    }else if (data.leftIRSensor){
        if (data.leftIRSensor == "true")
            leftLed.style.backgroundColor = "red";
        else
            leftLed.style.backgroundColor = "green";
    }else if (data.rightIRSensor){
        if (data.rightIRSensor == "true")
            rightLed.style.backgroundColor = "red";
        else
            rightLed.style.backgroundColor = "green";
    }
}



function init() {
    setupCanvas();
    touches = new Collection();
    canvas.addEventListener('pointerdown', onPointerDown, false);
    canvas.addEventListener('pointermove', onPointerMove, false);
    canvas.addEventListener('pointerup', onPointerUp, false);
    canvas.addEventListener('pointerout', onPointerUp, false);
    requestAnimFrame(draw);
}

function resetCanvas(e) {
    // resize the canvas - but remember - this clears the canvas too. 
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    
    //halfWidth = canvas.width / 2;
    halfWidth = canvas.width; // This remove the right touches that are not use for now.
    halfHeight = canvas.height / 2;

    //make sure we scroll to the top left. 
    window.scrollTo(0, 0);
}

function draw() {
    c.clearRect(0, 0, canvas.width, canvas.height);
    touches.forEach(function (touch) {
        if (touch.identifier === leftPointerID) {
            var controlPositionX, controlPositionY;
            var outCircleColor;
            
            if (Math.abs(leftPointerPos.x - leftPointerStartPos.x) > maxJoitickValue)
                controlPositionX = leftPointerStartPos.x + Math.sign(leftPointerPos.x - leftPointerStartPos.x) * maxJoitickValue ;
            else
                controlPositionX = leftPointerPos.x;
            
            if (Math.abs(leftPointerPos.y - leftPointerStartPos.y) > maxJoitickValue)
                controlPositionY = leftPointerStartPos.y + Math.sign(leftPointerPos.y - leftPointerStartPos.y) * maxJoitickValue ;
            else
                controlPositionY = leftPointerPos.y;
            
            if (Math.abs(leftPointerPos.x-leftPointerStartPos.x) > maxJoitickValue || Math.abs(leftPointerPos.y-leftPointerStartPos.y) > maxJoitickValue)
                outCircleColor = "red";
            else
                outCircleColor = "cyan";
            
            
            c.beginPath();
            c.fillStyle = "white";
            c.strokeStyle = "cyan";
            c.lineWidth = 2;
            c.arc(leftPointerStartPos.x, leftPointerStartPos.y-canvas.getBoundingClientRect().top, 50, 0, Math.PI * 2, true);
            c.stroke();
            c.beginPath();
            c.strokeStyle = outCircleColor;
            c.lineWidth = 4;
            c.arc(leftPointerStartPos.x, leftPointerStartPos.y-canvas.getBoundingClientRect().top, 100, 0, Math.PI * 2, true);
            c.stroke();
            c.beginPath();
            c.strokeStyle = "cyan";
            c.lineWidth = 2;
            c.fillText("type : " + touch.type + " id : " + touch.identifier + " x:" + (leftPointerStartPos.x - leftPointerPos.x) + " y:" + (leftPointerStartPos.y - leftPointerPos.y), touch.x + 30, touch.y - 30);
            c.arc(controlPositionX, controlPositionY-canvas.getBoundingClientRect().top, 40, 0, Math.PI * 2, true);
            c.stroke();
            
            motorChange((leftPointerStartPos.x - controlPositionX),(leftPointerStartPos.y - controlPositionY));

        } else {

            c.beginPath();
            c.fillStyle = "white";
            c.fillText("type : " + touch.type + " id : " + touch.identifier + " x:" + touch.x + " y:" + touch.y + " c.top:" +canvas.getBoundingClientRect().top , touch.x + 30, touch.y - 30);

            c.beginPath();
            c.strokeStyle = "red";
            c.lineWidth = "6";
            c.arc(touch.x, touch.y-canvas.getBoundingClientRect().top, 40, 0, Math.PI * 2, true);
            c.stroke();
        }
    });

    requestAnimFrame(draw);
}


function givePointerType(event) {
    switch (event.pointerType) {
        case event.POINTER_TYPE_MOUSE:
            return "MOUSE";
            break;
        case event.POINTER_TYPE_PEN:
            return "PEN";
            break;
        case event.POINTER_TYPE_TOUCH:
            return "TOUCH";
            break;
    }
}

function onPointerDown(e) {
    var newPointer = { identifier: e.pointerId, x: e.clientX, y: e.clientY, type: givePointerType(e) };
    if ((leftPointerID < 0) && (e.clientX < halfWidth)) {
        leftPointerID = e.pointerId;
        leftPointerStartPos.reset(e.clientX, e.clientY);
        leftPointerPos.copyFrom(leftPointerStartPos);
        leftVector.reset(0, 0);
    }

    touches.add(e.pointerId, newPointer);
}

function onPointerMove(e) {
    if (leftPointerID === e.pointerId) {
        leftPointerPos.reset(e.clientX, e.clientY);
        leftVector.copyFrom(leftPointerPos);
        leftVector.minusEq(leftPointerStartPos);
    }
    else {
        if (touches.item(e.pointerId)) {
            touches.item(e.pointerId).x = e.clientX;
            touches.item(e.pointerId).y = e.clientY;
        }
    }
}

function onPointerUp(e) {
    if (leftPointerID === e.pointerId) {
        leftPointerID = -1;
        leftVector.reset(0, 0);
        
         sendMessage("M:x=0,y=0");
    }
    leftVector.reset(0, 0);

    touches.remove(e.pointerId);
}

function setupCanvas() {

    canvas = document.getElementById('canvasSurfaceGame');
    
    canvas.style.backgroundImage = "url(http://raspi-pi2go.homenet.telecomitalia.it:8080/stream/video.mjpeg)";
    //canvas.style.backgroundImage = "url(http://" + location.hostname + ":8080/stream/video.mjpeg)";
    c = canvas.getContext('2d');

    leftLed = document.getElementById('leftLed');
    distance = document.getElementById('distance');
    rightLed = document.getElementById('rightLed');
    
    leftLed.style.backgroundColor = "green";
    distance.style.backgroundColor = "green";
    rightLed.style.backgroundColor = "green";

    document.getElementById('bLeft').onclick = function () {btnClick('bLeft');sendMessage("btn:left");} ;
    document.getElementById('bRight').onclick = function () {sendMessage("btn:right");} ;
    document.getElementById('bUp').onclick = function () {sendMessage("btn:up");} ;
    document.getElementById('bDown').onclick = function () {sendMessage("btn:down");} ;
    document.getElementById('bCenter').onclick = function () {sendMessage("btn:center");} ;
    document.getElementById('bA').onclick = function () {sendMessage("btn:a");} ;
    document.getElementById('bB').onclick = function () {sendMessage("btn:b");} ;
    document.getElementById('bC').onclick = function () {sendMessage("btn:c");} ;
    document.getElementById('bD').onclick = function () {sendMessage("btn:d");} ;
    document.getElementById('bOff').onclick = function () {sendMessage("btn:off");} ;

    resetCanvas();

    c.strokeStyle = "#ffffff";
    c.lineWidth = 2;
}


function btnClick(buttonName){
    //setTimeout((document.getElementById('bLeft').style.backgroundColor = "#110000"), 500);
    //setTimeout((document.getElementById('bLeft').style.backgroundColor = "#001100"), 1500);
}


function motorStop(){
    motorChange(0,0);
}

function motorChange(x,y){
    
    //(a/b>>0) integer division see: http://stackoverflow.com/questions/4228356/integer-division-in-javascript
    
    x = Math.sign(x)* 9 + (x/10>>0)*10;
    y = Math.sign(y)* 9 + (y/10>>0)*10;
//    x =  Math.floor(x/10)*10;
//    y =  Math.floor(y/10)*10;
    
    if (oldMotorX === x && oldMotorY ===y) return;
    
    oldMotorX = x;
    oldMotorY = y;
    sendMessage("M:x=" + x + ",y=" + y);
}


Math.sign = Math.sign || function(x) {
  x = +x; // convert to a number
  if (x === 0 || isNaN(x)) {
    return x;
  }
  return x > 0 ? 1 : -1;
};
