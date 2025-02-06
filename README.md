
# **Spring Security JWT**  
A simple REST API using Spring Security and JWT.  

## **Setup**  

### **Prerequisites**  
- **IDE Recommendation**: Use **IntelliJ IDEA**. Ensure that the **Lombok** plugin is installed.  
- **Database**: The project uses **MySQL** as the database.  

### **Configuration**  
After downloading the project, update the database configuration in the `application.properties` file located at:  

📂 `src/main/resources/application.properties`  

Modify the following lines with your MySQL credentials:  
```properties
spring.datasource.username=root  
spring.datasource.password=yourpassword  
```

---

## **Instructions**  

### **Testing the API with Postman**  
#### 1️⃣ **Create a New Role**  
Since the database is empty initially, you must first create a **Role** and **User**.  

- **Method**: `POST`  
- **URL**: `http://localhost:8080/api/roles`  
- **Body**:  
  - Go to **Body** → **Raw** → Select **JSON**  
  - Provide the role details:  
  ```json
  {
     "name": "ROLE_ADMIN"
  }
  ```

#### 2️⃣ **Create a New User**  
- **Method**: `POST`  
- **URL**: `http://localhost:8080/api/users`  
- **Body**:  
  - Go to **Body** → **Raw** → Select **JSON**  
  - Provide the user details:  
  ```json
  {
      "username": "sasuke",
      "password": "sasuke123",
      "enabled": true,
      "roles": [
          "ROLE_ADMIN"
      ]
  }
  ```

#### 3️⃣ **Login**  
Once the Role and User are created, you can proceed with login.  

- **Method**: `POST`  
- **URL**: `http://localhost:8080/api/auth`  
- **Body**:  
  - Go to **Body** → **Raw** → Select **JSON**  
  - Provide the login credentials:  
  ```json
  {
      "username": "sasuke",
      "password": "sasuke123"
  }
  ```
- If the login is successful, you will receive a **token**.  
- This token can be used to access protected endpoints without providing a username and password again.  

---

### **Using the Token for Authorization**  

#### 4️⃣ **Get All Roles**  
- **Method**: `GET`  
- **URL**: `http://localhost:8080/api/roles`  
- **Authorization**:  
  - Go to **Authorization** → **Type** → Select **Bearer Token**  
  - Enter the token received from login.  

**Example Token:**  
```
eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtb2hhbWVjIiwiaWF0IjoxNzM4ODI4OTE1LCJleHAiOjE3Mzg4MjkwMzV9.dtJiE1jUhyweQQtTSgEiSvpmeeDRTrNghdHzc5wqGuoFn1pcD_-YMNVlr9t6Sa3C
```

---

#### 5️⃣ **Get All Users**  
- **Method**: `GET`  
- **URL**: `http://localhost:8080/api/users`  
- **Authorization**:  
  - Go to **Authorization** → **Type** → Select **Bearer Token**  
  - Enter the token received from login.  

**Example Token:**  
```
eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJzYXN1a2UiLCJpYXQiOjE3Mzg4MzUwMTAsImV4cCI6MTczODgzNTEzMH0.uOA-C-gDysNZ8Lxja_1drfg0ppprKFCWh2ljAh6udxAX5rr-06-sfPrv4u92yJLW
```

---

### **Final Notes**  
- Ensure your **database is running** before starting the application.  
