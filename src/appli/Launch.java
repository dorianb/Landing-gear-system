package appli;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import appli.Component.Handle;
import appli.Component.Plane;
import appli.Component.SystemControl;
import appli.Observer.CentralObserver;

@SuppressWarnings("serial")
public class Launch extends JFrame
{	
	public Launch()
	{
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("The Landing Gear System");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(300,300));
	}
	
	public static void main(String[] args)
	{
		Launch myLaunch = new Launch();
		
		Handle theHandle = new Handle();
		SystemControl theSystemControl = new SystemControl();
		Plane thePlane = new Plane();
		
		new CentralObserver(thePlane, theHandle, theSystemControl);
		
		JPanel main = new JPanel();
		JPanel pane1 = new JPanel();
		
		main.setLayout(new GridLayout(1,2));
		pane1.setLayout(new GridLayout(1,2));
		
		pane1.add(theHandle);
		pane1.add(theSystemControl);
		
		main.add(thePlane);
		main.add(pane1);
		
		myLaunch.setContentPane(main);
		
		myLaunch.setVisible(true);
	}
}
