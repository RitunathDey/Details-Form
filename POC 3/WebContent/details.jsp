<html>
<head>
<title>Details Form</title>
<style>
form {
  max-width: 500px;
  margin: auto;
}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}
input[type=file], input[type=date] {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
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



/* style for the form container */
.container {
  width: 50%;
  margin: auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

/* style for the form headings */
h2 {
  color: #333;
  text-align: center;
  text-decoration: underline;
}

/* style for the error messages */
.error {
  color: red;
  font-size: 14px;
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
    var mobile = document.forms["detailsForm"]["mobile"].value;
    var address = document.forms["detailsForm"]["address"].value;
    var department = document.forms["detailsForm"]["department"].value;
    var location = document.forms["detailsForm"]["location"].value;
    var dob = document.forms["detailsForm"]["dob"].value;
    var photo = document.forms["detailsForm"]["photo"].value;
    
    if (mobile == "") {
        alert("Mobile number must be filled out");
        return false;
    }
    if (address == "") {
        alert("Address must be filled out");
        return false;
    }
    if (department == "") {
        alert("Department must be selected");
        return false;
    }
    if (location == "") {
        alert("Current location must be filled out");
        return false;
    }
    if (dob == "") {
        alert("Date of Birth must be filled out");
        return false;
    }
    if (photo == "") {
        alert("Photo must be uploaded");
        return false;
   }
}
</script>
</head>
<body>
<div class="container" align="center">
<h2>Details Form</h2>
<form name="detailsForm" action="DetailsServlet" onsubmit="return validateForm()" method="post" enctype="multipart/form-data">
Mobile Number: <input type="text" name="mobile"><br><br>
Address: <textarea name="address"></textarea><br><br>
Department: 
<select name="department">
  <option value="">--Select--</option>
  <option value="Engineering">Engineering</option>
  <option value="Business">Business</option>
  <option value="Support">Support</option>
</select><br><br>
Current Location: <input type="text" name="location"><br><br>
Photo: <input type="file" name="photo"><br><br>
Date of Birth: <input type="date" name="dob" id="dob" onchange="calculateAge(this.value)"><br><br>
Age: <input type="text" name="age" disabled><br><br>
<input type="submit" value="Save">
</form>
<br>
<a href="success.jsp">I have already submitted my Details.</a>
<br>

<script type="text/javascript">
function calculateAge(dob) {
    var today = new Date();
    var birthDate = new Date(dob);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    document.forms["detailsForm"]["age"].value = age;
}
</script>
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
