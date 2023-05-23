import React from 'react';
import { useNavigate } from 'react-router-dom';
import "./Error.css";

function Error() {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate('/cart');
  };

  return (
    <body className='profileBody'>
      <div className="error-container">
        <h1 className="error-title">Payment Error</h1>
        <p className="error-message">There was an error processing your payment.</p>
        <p><button className="error-button" onClick={handleClick}>Return to Cart</button></p>
      </div>
    </body>
  );
}

export default Error;
