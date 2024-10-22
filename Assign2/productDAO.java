public import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/product_management";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password"; // replace with your MySQL password

    // Method to get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // Method to add a product to the database
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, category, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.executeUpdate();
        }
    }

    // Method to get all products
    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("category"), rs.getDouble("price"), rs.getInt("stock"));
                productList.add(product);
            }
        }
        return productList;
    }

    // Method to delete a product by ID
    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Method to update a product
    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, category = ?, price = ?, stock = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        }
    }

    // Method to search products by name or category
    public List<Product> searchProducts(String searchQuery) throws SQLException {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ? OR category LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + searchQuery + "%");
            stmt.setString(2, "%" + searchQuery + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(rs.getInt("id"), rs.getString("name"),
                            rs.getString("category"), rs.getDouble("price"), rs.getInt("stock"));
                    productList.add(product);
                }
            }
        }
        return productList;
    }
}
