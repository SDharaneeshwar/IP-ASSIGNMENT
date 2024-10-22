<?php
include('db.php');

$sql = "SELECT * FROM events ORDER BY date";
$result = $conn->query($sql);

while($event = $result->fetch_assoc()) {
    echo "<h3>" . $event['title'] . "</h3>";
    echo "<p>" . $event['description'] . "</p>";
    echo "<p>Date: " . $event['date'] . " Time: " . $event['time'] . "</p>";
    echo "<p>Location: " . $event['location'] . " Price: $" . $event['price'] . "</p>";
    echo "<a href='book_event.php?event_id=" . $event['id'] . "'>Book Ticket</a><br><br>";
}

$conn->close();
?>
