package sushi.runko;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database tietokanta = new Database("jdbc:sqlite:raakaaineet.db");

        RaakaAineDao raakaAineDao = new RaakaAineDao(tietokanta);
        SushiDao sushiDao = new SushiDao(tietokanta);
        SushiRaakaAineDao sushiRaakaAineDao = new SushiRaakaAineDao(tietokanta);

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

        Spark.get("/sushit/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            HashMap map = new HashMap<>();
            List<SushiRaakaAine> sushinRaakaAineidenSushiRaakaAineet = sushiRaakaAineDao.findSushi(id);

            ArrayList<RaakaAine> SushinRaakaAineidenRaakaAineet = new ArrayList();
            sushinRaakaAineidenSushiRaakaAineet.forEach(sushiRaakaAine -> {
                Integer raakaAineenId = sushiRaakaAine.getRaakaAineId();
                try {
                    SushinRaakaAineidenRaakaAineet.add(raakaAineDao.findOne(raakaAineenId));
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex);
                }
            });
            map.put("sushinRaakaAineet", SushinRaakaAineidenRaakaAineet);
            map.put("SushiinLiittyvatSushiRaakaAineet", sushinRaakaAineidenSushiRaakaAineet);
            map.put("aineksia", raakaAineDao.findAll());
            map.put("sushinNimi", sushiDao.findOne(id));
            map.put("sushiId", id);
            
            return new ModelAndView(map, "sushinAinekset");
        }, new ThymeleafTemplateEngine());

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

        Spark.post("/sushit/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            sushiRaakaAineDao.saveOrUpdate(new SushiRaakaAine(id, Integer.parseInt(req.queryParams("raakaAine")), req.queryParams("maara"), Integer.parseInt(req.queryParams("jarjestys")), req.queryParams("ohje")));

            res.redirect("/sushit/"+id);
            return "";
        });

        Spark.post("/ainekset", (req, res) -> {
            String nimi = req.queryParams("raakaAineenNimi");
            String tarkistaNimi = nimi.replaceAll("\\s+","");
            
            if (!tarkistaNimi.isEmpty()) {
                raakaAineDao.saveOrUpdate(new RaakaAine(null, nimi));
            }

            res.redirect("/ainekset");
            return "";
        });

        Spark.post("/sushit/poista/:sushi_id", (req, res) -> {
            int id = Integer.parseInt(req.params(":sushi_id"));
            List<SushiRaakaAine> poistettavatSushiRaakaAineet = sushiRaakaAineDao.findSushi(id);
            poistettavatSushiRaakaAineet.forEach(sushiRaakaAine -> {
                try {
                    sushiRaakaAineDao.delete(sushiRaakaAine.getSushiId(), sushiRaakaAine.getRaakaAineId());
                } catch (SQLException ex) {
                    System.out.println("Error: "+ex);
                }
            });
            sushiDao.delete(id);
            res.redirect("/sushit");
            return "";
        });

        Spark.post("/ainekset/poista/:raakaAine_id", (req, res) -> {
            int id = Integer.parseInt(req.params(":raakaAine_id"));
            raakaAineDao.delete(id);
            res.redirect("/ainekset");
            return "";
        });

        Spark.post("/sushit/:id/:raakaAineId", (req, res) -> {
            int sushiId = Integer.parseInt(req.params(":id"));
            int raakaAineId = Integer.parseInt(req.params(":raakaAineId"));
            sushiRaakaAineDao.delete(sushiId, raakaAineId);
            res.redirect("/sushit/"+sushiId);
            return "";
        });
    }
}
