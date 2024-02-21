/* import { createServer } from "node:http";

const server = createServer((request, response) => {
  console.log("request received");

  response.statusCode = 200;

  response.setHeader("Content-Type", "application/json");

  const jsonResponseBody = JSON.stringify({ location: "Earth" });

  response.end(jsonResponseBody);
});

server.listen(3000, () => {
  console.log(`Server running at http://localhost:3000`);
});
 */

require("dotenv").config();
const express = require("express");
const helmet = require("helmet");
const cors = require("cors");

const app = express();

app.use(helmet());
app.use(cors());

app.use(express.urlencoded({ extended: true }));
app.use(express.json({ extended: true }));

app.use("/", (_, res) => {
  return res.status(200).json({ location: "Mars" });
});

app.listen(process.env.SERVER_PORT, () => {
  console.log(`Server up and running on port ${process.env.SERVER_PORT}`);
});
