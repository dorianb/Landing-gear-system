package appli.Observer;

import java.util.Timer;
import java.util.TimerTask;

import appli.Component.Door;
import appli.Component.Gear;
import appli.Component.Handle;
import appli.Component.Plane;
import appli.Component.SystemControl;

public class CentralObserver extends Observer
{
	public CentralObserver(Plane thePlane, Handle theHandle, SystemControl theSystemControl)
	{
		this.setThePlane(thePlane);
		this.setTheHandle(theHandle);
		this.setTheSystemControl(theSystemControl);
		
		this.getThePlane().centralDoor.attach(this);
		this.getThePlane().rightDoor.attach(this);
		this.getThePlane().leftDoor.attach(this);
		
		this.getThePlane().centralGear.attach(this);
		this.getThePlane().rightGear.attach(this);
		this.getThePlane().leftGear.attach(this);
		
		this.getTheHandle().attach(this);
	}
	
	@Override
   public void update()
   {						
	    this.setAction(this.getThePlane().centralGear, this.getThePlane().centralDoor);
	    this.setAction(this.getThePlane().rightGear, this.getThePlane().rightDoor);
	    this.setAction(this.getThePlane().leftGear, this.getThePlane().leftDoor);
		
		//Si toutes les roues sont déployées et toutes les portes fermées
	    //Alors on allume le voyant vert
		//Sinon on l'éteint
		if(this.getThePlane().centralGear.getGearDown() && this.getThePlane().rightGear.getGearDown() && this.getThePlane().leftGear.getGearDown() && this.getThePlane().centralDoor.getClosed() && this.getThePlane().rightDoor.getClosed() && this.getThePlane().leftDoor.getClosed())
		{
			this.getTheSystemControl().setGreenLight(true);
	    }
	    else
	    {
	    	this.getTheSystemControl().setGreenLight(false);
	    }
	     
		//Si une roue ou une porte est en manoeuvre
		//Alors on allume le voyant orange
		//Sinon on l'éteint
	    if(checkMoving(getThePlane().centralDoor, getThePlane().centralGear) || checkMoving(getThePlane().rightDoor, getThePlane().rightGear) || checkMoving(getThePlane().leftDoor, getThePlane().leftGear))
	    {
	    	this.getTheSystemControl().setOrangeLight(true);
	    }
	    else
	    {
	    	this.getTheSystemControl().setOrangeLight(false);
	    }
	     
	    //Si le système échoue, on allume le voyant rouge
	    //Sinon on l'éteint
	    if(checkFailure(getThePlane().centralDoor, getThePlane().centralGear) || checkFailure(getThePlane().rightDoor, getThePlane().rightGear) || checkFailure(getThePlane().leftDoor, getThePlane().leftGear))
	    {
	    	 this.getTheSystemControl().setRedLight(true);
	    }
	    else
	    {
	    	 this.getTheSystemControl().setRedLight(false);
	    }
   }
	
	public boolean checkMoving(Door theDoor, Gear theGear)
	{
		if(theDoor.getMoving() || theGear.getGearIsMoving())
		{
			return true;
		}
		return false;
	}
	
	public boolean checkFailure(Door theDoor, Gear theGear)
	{
		if( (!(theGear.getGearDown() || theGear.getGearUp()) && !theGear.getGearIsMoving()) || (!(theDoor.getClosed() || theDoor.getOpened()) && !theDoor.getMoving()))
		{
			return true;
		}
		return false;
	}
	
	public void setAction(Gear theGear, Door theDoor)
	{
		synchronized(this)
		{
			//Si la poignée est placée en haut, la porte n'est pas fermée, la porte n'est pas en manoeuvre, la roue est rangée
			//Alors on doit fermer la porte
			if(this.getTheHandle().getHandleUp() && !theDoor.getClosed() && !theDoor.getMoving() && theGear.getGearUp())
		    {
		    	this.setClosingDoorTimer(theDoor, theGear);
		    }
			
			//Si la poignée est placée en bas, la roue est déployée, la porte n'est pas fermée, la porte n'est pas en manoeuvre
			//Alors on doit fermer la porte
			if(!this.getTheHandle().getHandleUp() && theGear.getGearDown() && !theDoor.getClosed() && !theDoor.getMoving())
		    {
				this.setClosingDoorTimer(theDoor, theGear);
		    }
			
			//Si la poignée est placée en bas, la porte n'est pas ouverte, la porte n'est pas en manoeuvre, la roue n'est pas déployée
		    //Alors on doit ouvrir la porte
			if(!this.getTheHandle().getHandleUp() && !theDoor.getOpened() && !theDoor.getMoving() && !theGear.getGearDown())
			{
				this.setOpeningDoorTimer(theDoor, theGear);
		    }
			
			//Si la poignée est placée en haut, la porte n'est pas ouverte, la porte n'est pas en manoeuvre, la roue est déployée
		    //Alors on doit ouvrir la porte
			if(this.getTheHandle().getHandleUp() && !theDoor.getOpened() && !theDoor.getMoving() && theGear.getGearDown())
			{
				this.setOpeningDoorTimer(theDoor, theGear);
		    }
			
			//Si la poignée est placée en haut, la porte est ouverte, la roue n'est pas rangée, la roue n'est pas en manoeuvre,
		    //Alors on doit ranger la roue
			if(this.getTheHandle().getHandleUp() && theDoor.getOpened() && !theGear.getGearUp() && !theGear.getGearIsMoving())
		    {	    	
				this.setRetractingGearTimer(theGear);
		    }
			
			//Si la poignée est placée en bas, la porte est ouverte, la roue n'est pas déployée, la roue n'est pas en manoeuvre
		    //Alors on doit déployer la roue
			if(!this.getTheHandle().getHandleUp() && theDoor.getOpened() && !theGear.getGearDown() && !theGear.getGearIsMoving())
		    {	    	
				this.setExtendingGearTimer(theGear);
		    }
		}
	}
    
	public void setRetractingGearTimer(final Gear theGear)
	{
		TimerTask retracting = new TimerTask()
		{
			@Override
			public void run()
			{					
				if(theGear.theta<=1)
				{
					theGear.timer.cancel();
					theGear.setGearUp(true);
				}
				else if(!getTheHandle().getHandleUp())
				{
					theGear.timer.cancel();
					theGear.setGearIsMoving(false);
				}
				
				theGear.theta--;
				getThePlane().repaint();
			}
		};
		
		theGear.setGearIsMoving(true);
		
		theGear.timer = new Timer();
		theGear.timer.scheduleAtFixedRate(retracting, 0, 30);
	}
	
	public void setExtendingGearTimer(final Gear theGear)
	{
		TimerTask extending = new TimerTask()
		{
			@Override
			public void run()
			{					
				if(theGear.theta>=89)
				{
					theGear.timer.cancel();
					theGear.setGearDown(true);
				}
				else if(getTheHandle().getHandleUp())
				{
					theGear.timer.cancel();
					theGear.setGearIsMoving(false);
				}
				
				theGear.theta++;
				getThePlane().repaint();
			}
		};
		
		theGear.setGearIsMoving(true);
		
		theGear.timer = new Timer();
		theGear.timer.scheduleAtFixedRate(extending, 0, 30);
	}
	
	public void setClosingDoorTimer(final Door theDoor, final Gear theGear)
	{
		TimerTask closing = new TimerTask()
		{
			@Override
			public void run()
			{					
				if(theDoor.theta<=1)
				{
					theDoor.timer.cancel();
					theDoor.setClosed(true);
				}
				else if((!getTheHandle().getHandleUp() && theGear.getGearUp()) || (getTheHandle().getHandleUp() && theGear.getGearDown()))
				{
					theDoor.timer.cancel();
					theDoor.setMoving(false);
				}
				
				theDoor.theta--;
				getThePlane().repaint();
			}
		};
		
		theDoor.setMoving(true);
		
		theDoor.timer = new Timer();
		theDoor.timer.scheduleAtFixedRate(closing, 0, 30);
	}
	
	public void setOpeningDoorTimer(final Door theDoor, final Gear theGear)
	{
		TimerTask opening = new TimerTask()
		{
			@Override
			public void run()
			{					
				if(theDoor.theta>=89)
				{
					theDoor.timer.cancel();
					theDoor.setOpened(true);
				}
				else if((getTheHandle().getHandleUp() && theGear.getGearUp()) || (!getTheHandle().getHandleUp() && theGear.getGearDown()))
				{
					theDoor.timer.cancel();
					theDoor.setMoving(false);
				}
				
				theDoor.theta++;
				getThePlane().repaint();
			}
		};
		
		theDoor.setMoving(true);
		
		theDoor.timer = new Timer();
		theDoor.timer.scheduleAtFixedRate(opening, 0, 30);
	}
}
