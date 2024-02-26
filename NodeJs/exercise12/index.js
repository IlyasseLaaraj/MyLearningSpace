require("dotenv").config();
const express = require("express");
const helmet = require("helmet");
const cors = require("cors");
const fs = require("fs");
const Joi = require("joi");

const app = express();

app.use(helmet());
app.use(cors());

app.use(express.urlencoded({ extended: true }));
app.use(express.json({ extended: true }));

const dataPath = "./data.json";

let jsonData;

const planetSchema = Joi.object({
  id: Joi.number().integer().required(),
  name: Joi.string().required(),
});

const data = fs.readFileSync(dataPath, "utf-8");
jsonData = JSON.parse(data);

app.get("/api/planets", (_, res) => {
  if (jsonData) {
    return res.status(200).json({ jsonData });
  } else {
    return res.status(500).json({ error: "No data available" });
  }
});

app.get("/api/planets/:planet_id", (req, res) => {
  const planet_id = req.params.planet_id;
  const planet = jsonData.planets.find((item) => item.id === Number(planet_id));

  return res.status(200).json({ planet });
});

app.post("/api/planets", (req, res) => {
  const name = req.body.name;
  const id = req.body.id;

  const newPlanet = {
    name,
    id,
  };

  const validatedNewPlanet = planetSchema.validate(newPlanet);
  if (validatedNewPlanet.error) {
    return res.status(400).json({ msg: validatedNewPlanet.error });
  }

  jsonData.planets.push(newPlanet);

  return res.status(201).json({ newPlanet });
});

app.put("/api/planets/:planet_id", (req, res) => {
  const planet_id = req.params.planet_id;
  const name = req.body.name;

  const planet_index = jsonData.planets.findIndex(
    (item) => item.id == planet_id
  );

  if (planet_index === -1) {
    return res.status(404).json({
      message: "Planet not found",
    });
  }

  if (name) {
    jsonData.planets[planet_index].name = name;

    return res.status(200).json({ message: "user updated" });
  }
});

app.delete("/api/planets/:planet_id", (req, res) => {
  const planet_id = req.params.planet_id;
  const name = req.body.name;

  const planet_index = jsonData.planets.findIndex(
    (item) => item.id == planet_id
  );

  if (planet_index === -1) {
    return res.status(404).json({
      message: "Planet not found",
    });
  }

  jsonData.planets.splice(planet_index, 1);

  return res.status(200).json({ message: "planet deleted" });
});

app.listen(process.env.SERVER_PORT, () => {
  console.log(`Server up and running on port: ${process.env.SERVER_PORT}`);
});
