package appli.Observer;

import appli.Component.Handle;
import appli.Component.Plane;
import appli.Component.SystemControl;

public abstract class Observer 
{
	/**
	 * @uml.property  name="thePlane"
	 * @uml.associationEnd  inverse="observers:appli.Component.Plane"
	 */
	private Plane thePlane;
	/**
	 * @uml.property  name="theHandle"
	 * @uml.associationEnd  inverse="observers:appli.Component.Handle"
	 */
	private Handle theHandle;
	/**
	 * @uml.property  name="theSystemControl"
	 * @uml.associationEnd  
	 */
	private SystemControl theSystemControl;
	
	public abstract void update();

	public Plane getThePlane() {
		return thePlane;
	}

	public void setThePlane(Plane thePlane) {
		this.thePlane = thePlane;
	}

	public Handle getTheHandle() {
		return theHandle;
	}

	public void setTheHandle(Handle theHandle) {
		this.theHandle = theHandle;
	}

	public SystemControl getTheSystemControl() {
		return theSystemControl;
	}

	public void setTheSystemControl(SystemControl theSystemControl) {
		this.theSystemControl = theSystemControl;
	}
}
