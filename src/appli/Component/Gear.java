package appli.Component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import appli.Observer.Observer;

public class Gear
{
	/**
	 * Model class variable
	 * @uml.property  name="observers"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="theGear:appli.Observer.Observer"
	 */
	private List<Observer> observers = new ArrayList<Observer>();
	/**
	 * @uml.property  name="gearUp"
	 */
	private boolean gearUp = true;
	/**
	 * @uml.property  name="gearIsMoving"
	 */
	private boolean gearIsMoving = false;
	/**
	 * @uml.property  name="gearDown"
	 */
	private boolean gearDown = false;
	
	/**
	 * @uml.property  name="theta"
	 */
	public int theta=0;
	
	/**
	 * @uml.property  name="timer"
	 */
	public Timer timer = null;
	
	private Plane plane;
		
	public Gear(Plane plane)
	{
		super();
		
		this.plane = plane;
	}

	public boolean getGearUp() 
	 {
	    return this.gearUp;
	 }
	 
	 public boolean getGearIsMoving()
	 {
		 return this.gearIsMoving;
	 }
	 
	 public boolean getGearDown()
	 {
		 return this.gearDown;
	 }
	
	 /**
	 * @param gearUp
	 * @uml.property  name="gearUp"
	 */
	public void setGearUp(boolean gearUp) 
	 {
	    this.gearDown = false;
	    this.gearIsMoving = false;
		
	    this.gearUp = gearUp;
	    
	    notifyAllObservers();
	    
	    this.plane.repaint();
	 }
	 
	 /**
	 * @param gearIsMoving
	 * @uml.property  name="gearIsMoving"
	 */
	public void setGearIsMoving(boolean gearIsMoving) 
	 {
		this.gearDown = false;
		this.gearUp = false;
		
		this.gearIsMoving = gearIsMoving;
		
	    notifyAllObservers();
	    
	    this.plane.repaint();
	 }
	 
	 /**
	 * @param gearDown
	 * @uml.property  name="gearDown"
	 */
	public void setGearDown(boolean gearDown)
	 {
		this.gearUp = false;
		this.gearIsMoving = false;
		 
		this.gearDown = gearDown;
		 
		notifyAllObservers();
		
		this.plane.repaint();
	 }
	
	 public void attach(Observer observer)
	 {
	    observers.add(observer);		
	 }
	
	 public void notifyAllObservers()
	 {
	    for (Observer observer : observers) 
	    {
	       observer.update();
	    }
	 }	
	 
	 public void drawGear(Graphics2D ga, Color theColor, int x, int y, int width, int height)
	 {
		ga.setColor(theColor);
		ga.fillRect(x, y , width, height);
		ga.setColor(Color.black);
		ga.drawRect(x, y, width, height);
		
		AffineTransform old = ga.getTransform();
		ga.rotate(Math.toRadians(-this.theta), x + width/2, y);
		ga.fillRect(x + width/2, y, width/10, height/2);
		this.drawCircle(ga, Color.black, x + width/2, y + height/2, width/6, height/4);
		ga.setTransform(old);
	}
			
	private void drawCircle(Graphics2D ga, Color theColor, int x, int y, int width, int height)
	{
		ga.setPaint(theColor);
	    ga.fillOval(x, y, width, height);
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
