package net.sourceforge.jgrib.examples;

import org.openstreetmap.gui.jmapviewer.Coordinate;

@SuppressWarnings("serial")
public class MeteoCoordinate extends Coordinate {

	private double speed;

	private double direction;

	public MeteoCoordinate(double lat, double lon, double speed,
			double direction) {
		super(lat, lon);
		this.speed = speed;
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		String s = "X(Lat): " + getLat() + "  " + "Y(Lon): " + getLon();
		s += "\nSpeed: " + speed;
		s += "\nDirection: " + direction;

		return s;
	}

	public double toString(double speed) {
		double speed1 = speed;
		return speed1;
		
	}

}
