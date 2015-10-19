package appli.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Plane extends JPanel
{	
	public Door centralDoor;
	public Door rightDoor;
	public Door leftDoor;
	
	public Gear centralGear;
	public Gear rightGear;
	public Gear leftGear;
	
	public Plane()
	{
		this.centralDoor = new Door(this);
		this.rightDoor = new Door(this);
		this.leftDoor = new Door(this);
		
		this.centralGear = new Gear(this);
		this.rightGear = new Gear(this);
		this.leftGear = new Gear(this);
		
		this.initializeComponents();
	}
	
	private void initializeComponents() 
	{
		this.setBackground(Color.white);
	}
	
	 /** View Class Functions **/
	
	@Override
    public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		Graphics2D ga = (Graphics2D) g;
		
		ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ga.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        int x = this.getWidth()/2;
        int y = this.getHeight()/4;
        int width = this.getWidth();
        int height = this.getHeight();

        this.drawPlane(ga, Color.gray, x, y, width, height);
        
        this.centralGear.drawGear(ga, Color.darkGray, x - (int) (width/15), y + 10, 2 * (int) (width/15), height/9);
        this.rightGear.drawGear(ga, Color.darkGray, x + (int) (width/12), y + (int) (height/1.6) - height/9, 2 * (int) (width/15), height/9);
        this.leftGear.drawGear(ga, Color.darkGray, x - (int) (width/12) - 2 * (int) (width/15), y + (int) (height/1.6) - height/9, 2 * (int) (width/15), height/9);
        
        this.centralDoor.drawDoor(ga, Color.white, x - (int) (width/15), y + 10, 2 * (int) (width/15), height/9);
        this.rightDoor.drawDoor(ga, Color.white, x + (int) (width/12), y + (int) (height/1.6) - height/9, 2 * (int) (width/15), height/9);
        this.leftDoor.drawDoor(ga, Color.white, x - (int) (width/12) - 2 * (int) (width/15), y + (int) (height/1.6) - height/9, 2 * (int) (width/15), height/9);
    }
	
	private void drawPlane(Graphics2D ga, Color theColor, int x, int y, int width, int height)
	{	
		ga.setPaint(theColor);
		
		Polygon wings = new Polygon();
		wings.addPoint(x - (int) (width/12), y);
		wings.addPoint(x + (int) (width/12), y);
		wings.addPoint(x + (int) (width/2.5), y + (int) (height/1.5));
		wings.addPoint( x - (int) (width/2.5), y + (int) (height/1.5));
		ga.fillPolygon(wings);
		
		Polygon nose = new Polygon();
		nose.addPoint(x - (int) (width/12), y);
		nose.addPoint(x + (int) (width/12), y);
		nose.addPoint(x, y - (int) (height/5));
		ga.fillPolygon(nose);
		
		ga.setPaint(Color.black);
	    ga.drawPolygon(wings);
	    ga.drawPolygon(nose);
	    
	    ga.setPaint(theColor);
	    ga.fillRect(x - (int) (width/12), y, 2 * (int) (width/12), (int) (height/1.4));
	    ga.setPaint(Color.black);
	    ga.drawRect(x - (int) (width/12), y, 2 * (int) (width/12), (int) (height/1.4));
	}
}
