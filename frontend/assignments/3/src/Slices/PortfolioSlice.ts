import { createSlice } from "@reduxjs/toolkit";
import { Itransactions } from "../config/interface";
import { getTransactions } from "../thunk/getTransactions";
import { getUserTransactions } from "../thunk/getUserTransactions";

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
      })
      .addCase(getUserTransactions.fulfilled, (state, action) => {
        state.transactions.push(...action.payload);
        state.state = "fulfilled";
      })
      .addCase(getUserTransactions.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});

export default PortfolioSlice.reducer;
