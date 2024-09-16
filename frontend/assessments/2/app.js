    const express = require('express');
    const http = require('http');
    const socketIO = require('socket.io');

    const app = express();
    const server = http.createServer(app);

    let priceHistory = [];

    const io=new socketIO.Server(server,{
        cors:{
            origin:"http://127.0.0.1:5500"
        }
    });

    io.on("connection",(socket)=>{
        console.log("socket connection created");
    })

    function generatePriceChange() {
    return Math.floor(Math.random() * 501);
    }

    function generateCandlestick() {
    const priceChange = generatePriceChange()-250;
    const candlestick = {
        priceChange: priceChange,
        color: priceChange >= 0 ? 'green' : 'red',
        height: Math.abs(priceChange) + 1 
    };
    return candlestick;
    }

    function sendCandlestick() {
    const candlestick = generateCandlestick();
    priceHistory.push(candlestick);
    io.emit('candlestick', candlestick);
    }

    setInterval(sendCandlestick, 5000);

    app.use(express());

    server.listen(3001, () => {
    console.log(`Server running on port 3001`);
    });
