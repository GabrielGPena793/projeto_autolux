import React, { useEffect, useState } from "react";
import "./DateFree.css";
import { Calendar } from "react-multi-date-picker";
import useAuth from "../../Login/hooks/useAuth";
import { Link } from "react-router-dom";
import { ButtonPadrao } from "../../../components/ButtonPadrao";
import { format } from "date-fns/esm";
import { api } from "../../../Service/axios";

const weekDays = ["Dom", "Seg", "Ter", "Qua", "Quin", "Sex", "Sab"];
const months = [
  "Jan",
  "Fev",
  "Mar",
  "Abr",
  "Mai",
  "Jun",
  "Jul",
  "Ago",
  "Set",
  "Out",
  "Nov",
  "Dez",
];

export function DateFree({ numberOfMonths, productId }) {
  const { isLogin, handleRedirect } = useAuth();
  const [carReserv, setCarReserv] = useState([]);
  const [datesBooked, setDatesBooked] = useState({});

  function handleChange(e) {
    //setando data no localStorage
    if (e[0] && e[1]) {
      localStorage.setItem(
        "@inicial_date",
        JSON.stringify({
          inputDate: format(new Date(e[0]), "dd/MM/yyyy"),
          calendarDate: format(new Date(e[0]), "yyyy-MM-dd"),
        })
      );

      localStorage.setItem(
        "@final_date",
        JSON.stringify({
          inputDate: format(new Date(e[1]), "dd/MM/yyyy"),
          calendarDate: format(new Date(e[1]), "yyyy-MM-dd"),
        })
      );
    }
  }

  var getDaysArray = function (s, e) {
    let dateInitial = new Date(s);
    let dateFinal = new Date(e);
    dateInitial.setDate(dateInitial.getDate() + 1);
    dateFinal.setDate(dateFinal.getDate() + 1);
    
    for (
      var a = {}, d = dateInitial;
      d <= new Date(dateFinal);
      d.setDate(d.getDate() + 1)
    ) {
      let currentDate = format(new Date(d), "yyyy-MM-dd").split("-");
      let month =
        currentDate[1][0] === "0" ? currentDate[1].slice(-1) : currentDate[1];
      let day = Number(currentDate[2]);

      if (Object.keys(a).includes(month)) {
        if (!a[month].includes(day)) {
          a[month].push(day);
        }
      } else {
        a[month] = [day];
      }
    }
    return a;
  };

  async function getByCarReserv() {
    try{
      const response = await api.get(`v1/bookings/product/${productId}`);
      setCarReserv(response.data);
    }catch(error){
      console.log("Não tem reservas para esse carro")
    }
  }

  function handleDate(carReserv) {
    let datesBookedObject = {};
    carReserv.map((booking) => {
      let arrayDates = getDaysArray(booking.startDate, booking.endDate);
      for (const key in arrayDates) {
        if (Object.keys(datesBookedObject).includes(key)) {
          datesBookedObject[key] = [...datesBookedObject[key], ...arrayDates[key]];
        } else {
          datesBookedObject[key] = [...arrayDates[key]];
        }
      }
      return "";
    });
    setDatesBooked(datesBookedObject)
  }

  useEffect(() => {
    getByCarReserv();
  }, []);

  useEffect(() => {
    handleDate(carReserv);
  },[carReserv])

  return (
    <div className="available-date">
      <h3>Datas disponíveis</h3>
      <div className="card-datepicker">
        <div className="date_free_calendar">
          <Calendar
            mode="range"
            numberOfMonths={numberOfMonths}
            range
            value={
              localStorage.getItem("@inicial_date") === ""
                ? []
                : [
                    JSON.parse(localStorage.getItem("@inicial_date"))
                      .calendarDate,
                    JSON.parse(localStorage.getItem("@final_date"))
                      .calendarDate,
                  ]
            }
            onChange={(event) => handleChange(event)}
            weekDays={weekDays}
            months={months}
            className="container__calendar"
            mapDays={({ date }) => {
              let isWeekend = datesBooked[date.month.number]?.includes(
                date.day
              );

              if (isWeekend)
                return {
                  disabled: true,
                  style: { color: "var(--chocal)", backgroundColor: "var(--medium-grey)" },
                  onClick: () =>
                    alert("O carro não está disponível para essa data"),
                };
            }}
          />
        </div>
        <div className="card-add-date">
          <p>Adicione as datas que deseja reservar para obter preços exatos</p>
          {isLogin ? (
            <>
              <Link to={`/reservation/${productId}`}>
                <ButtonPadrao text="Iniciar Reserva" />
              </Link>
            </>
          ) : (
            <Link to="/login">
              <ButtonPadrao
                text="Iniciar Reserva"
                handleClick={() => handleRedirect(productId)}
              />
            </Link>
          )}
        </div>
      </div>
    </div>
  );
}
