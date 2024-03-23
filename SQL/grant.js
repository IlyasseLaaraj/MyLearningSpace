import pgPromise from "pg-promise";

const db = pgPromise()("postgress://postgres:postgres@localhost:5432/postgres");

const setUpDb = async () => {
  await db.none(`
    GRANT SELECT, UPDATE ON books TO "martin@localhost"
  `);
};
setUpDb();

export { db };
