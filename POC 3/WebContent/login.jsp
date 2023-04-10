<!DOCTYPE html>
<html>
<head>
	<title>Login Form</title>
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
	</style>
	<script type="text/javascript">
		function validateForm() {
		    var email = document.forms["loginForm"]["email"].value;
		    var password = document.forms["loginForm"]["password"].value;
		    if (email == "") {
		        alert("Email must be filled out");
		        return false;
		    }
		    if (password == "") {
		        alert("Password must be filled out");
		        return false;
		    }
		}
	</script>
</head>
<body>
	<div class="container" align="center">
		<h2>Login Form</h2>
		<form name="loginForm" action="LoginServlet" onsubmit="return validateForm()" method="post">
			Email: <input type="text" name="email"><br><br>
			Password: <input type="password" name="password"><br><br>
			<input type="submit" value="Login">
			
		</form>
	</div>
</body>
</html>
