import { useState, useEffect, useContext, useRef } from "react";
import { Link } from "react-router-dom";
import { ProductContext } from "./ProductContext";
import { IProducts } from "./types";
import searchIcon from "./searchIcon.png";
import { useStyles } from "./ProductListStyles";
interface IProductListPageProps {
  products: IProducts[];
}

export function ProductList({ products }: Readonly<IProductListPageProps>) {
  const classes = useStyles();

  const [filteredProducts, setFilteredProducts] =
    useState<IProducts[]>(products);
  const searchInputRef = useRef<HTMLInputElement>(null);
  const { setSelectedProduct } = useContext(ProductContext);
  const [searchQuery, setSearchQuery] = useState("");
  const [selectedCategory, setSelectedCategory] = useState<string>("All");
  const [categories, setCategories] = useState<string[]>([]);
  const [sortOrder, setSortOrder] = useState<string>("ASC");

  useEffect(() => {
    fetch("https://fakestoreapi.com/products")
      .then((response) => response.json())
      .then((data: IProducts[]) => {
        const uniqueCategories = [
          "All",
          ...new Set(data.map((product) => product.category)),
        ];
        setCategories(uniqueCategories);
      })
      .catch((error) => console.error("Error fetching categories", error));
  }, []);
  useEffect(() => {
    const filteredBySearch = products.filter((product) =>
      product.title.toLowerCase().includes(searchQuery.toLowerCase())
    );

    // Filter products based on selected category
    const filteredByCategory =
      selectedCategory === "All"
        ? filteredBySearch
        : filteredBySearch.filter(
            (product) => product.category === selectedCategory
          );

    // Sort products based on price
    const sortedProducts = filteredByCategory.sort((a, b) => {
      if (sortOrder === "ASC") {
        return a.price - b.price;
      } else {
        return b.price - a.price;
      }
    });

    setFilteredProducts(sortedProducts);
  }, [products, searchQuery, selectedCategory, sortOrder]);

  const handleProductSelect = (product: IProducts) => {
    setSelectedProduct(product);
  };

  const handleSearchClick = () => {
    if (searchInputRef.current) {
      setSearchQuery(searchInputRef.current.value);
    }
  };

  return (
    <div className={classes.body}>
      <div className={classes.navbar}>
        <div>
          <input
            className={classes.navSearchBar}
            type="text"
            ref={searchInputRef}
            placeholder="Search products..."
          />
          <button
            className={classes.navSearchButton}
            onClick={handleSearchClick}
          >
            <img
              src={searchIcon}
              alt="Search"
              style={{ width: "15px", height: "15px", cursor: "pointer" }}
            />
          </button>{" "}
        </div>
        <div className={classes.navFilterSort}>
          <div className={classes.navItems}>
            {" "}
            <span className={classes.navOptionsHeading}>Filter :</span>
            <select
              className={classes.navOptions}
              value={selectedCategory}
              onChange={(e) => {
                setSelectedCategory(e.target.value);
              }}
            >
              {categories.map((category, index) => (
                <option
                  className={classes.navOptions}
                  key={index}
                  value={category}
                >
                  {category}
                </option>
              ))}
            </select>
          </div>
          <div className={classes.navItems}>
            <span className={classes.navOptionsHeading}>Sort:</span>
            <select
              className={classes.navOptions}
              value={sortOrder}
              onChange={(e) => setSortOrder(e.target.value)}
            >
              <option className={classes.navOptions} value="NONE">
                Price
              </option>
              <option className={classes.navOptions} value="ASC">
                Ascending
              </option>
              <option className={classes.navOptions} value="DESC">
                Descending
              </option>
            </select>
          </div>
        </div>
      </div>
      <h1 className="pageHeading">
        KDU <span className={classes.pageSubHeading}>MARKETPLACE</span>
      </h1>
      <ul>
        {filteredProducts.map((product) => (
          <li className={classes.item} key={product.id}>
            <Link
              to={`/products/${product.id}`}
              onClick={() => handleProductSelect(product)}
            >
              <img
                className={classes.itemImage}
                src={product.image}
                alt={product.title}
              />  
              <div className={classes.itemTitlePrice}>
                <span className={classes.itemTitle}>{product.title}</span>
                <span className={classes.itemPrice}>${product.price}</span>
              </div>
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ProductList;
