import { getEmtpyFarmerProduct, save } from "../services/farmerProductService"
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { findById } from "../services/productService"

export default function AddProductForm({farmer}){
    // const [currentFarmerProduct, setCurrentFarmerProduct] = useState({ ...farmerProduct });
    // const [product, setProduct] = useState();
    // const navigate = useNavigate();

    // useEffect(() => {
    //     if (farmer.productId) {
    //         findById(farmer.productId)
    //         .then(product => {
    //             console.log(product)
    //             setProduct(product)
    //         })
    //         .catch(() => navigate("/500"))
    //     }
    // }, []);

    // function handleChange(evt) {
    //     const nextProduct = { ...currentProduct };
    //     nextProduct[evt.target.name] = evt.target.value;
    //     setCurrentProduct(nextProduct);
    // }

    // function cancel() {
    //     navigate(`/farmerProfile/${farmer.farmerId}`);
    // }

    // function handleSubmit(evt) {
    //     evt.preventDefault();
    //     save(currentProduct);
    // }

    return (
        <div>
        {/* <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="productName" className="form-label">Product Name</label>
                <input type="text" id="productName" name="productName" className="form-control"
                    value={product.productName} onChange={handleChange} />
            </div>
            <div>
                <label htmlFor="productUrl" className="form-label">Picture Url</label>
                <input type="text" id="productUrl" name="productUrl" className="form-control"
                    value={product.pictureUrl} onChange={handleChange} />
            </div>
            <div>
                <label htmlFor="price" className="form-label">Price</label>
                <input type="decimal" id="price" name="pricel" className="form-control"
                    value={product.price} onChange={handleChange} />
            </div>
            <div className="mt-2">
                <button type="submit" className="btn btn-primary me-2">Save</button>
                <button onClick={cancel} type="button" className="btn btn-warning">Cancel</button>
            </div>
        </form> */}
    </div>
    )
}