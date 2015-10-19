package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import appli.Component.Door;
import appli.Component.Plane;

public class DoorTest 
{
	public Plane thePlane;
	public Door theDoor;

	@Before
	public void setUp() throws Exception 
	{
		thePlane = new Plane();
		theDoor = new Door(thePlane);
	}

	@After
	public void tearDown() throws Exception 
	{
		theDoor = null;
		thePlane = null;
	}

	@Test
	public void testDoor() 
	{
		assertTrue(theDoor != null);
		assertTrue(theDoor.getOpened() == false);
		assertTrue(theDoor.getClosed() == true);
		assertTrue(theDoor.getMoving() == false);
		assertTrue(theDoor.theta == 0);
	}
	
	@Test
	public void testDoorOpen()
	{
		theDoor.setOpened(true);
		
		assertTrue(theDoor.getOpened() == true);
		assertTrue(theDoor.getClosed() == false);
		assertTrue(theDoor.getMoving() == false);
	}

	@Test
	public void testDoorClosed()
	{
		theDoor.setClosed(true);
		
		assertTrue(theDoor.getOpened() == false);
		assertTrue(theDoor.getClosed() == true);
		assertTrue(theDoor.getMoving() == false);
	}
	
	@Test
	public void testDoorMoving()
	{
		theDoor.setMoving(true);
		
		assertTrue(theDoor.getOpened() == false);
		assertTrue(theDoor.getClosed() == false);
		assertTrue(theDoor.getMoving() == true);
	}

}
