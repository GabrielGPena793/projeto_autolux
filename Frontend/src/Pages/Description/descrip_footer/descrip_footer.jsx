import React from "react";
import "./descrip_footer.css";

export default function Descrip_footer() {
  return (
    <div className="descrip_footer_all">
      <h3 className="descrip_footer_header_title">Política de locação</h3>
      <div className="descrip_footer_box">
        <div>
          <h4 className="descrip_footer_box_Tit">Regras da locadora</h4>
          <h6 className="descrip_footer_box_tex">Para retirar o veículo, você precisa ter no mínimo 18 anos de idade, sem tempo mínimo de habilitação e ter cartão crédito em seu nome aprovado pela Autolux. Com cartão de crédito, a aprovação poderá ser imediata.
            <br />
            A diária do carro é de 24 horas, com até 1 hora de tolerância para devolução. A partir da 25ª hora, incidirá cobrança de hora extra (1/5 do valor da diária para cada hora extra), sendo cobrada, inclusive, a hora de tolerância.</h6>
        </div>
        <div>
          <h4 className="descrip_footer_box_Tit">Seguro obrigatório</h4>
          <h6 className="descrip_footer_box_tex">
            A proteção do carro já está incluída no seu plano de locação. A cobertura para danos a terceiros é opcional e pode ser adquirida no momento da reserva ou na abertura do contrato por R$50,30 ao dia.
            <br />
            Atenção: a cobertura é passível de perda em casos de embriaguez e condutor não previsto na contratação do seguro.
          </h6>

        </div>
        <div>
          <h4 className="descrip_footer_box_Tit">Política de cancelamento</h4>
          <h6 className="descrip_footer_box_tex">
            Cancelamento online de forma gratuita até 48 horas antes do início da locação. Em reservas de aluguel de carros canceladas dentro do prazo de 48 horas do início da retirada, será cobrada uma tarifa equivalente a um aluguel de três dias. A tarifa de cancelamento nunca será maior do que o custo do aluguel do carro.
          </h6>
        </div>
      </div>
    </div>
  );
}
