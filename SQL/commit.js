import pgPromise from "pg-promise";

const db = pgPromise()("postgress://postgres:postgres@localhost:5432/postgres");

const setUpDb = async () => {
  await db.none(`
    BEGIN TRANSACTION;
    DELETE FROM books WHERE book_id = 101;
    COMMIT;
  `);
};
setUpDb();

export { db };
