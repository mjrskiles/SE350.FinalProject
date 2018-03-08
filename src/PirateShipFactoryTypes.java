import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PirateShipFactoryTypes {
    List<PirateShipFactory> factories;
    Random rand;

    public PirateShipFactoryTypes() {
        factories = new ArrayList<>();

        //Add one of each factory
        factories.add(new AveragePirateShipFactory())
    }
}
