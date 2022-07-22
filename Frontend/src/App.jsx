import { BrowserRouter, Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./Pages/NavBar/navbar";
import Footer from "./Pages/Footer/footer";
import Register from "./Pages/Register/register";
import Login from "./Pages/Login/login";
import Home from "./Pages/Home";
import Description from "./Pages/Description/Description";
import Reservation from "./Pages/Reservation/Reservation";
import { AuthProvider } from "./Pages/Login/contextLogin/auth";
import Category from "./Pages/Category";
import "./App.css";
import Admin from "./Pages/Admin";
import MyReservation from "./Pages/MyReservation";
import ListProducts from "./Pages/ListProducts";
import NotFound from "./Pages/404";

export default function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/description/:id" element={<Description />} />
          <Route path="/category/:categoryParams" element={<Category />} />
          <Route path="/category/city/:cityParams" element={<Category />} />
          <Route
            path="/category/date/:dateInit/:dateFinal"
            element={<Category />}
          />
          <Route
            path="/category/city/:cityParams/date/:dateInit/:dateFinal"
            element={<Category />}
          />
          <Route path="/reservation/:id" element={<Reservation />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/listProducts" element={<ListProducts />} />
          <Route path="/myReservation" element={<MyReservation />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
        <Footer />
      </AuthProvider>
    </BrowserRouter>
  );
}
