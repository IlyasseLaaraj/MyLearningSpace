import Age from "./Age";

const Welcome = ({ name }) => {
  return (
    <>
      <p>Welcome {name}!</p>
      <Age age={24} />
    </>
  );
};

export default Welcome;
