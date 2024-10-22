<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Products</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="ProductServlet?action=new">Add Product</a></li>
                <li><a href="ProductServlet?action=list">View Products</a></li>
                <li><a href="searchProducts.jsp">Search Products</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>Search Products</h2>
        <form action="SearchServlet" method="get">
            <input type="text" name="query" placeholder="Search by Name or Category" required>
            <input type="submit" value="Search">
        </form>
    </main>
    <footer>
        <p>© 2024 Product Management. All rights reserved.</p>
    </footer>
</body>
</html>
