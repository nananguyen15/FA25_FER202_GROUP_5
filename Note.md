# Project Setup Instructions
## Resquirement: 
Before you begin, please ensure your environment meets the following requirements:
- Java 21: This project requires Java 21. Using a version higher than 21 may cause incompatibility issues with some libraries or annotations.
- MySQL 8
- Docker
- Vscode or other IDE, text editor that can run React
## Setup Stepsn:
- Install Frontend Dependencies Run the following command in your terminal to install the necessary packages (React, Tailwind, TypeScript) defined in package.json:
  ```
  npm i
  ```
- Configure the Database To set up the database correctly, please follow the instructions in this video guide: [Database Setup Video](https://drive.google.com/file/d/1zzH_o-RQQtoanZhAhrFl6w6RQvO0tkWw/view?usp=sharing)
- **Update Database Image Paths:** After setting up the database, open and run the _update-image-paths-to-public.sql_ script. This will migrate the old image folder paths from src/assets/img to the new public/img path.
- **Configure Docker (If New):** If you haven't configured Docker for this project before, or if you need a refresher, please watch this video: [Docker Configuration Video](https://www.youtube.com/watch?v=Oa7bpIZ6RxI&list=PL2xsxmVse9IaxzE8Mght4CFltGOqcG6FC&index=3)
- Run the Backend Application
  + Docker (ensure it is running).
  + Open the project in IntelliJ.
  + Navigate to the following file: back-end/bookverse/src/main/java/com/swp391/bookverse/
  + Run the BookverseApplication.java file to start the backend server.
