package apptestemunho;

import apptestemunho.objetos.AmostraLateral;
import apptestemunho.objetos.Calha;
import apptestemunho.objetos.Plug;
import apptestemunho.objetos.AparaPlug;
import apptestemunho.objetos.CaixaCalha;
import apptestemunho.objetos.CaixaLateral;
import apptestemunho.objetos.CaixaTestemunho;
import apptestemunho.objetos.CaixaPlug;
import apptestemunho.objetos.CaixaAparaPlug;
import apptestemunho.objetos.Poco;
import apptestemunho.objetos.Testemunho;
import java.awt.image.RenderedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import jxl.WorkbookSettings;


/**
 * FXML Controller class
 *
 * @author Felipe Passos
 */
public class ViewGerarEtiquetaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnAbrirTabelaAcervo;
    private Button btnSalvarEtiquetas;
    private Button btnExportarXLS;
    private Button btnImprimir;
    public VBox vboxPimacos;
    private Canvas[] listaDeEtiquetas;
    private List listaCaixas;
    public GridPane papelPimacoCaixa;
    
    //public GridPane papelPimacoCaixa2;
    
    
    @FXML
    public Canvas[] abrirTabelaAcervo(ActionEvent event) throws IOException, BiffException, Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione a planilha");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS", "*.xls"));        
        File file = fileChooser.showOpenDialog(null);
        this.listaCaixas = lerExcel(file);
        
        String[] enderecos;
        enderecos = new String[this.listaCaixas.size()];
        
        this.listaDeEtiquetas = new Canvas[this.listaCaixas.size()];
        for (int i=0; i<this.listaCaixas.size(); i++) {
            if (this.listaCaixas.get(i).getClass() == CaixaCalha.class){
                CaixaCalha c = (CaixaCalha) this.listaCaixas.get(i);
                Canvas ca = GerarEtiqueta.gerarEtiquetaCaixaCalha(c);
                this.listaDeEtiquetas[i] = ca;
                enderecos[i] = c.getEndereco();
            } else if(this.listaCaixas.get(i).getClass() == CaixaLateral.class){
                CaixaLateral lat = (CaixaLateral) this.listaCaixas.get(i);
                Canvas ca = GerarEtiqueta.gerarEtiquetaCaixaLateral(lat);
                this.listaDeEtiquetas[i] = ca;
                enderecos[i] = lat.getEndereco();
                //listaDeEtiquetas.add(GerarEtiqueta.gerarEtiquetaCaixaLateral((CaixaLateral) listaCaixas.get(i)));
            } else if (this.listaCaixas.get(i).getClass() == CaixaTestemunho.class){
                CaixaTestemunho t = (CaixaTestemunho) this.listaCaixas.get(i);
                Canvas ca = GerarEtiqueta.gerarEtiquetaCaixaTestemunho(t);
                this.listaDeEtiquetas[i] = ca;
                enderecos[i] = t.getEndereco();
            }
            else if (this.listaCaixas.get(i).getClass() == CaixaPlug.class){
                CaixaPlug pl = (CaixaPlug) this.listaCaixas.get(i);
                Canvas ca = GerarEtiqueta.gerarEtiquetaCaixaPlug(pl);
                this.listaDeEtiquetas[i] = ca;
                enderecos[i] = pl.getEndereco();
            }
            else if (this.listaCaixas.get(i).getClass() == CaixaAparaPlug.class){
                CaixaAparaPlug appl = (CaixaAparaPlug) this.listaCaixas.get(i);
                Canvas ca = GerarEtiqueta.gerarEtiquetaCaixaAparaPlug(appl);
                this.listaDeEtiquetas[i] = ca;
                enderecos[i] = appl.getEndereco();
            }
        }
        // define linhas e colunas no papel pimaco
        int coluna = 0;
        int linha = 0;
        GridPane papelPimacoCaixa = new GridPane();
        papelPimacoCaixa.setGridLinesVisible(true);
        papelPimacoCaixa.setHgap(15);
        
        
        for (int i=0; i<this.listaCaixas.size(); i++) {
            WritableImage etiqueta = new WritableImage(283, 240);
            this.listaDeEtiquetas[i].snapshot(null, etiqueta);
            papelPimacoCaixa.add(new ImageView(etiqueta), coluna, linha); 
            if (coluna==0 & linha<=2){
                coluna++;
                if (this.listaCaixas.size()-i == 1){
                    vboxPimacos.getChildren().add(papelPimacoCaixa);
                }
            } else if(coluna == 1 & linha<2){
                linha++;
                coluna = 0;
                if (this.listaCaixas.size()-i == 1){
                    vboxPimacos.getChildren().add(papelPimacoCaixa);
                }
            }else{
                vboxPimacos.getChildren().add(papelPimacoCaixa);
                
                papelPimacoCaixa = new GridPane();
                papelPimacoCaixa.setGridLinesVisible(true);
                papelPimacoCaixa.setHgap(15);
                
                coluna = 0;
                linha = 0;
                
                        
                //GridPane papelPimacoCaixa2 = new GridPane();
                               
                
                //vboxPimacos.getChildren().add(papelPimacoCaixa2);
                //vboxPimacos.setPrefHeight(vboxPimacos.getPrefHeight()+1019);
                
                //System.out.println(vboxPimacos.getPrefHeight());
                
            }
            
        }
        
        
        return this.listaDeEtiquetas;
    }
    
    public List lerExcel(File arquivoExcel) throws IOException, BiffException {
        WorkbookSettings workbookSettings = new WorkbookSettings();
        workbookSettings.setEncoding( "Cp1252" );
        
        Workbook workbook = Workbook.getWorkbook(arquivoExcel, workbookSettings);
        Sheet sheet = workbook.getSheet(0);
        int linhas = sheet.getRows();
        List<Calha> listacalhas = new ArrayList<>();
        List<Plug> listaplugs = new ArrayList<>();
        List<AparaPlug> listaaparaplugs = new ArrayList<>();
        List<AmostraLateral> listaAmostraLateral = new ArrayList<>();
        
        //identificando o índice de coluna de cada conteúdo na tabela
        int coluna_poco;
        int coluna_id_plug;
        int coluna_tipo_item;
        int coluna_profundidade;
        int coluna_profundidade_testemunho;
        int coluna_profundidade_segmento;
        int coluna_detalhes;
        //int coluna_diametro;
        int coluna_caixa;
        int coluna_segmentacao;
        //int coluna_prop_amostra;
        int coluna_instalacao;
        int coluna_endereco;
        int coluna_proprietario;
        
        
        
        
        coluna_tipo_item = sheet.findCell("Tipo de Item", 0, 0, sheet.getColumns(), 1, true).getColumn();
        /*coluna_poco = sheet.findCell("Poco", 0, 0, sheet.getColumns(), 1, true).getColumn();
        System.out.println("leu o findcell poço e o índice da coluna poço é:");
        System.out.println(coluna_poco);
        coluna_profundidade = sheet.findCell("Profundidade", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_profundidade_testemunho = sheet.findCell("Profundidade (Testemunho)", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_profundidade_segmento = sheet.findCell("Profundidade (Segmento)", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_detalhes = sheet.findCell("Detalhes", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_diametro = sheet.findCell("Diâmetro(pol)", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_caixa = sheet.findCell("Caixa", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_segmentacao = sheet.findCell("Segmentação", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_prop_amostra = sheet.findCell("Prop. Amostra", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_instalacao = sheet.findCell("Instalação", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_endereco = sheet.findCell("Endereço", 0, 0, sheet.getColumns(), 1, true).getColumn();
        coluna_proprietario = sheet.findCell("Proprietário", 0, 0, sheet.getColumns(), 1, true).getColumn();
        */
        
        List l = new ArrayList<>();
        
        
        //usando a coluna "Tipo de Item" para identificar o tipo de etiqueta a ser gerada
        String tipo_item = new String();
        tipo_item = sheet.getCell(coluna_tipo_item, 1).getContents();
        switch (tipo_item) {
            
            case "Calha":
                {
                    coluna_poco = sheet.findCell("Poço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_profundidade = sheet.findCell("Profundidade", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_caixa = sheet.findCell("Caixa", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_instalacao = sheet.findCell("Instalação", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_endereco = sheet.findCell("Endereço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_proprietario = sheet.findCell("Proprietário", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    
                    Poco p = new Poco("");
                    String prof = "";
                    
                    String caixa = "";
                    String instalacao = "";
                    
                    String endereco = sheet.getCell(coluna_endereco, 1).getContents() ;
                    String proprietario= "";
                    
                    int totalcaixas = 0;
                    
                    for (int i = 1; i <linhas; i++){
                        
                        if (!endereco.equals(sheet.getCell(coluna_endereco, i).getContents())){
                            
                            String topo = listacalhas.get(0).getProf();
                            String base = listacalhas.get(listacalhas.size()-1).getProf();
                            CaixaCalha caixacalha = new CaixaCalha(listacalhas, p, "Calha", topo, base, caixa, instalacao, endereco, proprietario);
                            l.add(caixacalha);
                            totalcaixas++;
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            
                            listacalhas.clear();
                            
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            
                            listacalhas.add(new Calha(p, prof, caixa, instalacao, endereco, proprietario));
                            
                        } else {
                            
                            p = new Poco(sheet.getCell(coluna_poco, i).getContents()); 
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            
                            caixa = sheet.getCell(coluna_caixa, i).getContents();
                            instalacao = sheet.getCell(coluna_instalacao, i).getContents();
                            
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            proprietario = sheet.getCell(coluna_proprietario, i).getContents();
                            listacalhas.add(new Calha(p, prof, caixa, instalacao, endereco, proprietario));
                            if (linhas - i == 1){
                                String topo = listacalhas.get(0).getProf();
                                String base = listacalhas.get(listacalhas.size()-1).getProf();
                                CaixaCalha caixacalha = new CaixaCalha(listacalhas, p, "Calha", topo, base, caixa, instalacao, endereco, proprietario);
                                l.add(caixacalha);
                                totalcaixas++;
                            }
                        }
                    }
                    System.out.println("total de caixas é: "+totalcaixas);
                    
                
                    System.out.println("termina de ler excel");
                    workbook.close();
                    
                
                }
            return l;
            case "Testemunho":
                {
                    coluna_poco = sheet.findCell("Poço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_profundidade_segmento = sheet.findCell("Profundidade (Segmento)", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_detalhes = sheet.findCell("Detalhes", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_caixa = sheet.findCell("Caixa", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_segmentacao = sheet.findCell("Segmentação", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_instalacao = sheet.findCell("Instalação", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_endereco = sheet.findCell("Endereço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_proprietario = sheet.findCell("Prop. Amostra", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    
                    //então se trata de tabela de testemunho
                    
                    Poco p = new Poco("");                    
                    Testemunho t = new Testemunho(p, sheet.getCell(coluna_detalhes, 1).getContents());
                    String topo = "";
                    String base = "";
                    String detalhes = "";
                    String caixa = "";
                    String tipoSegmento = "";
                    String proprietario= "";
                    String instalacao = "";
                    
                    String endereco = sheet.getCell(coluna_endereco, 1).getContents() ;
                    
                    
                    int totalcaixas = 0;
                    
                    for (int i = 1; i <linhas; i++){
                        
                        if (!endereco.equals(sheet.getCell(coluna_endereco, i).getContents())){
                                      
                            CaixaTestemunho caixaTestemunho = new CaixaTestemunho(t,detalhes,tipoSegmento,p, "Testemunho", topo, base, caixa,instalacao, endereco, proprietario);
                            l.add(caixaTestemunho);
                            totalcaixas++;
                            p = new Poco(sheet.getCell(coluna_poco, i).getContents()); 
                            t = new Testemunho(p, sheet.getCell(coluna_detalhes, 1).getContents());
                            topo = (sheet.getCell(coluna_profundidade_segmento, i).getContents().split("-")[0])+" m";
                            base = (sheet.getCell(coluna_profundidade_segmento, i).getContents().split("-")[1]);
                            detalhes = sheet.getCell(coluna_detalhes, i).getContents();
                            caixa = sheet.getCell(coluna_caixa, i).getContents();
                            tipoSegmento = sheet.getCell(coluna_segmentacao, i).getContents();
                            proprietario = sheet.getCell(coluna_proprietario, i).getContents();
                            instalacao = sheet.getCell(coluna_instalacao, i).getContents();
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            if (linhas - i == 1){
                                
                                caixaTestemunho = new CaixaTestemunho(t,detalhes,tipoSegmento,p, "Testemunho", topo, base, caixa, instalacao, endereco, proprietario);
                                l.add(caixaTestemunho);
                                totalcaixas++;
                            }
                            
                            System.out.println("entrou no if");
                                                         
                        } else {
                            p = new Poco(sheet.getCell(coluna_poco, i).getContents()); 
                            t = new Testemunho(p, sheet.getCell(coluna_detalhes, 1).getContents());
                            //topo = (sheet.getCell(coluna_profundidade_segmento, i).getContents().substring(0,8))+" m";
                           // base = (sheet.getCell(coluna_profundidade_segmento, i).getContents().substring(9,17))+" m";
                            topo = (sheet.getCell(coluna_profundidade_segmento, i).getContents().split("-")[0])+" m";
                            base = (sheet.getCell(coluna_profundidade_segmento, i).getContents().split("-")[1]);
                            detalhes = sheet.getCell(coluna_detalhes, i).getContents();
                            caixa = sheet.getCell(coluna_caixa, i).getContents();
                            tipoSegmento = sheet.getCell(coluna_segmentacao, i).getContents();
                            proprietario = sheet.getCell(coluna_proprietario, i).getContents();
                            instalacao = sheet.getCell(coluna_instalacao, i).getContents();
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            System.out.println("entrou no else");
                            
                            if (linhas - i == 1){
                                
                                CaixaTestemunho caixaTestemunho = new CaixaTestemunho(t,detalhes,tipoSegmento,p, "Testemunho", topo, base, caixa, instalacao, endereco, proprietario);
                                l.add(caixaTestemunho);
                                totalcaixas++;
                            }
                        }
                    }
                    System.out.println("total de caixas é: "+totalcaixas);
                 
                    workbook.close();
                    
                }
            return l;
            case "Plug":
                {
                    coluna_poco = sheet.findCell("Poço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_profundidade = sheet.findCell("Profundidade", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_id_plug = sheet.findCell("No. Plug", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_caixa = sheet.findCell("Caixa", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_instalacao = sheet.findCell("Instalação", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_endereco = sheet.findCell("Endereço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_proprietario = sheet.findCell("Proprietário", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    
                    Poco p = new Poco("");
                    String prof = "";
                    String id_plug = "";
                    String caixa = "";
                    String instalacao = "";
                    
                    String endereco = sheet.getCell(coluna_endereco, 1).getContents() ;
                    String proprietario= "";
                    
                    int totalcaixas = 0;
                    
                    for (int i = 1; i <linhas; i++){
                        
                        if (!endereco.equals(sheet.getCell(coluna_endereco, i).getContents())){
                            
                            String topo = listaplugs.get(0).getProf();
                            String base = listaplugs.get(listaplugs.size()-1).getProf();
                            CaixaPlug caixaplug = new CaixaPlug(listaplugs, p, "Plug", topo, base, caixa, instalacao, endereco, proprietario);
                            l.add(caixaplug);
                            totalcaixas++;
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            
                            listaplugs.clear();
                            
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            
                            listaplugs.add(new Plug(p, prof, id_plug, caixa, instalacao, endereco, proprietario));
                            
                        } else {
                            
                            p = new Poco(sheet.getCell(coluna_poco, i).getContents()); 
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            
                            caixa = sheet.getCell(coluna_caixa, i).getContents();
                            instalacao = sheet.getCell(coluna_instalacao, i).getContents();
                            
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            proprietario = sheet.getCell(coluna_proprietario, i).getContents();
                            listaplugs.add(new Plug(p, prof, id_plug, caixa, instalacao, endereco, proprietario));
                            if (linhas - i == 1){
                                String topo = listaplugs.get(0).getProf();
                                String base = listaplugs.get(listaplugs.size()-1).getProf();
                                CaixaPlug caixaplug = new CaixaPlug(listaplugs, p, "Plug", topo, base, caixa, instalacao, endereco, proprietario);
                                l.add(caixaplug);
                                totalcaixas++;
                            }
                        }
                    }
                    System.out.println("total de caixas é: "+totalcaixas);
                    
                
                    System.out.println("termina de ler excel");
                    workbook.close();
                    
                
                }
            return l;
            case "Apara de plug":
                {
                    coluna_poco = sheet.findCell("Poço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_profundidade = sheet.findCell("Profundidade", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_id_plug = sheet.findCell("No. Plug", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_caixa = sheet.findCell("Caixa", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_instalacao = sheet.findCell("Instalação", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_endereco = sheet.findCell("Endereço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_proprietario = sheet.findCell("Proprietário", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    
                    Poco p = new Poco("");
                    String prof = "";
                    String id_plug = "";
                    String caixa = "";
                    String instalacao = "";
                    
                    String endereco = sheet.getCell(coluna_endereco, 1).getContents() ;
                    String proprietario= "";
                    
                    int totalcaixas = 0;
                    
                    for (int i = 1; i <linhas; i++){
                        
                        if (!endereco.equals(sheet.getCell(coluna_endereco, i).getContents())){
                            
                            String topo = listaaparaplugs.get(0).getProf();
                            String base = listaaparaplugs.get(listaaparaplugs.size()-1).getProf();
                            CaixaAparaPlug caixaaparaplug = new CaixaAparaPlug(listaaparaplugs, p, "Apara de plug", topo, base, caixa, instalacao, endereco, proprietario);
                            l.add(caixaaparaplug);
                            totalcaixas++;
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            
                            listaaparaplugs.clear();
                            
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            
                            listaaparaplugs.add(new AparaPlug(p, prof, id_plug, caixa, instalacao, endereco, proprietario));
                            
                        } else {
                            
                            p = new Poco(sheet.getCell(coluna_poco, i).getContents()); 
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            
                            caixa = sheet.getCell(coluna_caixa, i).getContents();
                            instalacao = sheet.getCell(coluna_instalacao, i).getContents();
                            
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            proprietario = sheet.getCell(coluna_proprietario, i).getContents();
                            listaaparaplugs.add(new AparaPlug(p, prof, id_plug, caixa, instalacao, endereco, proprietario));
                            if (linhas - i == 1){
                                String topo = listaaparaplugs.get(0).getProf();
                                String base = listaaparaplugs.get(listaaparaplugs.size()-1).getProf();
                                CaixaAparaPlug caixaaparaplug = new CaixaAparaPlug(listaaparaplugs, p, "Apara de plug", topo, base, caixa, instalacao, endereco, proprietario);
                                l.add(caixaaparaplug);
                                totalcaixas++;
                            }
                        }
                    }
                    System.out.println("total de caixas é: "+totalcaixas);
                    
                
                    System.out.println("termina de ler excel");
                    workbook.close();
                    
                
                }
            return l;
            case "Amostra Lateral":
                {
                    coluna_poco = sheet.findCell("Poço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_profundidade = sheet.findCell("Profundidade", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_detalhes = sheet.findCell("Detalhes", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_caixa = sheet.findCell("Caixa", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_instalacao = sheet.findCell("Instalação", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_endereco = sheet.findCell("Endereço", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    coluna_proprietario = sheet.findCell("Proprietário", 0, 0, sheet.getColumns(), 1, true).getColumn();
                    
                    //então se trata de tabela de lateral
                    Poco p = new Poco("");                    
                    String prof = "";
                    
                    String detalhes = "";
                    String caixa = "";
                    String peso = "";
                    String comprimento = "";
                    String estado = "";
                    String instalacao = "";
                    
                    String endereco = sheet.getCell(coluna_endereco, 1).getContents() ;
                    String proprietario= "";
                    
                                        
                    int totalcaixas = 0;
                    
                    for (int i = 1; i <linhas; i++){
                        
                        if (!endereco.equals(sheet.getCell(coluna_endereco, i).getContents())){
                            System.out.println("entrou no if");
                            String topo = listaAmostraLateral.get(0).getProf();
                            String base = listaAmostraLateral.get(listaAmostraLateral.size()-1).getProf();
                            CaixaLateral caixaLateral = new CaixaLateral(listaAmostraLateral, p, "Lateral", topo, base, caixa,instalacao, endereco, proprietario);
                            l.add(caixaLateral);
                            totalcaixas++;
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            
                            listaAmostraLateral.clear();
                            
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            p = new Poco(sheet.getCell(coluna_poco, i).getContents());
                            listaAmostraLateral.add(new AmostraLateral(p, prof));
                            
                            
                        } else {
                            System.out.println("entrou no else");
                            p = new Poco(sheet.getCell(coluna_poco, i).getContents()); 
                            prof = sheet.getCell(coluna_profundidade, i).getContents();
                            detalhes = sheet.getCell(coluna_detalhes, i).getContents();
                            caixa = sheet.getCell(coluna_caixa, i).getContents();
                            instalacao = sheet.getCell(coluna_instalacao, i).getContents();
                            endereco = sheet.getCell(coluna_endereco, i).getContents();
                            proprietario = sheet.getCell(coluna_proprietario, i).getContents();
                            listaAmostraLateral.add(new AmostraLateral(p, prof));
                            if (linhas - i == 1){
                                String topo = listaAmostraLateral.get(0).getProf();
                                String base = listaAmostraLateral.get(listaAmostraLateral.size()-1).getProf();
                                CaixaLateral caixaLateral = new CaixaLateral(listaAmostraLateral, p, "Lateral", topo, base, caixa,instalacao, endereco, proprietario);
                                l.add(caixaLateral);
                                totalcaixas++;
                            }
                        }
                    }
                    System.out.println("total de caixas é: "+totalcaixas);
                    
                
                    
                    workbook.close();
                }
        }
        
        return l;
    
    }
    @FXML
    public void salvarEtiquetas(ActionEvent event){
        DirectoryChooser pasta = new DirectoryChooser();
        pasta.setTitle("Selecione a pasta destino");        
        File destino = pasta.showDialog(null);
        System.out.println("a pasta é: "+destino.getAbsolutePath());
        System.out.println("tamanha da lista é: "+this.listaDeEtiquetas[2].toString());
        for (int i=0; i<this.listaDeEtiquetas.length; i++){
            File file;
            if (this.listaCaixas.get(i).getClass() == CaixaCalha.class){
                CaixaCalha caixa = (CaixaCalha) this.listaCaixas.get(i);
                file = new File(destino.getAbsolutePath()+"\\"+caixa.getPoco().getNomePoco()+"_"+caixa.getTipoamostra()+"_"+caixa.getTopo()+"_"+caixa.getBase()+".png");
                System.out.println("file: "+file.getName());
                try {
                    WritableImage writableImage = new WritableImage(282, 240);
                    this.listaDeEtiquetas[i].snapshot(null, writableImage);
                
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                
                    ImageIO.write(renderedImage, "png", file);
                
                } catch (IOException ex) {
                
                }
            }else if (this.listaCaixas.get(i).getClass() == CaixaLateral.class){
                CaixaLateral caixa = (CaixaLateral) this.listaCaixas.get(i);
                file = new File(destino.getAbsolutePath()+"\\"+caixa.getPoco().getNomePoco()+"_"+caixa.getTipoamostra()+"_"+caixa.getTopo()+"_"+caixa.getBase()+".png");
                System.out.println("file: "+file.getName());
                try {
                    WritableImage writableImage = new WritableImage(282, 240);
                    this.listaDeEtiquetas[i].snapshot(null, writableImage);
                
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                
                    ImageIO.write(renderedImage, "png", file);
                
                } catch (IOException ex) {
                
                }
            }else if (this.listaCaixas.get(i).getClass() == CaixaTestemunho.class){
                CaixaTestemunho caixa = (CaixaTestemunho) this.listaCaixas.get(i);
                file = new File(destino.getAbsolutePath()+"\\"+caixa.getPoco().getNomePoco()+"_"+"Testo "+caixa.getDetalhes().substring(1, 3)+"_"+caixa.getTopo()+"_"+caixa.getBase()+".png");
                System.out.println("file: "+file.getName());
                try {
                    WritableImage writableImage = new WritableImage(282, 240);
                    this.listaDeEtiquetas[i].snapshot(null, writableImage);
                
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                
                    ImageIO.write(renderedImage, "png", file);
                
                } catch (IOException ex) {
                
                }
            }else if (this.listaCaixas.get(i).getClass() == CaixaPlug.class){
                CaixaPlug caixa = (CaixaPlug) this.listaCaixas.get(i);
                file = new File(destino.getAbsolutePath()+"\\"+caixa.getPoco().getNomePoco()+"_"+caixa.getTipoamostra()+"_"+caixa.getTopo()+"_"+caixa.getBase()+".png");
                System.out.println("file: "+file.getName());
                try {
                    WritableImage writableImage = new WritableImage(282, 240);
                    this.listaDeEtiquetas[i].snapshot(null, writableImage);
                
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                
                    ImageIO.write(renderedImage, "png", file);
                
                } catch (IOException ex) {
                
                }
            }else if (this.listaCaixas.get(i).getClass() == CaixaAparaPlug.class){
                CaixaAparaPlug caixa = (CaixaAparaPlug) this.listaCaixas.get(i);
                file = new File(destino.getAbsolutePath()+"\\"+caixa.getPoco().getNomePoco()+"_"+caixa.getTipoamostra()+"_"+caixa.getTopo()+"_"+caixa.getBase()+".png");
                System.out.println("file: "+file.getName());
                try {
                    WritableImage writableImage = new WritableImage(282, 240);
                    this.listaDeEtiquetas[i].snapshot(null, writableImage);
                
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                
                    ImageIO.write(renderedImage, "png", file);
                
                } catch (IOException ex) {
                
                }
            }    
        }
    }
    public void Imprimir (ActionEvent event){
        
        PrinterJob job = PrinterJob.createPrinterJob();
        // Print the node
        Stage novo = new Stage();
        PageLayout pagelayout = Printer.getDefaultPrinter().createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, 13.33f, 13.33f, 40.0f, 26.66f);
        
        job.getJobSettings().setPageLayout(pagelayout);
        
        
        if (job != null && job.showPrintDialog(vboxPimacos.getScene().getWindow())){
        System.out.println(vboxPimacos.getChildren().size());
        int paginas = vboxPimacos.getChildren().size();
            for (int i=0; i<paginas; i++) {
            job.printPage(vboxPimacos.getChildren().get(0));
            vboxPimacos.getChildren().remove(0);
            }
        }
        
        job.endJob();
    }
    @FXML
    public void exportarXLS(ActionEvent event)throws WriteException{
        DirectoryChooser pasta = new DirectoryChooser();
        pasta.setTitle("Selecione a pasta destino");        
        File destino = pasta.showDialog(null);
        System.out.println("a pasta é: "+destino.getAbsolutePath());
        System.out.println("tamanha da lista é: "+Integer.toString(this.listaCaixas.size()));
        try{
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("pt", "BR"));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(destino.getAbsolutePath()+"\\etiquetas_para_zebra_printer.xls"), ws);
            WritableSheet s = workbook.createSheet("Folha1", 0);
            /* Formata a fonte */
            WritableFont wf = new WritableFont(WritableFont.ARIAL,10, WritableFont.NO_BOLD);
            WritableCellFormat cf = new WritableCellFormat(wf);
            cf.setWrap(false);
            Label qrcode_field = new Label(0,0,"QRCode",cf);
            Label poco_field = new Label(1,0,"poco",cf);
            Label tipoAmostra_field = new Label(2,0,"tipoAmostra",cf);
            Label topo_field = new Label(3,0,"Topo",cf);
            Label base_field = new Label(4,0,"Base",cf);
            Label cx_field = new Label(5,0,"Caixa",cf);
            Label end_field = new Label(6,0,"Endereço",cf);
            s.addCell(qrcode_field);
            s.addCell(poco_field);
            s.addCell(tipoAmostra_field);
            s.addCell(topo_field);
            s.addCell(base_field);
            s.addCell(cx_field);
            s.addCell(end_field);
            for (int i=0; i<this.listaCaixas.size(); i++){
                if (this.listaCaixas.get(i).getClass() == CaixaCalha.class){
                    CaixaCalha caixa = (CaixaCalha) this.listaCaixas.get(i);
                    Label qrcode = new Label(0,i+1,"Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Caixa:"+caixa.getCaixa()+";Topo:"+caixa.getTopo()+";base:"+caixa.getBase()+";Endereco:"+caixa.getEndereco(),cf);
                    Label poco = new Label(1,i+1,caixa.getPoco().getNomePoco(),cf);
                    Label tipoAmostra = new Label(2,i+1,caixa.getTipoamostra(),cf);
                    Label topo = new Label(3,i+1,caixa.getTopo(),cf);
                    Label base = new Label(4,i+1,caixa.getBase(),cf);
                    Label cx = new Label(5,i+1,caixa.getCaixa(),cf);
                    Label end = new Label(6,i+1,caixa.getEndereco(),cf);
                    s.addCell(qrcode);
                    s.addCell(poco);
                    s.addCell(tipoAmostra);
                    s.addCell(topo);
                    s.addCell(base);
                    s.addCell(cx);
                    s.addCell(end);
                } else if(this.listaCaixas.get(i).getClass() == CaixaLateral.class){
                    CaixaLateral caixa = (CaixaLateral) this.listaCaixas.get(i);
                    Label qrcode = new Label(0,i+1,"Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Topo:"+caixa.getTopo()+";Base:"+caixa.getBase()+";Caixa:"+caixa.getCaixa()+";Endereco:"+caixa.getEndereco(),cf);
                    Label poco = new Label(1,i+1,caixa.getPoco().getNomePoco(),cf);
                    Label tipoAmostra = new Label(2,i+1,caixa.getTipoamostra(),cf);
                    Label topo = new Label(3,i+1,caixa.getTopo(),cf);
                    Label base = new Label(4,i+1,caixa.getBase(),cf);
                    Label cx = new Label(5,i+1,caixa.getCaixa(),cf);
                    Label end = new Label(6,i+1,caixa.getEndereco(),cf);
                    s.addCell(qrcode);
                    s.addCell(poco);
                    s.addCell(tipoAmostra);
                    s.addCell(topo);
                    s.addCell(base);
                    s.addCell(cx);
                    s.addCell(end);
                    //listaDeEtiquetas.add(GerarEtiqueta.gerarEtiquetaCaixaLateral((CaixaLateral) listaCaixas.get(i)));
                } else if (this.listaCaixas.get(i).getClass() == CaixaTestemunho.class){
                    CaixaTestemunho caixa = (CaixaTestemunho) this.listaCaixas.get(i);
                    System.out.println(caixa.getDetalhes());
                    Label qrcode = new Label(0,i+1,"Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";"+caixa.getDetalhes()+";Caixa:"+caixa.getCaixa()+";Topo:"+caixa.getTopo()+";Base:"+caixa.getBase()+";Endereco:"+caixa.getEndereco(),cf);
                    Label poco = new Label(1,i+1,caixa.getPoco().getNomePoco(),cf);
                    Label tipoAmostra = new Label(2,i+1,"Testo "+caixa.getDetalhes().substring(1, 3),cf);
                    Label topo = new Label(3,i+1,caixa.getTopo(),cf);
                    Label base = new Label(4,i+1,caixa.getBase(),cf);
                    Label cx = new Label(5,i+1,caixa.getCaixa(),cf);
                    Label end = new Label(6,i+1,caixa.getEndereco(),cf);
                    s.addCell(qrcode);
                    s.addCell(poco);
                    s.addCell(tipoAmostra);
                    s.addCell(topo);
                    s.addCell(base);
                    s.addCell(cx);
                    s.addCell(end);
                    
                    
                }
                else if (this.listaCaixas.get(i).getClass() == CaixaPlug.class){
                    CaixaPlug caixa = (CaixaPlug) this.listaCaixas.get(i);
                    Label qrcode = new Label(0,i+1,"Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Topo:"+caixa.getTopo()+";Base:"+caixa.getBase()+";Caixa:"+caixa.getCaixa()+";Endereco:"+caixa.getEndereco(),cf);
                    Label poco = new Label(1,i+1,caixa.getPoco().getNomePoco(),cf);
                    Label tipoAmostra = new Label(2,i+1,caixa.getTipoamostra(),cf);
                    Label topo = new Label(3,i+1,caixa.getTopo(),cf);
                    Label base = new Label(4,i+1,caixa.getBase(),cf);
                    Label cx = new Label(5,i+1,caixa.getCaixa(),cf);
                    Label end = new Label(6,i+1,caixa.getEndereco(),cf);
                    s.addCell(qrcode);
                    s.addCell(poco);
                    s.addCell(tipoAmostra);
                    s.addCell(topo);
                    s.addCell(base);
                    s.addCell(cx);
                    s.addCell(end);
                }
                else if (this.listaCaixas.get(i).getClass() == CaixaAparaPlug.class){
                    CaixaAparaPlug caixa = (CaixaAparaPlug) this.listaCaixas.get(i);
                    Label qrcode = new Label(0,i+1,"Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Topo:"+caixa.getTopo()+";Base:"+caixa.getBase()+";Caixa:"+caixa.getCaixa()+";Endereco:"+caixa.getEndereco(),cf);
                    Label poco = new Label(1,i,caixa.getPoco().getNomePoco(),cf);
                    Label tipoAmostra = new Label(2,i+1,caixa.getTipoamostra(),cf);
                    Label topo = new Label(3,i+1,caixa.getTopo(),cf);
                    Label base = new Label(4,i+1,caixa.getBase(),cf);
                    Label cx = new Label(5,i+1,caixa.getCaixa(),cf);
                    Label end = new Label(6,i+1,caixa.getEndereco(),cf);
                    s.addCell(qrcode);
                    s.addCell(poco);
                    s.addCell(tipoAmostra);
                    s.addCell(topo);
                    s.addCell(base);
                    s.addCell(cx);
                    s.addCell(end);
                } 
            }
            workbook.write();
            workbook.close();
        } catch(IOException e){
            e.printStackTrace();            
        } catch(WriteException e){
            e.printStackTrace();            
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
