package psn;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;

import robot.Deplacement;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class Main {
	public final static String FICHIER_GRAPHE = "facteurN_i.txt";
	public final static String FICHIER_ORIENTATION = "orientation.txt";
	private final static int ACCELERATION = 3600;
	private final static int VITESSE =360;

	int angle = 45; 
	// du carreffours 1  a 2
	
	private EV3LargeRegulatedMotor JambeGauche;
	private EV3LargeRegulatedMotor JambeDroite;
	
	public static void main(String[] args) throws NotBoundException, InterruptedException, IOException, AWTException {
		Graphe A = new Graphe(FICHIER_GRAPHE, FICHIER_ORIENTATION);
		
		ArrayList<ArrayList<Sommet>> coor = new ArrayList<>();
		
		Robot robal = new Robot();
		
		
//		Main leo = new Main();
//		ignitionRobot(leo);
//		
//		leo.arete1_2();
//		LCD.drawString("Debut programme", 0, 0);
//		LCD.clear();
//		
//		leo.JambeGauche.close();
//		leo.JambeDroite.close();
	}
	
	private static void ignitionRobot(Main leo) {
		Port portJambeGauche = LocalEV3.get().getPort("B");
		Port portJambeDroite = LocalEV3.get().getPort("C");
		leo.JambeGauche = new EV3LargeRegulatedMotor(portJambeGauche);
		leo.JambeDroite = new EV3LargeRegulatedMotor(portJambeDroite);
		leo.JambeGauche.setAcceleration(ACCELERATION);
		leo.JambeGauche.setSpeed(VITESSE);
		leo.JambeDroite.setAcceleration(ACCELERATION);
		leo.JambeDroite.setSpeed(VITESSE);
		LCD.clear();
	}
	
	public void arete1_2() {
		Deplacement.avancer(3);
		Deplacement.tourner("Gauche", angle);
		Deplacement.avancer(12);
		Deplacement.tourner("Gauche", angle);
		Deplacement.avancer(3);
	}

}
