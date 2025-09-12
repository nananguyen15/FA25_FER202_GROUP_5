import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";


// Hàm tính bình phương của một số
const square = (x) => x * x;

// Hàm trả về số lớn hơn trong hai số
const max = (a, b) => (a > b ? a : b);

// Mảng bình phương của các phần tử
const arr = [1, 2, 3, 4, 5];
const squaredArr = arr.map(square);

function App() {
  const [count, setCount] = useState(0);
  const [numx, setNumx] = useState("");
  const [num1, setNum1] = useState("");
  const [num2, setNum2] = useState("");

  return (
    <>
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
      <p>Nhập 1 số:  
        <input
          type="number"
          value={numx}
          onChange={(e) => setNumx(e.target.value)}
        />
      </p>
      <p>Bình phương của {numx} là: {numx !== "" ? square(Number(numx)) : ""}</p>
      
      <p>Nhập số thứ 1:
        <input
          type="number"
          value={num1}
          onChange={(e) => setNum1(e.target.value)}
        />
      </p>
     
      <p>Nhập số thứ 2:
        <input
          type="number"
          value={num2}
          onChange={(e) => setNum2(e.target.value)}
        />
      </p>
      <p>
          Số lớn hơn giữa {num1} và {num2} là: {(num1 !== "" && num2 !== "") ? max(Number(num1), Number(num2)) : ""}

      </p>
      <p>Mảng chứa các phần tử: 1, 2, 3, 4, 5</p>
      <p>Mảng bình phương của các phần tử là: {squaredArr.join(", ")}</p>
    </>
  );
}

export default App;
