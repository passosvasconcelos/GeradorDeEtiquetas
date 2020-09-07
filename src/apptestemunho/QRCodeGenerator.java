package apptestemunho;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import apptestemunho.objetos.Caixa;
import apptestemunho.objetos.CaixaCalha;
import apptestemunho.objetos.CaixaPlug;
import apptestemunho.objetos.CaixaAparaPlug;
import apptestemunho.objetos.CaixaLateral;
import apptestemunho.objetos.CaixaTestemunho;
import java.io.ByteArrayInputStream;
//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
//import javax.imageio.ImageIO;
//import sun.plugin.javascript.navig.Image;
import static sun.plugin.javascript.navig.JSType.Image;

/**
 *
 * @author Felipe Passos
 */
public class QRCodeGenerator {
    byte[] qrcode;
    Image imgqrcode;

    public QRCodeGenerator(byte[] qrcode, Image imgqrcode) {
        this.qrcode = qrcode;
        this.imgqrcode = imgqrcode;
    }

    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }

    public Image getImgqrcode() {
        return imgqrcode;
    }

    public void setImgqrcode(Image imgqrcode) {
        this.imgqrcode = imgqrcode;
    }
    
    public static Image gerarQRCodeCaixaLateral(CaixaLateral caixa)  throws Exception {
        
        System.out.println("entrou no gerador de qrcode");
        String details = "Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Topo:"+caixa.getTopo()+";Base:"+caixa.getBase()+";Caixa:"+caixa.getCaixa()+";Endereco:"+caixa.getEndereco();
        byte[] byteArrayQRCode = QRCode.from(details).to(ImageType.JPG).stream().toByteArray();
        ByteArrayInputStream inputByteArrayQRCode = new ByteArrayInputStream(byteArrayQRCode);
        Image imgqrcode = new Image(inputByteArrayQRCode);
        System.out.println("qrcode de lateral gerado");
        /*ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
        File f = new File("C:\\Users\\Felipe Passos\\Documents\\PESSOAL\\teste.jpg");
        FileOutputStream fos = new FileOutputStream(f);
        
        fos.write(out.toByteArray());
        fos.flush();
        BufferedImage imgqrcode = null;
        try {
            imgqrcode = ImageIO.read(new File("C:\\Users\\Felipe Passos\\Documents\\PESSOAL\\teste.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        System.out.println("qrcode de lateral gerado");
        return imgqrcode;
        
        
        
    }
    public static Image gerarQRCodeCaixaTestemunho(CaixaTestemunho caixa) throws Exception {
        System.out.println("entrou no gerador de qrcode");
        String details = "Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";"+caixa.getDetalhes()+";Caixa:"+caixa.getCaixa()+";Topo:"+caixa.getTopo()+";Base:"+caixa.getBase()+";Endereco:"+caixa.getEndereco();
        byte[] byteArrayQRCode = QRCode.from(details).to(ImageType.JPG).stream().toByteArray();
        ByteArrayInputStream inputByteArrayQRCode = new ByteArrayInputStream(byteArrayQRCode);
        Image imgqrcode = new Image(inputByteArrayQRCode);
        System.out.println("qrcode de testemunho gerado");
        return imgqrcode;
    }
    public static Image gerarQRCodeCaixaCalha(CaixaCalha caixa)  throws Exception {
        System.out.println("entrou no gerador de qrcode");
        String details = "Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Caixa:"+caixa.getCaixa()+";Topo:"+caixa.getTopo()+";base:"+caixa.getBase()+";Endereco:"+caixa.getEndereco();
        byte[] byteArrayQRCode = QRCode.from(details).to(ImageType.JPG).stream().toByteArray();
        ByteArrayInputStream inputByteArrayQRCode = new ByteArrayInputStream(byteArrayQRCode);
        Image imgqrcode = new Image(inputByteArrayQRCode);
        System.out.println("qrcode de Calha gerado");
        return imgqrcode;
    }
    public static Image gerarQRCodeCaixaPlug(CaixaPlug caixa)  throws Exception {
        System.out.println("entrou no gerador de qrcode");
        String details = "Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Caixa:"+caixa.getCaixa()+";Topo:"+caixa.getTopo()+";base:"+caixa.getBase()+";Endereco:"+caixa.getEndereco();
        byte[] byteArrayQRCode = QRCode.from(details).to(ImageType.JPG).stream().toByteArray();
        ByteArrayInputStream inputByteArrayQRCode = new ByteArrayInputStream(byteArrayQRCode);
        Image imgqrcode = new Image(inputByteArrayQRCode);
        System.out.println("qrcode de Plug gerado");
        return imgqrcode;
    }
    public static Image gerarQRCodeCaixaAparaPlug(CaixaAparaPlug caixa)  throws Exception {
        System.out.println("entrou no gerador de qrcode");
        String details = "Poco:"+caixa.getPoco().getNomePoco()+";TipoAmostra:"+caixa.getTipoamostra()+";Caixa:"+caixa.getCaixa()+";Topo:"+caixa.getTopo()+";base:"+caixa.getBase()+";Endereco:"+caixa.getEndereco();
        byte[] byteArrayQRCode = QRCode.from(details).to(ImageType.JPG).stream().toByteArray();
        ByteArrayInputStream inputByteArrayQRCode = new ByteArrayInputStream(byteArrayQRCode);
        Image imgqrcode = new Image(inputByteArrayQRCode);
        System.out.println("qrcode de Apara de plug gerado");
        return imgqrcode;
    }
}
