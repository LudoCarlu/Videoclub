import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.text.DecimalFormat;
import java.awt.Font;

public class DisplayPanel extends JPanel implements ActionListener{
	private int type=0;
	private JLabel titre;
	private JTextField pseudo;
	private JTextField mdp;
	private JButton valider;
	JFrameGestionnaire fenetre;
	private JTable table;
	private DefaultTableModel defaultModel;
	private JTextField codeArticle;
	private JTextField totalCourant;
	private JTextField tps;
	private JTextField tpq;
	private JTextField montantTotal;
	private JTextField quantite;
	private JTextField numTel;
	private JTextField carteDeCredit;
	private JTextField nom;
	private JTextField prenom;
	private JButton btnAjouter = null;
	private JButton btnPayer =null;
	private JComboBox<String> duree;
	private JTextArea adresse;
	private JButton btnEffectuerUnRetour = null;
	private JButton btnAmende = null;
	private JTextField numLocation = null;
	private JTextField titleA;
	private JTextField genre;
	private JTextArea description;
	private JTextField prixVente;
	private JTextField prixJournalier;
	private JTextField prixHebdo;
	private JTextField estNouveau;
	private JTextField qte;
	
	

	public DisplayPanel(int choix,JFrameGestionnaire F){
		this.type=choix;
		setBackground(Color.WHITE);
		this.fenetre=F;
		String [] title={"Item","Quantité","Durée","Prix Journalier","Prix total"};
		String [] descProduit={"Code Article","titre","prix de vente","description"};
		//Location L=new Location(Date.from(ZonedDateTime.now().with(LocalTime.MIN).toInstant()),new Date("13/12/1021"), null, null);
		Object[][] data = {};

		if (choix==-1){ // Menu principale
			this.titre = new JLabel("Menu");
			titre.setFont(new Font("Adobe Garamond Pro", Font.PLAIN, 22));
			titre.setBounds(189, 27, 90, 27);
			add(titre);


		}

		if (choix==0){ // Authentification caissier
			setLayout(null);
			this.titre = new JLabel("Authentifcation Employe"); 
			add(titre);
			this.titre.setBounds(250,20, 200,20);

			JLabel id = new JLabel("Identifiant : ");
			add(id);
			id.setBounds(20,100,200,20);

			pseudo = new JTextField();
			add(pseudo);
			pseudo.setColumns(10);
			pseudo.setBounds(130,100,200,20);

			JLabel jl = new JLabel("Mot de passe : ");
			add(jl);
			jl.setBounds(20,140,200,20);
			mdp = new JTextField();
			add(mdp);
			mdp.setColumns(10);
			mdp.setBounds(130,140,200,20);

			this.valider= new JButton("Valider");
			add(this.valider);
			this.valider.addActionListener(this);
			this.valider.setBounds(250,200,100,20);
		}
		if (choix==7){ 
			this.titre = new JLabel("Authentifcation Location");
			add(titre);

			pseudo = new JTextField();
			add(pseudo);
			pseudo.setColumns(10);


			mdp = new JTextField();
			add(mdp);
			mdp.setColumns(10);
			this.valider= new JButton("Valider");
			add(this.valider);
			this.valider.addActionListener(this);
		}

		if(choix==1){ 
			setLayout(null);
			this.titre = new JLabel("Location");
			titre.setBounds(200,5, 90, 20);
			add(titre);
			defaultModel=new DefaultTableModel(data, title);
			table = new JTable(defaultModel);

			table.setBounds(60,40,500,80);
			add(table);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(60, 40, 500, 80);
			add(scrollPane);

			JLabel lblSaisirArticle = new JLabel("Code Barre");
			lblSaisirArticle.setBounds(40, 180, 90, 20);
			add(lblSaisirArticle);

			codeArticle = new JTextField("0");
			codeArticle.setBounds(120, 177, 130, 25);
			add(codeArticle);
			codeArticle.setColumns(10);

			this.btnAjouter= new JButton("Ajouter");
			btnAjouter.setBounds(430, 175, 120, 30);
			add(btnAjouter);
			this.btnAjouter.addActionListener(this);

			JLabel lblQt = new JLabel("Durée:");
			lblQt.setBounds(248, 183, 60, 15);
			add(lblQt);

			JButton btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setBounds(430, 220, 120, 30);
			add(btnSupprimer);

			totalCourant = new JTextField();
			totalCourant.setEditable(false);
			totalCourant.setBounds(430, 275, 130, 25);
			add(totalCourant);
			totalCourant.setColumns(10);

			tps = new JTextField();
			tps.setEditable(false);
			tps.setColumns(10);
			tps.setBounds(430, 315, 130, 25);
			add(tps);

			tpq = new JTextField();
			tpq.setEditable(false);
			tpq.setColumns(10);
			tpq.setBounds(430, 350, 130, 25);
			add(tpq);

			JLabel lblNewLabel = new JLabel("Total Courant:");
			lblNewLabel.setBounds(300, 280, 115, 15);
			add(lblNewLabel);

			JLabel lblTps = new JLabel("TPS:");
			lblTps.setBounds(301, 320, 115, 15);
			add(lblTps);

			JLabel lblTpq = new JLabel("TPQ:");
			lblTpq.setBounds(302, 355, 115, 15);
			add(lblTpq);

			montantTotal = new JTextField();
			montantTotal.setEditable(false);
			montantTotal.setColumns(10);
			montantTotal.setBounds(430, 394, 130, 25);
			add(montantTotal);

			JLabel lblMontantTotal = new JLabel("Montant Total:");
			lblMontantTotal.setBounds(300, 400, 115, 15);
			add(lblMontantTotal);

			this.btnPayer = new JButton("Payer");
			btnPayer.setBounds(430, 440, 115, 30);
			add(btnPayer);
			this.btnPayer.addActionListener(this);

			this.duree = new JComboBox();
			duree.setModel(new DefaultComboBoxModel(new String[] {"1", "7"}));
			duree.setBounds(300, 181, 70, 20);
			add(duree);

			JLabel lblJours = new JLabel("Jours");
			lblJours.setBounds(368, 182, 61, 16);
			add(lblJours);

			quantite = new JTextField("1");
			quantite.setBounds(310, 206, 60, 26);
			add(quantite);
			quantite.setColumns(10);

			JLabel Qte = new JLabel("Qté");
			Qte.setBounds(247, 214, 61, 16);
			add(Qte);

		}
		//VENTE
		if (choix==2){ 
			setLayout(null);
			this.titre = new JLabel("Vente");
			titre.setBounds(200,5, 90, 20);
			add(titre);


			DefaultTableModel model = new DefaultTableModel();
			table = new JTable(model);
			model.addColumn("Item");
			model.addColumn("Code");
			model.addColumn("Prix u");
			model.addColumn("Quantité");	
			//table = new JTable(data,titleV);
			table.setBounds(60,40,500,80);
			add(table);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(60, 40, 500, 80);
			add(scrollPane);

			fenetre.getController().initierVente();

			JLabel lblSaisirArticle = new JLabel("Code Article");
			lblSaisirArticle.setBounds(40, 180, 90, 20);
			add(lblSaisirArticle);

			this.codeArticle = new JTextField("");
			this.codeArticle.setBounds(120, 177, 130, 25);
			add(codeArticle);
			this.codeArticle.setColumns(10);

			this.btnAjouter = new JButton("Ajouter");
			btnAjouter.setBounds(430, 175, 120, 30);
			add(btnAjouter);
			this.btnAjouter.addActionListener(this);

			JLabel lblQt = new JLabel("Qté:");
			lblQt.setBounds(248, 183, 60, 15);
			add(lblQt);

			JButton btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setBounds(430, 220, 120, 30);
			add(btnSupprimer);

			totalCourant = new JTextField();
			totalCourant.setEditable(false);
			totalCourant.setBounds(430, 275, 130, 25);
			add(totalCourant);
			totalCourant.setColumns(10);

			tps = new JTextField();
			tps.setEditable(false);
			tps.setColumns(10);
			tps.setBounds(430, 315, 130, 25);
			add(tps);

			tpq = new JTextField();
			tpq.setEditable(false);
			tpq.setColumns(10);
			tpq.setBounds(430, 350, 130, 25);
			add(tpq);

			JLabel lblNewLabel = new JLabel("Total Courant:");
			lblNewLabel.setBounds(300, 280, 115, 15);
			add(lblNewLabel);

			JLabel lblTps = new JLabel("TPS:");
			lblTps.setBounds(301, 320, 115, 15);
			add(lblTps);

			JLabel lblTpq = new JLabel("TPQ:");
			lblTpq.setBounds(302, 355, 115, 15);
			add(lblTpq);

			montantTotal = new JTextField();
			montantTotal.setEditable(false);
			montantTotal.setColumns(10);
			montantTotal.setBounds(430, 394, 130, 25);
			add(montantTotal);

			JLabel lblMontantTotal = new JLabel("Montant Total:");
			lblMontantTotal.setBounds(300, 400, 115, 15);
			add(lblMontantTotal);

			this.btnPayer = new JButton("Payer");
			btnPayer.setBounds(430, 440, 115, 30);
			add(btnPayer);
			this.btnPayer.addActionListener(this);

			quantite = new JTextField("1");
			quantite.setBounds(280, 177, 60, 26);
			add(quantite);
			quantite.setColumns(10);



		}

		if (choix==3){ // Inscription
			setLayout(null);
			this.titre = new JLabel("Inscription");
			titre.setBounds(0, 0, 600, 26);
			titre.setVerticalAlignment(SwingConstants.TOP);
			titre.setHorizontalAlignment(SwingConstants.CENTER);
			add(titre);

			JLabel numtTel = new JLabel("Numero de telephone:");
			numtTel.setBounds(30, 100, 150, 20);
			add(numtTel);

			JLabel lbladresse = new JLabel("Adresse:");
			lbladresse.setBounds(30, 132, 100, 20);
			add(lbladresse);

			JLabel lblcarteDeCredit = new JLabel("Empreinte carte de crédit:");
			lblcarteDeCredit.setBounds(30, 220, 174, 51);
			add(lblcarteDeCredit);

			numTel = new JTextField();
			numTel.setBounds(250, 97, 130, 26);
			add(numTel);
			numTel.setColumns(10);

			carteDeCredit = new JTextField();
			carteDeCredit.setColumns(10);
			carteDeCredit.setBounds(250, 232, 130, 26);
			add(carteDeCredit);

			JLabel lblNom = new JLabel("Nom:");
			lblNom.setBounds(69, 50, 61, 16);
			add(lblNom);

			nom = new JTextField();
			nom.setBounds(121, 45, 130, 26);
			add(nom);
			nom.setColumns(10);

			JLabel lblPrenom = new JLabel("Prenom:");
			lblPrenom.setBounds(305, 50, 61, 16);
			add(lblPrenom);

			prenom = new JTextField();
			prenom.setBounds(400, 45, 130, 26);
			add(prenom);
			prenom.setColumns(10);

			JLabel lblMotDePasse = new JLabel("Mot De Passe:");
			lblMotDePasse.setBounds(30, 315, 100, 16);
			add(lblMotDePasse);

			mdp = new JTextField();
			mdp.setEditable(false);
			mdp.setBounds(250, 310, 130, 26);
			add(mdp);
			mdp.setColumns(10);

			JButton btnGenerer = new JButton("Generer");
			Random rand = new Random();
			btnGenerer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Random rand = new Random();
					int nombreAleatoire = rand.nextInt(9999 - 1000 + 1) + 1000;
					mdp.setText(String.valueOf(nombreAleatoire));
				}
			});
			btnGenerer.setBounds(430, 310, 117, 29);
			add(btnGenerer);

			adresse = new JTextArea();
			adresse.setLineWrap(true);
			adresse.setColumns(10);
			adresse.setRows(1);
			adresse.setBounds(250, 134, 130, 69);
			add(adresse);

			this.valider = new JButton("Valider");
			this.valider.setBounds(430, 429, 117, 29);
			add(this.valider);
			this.valider.addActionListener(this);

		}

		//Retours
		if (choix==4){
			setLayout(null);
			this.titre = new JLabel("Retours");
			titre.setBounds(276, 5, 48, 16);
			add(titre);

			codeArticle = new JTextField();
			codeArticle.setBounds(140, 119, 130, 26);
			add(codeArticle);
			codeArticle.setColumns(10);

			JLabel lblCodeArticle = new JLabel("CodeBarre :");
			lblCodeArticle.setBounds(31, 124, 83, 16);
			add(lblCodeArticle);

			this.btnEffectuerUnRetour = new JButton("Effectuer un retour");
			this.btnEffectuerUnRetour.setBounds(400, 119, 144, 29);
			this.add(btnEffectuerUnRetour);
			this.btnEffectuerUnRetour.addActionListener(this);

		}
		if (choix==5){ //Retard / Amende
			setLayout(null);
			this.titre = new JLabel("Gerer les retards");
			titre.setBounds(264, 5, 150, 16);
			add(titre);

			this.defaultModel = new DefaultTableModel();
			table = new JTable(defaultModel);
			defaultModel.addColumn("Location");
			defaultModel.addColumn("Adherent");
			defaultModel.addColumn("Code Barre");
			defaultModel.addColumn("Prix Amende");
			table.setBounds(60,40,500,400);
			add(table);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(60, 40, 500, 80);
			add(scrollPane);

			this.btnAmende = new JButton("Generer les amendes");
			this.btnAmende.setBounds(230,150,150, 20);
			this.add(btnAmende);
			this.btnAmende.addActionListener(this);

			JLabel paiement = new JLabel("Payer une amende : ");
			paiement.setBounds(60,200,200,20);
			add(paiement);

			JLabel num = new JLabel("Adherent numero :");
			this.numTel = new JTextField();
			num.setBounds(60,230,150,20);
			add(num);
			numTel.setBounds(220,230,150,20);
			add(numTel);

			JLabel loc = new JLabel("Location numero : ");
			this.numLocation = new JTextField();
			loc.setBounds(60,260,150,20);
			add(loc);
			numLocation.setBounds(220,260,150,20);
			add(numLocation);

			JLabel c = new JLabel("codeBarre : ");
			this.codeArticle = new JTextField();
			c.setBounds(60,290,150,20);
			add(c);
			codeArticle.setBounds(220,290,150,20);
			add(codeArticle);


			this.btnPayer = new JButton("Payer");
			this.btnPayer.addActionListener(this);
			this.btnPayer.setBounds(60,340,70,20);
			add(this.btnPayer);

		}

		if (choix==6){
			Hashtable<String,DescriptionArticle> list;
			list=this.fenetre.getController().getCatalogue().getList();

			this.titre = new JLabel("Acquisition");
			titre.setBounds(264, 5, 72, 16);
			add(titre);
			setLayout(null);

			this.defaultModel = new DefaultTableModel();
			table = new JTable(defaultModel);
			defaultModel.addColumn("Code Article");
			defaultModel.addColumn("Titre");
			defaultModel.addColumn("Prix de vente");
			defaultModel.addColumn("Description");
			table.setBounds(60,40,500,400);
			add(table);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(31, 78, 500, 80);
			add(scrollPane);

			Set<String> keys =list.keySet();
			DefaultTableModel t = (DefaultTableModel)table.getModel();
			for(String k: keys) {
				DescriptionArticle desc=list.get(k);
				//String [] descProduit={"Code Article","titre","prix de vente","description"};
				String code =desc.getCodeArticle();
				String titre =desc.getTitre();
				String prix = new Float(desc.getPrixJournalier()).toString();
				String description =desc.getDescription();
				Object[] dataV = {code,titre,prix,description};

				t.addRow(dataV);
			}
			table.setModel(t);
			t.fireTableDataChanged();
			
			JTextArea txtrDescriptionsDisponibles = new JTextArea();
			txtrDescriptionsDisponibles.setEditable(false);
			txtrDescriptionsDisponibles.setText("Descriptions disponibles :");
			txtrDescriptionsDisponibles.setBounds(31, 50, 172, 16);
			add(txtrDescriptionsDisponibles);
			
			JLabel jl = new JLabel("Acquerir un article");
			JLabel jl2 = new JLabel("Si l'article n'est pas un film de pas remplir les champs");
			JLabel jl3 = new JLabel ("genre,prix journalier, prix hebdomadaire, nouveauté");
			
			JLabel lblCodeArticle = new JLabel("Code Article:");
			JLabel jl4 = new JLabel("Titre");
			JLabel jl5 = new JLabel("Genre");
			JLabel jl6 = new JLabel("Description");
			JLabel jl7 = new JLabel("prix journalier");
			JLabel jl8 = new JLabel("prix Hebdomadaire");
			JLabel jl9 = new JLabel("prix de vente");
			JLabel jl10 = new JLabel("Nouveauté");
			JLabel jl11 = new JLabel("quantité");
			
			jl.setBounds(200,160,150,16);
			jl2.setBounds(30,180,500,16);
			jl3.setBounds(30,200,500,16);
			lblCodeArticle.setBounds(30,220,150,16);
			jl4.setBounds(30,240,150,16);
			jl5.setBounds(30,260,150,16);
			jl6.setBounds(30,280,150,16);
			jl7.setBounds(30,320,150,16);
			jl8.setBounds(30,340,150,16);
			jl9.setBounds(30,360,150,16);
			jl10.setBounds(30,380,150,16);
			jl11.setBounds(30,400,150,16);
			
			add(jl);
			add(jl2);
			add(jl3);
			add(lblCodeArticle);
			add(jl4);
			add(jl5);
			add(jl6);
			add(jl7);
			add(jl8);
			add(jl9);
			add(jl10);
			add(jl11);
			
			codeArticle = new JTextField();
			titleA = new JTextField();
			genre = new JTextField();
			description = new JTextArea();
			prixJournalier = new JTextField();
			prixHebdo = new JTextField();
			prixVente = new JTextField();
			estNouveau = new JTextField();
			quantite = new JTextField();
			
			codeArticle.setBounds(170,220,130,16);
			titleA.setBounds(170,240,130,16);
			genre.setBounds(170,260,130,16);
			description.setBounds(170,280,130,40);
			description.setLineWrap(true);
			description.setColumns(10);
			description.setRows(1);
			prixJournalier.setBounds(170,320,130,16);
			prixHebdo.setBounds(170,340,130,16);
			prixVente.setBounds(170,360,130,16);
			estNouveau.setBounds(170,380,130,16);
			quantite.setBounds(170,400,130,16);
			

			add(codeArticle);
			add(description);
			add(titleA);
			add(genre);
			add(prixJournalier);
			add(prixHebdo);
			add(prixVente);
			add(estNouveau);
			add(quantite);
			
			
			
			JButton btnAcquisition = new JButton("Effectuer une acquisition");
			btnAcquisition.setBounds(402, 156, 200, 30);
			add(btnAcquisition);



		}

	}
	/*
	 *  type -1 : Menu,
	 *  type 0 : authentification caissier
	 *  type 1 : location
	 *   type 2 : vente(non-Javadoc)
	 *  type 5 : Retard
	 *  type 6 : acquisition
	 *  type 7 : authentification location
	 */
	@Override
	public void actionPerformed(ActionEvent e) { 

		if (this.type==0){ // authentification caissier
			if (e.getSource()== this.valider){
				Employe caissier=this.fenetre.getController().authentificationEmploye(pseudo.getText(),mdp.getText());
				if (caissier!=null){
					this.fenetre.setDisplayPanel(new DisplayPanel(-1,this.fenetre));
					this.fenetre.setControlPanel();
				}
				else {
					JOptionPane.showMessageDialog(this,"Erreur d'authentification","Erreur..",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (this.type==7){ // authentification location
			if(e.getSource()==this.valider){
				if (this.fenetre.getController().authentificationMembre(pseudo.getText(), mdp.getText())!=null){
					this.fenetre.setDisplayPanel(new DisplayPanel(1,this.fenetre));
					this.fenetre.setControlPanel();
				}
				else {
					JOptionPane.showMessageDialog(this,"Erreur d'authentification","Erreur..",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (this.type==1){ //Location
			if (e.getSource()==this.btnAjouter){
				Controler c= this.fenetre.getController();
				String code= this.codeArticle.getText();
				int duree= Integer.parseInt(this.duree.getSelectedItem().toString());
				ArrayList <LigneArticle>list=c.getLocation().getListeLigneArticles();

				if (c.saisirArticleLoc(code,duree)){
					LigneArticle l= list.get(list.size()-1);
					String[] data={ l.getDescriptionArticle().getTitre(),
							new Integer(l.getQuantite()).toString(),
							new Integer(duree).toString(),
							new Float(l.getDescriptionArticle().getPrixJournalier()).toString(),
							new Float(l.getPrixLocation()*duree).toString()
					};
					this.defaultModel.addRow(data);
				};
				//System.out.print(this.fenetre.getController().getLocation());
				this.quantite.setText("1");
				this.duree.setSelectedIndex(0);
				this.codeArticle.setText("0");
				this.totalCourant.setText(Float.toString(c.getLocation().getMontant()));
				DecimalFormat df = new DecimalFormat("0.00");
				Location loc=c.getLocation();
				totalCourant.setText(df.format(loc.getMontant()) + " $");
				double ttps = loc.getMontant()*0.05;
				double ttvq = (loc.getMontant()+ttps)*0.0975;
				tps.setText(df.format(ttps) + " $");
				tpq.setText(df.format(ttvq) + " $");
				montantTotal.setText(df.format(loc.getMontant() + ttps + ttvq) + " $");
			}
			if (e.getSource()==this.btnPayer){
				this.fenetre.getController().terminerLocation();
				this.fenetre.setControlPanel();
				this.fenetre.setDisplayPanel(new DisplayPanel(-1,this.fenetre));
			}
		}
		if (this.type==3){ // Inscription
			if(e.getSource() == this.valider){
				if(numTel.getText().isEmpty() == false || carteDeCredit.getText().isEmpty() == false 
						|| adresse.getText().isEmpty() == false || mdp.getText().isEmpty() == false) {
					this.fenetre.getController().Inscription(nom.getText(), prenom.getText(), 
							numTel.getText(), carteDeCredit.getText(), adresse.getText(), mdp.getText());
					this.nom.setText("");
					this.prenom.setText("");
					this.numTel.setText("");
					this.adresse.setText("");
					this.carteDeCredit.setText("");
					this.mdp.setText("");
				}
				else {
					JOptionPane.showMessageDialog(this,"Veuillez remplir tout les champs","Erreur..",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(this.type == 4) {
			if(e.getSource() == this.btnEffectuerUnRetour) {
				if(codeArticle.getText().isEmpty() == false) {
					this.fenetre.getController().effectuerUnRetour(codeArticle.getText());
					this.codeArticle.setText("");
				}
			}
		}

		if(this.type == 5) { //Retard
			if(e.getSource() == btnAmende) {
				this.fenetre.getController().gererRetard();
				Hashtable<Integer,Location> am = this.fenetre.getController().getListeLocation();
				Set<Integer> keys = am.keySet();
				for(Integer k: keys) {
					Location loc = am.get(k);
					if(loc.getAmende() != null) {
						for(int i=0; i < loc.getAmende().size(); i++) {
							if(loc.getAmende().get(i).isTerminee() == false) {
								String[] data = {
										new Integer(am.get(k).getIdLoc()).toString(),
										am.get(k).getAdherent().getNumeroTel(),
										loc.getAmende().get(i).getCodeBarre(),
										new Float(loc.getAmende().get(i).getMontant()).toString()
								};
								this.defaultModel.addRow(data);
							}
						}
					}
				}
			}

			if(e.getSource() == btnPayer) {
				Controler c = this.fenetre.getController();
				JLabel ok;
				if(numLocation.getText().isEmpty() == false && numTel.getText().isEmpty() == false 
						&& codeArticle.getText().isEmpty() == false) {
					c.finAmende(Integer.parseInt(numLocation.getText()),numTel.getText(),codeArticle.getText());
					ok = new JLabel("Paiement accepté !");
					ok.setBounds(200,340,150,20);
					add(ok);
					repaint();
				}
				else {
					ok = new JLabel("Il manque un élément") ;
					ok.setBounds(200,340,200,20);
					add(ok);
				}


			}

		}
		if(this.type == 2) { //vente
			Controler c = this.fenetre.getController();

			if(e.getSource() == btnAjouter) {
				if(codeArticle.getText() != "") {

					c.creerligneVente(this.codeArticle.getText(), 1);

					int position = c.instanceVente().getListeLigneArticles().size()-1;
					LigneArticle lar = c.instanceVente().getListeLigneArticles().get(position);

					DefaultTableModel m = (DefaultTableModel)table.getModel();

					String item = lar.getArticle().getDescription().getDescription();
					String code = lar.getCodeBarreArticle();
					String prixU = lar.getPrixVente()+" $";
					int qte = lar.getQuantite();
					Object[] dataV = {item,code,prixU,qte};

					m.addRow(dataV);

					table.setModel(m);
					m.fireTableDataChanged();
					c.instanceVente().majMontant();

					DecimalFormat df = new DecimalFormat("0.00");

					totalCourant.setText(df.format(c.instanceVente().getMontant()) + " $");
					double ttps = c.instanceVente().getMontant()*0.05;
					double ttvq = (c.instanceVente().getMontant()+ttps)*0.0975;
					tps.setText(df.format(ttps) + " $");
					tpq.setText(df.format(ttvq) + " $");
					montantTotal.setText(df.format(c.instanceVente().getMontant() + ttps + ttvq) + " $");

				}
			}

			if (e.getSource() == btnPayer){
				if (c.instanceVente().getMontant()>0){

					for (int i= 0; i<c.instanceVente().getListeLigneArticles().size();i++){
						LigneArticle lar = c.instanceVente().getListeLigneArticles().get(i);

						for (int j = 0; j<lar.getQuantite();j++){
							c.instanceInventaire().retirerArticle(lar.getArticle());
						}
					}
					c.terminerVente();
					codeArticle.setText("");
					tps.setText("");
					tpq.setText("");
					montantTotal.setText("");
					totalCourant.setText("");
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
				}
			}
		}
	}
}
