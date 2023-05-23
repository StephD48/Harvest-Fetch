import "./FooterStyles.css";
import { Link } from "react-router-dom";

function Footer() {
    return (
        <>
            <footer>
                <div>
                    <li class="nav-item">©️ 2023 Harvest Fetch, Inc.</li>
                </div>
                <div>
                    <li class="nav-item"><Link class="nav-link" to="/terms">Terms & Conditions</Link></li>
                </div>
                <div>
                    <li class="nav-item"><Link class="nav-link" to="/faq">FAQ</Link></li>
                </div>
                <div>
                    <li class="nav-item"><Link class="nav-link" to="/contact">Contact Us</Link></li>
                </div>
                <div class="social-media-container" role="region">
                    <div>
                        <li class="nav-item">
                            <a href="#" class="fa fa-facebook">
                                <span class="sr-only">Visit our Facebook webpage</span>
                            </a>
                            <a href="#" class="fa fa-instagram">
                                <span class="sr-only">Visit our Instagram webpage</span>
                            </a>
                            <a href="#" class="fa fa-twitter">
                                <span class="sr-only">Visit our Twitter page</span>
                            </a>
                        </li>
                    </div>
                </div>
            </footer>
        </>
    );
}

export default Footer;