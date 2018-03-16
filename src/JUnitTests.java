// written by Brennen Stenson

import static org.junit.Assert.*;
import org.junit.Test;

public class JUnitTests {

    // T001 ensures that the there is only a single instance of the map.
    @Test
    public void T001() {
        assertTrue(Map.getInstance() == Main.map.getInstance());
    }

    // T002 tests that the correct number of islands have been populated
    // this ensures that islands are populated on ocean CellTypes, and not populated on island CellTypes.
    @Test
    public void T002() {
        int islandCount = 0;
        for(int i = 0; i < Map.DIMENSION; i++){
            for (int ii = 0; ii < Map.DIMENSION; ii++){
                if(Map.map[i][ii] == CellTypes.island()){
                    islandCount++;
                }
            }
        }
        assertTrue((islandCount == Map.numberOfIslands));
    }
}
