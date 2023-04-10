<!DOCTYPE html>
<html>
<head>
	<title>Success Page</title>
	
	<style>


.container {
  width: 50%;
  margin: auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

h2 {
  color: #333;
  text-align: center;
  text-decoration: underline;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: none;
  border-radius: 3px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

input[type="submit"] {
  background-color: #4CAF50;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 3px;
  cursor: pointer;
  font-size: 16px;
}

input[type="submit"]:hover {
  background-color: #3e8e41;
}

.save-btn {
  background-color: #4CAF50;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 3px;
  cursor: pointer;
  font-size: 16px;
  margin-right: 10px; /* Add some margin to separate the buttons */
}

.save-btn:hover {
  background-color: #3e8e41;
}

a {
  display: block;
  text-align: center;
  margin-top: 20px;
  color: #4CAF50;
}

    
  </style>
</head>

<body>
	<div class="container" align="center">
	<h2>Details Saved Successfully</h2>
		<p>To Show click the View Details Button</p>

	<form action="SuccessServlet" method="post">
    <input type="submit" value="View Details">
</form>
 </div>
 <div style="position: absolute; top: 20px; right: 20px;">
 <button class="save-btn" onclick="logout()">Logout</button>
 
 <script type="text/javascript">
function logout() {
    localStorage.removeItem('loggedIn');
    window.location.href = 'login.jsp';
  }
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 </div>
</body>
</html>
