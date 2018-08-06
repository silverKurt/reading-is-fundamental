package servlet;

import controle.ControlaFuncionarios;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcaoFuncionario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        System.out.println("Entrei no POST!");
        String parametro = req.getParameter("parametro");

        if (parametro.equals("cadUsuario")) {

            req.setAttribute("paginaOrigem", "usuario.jsp");

            if (new ControlaFuncionarios().cadastrarFuncionario(req, resp)) {
                encaminharPagina("sucesso.jsp", req, resp);
            } else {
                encaminharPagina("erro.jsp", req, resp);
            }
        }
    }

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
    }
}
