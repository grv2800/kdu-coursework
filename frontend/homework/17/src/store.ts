import { configureStore } from "@reduxjs/toolkit";
import ProductsReducer from "./ProductSlice";
import snackbarReducer from "./SnackBarSlice";
import loaderReducer from "./LoaderSlice";

export const store=configureStore({
    reducer:{
        products:ProductsReducer,
        loader: loaderReducer,
        snackbar: snackbarReducer,
    }
})
export type RootState=ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;