import { createAsyncThunk } from "@reduxjs/toolkit";
export const getRooms = createAsyncThunk("getRooms", async () => {
  try {
    const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json");
    const data = await response.json();
    return data.roomTypes;
  } catch {
    return "error while making api call";
  }
});
