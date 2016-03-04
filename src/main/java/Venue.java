import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;


public class Venue {
  private int id;
  private String location;
  private String show_date;

  public Venue(String location, String show_date){
    this.location = location;
    this.show_date = show_date;
  }

  public int getId() {
    return id;
  }

  public String getLocation() {
    return location;
  }

  public String getShowDate() {
    return show_date;
  }

  public static List<Venue> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM venues ORDER BY location";
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object newVenue) {
    if (newVenue instanceof Venue) {
      Venue otherVenue = (Venue) newVenue;
      return this.getLocation().equals(otherVenue.getLocation()) &&
        this.getId() == otherVenue.getId();
    } else {
      return false;
    }
  }

  public void save() {
    String sql = "INSERT INTO venues (location, show_date) VALUES (:location, :show_date)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
        .addParameter("location", location)
        .addParameter("show_date", show_date)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
  String sql = "SELECT * FROM venues WHERE id=:id";
  try(Connection con = DB.sql2o.open()){
    return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Venue.class);
    }
  }

  public void addBand(int band_id) {
    String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("band_id", band_id)
        .addParameter("venue_id", id)
        .executeUpdate();
    }
  }

  public List<Band> getBands() {
  String sql = "SELECT DISTINCT ON (name) bands.* FROM venues JOIN bands_venues ON (venues.id = bands_venues.venue_id) JOIN bands ON (bands_venues.band_id = bands.id) WHERE venues.id = :id ORDER BY name, id";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Band.class);
    }
  }

  public void delete() {
    String sql = "DELETE FROM venues WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
