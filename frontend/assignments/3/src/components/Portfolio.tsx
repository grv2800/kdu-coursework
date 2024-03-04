import React, { useState, useEffect } from "react";
import { DrawerAppBar } from "./navbar";
import { Itransactions } from "../config/interface";
import { createUseStyles } from "react-jss";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../store/store";
import { setLoading } from "../Slices/LoaderSlice";
import { Loader } from "./Loader";
import { getTransactions } from "../thunk/getTransactions";
import { getUserTransactions } from "../thunk/getUserTransactions";

const useStyles = createUseStyles({
  dot: {
    width: "10px",
    height: "10px",
    borderRadius: "50%",
    display: "flex",
    alignItems: "center",
    marginLeft: "5px",
  },
  green: {
    backgroundColor: "green",
  },
  red: {
    backgroundColor: "red",
  },
  transactionGroup: {
    marginTop: "2rem",
    padding: "10px",
    marginLeft: "30rem",
    width: "60rem",
  },
  filterSection: {
    position: "fixed",
    display: "flex",
    flexDirection: "column",
    flexWrap: "wrap",
    padding: "1rem",
    border: "2px solid #ccc",
    borderRadius: "3rem",
    backgroundColor: "#e9ecef",
    height: "72%",
    width: "27%",
    marginLeft: "1.2rem",
  },
  filterHeader: {
    display: "flex",
    justifyContent: "space-between",
  },
  clearButton: {
    height: "3rem",
    width: "10rem",
    background: "none",
    color: "#1976d2",
    fontWeight: "600",
    marginTop: ".5rem",
  },
  customizedHr: {
    backgroundColor: "#000",
    width: "100%",
  },
  searchField: {
    backgroundColor: "#e9ecef",
    border: "2px solid #000",
    borderRadius: "0.4rem",
    height: "2rem",
    fontSize: "1rem",
    color: "#000",
    margin:".4rem 0"
  },
  filterDate: {
    marginTop: "1rem",
    border: "1px solid #ccc",
    padding: "0.5rem",
    display: "flex",
    flexDirection: "column",
    width: "48%",
    borderRadius: "1rem",
   
  },
  datePicker: {
    display: "flex",
    justifyContent: "space-between",
    width: "100%",
    margin:"0.4rem 0"
  },
  noTransactions: {
    marginLeft: "50rem",
    marginTop: "25rem",
    border:"2px solid #ccc",
    position:"relative",
    fontSize:"2rem"
  },
  checkboxFilters: {
    display: "flex",
    flexDirection: "column",
  },
  startDate: {
    backgroundColor: "#e9ecef",
    color: "#000",
    border: "2px solid #000",
    borderRadius: ".4rem",
  },
  endDate: {
    backgroundColor: "#e9ecef",
    color: "#000",
    border: "2px solid #000",
    borderRadius: ".4rem",
  },
  transactionDetails: {
    display: "flex",
    justifyContent: "space-between",
    width: "100%",
    marginLeft: "10px",
    marginTop: "2rem",
  },
  stockName: {
    width: "35%",
  },
  stockPrice: {
    width: "30%",
    marginLeft: "6rem",
  },
  dottedLine: {
    width: "100%",
    height: "0",
    borderTop: "5px dotted #ccc",
    margin: "10px 0",
  },
  stockNameCheckboxes: {
    display:"flex",
    flexDirection:"column",
    maxHeight: "120px",
    overflowY: "auto",
    marginTop: "1rem",
    paddingRight: "0.5rem", 
  },

  stockNameCheckbox: {
    marginBottom: "0.5rem", 
  },
});

export function Portfolio() {
  const transactions = useSelector(
    (state: RootState) => state.portfolio.transactions
  );
  const transactionsDispatch: AppDispatch = useDispatch();
  useEffect(() => {
    transactionsDispatch(getTransactions());
    transactionsDispatch(getUserTransactions());
  }, [transactionsDispatch]);

  const classes = useStyles();
  const dispatch: AppDispatch = useDispatch();
  const loader = useSelector((state: RootState) => state.loader.isLoading);
  const [filter, setFilter] = useState<string>("");
  const [startDate, setStartDate] = useState<string>("");
  const [endDate, setEndDate] = useState<string>("");
  const [showPassed, setShowPassed] = useState<boolean>(true);
  const [showFailed, setShowFailed] = useState<boolean>(true);
  const [filteredTransactions, setFilteredTransactions] =
    useState<Itransactions[]>(transactions);

  const uniqueStockNames = Array.from(
    new Set(transactions.map((transaction) => transaction.stock_name))
  );

  const [stockNameFontWeights, setStockNameFontWeights] = useState<
    Record<string, string>
  >({});

  useEffect(() => {
    sortTransactions(transactions);
  }, [transactions]);

  const sortTransactions = (transactions: Itransactions[]) => {
    const sorted = [...transactions].sort((a, b) => {
      return new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime();
    });
    setFilteredTransactions(sorted);
  };

  const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setFilter(event.target.value);
    filterTransactions(
      event.target.value,
      startDate,
      endDate,
      showPassed,
      showFailed
    );
  };

  const handleStartDateChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setStartDate(event.target.value);
    filterTransactions(
      filter,
      event.target.value,
      endDate,
      showPassed,
      showFailed
    );
  };

  const handleEndDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEndDate(event.target.value);
    filterTransactions(
      filter,
      startDate,
      event.target.value,
      showPassed,
      showFailed
    );
  };

  const handlePassedCheckboxChange = () => {
    setShowPassed(!showPassed);
    filterTransactions(filter, startDate, endDate, !showPassed, showFailed);
  };

  const handleFailedCheckboxChange = () => {
    setShowFailed(!showFailed);
    filterTransactions(filter, startDate, endDate, showPassed, !showFailed);
  };

  const handleClearFilters = () => {
    setFilter("");
    setStartDate("");
    setEndDate("");
    setShowPassed(true);
    setShowFailed(true);
    setFilteredTransactions(transactions);
  };

  const filterTransactions = (
    filterText: string,
    start: string,
    end: string,
    passed: boolean,
    failed: boolean
  ) => {
    dispatch(setLoading(true));
    let filtered = transactions.filter((transaction) =>
      transaction.stock_name.toLowerCase().includes(filterText.toLowerCase())
    );

    if (start) {
      filtered = filtered.filter(
        (transaction) => new Date(transaction.timestamp) >= new Date(start)
      );
    }

    if (end) {
      filtered = filtered.filter(
        (transaction) => new Date(transaction.timestamp) <= new Date(end)
      );
    }

    if (!passed) {
      filtered = filtered.filter(
        (transaction) => transaction.status !== "Passed"
      );
    }
    if (!failed) {
      filtered = filtered.filter(
        (transaction) => transaction.status !== "Failed"
      );
    }

    sortTransactions(filtered);
    dispatch(setLoading(false));
  };

  const handleStockNameCheckboxChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { value, checked } = event.target;
    setStockNameFontWeights((prevFontWeights) => ({
      ...prevFontWeights,
      [value]: checked ? "800" : "normal",
    }));
  };

  const groupedTransactions: { [date: string]: Itransactions[] } = {};
  filteredTransactions.forEach((transaction) => {
    const date = transaction.timestamp.split("T")[0];
    if (!groupedTransactions[date]) {
      groupedTransactions[date] = [];
    }
    groupedTransactions[date].push(transaction);
  });

  for (const date in groupedTransactions) {
    groupedTransactions[date].sort((a, b) => {
      return new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime();
    });
  }

  let transactionsContent;
  if (loader) {
    transactionsContent = <Loader />;
  } else if (Object.keys(groupedTransactions).length === 0) {
    transactionsContent = (
      <div className={classes.noTransactions}>No transactions available</div>
    );
  } else {
    transactionsContent = Object.keys(groupedTransactions).map(
      (date, index) => (
        <div className={classes.transactionGroup} key={index}>
          <h3>{date}</h3>
          <div className={classes.dottedLine}></div>
          {groupedTransactions[date].map((transaction, index) => (
            <div
              key={index}
              style={{ fontWeight: stockNameFontWeights[transaction.stock_name] || 'normal' }}
            >
              <div className={classes.transactionDetails}>
                <p className={classes.stockName}>{transaction.stock_name}</p>
                <p>{transaction.stock_symbol}</p>
                <p className={classes.stockPrice}>
                  {transaction.transaction_price}
                </p>
                {new Date(transaction.timestamp).toLocaleTimeString()}
                {transaction.status === "Passed" && (
                  <div className={`${classes.dot} ${classes.green}`}></div>
                )}
                {transaction.status === "Failed" && (
                  <div className={`${classes.dot} ${classes.red}`}></div>
                )}
              </div>
              <hr />
            </div>
          ))}
        </div>
      )
    );
  }

  return (
    <div className="portfolio-container">
      <DrawerAppBar />
      <div className={classes.filterSection}>
        <div className={classes.filterHeader}>
          <h3>Filters :</h3>
          <button className={classes.clearButton} onClick={handleClearFilters}>
            Clear All
          </button>
        </div>
        <hr className={classes.customizedHr} />
        <input
          type="text"
          placeholder="Search for a Stock"
          value={filter}
          onChange={handleFilterChange}
          className={classes.searchField}
        />
        <hr className={classes.customizedHr} />
        <div className={classes.datePicker}>
          <div className={classes.filterDate}>
            <label htmlFor="startDate">Start Date:</label>
            <input
              type="date"
              value={startDate}
              id="startDate"
              className={classes.startDate}
              onChange={handleStartDateChange}
            />
          </div>
          <div className={classes.filterDate}>
            <label htmlFor="endDate">End Date:</label>
            <input
              type="date"
              value={endDate}
              id="endDate"
              className={classes.endDate}
              onChange={handleEndDateChange}
            />
          </div>
        </div>
        <hr className={classes.customizedHr} />
        <div className={classes.checkboxFilters}>
          <label>
            <input
              type="checkbox"
              checked={showPassed}
              onChange={handlePassedCheckboxChange}
            />
            Show Passed
          </label>
          <label>
            <input
              type="checkbox"
              checked={showFailed}
              onChange={handleFailedCheckboxChange}
            />
            Show Failed
          </label>
        </div>
        <hr className={classes.customizedHr} />
        <div className={classes.stockNameCheckboxes}>
          {uniqueStockNames.map((stockName) => (
            <label key={stockName} className={classes.stockNameCheckbox}>
              <input
                type="checkbox"
                value={stockName}
                onChange={handleStockNameCheckboxChange}
              />
              {stockName}
            </label>
          ))}
        </div>
      </div>
      <div className="transaction-list">{transactionsContent}</div>
    </div>
  );
}
