/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apptestemunho.objetos;

import java.util.List;

/**
 *
 * @author Felipe Passos
 */
public class CaixaCalha extends Caixa {
    private List listacalhas;

    public CaixaCalha(List listacalhas, Poco poco, String tipoamostra, String topo, String base, String caixa, String instalacao, String endereco, String proprietario) {
        super(poco, tipoamostra, topo, base, caixa, instalacao, endereco, proprietario);
        this.listacalhas = listacalhas;
    }

    

    

    public List getListacalhas() {
        return listacalhas;
    }

    public void setListacalhas(List listacalhas) {
        this.listacalhas = listacalhas;
    }
}
    