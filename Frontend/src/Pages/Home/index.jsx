import React, { useEffect} from "react";
import CardCategory from "../../components/CardCategory";
import CardRecommend from "../../components/CardRecommend";
import SearchBar from "../../components/SearchBar/searchBar";
import FindBrands from "../../components/FindBrands/FindBrands";
import "./styles.css";
import { SideBarNavigation } from "../../components/SideBarNavigation";

function Home() {

  useEffect(() => {
    localStorage.setItem("@inicial_date", "")
    localStorage.setItem("@final_date", "")
  }, [])

  return (
    <div className="container__home_page">
      <section className="container__home">
        <SideBarNavigation />
        <div className="container__home_title_banner">
          <h1 className="home_title">Viva essa experiência</h1>
          <p className="home_paragraph">Alugue o carro dos sonhos na Autolux</p>
        </div>

        <div className="container__home_img">
          <img
            src="https://i.postimg.cc/K8BW4xn1/Rectangle-103.png"
            alt="carro vermelho de luxo com rodas pretas"
          />
        </div>
      </section>
      <SearchBar />
      <FindBrands />
      <CardCategory />
      <CardRecommend />
    </div>
  );
}

export default Home;
