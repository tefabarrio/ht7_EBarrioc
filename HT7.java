
    


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase principal donde se realizan las acciones y operaciones del trabajo.
 * @author Estefania Barrio Carné 17927
 */
public class HT7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        int decision = 0; 
        
        BinaryNode<String> nod = null;
        BinaryTree2<String> tree = new BinaryTree2<String>(nod);
        
        Scanner teclado = new Scanner(System.in);
        Scanner teclado2 = new Scanner(System.in);
        
        
            System.out.println("Ingrese el nombre del archivo.txt que quiere leer para su diccionario (Ej. diccionario.txt): ");
            String file = teclado.nextLine();
            
            BufferedReader br = new BufferedReader(new FileReader(file));     

            try {                
                StringBuilder sb = new StringBuilder();
                String line = "";              
                                                
                String ingles = "";
                String espanol = "";
                
                //Ciclo principal en donde se lee cada linea del .txt donde estan las asociaciones de ingles y espanol
                while ((line=br.readLine())!=null) {
                    sb.append(line);
                    sb.append(System.lineSeparator()); 
                    line = line + " "; //Concatenado para que el ultimo valor sea leido sin problemas
                                       //por substring
                    for(int i=1;i<line.length();i++){
                        String iter = line.substring((i-1), i); 
                        if(iter.equals(",")){
                            espanol = line.substring(i, line.length()).toUpperCase(); //se obtiene la subcadena luego de "|"
                            ingles = line.substring(0, i-1).toUpperCase(); //se obtiene la subcadena antes de "|"       
                        }
                    }
                    
                    BinaryNode<String> node = new BinaryNode<String>(ingles,espanol); //se crea un nuevo nodo con cada linea
                    tree.addNodo(node); //el nodo se agrega al arbol
                    
                }
                System.out.println("Que desea hacer? \n1. Traducir archivo de texto \n2. Mostrar recorrido IN-ORDER del arbol \n3. Salir ");
                decision = teclado.nextInt();               
                while(decision != 3){
                    if(decision == 1){
                        String traduccion = "";
                        String oracion = "";
                        System.out.println("Ingrese el nombre del archivo a traducir(Ej. oraciones.txt): ");
                        String texto = teclado2.nextLine();
                        File archivo = new File (texto);

                        FileReader fr = new FileReader (archivo);
                        BufferedReader br1 = new BufferedReader(fr);
                        String linea = "";
                        Scanner scanner = new Scanner(fr);
                        String palabra = "";
        
                        while (scanner.hasNextLine()) {
                            linea += scanner.nextLine();
                            palabra = linea.replaceAll("\n", " ");
                        }
                        fr.close();
                        br1.close();
        
                        String palabras[] = palabra.split(" ");
                        for(int i = 0; i < palabras.length;i++){
                            palabra = palabras[i];
                            palabra = palabra.toUpperCase();
                            String prueba = tree.buscarPalabra(palabra);
                            traduccion = traduccion + prueba;
                        }
                        System.out.println(traduccion);
                    }else if(decision == 2){
                        tree.recorrer(BinaryTree2.recorrido.INORDER); //PRUEBA de recorridos
                    }else{
                        System.out.println("No ha ingresado una opcion valida");
                    }    
                System.out.println("Que desea hacer? \n1. Traducir archivo de texto \n2. Mostrar recorrido IN-ORDER del arbol \n3. Salir ");
                decision = teclado.nextInt();
                }
                
            }
            finally{
                br.close();
            }  
        
        
    }
    

    
}
