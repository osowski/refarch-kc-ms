package ibm.labs.kc.serv.ut;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ibm.labs.kc.app.rest.FleetService;
import ibm.labs.kc.dao.FleetDAO;
import ibm.labs.kc.dao.FleetDAOMockup;
import ibm.labs.kc.model.Fleet;
import ibm.labs.kc.model.Ship;

public class TestReadingFleet {

	public static FleetService serv ;
	public static FleetDAO dao;
	
	@BeforeClass
	public static void init() {
		serv = new FleetService();
	}
	
	@Test
	public void testGetAllFleets() {
		List<Fleet> f = serv.getFleets();
		Assert.assertNotNull(f);
		Assert.assertTrue(f.size() >= 1);
	}
	
	@Test
	public void testGetFleetByName() {
		Fleet f = serv.getFleetByName("KC-FleetNorth");
		Assert.assertNotNull(f);
		Assert.assertTrue("KC-FleetNorth".equals(f.getName()));
		f = serv.getFleetByName("wrongname");
		Assert.assertNull(f);
	}

	@Test
	public void testGetAllFleetsFromFile() {
		List<Fleet> fl = serv.getFleets();
		Assert.assertNotNull(fl);
		Assert.assertTrue(fl.size() >= 1);
		for (Fleet f : fl) {
			System.out.println(f.toString());
			for (Ship s : f.getShips()) {
				System.out.println("\t"+s.toString());
			}
		}
		
	}
}
