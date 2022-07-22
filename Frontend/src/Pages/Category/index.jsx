import React, { useEffect, useState } from "react";
import { Card, Navbar} from "react-bootstrap";
import { Link, useNavigate, useParams } from "react-router-dom";
import Loading from "../../components/Loading";
import { CaretLeft } from "phosphor-react";

import "./style.css";
import { StarScore } from "../../components/StartScore";

function Category(props) {
  const cities = {
    1: "São Paulo",
    2: "Rio de Janeiro",
    3: "Recife",
    4: "Manaus",
    5: "Florianópolis",
  };

  const [category, setCategory] = useState([]);
  const [products, setProducts] = useState([]);
  const [city, setCity] = useState([]);
  const [loading, setLoading] = useState(true);

  let navigate = useNavigate();

  const { categoryParams, cityParams, dateInit, dateFinal } = useParams();

  useEffect(() => {
    getCity();
    getCategory();
    if (categoryParams === "all") {
      resetLocalStorageDates();
      filterAllProducts();
    } else if (categoryParams !== undefined) {
      resetLocalStorageDates();
      filterByCategory(categoryParams);
    } else if (cityParams !== undefined && dateFinal !== undefined) {
      filterProductsByCityDate(cityParams, dateInit, dateFinal);
    } else if (cityParams !== undefined) {
      resetLocalStorageDates();
      filterProductsByCity(cityParams);
    } else if (dateFinal !== undefined) {
      filterProductsByDate(dateInit, dateFinal);
    }
  }, []);

  function resetLocalStorageDates() {
    localStorage.setItem("@inicial_date", "");
    localStorage.setItem("@final_date", "");
  }

  //Busca os nomes das categorias para utilizar na sidebar
  async function getCategory() {
    try {
      const data = await fetch(
        "https://back-end-booking.herokuapp.com/v1/categories"
      );
      const content = await data.json();
      setCategory(content);
      setLoading(false);
    } catch (error) {
      alert("Erro de comunicação com o servidor", error);
    }
  }

  //Filtra os produtos a partir da categoria
  async function filterByCategory(qualification) {
    try {
      const data = await fetch(
        `https://back-end-booking.herokuapp.com/v1/products/category?qualification=${qualification}&page=0&size=20`
      );
      const response = await data.json();
      setProducts(response.content);
      navigate(`/category/${qualification}`, { replace: true });
      moveScroll();
      setLoading(false);
    } catch (error) {
      alert("Erro de comunicação com o servidor", error);
    }
  }

  //Busca as cidades para utilizar no botão de filtro
  async function getCity() {
    try {
      const data = await fetch(
        "https://back-end-booking.herokuapp.com/v1/cities"
      );
      const response = await data.json();
      setCity(response);
      setLoading(false);
    } catch (error) {
      alert("Erro de comunicação com o servidor", error);
    }
  }

  //Busca todos os produtos
  async function filterAllProducts() {
    try {
      const data = await fetch(
        `https://back-end-booking.herokuapp.com/v1/products?page=0&size=40`
      );
      const response = await data.json();
      setProducts(response.content);
      navigate(`/category/all`, { replace: true });
      moveScroll();
      setLoading(false);
    } catch (error) {
      console.log("Erro de comunicação com o servidor", error);
    }
  }

  //Filtra todos os modelos a partir da cidade
  async function filterProductsByCity(id) {
    try {
      if (id === "") filterAllProducts();
      const data = await fetch(
        `https://back-end-booking.herokuapp.com/v1/products/city?id=${id}&page=0&size=32`
      );
      const response = await data.json();
      setProducts(response.content);
      setLoading(false);
      navigate(`/category/city/${id === undefined ? cityParams : id}`, {
        replace: true,
      });
      moveScroll();
    } catch (error) {
      alert("Erro de comunicação com o servidor", error);
    }
  }

  async function filterProductsByCityDate(id, dateInit, dateEnd) {
    try {
      const data = await fetch(
        `https://back-end-booking.herokuapp.com/v1/products/city/date?cityId=${id}&init=${dateInit}&end=${dateEnd}`
      );
      const response = await data.json();
      setProducts(response);
      setLoading(false);
    } catch (error) {
      alert("Erro de comunicação com o servidor", error);
    }
  }

  async function filterProductsByDate(dateInit, dateFinal) {
    try {
      const data = await fetch(
        `https://back-end-booking.herokuapp.com/v1/products/date?init=${dateInit}&end=${dateFinal}`
      );
      const response = await data.json();
      setProducts(response);
      navigate(`/category/date/${dateInit}/${dateFinal}`, { replace: true });
      moveScroll();
      setLoading(false);
    } catch (error) {
      alert("Erro de comunicação com o servidor", error);
    }
  }

  // Para que ao sair e voltar para a página, o body esteja no topo
  function moveScroll() {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  }

  if (loading === true) {
    return <Loading />;
  }

  return (
    <>
      <div className="description_header_all">
        <Navbar className="Header_description_All">
          <Navbar.Brand >
              Categoria
          </Navbar.Brand>
          <Link className="link-to" to="/" alt="Voltar" title="Voltar">
            <div >
            <CaretLeft size={50} weight="bold" color="var(--dark-blue)" />
            </div>
          </Link>
        </Navbar>
      </div>

      <div className="container category_container">
        <div className="row mt-5 mx-2  gap-5">
          <div className="col-md-2">
            <div className="sidebar_category">
              <ul className="list-unstyled components">
                {category.map((category) => (
                  <li
                    className="sidebar_li_btn"
                    key={category.id}
                    onClick={() => filterByCategory(category.qualification)}
                  >
                    {category.qualification}
                  </li>
                ))}
                <li
                  className="sidebar_li_btn"
                  value="all"
                  onClick={filterAllProducts}
                >
                  Todos os carros
                </li>
                <select
                  className="sidebar_select"
                  defaultValue={
                    cities[cityParams]
                      ? cities[cityParams]
                      : "Filtre por cidade"
                  }
                  onChange={(e) => filterProductsByCity(e.target.value)}
                >
                  <option className="sidebar_option" disabled>
                    {cities[cityParams]
                      ? cities[cityParams]
                      : "Filtre por cidade"}
                  </option>
                  {city.map((city) => (
                    <option key={city.id} value={city.id}>
                      {city.name}
                    </option>
                  ))}
                </select>
              </ul>
            </div>
          </div>

          <div className="col-md-9">
            <div className="row">
              {products.map((product) => (
                <div
                  key={product.id}
                  className="col-xs-12 col-sm-12 col-md-12 col-lg-6 col-xl-4 mb-4"
                >
                  <Link
                    className="cardCategory_link"
                    to={`/description/${product.id}`}
                  >
                    <Card className="singleCard border-0">
                      <div className="singleCard_img_content">
                        <Card.Img
                          className="singleCard_img img-fluid"
                          src={product.images[0].url}
                          alt={product.description}
                        />
                      </div>
                      <Card.Body>
                        <Card.Title className="singleCard_title">
                          {product.name}
                          <StarScore score={product.score} className="container_starts_category" />
                        </Card.Title>
                        <div className="container__tags">
                          <span>Cidade: {product.city.name}</span>
                          <span>
                            Categoria: {product.category.qualification}
                          </span>
                        </div>
                      </Card.Body>
                    </Card>
                  </Link>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
      <div></div>
    </>
  );
}

export default Category;
