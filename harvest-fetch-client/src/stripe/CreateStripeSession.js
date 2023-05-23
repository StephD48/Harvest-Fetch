import { useNavigate } from "react-router-dom";

async function CreateStripeSession(orderItems) {
  const config = {
    method: "POST",
    body: JSON.stringify(orderItems),
    headers: {
        "Content-Type": "application/json",
      },
  };
  const response = await fetch("http://localhost:8080/api/create/session", config);

  if (response.ok) {
    const url = await response.text();
    const navigate = useNavigate();
    navigate("http://localhost:3000/success");
  } else {
    const navigate = useNavigate();
    navigate("http://localhost:3000/error");
  }
}
