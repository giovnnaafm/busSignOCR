import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class BorderFilters{
    static BufferedImage img;

    public BorderFilters(BufferedImage image){
        this.img = image;
    }

    public BufferedImage ProcessaImagem () {
        BufferedImage ima_out  = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Raster raster = img.getRaster();
        WritableRaster wraster = ima_out.getRaster();
    
  
    
            double valornr, valorng, valornb;
            int[] v = new int[9];
    
            for(int y=1; y<img.getHeight()-1; y++)
                for(int x=1; x<img.getWidth()-1; x++){
    
    //            Aplica Filtro de Mediana 3x3
    
                    LeJanela3x3(raster,v,x,y,0);
                    valornr = CalcMediana(9,v);
    
                    LeJanela3x3(raster,v,x,y,1);
                    valorng = CalcMediana(9,v);
    
                    LeJanela3x3(raster,v,x,y,2);
                    valornb = CalcMediana(9,v);
    
                    wraster.setSample(x,y,0,(int)(valornr+.5));
                    wraster.setSample(x,y,1,(int)(valorng+.5));
                    wraster.setSample(x,y,2,(int)(valornb+.5));
    
                }
    
            return ima_out;
        };
    
        public static void LeJanela3x3(Raster raster, int []v, int x, int y, int banda){
                    v[0] = raster.getSample(x-1,y-1,banda);
                    v[1] = raster.getSample(x  ,y-1,banda);
                    v[2] = raster.getSample(x+1,y-1,banda);
                    v[3] = raster.getSample(x-1,y  ,banda);
                    v[4] = raster.getSample(x  ,y  ,banda);
                    v[5] = raster.getSample(x+1,y  ,banda);
                    v[6] = raster.getSample(x-1,y+1,banda);
                    v[7] = raster.getSample(x  ,y+1,banda);
                    v[8] = raster.getSample(x+1,y+1,banda);
    
                    return;
        }
    
    
      public static double CalcMediana(int npts, int []v){
    
        int aux;
    
          for(int i=0; i<npts-1; i++)
              for(int j=i+1; j<npts; j++)
                if(v[i] > v[j]){
                        aux = v[i]; v[i]=v[j]; v[j]=aux;
                }
    
          if((npts%2)==0)
              return((double)v[npts/2]);
          else
                return((double)((v[npts/2]+v[npts/2+1])/2.));
      }
}