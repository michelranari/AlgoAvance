
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class MainGraph {


    public static void main(String args[]) throws FileNotFoundException, IllegalArgumentException, IOException {		
    	int[][] data = {{0,255,0,0},{255,0,255,255}};

    	Img img = new Img(data);
    	img.creerImage("example");

    	InstanceDebruitage instdeb = new InstanceDebruitage(img);
    	instdeb.setAlpha(7);
    	System.out.println(instdeb);

    	Reseau r = new Reseau(instdeb);
    	System.out.println("Reseau:");
    	System.out.println(r);

    	arcForReseau double[4][4]= {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}

    	Reseau r1 = new Reseau();

   }
}
