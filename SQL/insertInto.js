import pgPromise from "pg-promise";

const db = pgPromise()("postgress://postgres:postgres@localhost:5432/postgres");

const setUpDb = async () => {
  await db.none(`
    INSERT INTO books VALUE (
      1, "A Journey to the Center", "Jules Verne", "Adventure", "1864", "1234567890123", "$12.992", "4.5", "10", "Verne Publishing", "350"
    )
    INSERT INTO books VALUE (
      2, "War and Peace", "Leo Tolstoy", "Historical", "1869", "1234567890124", "$14.99", "4.8", "5", "Tolstoy Prints", "1200"
    )
    INSERT INTO books VALUE (
      3, "Whispers of the Wind", "Amelia Blackburn", "Romance", "1982", "1234567890125", "$9.99", "4.2", "20", "Blackburn House", "275"
    )
    INSERT INTO books VALUE (
      4, "The Galactic Odyssey", "Orion Starfield",  "Science Fiction", "2005", "1234567890126", "$19.99", "4.9", "15", "Nebula Press", "450"
    )
  `);
};
setUpDb();

export { db };
