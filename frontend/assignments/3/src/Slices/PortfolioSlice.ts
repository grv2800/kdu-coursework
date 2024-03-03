import { createSlice } from "@reduxjs/toolkit";
import { Itransactions } from "../config/interface";
import { getTransactions } from "../thunk/getTransactions";

interface IProductList {
  transactions: Itransactions[];
  state: "fulfilled" | "pending" | "error";
  error: string;
}

const initialState: IProductList = {
  transactions: [],
  state: "fulfilled",
  error: "",
};

export const PortfolioSlice = createSlice({
  name: "transactions",
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(getTransactions.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getTransactions.fulfilled, (state, action) => {
        state.transactions = action.payload;
        state.state = "fulfilled";
      })
      .addCase(getTransactions.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});

export default PortfolioSlice.reducer;
