package sushi;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import sushi.runko.Database;
import sushi.runko.RaakaAine;
import sushi.runko.RaakaAineDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database tietokanta = new Database("jdbc:sqlite:raakaaineet.db");
        RaakaAineDao raakaAineDao = new RaakaAineDao(tietokanta);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("/", raakaAineDao.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/", (req, res) -> {
            raakaAineDao.saveOrUpdate(new RaakaAine(null, req.queryParams("nimi")));
            
            res.redirect("/");
            return "";
        });

    }
}
