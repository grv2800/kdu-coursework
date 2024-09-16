import { Tuple, configureStore } from "@reduxjs/toolkit";
import listReducer  from "./TodoSlice";
import storage from 'redux-persist/lib/storage';
import { persistReducer, persistStore } from 'redux-persist';
import {thunk} from 'redux-thunk';
        
const persistConfig = {
    key: 'root',
    storage,
  }
  
const persistedReducer = persistReducer(persistConfig, listReducer);

export const store=configureStore({
    reducer:{
        list:persistedReducer
    },
    middleware: () => new Tuple(thunk),
})
export const persistor = persistStore(store);
export type RootState=ReturnType<typeof store.getState>