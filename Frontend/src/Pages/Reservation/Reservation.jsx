import Header_desc from "../Description/Header_desc/Header_desc";
import Descrip_footer from "../Description/descrip_footer/descrip_footer";
import Reserv_page from "./Reservation/Reserv_page";
import { useState, useEffect } from "react";
import { api } from "../../Service/axios";
import { useParams } from "react-router-dom";
import Loading from "../../components/Loading";

export default function Reservation() {
  const [products, setProducts] = useState([]);
  const [load, setLoad] = useState(true)
  const { id } = useParams();

  useEffect(() => {
    getByIdproducts();
  }, []);

  async function getByIdproducts() {
    const response = await api.get(`/v1/products/${id}`);
    setProducts([response.data]);
    setLoad(false)
  }

  if (load) {
    return <Loading />
  }

  return (
    <div>
      <div className="Header_reservation">
        <Header_desc products={products} />
      </div>
      <div className="Header_desc">
        <Reserv_page products={products} />
      </div>
      <div className="car_categories_reservation">
        <Descrip_footer />
      </div>
    </div>
  );
}
