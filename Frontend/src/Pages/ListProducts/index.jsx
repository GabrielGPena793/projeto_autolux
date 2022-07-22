import React, { useEffect, useState } from 'react'
import Loading from '../../components/Loading';
import { Container, Accordion, Navbar, Row } from 'react-bootstrap'
import { BsChevronLeft, BsXSquare } from 'react-icons/bs';
import { Link } from 'react-router-dom';
import { FaChevronLeft, FaChevronRight } from "react-icons/fa"
import { ButtonPadrao } from '../../components/ButtonPadrao';
import Modal from 'react-modal';
import AddAttribute from '../Admin/AddAttribute';
import AddProduct from '../Admin/AddProduct';
import Swal from 'sweetalert2';
import { CaretLeft } from "phosphor-react";

import './style.css'


const customStyles = {
  content: {
    maxWidth: '42rem',
    width: '90vw',
    height: '80vh',
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
    backgroundColor: 'var(--primary-color)',
    overflowY: 'scroll',
  },
  overlay: {
    background: 'rgba(27, 27, 28, 0.9)',
    zIndex: '9999',
    alignItems: 'center'
  }

};

Modal.setAppElement('#root')

function ListProducts() {

  const [id, setId] = useState(0);
  const [name, setName] = useState("");
  const [category, setCategory] = useState(0);
  const [city, setCity] = useState(0);
  const [description, setDescription] = useState("");

  const [charact, setCharact] = useState(0);
  const [descriptionAttr, setDescriptionAttr] = useState("");

  const [attrList, setAttrList] = useState([])
  const [imageList, setImageList] = useState([]);

  const [products, setProducts] = useState([]);
  const [pageNumber, setPageNumber] = useState(0)
  const [totalPages, setTotalPages] = useState(0)
  const [lastPage, setLastPage] = useState(false)
  const [firstPage, setFirstPage] = useState(false)
  const [loading, setLoading] = useState(true)

  const [modalIsOpen, setModalIsOpen] = useState(false);


  let subtitle;


  function afterOpenModal() {
    subtitle.style.color = '#fff';
  }

  function closeModal() {
    setModalIsOpen(false);
  }

  function fillStates(product) {
    setLoading(false)
    setModalIsOpen(true)
    setId(product.id)
    setName(product.name)
    setCategory(product.category.qualification)
    setCity(product.city.name)
    setDescription(product.description)
    setAttrList(product.characteristics)
    setImageList(product.images)
  }


  async function getProducts() {
    try {
      const data = await fetch(`https://back-end-booking.herokuapp.com/v1/products?page=${pageNumber}`)
      const { content, totalPages, last, first } = await data.json()
      setProducts(content)
      setTotalPages(totalPages)
      setLastPage(last)
      setFirstPage(first)
      setLoading(false)
    } catch (error) {
      Swal.fire({
        title: "Erro de comunicação com o servidor",
        text: "tente novamente",
        icon: "error",
        confirmButtonText: "Voltar ao formulário",
      })
    }
  }

  async function editProduct(e) {
    e.preventDefault()

    try {
      const body = {
        id,
        name,
        description,
        category,
        city,
        characteristics: attrList,
        imageDTOS: imageList
      }
      await fetch('https://back-end-booking.herokuapp.com/v1/products/', {
        method: 'PUT',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem('@token_user')
        },
        body: JSON.stringify(body)
      })

      Swal.fire({
        title: "Produto alterado com sucesso",
        icon: "success",
        confirmButtonText: "Ok"
      })
      getProducts()
      setModalIsOpen(false)
      setLastPage(lastPage)
    } catch (error) {
      Swal.fire({
        title: "Erro ao alterar o produto",
        text: "tente novamente",
        icon: "error",
        confirmButtonText: "Voltar ao formulário",
      })
    }

  }

  async function deleteProduct(id) {
    try {
      await fetch('https://back-end-booking.herokuapp.com/v1/products/' + id, {
        method: 'DELETE',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem('@token_user')
        },
      })
    } catch (error) {
    }
  }

  function deleteConfirmation(id) {
    Swal.fire({
      title: 'Deseja deletar o produto?',
      text: "Não é possível reverter a ação.",
      icon: "warning",
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Sim, deletar',
    }).then((result) => {
      if (result.isConfirmed) {
        deleteProduct(id)

        Swal.fire('Produto deletado', '', 'success')
        setTimeout(() => { getProducts() }, 1000)
      } else if (result.isDenied) {
        Swal.fire('Cancelado', '', 'info')
      }
    })
  }

  useEffect(() => {
    getProducts()
  }, [pageNumber])

  if (loading === true) {
    return <Loading />
  }

  return (
    <div>
      <div className="description_header_all">
        <Navbar className="Header_description_All">
          <Navbar.Brand >
            Administrativo
          </Navbar.Brand>
          <Link className="link-to" to="/admin" alt="Voltar" title="Voltar">
            <div >
              <CaretLeft size={50} weight="bold" color="var(--dark-blue)" />
            </div>
          </Link>
        </Navbar>
      </div>



      <Container>
        <div className="list_container">
          <h1 className="list_h1">Produtos cadastrados</h1>

          <div className="list_button">
            <button
              className="list_icon_chevron"
              onClick={() => setPageNumber(pageNumber - 1)}
              disabled={firstPage}
            >
              <FaChevronLeft />
            </button>
            <span>{pageNumber + 1}  -  {totalPages}</span>
            <button
              className="list_icon_chevron"
              onClick={() => setPageNumber(pageNumber + 1)}
              disabled={lastPage}>
              <FaChevronRight />
            </button>
          </div>

          {products.map(product => (
            <div key={product.id}>
              <Accordion defaultActiveKey={product.id} className="accordion_list" flush>
                <Accordion.Item className="accordion_item">
                  <Accordion.Header className="accordion_header">
                    <h5>{product.id} - Nome: {product.name}</h5>
                  </Accordion.Header>
                  <Accordion.Body>
                    <Row>
                      <div className="col-sm-12 col-md-6 col-lg-3 accordion_list">
                        <img className="accordion_thumbnail" src={product.images[0]?.url} alt={product.name} />
                        <p>Categoria: {product.category.qualification}</p>
                        <p>Cidade: {product.city.name}</p>

                        <h5 className="accordion_h5">Atributos</h5>
                        {product.characteristics.map((characteristics) => (
                          <>
                            <p>{characteristics.name}: {characteristics.description}</p>

                          </>
                        ))}
                      </div>

                      <div className="col-sm-12 col-md-6 col-lg-3 accordion_list_charact">
                        <h5 className="accordion_h5">Descrição do carro</h5>
                        <p className="accordion_description">{product.description}</p>
                      </div>

                      <div className="col-sm-12 col-md-12 col-lg-6 accordion_list_image">
                        <h5 className="accordion_h5">Endereço das imagens | URL</h5>

                        {product.images.map((image) => (
                          <div key={image.id}>
                            <p>Id. {image.id}: {image.url}</p>
                          </div>
                        ))}
                      </div>

                      <div className="accordion_btn_container">

                        <ButtonPadrao
                          text="Editar cadastro"
                          className="accordion_btn"
                          handleClick={() => fillStates(product)}
                        />
                        <ButtonPadrao
                          text="Deletar cadastro"
                          className="accordion_btn"
                          handleClick={() => deleteConfirmation(product.id)}
                        />
                      </div>
                    </Row>

                  </Accordion.Body>
                </Accordion.Item>
              </Accordion>
            </div>
          ))
          }
        </div>
      </Container>

      <Modal
        isOpen={modalIsOpen}
        onAfterOpen={afterOpenModal}
        onRequestClose={closeModal}
        style={customStyles}
        contentLabel="Example Modal"
      >
        <div className="modal_header">
          <h4 ref={(_subtitle) => (subtitle = _subtitle)}>Editar produto</h4>
          <BsXSquare className="modal_btn" title='Fechar' onClick={() => closeModal()} />
        </div>

        <div>
          <AddProduct
            name={name} setName={setName}
            category={category} setCategory={setCategory}
            city={city} setCity={setCity}
            description={description} setDescription={setDescription}
          />

          <AddAttribute
            attrList={attrList} setAttrList={setAttrList}

            charact={charact} setCharact={setCharact}
            descriptionAttr={descriptionAttr} setDescriptionAttr={setDescriptionAttr}

            imageList={imageList} setImageList={setImageList}
          />

          <ButtonPadrao
            text="Salvar alterações"
            handleClick={(e) => editProduct(e)}
          />
        </div>
      </Modal>

    </div>
  )
}

export default ListProducts
