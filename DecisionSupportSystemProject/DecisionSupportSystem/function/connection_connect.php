<?php
    
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "engkps";

    $conn = new mysqli($servername,$username,$password);
    $conn->set_charset("utf8");
    if($conn->connect_error){
        die("Connection failed: ".$conn->connect_error);

    }
   

    if(!$conn->select_db($dbname)){
        echo $conn->connect_error;
    }

   
?>