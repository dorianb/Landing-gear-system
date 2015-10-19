package appli.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import appli.Observer.Observer;

@SuppressWarnings("serial")
public class Handle extends JPanel
{	
	/**
	 * Model class variable
	 * @uml.property  name="observers"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="theHandle:appli.Observer.Observer"
	 */
	private List<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * @uml.property  name="handleUp"
	 */
	private boolean handleUp = true;
	
	public JButton top = new JButton();
	public JButton down = new JButton();
	
	public Handle()
	{
		super();
		
		this.initializeComponent();
	}

	/** Model Class Functions **/
	
	public boolean getHandleUp() 
	 {
	    return this.handleUp;
	 }
	
	 /**
	 * @param handleUp
	 * @uml.property  name="handleUp"
	 */
	public void setHandleUp(boolean handleUp) 
	 {
	    this.handleUp = handleUp;
	    notifyAllObservers();
	   
	    this.repaint();
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
	 
	 /** View Class functions **/
	 
	 private void initializeComponent() 
	 {
		this.setBackground(Color.white);
		this.setLayout(null);
		
		this.top.setBackground(Color.black);
		this.down.setBackground(Color.black);
		
		this.add(this.top);
		this.add(this.down);
		
		Action action = new Action();
		this.top.addActionListener(action);
		this.down.addActionListener(action);
		
		this.resetComponent();
	 }
	 
	 private void resetComponent()
	 {
		int x = this.getWidth()/2;
        int y = this.getHeight()/6;
        int width = this.getWidth();
        int height = this.getHeight();
		 
		this.drawCommand(x, y, width, height);
		    
	    if(this.getHandleUp())
		{
			this.top.setVisible(true);
			this.down.setVisible(false);
		}
	    else
	    {
	    	this.top.setVisible(false);
	    	this.down.setVisible(true);
	    }
	 }
	 
	 private void drawCommand(int x, int y, int width, int height)
	{
		this.top.setBounds(x - width/10, y, width/5 + 2*width/10, height/10);
		this.down.setBounds(x - width/10, y + height/2, width/5 + 2*width/10, height/10);		
	}
	 
	 @Override
    public void paintComponent(Graphics g) 
	{
	    super.paintComponent(g);
	    
	    int x = this.getWidth()/2;
        int y = this.getHeight()/6;
        int width = this.getWidth();
        int height = this.getHeight();
	    
	    Graphics2D ga = (Graphics2D)g;

	    ga.setPaint(Color.gray);
	    ga.fillRect(x, y, width/5, height/2);
	    
	    ga.setPaint(Color.black);
	    ga.drawRect(x, y, width/5, height/2);
	    
	    this.resetComponent();
    }
	
	public class Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if( e.getSource().equals(top) )
			{
				setHandleUp(false);
			}
			else if (e.getSource().equals(down))
			{
				setHandleUp(true);
			}
		}	
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
