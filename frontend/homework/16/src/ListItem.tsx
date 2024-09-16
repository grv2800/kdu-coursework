import { createUseStyles } from "react-jss";
import cross from "./cross.png";

interface ListItemProps {
  text: string;
  completed: boolean;
  onDelete: () => void;
  onToggleCompleted: () => void; 
}

const useStyles = createUseStyles({
  listItem: {
    border: "2px solid #000",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "15px",
    margin: "15px",
    textDecoration: "none",
  },
  listItemCompleted: {
    textDecoration: "line-through",
  },
  listItemImg: {
    height: "20px",
    width: "20px",
    cursor: "pointer",
  },
});

export function ListItem({
  text,
  completed,
  onDelete,
  onToggleCompleted,
}: ListItemProps) {
  const classes = useStyles();

  return (
    <li
      className={`${classes.listItem} ${
        completed ? classes.listItemCompleted : ""
      }`}
    >
      <div>
        <input
          type="checkbox"
          checked={completed}
          onChange={onToggleCompleted}
        />
        <label>{text}</label>
      </div>
      <img
       data-testid="delete-button"
        src={cross}
        alt=""
        className={classes.listItemImg}
        onClick={onDelete}
      />
    </li>
  );
}
