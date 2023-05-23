import "./ContactUsStyles.css";
import { Link } from "react-router-dom";

function ContactUs() {
    return (
        <>
            <div>
                <div class="container-image">
                    <img src="https://www.landthink.com/wp-content/uploads/leasing-farmland.jpg" />
                    <div class="text-container">
                        <div class="bottom-centered">Help & Contact</div>
                        <div class="bottom-centered-subtitle">Reach out at any time for anything from advice to feedback on your order.</div>
                    </div>
                </div>
                <section id="top" className="mt-5">
                    <div className="left">
                        <div className="title">FAQ</div>
                        <div className="message mt-2">Answers to some of the questions frequently asked of us.</div>
                        <div>
                            <button className="button btn btn-success mt-2 mb-2"><Link className="nav-link" to="/faq">Visit FAQ</Link></button>
                        </div>
                    </div>
                    <div className="right">
                        <div className="title">Problem With The Service?</div>
                        <div className="message mt-2">For any issues with Harvest Fetch's service as a customer or farmer, let us know so that we may help!</div>
                        <div>
                            <button className="button btn btn-success mt-2 mb-2">
                                <a href="mailto:info@harvestfetch.com" className="button">Email Us</a>
                            </button>
                        </div>
                    </div>
                </section>
                <section id="bottom">
                    <div className="bottom mt-5">
                        <div className="title">Customer Service</div>
                        <div className="message">
                            <br />
                            Email: <div className="bold">info@harvestfetch.com</div>
                            Call/Text: 901-555-5555
                        </div>
                    </div>
                </section>
            </div>
        </>
    );
}

export default ContactUs;