# Sign Language Application Backend

## Overview

Welcome to the backend of the Sign Language Application! This Spring Boot project is designed to handle authentication, real-time communication, user management, and media uploads seamlessly. It leverages JWT for secure authentication, WebSocket for real-time chat, MongoDB for efficient data management, and Docker for streamlined deployment. Additionally, it integrates Cloudinary for handling image uploads.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [WebSocket Communication](#websocket-communication)
- [Docker Deployment](#docker-deployment)
- [Contributing](#contributing)
- [Contact](#contact)
- [License](#license)

## Features

- **JWT Authentication**: Secure user authentication and authorization.
- **Real-time WebSocket Chat**: Instant messaging between users.
- **User Management**: Create, read, update, and delete users in MongoDB.
- **Image Upload with Cloudinary**: Upload and manage images efficiently.
- **Dockerized Deployment**: Easy deployment using Docker containers.

## Prerequisites

- **Java 11 or higher**
- **Maven 3.6+**
- **MongoDB**
- **Docker (optional, for containerization)**

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/sign-language-backend.git
   cd sign-language-backend

2. **Install Dependencies
     ```bash
     mvn clean install
Configuration
-------------

1.  **MongoDB Setup**

    Ensure MongoDB is running on your machine or configure it in the `application.properties` file.

2.  **Cloudinary Configuration**

    Sign up for a [Cloudinary](https://cloudinary.com/) account and obtain your API credentials.

3.  **Application Properties**

    Update `src/main/resources/application.properties` with your configurations:

    properties


    `# MongoDB Configuration
    spring.data.mongodb.uri=mongodb://localhost:27017/sign-language-db

    # JWT Configuration
    jwt.secret=your_jwt_secret
    jwt.expiration=86400000

    # Cloudinary Configuration
    cloudinary.cloud_name=your_cloud_name
    cloudinary.api_key=your_api_key
    cloudinary.api_secret=your_api_secret`

Running the Application
-----------------------

bash


`mvn spring-boot:run`

The application will start on `http://localhost:8080`.

API Endpoints
-------------

### Authentication

-   **Register User**



    `POST /auth/register`

-   **Login User**



    `POST /auth/login`

### User Management

-   **Get All Users**



    `GET /users`

-   **Get User by ID**


    `GET /users/{id}`

-   **Update User**


    `PUT /users/{id}`

-   **Delete User**



    `DELETE /users/{id}`

### Image Upload

-   **Upload Image**



    `POST /images/upload`

WebSocket Communication
-----------------------

-   **Endpoint**



    `/chat`

-   **Description**

    This endpoint facilitates real-time chat between users using WebSocket protocol.

Docker Deployment
-----------------

1.  **Build Docker Image**



    `docker build -t sign-language-backend .`

2.  **Run Docker Container**



    `docker run -p 8080:8080 sign-language-backend`

Contributing
------------

Contributions are welcome! Please open an issue or submit a pull request for any improvements.

Contact
-------

Developed by **Taha Hamdi**
Hamdi.taha@esprit.tn
