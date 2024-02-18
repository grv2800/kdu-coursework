document.addEventListener("DOMContentLoaded", () => {
    let socket = io("http://localhost:3010");
    let recipientSocketId = null;
    let selectedUser = null; 
    let chatHeader = null; 
  
    const userDataString = sessionStorage.getItem("userData");
    const userData = JSON.parse(userDataString);
  
    socket.emit("joinChat", userData);
  
    socket.on("userJoined", (onlineUsers) => {
      console.log("User joined:", onlineUsers);
  
      const userList = document.querySelector(".userList");
      userList.innerHTML = "";
  
      onlineUsers.forEach((user) => {
        let listItem = document.createElement("div");
        listItem.classList.add("listItem");
        let userName = document.createElement("h3");
        userName.textContent = user.user.user_name;
  
        const userEmail = document.createElement("p");
        userEmail.textContent = user.user.user_email_id;
  
        listItem.appendChild(userName);
        listItem.appendChild(userEmail);
        userList.appendChild(listItem);
  
        listItem.addEventListener("click", function (event) {
          selectedUser = user.user
          const chatPage = document.querySelector(".chatWindow");
  
          if (chatHeader) {
            chatHeader.remove();
          }
  
          chatHeader = document.createElement("div");
          chatHeader.classList.add("chatHeader");
  
          const clickedUserName = document.createElement("h3");
          clickedUserName.textContent = selectedUser.user_name;
  
          const clickedUserEmail = document.createElement("p");
          clickedUserEmail.textContent = selectedUser.user_email_id;
  
          chatHeader.appendChild(clickedUserName);
          chatHeader.appendChild(clickedUserEmail);
          chatPage.appendChild(chatHeader);
  
          socket.emit("getRecipientSocketId", selectedUser.user_name);
  
          if (chatPage.style.display === "none") {
            chatPage.style.display = "flex";
          } else {
            chatPage.style.display = "none";
          }
        });
      });
    });
  
    const messageInput = document.getElementById("msgInput");
    const sendButton = document.getElementById("sendMessages");
    const messageOutput = document.getElementById("messsages");
  
    function addMessage(type, message) {
      const wrap = document.createElement("div");
      if (type === "sender") {
        wrap.classList.add("sender");
      } else {
        wrap.classList.add("receiver");
      }
      const element = document.createElement("p");
      element.innerText = message;
      wrap.appendChild(element);
      messageOutput.appendChild(wrap);
    }
  
    socket.on("recipientSocketId", (socketID) => {
      recipientSocketId = socketID;
    });
  
    sendButton.addEventListener("click", () => {
      const message = messageInput.value;
      console.log(message);
      if (recipientSocketId) {
        socket.emit("message", { message, socketID: recipientSocketId });
      } else {
        console.log("Recipient socket ID not available.");
      }
      addMessage("sender", message);
    });
  
    socket.on("new-message", (messageData) => {
      addMessage("receiver", messageData.message);
    });
  });
  