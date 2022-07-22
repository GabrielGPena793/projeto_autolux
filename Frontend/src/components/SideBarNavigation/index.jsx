import {  Bookmarks, CircleWavyCheck, Eye, EyeClosed, House, SquaresFour } from "phosphor-react";
import { useState } from "react";
import "./styles.css";

export function SideBarNavigation() {

  const [isHidden, setIsHidden] = useState(false);

  return (
    <nav className="container_sideBarNavigation">
      {isHidden ? (
        <div 
          className={`container__hidden ${isHidden && "container__hidden_hidden"}`} 
          onClick={() => setIsHidden(!isHidden)}
        >
         <EyeClosed size={20} weight="bold"  color="var(--dark-blue)"/>
        </div>
      ) : (
        <div className="container__hidden" onClick={() => setIsHidden(!isHidden)}>
          <Eye size={20} weight="bold"  color="var(--dark-blue)"/>
        </div>
      )
      }
      <ul className={isHidden ? "hidden" : ""}>
        <li>
          <div className="home-icon">
            <a href="#home" className="container_sideBarNavigation_icon">
              <House size={32} weight="bold" color="white" />
            </a>
          </div>
        </li>
        <li>
          <div className="about-icon">
            <a href="#brand" className="container_sideBarNavigation_icon">
              <Bookmarks size={32} weight="bold" color="white" />
            </a>
          </div>
        </li>
        <li>
          <div className="work-icon">
            <a href="#categories" className="container_sideBarNavigation_icon">
              <SquaresFour
                size={32}
                weight="bold"
                color="white"
              />
            </a>
          </div>
        </li>
        <li>
          <div className="mail-icon">
            <a href="#recommended" className="container_sideBarNavigation_icon">
              <CircleWavyCheck
                size={32}
                weight="bold"
                color="white"
              />
            </a>
          </div>
        </li>
      </ul>
    </nav>
  );
}
