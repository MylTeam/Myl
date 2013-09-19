import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
/*
 * @author mkyong
 *
 */
public class ImageTest {
 
	private static final int IMG_WIDTH = 50;
	private static final int IMG_HEIGHT = 70;
 
	public static void main(String [] args){
 
	try{
 
		String ruta="C:/Users/cdt/Documents/mylimagestest/";
		File f=new File(ruta+"images/");
				
		for(File file:f.listFiles()){
			if(file.isFile()){
				
			}else if(file.isDirectory()){
				File c1=new File(ruta+"thumbs1/"+file.getName());
				File c2=new File(ruta+"thumbs2/"+file.getName());
				File c3=new File(ruta+"thumbs3/"+file.getName());
				
				if(!c1.exists()){
					c1.mkdirs();
				}
				if(!c2.exists()){
					c2.mkdirs();
				}
				if(!c3.exists()){
					c3.mkdirs();
				}
				
				for(File img:file.listFiles()){
					if(img.isFile()){
						
						BufferedImage originalImage = ImageIO.read(img);
						int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				 
//						BufferedImage resizeImageJpg = resizeImage(originalImage, type);
//						ImageIO.write(resizeImageJpg, "jpg", new File(ruta+"thumbs1/"+file.getName()+"/"+img.getName()));  
				 
						BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
						ImageIO.write(resizeImageHintJpg, "jpg", new File(ruta+"thumbs2/"+file.getName()+"/"+img.getName()));

//						BufferedImage resizeImagePng = resizeImage(originalImage, type);
//						ImageIO.write(resizeImagePng, "png", new File(ruta+"thumbs3/"+file.getName()+"/"+img.getName()));
					}
				}
			}
			
		}
		
		 
 		
 
	}catch(IOException e){
		System.out.println(e.getMessage());
	}
 
    }
 
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
 
	return resizedImage;
    }
 
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
 
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
		
	g.setComposite(AlphaComposite.Src);
 
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
 
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
	return resizedImage;
    }	
}