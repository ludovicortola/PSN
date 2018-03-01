package robot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Deplacement {
	
	private static EV3LargeRegulatedMotor JambeGauche;
	private static EV3LargeRegulatedMotor JambeDroite;
	
	public static void avancer(int n) {
		JambeGauche.rotate(3*n*360, true);
		JambeDroite.rotate(3*n*360);
	}
	
	public static void tourner(String dir, int a){
		double n = (a*2.7/90);
		if (dir.equals("Droite")) {
			JambeDroite.rotate((int)(n*360));
			JambeDroite.rotate(-150);
			int  r = (int)(-1.5*360);
			JambeGauche.rotate(r, true);
			JambeDroite.rotate(r);
		}
		if (dir.equals("Gauche")) {
			JambeGauche.rotate((int)(n*360));
			JambeGauche.rotate(-150);
			int  r = (int)(-1.5*360);
			JambeGauche.rotate(r, true);
			JambeDroite.rotate(r);
		}
	}
}
