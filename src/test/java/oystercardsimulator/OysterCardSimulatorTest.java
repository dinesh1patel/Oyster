package oystercardsimulator;

import org.junit.Assert;
import org.junit.Test;

import oystercardsimulator.enums.Station;
import oystercardsimulator.enums.Transport;
import oystercardsimulator.model.Card;
import oystercardsimulator.model.Fare;
import oystercardsimulator.model.Journey;

public class OysterCardSimulatorTest {
	Card oysterCard;

	@Test
	public void whenCardCreatedThenBalanceWillBe0() {
		oysterCard = new Card();
		Assert.assertEquals(0f, oysterCard.getBalance(), 1);
	}

	@Test
	public void whenCardCreatedWith30ThenBalanceWillBe30() {
		oysterCard = new Card(30f);
		Assert.assertEquals(30f, oysterCard.getBalance(), 1);
	}

	@Test(expected = RuntimeException.class)
	public final void whenRemoveMoreThanOnCardThanThrowError() {
		oysterCard = new Card(30f);
		oysterCard.deductFromBalance(31f);
	}

	@Test
	public final void whenCardTopupThenShowCorrectBalance() {
		oysterCard = new Card();
		oysterCard.TopUp(40f);
		Assert.assertEquals(40f, oysterCard.getBalance(), 1);
	}

	@Test
	public final void whenBusJourneyWith1FareThenBalanceMinusOne() {
		oysterCard = new Card(30f);
		Journey journey = new Journey();
		journey.setTransport(Transport.BUS);
		journey.setStartStation(null);
		journey.setEndStation(null);
		
		oysterCard.deductFromBalance(Fare.CalculateFare(journey));
		Assert.assertEquals(29f, oysterCard.getBalance(), 1);
	}

	@Test
	public final void whenStartJourneyHolbornNoEndJourneyChargeMaxFare() {
		oysterCard = new Card(30f);
		Journey journey = new Journey();
		journey.setTransport(Transport.TUBE);
		journey.setStartStation(Station.HOLBORN);
		journey.setEndStation(null);

		oysterCard.deductFromBalance(Fare.CalculateFare(journey));
		Assert.assertEquals(26.8f, oysterCard.getBalance(), 1);
	}
	
	@Test
	public final void whenStartJourneyHolbornEndJourneyCEarlsCourtCharge250() {
		oysterCard = new Card(30f);
		Journey journey = new Journey();
		journey.setTransport(Transport.TUBE);
		journey.setStartStation(Station.HOLBORN);
		journey.setEndStation(Station.EARLS_COURT);

		oysterCard.deductFromBalance(Fare.CalculateFare(journey));
		Assert.assertEquals(27.5f, oysterCard.getBalance(), 1);
	}
	
	@Test
	public final void whenStartJourneyEarlsCourtEndJourneyHammersmithCharge250() {
		oysterCard = new Card(30f);
		Journey journey = new Journey();
		journey.setTransport(Transport.TUBE);
		journey.setStartStation(Station.HOLBORN);
		journey.setEndStation(Station.EARLS_COURT);
		
		oysterCard.deductFromBalance(Fare.CalculateFare(journey));
		Assert.assertEquals(27.0f, oysterCard.getBalance(), 1);
	}
	
	@Test
	public final void whenChargeMaxFareReturn268() {
		oysterCard = new Card(30f);
		Journey journey = new Journey();
		journey.setTransport(Transport.TUBE);
		journey.setStartStation(Station.HOLBORN);
		journey.setEndStation(null);
		journey.Transact(oysterCard, journey);
		Assert.assertEquals(26.8f, oysterCard.getBalance(), 1);
	}
}
