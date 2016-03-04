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
   Venue firstVenue = new Venue("Keller Auditorium");
   Venue secondVenue = new Venue("Keller Auditorium");
   assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueIntoDatabase(){
    Venue firstVenue = new Venue("Keller Auditorium");
    firstVenue.save();
    assertEquals(Venue.all().get(0), firstVenue);
  }

  @Test
  public void find_findsASpecificVenueInDatabase() {
    Venue firstVenue = new Venue("Keller Auditorium");
    firstVenue.save();
    Venue secondVenue = new Venue("The Gorge");
    secondVenue.save();
    Venue newVenue = Venue.find(firstVenue.getId());
    assertTrue(newVenue.equals(firstVenue));
    assertTrue(Venue.all().contains(secondVenue));
  }

  @Test
  public void addBand_addsABandToAVenue() {
    Band firstBand = new Band("Sylvan Esso");
    firstBand.save();
    Band secondBand = new Band("The Head & the Heart");
    secondBand.save();
    Venue firstVenue = new Venue("Keller Auditorium");
    firstVenue.save();
    Venue secondVenue = new Venue("The Gorge");
    secondVenue.save();
    firstVenue.addBand(firstBand.getId());
    firstVenue.addBand(secondBand.getId());
    secondVenue.addBand(firstBand.getId());
    assertTrue(secondVenue.getBands().contains(firstBand));
    assertEquals(firstVenue.getBands().size(), 2);
  }

  @Test
  public void delete_deletesObjectFromDatabase(){
    Venue firstVenue = new Venue("Keller Auditorium");
    firstVenue.save();
    Venue secondVenue = new Venue("The Gorge Ampitheater");
    secondVenue.save();
    firstVenue.delete();
    assertEquals(Venue.all().size(), 1);
  }
}
