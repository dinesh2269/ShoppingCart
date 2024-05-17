<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
  font-family: "Roboto", sans-serif;
  margin: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #282c37;
  color: #fff;
}

.container {
  width: 100%;
  max-width: 400px;
}

.card {
  background-color: #373d4b;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  color: #61dafb;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 6px;
}

input {
  padding: 10px;
  margin-bottom: 12px;
  border: 1px solid #61dafb;
  border-radius: 4px;
  transition: border-color 0.3s ease-in-out;
  outline: none;
  color: #282c37;
}

input:focus {
  border-color: #90caf9;
}

button {
  background-color: #61dafb;
  color: #282c37;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
}

button:hover {
  background-color: #90caf9;
}
	
</style>
</head>
<body>

<div class="container">
  <div class="card">
    <h2>Login</h2>
    <form action="home">
      <label for="username">Username</label>
      <input type="text" id="username" name="username" required>

      <label for="password">Password</label>
      <input type="password" id="password" name="password" required>

      <button type="submit">Login</button>
    </form>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	
</script>
</body>
</html>