import pgPromise from "pg-promise";

const db = pgPromise()("postgress://postgres:postgres@localhost:5432/postgres");

const setUpDb = async () => {
  await db.none(`
    UPDATE books SET price = "$10.99", stock_count = "4"
    WHERE id = 2
  `);
};
setUpDb();

export { db };
