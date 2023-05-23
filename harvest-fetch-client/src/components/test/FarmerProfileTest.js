import "./FarmerProfileTestStyles.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { findAll } from "../../services/productService";
import ProductTestCard from "./ProductTestCard";

function FarmerProfileTest() {
    const [products, setProducts] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        findAll()
        .then(setProducts)
        .catch(() => navigate("/500"));
    }, [navigate])

    return (
        <>
            <body className="profileBody">
                <div className="full">
                    <section id="profile">
                        <div className="name">
                            Alpine Ridge
                        </div>
                        <div className="img">
                            <img src="https://images.squarespace-cdn.com/content/v1/5a7874cabe42d6cd6a7dca35/1517858230310-UFO302OSS2ZCEOGCHK0Z/fazenda-santa-ines-5.jpg" />
                        </div>
                        <section id="info" className="info">
                            <div class="row">
                                <div class="column">
                                    <ul>
                                        <li><span className="label">Address: </span></li>
                                        <li><span className="label">City: </span></li>
                                        <li><span className="label">State: </span></li>
                                        <li><span className="label">Zip Code: </span></li>
                                        <li><span className="label">Email: </span></li>
                                        <li><span className="label">Phone: </span></li>
                                    </ul>
                                </div>
                                <div class="columnb">
                                    <ul>
                                        <li><span className="entry">123 Main St.</span></li>
                                        <li><span className="entry">Memphis</span></li>
                                        <li><span className="entry">TN</span></li>
                                        <li><span className="entry">38118</span></li>
                                        <li><span className="entry">alpineridge@alpine.com</span></li>
                                        <li><span className="entry">9015551234</span></li>
                                    </ul>
                                </div>
                            </div>
                        </section>
                        <div className="subtitle">Details</div>
                        <section className="details">
                            <div class="boxa">Alpine Ridge is a beautiful produce farm nestled in the heart of a picturesque mountain range.
                            With focus on sustainable and organic farming practices, this farm produces a wide variety of fresh and delicious
                            fruits and vegetables. We hope to see you soon! </div>
                        </section>
                    </section>
                    <section id="inventory">
                        <div className="name">Inventory</div>
                        <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2 boxb">
                            {products.map(p => <ProductTestCard key={p.productId} product={p} />)}
                        </div>
                    </section>
                </div>
            </body>
        </>
    )
}

export default FarmerProfileTest;