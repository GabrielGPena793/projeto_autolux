import React, { useState } from 'react'
import { Container, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom'
import { BsChevronLeft } from 'react-icons/bs'
import AddAttribute from './AddAttribute';
import AddProduct from './AddProduct';
import AddRules from './AddRules';
import { ButtonPadrao } from '../../components/ButtonPadrao';
import Swal from 'sweetalert2';
import api from '../Login/servicesLogin/api';
import { CaretLeft } from "phosphor-react";

import './style.css'

function Admin(props) {

  const [name, setName] = useState("");
  const [category, setCategory] = useState(0);
  const [city, setCity] = useState(0);
  const [description, setDescription] = useState("");

  const [attrList, setAttrList] = useState([
    { name: '', description: '' }
  ])
  const [imageList, setImageList] = useState([
    { url: '' }
  ]);

  function moveScroll() {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  }

  function clearInput() {
    try {
      Swal.fire({
        title: "Campos limpos",
        icon: "success",
        confirmButtonText: "Voltar ao formulário",
      })

      setName("")
      setCategory("0")
      setCity("0")
      setDescription("")
      setAttrList([{ description: '' }])
      setImageList([{ url: '' }])
    } catch (error) {
      Swal.fire({
        position: 'center',
        title: "Erro",
        text: "tente novamente",
        icon: "error",
        confirmButtonText: "Voltar ao formulário",
      })
    }

  }

  async function newProduct() {
    if (!name || !category || !city || !description || !attrList || !imageList) {
      Swal.fire({
        position: 'center',
        title: "Preencha todos os campos",
        text: "para cadastrar o produto",
        icon: "warning",
        confirmButtonText: "Voltar ao formulário",
      })
    } else {
      const body = {
        name,
        description,
        category,
        city,
        characteristics: attrList,
        imageDTOS: imageList
      }
      try {
        await api.post('/v1/products',
          body, {
          headers: {
            Accept: '*/*',
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem('@token_user')
          },
          body: JSON.stringify(body)
        })
        clearInput()

        Swal.fire({
          position: 'center',
          title: "Produto cadastrado com sucesso",
          icon: "success",
          confirmButtonText: "Voltar ao formulário",
        })
      } catch (error) {
        Swal.fire({
          title: "Erro ao cadastrar o produto",
          text: "tente novamente",
          icon: "error",
          confirmButtonText: "Voltar ao formulário",
        })
      }
    }
  }

  return (
    <>
      <div className="description_header_all">
        <Navbar className="Header_description_All">
          <Navbar.Brand >
            Administrativo - cadastro
          </Navbar.Brand>
          <Link className="link-to" to="/" alt="Voltar" title="Voltar">
            <div >
              <CaretLeft size={50} weight="bold" color="var(--dark-blue)" />
            </div>
          </Link>
        </Navbar>
      </div>

      <div className="admin_container">
        <Container>
          <div className="admin_header">
            <h1 className="admin_h1">Cadastrar novo produto</h1>

            <Link to="/listProducts" title="Lista de todos os produtos">
              <ButtonPadrao
                className="admin_header_btn"
                text="Acessar todos os produtos"
              />
            </Link>
          </div>

          <div className="admin_add">
            <AddProduct
              name={name} setName={setName}
              category={category} setCategory={setCategory}
              city={city} setCity={setCity}
              description={description} setDescription={setDescription}
            />
            <AddAttribute
              attrList={attrList} setAttrList={setAttrList}
              imageList={imageList} setImageList={setImageList}
            />

            <AddRules />
            <div className="admin_btn_container">

              <ButtonPadrao
                text="Limpar"
                className="admin_btn"
                handleClick={clearInput}
              />

              <ButtonPadrao
                text="Cadastrar"
                className="admin_btn"
                handleClick={() => newProduct()}
              />

            </div>
          </div>
        </Container>
      </div >
    </>
  )
}

export default Admin;

