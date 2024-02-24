import React, { createContext, useContext, useState } from 'react'
import { List } from './List'
export interface IlistItems {
  id: number;
  text: string;
}
interface IlistContext{
  listItems:IlistItems[],
  setListItems:React.Dispatch<React.SetStateAction<IlistItems[]>>,
  searchInput:string,
  setSearchInput:React.Dispatch<React.SetStateAction<string>>
}
export const ListContext =createContext<IlistContext>({
  listItems:[],
  setListItems:()=>{},
  searchInput:"",
  setSearchInput:()=>{}
})

interface ListProviderProps{
  children:React.ReactNode;
}
const ListContextProvider=({children}:ListProviderProps)=>{
  const [listItems, setListItems] = useState<IlistItems[]>([
    { id: 1, text: "assignment" },
    { id: 2, text: "udemy-videos" },
  ]);
  const [searchInput, setSearchInput] = useState("");

  return(
    <ListContext.Provider value={{listItems,setListItems,searchInput,setSearchInput}}>
      {children}
    </ListContext.Provider>
  );
};
export function TodoList() {
  return (
    <ListContextProvider>
    <List/>
  </ListContextProvider>
  )
}
