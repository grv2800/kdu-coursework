import React, { useContext, useState } from "react";
import "./List.css";
import { Header } from "./Header";
import Cross from "./cross.png";
import { IlistItems, ListContext } from "./TodoList";


export function List() {
  const {listItems,setListItems,searchInput}=useContext(ListContext);
  const [inputText, setInputText] = useState("");
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
      <Header />
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
          {/* <div className="list-div"> */}
            {filteredItems.length > 0
              ? filteredItems.map((item) => (
                  <li className="list-item" key={item.id}>
                    {" "}
                    {item.text} <img src={Cross} alt="" />
                  </li>
                ))
              : listItems.map((item) => <li className="list-item" key={item.id} >{item.text} <img src={Cross} alt="" /></li>)}
          {/* </div> */}
        </ul>
      </div>
    </div>
  );
}
