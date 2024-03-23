import { db } from "./db";
import jws from "jsonwebtoken";
import * as dotenv from "dotenv";
dotenv.config();

const logIn = async (req, res) => {
  const { username, password } = req.body;
  const user = await db.one(`SELECT * FROM users WHERE username=$1`, username);

  if (user && user.password === password) {
    const payload = {
      id: user.id,
      username,
    };
    const { SECRET = "" } = process.env;
    const token = jwt.sign(payload, SECRET);
    await db.none(`UPDATE user SET token=$2 WHERE id=$1`, [user.id, token]);
    res.status(200).json({ message: "" });
  } else {
    res.status(400).json({ message: "User or password incorrect." });
  }
};

const signUp = async (req, res) => {
  const { username, password } = req.body;
  const user = await db.oneOrNone(
    `SELECT * FROM users WHERE username=$1`,
    username
  );

  if (user) {
    res.status(409).json({ message: "username already in use" });
  } else {
    const { id } = await db.one(
      `INSERT INTO users (username, password) VALUES ($1, $2) RETURNING id`,
      [username, password]
    );
    res
      .status(201)
      .json({ id, message: "Signup successful. Now you can log in." });
  }
};

export { logIn, signUp };
