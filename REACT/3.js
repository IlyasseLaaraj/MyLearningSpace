export function Welcome({ name = 'World', age }) {
	return (
		<div>
			<p>Hello, {name}!</p>
			<Age age={age} />
		</div>
	)
}
export function Age({ age }) {
	return <p>Your age is {age}</p>
}
