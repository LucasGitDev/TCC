package ga.appsmartwallet.smartwallet.Modelos;

public class ModeloItem {

    int id, foto;
    String nomeProd, textoSimples, textoCompleto;


    double valorProd;

    public ModeloItem(int id, String nomeProd, String textoSimples, String textoCompleto, double valorProd, int foto) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.textoSimples = textoSimples;
        this.textoCompleto = textoCompleto;
        this.valorProd = valorProd;
        this.foto = foto;
    }

    public ModeloItem() {
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getTextoSimples() {
        return textoSimples;
    }

    public void setTextoSimples(String textoSimples) {
        this.textoSimples = textoSimples;
    }

    public String getTextoCompleto() {
        return textoCompleto;
    }

    public void setTextoCompleto(String textoCompleto) {
        this.textoCompleto = textoCompleto;
    }

    public double getValorProd() {
        return valorProd;
    }

    public void setValorProd(double valorProd) {
        this.valorProd = valorProd;
    }
}
