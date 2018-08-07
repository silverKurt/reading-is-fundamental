package dao;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidades.Funcionario;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionarioDAO implements IDAO<Funcionario> {


    @Override
    public String salvar(Funcionario f) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO funcionarios VALUES ("
                    + "DEFAULT,"
                    + "'" + f.getNome() + "',"
                    + "'" + f.getCargo() + "',"
                    + "'" + f.getSenha() + "',"
                    + "'a',"
                    + "'"+f.getLogin()+"',"
                    + "'"+f.getEmail()+"'"
                    + ")";

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado != 0) {
                return null;
            }
            return "xxx";

        } catch (Exception e) {
            System.out.println("Erro ao salvar funcionário = " + e);
            return e.toString();
        }

    }

    @Override
    public String atualizar(Funcionario f) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE Funcionario "
                    + "SET nome = '" + f.getNome() + "',"
                    + "cargo = '" + f.getCargo() + "',"
                    + "senha = '" + f.getSenha() + "',"
                    + "usuario = '"+f.getLogin()+"', "
                    + "email = '"+f.getEmail()+"' "
                    + "WHERE id = " + f.getId();

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Deu erro ao atualizar: " + e);
            return e.toString();
        }

        return null;

    }

    @Override
    public String excluir(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update Funcionario set status = 'i'"
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Deu erro ao deletar: " + e);
            return e.toString();
        }

        return null;
    }
    public Funcionario confirmarLogin(String a, String b) {

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM funcionarios "
                    + "WHERE email = '" + a + "' AND senha = '" + b + "' AND status = 'a' ORDER BY id";
            ResultSet resultado = st.executeQuery(sql);
            if (resultado.next()) {
                Funcionario f1 = new Funcionario();

                f1.setId(Integer.parseInt(resultado.getString("id")));
                f1.setNome(resultado.getString("nome"));
                f1.setSenha(resultado.getString("senha"));
                f1.setCargo(resultado.getString("cargo"));
                f1.setStatus(resultado.getString("status").charAt(0));
                f1.setLogin(resultado.getString("usuario"));
                f1.setEmail(resultado.getString("email"));
                return f1;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro: " + e);
            return null;
        }

    }
    @Override
    public ArrayList<Funcionario> consultarTodos() {
        ArrayList<Funcionario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Funcionario ORDER BY id";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Funcionario u = new Funcionario();

                u.setId(resultado.getInt("id"));
                u.setNome(resultado.getString("nome"));
                u.setLogin(resultado.getString("usuario"));
                u.setStatus(resultado.getString("status").charAt(0));
                u.setEmail(resultado.getString("email"));
                u.setCargo(resultado.getString("cargo"));
                usuarios.add(u);
            }

        } catch (Exception e) {
            System.out.println("erro ao consultar usuários: " + e);
        }

        return usuarios;
    }

    @Override
    public boolean registroUnico(Funcionario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Funcionario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM Funcionario "
                    + "WHERE id = " + id;

            ResultSet resultado = st.executeQuery(sql);

            if (resultado.next()) {
                Funcionario f = new Funcionario();

                f.setId(id);
                f.setNome(resultado.getString("nome"));
                f.setCargo(resultado.getString("cargo"));
                f.setSenha(resultado.getString("senha"));
                f.setLogin(resultado.getString("usuario"));
                f.setEmail(resultado.getString("email"));

                return f;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Deu erro: " + e);
            return e.toString();
        }
    }

    @Override
    public boolean consultar(Funcionario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
