import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.image.BufferedImage;


public class Ocr {
    String busSignText;
    BufferedImage image;
    public Ocr(BufferedImage img){
        this.image = img;
    }

    public String getBusSignText(){
        try{
            Tesseract tesseract = new Tesseract();
            tesseract.setLanguage("por");
            String ocr = tesseract.doOCR(image);

            return ocr;
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }


}
