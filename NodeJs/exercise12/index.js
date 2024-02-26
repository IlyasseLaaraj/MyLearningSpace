require("dotenv").config();
const express = require("express");
const helmet = require("helmet");
const cors = require("cors");

const {
  getAll,
  getOneById,
  create,
  updateById,
  deleteById,
} = require("./conrtrollers/planets");
const app = express();

app.use(helmet());
app.use(cors());

app.use(express.urlencoded({ extended: true }));
app.use(express.json({ extended: true }));

app.get("/api/planets", getAll);

app.get("/api/planets/:planet_id", getOneById);

app.post("/api/planets", create);

app.put("/api/planets/:planet_id", updateById);

app.delete("/api/planets/:planet_id", deleteById);

app.listen(process.env.SERVER_PORT, () => {
  console.log(`Server up and running on port: ${process.env.SERVER_PORT}`);
});
