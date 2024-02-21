const crypto = require("crypto");

const generateRandomId = crypto.randomUUID();

console.log(generateRandomId);
