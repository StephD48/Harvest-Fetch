import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { findAll } from "../services/productService";
import ProductCardSearch from "../components/ProductCardSearch";

export default function Search() {
    const [filteredProducts, setFilteredProducts] = useState([])
    const [products, setProducts] = useState([])
    const navigate = useNavigate();
    const [inputText, setInputText] = useState("");
    let inputHandler = e => {
        const value = e.target.value.toLowerCase()
        setFilteredProducts(products.filter(p => p.productName.toLowerCase().includes(value)))
    }

    useEffect(() => {
        findAll()
            .then(result => {
                setProducts(result)
                setFilteredProducts(result)
            })
            .catch(() => navigate("/500"));
    }, [navigate])

    



    return (
        <>
            <div>
                <h1>Product Search</h1>
                <div className="search">
                    <input type="search"
                        id="search"
                        class="form-control"
                        placeholder="Search"
                        onChange={inputHandler}
                        data-search />
                </div>
                <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2">
                    {filteredProducts.map(p => <ProductCardSearch key={p.productId} product={p} input={inputText} />)}
                </div>
            </div>
        </>
    );

}
