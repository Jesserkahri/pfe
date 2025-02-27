import { useEffect, useState } from "react";
import axios from "axios";

const FuelStock = () => {
  const [fuelStock, setFuelStock] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/fuel")
      .then((response) => setFuelStock(response.data))
      .catch((error) => console.error("Error fetching fuel stock", error));
  }, []);

  return (
    <div>
      <h2>Fuel Stock</h2>
      <ul>
        {fuelStock.map((fuel) => (
          <li key={fuel.id}>{fuel.type} - {fuel.quantity}L</li>
        ))}
      </ul>
    </div>
  );
};

export default FuelStock;
