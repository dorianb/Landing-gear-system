package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import appli.Component.SystemControl;

public class SystemControlTest 
{
	public SystemControl theSystemControl;

	@Before
	public void setUp() throws Exception 
	{
		this.theSystemControl = new SystemControl();
	}

	@After
	public void tearDown() throws Exception 
	{
		theSystemControl = null;
	}

	@Test
	public void testSystemControl() 
	{
		assertTrue(theSystemControl != null);
		
		assertTrue(theSystemControl.getRedLight() == false);
		assertTrue(theSystemControl.getGreenLight() == false);
		assertTrue(theSystemControl.getOrangeLight() == false);
	}
	
	@Test
	public void testRedLight()
	{
		theSystemControl.setRedLight(true);
		assertTrue(theSystemControl.getRedLight() == true);
		
		theSystemControl.setRedLight(false);
		assertTrue(theSystemControl.getRedLight() == false);
	}
	
	@Test
	public void testGreenLight()
	{
		theSystemControl.setGreenLight(true);
		assertTrue(theSystemControl.getGreenLight() == true);
		
		theSystemControl.setGreenLight(false);
		assertTrue(theSystemControl.getGreenLight() == false);
	}
	
	@Test
	public void testOrangeLight()
	{
		theSystemControl.setOrangeLight(true);
		assertTrue(theSystemControl.getOrangeLight() == true);
		
		theSystemControl.setOrangeLight(false);
		assertTrue(theSystemControl.getOrangeLight() == false);
	}
}
