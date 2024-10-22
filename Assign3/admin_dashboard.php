<?php
include('db.php');
session_start();

// Check if the user is an admin
if (!isset($_SESSION['user_id']) || $_SESSION['role'] != 'admin') {
    header('Location: login.php');  // Redirect to login if not an admin
    exit();
}

// Fetch events and users for management
$events = $conn->query("SELECT * FROM events");
$users = $conn->query("SELECT * FROM users");

echo "<h2>Manage Events</h2>";
while ($event = $events->fetch_assoc()) {
    echo "<h3>" . $event['title'] . "</h3>";
    echo "<a href='edit_event.php?id=" . $event['id'] . "'>Edit</a> | ";
    echo "<a href='delete_event.php?id=" . $event['id'] . "'>Delete</a><br><br>";
}

echo "<h2>Manage Users</h2>";
while ($user = $users->fetch_assoc()) {
    echo "Username: " . $user['username'] . " Email: " . $user['email'] . "<br>";
}

$conn->close();
?>
