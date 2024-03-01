import { useEffect } from "react"
import { AppDispatch, RootState } from "./store";
import { useDispatch, useSelector } from "react-redux";
import { getRooms } from "./Thunk/getRooms";
import './App.css'
import  {Room}  from "./Room";

function App() {
  const rooms = useSelector((state: RootState) => state.rooms.rooms);
  const dispatch: AppDispatch = useDispatch();
  useEffect(() => {
    dispatch(getRooms());
  }, [dispatch]);
  return (
    <>
    <Room rooms={rooms}/>
    </>
  )
}

export default App
