import Hello from "./components/Hello";
import Message from "./components/Message";

const App = () => {
  return (
    <>
      <div>
        {/* yes you can use it more than once */}
        <Message />
        <Hello />
      </div>
    </>
  );
};

export default App;
