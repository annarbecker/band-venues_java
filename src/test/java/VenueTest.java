import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class VenueTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_returnsAllVenues_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }
}
