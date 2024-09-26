import React, { useContext, useState } from "react";
import "./Header.css";
import { ListContext } from "./TodoList";

export function Header() {
  const{setSearchInput}=useContext(ListContext);
  const [inputValue, setInputValue] = useState("");
  const handleButtonClick = () => {
    setSearchInput(inputValue);
  };
  return (
    <div className="header">
      <h1>Item Lister</h1>
      <div>
        <input
          type="text"
          placeholder="Search Items.."
          id="input"
          onChange={(e) => setInputValue(e.target.value)}
        />
        <button onClick={handleButtonClick}> Search</button>
      </div>
    </div>
  );
}
