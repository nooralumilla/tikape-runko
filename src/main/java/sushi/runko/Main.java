package sushi.runko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database tietokanta = new Database("jdbc:sqlite:raakaaineet.db");

        RaakaAineDao raakaAineDao = new RaakaAineDao(tietokanta);
        SushiDao sushiDao = new SushiDao(tietokanta);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aineksia", raakaAineDao.findAll());
            map.put("sushit", sushiDao.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/ainekset", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aineksia", raakaAineDao.findAll());

            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());

        Spark.get("/sushit", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("sushit", sushiDao.findAll());

            return new ModelAndView(map, "sushit");
        }, new ThymeleafTemplateEngine());

//        Spark.get("/sushit/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("sushi", raakaAineDao.findOne(:id));
//
//            return new ModelAndView(map, "ainekset");
//        }, new ThymeleafTemplateEngine());
        Spark.post("/", (req, res) -> {
            raakaAineDao.saveOrUpdate(new RaakaAine(null, req.queryParams("nimi")));

            res.redirect("/");
            return "";
        });

        Spark.post("/sushit", (req, res) -> {
            String nimi = req.queryParams("sushinNimi");
            sushiDao.saveOrUpdate(new Sushi(null, nimi));

            res.redirect("/sushit");
            return "";
        });

        Spark.post("/ainekset", (req, res) -> {
            String nimi = req.queryParams("raakaAineenNimi");
            raakaAineDao.saveOrUpdate(new RaakaAine(null, nimi));

            res.redirect("/ainekset");
            return "";
        });

        Spark.post("/delete/:sushi_id", (req, res) -> {
            int id = Integer.parseInt(req.params(":sushi_id"));
            sushiDao.delete(id);
            res.redirect("/");
            return "";
        });

        Spark.post("/poista/:raakaAine_id", (req, res) -> {
            int id = Integer.parseInt(req.params(":raakaAine_id"));
            raakaAineDao.delete(id);
            res.redirect("/ainekset");
            return "";
        });

        Spark.post("/sushiRaakaAineet/:sushiRaakaAine_id", (req, res) -> {
            int id = Integer.parseInt(req.params(":sushiRaakaAine_id"));
            //
            //tähän toiminnallisuutta että lisätään raaka-aine valitulle sushille
            res.redirect("/sushit");
            return "";
        });

    }
}
