import "../components/test/FarmerProfileTestStyles.css";
import { useEffect, useState } from "react";
import { findById } from "../services/appUserInfoService";
import { useNavigate } from "react-router-dom";

export default function UserInfo({userId}){
    const [user, setUser] = useState([])
    const navigate = useNavigate();
    console.log("user Info", userId)
    useEffect(() => {
        if (userId) {
            findById(userId)
            .then(user => {
                console.log(user)
                setUser(user)
            })
            .catch(() => navigate("/500"))
        }
    }, [userId]);
    
    return (
        <div class="columnb">
            <ul>
                <li><span className="entry">{user.address}</span></li>
                <li><span className="entry">{user.city}</span></li>
                <li><span className="entry">{user.state}</span></li>
                <li><span className="entry">{user.zipCode}</span></li>
                <li><span className="entry">{user.email}</span></li>
                <li><span className="entry">{user.phone}</span></li>
            </ul>
        </div>
    );
}