import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

/**
 * Created by arsee on 31.05.2017.
 */
public class Allocation {
    public static String path = "test-public";
    public static int start=5;
    public static  int end =250;
    public static int step=20;
    public static int width =80;
    public static int height =200;
    private boolean bool (int y1,int y2,int j1,int j2){
        //System.out.print(y1+" "+y2+" "+j1+" "+j2+"\n");
        if (Math.abs(y1-j1)<=9 && Math.abs(y2-j2)<=9){
            return  true;
        }else{
            return false;
        }
    }
    private boolean costToCount (int y1,int y2,int j1,int j2){
        if (y2<j1 || y1>j2){
            return true;
        }
        return false;
    }
    public static BufferedImage crop(BufferedImage img,int x1,int y1,int x2,int y2){
        img = img.getSubimage(x1, y1, x2 - x1 , y2 - y1);
        BufferedImage im = new BufferedImage(img.getWidth() * 1, img.getHeight() * 1, 5);
        Graphics graphics = im.getGraphics();
        graphics.drawImage(img, 0, 0, img.getWidth() * 1, img.getHeight() * 1, null);
        img = im;
        img.flush();
        return img;
    }

    Allocation ()throws Exception{
        Scanner sc = new Scanner(new File(path+"/train-processed.idl"));
        int i=1;
        int i1=0;
        int im1=0;
        int x1,x2,y1,y2,n;

        while (sc.hasNextInt()){

            n=sc.nextInt();
            y1=sc.nextInt();
            x1=sc.nextInt();
            y2=sc.nextInt();
            x2=sc.nextInt();
          int f=i;
          for (;i<=n;i++){

              System.out.print(i+" "+n+"|");

              BufferedImage img = ImageIO.read(new File(path + "/"+i+".png"));
               for (int j=0;j<img.getWidth()-width-step;j+=step){

                   BufferedImage image =crop(img,j,0,j+width,height);

                    int ff=1;
                    int oi;
                    if (!bool(x1,x2,j,j+width)){
                        ff=-1;
                        im1++;
                        oi=im1;
                    }else{
                        ff=1;
                        i1++;
                        oi=i1;
                    }
                    if (ff==-1) {
                        if (costToCount(x1,x2,j,j+width)) {
                            ImageIO.write(image, "png", new File(path + "/" + ff + "/" + oi + ".png"));
                        }else{
                            im1--;
                        }
                    }else{
                        ImageIO.write(image, "png", new File(path + "/" + ff + "/" + oi + ".png"));
                    }
                 }
              System.out.print(i+" "+n+"\n");
            }



        }
        sc.close();
         sc=new Scanner(new File(path+"/train-processed.idl"));
        while (sc.hasNextInt()){
            n=sc.nextInt();
            y1=sc.nextInt();
            x1=sc.nextInt();
            y2=sc.nextInt();
            x2=sc.nextInt();
            BufferedImage img = ImageIO.read(new File(path + "/"+n+".png"));
            BufferedImage image =crop(img,x1,y1,x2,y2);
            i1++;
            ImageIO.write(image, "png", new File(path + "/" + 1+ "/" + i1 + ".png"));
        }
    }
}
