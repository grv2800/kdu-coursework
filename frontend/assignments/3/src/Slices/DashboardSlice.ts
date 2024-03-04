import { createSlice } from "@reduxjs/toolkit";
import { Istocks } from "../config/interface";
import { getStocks } from "../thunk/getStocks";

interface IProductList {
  stocks: Istocks[];
  stock: Istocks;
  state: "fulfilled" | "pending" | "error";
  error: string;
}
const initialStock: Istocks = {
  stock_name: "",
  stock_symbol: "",
  base_price: "0"
};
const initialState: IProductList = {
  stocks: [],
  stock: initialStock,
  state: "fulfilled",
  error: "",
};
export const DashboardSlice = createSlice({
  name: "stocks",
  initialState,
  reducers: {
    setStock(state, action) {
      state.stock = action.payload;
    },
  },
  extraReducers(builder) {
    builder
      .addCase(getStocks.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getStocks.fulfilled, (state, action) => {
        state.stocks = action.payload;
        state.state = "fulfilled";
      })
      .addCase(getStocks.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});
export const {setStock} = DashboardSlice.actions;
export default DashboardSlice.reducer;
