package controle;

import dao.AlunosDAO;
import entidades.Aluno;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlaAluno {
    public boolean cadastrarAluno(HttpServletRequest request, HttpServletResponse response) {
        Aluno a = new Aluno();

        int id = Integer.parseInt(request.getParameter("id"));

        a.setId(id);
        a.setNome(request.getParameter("nome"));
        a.setCidade(request.getParameter("cidade"));
        a.setCpf(request.getParameter("cpf"));
        a.setRg("rg");
        a.setTelefone(request.getParameter("telefone"));
        a.setSexo(request.getParameter("selected").charAt(0));
        a.setEmail(request.getParameter("email"));
        a.setStatus('a');
        a.setRua(request.getParameter("rua"));

        String retorno = null;

        if (id == 0) {
            retorno = new AlunosDAO().salvar(a);
        } else {
            retorno = new AlunosDAO().atualizar(a);
        }

        if (retorno == null) {
            return true;
        } else {
            return false;
        }
    }

}
