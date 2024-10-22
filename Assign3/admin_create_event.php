<?php
include('db.php');
session_start();

// Check if the user is an admin (this requires an admin flag in the user table)
if (!isset($_SESSION['user_id']) || $_SESSION['role'] != 'admin') {
    header('Location: login.php');  // Redirect to login if not an admin
    exit();
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $title = $_POST['title'];
    $description = $_POST['description'];
    $date = $_POST['date'];
    $time = $_POST['time'];
    $location = $_POST['location'];
    $price = $_POST['price'];

    $sql = "INSERT INTO events (title, description, date, time, location, price) 
            VALUES ('$title', '$description', '$date', '$time', '$location', '$price')";
    
    if ($conn->query($sql) === TRUE) {
        echo "Event created successfully!";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
    
    $conn->close();
}
?>
<!-- HTML form for event creation -->
<form method="post" action="admin_create_event.php">
    Title: <input type="text" name="title" required><br>
    Description: <textarea name="description" required></textarea><br>
    Date: <input type="date" name="date" required><br>
    Time: <input type="time" name="time" required><br>
    Location: <input type="text" name="location" required><br>
    Price: <input type="number" name="price" step="0.01" required><br>
    <input type="submit" value="Create Event">
</form>
