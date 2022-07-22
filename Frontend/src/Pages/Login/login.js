import React, { useState } from 'react'
import * as yup from 'yup';
import { useFormik } from 'formik';
import { Link, useNavigate } from 'react-router-dom';
import Textfield from '@material-ui/core/TextField';
import Button from '@mui/material/Button';
import './login.css';
import axios from './servicesLogin/api';
import useAuth from "../Login/hooks/useAuth";
import Spinner from 'react-bootstrap/Spinner'

const validationSchema = yup.object().shape({
  email: yup.string().email('Digite um email válido').required('*Campo obrigatório'),
  password: yup.string().min(6, 'A senha precisa ter 6 ou mais caracteres').required('*Campo obrigatório'),
});


export default function Login() {

  const { setIsLogin, setUser, logginRedirect, productId, setProductId, setLogginRedirect} = useAuth();
  let navigate = useNavigate();

  const [alertError, setAlertError] = useState([]);

  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    onSubmit: (values) => {
      document.getElementsByClassName('loading')[0].style.display = 'flex';
      axios.post('/login', {
        email: values.email,
        password: values.password
      }, { timeout: 5000 })
        .then(function (response) {
          localStorage.setItem("@token_user", response.data.token);
          localStorage.setItem("@user_id", response.data.id);
          localStorage.setItem("@email", values.email)
          setUser(response.data);
        }).then(() => {
          if (localStorage.getItem("@token_user")) {
            setIsLogin(true)
            if(productId){
            console.log(productId)
              navigate(`/description/${productId}`)
              setProductId(null)
            }else {
              navigate(`../`, { replace: true })
            }
          }
        })
        .catch(function (error) {
          document.getElementsByClassName('loading')[0].style.display = 'none';
          if (error.code === 'ECONNABORTED') {
            setAlertError(["Não foi possível conectar ao servidor, tente novamente mais tarde"]);
          }
          console.log(error.response.status);
          setAlertError([error.response.data.message])
        });
    },

    validationSchema: validationSchema
  });


  return (
    <div className="container container-login">
      <div className="loading hide text-light">
        <p>Aguarde</p>
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      </div>
      <div className="card-login">
      <form className="login-form" onSubmit={formik.handleSubmit}>
        <div className=""><h1 className="login-title">Iniciar Sessão</h1></div>
        {logginRedirect ? <p className="text-login">Você precisa estar logado para fazer uma reserva</p> : ""}
        {alertError.length > 0 && <div className="alert-error">{alertError}</div>}
        <Textfield
          id="email"
          name="email"
          label="Email"
          margin="normal"
          value={formik.values.email}
          onChange={formik.handleChange}
          error={formik.touched.email && Boolean(formik.errors.email)}
          onBlur={formik.handleBlur}
          helperText={formik.touched.email && formik.errors.email}
        />

        <Textfield
          id="password"
          type="password"
          name="password"
          label="Senha"
          margin="normal"
          value={formik.values.password}
          onChange={formik.handleChange}
          error={formik.touched.password && Boolean(formik.errors.password)}
          onBlur={formik.handleBlur}
          helperText={formik.touched.password && formik.errors.password}
        />
        <Button onClick={() => setLogginRedirect(false)} type="submit" className="btn-login" variant="contained">Enviar</Button>
        <p className="link-register">Ainda não tem conta? <Link to="/Register">Registre-se</Link></p>
      </form>        
      <div className="car-image">
        <img src="https://i.postimg.cc/4xTbr3L3/jan-vlacuha-U4-Iao-KF5aj4-unsplash.jpg" alt="car" />        
      </div>       
      </div>   
    </div>
  );
}
