import React from "react";
import { Link } from "react-router-dom";
import { Navbar, Nav } from "react-bootstrap";
import { Logo } from "../../components/Icon/Logo";
import "./navbar.css";
import useAuth from "../Login/hooks/useAuth";
import { MenuSidebar } from "../../components/MenuSidebar";
import { AvatarProfile } from "../../components/AvatarProfile";

function gettingFirstLetters(user) {
  if (typeof user !== "undefined") {
    return user
      .split(" ")
      .map((word) => word[0])
      .join("");
  } else {
    return "";
  }
}

export default function NavBar() {
  const { isLogin, setIsLogin, user, setUser } = useAuth();

  function handleLogout(){
    setIsLogin(false)
    setUser(null)
    localStorage.removeItem("@token_user");
  }


  return (
    <>
      <div className="container__menu_sidebar">
        <MenuSidebar
          pageWrapId={"page-wrap"}
          outerContainerId={"outer-container"}
        />
      </div>
      <header className="container__header" id="home">
        <Navbar expand="md" className="header_all sticky-top">
          <Navbar.Collapse id="responsive-navbar-nav">
            {isLogin ? (
              <>
                <Nav>
                  <nav className="container__nav_lef navigation_header">
                    <Link to="/">
                      <span>Home</span>
                    </Link>
                    <Link to="/category/all">
                      <span>Categorias</span>
                    </Link>
                  </nav>

                  <Link
                    title="Logo"
                    className="link-to"
                    to="/"
                    aria-label="Read more about Seminole tax hike"
                  >
                    <Logo />
                  </Link>

                  <nav className="container__nav_right navigation_header">
                    <Link to="/myReservation">
                      <span>Reservas</span>
                    </Link>
                    <div className="container__avatar_perfil">
                      <div className="circle">
                        <span>{gettingFirstLetters(user.login)}</span>
                      </div>
                      <AvatarProfile user={user} onLogout={handleLogout} />
                    </div>
                  </nav>
                </Nav>
              </>
            ) : (
              <Nav>
                <nav className="container__nav_lef navigation_header">
                  <Link to="/">
                    <span>Home</span>
                  </Link>
                  <Link to="/category/all">
                    <span>Categorias</span>
                  </Link>
                </nav>

                <Link
                  title="Logo"
                  className="link-to"
                  to="/"
                  aria-label="Read more about Seminole tax hike"
                >
                  <Logo />
                </Link>

                <nav className="container__nav_right navigation_header">
                  <Link to="/login" >
                    <span>Reservas</span>
                  </Link>
                  <Link to="/login">
                    <span>Login</span>
                  </Link>
                </nav>
              </Nav>
            )}
          </Navbar.Collapse>
        </Navbar>
      </header>
    </>
  );
}
