import Textfield from "@material-ui/core/TextField";
import { Card } from "react-bootstrap";
import { Calendar } from "react-multi-date-picker";
import "./Reserv_page.css";
import { useState, useEffect } from "react";
import { api } from "../../../Service/axios";
import { BsFillGeoAltFill } from "react-icons/bs";
import { useParams, useNavigate } from "react-router";
import Swal from "sweetalert2";
import { CheckCircle } from "phosphor-react";
import { ButtonPadrao } from "../../../components/ButtonPadrao";
import { format } from "date-fns";
import useAuth from "../../Login/hooks/useAuth";

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

export default function Reserv_page({ products }) {
  const { user } = useAuth();

  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [numberOfMonths, setNumberOfMonths] = useState(2);
  const [dateValue, setDateValue] = useState([
    localStorage.getItem("@inicial_date") !== (null || "")
      ? JSON.parse(localStorage.getItem("@inicial_date")).inputDate
      : "",
    localStorage.getItem("@final_date") !== (null || "")
      ? JSON.parse(localStorage.getItem("@final_date")).inputDate
      : "",
  ]);
  const { id } = useParams();
  const [startTime, setStartTime] = useState("");
  const [userEmail, setUserEmail] = useState(
    localStorage.getItem("@email") || ""
  );
  const [name, setName] = useState(user?.login.split(" ")[0] || "");
  const [lastName, setLastName] = useState(user?.login.split(" ")[1] || "");
  const [productCity, setProductCity] = useState(products[0].city.name || "");
  const tokenUser = localStorage.getItem("@token_user");
  const navigate = useNavigate();

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

      setDateValue([
        format(new Date(e[0]), "dd/MM/yyyy"),
        format(new Date(e[1]), "dd/MM/yyyy"),
      ]);
    }
  }

  function postBooking(e) {
    e.preventDefault();
    api
      .post(
        "/v1/bookings",
        {
          startTime: `2022-06-24T${startTime}.895Z`,
          startDate: JSON.parse(localStorage.getItem("@inicial_date"))
            .calendarDate,
          endDate: JSON.parse(localStorage.getItem("@final_date")).calendarDate,
          email: userEmail,
          productId: id,
        },
        {
          headers: { Authorization: tokenUser },
        }
      )
      .then(function (response) {
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Reserva realizada com sucesso",
          showConfirmButton: false,
          timer: 3000,
        }).then(() => {
          navigate(`../`, { replace: true });
        });
      })
      .catch(function (error) {
        Swal.fire({
          position: "center",
          icon: "error",
          title:
            "Reserva falhou, tente novamente ou entre em contato com nosso Sac.",
          showConfirmButton: false,
          timer: 3000,
        });
      });
  }
  const [carReserv, setCarReserv] = useState([]);
  const [datesBooked, setDatesBooked] = useState({});

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
    try {
      const response = await api.get(`v1/bookings/product/${id}`);
      setCarReserv(response.data);
    } catch (error) {
      console.log("Não tem reservas para esse carro");
    }
  }

  function handleDate(carReserv) {
    let datesBookedObject = {};
    carReserv.map((booking) => {
      let arrayDates = getDaysArray(booking.startDate, booking.endDate);
      for (const key in arrayDates) {
        if (Object.keys(datesBookedObject).includes(key)) {
          datesBookedObject[key] = [
            ...datesBookedObject[key],
            ...arrayDates[key],
          ];
        } else {
          datesBookedObject[key] = [...arrayDates[key]];
        }
      }
      return "";
    });
    setDatesBooked(datesBookedObject);
  }

  useEffect(() => {
    window.addEventListener("resize", () => {
      setWindowWidth(window.innerWidth);
    });
    if (windowWidth < 830) {
      setNumberOfMonths(1);
    } else {
      setNumberOfMonths(2);
    }
  }, [windowWidth]);

  useEffect(() => {
    getByCarReserv();
  }, []);

  useEffect(() => {
    handleDate(carReserv);
  }, [carReserv]);

  return (
    <form className="reserv_page">
      {products.map((product) => (
        <div key={product.id} className="reserv_page_color_pad">
          <h4>Complete seus dados</h4>
          <div className="reserv_page_all">
            <div className="reserv_page_left">
              <div className="reserv_page_data">
                <div className="reservation-form">
                  <div className="reserv_page_data_all">
                    <div className="reserv_page_name_lastname">
                      <Textfield
                        value={name}
                        className="reserv_page_data_size"
                        id="name"
                        name="name"
                        label="Nome"
                        margin="normal"
                        onChange={(e) => setName(e.target.value)}
                      />

                      <Textfield
                        value={lastName}
                        className="reserv_page_data_size"
                        id="lastname"
                        name="lastname"
                        label="Sobrenome"
                        margin="normal"
                        onChange={(e) => setLastName(e.target.value)}
                      />
                    </div>
                    <div className="reserv_page_email_city">
                      <Textfield
                        value={userEmail}
                        className="reserv_page_data_size"
                        id="email"
                        name="email"
                        label="Email"
                        type="email"
                        margin="normal"
                        onChange={(e) => setUserEmail(e.target.value)}
                      />

                      <Textfield
                        value={productCity}
                        className="reserv_page_data_size"
                        id="city"
                        name="city"
                        label="Cidade"
                        type="city"
                        margin="normal"
                        onChange={(e) => setProductCity(e.target.value)}
                      />
                    </div>
                  </div>
                </div>
              </div>
              <h4>Selecione sua data de reserva</h4>
              <div className="reserv_page_schedule">
                <Calendar
                  onChange={(e) => handleChange(e)}
                  value={
                    localStorage.getItem("@inicial_date") === (null || "")
                      ? []
                      : [
                          JSON.parse(localStorage.getItem("@inicial_date"))
                            ?.calendarDate,
                          JSON.parse(localStorage.getItem("@final_date"))
                            ?.calendarDate,
                        ]
                  }
                  minDate={new Date()}
                  mode="range"
                  numberOfMonths={numberOfMonths}
                  range
                  className="container__calendar"
                  weekDays={weekDays}
                  months={months}
                  mapDays={({ date }) => {
                    let isWeekend = datesBooked[date.month.number]?.includes(
                      date.day
                    );

                    if (isWeekend)
                      return {
                        disabled: true,
                        style: {
                          color: "var(--chocal)",
                          backgroundColor: "var(--medium-grey)",
                        },
                        onClick: () =>
                          alert("O carro não está disponível para essa data"),
                      };
                  }}
                />
              </div>
              <h4>Seu Horário de retirada</h4>
              <div className="reserv_page_time_all">
                <div className="reserv_page_time">
                  <h6>
                    <CheckCircle
                      className="reserv_page_icon"
                      size={20}
                      weight="bold"
                      color="var(--secondary-color)"
                    />
                    Horário de retirada e devolução do veículo: 08h00 às 23h00.
                  </h6>
                  <div className="container__select_hour">
                    <p className="select_hour_text">
                      Indique a sua hora prevista de retirada
                    </p>
                    <div>
                      <select
                        className="reserv_page_time_choice"
                        name="startTime"
                        onChange={(e) => setStartTime(e.target.value)}
                      >
                        <option defaultValue>
                          Escolha o horário de retirada
                        </option>
                        <option name="startTime" value="08:00:00">
                          08:00:00
                        </option>
                        <option name="startTime" value="09:00:00">
                          09:00:00
                        </option>
                        <option name="startTime" value="10:00:00">
                          10:00:00
                        </option>
                        <option name="startTime" value="11:00:00">
                          11:00:00
                        </option>
                        <option name="startTime" value="12:00:00">
                          12:00:00
                        </option>
                        <option name="startTime" value="13:00:00">
                          13:00:00
                        </option>
                        <option name="startTime" value="14:00:00">
                          14:00:00
                        </option>
                        <option name="startTime" value="15:00:00">
                          15:00:00
                        </option>
                        <option name="startTime" value="16:00:00">
                          16:00:00
                        </option>
                        <option name="startTime" value="17:00:00">
                          17:00:00
                        </option>
                        <option name="startTime" value="18:00:00">
                          18:00:00
                        </option>
                        <option name="startTime" value="19:00:00">
                          19:00:00
                        </option>
                        <option name="startTime" value="20:00:00">
                          20:00:00
                        </option>
                        <option name="startTime" value="21:00:00">
                          21:00:00
                        </option>
                        <option name="startTime" value="22:00:00">
                          22:00:00
                        </option>
                        <option name="startTime" value="23:00:00">
                          23:00:00
                        </option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="reserv_page_right">
              <h4>Detalhes da reserva</h4>
              <Card.Img
                className="reserv_page_img"
                src={product.images[1].url}
              />
              <div className="reserv_page_details">
                <span className="details_span">Modelo</span>
                <h3>{product.name}</h3>
                <h6 title="Titulo Carro" alt="Titulo Carro">
                  <BsFillGeoAltFill className="reserv_page_icon" />
                  {product.city.name}, {product.city.country}
                </h6>
              </div>
              <div className="reserv_page_date">
                <div className="reserv_page_date_check">
                  <p>Check in</p>
                  <p>{dateValue[0] ? dateValue[0] : "__/__/____"}</p>
                </div>
                <div className="reserv_page_date_check">
                  <p>Check out</p>
                  <p>{dateValue[1] ? dateValue[1] : "__/__/____"}</p>
                </div>
              </div>
              <div className="reserv_details_box_button">
                <ButtonPadrao
                  text="Confirmar reserva"
                  handleClick={(e) => postBooking(e)}
                />
              </div>
            </div>
          </div>
        </div>
      ))}
    </form>
  );
}
