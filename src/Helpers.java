import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

public class Helpers {
    BufferedImage img;

    public Helpers(BufferedImage img){
        this.img = img;
    }

    public int[] getRBG(int i, int j){
        var pixel = img.getRGB(i,j);
        int pixelRedColor = new Color(pixel).getRed();
        int pixelGreenColor = new Color(pixel).getGreen();
        int pixelBlueColor = new Color(pixel).getBlue();
        int[] arr = {pixelRedColor, pixelGreenColor, pixelBlueColor};
        return arr;
    }

    public int checkColorLimit(int color){
        if(color > 255){
            return 255;
        } else if (color < 0) {
            return 0;
        }
        return color;
    }

}
