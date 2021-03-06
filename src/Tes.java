import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;

import javax.imageio.*;

public class Tes {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		init();
		readArff("nome_do_arquivo.arff");
	}
	
	private static BufferedImage img;

    public static void init() {
    	
    	BufferedImage bImage = null;

        try {
        	int type;
        	
            ArrayList<String> array = new ArrayList<String>();
            
            System.out.println(1);
            
            for (int i = 1; i < 3; i++){
            	File initialImage = new File("Digitos/digitos_" + i + ".jpg");
                bImage = ImageIO.read(initialImage);
                int transparencia = bImage.getTransparency();
                type = bImage.getType();
                int width = bImage.getWidth();
                int height = bImage.getHeight();
                int hasCode = bImage.hashCode();
                System.out.println(100);
                array.add(transparencia + "," + type + "," + width + "," + height + "," + hasCode + "," + "Digitos");
                
                
            }
            
            FileWriter fw = new FileWriter("nome_do_arquivo.arff");
            
            BufferedWriter bw = new BufferedWriter(fw);
             
            bw.write(criaArquivoArff(array));
             
            bw.newLine();
             
            bw.close();
            fw.close();

            
        } catch (IOException e) {
        	System.out.println("erro");
        }
    }
    
    public static void readArff(String address) throws Exception{
    	DataSource source = new DataSource(address);
    	 Instances data = source.getDataSet();
    	 // setting class attribute if the data format does not provide this information
    	 // For example, the XRFF format saves the class attribute information as well
    	 System.out.println(data.classIndex());
    	 System.out.println(data.numAttributes());
    	 if (data.classIndex() == -1)
    	   data.setClassIndex(data.numAttributes() - 1);
    	
    }
    
    public static String criaArquivoArff(ArrayList<String> array){
    	String texto = "@Relation Treinamento\n@Attribute transparencia NUMERIC\n@Attribute tipo NUMERIC\n@Attribute largura NUMERIC\n@Attribute comprimento NUMERIC\n@Attribute hasCode NUMERIC\n@Attribute Classe  {Digitos, Digitos_letras, letras, sem_caracteres}\n";
    	texto = texto + "@DATA" + "\n";
    	
    	for (String string : array){
    		texto = texto + string + "\n";
    	}
    	
    	return texto;
    }

    public void paint(Graphics g) {
      g.drawImage(img, 50, 50, null);
    }
}
