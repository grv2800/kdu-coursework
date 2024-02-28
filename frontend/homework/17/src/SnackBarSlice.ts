import { createSlice } from "@reduxjs/toolkit";
import { getProducts } from "./thunk/getProducts";

interface SnackbarState {
  message: string;
  type: "success" | "error";
}

const initialState: SnackbarState = {
  message: "succcessful loading",
  type: "success",
};

export const snackbarSlice = createSlice({
  name: "snackbar",
  initialState,
  reducers: {
  },
  extraReducers(builder) {
    builder
      .addCase(getProducts.fulfilled, (state, action) => {
        state.message = action.payload;
        state.type = "success";
      })
      .addCase(getProducts.rejected, (state, action) => {
        state.message = action.payload as string;
        state.type = "error";
      });
    },
});

export default snackbarSlice.reducer;
