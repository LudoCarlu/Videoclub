import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class DisplayPanel extends JPanel implements ActionListener{
	private int type=0;
	private JTextField pseudo;
	private JTextField mdp;
	private JButton valider;
	JFrameGestionnaire fenetre;
	private JTable table;
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
	private JButton btnAjouter =null;

	
	
	public DisplayPanel(int choix,JFrameGestionnaire F){
		this.type=choix;
		setBackground(Color.BLUE);
		this.fenetre=F;
		String [] title={"Item","Quantite","Prix"};
		Location L=new Location(Date.from(ZonedDateTime.now().with(LocalTime.MIN).toInstant()),new Date("13/12/1021"), null, null);
		Object[][] data = {
			      {L.getDateRetour(),L.getAmende(),L.getDateDue()},
			      {"BZHHydde", "28 ans", "1.80 m"},
			      {"IamBow", "24 ans", "1.90 m"},
			      {"FunMan", "32 ans", "1.85 m"}
			    };
		
		
		if (choix==0){
			JLabel titre = new JLabel("Authentifcation");
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
			JLabel titre = new JLabel("Location");
			titre.setBounds(200,5, 90, 20);
			add(titre);
			table = new JTable(data,title);
			table.setBounds(60,40,500,80);
			add(table);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(60, 40, 500, 80);
			add(scrollPane);
			
			JLabel lblSaisirArticle = new JLabel("Code Article");
			lblSaisirArticle.setBounds(40, 180, 90, 20);
			add(lblSaisirArticle);
			
			codeArticle = new JTextField("0000");
			codeArticle.setBounds(120, 177, 130, 25);
			add(codeArticle);
			codeArticle.setColumns(10);
			
			this.btnAjouter= new JButton("Ajouter");
			btnAjouter.setBounds(430, 175, 120, 30);
			add(btnAjouter);
			
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
			
			JButton btnPayer = new JButton("Payer");
			btnPayer.setBounds(430, 440, 115, 30);
			add(btnPayer);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "7"}));
			comboBox.setBounds(300, 181, 70, 20);
			add(comboBox);
			
			JLabel lblJours = new JLabel("Jours");
			lblJours.setBounds(368, 182, 61, 16);
			add(lblJours);
			
	}
		if (choix==2){
			setLayout(null);
			JLabel titre = new JLabel("Vente");
			titre.setBounds(200,5, 90, 20);
			add(titre);
			table = new JTable(data,title);
			table.setBounds(60,40,500,80);
			add(table);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(60, 40, 500, 80);
			add(scrollPane);
			
			JLabel lblSaisirArticle = new JLabel("Code Article");
			lblSaisirArticle.setBounds(40, 180, 90, 20);
			add(lblSaisirArticle);
			
			codeArticle = new JTextField("0000");
			codeArticle.setBounds(120, 177, 130, 25);
			add(codeArticle);
			codeArticle.setColumns(10);
			
			JButton btnAjouter = new JButton("Ajouter");
			btnAjouter.setBounds(430, 175, 120, 30);
			add(btnAjouter);
			
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
			
			JButton btnPayer = new JButton("Payer");
			btnPayer.setBounds(430, 440, 115, 30);
			add(btnPayer);
			
			quantite = new JTextField("1");
			quantite.setBounds(280, 177, 60, 26);
			add(quantite);
			quantite.setColumns(10);
			
			
		}
			
		if (choix==3){
			setLayout(null);
			JLabel titre = new JLabel("Inscription");
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
					System.out.println(nombreAleatoire);
					mdp.setText(String.valueOf(nombreAleatoire));
				}
			});
			btnGenerer.setBounds(430, 310, 117, 29);
			add(btnGenerer);
			
			JButton btnValider = new JButton("Valider");
			btnValider.setBounds(430, 429, 117, 29);
			add(btnValider);
			
			JTextArea adresse = new JTextArea();
			adresse.setLineWrap(true);
			adresse.setColumns(10);
			adresse.setRows(1);
			adresse.setBounds(250, 134, 130, 69);
			add(adresse);
			
			
		}

		
		if (choix==4){
			setLayout(null);
			JLabel titre = new JLabel("Retours");
			titre.setBounds(276, 5, 48, 16);
			add(titre);
			
			codeArticle = new JTextField();
			codeArticle.setBounds(140, 119, 130, 26);
			add(codeArticle);
			codeArticle.setColumns(10);
			
			JLabel lblCodeArticle = new JLabel("Code Article:");
			lblCodeArticle.setBounds(31, 124, 83, 16);
			add(lblCodeArticle);
			
			JButton btnEffectuerUnRetour = new JButton("Effectuer un retour");
			btnEffectuerUnRetour.setBounds(400, 119, 144, 29);
			add(btnEffectuerUnRetour);
			
		}
		if (choix==5){
			setLayout(null);
			JLabel titre = new JLabel("Gerer les retards");
			titre.setBounds(264, 5, 150, 16);
			add(titre);
		}
			
		if (choix==6){
			JLabel titre = new JLabel("Acquisition");
			titre.setBounds(264, 5, 72, 16);
			add(titre);
			setLayout(null);
			
			codeArticle = new JTextField();
			codeArticle.setBounds(140, 119, 130, 26);
			add(codeArticle);
			codeArticle.setColumns(10);
			
			JLabel lblCodeArticle = new JLabel("Code Article:");
			lblCodeArticle.setBounds(31, 124, 83, 16);
			add(lblCodeArticle);
			
			JButton btnAcquisition = new JButton("Effectuer une acquisition");
			btnAcquisition.setBounds(400, 119, 200, 30);
			add(btnAcquisition);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== this.valider){
			this.fenetre.setControlPanel();
			this.fenetre.setDisplayPanel(new DisplayPanel(1,this.fenetre));
		}
		if(e.getSource()==this.btnAjouter && this.type==1){
			
		}
		
	}
}
