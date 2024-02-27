import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface ListItem {
  id: number;
  text: string;
  complete: boolean;
}

interface ListState {
  listItems: ListItem[];
}

const initialState: ListState = {
  listItems: [],
};

export const todoSlice = createSlice({
  name: "todo",
  initialState,
  reducers: {
    addItem: (state, action: PayloadAction<ListItem>) => {
      state.listItems.push(action.payload);
    },
    deleteItem: (state, action: PayloadAction<number>) => {
      state.listItems = state.listItems.filter(
        (item) => item.id !== action.payload
      );
    },
    toggleCompleted: (state, action: PayloadAction<number>) => {
      const item = state.listItems.find((item) => item.id === action.payload);
      if (item) {
        item.complete = !item.complete;
      }
    },
    clearCompleted: (state) => {
      state.listItems = state.listItems.filter((item) => !item.complete);
    },
  },
});

export const { addItem, deleteItem, toggleCompleted, clearCompleted } =
  todoSlice.actions;
export default todoSlice.reducer;
