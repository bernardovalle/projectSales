package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexao.ConexaoMySQL;
import Model.Pagamento;
import Model.Venda;

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
	
	public void update(Pagamento pagamento) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("UPDATE tipopagamento SET tipoPagamento = ? where idTiPagamento = ?");
			stmt.setInt(1, pagamento.getTipoPagamento());
			stmt.setInt(2, pagamento.getTipoPagamento());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}
	
	public ArrayList<Pagamento> read() {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Pagamento> listPagamentoBD =  new ArrayList<Pagamento>();
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("SELECT * FROM tipopagamento");
			rs = stmt.executeQuery();
			// resultset
			while(rs.next()) {
				//replicar o rsultset para o ArrayList
				Pagamento pag = new Pagamento();
				pag.setTipoPagamento(rs.getInt("idTipoPagamento"));
				pag.setTipoPagamento(rs.getInt("tipoPagamento"));
				listPagamentoBD.add(pag);
			}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt, rs);
		}
		return listPagamentoBD;	
	}
	
	public void delete(Pagamento Pagamento) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("DELETE from tipagamento where idTipoPagamento = ?");
			stmt.setInt(1, Pagamento.getIdTipoPagamento());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}

}
