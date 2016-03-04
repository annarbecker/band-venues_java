import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-band", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputtedName = request.queryParams("name");
      Band newBand = new Band(inputtedName);
      newBand.save();
      model.put("newBand", newBand);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-venue", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputtedLocation = request.queryParams("location");
      Venue newVenue = new Venue(inputtedLocation);
      newVenue.save();
      model.put("newVenue", newVenue);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      model.put("band", band);
      model.put("venues", band.getVenues());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      model.put("band", band);
      model.put("template", "templates/band-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      String editName = request.queryParams("editName");
      band.update(editName);
      response.redirect("/bands");
      return null;
      });

    post("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      band.delete();
      response.redirect("/bands");
      return null;
    });
  }
}
