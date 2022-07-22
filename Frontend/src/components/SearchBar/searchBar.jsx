import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'
import DatePicker, { DateObject } from "react-multi-date-picker"
import './searchBar.css'
import transition from "react-element-popper/animations/transition"
import { format } from 'date-fns'
import { ButtonPadrao } from '../ButtonPadrao'


const weekDays = ["Dom", "Seg", "Ter", "Qua", "Quin", "Sex", "Sab"];
const months = ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"]


export const CustomDropdown = (props) => {

  let navigate = useNavigate()

  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const [numberOfMonths, setNumberOfMonths] = useState(2);

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

  const [values, setValues] = useState([
    new DateObject(),
    new DateObject().add()
  ])

  const [date, setDate] = useState([])

  const [selectCity, setSelectCity] = useState(0)

  function handleChange(event) {
    setDate(event)
    setValues(event)
  }

  function handleClick() {
   

    if (selectCity !== 0 && date[1] !== undefined) {
      setDateInLocalStorage()
      navigate(`/category/city/${selectCity}/date/${format(new Date(date[0]), 'yyyy-MM-dd')}/${format(new Date(date[1]), 'yyyy-MM-dd')}`, { replace: true })
    }
    else if (selectCity !== 0) {
      navigate(`/category/city/${selectCity}`, { replace: true })
    }
    else if (date[1] !== undefined) {
      setDateInLocalStorage()
      navigate(`/category/date/${format(new Date(date[0]), 'yyyy-MM-dd')}/${format(new Date(date[1]), 'yyyy-MM-dd')}`, { replace: true })
    }
  }

  function setDateInLocalStorage() {
     //setando data no localStorage
     localStorage.setItem(
      "@inicial_date",
      JSON.stringify({
        inputDate: format(new Date(date[0]), "dd/MM/yyyy"),
        calendarDate: format(new Date(date[0]), "yyyy-MM-dd"),
      })
    );

    localStorage.setItem(
      "@final_date",
      JSON.stringify({
        inputDate: format(new Date(date[1]), "dd/MM/yyyy"),
        calendarDate: format(new Date(date[1]), "yyyy-MM-dd"),
      })
    );
  }

  return (
    <div className="container_search">
      <div className='search_input'>
        <select
          className="form-control search_input_select"
          name={props.name}
          onChange={(event) => setSelectCity(event.target.value)}
        >
          <option defaultValue>Escolha o local de retirada</option>
          {props.options.map((item, index) => (
            <option key={index} value={item.id}>              
              {item.name}
            </option>
          ))}
        </select>
      </div>

      <div className="search_input input_date">
        <DatePicker          
          animations={[transition()]}
          mode="range"
          numberOfMonths={numberOfMonths}
          range
          weekDays={weekDays}
          months={months}
          format="DD/MM/YYYY"
          placeholder="Data de retirada - Data de devolução "
          containerClassName="form-control"
          values={values}
          onChange={event => handleChange(event)}
          minDate={new DateObject().subtract(0, "days")}
          maxDate={new DateObject().add(365, "days")}                  
        />
      </div>
      <div className="search_button">
        <ButtonPadrao text="Buscar" handleClick={handleClick}/>
      </div>
    </div>
  )
}

export default class CustomListDropDown extends React.Component {
  constructor() {
    super()
    this.state = {
      collection: [],
      value: '',
    }
  }
  componentDidMount() {
    fetch('https://back-end-booking.herokuapp.com/v1/cities')
      .then((response) => response.json())
      .then((res) => this.setState({ collection: res }))
  }
  render() {
    return (
      <div className="container-fluid search-bar-div" id="search-bar">
        <div className="row">
          <div className="title"><h1 className="search-bar-title">Reserve agora seu carro</h1></div>

          <CustomDropdown
            name={this.state.name}
            options={this.state.collection}
          />
        </div>
      </div>
    )
  }
}


