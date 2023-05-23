import { useContext, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { findById, removeProductFromFarmer, getEmptyProduct } from "../services/productService";
import AuthContext from "../contexts/AuthContext";

export default function ProductDelete(){
    const [product, setProduct] = useState(getEmptyProduct());
    const { appUser } = useContext(AuthContext);

    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (!(appUser && appUser.authorities.includes("FARMER"))) {
            navigate("/")
        }

        if (id) {
            findById(id).then(setProduct).catch(() => navigate("/"));
        } else {
            navigate("/")
        }
    }, [id, navigate]);

    function handleDelete(){
        removeProductFromFarmer(product.productId)
        .then(navigate("/"))
        .catch(console.log);
    }

    return (
        <>
        <h1>Delete {product.productName}?</h1>
            <div className="alert alert-danger">
                Are sure you want to delete {product.productName}?
            </div>
            <div>
                <button className="btn btn-danger me-2" onClick={handleDelete}>Yes, Delete</button>
                <Link to="/" className="btn btn-warning">No, Cancel</Link>
            </div>
        </>
    )
}