import pgPromise from "pg-promise";

const db = pgPromise()("postgress://postgres:postgres@localhost:5432/postgres");

const setUpDb = async () => {
  await db.none(`
    SELECT *
    FROM titanic
    WHERE Sex = "female"
        AND Survived = 1
            AND Age > 30;

    SELECT AVG(Age)
    FROM titanic
    WHERE Sex = "male"
        AND Survived = 0;

    SELECT *
    FROM titanic
    WHERE Fare > 20
        AND Fare < 50
            AND Embarked = "C";

    SELECT COUNT(*)
    FROM titanic
    WHERE Survived = 1
        AND Pclass = 1;

    SELECT *
    FROM titanic
    WHERE Embarked = "C"
        AND Fare > 75;
  `);
};
setUpDb();

export { db };
