import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { findAll } from "../services/farmerService";
import FarmerCard from "./FarmerCard";

export default function Farmer(){
    const [farmers, setFarmers] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        findAll()
        .then(setFarmers)
        .catch(() => navigate("/500"));
    }, [navigate])

    return (
        <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2">
            {farmers.map(f => <FarmerCard key={f.farmerId} farmer={f} />)}
        </div>
    );
}