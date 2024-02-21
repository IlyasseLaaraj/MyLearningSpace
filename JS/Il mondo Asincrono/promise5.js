// Convertire la seguente funzione basata su callback in una funzione basata su Promise.

function callback_BasedFunction(arg1, arg2, callback) {
  setTimeout(() => {
    const result = arg1 + arg2;
    if (result % 2 !== 0) {
      callback(null, result);
    } else {
      callback(new Error("Result is not odd!"), null);
    }
  }, 1000);
}

/* ---------------------------------------------------- */

function promise_BasedFunction(arg1, arg2) {
  return new Promise((resolve, reject) => {
    const result = arg1 + arg2;
    if (result % 2 !== 0) {
      return resolve(result);
    } else {
      return reject(new Error("Result is not odd!"));
    }
  });
}

promise_BasedFunction(1, 2)
  .then((result) => {
    console.log(result);
  })
  .catch((error) => {
    console.error(error);
  });
