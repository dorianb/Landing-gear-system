package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import appli.Component.Gear;
import appli.Component.Plane;

public class GearTest 
{
	public Plane thePlane;
	public Gear theGear;
	
	@Before
	public void setUp() throws Exception 
	{
		thePlane = new Plane();
		theGear = new Gear(thePlane);
	}

	@After
	public void tearDown() throws Exception 
	{
		theGear = null;
		thePlane = null;
	}

	@Test
	public void testGear() 
	{
		assertTrue(theGear != null);
		assertTrue(theGear.getGearDown() == false);
		assertTrue(theGear.getGearUp() == true);
		assertTrue(theGear.getGearIsMoving() == false);
		assertTrue(theGear.theta == 0);
	}
	
	@Test
	public void testGearUp()
	{
		theGear.setGearUp(true);
		
		assertTrue(theGear.getGearUp() == true);
		assertTrue(theGear.getGearDown() == false);
		assertTrue(theGear.getGearIsMoving() == false);
	}

	@Test
	public void testGearDown()
	{
		theGear.setGearDown(true);
		
		assertTrue(theGear.getGearDown() == true);
		assertTrue(theGear.getGearUp() == false);
		assertTrue(theGear.getGearIsMoving() == false);
	}
	
	@Test
	public void testGearIsMoving()
	{
		theGear.setGearIsMoving(true);
		
		assertTrue(theGear.getGearIsMoving() == true);
		assertTrue(theGear.getGearDown() == false);
		assertTrue(theGear.getGearUp() == false);
	}
}
