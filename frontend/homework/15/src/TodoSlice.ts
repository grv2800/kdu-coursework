import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface ListItems {
  id: number;
  text: string;
}
interface ListState {
  listItems: ListItems[];
}
const initialState: ListState = {
  listItems: [
    { id: 1, text: "alice" },
    { id: 2, text: "bob" },
  ],
};
export const listSlice = createSlice({
  name: "list",
  initialState,
  reducers: {
    addItem: (state, action: PayloadAction<ListItems>) => {
      state.listItems.push(action.payload);
    },
  },
});

export const {addItem}=listSlice.actions;
export default listSlice.reducer;