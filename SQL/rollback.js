import pgPromise from "pg-promise";

const db = pgPromise()("postgress://postgres:postgres@localhost:5432/postgres");

const setUpDb = async () => {
  await db.none(`
    BEGIN TRANSACTION;
    DELETE FROM books WHERE book_id = 103;
    ROLLBACK;
  `);
};
setUpDb();

export { db };
