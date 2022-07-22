import React, { useState, useEffect } from 'react'
import "../MyReservation/style.css"
import 'bootstrap/dist/css/bootstrap.min.css'
import { Navbar } from 'react-bootstrap';
import { Link, useNavigate } from "react-router-dom";
import useAuth from "../Login/hooks/useAuth";
import { BsFillSuitHeartFill, BsFillHouseFill, BsEnvelopeFill } from "react-icons/bs";
import axios from "../Login/servicesLogin/api";
import { CaretLeft } from 'phosphor-react';
import { StarScore } from '../../components/StartScore';
import { ButtonPadrao } from '../../components/ButtonPadrao';
import { ValuationModal } from '../../components/ValuationModal';
import { ReservationNotFound } from '../../components/Icon/ReservationNotFound';



function gettingFirstLetters(user) {
  if (typeof user !== "undefined") {
    return user.split(' ').map(word => word[0]).join('');
  } else {
    return "";
  }
}


export default function MyReservation() {

  const [userBookings, setUserBookings] = useState([]);
  const [modalIsOpen, setIsOpen] = useState(false);
  const [productId, setProductId] = useState(null)
  const navigation = useNavigate()

  const { user } = useAuth();

  async function getBookingsByUserId() {
    if(user == null) {
      navigation("/")
    }

    let bookings = await axios.get(`v1/bookings/user/${localStorage.getItem('@user_id')}`)
    setUserBookings(bookings.data);
  }

  useEffect(() => {
    getBookingsByUserId();
  }, [user])

  let convertDate = (date) => {
    let arrayDate = date.split("-");
    return `${arrayDate[2]}/${arrayDate[1]}/${arrayDate[0]}`
  };


  //functions modal
  function openModal(id) {
    setProductId(id);
    setIsOpen(true);
  }

  function closeModal() {
    setIsOpen(false);
  }

  function redirectToCategories(){
    navigation("/category/all")
  }

  return (
    <>
      <div className="description_header_all">
        <Navbar className="Header_description_All">
          <Navbar.Brand >
            <h4 title="Categoria" alt="Categoria">
              Minhas Reservas
            </h4>
          </Navbar.Brand>
          <Link className="link-to" to="/" alt="Voltar" title="Voltar">
            <CaretLeft size={50} weight="bold" color="var(--dark-blue)" />
          </Link>
        </Navbar>
      </div>
      <div className="container container-my-reservation">
        <div className="row gap-5 side-list">
          <div className="col-3 shadow p-3 mb-5 bg-body rounded mt-3 link-list">
            <div className="initials">
              <div className="circle-reservation">
                <span>{gettingFirstLetters(user?.login)}</span>
              </div>
            </div>
            <div className="user-reservation mt-3">
              <p className="hello-reservation">Olá,</p>
              <p className="name-reservation">{user?.login}</p>
            </div>
            <ul className="list-group list-group-flush list-link mt-4">
              <li className="list-group-item"><Link className="list-item" to="/"><BsFillSuitHeartFill /> Favoritos</Link></li>
              <li className="list-group-item"><Link className="list-item" to="/"><BsFillHouseFill /> Home</Link></li>
              <li className="list-group-item"><Link className="list-item" to="/"><BsEnvelopeFill /> Contato</Link></li>
            </ul>
          </div>
          <div className="col-8 shadow p-3 mb-5 bg-body rounded mt-3 reservation-list">
            {userBookings.length === 0 ?

              <div className='container_not_found_my_reservation'>
                <ReservationNotFound />
                <h1>Você não tem reservas</h1>
                <p>Realize alguma reserva em nosso site e visualize elas no seu perfil</p>
                <ButtonPadrao handleClick={redirectToCategories} text="Ver produtos" />
              </div>
              :
              userBookings.map(booking => (
                <div key={booking.id} className="card-reservation">
                  <div className="car-image-reservation">
                    <p className="car-category-reservation">{booking.product.category.qualification}</p>
                    <p className="product-title">{booking.product.name}</p>
                    <StarScore score={booking.product.score} />
                    <img className="img-reservation" src={booking.product.images[2].url} alt={booking.product.name} />
                  </div>
                  <div className="date-hour-reservation">
                    <p className="reservation-city">{booking.product.city.name}, {booking.product.city.country}</p>
                    <p><strong>Id</strong>: {booking.id}</p>
                    <p className="hour-start"><span>Horário de retirada:</span>{booking.startTime}</p>
                    <p className="date-start"><span>Data de retirada:</span>{convertDate(booking.startDate)}</p>
                    <p className="date-final"><span>Data de devolução:</span>{convertDate(booking.endDate)}</p>
                    <ButtonPadrao handleClick={() => openModal(booking.product.id)} text="Avaliar Produto" type="button" />
                  </div>
                </div>
              ))
            }


            <ValuationModal
              onRequestClose={closeModal}
              modalIsOpen={modalIsOpen}
              userEmail={user?.email}
              productId={productId}
              getBookingsByUserId={getBookingsByUserId}
            />
          </div>
        </div>
      </div>
    </>
  )
}
