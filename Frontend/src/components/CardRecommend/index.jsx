import React, { useEffect, useState } from "react";
import { api } from "../../Service/axios.js";
import "./style.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Loading from "../../components/Loading";
import { Link } from "react-router-dom";

function CardRecommend() {
  const [productRecommend, setProductRecommend] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/v1/products").then((renponse) => {
      setProductRecommend(renponse.data.content);
      setLoading(false);
    });
  }, []);

  if (loading === true) {
    return <Loading />;
  }


  return (
    <section className="cardRecommend-container" id="recommended">
      <div className="box_recommend_shadow">
        <h2 className="card_recommend_title">
          <span>Recomendações</span>
        </h2>
        <h2 className="card_recommend_title_shadow">
          <span>Recomendações</span>
        </h2>
      </div>
      <div className="card_recommend_content">
        <img
          className="card_recommend_img1 card_recommend_img"
          src={productRecommend[8].images[0].url}
          alt={productRecommend[8].description}
        />
        <div className="card_recommend_about_logo">
          <div className="card_recommend_about_logo_all">
            <div className="card_recommend_about_title">
              {productRecommend[8]?.name}
            </div>
            <div className="card_recommend_about">
              {productRecommend[8]?.description}
            </div>
            <div className="card_recommend_icon">
              <div className="card_recommend_icon0">
                <div>{productRecommend[8]?.characteristics[0].description}</div>
                <div>{productRecommend[8].characteristics[0].name}</div>
              </div>
              <div className="card_recommend_icon0 card_recommend_icon1">
                <div>{productRecommend[8]?.characteristics[1].description}</div>
                <div>{productRecommend[8]?.characteristics[1].name}</div>
              </div>
              <div className="card_recommend_icon0 card_recommend_icon2">
                <div>{productRecommend[8]?.characteristics[2].description}</div>
                <div>{productRecommend[8]?.characteristics[2].name}</div>
              </div>
              <div className="card_recommend_icon0 card_recommend_icon3">
                <div>{productRecommend[8]?.characteristics[3].description}</div>
                <div>{productRecommend[8]?.characteristics[3].name}</div>
              </div>
              <div className="card_recommend_icon4">
                <div> {productRecommend[8]?.characteristics[4].description}</div>
                <div>{productRecommend[8]?.characteristics[4].name}</div>
              </div>
            </div>
          </div>
          <div className="card_recommend_logo_button">
            <div className="card_recommend_logo">
              <img className="card_recommend_logo_img" src="https://i.postimg.cc/sXyz8b4y/Group-134.png" alt="logo do site" />
            </div>
            <Link className="button__card_recommend" to={`/description/${productRecommend[8]?.id}`}>
              <button className="button_card_recommend">Ver mais</button>
            </Link>
          </div>
        </div>
      </div>
      <div className="card_recommend_content card_recommend_box2">
        <img
          className="card_recommend_img2 card_recommend_img"
          src={productRecommend[4]?.images[0].url}
          alt={productRecommend[4]?.description}
        />
        <div className="card_recommend_about_logo">
          <div className="card_recommend_about_logo_all">
            <div className="card_recommend_about_title">
              {productRecommend[4]?.name}
            </div>
            <div className="card_recommend_about">
              {productRecommend[4]?.description}
            </div>
            <div className="card_recommend_icon">
              <div className="card_recommend_icon0">
                <div>{productRecommend[4]?.characteristics[0].description}</div>
                <div>{productRecommend[4]?.characteristics[0].name}</div>
              </div>
              <div className="card_recommend_icon0 card_recommend_icon1">
                <div>{productRecommend[4]?.characteristics[1].description}</div>
                <div>{productRecommend[4]?.characteristics[1].name}</div>
              </div>
              <div className="card_recommend_icon0 card_recommend_icon2">
                <div>{productRecommend[4]?.characteristics[2].description}</div>
                <div>{productRecommend[4]?.characteristics[2].name}</div>
              </div>
              <div className="card_recommend_icon0 card_recommend_icon3">
                <div>{productRecommend[4]?.characteristics[3].description}</div>
                <div>{productRecommend[4]?.characteristics[3].name}</div>
              </div>
              <div className="card_recommend_icon4">
                <div>{productRecommend[4]?.characteristics[4].description}</div>
                <div>{productRecommend[4]?.characteristics[4].name}</div>
              </div>
            </div>
          </div>
          <div className="card_recommend_logo_button">
            <div className="card_recommend_logo">
              <img className="card_recommend_logo_img" src="https://i.postimg.cc/sXyz8b4y/Group-134.png" alt="logo do site" />
            </div>
            <Link className="button__card_recommend" to={`/description/${productRecommend[4]?.id}`}>
              <button className="button_card_recommend">Ver mais</button>
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
}

export default CardRecommend;
