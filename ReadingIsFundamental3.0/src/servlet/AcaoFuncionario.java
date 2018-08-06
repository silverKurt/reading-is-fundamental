package servlet;

import controle.ControlaFuncionarios;
import dao.FuncionarioDAO;
import entidades.Funcionario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcaoFuncionario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        System.out.println("Entrei no GET!");

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
