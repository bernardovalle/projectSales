package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexao.ConexaoMySQL;
import Model.Produto;
import Model.Venda;

public class VendaDAO {
	
	public void creat(Venda venda) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("INSERT INTO venda (valorTotal) VALUES (?)");
			stmt.setDouble(1, venda.getVlrTotal());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}

}
