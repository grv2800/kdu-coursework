import { useSelector } from "react-redux";
import { RootState } from "./store";
import {  createUseStyles } from 'react-jss';

export const useStyles = createUseStyles({
  snackbar: {
    position: 'fixed',
    bottom: '0px',
    left: '50%',
    transform: 'translateX(-50%)',
    padding: '10px 20px',
    borderRadius: '4px',
    fontSize: '16px',
    color: '#fff',
  },
  success: {
    backgroundColor: '#4caf50', 
  },
  error: {
    backgroundColor: '#f44336', 
  },
});

export function Snackbar() {
  const classes=useStyles();
  const snackbarMessage = useSelector(
    (state: RootState) => state.snackbar.message
  );
  const snackbarType = useSelector(
    (state: RootState) => state.snackbar.type
  );

  const snackbarClass = `${classes.snackbar} ${snackbarType === "success" ? classes.success : classes.error}`;

  return (
  <div>
    <div className={snackbarClass}>{snackbarMessage}</div>
    </div>);
}
