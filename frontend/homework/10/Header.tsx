import React, { useState } from "react";
import "./Header.css";

interface HeaderProps {
  searchInput: string;
  setSearchInput: React.Dispatch<React.SetStateAction<string>>;
}

export function Header({ searchInput, setSearchInput }: Readonly<HeaderProps>) {
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
