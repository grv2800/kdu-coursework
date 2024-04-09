import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { RootState } from './store';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';

export function CustomSnackbar() {
  const [open, setOpen] = useState(false);
  const snackbarMessage = useSelector((state: RootState) => state.snackbar.message);
  const snackbarType = useSelector((state: RootState) => state.snackbar.type);

  useEffect(() => {
    if (snackbarMessage) {
      setOpen(true);
    }
  }, [snackbarMessage]);

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Snackbar
      open={open}
      autoHideDuration={3000}
      onClose={handleClose}
      anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
    >
      <MuiAlert
        elevation={6}
        variant="filled"
        severity={snackbarType === 'success' ? 'success' : 'error'}
        onClose={handleClose}
      >
        {snackbarMessage}
      </MuiAlert>
    </Snackbar>
  );
}
