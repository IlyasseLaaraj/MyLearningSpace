const Joi = require("joi");

const planets = [
  {
    id: 1,
    name: "Earth",
  },
  {
    id: 2,
    name: "Mars",
  },
];
const planetSchema = Joi.object({
  id: Joi.number().integer().required(),
  name: Joi.string().required(),
});

const getAll = (_, res) => {
  return res.status(200).json({ planets });
};

const getOneById = (req, res) => {
  const planet_id = req.params.planet_id;
  const planet = planets.find((item) => item.id === Number(planet_id));

  return res.status(200).json({ planet });
};

const create = (req, res) => {
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

  planets.push(newPlanet);

  return res.status(201).json({ newPlanet });
};

const updateById = (req, res) => {
  const planet_id = req.params.planet_id;
  const name = req.body.name;

  const planet_index = planets.findIndex((item) => item.id == planet_id);

  if (planet_index === -1) {
    return res.status(404).json({
      message: "Planet not found",
    });
  }

  if (name) {
    planets[planet_index].name = name;

    return res.status(200).json({ message: "user updated" });
  }
};

const deleteById = (req, res) => {
  const planet_id = req.params.planet_id;
  const name = req.body.name;

  const planet_index = planets.findIndex((item) => item.id == planet_id);

  if (planet_index === -1) {
    return res.status(404).json({
      message: "Planet not found",
    });
  }

  planets.splice(planet_index, 1);

  return res.status(200).json({ message: "planet deleted" });
};

module.exports = { getAll, getOneById, create, updateById, deleteById };
