import { createAsyncThunk } from "@reduxjs/toolkit";
export const getUserTransactions = createAsyncThunk("getUserTransactions", async () => {
  try {
    const response = await fetch("http://localhost:3001/api/transactions");
    const data = await response.json();
    return data;
  } catch {
    return "error while making api call";
  }
});
