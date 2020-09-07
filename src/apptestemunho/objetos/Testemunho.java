package apptestemunho.objetos;

/**
 *
 * @author Felipe Passos
 */
public class Testemunho {
    private Poco poco;
    private String detalhes;
    private CaixaTestemunho[] listacaixastestemunho;
    private byte totalcaixas;

    public Testemunho(Poco poco, String detalhes) {
        this.poco = poco;
        this.detalhes = detalhes;
        
    }

    public Poco getPoco() {
        return poco;
    }

    public void setPoco(Poco poco) {
        this.poco = poco;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public CaixaTestemunho[] getListacaixastestemunho() {
        return listacaixastestemunho;
    }

    public void setListacaixastestemunho(CaixaTestemunho[] listacaixastestemunho) {
        this.listacaixastestemunho = listacaixastestemunho;
    }

    public byte getTotalcaixas() {
        return totalcaixas;
    }

    public void setTotalcaixas(byte totalcaixas) {
        this.totalcaixas = totalcaixas;
    }

}    