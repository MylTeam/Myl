package com.myl.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.myl.modelo.Carta;
import com.myl.negocio.CartaNegocio;

public class Spoiler {

	static CartaNegocio cartaNegocio;
		
		public static void clean() throws IOException {
				
				
			try {			
				
				File f=new File("C:/Users/Mariana/Documents/prueba/");
				
				for(File file:f.listFiles()){
					if(file.isFile()){
					String filename=file.getName();
					System.out.println(filename);
					
					
				 BufferedReader in4 = new BufferedReader(new FileReader("C:/Users/Mariana/Documents/prueba/"+filename));			 
				 File archivo=new File("C:/Users/Mariana/Documents/prueba/s-"+filename+".txt");			 
				 
			      PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Mariana/Documents/prueba/spoilers/s-"+filename)));
			      int lineCount = 1;
			      String s = null;
			      while((s = in4.readLine()) != null ){ 
//			    	  System.out.println(s);
			    	  
			    	  if(s.contains("Who has this card?") || s.contains("click to enlarge") || s.contains("top upward arrow") || s.contains("search for a card") || s.contains("affiliates") || s.contains("Total time spent") || s.contains("TradeCardsOnline") || s.contains("The information presented") || s.contains("Close Ad") || s.contains("your messages")){
			    		  s="";
			    	  }else if(s.contains("Who wants this card?")){
			    		  s=s.replace("Who wants this card? 	", "	Nombre: ");		    		  
			    		  s=s.substring(0, s.length()-1);		    		  
			    		  
			    	  }else if(!s.contains("Nombre:") && !s.contains("N° de coleccionista:") && !s.contains("Tipo de Carta:") && !s.contains("Raza:") && !s.contains("Coste:") && !s.contains("Fuerza:") && !s.contains("Ilustrador:") && !s.contains("Frecuencia:") &&  !s.isEmpty() && s.trim().length()>0){
			    		  String aux=s;
			    		  in4.readLine();
			    		  s=in4.readLine();
			    		  
			    		  if(!s.contains("N° de coleccionista:") && !s.isEmpty()){
			    			  out1.println("Efecto: "+aux);
			    			  s="Leyenda: "+s;
			    		  }else{
			    			  out1.println("Efecto: -");
			    			  out1.println("Leyenda: "+aux);
			    			  s=s.replace("N° de coleccionista:", "No de coleccionista:");
			    		  }
			    	  }else if(s.contains("N° de coleccionista:")){
			    		  s=s.replace("N° de coleccionista:", "No de coleccionista:");
			    	  }else if(s.contains("Tipo de Carta:") && !s.isEmpty()){
			    		  String aux=s;
			    		  s=in4.readLine();
			    		  	if(!s.contains("Raza:")){
			    		  		out1.println(aux);
			    		  		out1.println("Raza: Sin raza");
			    		  	}else{
			    		  		out1.println(aux.trim());
			    		  	}
			    	  }else if(s.contains("Coste:") && !s.isEmpty()){
			    		  String aux=s;
			    		  s=in4.readLine();
//			    		  System.out.println(s);
			    		  	if(!s.contains("Fuerza:")){
			    		  		out1.println(aux);
			    		  		out1.println("Fuerza: 0");
			    		  	}else{
			    		  		out1.println(aux.trim());
			    		  	}
			    		  		
			    	  }
			    	  
			    	 if(s.length()!=0){
			    		 out1.println(s.trim());
			    	 }
			      }		      
			      out1.close();
				}
					}
			    } catch(EOFException e) {
			      System.err.println("End of stream");
			    }
			

		}
		
		public static void loadData(CartaNegocio cartaNegocio){
//			File f = new File("C:/Users/Mariana/Documents/prueba/spoilers/");			
			File f = new File("C:/Users/cdt/Documents/myl-big/spoilers/");
			
			Carta carta;

			for (File file : f.listFiles()) {
				if (file.isFile()) {
					String filename = file.getName();

					try {
						BufferedReader in = new BufferedReader(new FileReader(
								"C:/Users/cdt/Documents/myl-big/spoilers/"
										+ filename));

						String str = filename.replaceAll("\\D+", "");
						Integer noEdicion=Integer.valueOf(str);
						System.out.println(noEdicion);

						String s = null;
						while ((s = in.readLine()) != null) {
							
							if (!s.isEmpty() && !s.contains("Ilustrador:")) {
								carta = new Carta();
								
								// System.out.println(cad);
								for (int i = 0; i < 9; i++) {
									String cad = s.substring(s.indexOf(":") + 2);
									cad=new String(cad.getBytes("UTF-8"));
									System.out.println(cad);
									
									
									if (s.contains("Nombre:")) {
										carta.setNombre(cad);
									} else if (s.contains("Efecto:")) {
										carta.setEfecto(cad);
									} else if (s.contains("Leyenda:")) {
										carta.setLeyenda(cad);
									} else if (s.contains("No de coleccionista:")) {									
										carta.setNumero(cad);
									} else if (s.contains("Tipo de Carta:")) {
										carta.setTipo(cad);
									} else if (s.contains("Raza:")) {
										carta.setRaza(cad);
									} else if (s.contains("Frecuencia:")) {
										carta.setFrecuencia(cad);
									} else if (s.contains("Coste:")) {
										carta.setCoste(Integer.valueOf(cad));
									} else if (s.contains("Fuerza:")) {
										carta.setFuerza(Integer.valueOf(cad));									
									}
									
									if(!s.contains("Fuerza:")){
										s=in.readLine();
									}
								}
								System.out.println("------------------Edición "+noEdicion+" ---------------");
								carta.setIdEdicion(noEdicion);
								cartaNegocio.save(carta);
							}
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

	

}
