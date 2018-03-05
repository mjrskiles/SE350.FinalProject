
public class PirateFactoryA extends PirateShipFactory {
	
	PirateShip createPirate() {
		return new PirateTypeA();
	}

}
