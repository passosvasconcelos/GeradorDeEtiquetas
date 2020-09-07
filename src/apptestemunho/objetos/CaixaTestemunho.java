package apptestemunho.objetos;

/**
 *
 * @author Felipe Passos
 */
public class CaixaTestemunho extends Caixa{
    private Testemunho testemunho;
    private String detalhes;
    private String tipoSegmento;

    public CaixaTestemunho(Testemunho testemunho, String detalhes, String tipoSegmento, Poco poco, String tipoamostra, String topo, String base, String caixa, String instalacao, String endereco, String proprietario) {
        super(poco, tipoamostra, topo, base, caixa, instalacao, endereco, proprietario);
        this.testemunho = testemunho;
        this.detalhes = detalhes;
        this.tipoSegmento = tipoSegmento;
    }

    

    public Testemunho getTestemunho() {
        return testemunho;
    }

    public void setTestemunho(Testemunho testemunho) {
        this.testemunho = testemunho;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getTipoSegmento() {
        return tipoSegmento;
    }

    public void setTipoSegmento(String tipoSegmento) {
        this.tipoSegmento = tipoSegmento;
    }
    
    

    
}
    