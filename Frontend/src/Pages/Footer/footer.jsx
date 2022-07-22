import React from "react";
import { BsFacebook, BsLinkedin, BsTwitter, BsInstagram, BsFillRecordFill } from "react-icons/bs";
import { Nav } from "react-bootstrap";
import "./footer.css";

export default function Footer() {
  return (
    <footer className="text-white footer_all">
      <div className="align-items-center d-flex row">
        <div className="col-auto"> &copy; 2022 Autolux</div>
        <div className="col-auto ponto_footer"><BsFillRecordFill className="text-light" /></div>
        <div className="col-auto">
          <Nav.Link className="text-light">
            <div className="text-light">Sobre a empresa</div>
          </Nav.Link>
        </div>
        <div className="col mr-auto"></div>
        <div className="col-auto">
          <div className="row">
            <Nav.Link
              className="link-to text-light col-1"
              href="https://www.facebook.com/"
              target="_blank"
              id="myfacebook"
            >
              <div className="box_icon_footer">
                <BsFacebook className="Icon-color_footer" alt="Facebook" title="Facebook" />
              </div>
            </Nav.Link>
            <Nav.Link
              className="link-to text-light col-1"
              href="https://br.linkedin.com/"
              target="_blank"
              id="mylinkedin"
            >
              <div className="box_icon_footer">
                <BsLinkedin className="Icon-color_footer" alt="Linkedin" title="Linkedin" />
              </div>
            </Nav.Link>
            <Nav.Link
              className="link-to text-light col-1"
              href="https://twitter.com/"
              target="_blank"
              id="mytwitter"
            >
              <div className="box_icon_footer">
                <BsTwitter className="Icon-color_footer" alt="Twitter" title="Twitter" />
              </div>
            </Nav.Link>
            <Nav.Link
              className="link-to text-light col-1"
              href="https://www.instagram.com/"
              target="_blank"
              id="myinstagram"
            >
              <div className="box_icon_footer">
                <BsInstagram className="Icon-color_footer" alt="Instagram" title="Instagram" />
              </div>
            </Nav.Link>
          </div>
        </div>
      </div>
    </footer>
  );
}
