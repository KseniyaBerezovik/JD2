package setvlet;

import by.itacademy.ClientService;
import by.itacademy.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = ClientService.getClientByID(1L);
        req.setAttribute("client", client);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/client.jsp").forward(req, resp);
    }
}
