package oystercardsimulator.model;

import oystercardsimulator.enums.Transport;
import oystercardsimulator.enums.Station;

public class Journey {

	private Station startStation;
	private Station endStation;
	private Transport transport;
	
	public Station getStartStation() {
		return startStation;
	}
	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}
	public Station getEndStation() {
		return endStation;
	}
	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public void Transact(Card oysterCard, Journey journey) {
		if (journey.getStartStation() != null) {
			oysterCard.deductFromBalance(Fare.maximum_possible_fare);
		}
		if (journey.getEndStation() != null) {
			oysterCard.add(Fare.maximum_possible_fare);
			oysterCard.deductFromBalance(Fare.CalculateFare(journey));
		} else {
			oysterCard.deductFromBalance(Fare.CalculateFare(journey));
		}
	}
	
}
