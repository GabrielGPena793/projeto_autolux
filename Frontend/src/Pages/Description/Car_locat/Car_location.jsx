import React from "react";
import { GoogleMap, useJsApiLoader, Marker } from "@react-google-maps/api";

import "./Car_location.css";

export default function Car_location({ products }) {
  const { isLoaded } = useJsApiLoader({
    id: "google-map-script",
    googleMapsApiKey: "AIzaSyBYO5ZsdKKGHtblUNeCii7gA1pTaSEtX_A",
  });

  return (
    <>
      {products.map((product) => (
        <div className="Car_location_all" key={product.id}>
          <h3 className="Car_location_tit">Localização para a retirada do carro</h3>
          <p className="location_map">{product.city.name}, {product.city.country}</p>
          <div className="Car_location_map_size">
            {isLoaded ? (
              <GoogleMap
                mapContainerStyle={{ height: "100%", width: "100%" }}
                center={{
                  lat: product.city.latitude,
                  lng: product.city.longitude,
                }}
                zoom={14}
              >
                <Marker
                  position={{
                    lat: product.city.latitude,
                    lng: product.city.longitude,
                  }}
                  options={{
                    label: {
                      text: product.name,
                      className: "marker_google_maps",
                      fontSize: "16px",
                    },
                  }}
                />
              </GoogleMap>
            ) : (
              <></>
            )}
          </div>
        </div>
      ))}
    </>
  );
}
