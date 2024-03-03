import React, { useState } from "react";
import { Link } from "react-router-dom";
import { DrawerAppBar } from "./navbar";
import { Istocks } from "../config/interface";
import { Pagination, Tab, Tabs } from "@mui/material";
import { createUseStyles } from "react-jss";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../store/store";
import { setStock } from "../Slices/DashboardSlice";
const useStyles = createUseStyles({
  stockItem: {
    display: "flex",
    color: "#000",
    justifyContent: "space-between",
    padding: "10px",
    borderBottom: "1px solid #000",
    marginBottom: "5px",
    width: "60rem",
  },
  crossButton: {
    marginLeft: "10px",
    borderRadius: "100rem",
    height: "3rem",
    fontSize: "1rem",
    fontWeight: "600",
    backgroundColor: "red",
  },
  addButton: {
    marginLeft: "10px",
    backgroundColor: "#1976d2",
    borderRadius: "100rem",
    height: "3rem",
    fontSize: "1rem",
    fontWeight: "600",
  },
  stockName: {
    width: "65%",
    marginLeft: "1rem",
  },
  pageTab: {
    marginLeft: "20rem",
    "& .MuiPaginationItem-root": {
      fontSize: "1.2rem",
      color: "#1976d2",
    },
  },
  tabsContainer: {
    position: "absolute",
    top: "5rem",
  },
  container: {
    border: "2px solid grey",
    marginLeft: "15rem",
    borderRadius: "2rem",
    height: "75vh",
  },
  nameHeader: {
    marginLeft: "1rem",
    width: "65%",
  },
});

interface DashboardProps {
  stocks: Istocks[];
}

const Dashboard: React.FC<DashboardProps> = ({ stocks }) => {
  const classes = useStyles();
  const itemsPerPage = 5;
  const [page, setPage] = useState(1);
  const [activeTab, setActiveTab] = useState("explore");
  const [watchlist, setWatchlist] = useState<Istocks[]>([]);
  const dispatch: AppDispatch = useDispatch();
  const handleChangePage = (
    event: React.ChangeEvent<unknown>,
    newPage: number
  ) => {
    setPage(newPage);
  };

  const handleTabChange = (event: React.SyntheticEvent, newValue: string) => {
    setActiveTab(newValue);
  };

  const addToWatchlist = (event: React.MouseEvent, stock: Istocks) => {
    event.preventDefault();
    if (!watchlist.includes(stock)) {
      setWatchlist([...watchlist, stock]);
    }
  };

  const removeFromWatchlist = (event: React.MouseEvent, stock: Istocks) => {
    event.preventDefault();
    const updatedWatchlist = watchlist.filter((item) => item !== stock);
    setWatchlist(updatedWatchlist);
  };

  const isInWatchlist = (stock: Istocks) => {
    return watchlist.includes(stock);
  };
  const handleStockSelect = (selectedStock: Istocks) => {
    console.log("dispatching selected stock", selectedStock);   
    dispatch(setStock(selectedStock));
  };

  const startIndex = (page - 1) * itemsPerPage;
  const endIndex = page * itemsPerPage;

  return (
    <div>
      <DrawerAppBar />
      <div>
        <div className={classes.tabsContainer}>
          <Tabs value={activeTab} onChange={handleTabChange}>
            <Tab value="explore" label="Explore" />
            <Tab value="watchlist" label="My WatchList" />
          </Tabs>
        </div>
        <div className={classes.container}>
          <div>
            {activeTab === "explore" && (
              <>
                <div className={classes.stockItem}>
                  <h3 className={classes.nameHeader}>Name</h3>
                  <h3>Base Price</h3>
                  <h3>Watchlist</h3>
                </div>
                {stocks.slice(startIndex, endIndex).map((stock, index) => (
                  <Link
                    key={index}
                    to={`/stock/${stock.stock_name}`}
                    onClick={() => handleStockSelect(stock)}
                  >
                    <div className={classes.stockItem}>
                      <p className={classes.stockName}>{stock.stock_name}</p>
                      <p>{stock.base_price}</p>
                      {isInWatchlist(stock) ? (
                        <button
                          className={classes.crossButton}
                          onClick={(event) => removeFromWatchlist(event, stock)}
                        >
                          x
                        </button>
                      ) : (
                        <button
                          className={classes.addButton}
                          onClick={(event) => addToWatchlist(event, stock)}
                        >
                          +
                        </button>
                      )}
                    </div>
                  </Link>
                ))}
              </>
            )}
            {activeTab === "watchlist" && (
              <>
                <div className={classes.stockItem}>
                  <h3 className={classes.nameHeader}>Name</h3>
                  <h3>Base Price</h3>
                  <h3>Watchlist</h3>
                </div>
                {watchlist.map((stock, index) => (
                  <Link key={index} to={`/stock/${stock.stock_name}`}>
                    <div className={classes.stockItem}>
                      <p className={classes.stockName}>{stock.stock_name}</p>
                      <p>{stock.base_price}</p>
                      <button
                        className={classes.crossButton}
                        onClick={(event) => removeFromWatchlist(event, stock)}
                      >
                        x
                      </button>
                    </div>
                  </Link>
                ))}
              </>
            )}
            <Pagination
              className={classes.pageTab}
              count={Math.ceil(
                activeTab === "explore"
                  ? stocks.length / itemsPerPage
                  : watchlist.length / itemsPerPage
              )}
              page={page}
              onChange={handleChangePage}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
