<?php

$servername = "mysql.hostinger.co.uk";
$database = "u940148205_drone";
$username = "u940148205_root";
$password = "Drone123";
 
 // Create connection

$conn = mysqli_connect($servername, $username, $password, $database);

// Check connection
if (!$conn) {
     die("Connection failed: " . mysqli_connect_error());
}

echo "Connected successfully";

$sql = "INSERT INTO DroneMembers (id, firstname, lastname, username, password) VALUES ('12', 'Vial', 'tepp', 'denistepp', 'qwe1asd1')";
if (mysqli_query($conn, $sql)) {
     echo "New record created successfully";
} else {
     echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);

?>
