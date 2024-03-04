import { configureStore } from "@reduxjs/toolkit";
import DashboardReducer from "../Slices/DashboardSlice";
import LoaderReducer from "../Slices/LoaderSlice";
import PortfolioReducer from "../Slices/PortfolioSlice";

export const store=configureStore({
    reducer:{
        stocks:DashboardReducer,
        loader: LoaderReducer,
        portfolio:PortfolioReducer,
    }
})
export type RootState=ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;