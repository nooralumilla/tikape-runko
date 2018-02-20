package sushi.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database tietokanta = new Database("jdbc:sqlite:raakaaineet.db");

        RaakaAineDao raakaAineDao = new RaakaAineDao(tietokanta);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("/", raakaAineDao.findAll());
//            System.out.println(raakaAineDao.findOne(1).toString());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/ainekset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("/ainekset", raakaAineDao.findAll());

            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());

        Spark.post("/", (req, res) -> {
            raakaAineDao.saveOrUpdate(new RaakaAine(null, req.queryParams("nimi")));

            res.redirect("/");
            return "";
        });

    }
}
