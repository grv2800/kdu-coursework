import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "./store";
import { addItem } from "./TodoSlice";
import { createUseStyles } from 'react-jss';
import { Header } from "./Header";
import { ListItem } from "./ListItem";

interface ListItems {
  id: number;
  text: string;
}

const useStyles = createUseStyles({
  list: {
    margin: '10px',
    border: '1px solid #000',
  },
  listTitle: {
    fontSize: '32px',
    margin: '20px',
  },
  form: {
    marginLeft: '10px',
  },
  formTitle: {
    fontSize: '32px',
    marginBottom: '10px',
  },
  itemInput: {
    height: '30px',
  },
  submitButton: {
    marginLeft: '10px',
    color: '#fff',
    backgroundColor: '#343a40',
    border: '2px solid #343a40',
    borderRadius: '5px',
    height: '40px',
  },
  listItem: {
    border: '2px solid #000',
    display: 'flex',
    justifyContent: 'space-between',
    listStyleType: 'none',
    padding: '15px',
    marginRight: '15px',
  },
  listItemImg: {
    height: '30px',
    width: '30px',
  }
});

export function List() {
  const dispatch = useDispatch();
  const listItems = useSelector((state: RootState) => state.list.listItems);
  const [inputText, setInputText] = useState("");
  const [searchInput, setSearchInput] = useState("");
  const classes = useStyles();

  function submitText() {
    const newID =
      listItems.length > 0 ? listItems[listItems.length - 1].id + 1 : 1;
    const newItem: ListItems = {
      id: newID,
      text: inputText,
    };
    dispatch(addItem(newItem));
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
      <div className={classes.list}>
        <div className={classes.form}>
          <label htmlFor="item">
            <h2 className={classes.formTitle}>Add items</h2>
          </label>
          <input
            type="text"
            name="item"
            id="itemInput"
            value={inputText}
            className={classes.itemInput}
            onChange={(event) => setInputText(event.target.value)}
          />
          <button className={classes.submitButton} onClick={submitText}>Submit</button>
        </div>
        <h2 className={classes.listTitle}>Items</h2>
        <ul>
          {filteredItems.length > 0
            ? filteredItems.map((item) => <ListItem key={item.id} text={item.text} /> )
            : listItems.map((item) => <ListItem key={item.id} text={item.text} />)}
        </ul>
      </div>
    </div>
  );
}
