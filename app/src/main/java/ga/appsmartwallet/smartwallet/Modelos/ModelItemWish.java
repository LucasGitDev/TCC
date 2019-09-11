package ga.appsmartwallet.smartwallet.Modelos;

import java.io.Serializable;

public class ModelItemWish implements Serializable {

    String nome;
    int qtd;

    public ModelItemWish(String nome, int qtd) {
        this.nome = nome;
        this.qtd = qtd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
