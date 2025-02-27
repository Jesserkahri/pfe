import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav>
      <Link to="/dashboard">Dashboard</Link>
      <Link to="/fuel-stock">Fuel Stock</Link>
      <button onClick={() => localStorage.removeItem("token")}>Logout</button>
    </nav>
  );
};

export default Navbar;
