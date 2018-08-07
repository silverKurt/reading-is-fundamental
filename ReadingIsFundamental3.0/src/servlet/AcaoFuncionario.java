package servlet;

import controle.ControlaFuncionarios;
import dao.FuncionarioDAO;
import entidades.Funcionario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "/AcaoFuncionario")
public class AcaoFuncionario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao aaaa aaaaaaaat " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        System.out.println("Entrei no GET Funcionarios!");

        String parametro = req.getParameter("parametro");
        System.out.println("Parametro: " + parametro);

        if (parametro.equals("edUsuario")) {
            int id = Integer.parseInt(String.valueOf(req.getParameter("id")));
            Funcionario usu = (Funcionario) new FuncionarioDAO().consultarId(id);

            req.setAttribute("objuser", usu);
            encaminharPagina("usuario.jsp", req, resp);
        } else if (parametro.equals("exUsuario")) {
            int id = Integer.parseInt(String.valueOf(req.getParameter("id")));

            String retorno = new FuncionarioDAO().excluir(id);

            req.setAttribute("paginaOrigem", "usuario.jsp");

            if (retorno == null) {
                encaminharPagina("sucesso.jsp", req, resp);
            } else {
                encaminharPagina("erro.jsp", req, resp);
            }
        }else if (parametro.equals("logout")) {
            System.out.println("LOGOUTTTTTT");
            HttpSession sessao = req.getSession();
            sessao.invalidate();
            resp.sendRedirect("login.jsp");
        }

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
        }else if (parametro.equals("login")) {
            if (new ControlaFuncionarios().autenticarFuncionario(req, resp)) {
                //encaminharPagina("menu.jsp", request, response);
                resp.sendRedirect("menu.jsp");
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
