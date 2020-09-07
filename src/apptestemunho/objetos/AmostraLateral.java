/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apptestemunho.objetos;

/**
 *
 * @author Felipe Passos
 */
public class AmostraLateral {
    private Poco poco;
    private String prof;
    private String peso;
    private String comprimento;

    public AmostraLateral(Poco poco, String prof) {
        this.poco = poco;
        this.prof = prof;
    }

    public Poco getPoco() {
        return poco;
    }

    public void setPoco(Poco poco) {
        this.poco = poco;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getComprimento() {
        return comprimento;
    }

    public void setComprimento(String comprimento) {
        this.comprimento = comprimento;
    }
    
}
