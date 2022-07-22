import React, { useState, useEffect } from 'react'
import { Container, Row, Form } from 'react-bootstrap'
import './style.css'

function AddProduct(props) {


  const [cities, setCities] = useState([]);
  const [categories, setCategories] = useState([]);

  async function getCity() {
    try {
      const data = await fetch(`https://back-end-booking.herokuapp.com/v1/cities`)
      const response = await data.json();
      setCities(response)
    } catch (error) {
      alert("Erro de comunicação com o servidor", error)
    }
  }

  async function getCategories() {
    try {
      const data = await fetch(`https://back-end-booking.herokuapp.com/v1/categories`)
      const response = await data.json();
      setCategories(response)
    } catch (error) {
      alert("Erro de comunicação com o servidor", error)
    }
  }


  useEffect(() => {
    getCity()
    getCategories()
  }, []);


  return (

    <div>
      <Container className="w-100 mt-3">
        <Form>
          <Row>
            <div className="col-sm-12 col-md-6 mb-3">
              <label>Nome</label>
              <input
                autoFocus
                className="input_admin"
                placeholder="Nome"
                value={props.name}
                onChange={e => props.setName(e.target.value)}
              >
              </input>
            </div >

            <div className='col-sm-12 col-md-6 mb-3'>
              <label>Categoria</label>
              <select
                className="input_admin"
                value={props.category}
                onChange={e => props.setCategory(e.target.value)}
              >
                <option
                  value="0"
                >
                  Selecione a categoria
                </option>
                {categories.map(category => (
                  <option
                    className="select_admin"
                    value={props.category.id}
                  >
                    {category.qualification}
                  </option>
                ))}
              </select>
            </div>
          </Row>

          <Row>
            <div className='col-sm-12 col-md-6 mb-3'>
              <label>Descrição do carro</label>
              <textarea
                className="input_admin"
                rows="3"
                placeholder="Descrição do carro"
                value={props.description}
                onChange={e => props.setDescription(e.target.value)}
              />
            </div>
            <div className='col-sm-12 col-md-6 mb-3'>
              <label>Cidade</label>
              <select
                className="input_admin"
                value={props.city}
                onChange={e => props.setCity(e.target.value)}
              >
                <option
                  value="0"
                >
                  Selecione a cidade
                </option>
                {cities.map(city => (
                  <option
                    className="sidebar-option"
                    value={props.city.id}
                  >
                    {city.name}
                  </option>
                ))}
              </select>
            </div>
          </Row>
        </Form>
      </Container >
    </div >
  )
}

export default AddProduct;
