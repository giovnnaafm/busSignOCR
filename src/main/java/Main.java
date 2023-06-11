import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Main {
    public static void main(String[] args) {
        String path = "c:/Users/Windows/Documents/GitHub/bus-sign-ocr/assets/2.jpg";
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
            Filters filters = new Filters(img);
            filters.grayScale();
            filters.limiar(250);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Tesseract tesseract = new Tesseract();
            String busSignText = tesseract.doOCR(img);
            System.out.println(busSignText);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }
}
