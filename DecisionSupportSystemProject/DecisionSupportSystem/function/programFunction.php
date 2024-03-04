<?php


    function getProgramById($programId){

        require("connection_connect.php");

        $sql = "SELECT * FROM program WHERE programId = ".$programId;
        $result = $conn->query($sql);
        $program = $result->fetch_assoc();

        require("connection_close.php");

        return $program;

    }


?>