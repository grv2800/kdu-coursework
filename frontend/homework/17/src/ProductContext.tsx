import React, { createContext, useState } from "react";
import { IProducts } from "./types"; 
interface IProductContext {
  selectedProduct: IProducts | null;
  setSelectedProduct: React.Dispatch<React.SetStateAction<IProducts>>;
}

export const ProductContext = createContext<IProductContext>({
  selectedProduct: null,
  setSelectedProduct: () => {},
});
export interface ProductProviderProps {
  children: React.ReactNode;
}
export function ProductProvider({ children }: Readonly<ProductProviderProps>) {
  const [selectedProduct, setSelectedProduct] = useState<IProducts | null>(
    null
  );
  return (
    <ProductContext.Provider value={{ selectedProduct, setSelectedProduct }}>
      {children}
    </ProductContext.Provider>
  );
}
