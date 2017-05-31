package CRUD;
import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		CamadaBanco c = new CamadaBanco();
		c.inserirProfesores("", "");
	}

}
