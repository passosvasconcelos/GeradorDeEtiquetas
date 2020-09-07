package apptestemunho;

import apptestemunho.objetos.CaixaCalha;
import apptestemunho.objetos.CaixaPlug;
import apptestemunho.objetos.CaixaAparaPlug;
import apptestemunho.objetos.CaixaLateral;
import apptestemunho.objetos.CaixaTestemunho;
import java.io.InputStream;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Felipe Passos
 */
public class GerarEtiqueta {
    //métodos que devem ser utilizados para gerar etiquetas segundo o tipo de amostra  
    public static Canvas gerarEtiquetaCaixaLateral(CaixaLateral caixa) throws Exception{
        System.out.println("iniciou gerar etiqueta");
        Canvas etiqueta = new Canvas(283, 232);       
        GraphicsContext ctx = etiqueta.getGraphicsContext2D();
        ctx.drawImage(QRCodeGenerator.gerarQRCodeCaixaLateral(caixa), 170, 80);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 49));  
        ctx.fillText(caixa.getEndereco(), 10, 40);
        ctx.fillRect(10, 50, 260, 2);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 33)); 
        
        ctx.fillText(caixa.getPoco().getNomePoco(), 10, 90);
        ctx.setFill(Color.RED);
        ctx.fillText(caixa.getTipoamostra(), 10, 125);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 20));  
        ctx.fillText("Intv:\n"+caixa.getTopo()+"\n"+caixa.getBase(), 10, 145);
       
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 40));  
        ctx.fillText("Cx:"+caixa.getCaixa(), 70, 230);
        System.out.println("gerou uma etiqueta");
                
        return etiqueta;
    }
    public static Canvas gerarEtiquetaCaixaTestemunho(CaixaTestemunho caixa)throws Exception{
        Canvas etiqueta = new Canvas(283,232);
        GraphicsContext ctx = etiqueta.getGraphicsContext2D();
        ctx.drawImage(QRCodeGenerator.gerarQRCodeCaixaTestemunho(caixa), 170, 80);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 49));  
        ctx.fillText(caixa.getEndereco(), 10, 40);
        ctx.fillRect(10, 50, 260, 2);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 33)); 
        
        ctx.fillText(caixa.getPoco().getNomePoco(), 19, 90);
        ctx.setFill(Color.BLUE);
        ctx.fillText("Testo "+caixa.getDetalhes().substring(1, 3), 10, 125);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        ctx.fillText("Intv:\n"+caixa.getTopo()+"\n"+caixa.getBase(), 10, 145);
        
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 40));  
        ctx.fillText("Cx:"+caixa.getCaixa(), 70, 230);
        return etiqueta;        
    }
    public static Canvas gerarEtiquetaCaixaCalha(CaixaCalha caixa) throws Exception{
        
        Canvas etiqueta = new Canvas(283,232);
        GraphicsContext ctx = etiqueta.getGraphicsContext2D();
        ctx.drawImage(QRCodeGenerator.gerarQRCodeCaixaCalha(caixa), 170, 80);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 49));  
        ctx.fillText(caixa.getEndereco(), 10, 40);
        ctx.fillRect(10, 50, 260, 2);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 33)); 
        
        ctx.fillText(caixa.getPoco().getNomePoco(), 10, 90);
        ctx.fillText(caixa.getTipoamostra(), 10, 125);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 20));  
        ctx.fillText("Intv:\n"+caixa.getTopo()+"m\n"+caixa.getBase()+"m", 10, 145);
       
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 40));  
        ctx.fillText("Cx:"+caixa.getCaixa(), 70, 230);
        System.out.println("gerou uma etiqueta");
        
        return etiqueta;
    }
    public static Canvas gerarEtiquetaCaixaPlug(CaixaPlug caixa) throws Exception{
        
        Canvas etiqueta = new Canvas(283,232);
        GraphicsContext ctx = etiqueta.getGraphicsContext2D();
        ctx.drawImage(QRCodeGenerator.gerarQRCodeCaixaPlug(caixa), 170, 80);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 49));  
        ctx.fillText(caixa.getEndereco(), 10, 40);
        ctx.fillRect(10, 50, 260, 2);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 33)); 
        
        ctx.fillText(caixa.getPoco().getNomePoco(), 10, 90);
        ctx.setFill(Color.BLUE);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 25));
        ctx.fillText(caixa.getTipoamostra(), 10, 125);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 20));  
        ctx.fillText("Intv:\n"+caixa.getTopo()+"m\n"+caixa.getBase()+"m", 10, 145);
       
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 40));  
        ctx.fillText("Cx:"+caixa.getCaixa(), 70, 230);
        System.out.println("gerou uma etiqueta");
        
        return etiqueta;
    }
    public static Canvas gerarEtiquetaCaixaAparaPlug(CaixaAparaPlug caixa) throws Exception{
        
        Canvas etiqueta = new Canvas(283,232);
        GraphicsContext ctx = etiqueta.getGraphicsContext2D();
        ctx.drawImage(QRCodeGenerator.gerarQRCodeCaixaAparaPlug(caixa), 170, 80);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 49));  
        ctx.fillText(caixa.getEndereco(), 10, 40);
        ctx.fillRect(10, 50, 260, 2);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 33)); 
        
        ctx.fillText(caixa.getPoco().getNomePoco(), 10, 90);
        ctx.setFill(Color.BLUE);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 25));
        ctx.fillText(caixa.getTipoamostra(), 10, 125);
        ctx.setFill(Color.BLACK);
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 20));  
        ctx.fillText("Intv:\n"+caixa.getTopo()+"m\n"+caixa.getBase()+"m", 10, 145);
       
        ctx.setFont(Font.font("Serif", FontWeight.BOLD, 40));  
        ctx.fillText("Cx:"+caixa.getCaixa(), 70, 230);
        System.out.println("gerou uma etiqueta");
        
        return etiqueta;
    }
}
