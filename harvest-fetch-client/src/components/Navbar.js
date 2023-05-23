import "./NavbarStyles.css";
import { useContext, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { CartContext } from "../contexts/CartContext";

function Navbar() {

    const { appUser, logout } = useContext(AuthContext);
    const { cartItemCounter }  = useContext(CartContext);
   
    const [cartItems, setCartItems] = useState([]);

    // useEffect(() => {
    //     const cartItemsFromStorage = localStorage.getItem('cartItems');
    //     if (cartItemsFromStorage) {
    //         setCartItems(JSON.parse(cartItemsFromStorage));
    //         setCartItemCounter(JSON.parse(cartItemsFromStorage).length);
    //     }
    // }, []);

    // useEffect(() => {
    //     console.log(cartContext);
    // }, [cartContext]);

    
    function handleLogout(evt) {
        evt.preventDefault();
        logout();
    }
    // function handleAddToCart() {
    //     const newCartItem = {
    //         name: 'Product name',
    //         price: 10.99
    //       };
    //     const newCartItems = [...cartItems, newCartItem];
    //     setCartItems(newCartItems);
    //   }
    

    return (
        <>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <Link class="navbar-brand" to="/">Harvest Fetch</Link>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item"><Link class="nav-link active" aria-current="page" to="/">Home</Link></li>
                            <li class="nav-item"><Link class="nav-link" to="/farmers">Farmers</Link></li>
                            <li class="nav-item">{appUser ? <>
                                <a href="#logout" className="nav-link" onClick={handleLogout}>Logout</a>
                                {/* {appUser.sub} */}
                            </>
                                : <Link class="nav-link" to="/loginform">Login/Signup</Link>
                            }</li>

                        </ul>
                        <form class="d-flex bar" role="search">
                            <div class="location">
                                <input class="form-control me-2" placeholder="Zip Code" aria-label="Search" />
                            </div>
                            <div class="search">
                                <input
                                    type="text"
                                    class="form-control me-2"
                                    placeholder="search"
                                // value={searchInput}
                                />
                            </div>
                            <button class="btn btn-outline-success green-button nav-item"><Link class="nav-link" to="/search">Search</Link></button>
                        </form>
                        <Link to="/cart" className="cart">Cart ({cartItemCounter})
                            <svg className="cart"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 576 512">
                                <path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z" />
                            </svg> 
                        </Link>                                    
                    </div>
                </div>
            </nav>
        </>
    );
}

export default Navbar;
