package apptestemunho.objetos;

/**
 *
 * @author Felipe Passos
 */
public class Bancada {
    private String nome;
    private Caixa[] listacaixas;
    private float tamanhobancada;
    

    public Bancada(String nome, Caixa[] listacaixas, float tamanhobancada) {
        this.nome = nome;
        this.listacaixas = listacaixas;
        this.tamanhobancada = tamanhobancada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Caixa[] getListacaixas() {
        return listacaixas;
        
    }

    public void setListacaixas(Caixa[] listacaixas) {
        this.listacaixas = listacaixas;
    }

    public float getTamanhobancada() {
        return tamanhobancada;
    }

    public void setTamanhobancada(float tamanhobancada) {
        this.tamanhobancada = tamanhobancada;
    }
    
    //método para recuperar lista de caixas da bancada
    
        
    
    
}
    