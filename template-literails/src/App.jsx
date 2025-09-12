import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  // Tạo chuỗi chào mừng
  const name = "Bí";
  const age = 20;
  const greeting = `Xin chào, tôi tên là ${name}, năm nay ${age} tuổi.`;

  // Function nhận vào tên sản phẩm và giá
  const productInfo = (productName, price) => {
    return `Sản phẩm: ${productName}\nGiá: ${price.toLocaleString('vi-VN')} VNĐ`;
  };

  // Chuỗi nhiều dòng (multi-line string) hiển thị đoạn thơ
  const poem = `
Bầu ơi thương lấy bí cùng,

Tuy rằng khác giống nhưng chung một giàn.
  `;

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
      <div className="App">
        <header className="App-header">
          <p>{greeting}</p>
          <pre>{productInfo("Laptop", 15000000)}</pre>
          <pre>{poem}</pre>
        </header>
        <p className="read-the-docs">
          Click on the Vite and React logos to learn more
        </p>
      </div>
    </>
  );
}

export default App
