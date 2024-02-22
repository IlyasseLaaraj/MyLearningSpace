const luckyDraw = (player) => {
  return new Promise((resolve, reject) => {
    const win = Boolean(Math.round(Math.random()));

    process.nextTick(() => {
      if (win) {
        resolve(`${player} won a prize in the draw!`);
      } else {
        reject(new Error(`${player} lost the draw.`));
      }
    });
  });
};

luckyDraw("Joe")
  .then((playerName) => {
    console.log(playerName);
  })
  .catch((error) => {
    console.error(error);
  });

luckyDraw("Caroline")
  .then((playerName) => {
    console.log(playerName);
  })
  .catch((error) => {
    console.error(error);
  });

luckyDraw("Sabrina")
  .then((playerName) => {
    console.log(playerName);
  })
  .catch((error) => {
    console.error(error);
  });
