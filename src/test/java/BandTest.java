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

  @Test
  public void save_savesBandIntoDatabase() {
    Band myBand = new Band("Sylvan Esso");
    myBand.save();
    assertEquals(Band.all().get(0), myBand);
  }

  @Test
  public void save_addIdToLocalObject() {
    Band myBand = new Band("Sylvan Esso");
    myBand.save();
    assertEquals(Band.all().get(0).getId(), myBand.getId());
  }

  @Test
  public void find_returnsBandWithSameId() {
    Band firstBand = new Band("Sylvan Esso");
    firstBand.save();
    Band secondBand = new Band("The Head & The Heart");
    secondBand.save();
    Band newBand = Band.find(firstBand.getId());
    assertTrue(newBand.equals(firstBand));
  }
}
