import { BrowserRouter, Route, Routes } from "react-router-dom";
import {Summarizer} from './components/Summarizer';
import {Portfolio} from './components/Portfolio';
import  Dashboard  from "./components/Dashboard";
import { useDispatch, useSelector } from "react-redux";
import { setLoading } from "./Slices/LoaderSlice";
import { getStocks } from "./thunk/getStocks";
import { AppDispatch, RootState } from "./store/store";
import { useEffect } from "react";
import { Loader } from "./components/Loader";
import StockPage from "./components/StockPage";

function App() {
  const stocks = useSelector((state: RootState) => state.stocks.stocks);
  const loader = useSelector((state: RootState) => state.loader.isLoading);
  const dispatch: AppDispatch = useDispatch();
 
  useEffect(() => {
    dispatch(setLoading(true));
    dispatch(getStocks()).then(() => {
      dispatch(setLoading(false));
    });
  }, [dispatch]);
  console.log(stocks);
  return (
    loader ? (
      <Loader />
    ) : (
    <BrowserRouter>
         <Routes>
         <Route path="/" element={<Dashboard stocks={stocks}/>} />
         <Route path="/summarizer" element={<Summarizer />} />
         <Route path="/portfolio" element={ <Portfolio />} />
         <Route path="/stock/:stock_name" element={<StockPage/>} />
         </Routes>
    </BrowserRouter>
    )
  );
}
export default App;
