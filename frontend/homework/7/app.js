const express=require("express");
const http=require("http");
const cors=require("cors");
const socketIO=require("socket.io");

const app=express();
app.use(cors());
app.use(express.json());
const server= http.createServer(app);
const io=new socketIO.Server(server,{
    cors:{
        origin:"http://127.0.0.1:5501"
    }
});

app.get("/",(req,res)=>{
    res.json({
        "msg":"hello world"
    })
});

io.on("connection",(socket)=>{
    console.log("socket connection created");
    
    socket.on("message",(payload)=>{
        console.log("payload",payload);
        io.except(socket.id).emit("new-messsage",payload);
    })
})

server.listen(3000,()=>{
    console.log("application started on port 3000");
})