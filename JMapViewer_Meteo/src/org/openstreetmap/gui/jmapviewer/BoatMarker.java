package org.openstreetmap.gui.jmapviewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;

import net.sourceforge.jgrib.examples.MeteoCoordinate;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class BoatMarker extends MapMarkerDot {

	/**
	 * Diamètre du cercle dans lequel seront déssinés les bateaux.
	 */
	public static double D = 12;
	public static double a = 3;
	
	
	private Double boatMovingDraw;
	private Double boatStoppedDraw;
	private Double illegalBoatDraw;
	private java.awt.geom.Ellipse2D.Double circle;
	
	private double speed;
	private double direction;

	

	

	public BoatMarker(double lat, double lon) {
        this(new Coordinate(lat,lon));
    }
	
	public BoatMarker(Coordinate coord) {
		super(coord);

		boatMovingDraw = new Path2D.Double();
		boatMovingDraw.moveTo(-D / 2., 0.);
		boatMovingDraw.lineTo(D / 2, D / 2.);
		boatMovingDraw.lineTo(D / 3, 0);
		boatMovingDraw.lineTo(D / 2, -D / 2.);
		boatMovingDraw.closePath();

		boatStoppedDraw = new Path2D.Double();
		boatStoppedDraw.moveTo(-D / 2, 0);
		boatStoppedDraw.lineTo(0, D / 2);
		boatStoppedDraw.lineTo(D / 2, 0);
		boatStoppedDraw.lineTo(0, -D / 2);
		boatStoppedDraw.closePath();

		illegalBoatDraw = new Path2D.Double();
		illegalBoatDraw.moveTo(-D, 0.);
		illegalBoatDraw.lineTo(D, D);
		illegalBoatDraw.lineTo(2 * D / 3, 0.);
		illegalBoatDraw.lineTo(D, -D);
		illegalBoatDraw.closePath();
	}
	
	public BoatMarker(MeteoCoordinate coord) {
		super(coord);
		speed = coord.getSpeed();
		direction = coord.getDirection();
		boatMovingDraw = new Path2D.Double();
		boatMovingDraw.moveTo(-D / 2., 0.);
		boatMovingDraw.lineTo(D / 2, D / 2.);
		boatMovingDraw.lineTo(D / 3, 0);
		boatMovingDraw.lineTo(D / 2, -D / 2.);
		boatMovingDraw.closePath();
		
		circle = new Ellipse2D.Double(-D/2, -D/2, 10, 10);
		
		
	}
	
	public BoatMarker(MeteoCoordinate coord, double speed) {
		super(coord);
		coord.toString();
		speed = coord.getSpeed();
		direction = coord.getDirection();
		boatMovingDraw = new Path2D.Double();
		boatMovingDraw.moveTo(-D / 2., 0.);
		boatMovingDraw.lineTo(D / 2, D / 2.);
		boatMovingDraw.lineTo(D / 3, 0);
		boatMovingDraw.lineTo(D / 2, -D / 2.);
		boatMovingDraw.closePath();
		
		
		
		
	}
	
//	public BoatMarker(Layer layer, String name, MeteoCoordinate coord) {
//        this(new Coordinate(lat, lon));
//    }
	
	private static void toString(double speed2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "BoatMarker [speed]"+ speed;
	}

	@Override
	public void paint(Graphics g, Point position, int radio) {

		if (g instanceof Graphics2D && getBackColor() != null) {
			Graphics2D g2 = (Graphics2D) g;
			Composite oldComposite = g2.getComposite();
			AffineTransform oldAt = g2.getTransform();
			RenderingHints oldRendering = g2.getRenderingHints();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

			g2.translate(position.x, position.y);
			g2.rotate((-(Math.PI*(direction)/180)));
//			Angle en radian = pi * (angle en degr�) / 180 
			
			double speed_knot = speed*1.945;
			
			if (speed_knot > 23) {
			g2.setColor(Color.red); }
			else if (speed_knot > 23*0.5) {
			g2.setColor(Color.yellow); 
			}
			else if (speed_knot > 23*0.1) {
			g2.setColor(Color.green); 
			}
			else
			g2.setColor(Color.white);
			
			BasicStroke line = new BasicStroke(3.0f);
			((Graphics2D) g).setStroke(line);
			g2.drawLine(0, 0, (int) (speed*a), 0);
			
			if (speed > 0.5) {
			g2.setPaint(UIConfig.blackSusie);
			g2.setStroke(UIConfig.getStroke(5));
			g2.draw(boatMovingDraw); }

			if (speed_knot > 23) {
				g2.setPaint(UIConfig.blackSusie); }
				else if (speed_knot > 23*0.5) {
					g2.setPaint(UIConfig.darkOrange); 
				}
				else if (speed_knot > 23*0.1) {
					g2.setPaint(UIConfig.greenOpaque); 
				}
					else
						g2.setPaint(UIConfig.lightGreyAlpha);
			
//			g2.setPaint(UIConfig.blueSusie);
			
			
			g2.fill(boatMovingDraw);
			
			g2.setPaint(Color.WHITE);
			g2.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
			
			
			g2.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
			if (speed > 0.5) { g2.draw(boatMovingDraw);}
		
			else {
			g2.setPaint(Color.GRAY);
			g2.draw(circle);		
			g2.fill(circle); }

			g2.setComposite(oldComposite);
			g2.setTransform(oldAt);
			g2.setRenderingHints(oldRendering);
        }
       
        if(getLayer()==null||getLayer().isVisibleTexts()) paintText(g, position);
    }

}
