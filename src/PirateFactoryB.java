
public class PirateFactoryB extends PirateShipFactory {
	
	PirateShip createPirate() {
		return new PirateTypeB();
	}

}