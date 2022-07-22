import React from "react";
import { BsFillGeoAltFill } from "react-icons/bs";
import { Link } from "react-router-dom";
import { Navbar } from "react-bootstrap";
import "./Header_desc.css";
import { CaretLeft } from "phosphor-react";
import { StarScore } from "../../../components/StartScore";

export default function Header_desc({ products }) {
  return (
    <>
    {products.map((product) => (
    <div className="description_all" key={product.id}>
      <div className="description_header_all">
        <Navbar className="Header_description_All">
          <Navbar.Brand className="text-dark">
            <h5 title="Categoria" alt="Categoria">
              {product.category.qualification}
            </h5>
            <h4 title="Titulo Carro" alt="Titulo Carro">
             {product.name}
            </h4>
          </Navbar.Brand>
          <Link className="link-to" to="/category/all" alt="Voltar" title="Voltar">
            <CaretLeft size={50} weight="bold" color="var(--dark-blue)" />
          </Link>
        </Navbar>
      </div>

      <div className="description_localidade_avaliacao_color">
        <Navbar className="description_localidade_avaliacao">
          <Navbar.Brand>
            <h6 title="Titulo Carro" alt="Titulo Carro">
              <BsFillGeoAltFill className="header_iconLocation"/>
               {product.city.name}, {product.city.country}
            </h6>
          </Navbar.Brand>
          <div>
            <h5 title="Categoria" alt="Categoria">
              Avaliação
            </h5>
            <div className="container_star">
              <StarScore score={product.score} />
            </div>
          </div>
        </Navbar>
      </div>
    </div>
  ))}
  </>
  );
}
