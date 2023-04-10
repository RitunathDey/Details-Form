<html>
<head>
<title>Registration Form</title>
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

a {
  display: block;
  text-align: center;
  margin-top: 20px;
  color: #4CAF50;
}

    
  </style>
<script type="text/javascript">
function validateForm() {
    var name = document.forms["registerForm"]["name"].value;
    var email = document.forms["registerForm"]["email"].value;
    var emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    if (!emailPattern.test(email)) {
      alert("Please enter a valid email address.");
      return false;
    }
    var password = document.forms["registerForm"]["password"].value;
    if (name == "") {
        alert("Name must be filled out");
        return false;
    }
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
<h2>Registration Form</h2>
<form name="registerForm" action="RegisterServlet" onsubmit="return validateForm()" method="post">
Name: <input type="text" name="name"><br><br>
Email: <input type="text" name="email"><br><br>
Password: <input type="password" name="password"><br><br>
<input type="submit" value="Submit">
</form>
<a href="login.jsp">I am already a member</a>
</div>
</body>
</html>
