import { createSlice } from "@reduxjs/toolkit";
import { RoomType } from "./interface";
import { getRooms } from "./Thunk/getRooms";

interface IRoomList {
  rooms: RoomType[];
  state: "fulfilled" | "pending" | "error";
  error: string;
}
const initialState: IRoomList = {
  rooms: [],
  state: "fulfilled",
  error: "",
};
export const RoomSlice = createSlice({
  name: "rooms",
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(getRooms.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getRooms.fulfilled, (state, action) => {
        state.rooms = action.payload;
        state.state = "fulfilled";
      })
      .addCase(getRooms.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});
export default RoomSlice.reducer;
