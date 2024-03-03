import express from "express";
import http from "http";
import { Server as SocketIO } from "socket.io";

const app = express();
const server = http.createServer(app);

const priceHistory: { priceChange: number; color: string; height: number }[] =
  [];
let walletBalance = 2100;

const io = new SocketIO(server, {
  cors: {
    origin: "http://localhost:5173",
  },
});

io.on("connection", (socket) => {
  console.log("socket connection created");
});

function generatePriceChange(): number {
  return Math.floor(Math.random() * 501);
}

function generateCandlestick() {
  const priceChange = generatePriceChange();
  const candlestick = {
    priceChange: priceChange,
    color: priceChange >= 0 ? "green" : "red",
    height: Math.abs(priceChange) + 1,
  };
  return candlestick;
}

function sendCandlestick() {
  const candlestick = generateCandlestick();
  priceHistory.push(candlestick);
  io.emit("candlestick", candlestick);
}

setInterval(sendCandlestick, 5000);

app.use(express.json());
const transactions: {
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  timestamp: string;
  status: string;
}[] = [];

app.post("/api/transaction/buy", (req, res) => {
  const { stock_name, stock_symbol, transaction_price, timestamp } = req.body;
  if (transaction_price > walletBalance) {
    const status = "Failed";
    transactions.push({
      stock_name,
      stock_symbol,
      transaction_price,
      timestamp,
      status,
    });
    return res
      .status(400)
      .json({
        status: "Failed",
        message: "Transaction amount exceeds wallet balance",
      });
  }
  walletBalance -= transaction_price;
  transactions.push({
    stock_name,
    stock_symbol,
    transaction_price,
    timestamp,
    status: "Success",
  });
  return res.status(200).json({ 
    status: 'Success',
    stock_name,
    stock_symbol,
    transaction_price,
    timestamp
});
});
app.post("/api/transaction/sell", (req, res) => {
  const { stock_name, stock_symbol, transaction_price, timestamp } = req.body;
  walletBalance += transaction_price;
  transactions.push({
    stock_name,
    stock_symbol,
    transaction_price,
    timestamp,
    status: "Success",
  });
  return res.status(200).json({ 
    status: 'Success',
    stock_name,
    stock_symbol,
    transaction_price,
    timestamp
});
});
server.listen(3001, () => {
  console.log(`Server running on port 3001`);
});
