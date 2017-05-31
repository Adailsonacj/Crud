package CRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CamadaBanco {

	Connection conecta;
	PreparedStatement pst;
	ResultSet rs;

	public CamadaBanco() {
		try {
			conecta = Conexao.conexao();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet listarUsuarios() throws SQLException {
		ResultSet rs1;
		String sql = "SELECT * FROM academica.professores;";
		
			pst = conecta.prepareStatement(sql);
			rs1 = pst.executeQuery();
		
			/*
		ResultSetMetaData metaDados = rs1.getMetaData();
		
		int numeroColunas = metaDados.getColumnCount();
		for (int iIndex = 1; iIndex <= numeroColunas; iIndex++) {
			System.out.println(metaDados.getColumnName(iIndex));
		}

		while (rs1.next()) {
			for (int iIndex = 1; iIndex <= numeroColunas; iIndex++) {
				System.out.println(rs1.getObject(iIndex));
			}
		}
		rs1.close();
		pst.close();
		conecta.close();
		*/
		return rs1;
	}

	public void inserirProfesores(String nome, String sobrenome) throws SQLException {
		// INSERÇÃO NA TABELA
		String sqlInsert = "INSERT INTO professores (nome, sobrenome) VALUES (?, ?);";
		try {
			pst = conecta.prepareStatement(sqlInsert);
			if(nome != ""||sobrenome != ""){
			pst.setString(1, nome);
			pst.setString(2, sobrenome);
			pst.execute();
			}
		} catch (SQLException error) {
		}
	}

	public void cunsultaEstática() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/academica?useSSL=false", "root",
				"root");

		Statement consultaEstatica = conexao.createStatement();
		ResultSet resultadoEstatico = consultaEstatica
				.executeQuery("SELECT nome, sigla FROM professores INNER JOIN professoresUniversidades "
						+ "WHERE professores.idProfessor = professoresUniversidades.idProfessor "
						+ "AND  nome= 'silvano' ;");
		ResultSetMetaData metaDados = resultadoEstatico.getMetaData();

		int numeroColunas = metaDados.getColumnCount();
		for (int iIndex = 1; iIndex <= numeroColunas; iIndex++) {
			System.out.println(metaDados.getColumnName(iIndex));
		}

		while (resultadoEstatico.next()) {
			for (int iIndex = 1; iIndex <= numeroColunas; iIndex++) {
				System.out.println(resultadoEstatico.getObject(iIndex));
			}
		}
		resultadoEstatico.close();
		consultaEstatica.close();
		conexao.close();
	}

	public void consultaDinamica(String nome) throws SQLException {

		// CRIA VARIAVEL DE REFERENCIA DO OBJETO DE CONSULTA
		String sqlConsultaDinamica = "SELECT nome, sigla FROM professores INNER JOIN professoresUniversidades "
				+ "WHERE professores.idProfessor = professoresUniversidades.idProfessor " + "AND  nome= ? ;";
		pst = conecta.prepareStatement(sqlConsultaDinamica);
		pst.setString(1, nome);
		rs = pst.executeQuery();
		ResultSetMetaData metaDados = pst.getMetaData();

		int numeroColunas = metaDados.getColumnCount();
		for (int iIndex = 1; iIndex <= numeroColunas; iIndex++) {
			System.out.println(metaDados.getColumnName(iIndex));
		}

		while (rs.next()) {
			for (int iIndex = 1; iIndex <= numeroColunas; iIndex++) {
				System.out.println(rs.getObject(iIndex));
			}
		}
		rs.close();
		pst.close();
		conecta.close();
	}

	public void excluiProfessor(int iD) throws SQLException {
		// Exlusão de professores na Tabela
		String sqlDelete = "DELETE from professores where idProfessor  = ?;";
		pst = conecta.prepareStatement(sqlDelete);
		pst.setInt(1, iD);
		pst.execute();
	}

	public void excluiInstituicao() throws SQLException {

	}
	
	public void atribuirInstituicao(int iD, String sigla)throws SQLException{
		String sql = "INSERT INTO professoresUniversidades (idProfessor, sigla)VALUES (?,?)";
		try{
			pst = conecta.prepareStatement(sql);
			pst.setInt(1, iD);
			pst.setString(2, sigla);
			pst.execute();
		}catch (SQLException error) {
		}
	}
	
	public void desatribuirInstituicao(int iD)throws SQLException{
		String sql = "DELETE FROM professoresUniversidades WHERE idProfessor = ?;";
		pst = conecta.prepareStatement(sql);
		pst.setInt(1, iD);
		pst.execute();
	}
}
