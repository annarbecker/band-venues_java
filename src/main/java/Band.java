import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Band {
  private int id;
  private String name;

  public Band(String name) {
    this.name = name;
  }


  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public static List<Band> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands";
      return con.createQuery(sql)
      .executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object newBand) {
    if (newBand instanceof Band) {
      Band otherBand = (Band) newBand;
      return this.getName().equals(otherBand.getName()) &&
        this.getId() == otherBand.getId();
    } else {
      return false;
    }
  }
}
