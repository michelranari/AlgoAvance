import java.util.*;
import java.io.*;

class Img {

    private int[][] data;  //data[i][j] représente pixel(i,j) avec i numero colonne et j num ligne avec ligne 0 en haut, et colonne 0 à gauche
    private int hauteur;
    private int largeur;
      

    /**
     * 
     * @param nomFichier : adresse d'un fichier pgm, ex "fichier.pgm"
     */
    public Img(String nomFichier){
        try {
            Pixmap pm = new Pixmap(nomFichier);
            data = new int[pm.getW()][pm.getH()];
            for (int i = 0; i < pm.getW(); i++) {
                for (int j = 0; j < pm.getH(); j++) {
			  
                    data[i][j] = pm.get(i, j);
                }
            }
	    setHL();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Img(int[][] tab){
        data = new int[tab.length][tab[0].length];
        for(int i=0;i<tab.length;i++){
            for(int j=0; j<tab[0].length;j++){
                data[i][j] = tab[i][j];
            }
        }
	setHL();
    }

    private void setHL(){
	hauteur = data[0].length;
	largeur = data.length;
    }

    public int[][] getData(){
        return data;
    }

   public int get(int i, int j){
   	return data[i][j];
   }

    //utilisé dans InstanceDebruitage où le pixel (i,j) d'une Img correspondant au sommet numéro "calculIndice(i,j)" du graphe de l'instanceDebruitage associée à l'Img
    public int calculIndice(int i, int j){
	return largeur*j+i;
    }

    public Couple<Integer,Integer> calculCoord(int x){
	int j = x/largeur;
	int i  = x%largeur;
	Couple<Integer,Integer>  res = new Couple<Integer,Integer>(i,j);
	return res;
    }

    public int getPixel(int i, int j){
        return data[i][j];
    }

    public String toString(){
        String str = "";
        for (int j = 0; j < nbLignes(); j++) {
            for (int i = 0; i < nbColonnes(); i++) {
                str = str + "\t" + data[i][j];
            }
            str = str + "\n";
        }
        return str;
    }

    public int nbColonnes(){
        return largeur; 
    }

    public int nbLignes(){
        return hauteur; 
    }

    public void bruiter(int n){
	for (int x=0;x<n;x++){
		Random r = new Random();
		int i = r.nextInt(data.length);
		int j = r.nextInt(data[0].length);
		data[i][j] = 255-data[i][j];
	}

    }	


    
    /**
     * 
     * @param solution : liste des indices des pixels (le pixel (i,j) à l'indice "calculIndice(i,j)") à mettre en blanc 
     * @return les coordonnées de ces pixels 
     */
    public ArrayList<Couple<Integer,Integer>> calculFiltre(ArrayList<Integer> solutionBlancs)
    {
	ArrayList<Couple<Integer,Integer>> res = new ArrayList<Couple<Integer,Integer>>();
       for (Integer x : solutionBlancs){
            res.add(calculCoord(x));
        }
	return res;
    }



    /**
   param : liste des coordonnées de pixels à mettre en blanc
     */
    public Img appliquerFiltre(ArrayList<Couple<Integer,Integer>> solutionBlancs) {
        Img nouvelleImage = new Img(data);
        for(int i = 0; i<this.nbColonnes();i++){
            for(int j = 0;j<this.nbLignes();j++){
                Couple<Integer,Integer> indice = new Couple<Integer,Integer>(i,j); 
                if(solutionBlancs.contains(indice)){
                    nouvelleImage.data[i][j] = 255;
                }
		else{
		     nouvelleImage.data[i][j] = 0;
		}
            }
        }
        return nouvelleImage;
    }

    /**
     * Convertie l'image en un nouveau ficher pgm
     * @param nomFile : nom du nouveau ficher
     */
    public void creerImage(String nomFile) {
        Pixmap pm = new Pixmap(this.nbColonnes(), this.nbLignes());
        pm.setData();
        for (int i = 0; i < nbColonnes(); i++) {
            for (int j = 0; j < nbLignes(); j++) {
                pm.set(i, j, data[i][j]);
            }
        }
        pm.write(nomFile);
    }
}
