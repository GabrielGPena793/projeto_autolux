import React from 'react'
import AOS from "aos";
import "aos/dist/aos.css";
import { ButtonPadrao } from '../../components/ButtonPadrao';
import { Link } from 'react-router-dom';
import './style.css'

AOS.init();

function NotFound() {
  return (
    <div className="notfound_container">
      <img
        className="notfound_img notfound_card_img"
        src="https://i.postimg.cc/VNWzDGfk/PHOTOS02-720009999-PH-1-OKYOJCDCZBTK-1.png"
        alt="Carro de luxo na cor vermelha"
        data-aos="fade-right"
        data-aos-duration="500"
      />
      <div className="notfound_text_container">
        <h1 className="notfound_h1">404</h1>
        <p className="notfound_p">Ops... fim da estrada</p>
        <div className="notfound_btn">
          <Link to="/" alt="Voltar para a home" title="Voltar para a Home">
            <ButtonPadrao text="Voltar para a Home" />
          </Link>
        </div>
      </div>
    </div>
  )
}

export default NotFound
