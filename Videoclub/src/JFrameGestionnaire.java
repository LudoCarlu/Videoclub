import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JFrameGestionnaire extends JFrame{

	private Controler controler;
	private ControlPanel controlPanel;
	private DisplayPanel displayPanel;
	

	public JFrameGestionnaire(Controler c){
		this.controler=c;
		this.displayPanel=new DisplayPanel(0,this);
		getContentPane().setLayout(null);
		this.controlPanel= new ControlPanel(this);
		this.controlPanel.setBounds(20, 20, 100, 300);
		this.displayPanel.setBounds(120, 20, 600,500);
		//getContentPane().add(this.controlPanel);
		getContentPane().add(this.displayPanel);
		setVisible(true);
		setResizable(false);
		setBounds(0,0,800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Controler getController(){
		return this.controler;
	}
	
	public void setDisplayPanel(DisplayPanel F){
		this.remove(displayPanel);
		this.displayPanel=F;
		getContentPane().add(this.displayPanel);
		this.displayPanel.setBounds(120, 20, 600, 500);
		getContentPane().validate();
	}
	public void setControlPanel(){
		this.controlPanel.setBounds(20, 20, 100, 300);
		getContentPane().add(this.controlPanel);
		getContentPane().validate();
	}
}
