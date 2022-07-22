import React, { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { api } from "../../Service/axios.js";
import { Link } from "react-router-dom";
import "./style.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Loading from "../../components/Loading";
import AOS from "aos";
import "aos/dist/aos.css";

function CardCategory() {
  const [productCategory, setProductCategory] = useState([]);
  const [loading, setLoading] = useState(true);

  AOS.init();

  useEffect(() => {
    api.get("/v1/categories").then((response) => {
      setProductCategory(response.data);
      setLoading(false);
    });
  }, []);

  if (loading === true) {
    return <Loading />;
  }

  return (
    <section className="cardCategory-container" id="categories">
      <Container>
        <div className="box_category_shadow">
          <h2 className="card_category_title">
            <span>Categorias</span>
          </h2>
          <h2 className="card_category_title_shadow">
            <span>Categorias</span>
          </h2>
        </div>
        <div className="card_category_col">
          <div>
            <Link
              id='coupes'
              className="card_category_row"
              to={`/category/${productCategory[0].qualification}`}
              style={{ textDecoration: "none" }}
            >
              <div
                className="cardCategory-img-content"
              >
                <img
                  className="card_category_img0 card_category_img"
                  src="https://i.postimg.cc/VNWzDGfk/PHOTOS02-720009999-PH-1-OKYOJCDCZBTK-1.png"
                  alt={productCategory[0].description}
                  data-aos="fade-right"
                  data-aos-duration="500"
                />
              </div>
              <div className="cardCategory_content">
                <div className="card_category_content_title">
                  {productCategory[0].qualification}
                </div>
                <div className="card_category_content_text">
                  {productCategory[0].description}
                </div>
              </div>
            </Link>
          </div>
          <div>
            <Link
              id="conversiveis"
              className="card_category_row_reverse"
              to={`/category/${productCategory[1].qualification}`}
              style={{ textDecoration: "none" }}
            >
              <div
                className="cardCategory-img-content"
              >
                <img
                  className="card_category_img1 card_category_img"
                  src="https://i.postimg.cc/mg0S8jdJ/7ed7a8fa861553adc42b3f5d198fad34-1.png"
                  alt={productCategory[1].description}
                  data-aos="fade-left"
                  data-aos-duration="500"
                />
              </div>
              <div className="cardCategory_content">
                <div className="card_category_content_title">
                  {productCategory[1].qualification}
                </div>
                <div className="card_category_content_text">
                  {productCategory[1].description}
                </div>
              </div>
            </Link>
          </div>
          <div>
            <Link
              id="sedans"
              className="card_category_row"
              to={`/category/${productCategory[2].qualification}`}
              style={{ textDecoration: "none" }}
            >
              <div
                className="cardCategory-img-content"
              >
                <img
                  className="card_category_img2 card_category_img"
                  src="https://i.postimg.cc/tgWNf6wT/OIP-1.png"
                  alt={productCategory[2].description}
                  data-aos="fade-right"
                  data-aos-duration="500"
                />
              </div>
              <div className="cardCategory_content">
                <div className="card_category_content_title">
                  {productCategory[2].qualification}
                </div>
                <div className="card_category_content_text">
                  {productCategory[2].description}
                </div>
              </div>
            </Link>
          </div>
          <div>
            <Link
              id="suv"
              className="card_category_row_reverse"
              to={`/category/${productCategory[3].qualification}`}
              style={{ textDecoration: "none" }}
            >
              <div
                className="cardCategory-img-content"
              >
                <img
                  className="card_category_img3 card_category_img"
                  src="https://i.postimg.cc/qRGXBTkH/Lamborghini-Urus-Rental-Dubai-1.png"
                  alt={productCategory[3].description}
                  data-aos="fade-left"
                  data-aos-duration="500"
                />
              </div>
              <div className="cardCategory_content">
                <div className="card_category_content_title">
                  {productCategory[3].qualification}
                </div>
                <div className="card_category_content_text">
                  {productCategory[3].description}
                </div>
              </div>
            </Link>
          </div>
        </div>
      </Container>
    </section>
  );
}

export default CardCategory;
