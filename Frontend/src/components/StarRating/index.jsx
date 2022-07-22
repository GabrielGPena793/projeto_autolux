import { useState } from "react";
import { Star } from "phosphor-react"
import "./styles.css"

export function StarRating({rating, onSelectRating}){
 
  const [hover, setHover] = useState(null);

  return (
      <div className="container_rating_star">
      {[...Array(5)].map( (star, i) =>{
        const ratingValue = i + 1;
          
        return (
          <label 
            onClick={() => onSelectRating(ratingValue)} 
            onMouseEnter={() => setHover(ratingValue)}
            onMouseOut={() => setHover(null)}
          
          > 
            <input 
              type="radio" 
              name="rating" 
              value={ratingValue} 
            />
            {ratingValue <= (hover || rating) ?  
              <Star 
              className="star" 
              size="100%" 
              weight="fill" 
              color="yellow"
            /> 
            : 
            <Star 
              className="star" 
              size="100%" 
              weight="bold" 
              color="white"
            /> 
            }
           
          </label>
        )
      })}
      </div>
  );
};