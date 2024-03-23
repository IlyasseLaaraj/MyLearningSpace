import pgPromise from "pg-promise";

const db = pgPromise()("postgress://postgres:postgres@localhost:5432/postgres");

const setUpDb = async () => {
  await db.none(`
    CREATE TABLE books(
      book_id INTEGER PRIMARY KEY,
      title TEXT NOT NULL,
      author TEXT NOT NULL,
      genre TEXT NOT NULL,
      published_year TEXT NOT NULL,
      isbn TEXT NOT NULL,
      price TEXT NOT NULL,
      rating TEXT NOT NULL,
      stock_count TEXT NOT NULL,
    )

    ALTER TABLE books
    ADD COLUMN publisher TEXT NOT NULL,
    ADD COLUMN pages TEXT NOT NULL
  `);
};
setUpDb();

export { db };
