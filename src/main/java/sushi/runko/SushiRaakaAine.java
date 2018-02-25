
package sushi.runko;


public class SushiRaakaAine {
    
    private Integer raakaAineId;
    private Integer SushiId;
    private String maara;
    private String ohje;
    private Integer jarjestysNumero;
    
    
    public SushiRaakaAine(Integer sushiId, Integer raakaAineId, String maara, Integer jarjestysNumero, String ohje) {
        this.raakaAineId = raakaAineId;
        this.SushiId = sushiId;
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

    public String getMaara() {
        return maara;
    }

    public void setMaara(String maara) {
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
