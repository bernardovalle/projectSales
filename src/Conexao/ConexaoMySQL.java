package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoMySQL {
	// info para acesso ao banco

	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/sistema_venda";
	private static String USER = "root";
	private static String PASS = "12345";

	public static Connection iniciarConexao() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
			// se caso não for possivel conectar retorna um erro
		} catch (ClassNotFoundException | SQLException e) { 
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro na conexão: " + e);
		}
	}

	public static void encerrarConexao(Connection conexao) {
		if (conexao != null) {
			// tentar
			try {
				conexao.close();
				// caso não der, pegar.
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
	}

	public static void encerrarConexao(Connection conexao, PreparedStatement stmt) {
		encerrarConexao(conexao);
			// tentar
			try {
				stmt.close();
				//  não der, pegar.
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
	
	
	public static void encerrarConexao(Connection conexao, PreparedStatement stmt, ResultSet rs) {
	encerrarConexao(conexao, stmt);
		// tentar
		try {
			rs.close();
			//  não der, pegar.
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
	}

}

