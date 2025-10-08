package gabriel.fontes.br.quarkus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gabriel.fontes.br.quarkus.Model.Abstratc.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Endereco extends DefaultEntity{

    private String rua;
    private String bairro;
    private String complemento;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    @ManyToOne
    @JsonIgnore
    private Cliente cliente;

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public void setCliente(Cliente novoCliente) {
    }
    
}
