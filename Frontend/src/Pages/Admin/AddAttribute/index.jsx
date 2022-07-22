import React, { useState, useEffect } from 'react'
import { Container, Form, Row } from 'react-bootstrap'
import { BsPlusSquareFill, BsXSquare } from 'react-icons/bs'

import './style.css'

function AddAttribute(props) {

  const [characte, setCharacte] = useState([]);


  async function getCharact() {
    try {
      const data = await fetch(`https://back-end-booking.herokuapp.com/v1/characteristics`)
      const response = await data.json();
      setCharacte(response)
    } catch (error) {
      alert("Erro de comunicação com o servidor", error)
    }
  }

  useEffect(() => {
    getCharact()
  }, [])

  const handleAddImage = () => {
    props.setImageList([...props.imageList, { url: '' }])
  }

  const handleRemoveImage = (index) => {
    const list = [...props.imageList]
    list.splice(index, 1)
    props.setImageList(list)
  }

  const handleImageChange = (e, index) => {
    const { value } = e.target
    const list = [...props.imageList]
    list[index] = { url: value }
    props.setImageList(list)
  }

  const handleAddAttr = () => {
    props.setAttrList([...props.attrList, { name: '', description: '' }])
  }

  const handleRemoveAttr = (index) => {
    const list = [...props.attrList]
    list.splice(index, 1)
    props.setAttrList(list)
  }

  const handleAttrChange = (e, index) => {
    const { value } = e.target
    const list = [...props.attrList]
    list[index] = { ...list[index], description: value }
    props.setAttrList(list)
  }

  const handleSelectChange = (e, index) => {
    const { value } = e.target
    const list = [...props.attrList]
    list[index] = { ...list[index], name: value }
    props.setAttrList(list)
  }


  return (
    <div>
      <Container className="w-100 h-100">
        <h3 className="admin-h3">Adicionar atributos</h3>
        <Form className="attribute p-3">
          {props.attrList.map((singleAttr, index) => (
            <div key={index}>
              <Row>
                <div className="col-sm-12 col-md-3 col-lg-3 mb-3">
                  <label>Atributo</label>

                  <select
                    className="input_admin"
                    value={singleAttr.charact}
                    onChange={e => handleSelectChange(e, index)}
                  >
                    <option
                      value={singleAttr.name ? singleAttr.name : "0"}
                    >
                      {singleAttr.name ? singleAttr.name : "Selecione o atributo"}
                    </option>

                    {characte.map(charact => (
                      <option
                        value={charact.name}
                      >
                        {charact.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div className='col-sm-11 col-md-9 col-lg-9 mb-2'>

                  <label className="attribute_label">Descrição do atributo</label>
                  <input
                    className="attribute_input_descrip"
                    placeholder="Descrição"
                    value={singleAttr.description}
                    onChange={e => handleAttrChange(e, index)}
                  />
                  {props.attrList.length > 1 &&
                    <BsXSquare
                      className="attribute_btn"
                      title="Fechar campo"
                      onClick={() => handleRemoveAttr(index)}
                    />
                  }

                  {props.attrList.length - 1 === index && props.attrList.length < 7 &&
                    <BsPlusSquareFill
                      className="attribute_btn"
                      title="Incluir campo"
                      onClick={handleAddAttr}
                    />
                  }
                </div>
              </Row>
            </div>
          ))}

        </Form>
        <h3 className="admin-h3">Carregar imagens</h3>
        <Form className="attribute p-3 mt-3">
          <Row className='mb-4'>
            <div className="attribute_div col-12 ml-0">
              <label>Inserir o endereço da imagem</label>
              {props.imageList.map((singleImage, index) => (
                <div key={index}>
                  <input
                    className="attribute_input"
                    type="url"
                    placeholder='Inserir endereço da imagem https://exemplo.com/imagem.jpg'
                    value={singleImage.url}
                    onChange={e => handleImageChange(e, index)}
                  />
                  {props.imageList.length > 1 &&
                    <BsXSquare
                      className="attribute_btn"
                      title="Fechar campo"
                      onClick={() => handleRemoveImage(index)}
                    />
                  }

                  {props.imageList.length - 1 === index && props.imageList.length < 7 &&
                    <BsPlusSquareFill
                      className="attribute_btn"
                      title="Incluir campo"
                      onClick={handleAddImage}
                    />
                  }
                </div>
              ))}
            </div>
          </Row>
        </Form>
      </Container>
    </div >
  )
}

export default AddAttribute
