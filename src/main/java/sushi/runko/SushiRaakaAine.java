
package sushi.runko;


public class SushiRaakaAine {
    
    private Integer raakaAineId;
    private Integer SushiId;
    private Sushi sushi;
    private RaakaAine raakaAine;
    private Integer maara;
    private String ohje;
    private Integer jarjestysNumero;
    
    
    public SushiRaakaAine(Sushi sushi, RaakaAine raakaAine, Integer maara, Integer jarjestysNumero, String ohje) {
        this.raakaAineId = raakaAine.getId();
        this.SushiId = sushi.getId();
        this.maara = maara;
        this.jarjestysNumero = jarjestysNumero;
        this.ohje = ohje;
    }

    public Integer getRaakaAineId() {
        return raakaAineId;
    }

    public void setRaakaAineId(Integer raakaAineId) {
        this.raakaAineId = raakaAineId;
    }

    public Integer getSushiId() {
        return SushiId;
    }

    public void setSushiId(Integer SushiId) {
        this.SushiId = SushiId;
    }

    public Integer getMaara() {
        return maara;
    }

    public void setMaara(Integer maara) {
        this.maara = maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    public Integer getJarjestysNumero() {
        return jarjestysNumero;
    }

    public void setJarjestysNumero(Integer jarjestysNumero) {
        this.jarjestysNumero = jarjestysNumero;
    }
    
    
    
   
    
}
