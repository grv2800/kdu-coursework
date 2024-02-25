import { useContext } from "react";
import { ProductContext } from "./ProductContext";
import { Link } from "react-router-dom";
import { useStyles } from "./ProductDetailStyles";

export default function ProductDetail() {
  const classes = useStyles();
  const { selectedProduct } = useContext(ProductContext);

  if (!selectedProduct) return <div>Select a product!</div>;

  return (
    <div >
      <h2 className={classes.productName}>{selectedProduct.title}</h2>
      <div className={classes.mainContainer}>
        <img className={classes.productImage} src={selectedProduct.image} alt={selectedProduct.title} />
        <div className={classes.productDetails}>
          <p className={classes.productPrice}><strong>Price:</strong>  ${selectedProduct.price}</p>
          <p><strong>Product Description:</strong></p>
          <p>{selectedProduct.description}</p>
          <p><strong>Category:  </strong> {selectedProduct.category}</p>
          <p className={classes.productRating}><strong>Rating:</strong> {selectedProduct.rating.rate}</p>
         <span><Link to="/" className={classes.link}>Back to Products</Link></span> 
        </div>
      </div>
    </div>
  );
}
