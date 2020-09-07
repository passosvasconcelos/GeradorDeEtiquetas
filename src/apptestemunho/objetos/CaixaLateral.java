package apptestemunho.objetos;

import java.util.List;

/**
 *
 * @author Felipe Passos
 */
public class CaixaLateral extends Caixa{
    private List amostralateral;

    public CaixaLateral(List amostralateral, Poco poco, String tipoamostra, String topo, String base, String caixa, String instalacao, String endereco, String proprietario) {
        super(poco, tipoamostra, topo, base, caixa, instalacao, endereco, proprietario);
        this.amostralateral = amostralateral;
    }

    

    public List getAmostralateral() {
        return amostralateral;
    }

    public void setAmostralateral(List amostralateral) {
        this.amostralateral = amostralateral;
    }
    

    

    

    
    
}