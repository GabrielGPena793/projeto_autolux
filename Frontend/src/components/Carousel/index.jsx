import React from 'react'
import { Carousel } from 'react-bootstrap'
import "bootstrap/dist/css/bootstrap.min.css";
import './style.css'

function CarouselComponent() {
  return (
    <div>
      <Carousel pause={false}>
        <Carousel.Item interval={2000}>
          <img
            className="carousel-img w-100 d-inline-block"
            src={`https://images.unsplash.com/photo-1617434108848-6a1e827124ef?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80`}
            alt="Carro na cor vermelha em alta velocidade"
          />
        </Carousel.Item>
        <Carousel.Item interval={2000}>
          <img
            className="carousel-img w-100 d-inline-block"
            src={`https://images.unsplash.com/photo-1594611342073-4bb7683c27ad?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80`}
            alt="Carro esportivo na cor preta"
          />
        </Carousel.Item>
        <Carousel.Item interval={2000}>
          <img
            className="carousel-img w-100 d-inline-block"
            src={`https://images.unsplash.com/photo-1492144534655-ae79c964c9d7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1283&q=80`}
            alt="Foto de carros na cor branca, estacionados"
          />
        </Carousel.Item>
      </Carousel>
    </div>
  )
}

export default CarouselComponent;
