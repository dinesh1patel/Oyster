package oystercardsimulator;

import oystercardsimulator.enums.Station;
import oystercardsimulator.enums.Transport;
import oystercardsimulator.model.Card;
import oystercardsimulator.model.Journey;

public class program {

	public static void main(String[] args) {
		
		System.out.println("Loading a card with £10");
		Card oysterCard = new Card(10f);

		Journey journey = new Journey();
		journey.setTransport(Transport.TUBE);
		journey.setStartStation(Station.HOLBORN);
		journey.setEndStation(Station.EARLS_COURT);
		journey.Transact(oysterCard, journey);

		System.out.printf("Card Balance after first journey (Tube Holborn to Earl's Court)  is : %s \n",
				oysterCard.getBalance());
		
		journey = new Journey();
		journey.setTransport(Transport.BUS);
		journey.setStartStation(null);
		journey.setEndStation(null);
		journey.Transact(oysterCard, journey);

		System.out.printf("Card Balance after second journey (328 bus from Earl's Court to Chelsea) is : %s \n",
				oysterCard.getBalance());

		journey = new Journey();
		journey.setTransport(Transport.TUBE);
		journey.setStartStation(Station.EARLS_COURT);
		journey.setEndStation(Station.HAMMERSMITH);
		journey.Transact(oysterCard, journey);

		System.out.printf("Card Balance after third journey (Tube Earl's court to Hammersmith)  is : %s \n",
				oysterCard.getBalance());

	}

}
