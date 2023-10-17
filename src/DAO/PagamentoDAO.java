package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexao.ConexaoMySQL;
import Model.Pagamento;

public class PagamentoDAO {
	
	public void creat(Pagamento pagamento) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("INSERT INTO tipopagamento (tipoPagamento) VALUES (?)");
			stmt.setDouble(1, pagamento.getTipoPagamento());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}

}
