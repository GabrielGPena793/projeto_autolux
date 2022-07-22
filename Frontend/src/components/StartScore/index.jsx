import { Stars } from "../Stars";

function getFills(score) {
  const fills = [0, 0, 0, 0, 0];

  const integerPart = Math.floor(score);

  for (let i = 0; i < integerPart; i++) {
    fills[i] = 1;
  }

  const diff = score - integerPart;
  if (diff > 0) {
    fills[integerPart] = 0.5;
  }

  return fills;
}

export function StarScore({ score, className }) {
  const fills = getFills(score);
  return (
    <div className={className}>
      <Stars fill={fills[0]} />
      <Stars fill={fills[1]} />
      <Stars fill={fills[2]} />
      <Stars fill={fills[3]} />
      <Stars fill={fills[4]} />
    </div>
  );
}
