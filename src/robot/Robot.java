package robot;

public class Robot {
	
	private final Sensor sensor;
	
	public Robot() {
		this.sensor = new Sensor();
	}
	public void avancer() {
		System.out.println("J'avance de 1");
	}
	
	public void rotate() {
		System.out.println("Je tourne ta race 10°");
	}
	public Sensor getSensor() {
		return sensor;
	}
	
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
