import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Product> products = productDAO.getAllProducts();
        request.setAttribute("productList", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewProducts.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Product product = new Product(0, name, category, price, stock);
        productDAO.addProduct(product);
        response.sendRedirect("list");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        response.sendRedirect("list");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Product product = new Product(id, name, category, price, stock);
        productDAO.updateProduct(product);
        response.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher =
