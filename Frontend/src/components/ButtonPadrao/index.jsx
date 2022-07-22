import "./styles.css";

export function ButtonPadrao({ handleClick, text, disabled }) {
  return (
    <button
      disabled={disabled || false}
      onClick={handleClick}
      className="btn-search form-control shadow-none button_padrao"
    >
      {text}
    </button>
  );
}
