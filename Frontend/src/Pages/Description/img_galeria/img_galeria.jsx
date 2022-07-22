import React from "react";
import "./img_galeria.css";
import { Card } from "react-bootstrap";
import { Modal, Carousel } from "react-bootstrap";
import { useState } from "react";
import {
  FacebookShareButton,
  FacebookIcon,
  WhatsappShareButton,
  WhatsappIcon,
  TwitterShareButton,
  TwitterIcon,
  EmailShareButton,
  EmailIcon,
} from "react-share";

export default function Img_galeria({ products, windowWidth }) {
  const [show, setShow] = useState(false);
  const [selectImage, setSelectImage] = useState(0);


  const shareUrl = "http://autolux.ctdprojetos.com.br/";

  return (
    <>
      {products.map((product) => (
        <div key={product.id}>
          {windowWidth < 1024 ? (
            <div>
              <Carousel className="container__carousel" fade={true} interval={null}>
                {product.images.map((image) => (
                  <div key={image.url}>
                    <Carousel.Item>
                      <img
                        className="d-block w-100"
                        src={image.url}
                        alt="First slide"
                      />
                    </Carousel.Item>
                  </div>
                ))}
              </Carousel>
            </div>
          ) : (
            <div className="galeria_all">
              <div className="img_galeria_all" key={product.id}>
                <div className="img_galeria_grande_div">
                  <Card.Img
                    className="img_all_caract img_galeria_grande"
                    src={product.images[1]?.url}
                  />
                </div>
                <div className="img_galeria_col">
                  <div className="img_galeria_row">
                    <div className="img_all_padd">
                      <Card.Img
                        className="img_all_caract"
                        src={product.images[2]?.url}
                      />
                    </div>
                    <div className="img_all_padd">
                      <Card.Img
                        className="img_all_caract"
                        src={product.images[3]?.url}
                      />
                    </div>
                  </div>
                  <div className="img_galeria_row">
                    <div className="img_all_padd">
                      <Card.Img
                        className="img_all_caract"
                        src={product.images[4]?.url}
                      />
                    </div>
                    <div className="img_all_padd">
                      <div className="img_escrito_mais">
                        <>
                          <button
                            className="img_gal_button_mais"
                            onClick={() => setShow(true)}
                          >
                            Ver mais
                          </button>
                          <Modal
                            show={show}
                            onHide={() => setShow(false)}
                            dialogClassName="modal-xl"
                            centered
                          >
                            <Modal.Body>
                              <Modal.Header closeButton></Modal.Header>
                              <Carousel fade={true} interval={null}>
                                {product.images.map((image) => (
                                  <Carousel.Item key={image.id} className={`${selectImage === image.id ? "active" : ""}`}>
                                    <img
                                      className="d-block w-100"
                                      src={image.url}
                                      alt="First slide"
                                    />
                                  </Carousel.Item>
                                ))}
                              </Carousel>
                              <div className="container_images_preview" >
                                {product.images.map((image) => (
                                  <div key={image.id} onClick={() => setSelectImage(image.id)}>
                                    <img
                                      className="d-block w-100"
                                      src={image.url}
                                      alt="First slide"
                                    />
                                  </div>
                                ))}
                              </div>
                            </Modal.Body>
                          </Modal>
                        </>
                      </div>
                      <Card.Img
                        className="img_all_caract"
                        src={product.images[5]?.url}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          )}
          <div key={product.id}>
            <div className="img_gal_descr_all">
              <div>
                <h3 className="img_gal_descr_titulo mb">{product.name}</h3>
              </div>
              <div className="img_gal_descr_sobre">
                <p className="img_gal_text">{product.description}</p>
              </div>
            </div>
            <div className="img_gal_descr_all">
              <div className="img_especif_tit">
                <h3 className="img_gal_descr_titulo">O que o carro oferece?</h3>
              </div>
              <div className="img_especif_all">
                {product.characteristics.map((charact) => (
                  <h6 className="img_gal_text" key={charact.name}>
                    <i className={charact.icon} aria-hidden="true"></i> {charact.name}: {charact.description}
                  </h6>
                ))}
              </div>
              <div className="share">
                <h5>Compartilhe nas redes sociais</h5>
                <FacebookShareButton className="icon_share" url={shareUrl} quote={"Alugue hoje seu carro dos sonhos na Autolux"}>
                  <FacebookIcon size={30} round={true} />
                </FacebookShareButton>
                <WhatsappShareButton className="icon_share" url={shareUrl} title={"Autolux - alugue seu carro dos sonhos"}>
                  <WhatsappIcon size={30} round={true} />
                </WhatsappShareButton>
                <TwitterShareButton className="icon_share" url={shareUrl} title={"Autolux - alugue seu carro dos sonhos"}>
                  <TwitterIcon size={30} round={true} />
                </TwitterShareButton>
                <EmailShareButton className="icon_share" url={shareUrl} subject={"Autolux - alugue seu carro dos sonhos"}
                  body={"A AUTOLUX conta com uma frota variada e diferenciada para nossos clientes pois antes da reserva, todos os carros são submetidos a um rigoroso critério de seleção e revisão. Possuímos veículos sem sinais de uso ou desgaste, todos eles em excelente condição, portanto uma grande oportunidade de viver a experiência e o privilégio de dirigir os melhores carros de luxo. Para reservar seu próximo carro de luxo com conforto e segurança, conte conosco!"}
                  separator={" "}
                >
                  <EmailIcon size={30} round={true} />
                </EmailShareButton>
              </div>
            </div>
          </div>
        </div>
      ))}
    </>
  );
}
