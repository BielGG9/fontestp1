package gabriel.fontes.br.quarkus.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fonte")
public class Fonte extends DefaultEntity {


    @Column(name = "name")
    private String nome;

    @Column(name = "potencia")
    private Integer potencia;

    private double preco;

    private String fabricante;

    public Fonte() {
    }

    public Fonte(String nome, Integer potencia, double preco, String fabricante) {
        this.nome = nome;
        this.potencia = potencia;
        this.preco = preco;
        this.fabricante = fabricante;
    }
    public Fonte(String nome, Integer potencia) {
        this.nome = nome;
        this.potencia = potencia;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getPotencia() {
        return potencia;
    }
    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    
}
