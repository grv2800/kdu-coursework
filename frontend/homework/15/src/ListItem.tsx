import { createUseStyles } from 'react-jss';

interface ListItemProps {
  text: string;
}

const useStyles = createUseStyles({
  listItem: {
    border: '2px solid #000',
    display: 'flex',
    listStyleType: 'none',
    padding: '15px',
    marginRight: '15px',
  },
  listItemImg: {
    height: '10px',
    width: '10px',
  }
});

export function ListItem({ text }: ListItemProps) {
  const classes = useStyles();

  return (
    <div className={classes.listItem}>
      <li>{text}</li>
      <img src="cross.png" alt="" className={classes.listItemImg} />
    </div>
  );
}
