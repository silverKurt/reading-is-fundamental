package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidades.alunos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AlunosDAO implements IDAO<alunos> {
    @Override
    public String salvar(alunos al) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO alunos VALUES ("
                    + "DEFAULT,"
                    + "'" + al.getNome() + "',"
                    + "'" + al.getCpf() + "',"
                    + "'" + al.getRg() + "',"
                    + "'" + al.getTelefone() + "',"
                    + "'" + al.getSexo() + "',"
                    + "'" + al.getEmail() + "',"
                    + " " + al.getId_cidade() + ", "
                    + " 'a', "
                    + "'" + al.getRua() + "' "
                    + ")";

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro salvar Aluno = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(alunos al) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE alunos "
                    + "set nome = '" + al.getNome() + "',"
                    + "cpf = '" + al.getCpf() + "',"
                    + "rg = '" + al.getRg() + "',"
                    + "telefone = '" + al.getTelefone() + "',"
                    + "sexo = '" + al.getSexo() + "',"
                    + "email = '" + al.getEmail() + "',"
                    + "id_cidade = " + al.getId_cidade() + ","
                    + "rua = '" + al.getRua() + "' "
                    + "where id = " + al.getId();

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Deu erro ao atualizar: " + e);
            return e.toString();
        }

        return null;
    }
    public int consultar_2(String criterio) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT codcidade FROM cidade c, estado e "
                    + "WHERE c.nome ILIKE '" + criterio + "' and c.estado_codestado = e.codestado and e.nome = 'Rio Grande do Sul'";

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {

                return Integer.parseInt(resultado.getString("codcidade"));

            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Deu erro: " + e);
            return 0;
        }
    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE alunos set status = 'i'"
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Deu erro ao deletar: " + e);
            return e.toString();
        }

        return null;
    }
    public boolean consultarCPF(String cpf) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM alunos "
                    + "WHERE cpf = '" + cpf + "'";

            ResultSet resultado = st.executeQuery(sql);
            return resultado.next();

        } catch (Exception e) {
            System.out.println("Deu erro: " + e);
            return true;
        }
    }

    @Override
    public ArrayList<alunos> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registroUnico(alunos o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<alunos> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM alunos "
                    + "WHERE id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                alunos al = new alunos();

                al.setId(id);
                al.setNome(resultado.getString("nome"));
                al.setCpf(resultado.getString("cpf"));
                al.setRg(resultado.getString("rg"));
                al.setTelefone(resultado.getString("telefone"));
                al.setSexo(resultado.getString("sexo").charAt(0));
                al.setEmail(resultado.getString("email"));
                al.setId_cidade(Integer.parseInt(resultado.getString("id_cidade")));
                al.setRua(resultado.getString("rua"));
                return al;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(alunos o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
