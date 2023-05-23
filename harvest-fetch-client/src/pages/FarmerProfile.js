import "../components/test/FarmerProfileTestStyles.css";
import { useParams } from 'react-router-dom';
import { useEffect, useState } from "react";
import UserInfo from "./UserInfo";
import { findById } from "../services/farmerService";
import ProductCard from "./ProductCard";
import { useNavigate } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { Link } from "react-router-dom";
import { useContext } from "react";

export default function FarmerProfile(){
    const [farmer, setFarmer] = useState([]);
    const { appUser } = useContext(AuthContext);
    
    const { farmerId } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        if (farmerId) {
            findById(farmerId)
            .then(farmer => {
                console.log(farmer)
                setFarmer(farmer)
            })
            .catch(() => navigate("/500"))
        }
    }, []);

    return (
        <>
            <body className="profileBody">
                <div className="full">
                    <section id="profile">
                        <div className="name farm-name">{farmer.farmName}</div>
                        <div className="img">
                        {farmer.photoUrl && <img src={farmer.photoUrl} alt={farmer.farmName} />}
                        </div> 
                        <section id="info" className="info">
                            <div class="row">
                                <div class="column">
                                    <ul>
                                        <li><span className="label">Address: </span></li>
                                        <li><span className="label">City: </span></li>
                                        <li><span className="label">State: </span></li>
                                        <li><span className="label">Zipcode: </span></li>
                                        <li><span className="label">Email: </span></li>
                                        <li><span className="label">Phone: </span></li>
                                    </ul>
                                </div>
                                <UserInfo userId={farmer.userId} />
                            </div>
                        </section>
                        <div className="subtitle">Details</div>
                        <section className="details">
                            <div class="boxa">{farmer.details}</div>
                        </section>
                    </section>
                    <section id="inventory">
                    <div className="name inventoryTwo">
                        <h1>Inventory</h1>
                        {appUser && appUser.appUserId == farmer.userId && <>
                        <Link to={`/products/add/${farmer.farmerId}`}><button type="button" class="btn btn-primary add-product-button">Add Product</button></Link></>
                        }
                        </div>
                        <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2 boxb">
                            {farmer && farmer.products && farmer.products.map(p => <ProductCard key={p.productId} product={p} farmer={farmer} />)}
                        </div>
                    </section>
                </div>
            </body>
        </>
    );
}