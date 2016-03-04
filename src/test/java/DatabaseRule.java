import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/band_venues_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteSBandsQuery = "DELETE FROM bands *;";
      String deleteVenuesQuery = "DELETE FROM venues *;";
      String deleteBandsVenuesQuery = "DELETE from bands_venues *;";
      con.createQuery(deleteSBandsQuery).executeUpdate();
      con.createQuery(deleteVenuesQuery).executeUpdate();
      con.createQuery(deleteBandsVenuesQuery).executeUpdate();
    }
  }
}
