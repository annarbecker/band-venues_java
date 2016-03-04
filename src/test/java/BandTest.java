import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BandTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  public void all_returnsAllBands_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }
}
