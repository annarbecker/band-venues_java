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

  public int getId(){
    return id;
  }

  public String getLocation(){
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
}
