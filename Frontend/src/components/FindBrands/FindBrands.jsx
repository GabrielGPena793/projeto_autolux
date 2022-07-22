import React from "react";
import { Logo } from "../../components/Icon/Logo";

import "./findBrands.css";

export default function FindBrands() {
  return (
    <section className="find_brands_all" id="brand">
      <div className="container_find_brands">
        <div className="container_find_brands_logo">
          <Logo />
        </div>
        <div className="container_find_brands_text">
          <h1 className="find_brands_title">Encontre todas as marcas</h1>
          <p className="find_brands_paragraph">
            A AUTOLUX conta com uma frota variada e diferenciada para nossos
            clientes pois antes da reserva, todos os carros são submetidos a um
            rigoroso critério de seleção e revisão. Possuímos veículos sem
            sinais de uso ou desgaste, todos eles em excelente condição,
            portanto uma grande oportunidade de viver a experiência e o
            privilégio de dirigir os melhores carros de luxo. Para reservar seu
            próximo carro de luxo com conforto e segurança, conte conosco!
          </p>
        </div>
      </div>
      <div className="container_find_brands_image">
        <img
          className="find_brands_image"
          src="https://i.postimg.cc/J4pC0nQp/Rectangle-105.png"
          alt="img"
        />
        <img
          className="find_brands_image"
          src="https://i.postimg.cc/QdPLgyw5/Rectangle-106.png"
          alt="img"
        />
        <img
          className="find_brands_image find_brands_image1"
          src="https://i.postimg.cc/Bnt9K0Y4/Rectangle-107.png"
          alt="img"
        />
        <img
          className="find_brands_image"
          src="https://i.postimg.cc/SKzBgGbP/Rectangle-108.png"
          alt="img"
        />
      </div>
    </section>
  );
}
