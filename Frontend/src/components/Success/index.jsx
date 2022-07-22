import './style.css'
import { Link } from 'react-router-dom';
import IconSuccess from '../Icon/IconSuccess';

function Success({ mensagem }) {

  return (
    <>
      <div className="success">
        <IconSuccess />
        <h1 className="success-h1">Muito Obrigado!</h1>
        <p className="success-p">{mensagem}</p>
        <Link to="/" className="success-btn">Ok</Link>
      </div>
    </>
  )
}

export default Success;
