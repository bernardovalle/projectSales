package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public void update(Venda venda) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("UPDATE venda SET valorTotal = ? where idvenda = ?");
			stmt.setFloat(1, venda.getVlrTotal());
			stmt.setInt(2, venda.getIdVenda());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}
	
	public ArrayList<Venda> read() {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Venda> listVendaBD =  new ArrayList<Venda>();
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("SELECT * FROM venda");
			rs = stmt.executeQuery();
			// resultset
			while(rs.next()) {
				//replicar o rsultset para o ArrayList
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt("idvenda"));
				venda.setVlrTotal(rs.getFloat("valorTotal"));
				listVendaBD.add(venda);
			}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt, rs);
		}
		return listVendaBD;	
	}
	
	public void delete(Venda venda) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("DELETE from venda where idvenda = ?");
			stmt.setInt(1, venda.getIdVenda());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}

}
