package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import Conexao.ConexaoMySQL;
import Model.Produto;

public class ProdutoDAO {

	public void creat(Produto produto) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("INSERT INTO produto (nome, preco, quantidade_estoque) VALUES (?, ?, ?)");
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getQuantidadeEstoque());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}
	
	public void update(Produto produto) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("UPDATE produto SET nome = ?, preco = ?, quantidade_estoque = ? where idproduto = ?");
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getQuantidadeEstoque());
			stmt.setInt(4, produto.getIdProduto());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}
	
	public ArrayList<Produto> read() {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> listProdutoBD =  new ArrayList<Produto>();
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("SELECT * FROM produto");
			rs = stmt.executeQuery();
			// resultset
			while(rs.next()) {
				//replicar o rsultset para o ArrayList
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("idproduto"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				listProdutoBD.add(produto);
			}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt, rs);
		}
		return listProdutoBD;	
	}
	
	public void delete(Produto produto) {
		Connection conexao = ConexaoMySQL.iniciarConexao();
		PreparedStatement stmt = null;
		try {
			// construir o comando SLQ
			stmt = conexao.prepareStatement("DELETE from produto where idproduto = ?");
			stmt.setInt(1, produto.getIdProduto());
			stmt.executeUpdate();
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			ConexaoMySQL.encerrarConexao(conexao, stmt);
		}	
	}

}
