import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import "./index.css";
import Navbar from "./home/Navbar";
import Hero from "./home/Hero";
import BestSeller from "./home/BestSeller";
import PopularSeries from "./home/PopularSeries";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Navbar />
    <Hero />
    <BestSeller />
    <PopularSeries />
  </StrictMode>
);
