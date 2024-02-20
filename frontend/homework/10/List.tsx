import React, { useState } from "react";
import "./List.css";
import { Header } from "./Header";
import { ListItem } from "./ListItem";

export interface IlistItems {
  id: number;
  text: string;
}
export function List() {
  const [listItems, setListItems] = useState<IlistItems[]>([
    { id: 1, text: "alice" },
    { id: 2, text: "bob" },
  ]);
  const [inputText, setInputText] = useState("");
  const [searchInput, setSearchInput] = useState("");

  function submitText() {
    const newID =
      listItems.length > 0 ? listItems[listItems.length - 1].id + 1 : 1;
    const newItem: IlistItems = {
      id: newID,
      text: inputText,
    };
    setListItems([...listItems, newItem]);
    setInputText("");
  }

  const filteredItems = listItems.filter((item) =>
    item.text.toLowerCase().includes(searchInput.toLowerCase())
  );

  if (filteredItems.length === 0 && searchInput !== "") {
    alert("No items found");
  }

  return (
    <div>
      <Header searchInput={searchInput} setSearchInput={setSearchInput} />
      <div className="list">
        <div className="form">
          <label htmlFor="item">
            <h2>Add items</h2>
          </label>
          <input
            type="text"
            name="item"
            id="itemInput"
            value={inputText}
            onChange={(event) => setInputText(event.target.value)}
          />
          <button onClick={submitText}>Submit</button>
        </div>
        <h2>Items</h2>
        <ul>
          {filteredItems.length > 0
            ? filteredItems.map((item) => <ListItem key={item.id} text={item.text} /> )
            : listItems.map((item) => <ListItem key={item.id} text={item.text} />)}
        </ul>
      </div>
    </div>
  );
}
