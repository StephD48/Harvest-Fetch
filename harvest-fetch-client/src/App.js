import './App.css';
import { useState, useEffect} from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import Home from "./components/Home";
import Navbar from "./components/Navbar";
import { refresh } from "./services/authService";
import AuthContext from "./contexts/AuthContext";
import LoginForm from "./pages/LoginForm";
import SignupForm from "./pages/SignupForm";
import Footer from './components/Footer';
import Terms from './components/Terms';
import FAQ from './components/FAQ';
import Farmer from './pages/Farmer';
import ContactUs from './components/ContactUs';
import FarmerProfile from './pages/FarmerProfile';
import Cart from './components/Cart';
import Success from './stripe/Success.js';
import Error from './stripe/Error.js';
import FarmerProfileTest from './components/test/FarmerProfileTest';
import FarmerProfileTestA from './components/test/FarmProfileTestA';
import FarmerProfileTestB from './components/test/FarmProfileTestB';
import FarmerProfileTestC from './components/test/FarmProfileTestC';
import FarmerFormTest from './components/test/FarmerFormTest';
import  CartContextProvider  from './contexts/CartContext';
import Search from './pages/Search';
import ProductForm from './pages/ProductForm';
import ProductDelete from './pages/ProductDelete';
import LogoutConfirmation from './components/LogoutConfirmation';
import AddProductForm from './pages/AddProductForm';

function App() {

  const [appUser, setAppUser] = useState();

  useEffect(() => {
    refresh().then(login).catch();
  }, [])

  function login(appUserArg) {
    setAppUser(appUserArg);
    localStorage.setItem("HF_JWT", appUserArg.jwt);
  }

  function logout() {
    setAppUser();
    localStorage.removeItem("HF_JWT");
  }

  const auth = {
    appUser,
    login,
    logout

  };

  return (
    
    <div className="App">
      <AuthContext.Provider value={auth}>
        <CartContextProvider >
          <Router>
            <Navbar />
            <div className="main">
              <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/loginform" element={<LoginForm />} />
                <Route path="/farmers" element={<Farmer />}/>
                <Route path="/signupform" element={<SignupForm />} />
                <Route path="/farmerform" element={<FarmerFormTest />} />
                <Route path="/farmerProfile/:farmerId" element={<FarmerProfile />}/>
                <Route path="/cart" element={<Cart />} />
                <Route path="/success" element={<Success />} />
                <Route path="/products/edit/:id" element={<ProductForm/>}/>
                <Route path="/products/delete/:id" element={<ProductDelete />}/>
                <Route path="/error" element={<Error />} />
                <Route path="/terms" element={<Terms />} />
                <Route path="/faq" element={<FAQ />} />
                <Route path ="/contact" element={<ContactUs />} />
                <Route path ="/profiletest" element={<FarmerProfileTest />} />
                <Route path ="/profiletesta" element={<FarmerProfileTestA />} />
                <Route path ="/profiletestb" element={<FarmerProfileTestB />} />
                <Route path ="/profiletestc" element={<FarmerProfileTestC />} />
                <Route path ="/search" element={<Search />} />
                <Route path ="/logout" element={<LogoutConfirmation />} />
                <Route path="/products/add/:id" element={<AddProductForm />}/>
              </Routes>
            </div>
            <Footer />
          </Router>
        </CartContextProvider>  
      </AuthContext.Provider>
    </div>
  );
}

export default App;
