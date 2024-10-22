<?php
include('db.php');
session_start();

if (!isset($_SESSION['user_id'])) {
    header('Location: login.php');  // Redirect to login if not logged in
    exit();
}

if (isset($_GET['event_id'])) {
    $event_id = $_GET['event_id'];
    $user_id = $_SESSION['user_id'];

    $sql = "INSERT INTO bookings (user_id, event_id) VALUES ('$user_id', '$event_id')";
    
    if ($conn->query($sql) === TRUE) {
        echo "Ticket booked successfully!";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
    
    $conn->close();
}
?>
