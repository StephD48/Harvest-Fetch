import "./FAQStyles.css";

function FAQ() {

    return (
        <>
            <div class="container-image">
                <img src="https://imageio.forbes.com/specials-images/imageserve/62d4e316651300e49e9139fb/0x0.jpg?format=jpg&width=1200" />
                <div class="faq-title">Frequently Asked Questions (FAQ)</div>
            </div>
            <div class="questions-container mt-5">
                <div class="question-one">
                    <div class="question">
                        Why HarvestFetch?
                    </div>
                    <div class="answer">
                        Many reasons but just to name a few...
                        <br />
                        <ul>
                            <li>We connect local, small, community farmers to the people,
                                eliminating the need for massive overhead, storage fees while
                                ensuring a personal touch that homegrown business are known for.
                            </li>

                            <li>We believe in supporting the community from farmers to customers
                                to everyone in between while also helping those in need.
                            </li>

                            <li>We are committed to elminating waste which why we help in providing
                                our local farmers with sustainable packaging that also ensures your produce
                                lasts longer.
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="question-two">
                    <div class="question">
                        How do I create a HarvestFetch account?
                    </div>
                    <div class="answer">
                        On the website homepage (harvestfetch.com):
                        <br />
                        <ul>
                            <li>Click Login/Signup at the top of the page
                            </li>
                            <li>Enter your preferred username and password
                            </li>

                            <li>Fill out your personal information (name, address, email)
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="question-three">
                    <div class="question">
                        Is all of your produce organic?
                    </div>
                    <div class="answer">
                        While most of our farmers are Certified Organic by the USDA, we do partner
                        with a few farms that are Certified Naturally Grown, a peer-reviewed system
                        of standards that is less expensive but is often viewed as a path to USDA Organic Certification.
                    </div>
                </div>
            </div>
        </>
    );
}

export default FAQ;