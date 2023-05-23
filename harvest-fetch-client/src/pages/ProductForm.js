import { findById, getEmptyProduct, save } from "../services/productService"
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function ProductForm({farmer}){
    const [product, setProduct] = useState(getEmptyProduct());
    const [error, setErrors] = useState([]);
    const [wait, setWait] = useState(true);

    const navigate = useNavigate();
    const { id } = useParams();

    function cancel(){
        navigate("/");
    }

    function onChange(evt) {
        const nextProduct = {...product}
        nextProduct[evt.target.name] = evt.target.value;
        setProduct(nextProduct);
    }

    function handleSubmit(evt){
        evt.preventDefault();
        save(product);
    }

    if(wait) {
        return (
            <div className="spinner-border" role="status">
                <span className="visually-hidden">Loading...</span>
            </div>
        )
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="productName" className="form-label">Product Name</label>
                <input type="text" id="productName" name="productName" className="form-control"
                    value={product.productName} onChange={onChange} />
            </div>
            <div>
                <label htmlFor="productUrl" className="form-label">Picture Url</label>
                <input type="text" id="productUrl" name="productUrl" className="form-control"
                    value={product.pictureUrl} onChange={onChange} />
            </div>
            <div>
                <label htmlFor="price" className="form-label">Price</label>
                <input type="decimal" id="price" name="pricel" className="form-control"
                    value={product.price} onChange={onChange} />
            </div>
            <div className="mt-2">
                <button type="submit" className="btn btn-primary me-2">Save</button>
                <button onClick={cancel} type="button" className="btn btn-warning">Cancel</button>
            </div>
        </form>
    )
}