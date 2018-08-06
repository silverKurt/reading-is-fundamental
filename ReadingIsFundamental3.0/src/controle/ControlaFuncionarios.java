package controle;


import dao.FuncionarioDAO;
import entidades.Funcionario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControlaFuncionarios {

    public boolean autenticarFuncionario(HttpServletRequest request, HttpServletResponse response) {
        Funcionario f = new Funcionario();

        f.setEmail(String.valueOf(request.getParameter("email")));
        f.setSenha(String.valueOf(request.getParameter("senha")));
        f = new FuncionarioDAO().confirmarLogin(f.getEmail(), f.getSenha());
        if (f != null) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuarioLogado", f);

            return true;
        } else {
            return false;
        }
    }

    public boolean cadastrarFuncionario(HttpServletRequest request, HttpServletResponse response) {
        Funcionario f = new Funcionario ();

        int id = Integer.parseInt(request.getParameter("id"));

        f.setId(id);
        f.setNome(request.getParameter("nome"));
        f.setSenha(request.getParameter("senha"));
        f.setEmail(request.getParameter("email"));
        f.setStatus('a');
        f.setCargo(request.getParameter("select"));
        f.setLogin(request.getParameter("loginDoUser"));

        String retorno = null;

        if (id == 0) {
            retorno = new FuncionarioDAO().salvar(f);
        } else {
            retorno = new FuncionarioDAO().atualizar(f);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }


}
