/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apptestemunho.objetos;

/**
 *
 * @author b9c2
 */
public class Plug {
    private Poco poco;
    private String prof;
    private String id_plug;
    private String caixa;
    private String instalacao;
    
    private String endereco;
    private String proprietario;

    public Plug(Poco poco, String prof, String id_plug, String caixa, String instalacao, String endereco, String proprietario) {
        this.poco = poco;
        this.prof = prof;
        this.id_plug = id_plug;
        this.caixa = caixa;
        this.instalacao = instalacao;
        
        this.endereco = endereco;
        this.proprietario = proprietario;
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
    
    public String getId_Plug() {
        return id_plug;
    }

    public void setId_Plug(String id_plug) {
        this.id_plug = id_plug;
    }
    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public String getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(String instalacao) {
        this.instalacao = instalacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }
    
}