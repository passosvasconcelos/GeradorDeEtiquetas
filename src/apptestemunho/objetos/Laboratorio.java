package apptestemunho.objetos;

/**
 *
 * @author Felipe Passos
 */
public class Laboratorio {
    private String nome;
    private Bancada[] listabancadas;

    public Laboratorio(String nome, Bancada[] listabancadas) {
        this.nome = nome;
        this.listabancadas = listabancadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Bancada[] getListabancadas() {
        return listabancadas;
    }

    public void setListabancadas(Bancada[] listabancadas) {
        this.listabancadas = listabancadas;
    }
    
}
