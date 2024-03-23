import "dotenv/config";

import express from "express";
import "express-async-errors";
import morgan from "morgan";
import { logIn, signUp, logOut } from "./users.js";
import authorize from "./authorize.js";
import "./passport.js";

const app = express();

app.use(morgan("dev"));
app.use(express.json());

app.post("/api/user/login", logIn);
app.post("/api/user/signup", signUp);
app.get("/api/user/logout", authorize, logOut);

app.listen(process.env.SERVER_PORT, () => {
  console.log(`Example app listening on port ${process.env.SERVER_PORT}`);
});
