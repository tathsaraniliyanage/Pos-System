# API

The API for the POS System serves as the backend component, designed to operate separately from the frontend. This separation ensures easier maintenance and scalability of the system. The backend follows a RESTful architecture using Resource-Oriented Architecture (ROA) principles, allowing efficient interaction between the frontend and backend through standardized HTTP methods. The controller layer in the API manages the requests and responses, making it simple to handle various resources like products, customers, and orders. 

## Run Project 
Navigate to the backend directory and build the project:

```bash
    cd code/backend
    mvn clean install
