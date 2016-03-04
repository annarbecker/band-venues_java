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

  @Test
  public void equals_returnsTrueIfBandNamesAreTheSame() {
   Venue firstVenue = new Venue("Keller Auditorium", "10/11/2012");
   Venue secondVenue = new Venue("Keller Auditorium", "10/11/2012");
   assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueIntoDatabase(){
    Venue concertHall = new Venue("Keller Auditorium", "10/11/2012");
    concertHall.save();
    assertEquals(Venue.all().get(0), concertHall);
  }
}
