require("dotenv");
const express = require("express");
const helmet = require("helmet");
const cors = require("cors");

const app = express();

app.use(helmet());
app.use(cors());

app.use(express.urlencoded({ extended: true }));
app.use(express.json({ extended: true }));

app.listen(process.env.SERVER_PORT, () => {
  console.log(`server up and running on port: ${process.env.SERVER_PORT}`);
});
