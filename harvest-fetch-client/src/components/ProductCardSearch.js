import React from "react-router-dom";



export default function ProductCardSearch({product}){
 

    return (
        
        <div className="col">
            <div className="card">
                {product.pictureUrl && <img src={product.pictureUrl} className="card-img-top" alt={product.productName} />}
                <div className="card-body">
                <p className="card-text">
                    {product.productName}
                       
                </p>
                </div>
            </div>
        </div>
    )

}