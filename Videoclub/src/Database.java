import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Database {
	private String dbName;
	private Connection connexion;
	private Statement requete;
	private PreparedStatement requeteP;
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
		//L'instance utilisera le bon répertoire peut importe l'ordinateur
		String pathDB;
		//retourne le répertoire du projet et ajoute l'emplacement du la db au path
		pathDB = System.getProperty("user.dir")+"/database/testDB.db"; 
		
		//Maxime:
		//pathDB = "/Users/maxime/Videoclub/Database/testDB.db";
		
		//Samuel:
		//pathDB = "/Users/samuel/Documents/Videoclub/Videoclub/database/testDB.db";
	
		//Ludo:
		//pathDB = "/Users/ludoviccarlu/Github/Videoclub/Videoclub/database/testDB.db";
		
		if(instanceDB == null) {
			instanceDB = new Database(pathDB);
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
	public void faireRequete (String sql)
	{
		try
		{
			this.requete.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Hashtable<Integer,Employe> genererEmploye(){
		int idEmploye;
		String nom;
		String mdp;
		boolean gerant;

		Hashtable<Integer,Employe> listeEmploye=new Hashtable<Integer,Employe>();
		this.connexion();
		ResultSet employe=this.getResultatDe("SELECT * FROM Employe;");
		try {
			while (employe.next()){
				idEmploye=employe.getInt("id");
				nom=(employe.getString("nom"));
				mdp=(employe.getString("mdp"));
				gerant=(employe.getBoolean("gerant"));		
				Employe H=new Employe(idEmploye,nom,gerant,mdp);
				listeEmploye.put(idEmploye,H);
				//System.out.println(H.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("test");
		}
		return listeEmploye;
	}

	public Hashtable<String,Adherent> genererAdherent(){
		String numeroTel;
		String codeSecret;
		String nom;
		String prenom;
		String adresse;
		String numeroCB; //Adherent 

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
				float prixHebdomadaire = desc.getFloat("prixHebdomadaire");
				
				DescriptionArticle tmp = new DescriptionArticle(id, ca,description,prixVente,
						prixJournalier,titre,genre,estNouveau,prixHebdomadaire);
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
	
	//Permet de générer tous les articles de la base de données
	public Hashtable<String,Article> genererArticle() {
		Hashtable<String,Article> listeArticle = new Hashtable<String,Article>();
		this.connexion();
		ResultSet res = this.getResultatDe("SELECT * FROM Article;");
		
		try {
			while (res.next()) {
				String codeBarre = res.getString("codeBarre");
				String codeDesc = res.getString("codeDescription");
				boolean loue = res.getBoolean("estLoue");
				boolean perdu = res.getBoolean("estPerdu");
				
				Article art = new Article(codeBarre,codeDesc,loue,perdu);
				art.toString();
				listeArticle.put(codeBarre, art);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.deconnexion();
		return listeArticle;
	}
	
	//Fonctionne 
	public void insertLocation(Location l) {	//TODO à tester
		this.connexion();
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			//Pour recuperer le dernier id de Location
			int lastId = 0;
			ResultSet rs = this.getResultatDe("SELECT count(*) FROM Location;");
			while (rs.next()) {
				lastId = rs.getInt("count(*)");
				System.out.println(lastId);
			}
			
			requeteP = connexion.prepareStatement("INSERT INTO Location (id,numeroAdherent,codeBarre,dateHeure,datePrevue,dateRetour,montant) "
					+ "VALUES (?,?,?,?,?,?,?);");
			for(int i = 0; i < l.getListeLigneArticles().size(); i++) {
				requeteP.setInt(1,lastId+1);
				requeteP.setString(2,l.getAdherent().getNumeroTel());
				requeteP.setString(3,l.getListeLigneArticles().get(i).getCodeBarreArticle());
				
				//On met les dates au bon format
				/* 
				String dateFormatee = format.format(dateHeure); 
				
				String dateFormatee2 = format.format(dateDue);*/
				
				Date dateHeure = l.getDateHeure();
				Date dateDue = l.getListeLigneArticles().get(i).getDateDue();
				//Format des dates dans la base de donnees
				java.sql.Timestamp ts = new java.sql.Timestamp(dateHeure.getTime());
				requeteP.setString(4, ts.toString());
				java.sql.Timestamp ts2 = new java.sql.Timestamp(dateDue.getTime());
				requeteP.setString(5, ts2.toString());
				
				/*requeteP.setString(4,dateFormatee.toString());
				requeteP.setString(5,dateFormatee2.toString());*/
				//La date de retour doit etre a null car la location vient de commencer
				requeteP.setString(6, null);
				requeteP.setFloat(7, l.getMontant());
				
				requeteP.executeUpdate();
				this.faireRequete("UPDATE Article SET estLoue = 'true' WHERE codeBarre='"+l.ligneArticle.get(i).getCodeBarreArticle()+"';");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		this.deconnexion();
		

	}
	/**
	 * Ajoute un nouvel article à la database. 
	 * @param desc
	 */
	public void insertArticle(DescriptionArticle desc) {
		//Testé et fonctionne :)
		try {
		this.connexion();
		requeteP = connexion.prepareStatement("INSERT INTO DescriptionArticle ('codeArticle','description','prixVente','prixJournalier',"
				+ "'titre','genre','estNouveau','prixHebdomadaire') VALUES(?,?,?,?,?,?,?,?)");
		
		requeteP.setString(1, desc.getCodeArticle());
		requeteP.setString(2, desc.getDescription());
		requeteP.setFloat(3, desc.getPrixVente());
		requeteP.setFloat(4,desc.getPrixJournalier());
		requeteP.setString(5,desc.getTitre());
		requeteP.setString(6, desc.getGenre());
		requeteP.setBoolean(7, desc.getEstNouveau());
		requeteP.setFloat(8, desc.getPrixHebdomadaire());
		
		requeteP.executeUpdate();
		//this.faireRequete(strSQL);
		this.deconnexion();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertAdherent(Adherent ad) {
		//Testé et fonctionnel 
		try {
			this.connexion();
			String sql = "INSERT INTO Adherent ('numeroTel','codeSecret','nom','prenom','adresse','numeroCB') VALUES (?,?,?,?,?,?);";
			requeteP = connexion.prepareStatement(sql);
			requeteP.setString(1, ad.getNumeroTel());
			requeteP.setString(2, ad.getCodeSecret());
			requeteP.setString(3, ad.getNom());
			requeteP.setString(4, ad.getPrenom());
			requeteP.setString(5, ad.getAdresse());
			requeteP.setString(6, ad.getNumeroCB());
			requeteP.executeUpdate();
			
			//N'oubliez pas de vous deconnecter apres avoir fait vos requetes
			this.deconnexion();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	//Ne produit pas d'erreur mais pas tester
	public ArrayList<Location> genererLocation() {
		ArrayList<Location> listeLocation = new ArrayList<Location>();
		try {
			this.connexion();
			ResultSet res = this.getResultatDe("SELECT * FROM Location");
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			while (res.next()) {
				int id = res.getInt("id");
				String numAd = res.getString("numeroAdherent");
				String codeBarre = res.getString("codeBarre").toString();
				
				java.sql.Timestamp ts = res.getTimestamp("dateHeure");
				java.util.Date dateHeure = new java.util.Date(ts.getTime());
				
				java.sql.Timestamp ts2 = res.getTimestamp("datePrevue");
				java.util.Date dateDue = new java.util.Date(ts2.getTime());
				
				java.sql.Timestamp ts3 = res.getTimestamp("dateRetour");
				java.util.Date dateRetour = null;
				if(ts3 != null) {
					dateRetour = new java.util.Date(ts3.getTime());
				}
				
				System.out.println(dateHeure);
				System.out.println(dateDue);
				System.out.println(dateRetour);

				float montant = res.getFloat("montant");
				
				listeLocation.add(new Location(id,numAd,codeBarre,dateHeure,dateDue,dateRetour,montant));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listeLocation;
	}

}

