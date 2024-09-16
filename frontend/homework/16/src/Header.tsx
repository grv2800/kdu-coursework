import React, { useState } from "react";
import { createUseStyles } from "react-jss";

interface HeaderProps {
  searchInput: string;
  setSearchInput: React.Dispatch<React.SetStateAction<string>>;
}

const useStyles = createUseStyles({
  header: {
    backgroundColor: "#28a745",
    color: "white",
    display: "flex",
    fontFamily: "Lato",
    justifyContent: "space-around",
    alignItems:'center',
    fontSize: "100px",
  },
  h1: {
    fontSize: "40px",
  },
  input: {
    height: "30px",
    width: "450px",
  },
  searchField:{
    marginBottom:"2rem"
  }
});

export function Header({ searchInput, setSearchInput }: Readonly<HeaderProps>) {
  const classes = useStyles();
  const [inputValue, setInputValue] = useState("");

  const handleButtonClick = () => {
    setSearchInput(inputValue);
  };

  return (
    <div className={classes.header}>
      <h1 className={classes.h1}>Item Lister</h1>
      <div className={classes.searchField}>
          <input
            type="text"
            placeholder="Search Items.."
            className={classes.input}
            onChange={(e) => setInputValue(e.target.value)}
          />
        <button onClick={handleButtonClick}>Search</button>
      </div>
    </div>
  );
}
