package ibm.labs.kc.dao;

import ibm.labs.kc.model.Fleet;
import ibm.labs.kc.model.Ship;

public class DAOFactory {
	 private static FleetDAO fleetDAO = null;
	 private static ShipDAO shipDAO = null;

	 /**
	  * This method may change to use a DAO implementation going to a real DB as default
	  * @return
	  */
	 public static FleetDAO buildOrGetFleetDAO() {
		if (fleetDAO == null) {
			 fleetDAO = new FleetDAOMockup();
		 }
		return fleetDAO;
	 }

	public static FleetDAO buildOrGetFleetDAO(String fleetFileName) {
		if (fleetDAO == null) {
			System.out.println("######### REO 									#########");
			System.out.println("######### buildOrGetFleetDAO(String fleetFileName) #########");
			System.out.println("######### fleetDAO is NULL  #########");
			System.out.println("######### Loading new fleetDAO from " + fleetFileName + "#########");
			System.out.println("######### REO 									#########");
			fleetDAO = new FleetDAOMockup(fleetFileName);
		 }
		return fleetDAO;
	}


	public static ShipDAO buildOrGetShipDAOInstance() {
		if (shipDAO == null) {
			shipDAO = new ShipDAOMockup();
			for (Fleet f : DAOFactory.buildOrGetFleetDAO().getFleets()) {
				for (Ship s : f.getShips()) {
					shipDAO.save(s);
				}
			}
		 }
		return shipDAO;
	}

	public static ShipDAO buildOrGetShipDAOInstance(String fileName) {
		if (shipDAO == null) {
			System.out.println("######### REO 									#########");
			System.out.println("######### buildOrGetShipDAOInstance(String fileName) #########");
			System.out.println("######### shipDAO is NULL  #########");
			System.out.println("######### Loading new shipDAO from " + fileName + "#########");
			System.out.println("######### REO 									#########");
			shipDAO = new ShipDAOMockup();
			for (Fleet f : DAOFactory.buildOrGetFleetDAO(fileName).getFleets()) {
				for (Ship s : f.getShips()) {
					shipDAO.save(s);
				}
			}
		 }

		 System.out.println("######### REO 									#########");
		 System.out.println("######### DAOFactory::buildOrGetShipDAOInstance  #########");
		 System.out.println(shipDAO);
		 System.out.println("######### REO 									#########");
		return shipDAO;
	}
}
