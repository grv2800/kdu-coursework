import React from "react";
import { APIQoute } from "./qoutes.types";
import "./Qoute.scss";
interface QouteProps {
  qoute: APIQoute;
  filterItems: string[];
  setFilterItems: React.Dispatch<React.SetStateAction<string[]>>;
}
export function Qoute({
  qoute,
  filterItems,
  setFilterItems,
}: Readonly<QouteProps>) {
  const handleTagClick = (tag: string) => {
    if (!filterItems.includes(tag)) {
      console.log("adding filteritems", tag);
      setFilterItems((prevItems) => [...prevItems, tag]);
    }
  };
  return (
    <div>
      <div className="container">
        <h1>{qoute.content}</h1>

        <div className="temp">
          <p id="author">~{qoute.author}</p>
          <p>{qoute.dateAdded}</p>
        </div>

        <div>
          <ul>
            {qoute.tags.map((q) => {
              return (
                <li className="tags" key={q} onClick={() => handleTagClick(q)}>
                  {q}
                </li>
              );
            })}
          </ul>
        </div>
      </div>
    </div>
  );
}
