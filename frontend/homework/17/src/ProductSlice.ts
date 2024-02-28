import { createSlice } from "@reduxjs/toolkit";
import { IProducts } from "./types";
import { getProducts } from "./thunk/getProducts";

interface IProductList {
  products: IProducts[];
  state: "fulfilled" | "pending" | "error";
  error: string;
}
const initialState: IProductList = {
  products: [],
  state: "fulfilled",
  error: "",
};
export const ProductSlice = createSlice({
  name: "products",
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(getProducts.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getProducts.fulfilled, (state, action) => {
        state.products = action.payload;
        state.state = "fulfilled";
      })
      .addCase(getProducts.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});
export default ProductSlice.reducer;
