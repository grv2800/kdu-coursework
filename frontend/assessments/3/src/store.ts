import { configureStore } from "@reduxjs/toolkit";
import RoomReducer from "./RoomSlice";
export const store=configureStore({
    reducer:{
        rooms:RoomReducer,
    }
})
export type RootState=ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;