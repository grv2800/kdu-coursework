import React, { useEffect } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ProductList from "./ProductList";
import { ProductProvider } from "./ProductContext";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "./store";
import { setLoading } from "./LoaderSlice";
import { getProducts } from "./thunk/getProducts";
import { Loader } from "./Loader";
import { Snackbar } from "./SnackBar";


const App: React.FC = () => {
  const products = useSelector((state: RootState) => state.products.products);
  const loader = useSelector((state: RootState) => state.loader.isLoading);
  const dispatch: AppDispatch = useDispatch();
  useEffect(() => {
    dispatch(setLoading(true));
    dispatch(getProducts()).then(() => {
      dispatch(setLoading(false));
    });
  }, [dispatch]);
  
  return (
    <ProductProvider>
      {loader ? (
        <Loader />
      ) : (
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<ProductList products={products} />} />
          </Routes>
        </BrowserRouter>
      )}
      <Snackbar />
    </ProductProvider>
  );
};

export default App;
