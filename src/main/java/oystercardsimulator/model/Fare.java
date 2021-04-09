package oystercardsimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Fare {

	public static final float Anywhere_in_Zone_1 = 2.50f;
	public static final float Any_one_zone_outside_zone_1 = 2.00f;
	public static final float Any_two_zones_including_zone_1 = 3.00f;
	public static final float Any_two_zones_excluding_zone_1 = 2.25f;
	public static final float Any_three_zones = 3.20f;
	public static final float Any_bus_journey = 1.80f;
	public static final float maximum_possible_fare = 3.20f;

	public static float CalculateFare(Journey journey) {
		float fare = 0;
		if (journey.getTransport().equals(oystercardsimulator.enums.Transport.BUS)) {
			fare = Any_bus_journey;
		} else if ((journey.getStartStation() != null) && (journey.getEndStation() == null)) {
			fare = maximum_possible_fare;
		} else {
			fare = CalcZoneBasedFareLogic(journey);
		}
		return fare;
	}

	private static float CalcZoneBasedFareLogic(Journey journey) {
		float zoneBasedFare = 0f;
		String[] startStationZones = journey.getStartStation().getZone().split((","));
		String[] endStationZones = journey.getEndStation().getZone().split((","));
		List<Integer> istartStationZones = new ArrayList<Integer>();
		List<Integer> iendStationZones = new ArrayList<Integer>();
		for (String zones : startStationZones) {
			istartStationZones.add(Integer.parseInt(zones));
		}
		for (String zones : endStationZones) {
			iendStationZones.add(Integer.parseInt(zones));
		}
		if (istartStationZones.contains(1) && iendStationZones.contains(1)) {
			//journey started in zone 1 and ended in zone 1
			zoneBasedFare = Anywhere_in_Zone_1;
		} else if ((istartStationZones.size() == 2 && istartStationZones.contains(1)) || (iendStationZones.size() == 2 && iendStationZones.contains(1))) {
			zoneBasedFare = Any_two_zones_including_zone_1;
		}
		return zoneBasedFare;
	}

	public static float CalculateFare(Card oysterCard, Journey journey) {
		float fare = 0;
		if (journey.getTransport().equals(oystercardsimulator.enums.Transport.BUS)) {
			fare = Any_bus_journey;
		} else if ((journey.getStartStation() != null) && (journey.getEndStation() == null)) {
			fare = maximum_possible_fare;
		} else {
			fare = CalcZoneBasedFareLogic(journey);
		}
		return fare;
	}

}