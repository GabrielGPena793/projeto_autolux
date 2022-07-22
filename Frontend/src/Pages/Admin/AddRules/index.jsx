import React from 'react'
import { Container, Row } from 'react-bootstrap'
import './style.css'

function AddRules() {
  return (
    <div className="addRules">
      <Container className="w-100 mt-3 p-4">
        <h1 className="rules_h1">Política de locação</h1>
        <Row className="rules_content ">
          <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4 mb-3 p-3">
            <h3 className="rules_h3">Regras de locação</h3>
            <p className="rules_text">
              Para retirar o veículo, você precisa ter no mínimo 18 anos de idade, sem tempo mínimo de habilitação e ter cartão crédito em seu nome aprovado pela Autolux. Com cartão de crédito, a aprovação poderá ser imediata.
              <br />
              A diária do carro é de 24 horas, com até 1 hora de tolerância para devolução. A partir da 25ª hora, incidirá cobrança de hora extra (1/5 do valor da diária para cada hora extra), sendo cobrada, inclusive, a hora de tolerância.
            </p>
          </div>

          <div className='col-sm-12 col-md-12 col-lg-4 col-xl-4 mb-3 p-3'>
            <h3 className="rules_h3">Seguro obrigatório</h3>
            <p className="rules_text">
              A proteção do carro já está incluída no seu plano de locação. A cobertura para danos a terceiros é opcional e pode ser adquirida no momento da reserva ou na abertura do contrato por R$50,30 ao dia.
              <br />
              Atenção: a cobertura é passível de perda em casos de embriaguez e condutor não previsto na contratação do seguro.
            </p>
          </div>

          <div className='col-sm-12 col-md-12 col-lg-4 col-xl-4 mb-3 p-3'>
            <h3 className="rules_h3">Política de cancelamento</h3>
            <p className="rules_text">
              Cancelamento online de forma gratuita até 48 horas antes do início da locação. Em reservas de aluguel de carros canceladas dentro do prazo de 48 horas do início da retirada, será cobrada uma tarifa equivalente a um aluguel de três dias. A tarifa de cancelamento nunca será maior do que o custo do aluguel do carro.
            </p>
          </div>
        </Row >
      </Container >
    </div >
  )
}

export default AddRules;
