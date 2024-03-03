import React, { useEffect, useState } from "react";
import { io } from "socket.io-client";
import { useParams } from "react-router-dom";
import { createUseStyles } from "react-jss";
import { DrawerAppBar } from "./navbar" ;
import { useSelector } from "react-redux";
import { RootState } from "../store/store";
import Paper from "@mui/material/Paper";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import ArrowDownwardIcon from "@mui/icons-material/ArrowDownward";

const useStyles = createUseStyles({
  body: {
    fontFamily: "'Poppins', sans-serif",
  },
  nav: {
    backgroundColor: "#1871c2",
    color: "white",
    textAlign: "center",
    width: "100%",
    position: "fixed",
    height: "60px",
    top: 0,
  },
  mainContainer: {
    display: "grid",
    marginTop: "7rem",
    height: "100%",
    gridTemplateColumns: "repeat(4, 1fr)",
  },
  leftContainer: {
    gridColumn: "span 3",
    display: "flex",
    flexDirection: "column",
    padding: "10px",
    "& .dashboard": {
      display: "flex",
      justifyContent: "space-evenly",
      marginBottom: "10px",
      "& #logo": {
        display: "flex",
        border: "1px solid #000",
        width: "250px",
        height: "60px",
        padding: 0,
        "& img": {
          height: "100%",
          width: "80px",
        },
        "& h5": {
          marginTop: "-10px",
          textAlign: "center",
          display: "inline-block",
          fontSize: "40px",
        },
      },
      "& #price": {
        display: "flex",
        justifyContent: "space-evenly",
        width: "400px",
        border: "1px solid #000",
        alignItems: "center",
        padding: "0 10px",
      },
      "& #price-value": {
        display: "flex",
        alignItems: "center",
        color: "inherit",
      },
      "& #arrow-icon": {
        fontSize: "1.5rem",
        marginLeft: "5px",
      },
      "& #quantity-input": {
        textAlign: "center",
        border: "1px solid #000",
        height: "60px",
        width: "250px",
      },
      "& .buttons": {
        "& button": {
          height: "60px",
        },
        "& #buy": {
          cursor: "pointer",
          color: "#2f9e44",
          backgroundColor: "#b2f2bb",
          border: "1px solid #2f9e44",
        },
        "& #sell": {
          cursor: "pointer",
          color: "#e03131",
          backgroundColor: "#ffe9e9",
          border: "1px solid #e03131",
        },
      },
    },
    "& .graph": {
      height: "500px",
      border: "1px solid rgb(21, 16, 16)",
      position: "relative",
      display: "flex",
      flexWrap: "wrap-reverse",
      whiteSpace: "nowrap",
      "& .bar": {
        width: "20px",
        border: "1px solid #ccc",
      },
    },
  },
  graphContainer: {
    overflowX: "auto",
    overflowY: "auto",
  },
  rightContainer: {
    gridColumn: "span 1",
    border: "1px solid black",
    "& .mainDiv": {
      border: "1px solid #000",
      borderRadius: "10px",
      color: "#000",
      "& .quantityDiv": {
        fontSize: "25px",
      },
      "& .actionDiv": {
        display: "inline-block",
        marginLeft: "220px",
      },
      "& .datetimeDiv": {
        marginTop: "-22px",
      },
    },
  },
  quantityInput: {
    backgroundColor: "#fff",
    color: "#000",
  },
});

const StockPage: React.FC = () => {
  const classes = useStyles();
  const selectedStock = useSelector((state: RootState) => state.stocks.stock);
  const [priceValue, setPriceValue] = useState<number>(
    parseInt(selectedStock.base_price)
  );
  const [quantityInput, setQuantityInput] = useState<string>("");
  const [history, setHistory] = useState<
    { quantity: string; action: string; datetime: string }[]
  >([]);
  const [priceChangeColor, setPriceChangeColor] = useState<string>("");
  const [candlesticks, setCandlesticks] = useState<{
    priceChange: number;
    color: string;
    height: number;
  }[]>([]);

  const { stock_name } = useParams<{ stock_name: string }>();

  useEffect(() => {
    const socket = io("http://localhost:3001");
    socket.on("candlestick", (candlestick) => {
      updatePrice(candlestick);
      updateCandlesticks(candlestick);
    });

    return () => {
      socket.disconnect();
    };
  }, []);

  const updatePrice = (candlestick: {
    priceChange: number;
    color: string;
    height: number;
  }) => {
    setPriceValue((prevPrice) => prevPrice + candlestick.priceChange);
    setPriceChangeColor(candlestick.color);
  };

  const updateCandlesticks = (candlestick: {
    priceChange: number;
    color: string;
    height: number;
  }) => {
    setCandlesticks((prevCandlesticks) => [...prevCandlesticks, candlestick]);
  };

  const handleBuy = () => {
    updateHistory("BUY");
  };

  const handleSell = () => {
    updateHistory("SELL");
  };

  const updateHistory = (action: string) => {
    const quantity = quantityInput;
    const datetime = new Date().toLocaleString();

    setHistory((prevHistory) => [
      ...prevHistory,
      {
        quantity,
        action,
        datetime,
      },
    ]);
    setQuantityInput("");
  };

  return (
    <div className={classes.body}>
      <header>
        <nav className={classes.nav}>
          <DrawerAppBar />
        </nav>
      </header>

      <div className={classes.mainContainer}>
        <div className={classes.leftContainer}>
          <div className="dashboard">
            <div id="logo">
              <p>{selectedStock.stock_symbol}</p>
              <p>{stock_name}</p>
            </div>
            <div id="price">
              <h5>price</h5>
              <p
                id="price-value"
                style={{ color: priceChangeColor }}
              >
                {priceValue}{" "}
                {candlesticks.length > 0 &&
                  (candlesticks[candlesticks.length - 1].priceChange > 0 ? (
                    <ArrowUpwardIcon id="arrow-icon" />
                  ) : (
                    <ArrowDownwardIcon id="arrow-icon" />
                  ))}
              </p>
            </div>
            <div id="quantity">
              <input
                type="number"
                id="quantity-input"
                placeholder="Enter QTY"
                value={quantityInput}
                className={classes.quantityInput}
                onChange={(e) => setQuantityInput(e.target.value)}
              />
            </div>
            <div className="buttons">
              <button id="buy" onClick={handleBuy}>
                BUY
              </button>
            </div>
            <div className="buttons">
              <button id="sell" onClick={handleSell}>
                SELL
              </button>
            </div>
          </div>
          <div className={classes.graphContainer}>
            <Paper>
              <div className="graph">
                {candlesticks.map((candlestick, index) => (
                  <div
                    key={index}
                    className="bar"
                    style={{
                      height: `${candlestick.height}px`,
                      backgroundColor: candlestick.color,
                      marginBottom: "2px",
                    }}
                  ></div>
                ))}
              </div>
            </Paper>
          </div>
        </div>
        <div className={classes.rightContainer}>
          <h2>History</h2>
          <div className="history-container">
            {history.map((item, index) => (
              <div key={index} className="history-item">
                <div className="quantityDiv">{item.quantity} Stocks</div>
                <div
                  className="actionDiv"
                  style={{ color: item.action === "BUY" ? "green" : "red" }}
                >
                  {item.action}
                </div>
                <div className="datetimeDiv">{item.datetime}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default StockPage;
