import Header_desc from "./Header_desc/Header_desc";
import Img_galeria from "./img_galeria/img_galeria";
import Car_location from "./Car_locat/Car_location";
import { DateFree } from "./DateFree/DateFree";
import { useState, useEffect } from "react";
import { api } from "../../Service/axios";
import { useParams } from "react-router-dom";
import AddRules from "../Admin/AddRules";

import "./Description.css";

export default function Description() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [numberOfMonths, setNumberOfMonths] = useState(2);
  const [products, setProducts] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    window.addEventListener("resize", () => {
      setWindowWidth(window.innerWidth);
    });
    if (windowWidth < 830) {
      setNumberOfMonths(1);
    } else {
      setNumberOfMonths(2);
    }
  }, [windowWidth]);

  useEffect(() => {
    getByIdproducts();
  }, []);

  async function getByIdproducts() {
    const response = await api.get(`/v1/products/${id}`);
    setProducts([response.data]);
  }

  return (
    <>
      <div className="Header_desc">
        <Header_desc products={products} />
      </div>
      <div className="container__description_owner">
        <div className="Img_galeria">
          <Img_galeria products={products} windowWidth={windowWidth} />
        </div>
      </div>

      <div className="date_free">
        <DateFree
          products={products}
          numberOfMonths={numberOfMonths}
          productId={id}
        />
      </div>
      <div className="container__description_owner">
        <div className="Car_location">
          <Car_location products={products} />
        </div>

        <div className="car_categories">
          <AddRules />
        </div>
      </div>
    </>
  );
}
