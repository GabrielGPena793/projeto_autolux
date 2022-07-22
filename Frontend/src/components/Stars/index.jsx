import { StarHalf, Star } from "phosphor-react";

export function Stars({fill, className}){

  if(fill === 0 )
    return <Star size={15} weight="bold" color="white" />;
  else if (fill === 1)
    return <Star size={15} weight="fill" color="var(--dark-gold)"/>;
  else
    return <StarHalf size={15} weight="fill" color="var(--dark-gold)"/>;
}