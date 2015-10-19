package appli.Component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import appli.Observer.Observer;

public class Door 
{
	private List<Observer> observers = new ArrayList<Observer>();
	
	private boolean opened = false;
	private boolean moving = false;
	private boolean closed = true;
	
	private Plane plane;
	
	public int theta=0;
	public Timer timer;
	
	public Door(Plane plane)
	{
		super();
		
		this.plane = plane;
	}
	
	public boolean getOpened()
	{
		return this.opened;
	}
	
	public boolean getMoving()
	{
		return this.moving;
	}
	
	public boolean getClosed()
	{
		return this.closed;
	}
	
	public void setOpened(boolean opened)
	{
		this.moving = false;
	    this.closed = false;
		
	    this.opened = opened;
		
		notifyAllObservers();
		
		this.plane.repaint();
	}
	
	public void setMoving(boolean moving)
	{
		this.opened = false;
	    this.closed = false;
		
	    this.moving = moving;
	    
	    notifyAllObservers();
	    
	    this.plane.repaint();
	}
	
	public void setClosed(boolean closed)
	{
		this.moving = false;
		this.opened = false;
		
		this.closed = closed;
		
		notifyAllObservers();
		
		this.plane.repaint();
	}
	
	 public void attach(Observer observer)
	 {
	    getObservers().add(observer);		
	 }
	
	 public void notifyAllObservers()
	 {
	    for (Observer observer : getObservers()) 
	    {
	       observer.update();
	    }
	 }
	
	public void drawDoor(Graphics2D ga, Color theColor, int x, int y, int width, int height)
	{
		AffineTransform old = ga.getTransform();
		ga.rotate(Math.toRadians(this.theta), x, y);
		ga.setColor(theColor);
		ga.fillRect(x, y , width, height);
		ga.setColor(Color.black);
		ga.drawRect(x, y, width, height);
		ga.setTransform(old);
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
