package appli.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SystemControl extends JPanel
{
	/**
	 * Model class variable
	 * @uml.property  name="redLight"
	 */
	private boolean redLight = false;
	/**
	 * @uml.property  name="greenLight"
	 */
	private boolean greenLight = false;
	/**
	 * @uml.property  name="orangeLight"
	 */
	private boolean orangeLight = false;
	
	/** View Class variable **/
	
	public SystemControl()
	{
		super();
		
		this.initializeComponents();
	}
	
	/** Model Class Functions **/
	
	//Accessors
	public boolean getRedLight()
	{
		return redLight;	
	}
	public boolean getGreenLight()
	{
		return greenLight;
	}
	public boolean getOrangeLight()
	{
		return orangeLight;
	}
	
	//Mutators
	/**
	 * @param redLight
	 * @uml.property  name="redLight"
	 */
	public void setRedLight(boolean redLight)
	{
		this.redLight = redLight;
		
		this.repaint();
	}
	/**
	 * @param greenLight
	 * @uml.property  name="greenLight"
	 */
	public void setGreenLight(boolean greenLight)
	{
		this.greenLight = greenLight;
		
		this.repaint();
	}
	/**
	 * @param orangeLight
	 * @uml.property  name="orangeLight"
	 */
	public void setOrangeLight(boolean orangeLight)
	{
		this.orangeLight = orangeLight;
		
		this.repaint();
	}
	
	/** View Class Functions **/
	
	private void initializeComponents() 
	{
		this.setBackground(Color.white);
	}
	
	@Override
    public void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        
        int x = this.getWidth()/3;
        int y = this.getHeight()/6;
        int width = this.getWidth()/4;
        int height = this.getHeight()/4;

        if(this.getRedLight())
        {
        	this.drawCircle(g, Color.red, x, y, (width + height)/2, (width + height)/2);
        }
        else
        {
        	this.drawCircle(g, Color.black, x, y, (width + height)/2, (width + height)/2);
        }
        
        if(this.getOrangeLight())
        {
        	this.drawCircle(g, Color.orange, x, y+1*height, (width + height)/2, (width + height)/2);
        }
        else
        {
        	this.drawCircle(g, Color.black, x, y+1*height, (width + height)/2, (width + height)/2);
        }
        
        if(this.getGreenLight())
        {
        	this.drawCircle(g, Color.green, x, y+2*height,(width + height)/2, (width + height)/2);
        }
        else
        {
        	this.drawCircle(g, Color.black, x, y+2*height, (width + height)/2, (width + height)/2);
        }
    }
	
	private void drawCircle(Graphics g, Color theColor, int x, int y, int width, int height)
	{
		 Graphics2D ga = (Graphics2D)g;
		 
	     ga.setPaint(Color.black);
	     ga.fillOval(x, y, width, height);
	     
	     ga.setPaint(theColor);
	     ga.fillOval(x + (width + height)/20, y + (width + height)/20, (width + height)/2 - (width + height)/10, (width + height)/2 - (width + height)/10);
	}
}
