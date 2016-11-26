import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Database {
	private String dbName;
	private Connection connexion;
	private Statement requete;
	public static Database instanceDB = null;

	private Database (String dbName) {
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
	
	public static Database instanceDB() {
		if(instanceDB == null) {
			instanceDB = new Database("/Users/maxime/Videoclub/Database/testDB.db");
		}
		return instanceDB;
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
	public Hashtable<String,Adherent> genererAdherent(){
		String numeroTel;
		String codeSecret;
		String nom;
		String prenom;
		String adresse;
		String numeroCB; //Adherent 

		SimpleDateFormat sdfSource = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");

		Hashtable<String,Adherent> listeMembre=new Hashtable<String,Adherent>();
		this.connexion();
		ResultSet adh=this.getResultatDe("SELECT * FROM Adherent;");
		try {
			while (adh.next()){
				numeroTel=(adh.getString("numeroTel"));
				codeSecret=(adh.getString("codeSecret"));
				nom=(adh.getString("nom"));
				prenom=(adh.getString("prenom"));
				adresse=(adh.getString("adresse"));
				numeroCB=(adh.getString("numeroCB"));		
				Adherent H=new Adherent(numeroTel,codeSecret,nom,prenom,adresse,numeroCB);
				listeMembre.put(numeroTel,H);
				//System.out.println(H.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("test");
		}
		return listeMembre;
	}
	
	public Hashtable<String,DescriptionArticle> genererDescriptionArticle() {
		Hashtable<String,DescriptionArticle> catalogue = new Hashtable<String,DescriptionArticle>();
		//ArrayList<DescriptionArticle> catalogue = new ArrayList<DescriptionArticle>();
		this.connexion();
		ResultSet desc = this.getResultatDe("SELECT * FROM DescriptionArticle;");
		
		try {
			while (desc.next()) {
				int id = desc.getInt("id");
				String ca = desc.getString("codeArticle");
				String description = desc.getString("description");
				float prixVente = desc.getFloat("prixVente");
				float prixJournalier = desc.getFloat("prixJournalier");
				String titre = desc.getString("titre");
				String genre = desc.getString("genre");
				boolean estNouveau = desc.getBoolean("estNouveau");
				
				DescriptionArticle tmp = new DescriptionArticle(id,ca,description,prixVente,
						prixJournalier,titre,genre,estNouveau);
				//System.out.println(tmp);
				//catalogue.add(tmp);
				catalogue.put(ca,tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catalogue;
	
	}
	
	public void insertLocation(Location l) {
		this.connexion();
		int lastId = 0;
		ResultSet rs = this.getResultatDe("Select count(*) FROM Location;");
		try {
			while(rs.next()) {
				lastId = rs.getInt("count")+1;
			}
			
			for(int i = 0; i < l.getListeLigneArticles().size(); i++) {
				String strSQL = "INSERT INTO Location (id,numeroAdherent,codeBarre,dateHeure,datePrevue,dateRetour,montant) "
						+ "VALUES "
						+ "(id = " + lastId
						+ "numeroAdherent = "+l.getAdherent().getNumeroTel()
						//+ "codeBarre = "+l.getCodeBarre()
						+ "dateHeure = " + l.getDateHeure()
						+ "datePrevue = " + l.getListeLigneArticles().get(i).getDateDue()
						+ "dateRetour = null"
						+ "montant = " + l.getMontant()
						+ ");";
			this.faireRequete(strSQL);			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void insertArticle(DescriptionArticle desc) {
		this.connexion();
		int lastId = 0;
		ResultSet rs = this.getResultatDe("Select count(*) FROM DescriptionArticle;");
		try {
			while(rs.next()) {
				lastId = rs.getInt("count")+1;
			}
			
				String strSQL = "INSERT INTO DescriptionArticle (id,codeArticle,description,prixVente,prixJournalier,titre,genre,estNouveau,prixHebdomadaire) "
						+ "VALUES "
						+ "(id = " + lastId
						+ "codeArticle = " + desc.getCodeArticle();
						+ "description = " + desc.getDescription();
						+ "prixVente = " + desc.getPrixVente();
						+ "prixJournalier = " + desc.getPrixJournalier();
						+ "titre = " + desc.getTitre();
						+ "genre = " + desc.getGenre();
						+ "estNouveau = " + desc.isEstNouveau();
						+ "prixHebdomadaire =" + desc.getPrixHebdomadaire();
						+ ");";
				
		this.faireRequete(strSQL);	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

