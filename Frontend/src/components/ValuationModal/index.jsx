import { useState } from "react";
import Modal from "react-modal";
import api from "../../Pages/Login/servicesLogin/api";
import { ButtonPadrao } from "../ButtonPadrao";
import { SuccessValidationIcon } from "../Icon/SuccessValidationIcon";
import { ValidationIcon } from "../Icon/ValidationIcon";
import { StarRating } from "../StarRating";
import "./styles.css";

Modal.setAppElement("#root");

export function ValuationModal({
  modalIsOpen,
  onRequestClose,
  userEmail,
  productId,
  getBookingsByUserId,
}) {
  const [rating, setRating] = useState(null);
  const [isSuccess, setIsSuccess] = useState(false);

  function handleSelectRating(value) {
    setRating(value);
  }

  function handleReset() {
    setRating(null);
    setIsSuccess(false);
    onRequestClose();
  }

  async function sendingValidation() {
    try {
      await api.put(
        "/v1/products/score",
        {
          userEmail,
          productId,
          score: rating,
        },
        {
          headers: {
            Accept: "*/*",
            "Content-Type": "application/json",
            Authorization: localStorage.getItem("@token_user"),
          },
        }
      );
      getBookingsByUserId();
      setIsSuccess(true);
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <Modal
      isOpen={modalIsOpen}
      onRequestClose={handleReset}
      className="react-modal-content"
      overlayClassName="react-modal-overlay"
    >
      {isSuccess ? (
        <>
          <div className="container_modal_validation">
            <h2 className="title_valuation">Obrigado pela sua Avaliação</h2>
            <SuccessValidationIcon className="success_validation_icon"/>
          </div>
          <ButtonPadrao
            text="Voltar"
            handleClick={handleReset}
          />
        </>
      ) : (
        <>
          <div className="container_modal_validation">
            <h2 className="title_valuation">Avalie o produto</h2>
            <ValidationIcon />
          </div>
          <StarRating rating={rating} onSelectRating={handleSelectRating} />
          <ButtonPadrao
            text={rating === null ? "Selecione uma avaliação" : "Avaliar"}
            disabled={rating === null ? true : false}
            handleClick={sendingValidation}
          />
        </>
      )}
    </Modal>
  );
}
