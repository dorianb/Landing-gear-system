package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import appli.Component.Handle;
import appli.Component.Plane;
import appli.Component.SystemControl;
import appli.Observer.CentralObserver;

public class CentralObserverTest 
{
	public CentralObserver theCentralObserver;
	public Plane thePlane;
	public Handle theHandle;
	public SystemControl theSystemControl;
	
	@Before
	public void setUp() throws Exception 
	{
		this.thePlane = new Plane();
		this.theHandle = new Handle();
		this.theSystemControl = new SystemControl();
		
		this.theCentralObserver = new CentralObserver(thePlane, theHandle, theSystemControl);
	}

	@After
	public void tearDown() throws Exception 
	{
		thePlane = null;
		theHandle = null;
		theSystemControl = null;
		
		theCentralObserver = null;
	}

	@Test
	public void testObserver()
	{	
		assertTrue(theCentralObserver != null);
		
		assertTrue(theCentralObserver.getThePlane() != null);
		assertTrue(theCentralObserver.getTheHandle() != null);
		assertTrue(theCentralObserver.getTheSystemControl() != null);
	}
	
	@Test
	public void testDoorsAttached()
	{
		assertTrue(theCentralObserver.getThePlane().centralDoor.getObservers().get(0) == theCentralObserver);
		assertTrue(theCentralObserver.getThePlane().rightDoor.getObservers().get(0) == theCentralObserver);
		assertTrue(theCentralObserver.getThePlane().leftDoor.getObservers().get(0) == theCentralObserver);
	}
	
	@Test
	public void testGearsAttached()
	{
		assertTrue(theCentralObserver.getThePlane().centralGear.getObservers().get(0) == theCentralObserver);
		assertTrue(theCentralObserver.getThePlane().rightGear.getObservers().get(0) == theCentralObserver);
		assertTrue(theCentralObserver.getThePlane().leftGear.getObservers().get(0) == theCentralObserver);
	}
	
	@Test
	public void testHandleAttached()
	{
		assertTrue(theCentralObserver.getTheHandle().getObservers().get(0) == theCentralObserver);
	}
	
	@Test
	public void testExtendingRetractingSequence() throws InterruptedException
	{		
		theHandle.setHandleUp(false);
		
		Thread.sleep(5000);
		
		assertTrue(theCentralObserver.getThePlane().centralDoor.getOpened() == true);
		assertTrue(theCentralObserver.getThePlane().rightDoor.getOpened() == true);
		assertTrue(theCentralObserver.getThePlane().leftDoor.getOpened() == true);
		
		assertTrue(theCentralObserver.getTheSystemControl().getGreenLight() == false);
		assertTrue(theCentralObserver.getTheSystemControl().getOrangeLight() == true);
		assertTrue(theCentralObserver.getTheSystemControl().getRedLight() == false);
		
		Thread.sleep(10000);
		
		assertTrue(theCentralObserver.getThePlane().centralDoor.getClosed() == true);
		assertTrue(theCentralObserver.getThePlane().rightDoor.getClosed() == true);
		assertTrue(theCentralObserver.getThePlane().leftDoor.getClosed() == true);
		
		assertTrue(theCentralObserver.getThePlane().centralGear.getGearDown() == true);
		assertTrue(theCentralObserver.getThePlane().rightGear.getGearDown() == true);	
		assertTrue(theCentralObserver.getThePlane().leftGear.getGearDown() == true);
		
		assertTrue(theCentralObserver.getTheSystemControl().getGreenLight() == true);
		assertTrue(theCentralObserver.getTheSystemControl().getOrangeLight() == false);
		assertTrue(theCentralObserver.getTheSystemControl().getRedLight() == false);
		
		theHandle.setHandleUp(true);
		
		Thread.sleep(5000);
		
		assertTrue(theCentralObserver.getThePlane().centralDoor.getOpened() == true);
		assertTrue(theCentralObserver.getThePlane().rightDoor.getOpened() == true);
		assertTrue(theCentralObserver.getThePlane().leftDoor.getOpened() == true);
		
		assertTrue(theCentralObserver.getTheSystemControl().getGreenLight() == false);
		assertTrue(theCentralObserver.getTheSystemControl().getOrangeLight() == true);
		assertTrue(theCentralObserver.getTheSystemControl().getRedLight() == false);
		
		Thread.sleep(10000);
		
		assertTrue(theCentralObserver.getThePlane().centralDoor.getClosed() == true);
		assertTrue(theCentralObserver.getThePlane().rightDoor.getClosed() == true);
		assertTrue(theCentralObserver.getThePlane().leftDoor.getClosed() == true);
		
		assertTrue(theCentralObserver.getThePlane().centralGear.getGearUp() == true);
		assertTrue(theCentralObserver.getThePlane().rightGear.getGearUp() == true);	
		assertTrue(theCentralObserver.getThePlane().leftGear.getGearUp() == true);
		
		assertTrue(theCentralObserver.getTheSystemControl().getGreenLight() == false);
		assertTrue(theCentralObserver.getTheSystemControl().getOrangeLight() == false);
		assertTrue(theCentralObserver.getTheSystemControl().getRedLight() == false);
	}
}
