import { createRoot } from 'react-dom/client'

export function Hello() {
	return <h2>Hello, World!</h2>
}

export function App() {
	return (
		<div>
			<Hello />
		</div>
	)
}

createRoot(document.getElementById('root')).render(<App />)