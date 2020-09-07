/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apptestemunho.objetos;

import java.util.List;

/**
 *
 * @author b9c2
 */
public class CaixaPlug extends Caixa {
    private List listaplugs;

    public CaixaPlug(List listaplugs, Poco poco, String tipoamostra, String topo, String base, String caixa, String instalacao, String endereco, String proprietario) {
        super(poco, tipoamostra, topo, base, caixa, instalacao, endereco, proprietario);
        this.listaplugs = listaplugs;
    }

    

    

    public List getListaplugs() {
        return listaplugs;
    }

    public void setListaplugs(List listaplugs) {
        this.listaplugs = listaplugs;
    }
}