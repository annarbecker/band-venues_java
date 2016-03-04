import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band");
  }

  @Test
  public void bandIsCreated() {
    goTo("http://localhost:4567/");
    fill("#name").with("Sylvan Esso");
    submit("#addBand");
    assertThat(pageSource()).contains("Sylvan Esso");
  }

  @Test
  public void venueIsCreated() {
    goTo("http://localhost:4567/");
    fill("#location").with("Keller Auditorium");
    submit("#addVenue");
    assertThat(pageSource()).contains("Keller Auditorium");
  }

  @Test
  public void bandIsDisplayed() {
    Band myBand = new Band("Sylvan Esso");
    myBand.save();
    Band newBand = new Band("The Head and the Heart");
    newBand.save();
    goTo("http://localhost:4567/bands");
    assertThat(pageSource()).contains("Sylvan Esso");
    assertThat(pageSource()).contains("The Head and the Heart");
  }

  @Test
  public void venueIsDisplayed() {
    Venue myVenue = new Venue("Keller Auditorium");
    myVenue.save();
    Venue newVenue = new Venue("The Gorge");
    newVenue.save();
    goTo("http://localhost:4567/venues");
    assertThat(pageSource()).contains("Keller Auditorium");
    assertThat(pageSource()).contains("The Gorge");
  }

  @Test
  public void editBand() {
    Band myBand = new Band("Sylvan Esso");
    myBand.save();
    String bandPath = String.format("http://localhost:4567/bands/%d/edit", myBand.getId());
    goTo(bandPath);
    assertThat(pageSource()).contains("Sylvan Esso");
  }

  @Test
  public void deleteBand() {
    Band myBand = new Band("Sylvan Esso");
    myBand.save();
    String bandPath = String.format("http://localhost:4567/bands/%d/edit", myBand.getId());
    goTo(bandPath);
    submit("#deleteBand");
    goTo("http://localhost:4567/bands");
    assertThat(!(pageSource()).contains("Sylvan Esso"));
  }
}
