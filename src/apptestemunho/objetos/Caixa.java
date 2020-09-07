package apptestemunho.objetos;

/**
 *
 * @author Felipe Passos
 */
public class Caixa {
    private Poco poco;
    private String tipoamostra;
    private String topo;
    private String base;
    private String caixa;
    private String instalacao;
    private String tipoContainer;
    private String endereco;
    private String proprietario;
    private float tamanhocaixa;
    private boolean exposta;
    private String solicitante;

    public Caixa(Poco poco, String tipoamostra, String topo, String base, String caixa, String instalacao, String endereco, String proprietario) {
        this.poco = poco;
        this.tipoamostra = tipoamostra;
        this.topo = topo;
        this.base = base;
        this.caixa = caixa;
        this.instalacao = instalacao;
        this.tipoContainer = "Caixa Plastico";
        this.endereco = endereco;
        this.proprietario = proprietario;
        this.tamanhocaixa = 15f;
        this.exposta = false;
        this.solicitante = "Solicitante não informado";
    }

    public Poco getPoco() {
        return poco;
    }

    public void setPoco(Poco poco) {
        this.poco = poco;
    }

    public String getTipoamostra() {
        return tipoamostra;
    }

    public void setTipoamostra(String tipoamostra) {
        this.tipoamostra = tipoamostra;
    }

    public String getTopo() {
        return topo;
    }

    public void setTopo(String topo) {
        this.topo = topo;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
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

    public String getTipoContainer() {
        return tipoContainer;
    }

    public void setTipoContainer(String tipoContainer) {
        this.tipoContainer = tipoContainer;
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

    public float getTamanhocaixa() {
        return tamanhocaixa;
    }

    public void setTamanhocaixa(float tamanhocaixa) {
        this.tamanhocaixa = tamanhocaixa;
    }

    public boolean isExposta() {
        return exposta;
    }

    public void setExposta(boolean exposta) {
        this.exposta = exposta;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
}