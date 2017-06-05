import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class Allocation{
    private String path = "C://Users//arsee//Desktop//lp-left";
    public  Allocation() throws Exception{
        System.out.print("k");
        Scanner sc =new Scanner(new File(path+"/data.txt"));
        System.out.print("k");
        while (sc.hasNextLine()){
            String s =sc.next();
            s = s.replace('"',' ').replace(" ","").replace(":"," ");
            System.out.print(s+"|");

        }
    }
}