import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Database {
	private String dbName;
	public Connection connexion;
	private Statement requete;

	public Database (String dbName) {
		// Charge le driver sqlite JDBC en utilisant le class loader actuel
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e1)
		{
			System.err.println(e1.getMessage());
		}

		this.dbName = dbName;
		this.connexion = null;
	}

	/* Ouvre la base de données spécifiées
	 * Return true si la connexion reussit / false sinon
	 */

	public boolean connexion ()
	{
		try
		{
			// Etabli la connection
			this.connexion = DriverManager.getConnection("jdbc:sqlite:"+this.dbName);
			// Déclare l'objet qui permet de faire les requêtes
			this.requete = connexion.createStatement();

			// Le PRAGMA synchronous de SQLite permet de vérifier chaque écriture
			// avant d'en faire une nouvelle.
			// Le PRAGMA count_changes de SQLite permet de compter le nombre de
			// changements fait sur la base
			// Résultats de mes tests :
			// synchronous OFF, une insertion est 20 fois plus rapide.
			// La différences avec le count_changes est de l'ordre de la µs.
			// Les autres PRAGMA : http://www.sqlite.org/pragma.html

			requete.executeUpdate("PRAGMA synchronous = OFF;");
			requete.setQueryTimeout(30);

			return true;
		}
		catch(SQLException e)
		{
			// message = "out of memory" souvent le resultat de la BDD pas trouvée
			e.printStackTrace();
			return false;
		}
	}
	public boolean deconnexion ()
	{
		try
		{
			if(this.connexion != null)
				this.connexion.close();

			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permet de faire une requête SQL
	 * @param requete La requête SQL (avec un ";" à la fin)
	 * @return Un ResultSet contenant le résultat de la requête
	 */
	public ResultSet getResultatDe (String requete)
	{
		try
		{
			return this.requete.executeQuery(requete);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * Permet de modifier une entrée de la base de données.</br>
	 * @param requete La requete SQL de modification
	 */
	public void faireRequete (String requete)
	{
		try
		{
			this.requete.executeUpdate(requete);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<Adherent> genererAdherent(){
		String numeroTel;
		String codeSecret;
		String nom;
		String prenom;
		String adresse;
		String numeroCB; //Adherent 

		String codeBarre; //Location
		Date dateHeure;
		Date datePrevue;
		Date dateRetour;
		float montant;
		SimpleDateFormat sdfSource = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");

		ArrayList<Adherent> listeMembre=new ArrayList<Adherent>();
		Database D=new Database("/Users/maxime/Videoclub/Database/testDB.db"); // A CHANGER
		D.connexion();
		Database T=new Database("/Users/maxime/Videoclub/Database/testDB.db"); // A CHANGER
		ResultSet adh=D.getResultatDe("SELECT * FROM Adherent;");
		T.connexion();
		try {
			while (adh.next()){
				numeroTel=(adh.getString("numeroTel"));
				codeSecret=(adh.getString("codeSecret"));
				nom=(adh.getString("nom"));
				prenom=(adh.getString("prenom"));
				adresse=(adh.getString("adresse"));
				numeroCB=(adh.getString("numeroCB"));
				ResultSet loc=T.getResultatDe("SELECT * FROM Location WHERE numeroAdherent='"+numeroTel+"'");
				ArrayList<Location> list=new ArrayList<Location>();
				Adherent H=new Adherent(numeroTel,codeSecret,nom,prenom,adresse,numeroCB);
				listeMembre.add(H);
				System.out.println(H.toString());
				try {
					while (loc.next()){
						codeBarre=(loc.getString("codeBarre"));
						dateHeure=sdfSource.parse(loc.getString("dateHeure"));
						datePrevue=sdfSource.parse(loc.getString("datePrevue"));
						dateRetour=sdfSource.parse(loc.getString("dateRetour"));
						montant=(loc.getFloat("montant"));
						Location L=new Location(dateHeure,datePrevue,dateRetour,H,montant);
						list.add(L);
						System.out.println(L.toString());
					}
					if (list!=null){
					H.setListeLocation(list);
					}
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.print("test");
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("test");
		}
		return listeMembre;
	}
	public static void main(String[] args) {
		Database D=new Database("/Users/maxime/Videoclub/Database/testDB.db");
		System.out.println(D.connexion());
		/*String sql = "CREATE TABLE Adherent("
				+ "numeroTel INTEGER PRIMARY KEY NOT NULL,"
				+ "codeSecret INTEGER NOT NULL,"
				+ "nom VARCHAR(50),"
				+ "prenom VARCHAR(50),"
				+ "adresse TEXT NOT NULL,"
				+ "numeroCB INTEGER NOT NULL,"
				+ "dateInscription DATETIME NOT NULL"
				+ ");";

				D.faireRequete(sql);*/

		//String sql = "INSERT INTO Adherent(numeroTel,codeSecret,nom,prenom,adresse,numeroCB,dateInscription) VALUES (1,1,'ma','AZ','12',1,'11/11/2012');";
		//D.faireRequete(sql);
		ResultSet res=D.getResultatDe("SELECT * FROM Adherent;");
		ArrayList<Adherent> F=D.genererAdherent();
		ArrayList<Location> X=F.get(2).getListeLocation();
	}
}

