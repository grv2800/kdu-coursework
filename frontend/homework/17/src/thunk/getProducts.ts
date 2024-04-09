import { createAsyncThunk } from "@reduxjs/toolkit";
export const getProducts = createAsyncThunk("getProducts", async () => {
  try {
    const response = await fetch("https://fakestoreapi.com/products");
    const data = await response.json();
    return data;
  } catch {
    return "error while making api call";
  }
});
