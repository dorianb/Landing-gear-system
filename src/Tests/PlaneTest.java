package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import appli.Component.Plane;

public class PlaneTest 
{
	public Plane thePlane;
	
	@Before
	public void setUp() throws Exception 
	{
		this.thePlane = new Plane();
	}

	@After
	public void tearDown() throws Exception 
	{
		this.thePlane = null;
	}

	@Test
	public void testPlane() 
	{
		assertTrue(this.thePlane != null);
		
		assertTrue(this.thePlane.centralDoor != null);
		assertTrue(this.thePlane.rightDoor != null);
		assertTrue(this.thePlane.leftDoor != null);
		
		assertTrue(this.thePlane.centralGear != null);
		assertTrue(this.thePlane.rightGear != null);
		assertTrue(this.thePlane.leftGear != null);
	}

}
