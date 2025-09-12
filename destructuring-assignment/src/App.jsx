import React from "react";
import "./App.css";

export default function App() {
  // 1. Array destructuring
  const arr = [10, 20, 30];
  const [first, second] = arr;

  // 2. Object destructuring
  const student = { name: "An", age: 20, major: "IT" };
  const { name, age } = student;

  // 3. Function parameter destructuring
  const book = { title: "JavaScript Basics", author: "John Doe", year: 2020 };
  function printBookInfo({ title, author, year }) {
    return `Title: ${title}, Author: ${author}, Year: ${year}`;
  }

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Q2.1 Destructuring Assignment</h2>
      <p>
        <strong>Array destructuring:</strong> Cho mảng arr = [10, 20, 30]; Dùng
        destructuring để lấy phần tử đầu tiên và thứ hai.
      </p>
      First: {first}, Second: {second}
      <div style={{ marginTop: "1rem" }}>
        <p style={{ textAlign: "left" }}>
          <strong>Object destructuring:</strong> Cho object: const student ={" "}
          {`{ name: "An", age: 20, major: "IT" }`}; Dùng destructuring để lấy
          name và age.
        </p>
        Name: {name}, Age: {age}
      </div>
      <div style={{ marginTop: "1rem" }}>
        <p style={{ textAlign: "left" }}>
          <strong>Function parameter destructuring:</strong> Cho hàm nhận object
          book = {"{ title, author, year }"}, hãy dùng destructuring ngay trong
          tham số của hàm để in thông tin sách.
        </p>
        {printBookInfo(book)}
      </div>
    </div>
  );
}
