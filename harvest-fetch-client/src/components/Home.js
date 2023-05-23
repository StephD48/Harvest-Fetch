import "./HomeStyles.css";
import { Link } from "react-router-dom";

function Home(){
    return (
        <>
        <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://images.pexels.com/photos/440731/pexels-photo-440731.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" class="d-block w-100" alt="..."/>
                </div>
                <div class="carousel-item">
                    <img src="https://images.pexels.com/photos/1483880/pexels-photo-1483880.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" class="d-block w-100" alt="..."/>
                </div>
                <div class="carousel-item">
                    <img src="https://images.pexels.com/photos/462119/pexels-photo-462119.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" class="d-block w-100" alt="..."/>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <h1 id="farmer-title">Featured Farmers</h1>
        <div id="farmers" className="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2">
            <div class="card" >
                <img src="resources/farmer_1.jpg" class="card-img-top" alt="..."/>
                <div class="card-body">
                    <h5 class="card-title">Alpine Ridge</h5>
                    <p class="card-text">Alpine Ridge is a beautiful produce farm nestled in the heart of a picturesque mountain range.
                            With focus on sustainable and organic farming practices, this farm produces a wide variety of fresh and delicious
                            fruits and vegetables.</p>
                    <a href="#" class="btn btn-primary"><Link class="nav-link" to="/profiletest">Shop Now</Link></a>
                </div>
            </div>
            <div class="card">
                <img src="resources/farmer_2.jpg" class="card-img-top" alt="..."/>
                <div class="card-body">
                    <h5 class="card-title">Sunset Acres</h5>
                    <p class="card-text">Sunset Acres is a charming small produce farm located in the heart of the countryside. Despite its modest size, this farm produces
                            an impressive array of fresh, healthy fruits and vegetables using sustainable farming practices.</p>
                    <a href="#" class="btn btn-primary"><Link class="nav-link" to="/profiletesta">Shop Now</Link></a>
                </div>
            </div>
            <div class="card">
                <img src="resources/farmer_3.jpg" class="card-img-top" alt="..."/>
                <div class="card-body">
                    <h5 class="card-title">Windy Willow Farms</h5>
                    <p class="card-text">Windy Willow farms is a small and delightful family-owned farm located in the idyllic countryside. This charming farm focuses 
                            on orgainic farming practices, and produces a variety of high-quality and fresh fruits and vegetables.</p>
                    <a href="#" class="btn btn-primary"><Link class="nav-link" to="/profiletestb">Shop Now</Link></a>
                </div>
            </div>
            <div class="card">
                <img src="resources/farmer_4.jpg" class="card-img-top" alt="..."/>
                <div class="card-body">
                    <h5 class="card-title">Ivy Hill Farm</h5>
                    <p class="card-text">Ivy Hill farms is a charming produce farm situated in a peaceful countryside. With a passionfor sustainable farming
                            practices, this family-owned farm produces a wide variety of high-quality, fresh, and organic fruits and vegetables.</p>
                    <a href="#" class="btn btn-primary"><Link class="nav-link" to="/profiletestc">Shop Now</Link></a>
                </div>
            </div>
        </div>
        </>
    );
}

export default Home;