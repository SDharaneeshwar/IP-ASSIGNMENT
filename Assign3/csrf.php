<?php
session_start();

// Generate a CSRF token if one does not exist
if (empty($_SESSION['csrf_token'])) {
    $_SESSION['csrf_token'] = bin2hex(random_bytes(32));
}

// Check CSRF token
function checkCsrfToken($token) {
    return hash_equals($_SESSION['csrf_token'], $token);
}
?>
