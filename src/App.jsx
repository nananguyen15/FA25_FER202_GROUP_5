import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [greetName, setGreetName] = useState("");
  const [greetResult, setGreetResult] = useState("");
  function greet(name = "dude") {
    return `Hello, ${name}`;
  }
  const handleGreet = () => {
    setGreetResult(greet(greetName || undefined));
  };

  const [a, setA] = useState("");
  const [b, setB] = useState("");
  const [multiplyResult, setMultiplyResult] = useState("");
  function multiply(a, b = 2) {
    return Number(a) * Number(b);
  }
  const handleMultiply = () => {
    if (a === "") {
      setMultiplyResult("Enter a: ");
      return;
    }
    setMultiplyResult(multiply(a, b || undefined));
  };

  const [width, setWidth] = useState("");
  const [height, setHeight] = useState("");
  const [areaResult, setAreaResult] = useState("");
  function calculateArea(width, height = width) {
    return Number(width) * Number(height);
  }
  const handleArea = () => {
    if (width === "") {
      setAreaResult("Enter width: ");
      return;
    }
    setAreaResult(calculateArea(width, height || undefined));
  };

  const sectionStyle = { maxWidth: 400, margin: '24px auto', fontFamily: 'sans-serif', background: '#f9f9f9', padding: 16, borderRadius: 8 };

  const [count, setCount] = useState(0)

  return (
    <>
      <div style={sectionStyle}>
        <h2>greet() function</h2>
        <input
          type="text"
          placeholder="Enter name..."
          value={greetName}
          onChange={e => setGreetName(e.target.value)}
          style={{ marginRight: 8 }}
        />
        <button onClick={handleGreet}>Say hello</button>
        <div style={{ marginTop: 8, minHeight: 24 }}>{greetResult}</div>
      </div>

      <div style={sectionStyle}>
        <h2>multiply() function</h2>
        <input
          type="number"
          placeholder="a"
          value={a}
          onChange={e => setA(e.target.value)}
          style={{ width: 60, marginRight: 8 }}
        />
        <input
          type="number"
          placeholder="b (default = 2)"
          value={b}
          onChange={e => setB(e.target.value)}
          style={{ width: 100, marginRight: 8 }}
        />
        <button onClick={handleMultiply}>Multply</button>
        <div style={{ marginTop: 8, minHeight: 24 }}>{multiplyResult}</div>
      </div>

      <div style={sectionStyle}>
        <h2>calculateArea() functon</h2>
        <input
          type="number"
          placeholder="Width"
          value={width}
          onChange={e => setWidth(e.target.value)}
          style={{ width: 100, marginRight: 8 }}
        />
        <input
          type="number"
          placeholder="Height (default = width)"
          value={height}
          onChange={e => setHeight(e.target.value)}
          style={{ width: 160, marginRight: 8 }}
        />
        <button onClick={handleArea}>Calculate area</button>
        <div style={{ marginTop: 8, minHeight: 24 }}>{areaResult}</div>
      </div>

      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default App
