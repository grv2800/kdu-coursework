const express = require("express");
const app = express();
const cors = require("cors");
const http = require("http");
const socketIO = require("socket.io");
const { log } = require("console");

app.use(cors());
app.use(express.json());

const server = http.createServer(app);
const io = new socketIO.Server(server, {
  cors: {
    origin: "http://127.0.0.1:5500",
  },
});

const onlineUsers = new Map();

io.on("connection", (socket) => {
  console.log("New user connected");

  socket.on("joinChat", (userData) => {
    onlineUsers.set(socket.id, userData);
    console.log(`${userData.user.user_name} joined the chat`);
    io.emit("userJoined", Array.from(onlineUsers.values()));
  });
  socket.on("message", (messageData) => {
    console.log(`Message :${messageData.message}`);
    const recipientSocketId = messageData.socketID;
    io.to(recipientSocketId).emit("new-message", messageData);
  });

  socket.on("disconnect", () => {
    onlineUsers.delete(socket.id);
    console.log(`User left the chat`);
  });

  socket.on("getRecipientSocketId", (selectedUsername) => {
    console.log(`Fetching recipient's socket ID for ${selectedUsername}`);
    let recipientSocketId = null;
    onlineUsers.forEach((userData, socketId) => {
      if (userData.user.user_name === selectedUsername) {
        recipientSocketId = socketId;
      }
    });
    if (recipientSocketId) {
      console.log(
        `Recipient's socket ID for ${selectedUsername}: ${recipientSocketId}`
      );
      socket.emit("recipientSocketId", recipientSocketId);
    } else {
      console.log(`Recipient ${selectedUsername} is not online`);
      socket.emit("recipientSocketId", null);
    }
  });
});

const users = [
  {
    id: 1,
    user_name: "grv28",
    user_email_id: "grv28@gmail.com",
    password: "password1",
    profile_url: "localhost./3300",
  },
  {
    id: 2,
    user_name: "nitesh01",
    user_email_id: "nitesh01@gmail.com",
    password: "password2",
    profile_url: "localhost./3301",
  },
  {
    id: 3,
    user_name: "prashant25",
    user_email_id: "prashant25@gmail.com",
    password: "password3",
    profile_url: "localhost./3302",
  },
];

app.post("/api/user/login", (req, res) => {
  const { username, password } = req.body;
  const user = users.find(
    (u) => u.user_name === username && u.password === password
  );
  if (user) {
    res.status(200).json({ user });
  } else {
    res.status(401).send("Invalid username or password");
  }
});
let posts = [];

app.get("/api/posts", (req, res) => {
  console.log("posts fetched from the array");
  const { page = 1, pageSize = 5 } = req.query;
  const startIndex = (page - 1) * pageSize;
  const endIndex = startIndex + parseInt(pageSize);
  const paginatedPosts = posts.slice(startIndex, endIndex);
  console.log(paginatedPosts);
  res.json(paginatedPosts);
});

app.post("/api/posts", (req, res) => {
  const newPost = req.body;
  console.log("post added into the array");
  posts.unshift(newPost);
  res.status(201).json(newPost);
});

server.listen(3010, () => {
  console.log("application started on port 3010");
});
module.exports = users;
