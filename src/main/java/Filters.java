import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Filters extends Helpers{
    BufferedImage img;
    public Filters(BufferedImage img){
        super(img);
        this.img = img;
    }
    public void grayScale(){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[] rgb = getRBG(i,j);
                int avg = (rgb[0] + rgb[1] + rgb[2])/3;
                Color scale = new Color(avg, avg, avg);
                img.setRGB(i,j,scale.getRGB());
            }
        }
    }

    public void grayBand(String band) {
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[] rgb = getRBG(i,j);
                Color cor =  new Color(rgb[0], rgb[1], rgb[2]);
                int value = 0;
                switch (band){
                    case "red":
                        value = cor.getRed();
                        break;
                    case "green":
                        value = cor.getGreen();
                        break;
                    case "blue":
                        value = cor.getBlue();
                        break;
                }

                Color newCor = new Color(value, value, value);
                img.setRGB(i,j,newCor.getRGB());
            }
        }
    }

    public void limiar(int limiar){
        int width = img.getWidth();
        int height = img.getHeight();
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int[] rgb = getRBG(i,j);
                int avg = (rgb[0] + rgb[1] + rgb[2]) / 3;
                img.setRGB(i, j, new Color(avg, avg, avg).getRGB());
                if (avg > limiar) {
                    img.setRGB(i, j, new Color(255, 255, 255).getRGB());
                } else {
                    img.setRGB(i, j, new Color(0, 0, 0).getRGB());
                }
            }
        }
    }

    public void negative(){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[] rgb = getRBG(i,j);
                Color scale = new Color(255 - rgb[0], 255 - rgb[1], 255 - rgb[2]);
                img.setRGB(i,j,scale.getRGB());
            }
        }
    }

    public void alterTone(String band, int value){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[] rgb = getRBG(i,j);
                switch (band){
                    case "red":
                        rgb[0] = rgb[0] + value;
                        if(rgb[0] > 255) {
                            rgb[0] = 255;
                        }
                        if(rgb[0] < 0) {
                            rgb[0] = 0;
                        }
                        break;
                    case "green":
                        rgb[1] = rgb[1] + value;
                        if(rgb[1] > 255) {
                            rgb[1] = 255;
                        }
                        if(rgb[1] < 0) {
                            rgb[1] = 0;
                        }
                        break;
                    case "blue":
                        rgb[2] = rgb[2] + value;
                        if(rgb[2] > 255) {
                            rgb[2] = 255;
                        }
                        if(rgb[2] < 0) {
                            rgb[2] = 0;
                        }
                        break;
                }
                Color finalColor = new Color( rgb[0], rgb[1], rgb[2]);
                img.setRGB(i,j,finalColor.getRGB());
            }
        }
    }

    public void bandR(){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int pixel = img.getRGB(i,j);
                int pixelRedColor = new Color(pixel).getRed();
                Color scale = new Color(pixelRedColor, 0, 0);
                img.setRGB(i,j,scale.getRGB());
            }
        }
    }

    public void bandG(){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int pixel = img.getRGB(i,j);
                int pixelGreenColor = new Color(pixel).getGreen();
                Color scale = new Color(0, pixelGreenColor, 0);
                img.setRGB(i,j,scale.getRGB());
            }
        }
    }

    public void bandB(){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int pixel = img.getRGB(i,j);
                int pixelBlueColor = new Color(pixel).getBlue();
                Color scale = new Color(0, 0, pixelBlueColor);
                img.setRGB(i,j,scale.getRGB());
            }
        }
    }

    public void brilhoAditivo(int value){
        int width = img.getWidth();
        int height = img.getHeight();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[]rgb = getRBG(i,j);
                Color scale = new Color(checkColorLimit(rgb[0] + value), checkColorLimit(rgb[1] + value), checkColorLimit(rgb[2] + value));
                img.setRGB(i,j,scale.getRGB());
            }
        }
    }
    public void brilhoMultiplicativo(double value){
        int width = img.getWidth();
        int height = img.getHeight();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[]rgb = getRBG(i,j);
                Color scale = new Color(checkColorLimit((int)(rgb[0] *  value)), checkColorLimit((int)(rgb[1] * value)), checkColorLimit((int)(rgb[2] * value)));
                img.setRGB(i,j,scale.getRGB());
            }
        }
    }

    public void rgbToYiq(){
        int width = img.getWidth();
        int height = img.getHeight();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[] rgb = getRBG(i,j);
                int yColor = (int)((rgb[0] * 0.299) + (rgb[1] * 0.587) + (rgb[2] * 0.114));
                int iColor = (int)((rgb[0] *  0.596) - (rgb[1] *  0.274) - (rgb[2] * 0.322));
                int qColor = (int)((rgb[0] * 0.211) - (rgb[1] *  0.523) + (rgb[2] * 0.312));
                Color color = new Color(checkColorLimit(yColor), checkColorLimit(iColor), checkColorLimit(qColor));
                img.setRGB(i,j,color.getRGB());
            }
        }
    }

    public void yiqToRgb(){
        int width = img.getWidth();
        int height = img.getHeight();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                int[] yiq = getRBG(i,j);
                int rColor = (int)((yiq[0] * 1) + (yiq[1] * 0.956) + (yiq[2] * 0.621));
                int gColor = (int)((yiq[0] *  1) - (yiq[1] * 0.272) - (yiq[2] * 0.647));
                int bColor = (int)((yiq[0] * 1) - (yiq[1] * 1.106) + (yiq[2] * 1.703));
                Color color = new Color(checkColorLimit(rColor), checkColorLimit(gColor), checkColorLimit(bColor));
                img.setRGB(i,j,color.getRGB());
            }
        }
    }


    public static BufferedImage media(BufferedImage imagemEntrada) {
        BufferedImage imagemSaida = imagemEntrada.getSubimage(0, 0, imagemEntrada.getWidth(), imagemEntrada.getHeight());
        int[] valoresVizinhanca = new int[9];

        for(int i = 1; i < imagemEntrada.getWidth() - 1; i++){
            for(int j = 1; j< imagemEntrada.getHeight();j++){
                int aux = -1;
                for(int x = j-1;x < j + 1; x++){
                    for(int y = i - 1; y < i + 1; y++){
                        valoresVizinhanca[++aux] = new Color(imagemEntrada.getRGB(x,y)).getRed();
                    }
                }
                int media = Arrays.stream(valoresVizinhanca).sum() / 9;
                int[] arrayOrdenado = Arrays.stream(valoresVizinhanca).sorted().toArray();
                int mediana = arrayOrdenado[arrayOrdenado.length/2];
                Color novaCor = new Color(media, media, media);
                imagemSaida.setRGB(j,i,novaCor.getRGB());
            }
        }
        return imagemSaida;
    }

    public BufferedImage applyConvolutionFilter() {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        double[][] kernel = {
            { 0, -1, 0 },
            {-1,  5,-1 },
            { 0, -1, 0 }
        };

        int kernelSize = kernel.length;
        int offset = kernelSize / 2;

        for (int y = offset; y < height - offset; y++) {
            for (int x = offset; x < width - offset; x++) {
                double redSum = 0;
                double greenSum = 0;
                double blueSum = 0;

                for (int j = 0; j < kernelSize; j++) {
                    for (int i = 0; i < kernelSize; i++) {
                        Color pixelColor = new Color(img.getRGB(x + i - offset, y + j - offset));
                        double kernelValue = kernel[j][i];

                        redSum += pixelColor.getRed() * kernelValue;
                        greenSum += pixelColor.getGreen() * kernelValue;
                        blueSum += pixelColor.getBlue() * kernelValue;
                    }
                }

                int red = Math.min(Math.max((int) redSum, 0), 255);
                int green = Math.min(Math.max((int) greenSum, 0), 255);
                int blue = Math.min(Math.max((int) blueSum, 0), 255);

                int filteredRGB = new Color(red, green, blue).getRGB();
                filteredImage.setRGB(x, y, filteredRGB);
            }
        }
        return filteredImage;
    }
}


