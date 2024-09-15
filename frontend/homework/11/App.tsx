import React, { useEffect, useState } from "react";
import "./App.scss";
import { APIQoute } from "./qoutes.types";
import { Qoute } from "./Qoute";

function App() {
  const [allQoutes, setAllQoutes] = useState<APIQoute[]>([]);
  const [qoutes, setQoutes] = useState<APIQoute[]>([]);
  const [filterItems, setFilterItems] = useState<string[]>([]);

  useEffect(() => {
    if (filterItems.length === 0) {
      setQoutes(allQoutes);
    } else {
      setQoutes(
        allQoutes.filter((qoute) =>
          filterItems.some((filter) => qoute.tags.includes(filter))
        )
      );
    }
  }, [filterItems, allQoutes]);

  useEffect(() => {
    fetch("https://api.quotable.io/quotes/random?limit=3")
      .then((response) => response.json())
      .then((data: APIQoute[]) => {
        setAllQoutes(data);
        console.log("data", data);
      });
  }, []);

  const fetchNewQuotes = () => {
    fetch("https://api.quotable.io/quotes/random")
      .then((response) => response.json())
      .then((data: APIQoute[]) => {
        setAllQoutes((prevQuotes) => [...data, ...prevQuotes]);
        console.log("New quotes fetched", data);
      });
  };

  const removeFilterItem = (itemToRemove: string) => {
    setFilterItems((prevItems) =>
      prevItems.filter((item) => item !== itemToRemove)
    );
  };

  return (
    <div className="app">
      <div className="btn">
        <button id="qoute-btn" onClick={fetchNewQuotes}>
          NEW QOUTE
        </button>
      </div>
      <div className="filter">
        <h2>Filters:</h2>
        <ul>
          {filterItems.map((item, index) => (
            <li className="filterTags" key={index}>
              {item}
              <button id="cross" onClick={() => removeFilterItem(item)}>
                x
              </button>
            </li>
          ))}
        </ul>
      </div>
      {qoutes.map((qoute) => (
        <Qoute
          key={qoute._id}
          qoute={qoute}
          filterItems={filterItems}
          setFilterItems={setFilterItems}
        />
      ))}
    </div>
  );
}

export default App;
