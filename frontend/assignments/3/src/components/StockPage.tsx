import React, { useEffect, useRef, useState } from "react";
import { io } from "socket.io-client";
import { useNavigate } from "react-router-dom";
import { createUseStyles } from "react-jss";
import { DrawerAppBar } from "./navbar";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../store/store";
import Paper from "@mui/material/Paper";
import ArrowUpwardIcon from "@mui/icons-material/ArrowUpward";
import ArrowDownwardIcon from "@mui/icons-material/ArrowDownward";
import axios from "axios";
import { Istocks } from "../config/interface";
import { setStock } from "../Slices/DashboardSlice";
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
        height: "100%",
        marginRight: "1rem",
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
        height: "100%",
        alignItems: "center",
        padding: "0 10px",
        marginRight: "1rem",
      },
      "& #price-value": {
        display: "flex",
        alignItems: "center",
        color: "inherit",
      },
      "& #quantity-input": {
        textAlign: "center",
        border: "1px solid #000",
        height: "100%",
        width: "250px",
        marginRight: "0.5rem",
      },
      "& .buttons": {
        "& button": {
          height: "100%",
        },
        "& #buy": {
          cursor: "pointer",
          color: "#2f9e44",
          backgroundColor: "#b2f2bb",
          border: "1px solid #2f9e44",
          width: "3rem",
          marginRight: "0.5rem",
          fontSize: "0.7rem",
        },
        "& #sell": {
          cursor: "pointer",
          color: "#e03131",
          backgroundColor: "#ffe9e9",
          border: "1px solid #e03131",
          width: "3rem",
          fontSize: "0.7rem",
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
      width: "200%",
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
  },

  historyContainer: {
    border: "1px solid black",
    height: "50%",
    overflowY: "auto",
    width: "95%",
    "& .mainDiv": {
      border: "1px solid #000",
      borderRadius: "10px",
      margin: "0.5rem",
      color: "#000",
      "& .quantityDiv": {
        fontSize: "25px",
        marginLeft: "0.5rem",
      },
      "& .actionDiv": {
        marginTop: "-1rem",
        marginLeft: "16rem",
      },
      "& .datetimeDiv": {
        marginTop: "-1rem",
        marginLeft: "0.5rem",
      },
    },
  },
  transactions: {
    marginTop:"1rem",
    width:"95%",
    border:"1px solid #000",
    padding:"1rem"
  },

  quantityInput: {
    backgroundColor: "#fff",
    color: "#000",
  },
  dottedLine: {
    position: "absolute",
    backgroundColor: "transparent",
    borderRight: "2px dotted black",
    height: "100%",
  },
  rowdottedLine: {
    position: "absolute",
    marginTop: "-31rem",
    backgroundColor: "transparent",
    borderBottom: "2px dotted black",
    height: "100%",
  },
  logoDropdown: {
    backgroundColor: "#fff",
    color: "#000",
    width: "100%",
  },
  selectedStock: {
    backgroundColor: "#ccc",
  },
  stockSymbol: {
    backgroundColor: "#ccc",
    fontWeight: "bold",
  },
});

const StockPage: React.FC = () => {
  const classes = useStyles();
  const selectedStock = useSelector((state: RootState) => state.stocks.stock);
  const [priceValue, setPriceValue] = useState<number>(
    parseInt(selectedStock.base_price)
  );
  const [quantityInput, setQuantityInput] = useState<string>("");
  const stocks = useSelector((state: RootState) => state.stocks.stocks);

  const [history, setHistory] = useState<
    { quantity: string; action: string; datetime: string }[]
  >([]);
  const [priceChangeColor, setPriceChangeColor] = useState<string>("");
  const [candlesticks, setCandlesticks] = useState<
    {
      priceChange: number;
      color: string;
      height: number;
    }[]
  >([]);

  useEffect(() => {
    const socket = io("http://localhost:3001");
  
    const intervalId = setInterval(() => {
      socket.on("candlestick", (candlestick) => {
        updatePrice(candlestick);
        updateCandlesticks(candlestick);
      });
    }, 5000);
  
    return () => {
      socket.disconnect();
      clearInterval(intervalId); 
    };
  }, [selectedStock]);
  

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

  const handleBuy = async () => {
    try {
      const response = await axios.post(
        "http://localhost:3001/api/transaction/buy",
        {
          stock_name: selectedStock.stock_name,
          stock_symbol: selectedStock.stock_symbol,
          transaction_price: parseInt(quantityInput) * priceValue,
        }
      );
      if (response.status === 200) {
        updateHistory("BUY");
      } else {
        alert("insufficient wallet balance");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("insufficient wallet balance");
    }
  };

  const handleSell = async () => {
    try {
      const response = await axios.post(
        "http://localhost:3001/api/transaction/sell",
        {
          stock_name: selectedStock.stock_name,
          stock_symbol: selectedStock.stock_symbol,
          transaction_price: parseInt(quantityInput) * priceValue,
        }
      );
      if (response.status === 200) {
        updateHistory("SELL");
      } else {
        alert("Failed to process transaction");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Failed to process transaction");
    }
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

  const graphRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (graphRef.current) {
      graphRef.current.scrollLeft = graphRef.current.scrollWidth;
    }
  }, [candlesticks]);

  const dispatch: AppDispatch = useDispatch();
  const navigate = useNavigate();
  const handleStockSelect = (selectedStock: Istocks) => {
    console.log("dispatching selected stock from dropdown ", selectedStock);
    dispatch(setStock(selectedStock));
    setPriceValue(parseInt(selectedStock.base_price));
    navigate(`/stock/${selectedStock.stock_name}`);
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
              <select
                className={classes.logoDropdown}
                onChange={(e) => {
                  const selectedStock = stocks.find(
                    (stock) => stock.stock_name === e.target.value
                  );
                  if (selectedStock) {
                    handleStockSelect(selectedStock);
                  }
                }}
              >
                <option value="">
                  {selectedStock.stock_symbol} {selectedStock.stock_name}
                </option>
                {stocks.map((stock, index) => (
                  <option key={index} value={stock.stock_name}>
                    <div className={classes.stockSymbol}>
                      {stock.stock_symbol}
                    </div>
                    <div>{stock.stock_name}</div>
                  </option>
                ))}
              </select>
            </div>
            <div id="price">
              <h5>price</h5>
              <p id="price-value" style={{ color: priceChangeColor }}>
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
              <div className="graph" ref={graphRef}>
                {[...Array(4)].map((_, rowIndex) => (
                  <div
                    key={`row-${rowIndex}`}
                    className={classes.rowdottedLine}
                    style={{
                      top: `${125 * (rowIndex + 1)}px`,
                      left: 0,
                      width: "100%",
                      zIndex: 2,
                    }}
                  ></div>
                ))}
                {[...Array(21)].map((_, colIndex) => (
                  <div
                    key={`col-${colIndex}`}
                    className={classes.dottedLine}
                    style={{
                      top: 0,
                      width: "1px",
                      height: "100%",
                      left: `${100 * (colIndex + 1)}px`,
                      zIndex: 1,
                    }}
                  ></div>
                ))}
                {candlesticks.map((candlestick, index) => (
                  <div
                    key={index}
                    className="bar"
                    style={{
                      height: `${candlestick.height}px`,
                      backgroundColor: candlestick.color,
                      marginBottom: "2px",
                      zIndex: 3,
                    }}
                  ></div>
                ))}
              </div>
            </Paper>
          </div>
        </div>
        <div className={classes.rightContainer}>
          <div className={classes.historyContainer}>
            <h2>History</h2>
            {history.map((item, index) => (
              <div key={index} className="mainDiv">
                <div className="quantityDiv">{item.quantity} Stocks</div>
                <span
                  className="actionDiv"
                  style={{ color: item.action === "BUY" ? "green" : "red" }}
                >
                  {item.action}
                </span>
                <div className="datetimeDiv">{item.datetime}</div>
              </div>
            ))}
          </div>
          <div className={classes.transactions}>
          <h3>Sagun bought 500 Morgan PLC shares</h3>
          <h3>Aakash sold 100 Morgan PLC shares</h3>
          <h3>Gaurav bought 1000 Morgan PLC shares</h3>
        </div>
        </div>
       
      </div>
    </div>
  );
};

export default StockPage;
