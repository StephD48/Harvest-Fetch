import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { findById } from "../services/farmerService";
import { getEmtpyFarmer } from "../services/farmerService";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';

export default function Farmer({farmer}){
    const navigate = useNavigate();

    function findByFarmerId(){
        findById(farmer.farmerId)
        .then(() => navigate(`/farmerProfile/${farmer.farmerId}`))
        .catch(() => navigate("/error"));
    }

    return (
        <div className="col">
            <div className="card">
                {farmer.photoUrl && <img src={farmer.photoUrl} className="card-img-top" alt={farmer.farmName} />}
                <div className="card-body">
                    <div className="row">
                        <Link class="nav-link" onClick={findByFarmerId}><h5 className="card-title col">{farmer.farmName}</h5></Link>
                    </div>
                </div>
            </div>
        </div>
    )
}