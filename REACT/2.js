export function Welcome({ name = 'World', age }) {
	return (
		<div>
			<p>Hello, {name}!</p>
			<p>Your age is {age}</p>
		</div>
	);
}

function App() {
	return (
		<>
			<Welcome name={<strong>World</strong>} age="18" />
		</>
	);
}
