import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BandTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  public void all_returnsAllBands_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
   public void equals_returnsTrueIfBandNamesAreTheSame() {
     Band firstBand = new Band("Sylvan Esso");
     Band secondBand = new Band("Sylvan Esso");
     assertTrue(firstBand.equals(secondBand));
   }
}
