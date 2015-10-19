package Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import appli.Component.Handle;

public class HandleTest 
{
	public Handle theHandle;

	@Before
	public void setUp() throws Exception 
	{
		theHandle = new Handle();
	}

	@After
	public void tearDown() throws Exception 
	{
		theHandle = null;
	}

	@Test
	public void testHandle() 
	{
		assertTrue(theHandle != null);
		assertTrue(theHandle.getHandleUp() == true);
	}
	
	@Test
	public void testHandleUp()
	{
		theHandle.setHandleUp(true);
		assertTrue(theHandle.getHandleUp() == true);
		
		theHandle.setHandleUp(false);
		assertTrue(theHandle.getHandleUp() == false);
	}
}
