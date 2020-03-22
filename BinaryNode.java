/*
 * ejemplo con referencia: http://www.juliocesar.in/2013/09/arbol-binario-y-recorridos-preorden.html
 */

/**
 * Clase que representa el nodo binario de los cuales se conforma el arbol binario.
 * @author Estefania Barrio carne 17927
 */
public class BinaryNode<E>{
    
    //private E valor;
    Association<String,String> value;
    private BinaryNode<E> parent;
    private BinaryNode<E> left;
    private BinaryNode<E> right;  
    
    /**
     * Primer constructor. Únicamente asigna el par asociado sin referencias a otros nodos
     * @param Ing la palabra en ingles
     * @param Esp la palabra en espanol
     */
    public BinaryNode(String Ing,String Esp) {
        value = new Association(Ing,Esp); //valores del nodo asociaados
        parent = null;
        left = null;
        right = null;
        
    }
    
    /**
     * Segundo constructor. asigna el par asociado y el nodo derecho e izquierdo
     * @param Ing la palabra en ingles
     * @param Esp la palabra en espanol
     * @param ni Nodo Izquierdo
     * @param nd Nodo derecho
     */
    public BinaryNode(String Ing, String Esp, BinaryNode<E> ni, BinaryNode<E> nd) {
        value = new Association(Ing,Esp); //valores del nodo asociaados
        if (ni != null) left = ni;
        if (nd != null) right = nd;
    }
    
    /**
     * Tercer constructor, asigna pares asociados al nodo y a sus hijos izquierdo y derecho
     * @param Ing la palabra en ingles  
     * @param Esp la palabra en espanol
     * @param IngLeft la palabra en ingles a la izquierda
     * @param EspLeft la palabra en espanol a la izquierda
     * @param IngRight la palabra en ingles a la derecha
     * @param EspRight la palabra en espanol a la derecha
     */
    public BinaryNode(String Ing, String Esp, String IngLeft,String EspLeft,String IngRight,String EspRight) {
        value = new Association(Ing,Esp); //valores del nodo asociaados
        if (IngLeft!= null && EspLeft != null) {
            left = new BinaryNode<>(IngLeft,EspLeft);
        }
        if (IngRight!= null && EspRight != null) {
            right = new BinaryNode<>(IngRight,EspRight);
        }
    }
    
    
     /**
     * Indica si el nodo tiene hijos
     * @return true o false 
     */
    public boolean hasChildren() {
        return (left != null && right != null);
    }
    
    /**
     * Nodo leaf cuando no tiene hijos
     * @return  true o false 
     */
    public boolean isLeaf() {
        return (left == null && right == null);
    }
    
    /**
     * Devuelve el valor del nodo
     * @return el par asociado del nodo
     */
    public Association getAsociacion() {
        return value;
    }
    
    /**
     * Devuelve la palabra en espanol
     * @return la palabra en espanol
     */
    public String getEspanol() {
       return value.getValue();
    }
    /**
     * Devuelve la palabra en ingles
     * @return la palabra en ingles
     */
    public String getEnglish(){
        return value.getKey();
    }
    /**
     * Metodo que devuelve el nodo izquierdo de un nodo padre
     * @return el nodo derecho
     */
    public BinaryNode getLeft(){
        return left;
    }
    /**
     * Metodo que devuelve el nodo derecho de un nodo padre
     * @return el nodo derecho
     */
    public BinaryNode getRight(){
        return right;
    }
    /**
     * Metodo que le da valor a el nodo izquierdo de un nodo padre
     * @param left el nodo izquierdo
     */
    public void setLeft(BinaryNode left){
        this.left = left;
    }
    /**
     * Metodo que le da valor a el nodo derecho de un nodo padre
     * @param right el nodo derecho
     */
    public void setRight(BinaryNode right){
        this.right=right;
    }
    
    /**
     * Recorrido pre orden NLR
     */
    public void preOrder() {
        System.out.print(getEspanol() + " ");
        if (left != null)
            left.preOrder();
        if (right != null)
            right.preOrder();
    }
    
    /**
     * Recorrido en orden LNR
     */
    public void inOrder() {
        if (this == null)
            return;
        if (left != null)
            if (left.isLeaf())
                System.out.print("("+left.getEnglish() + ", "+ left.getEspanol()+")\n");
            else
                left.inOrder();
        System.out.print("("+getEnglish() + ", "+getEspanol()+")\n");
        if (right != null)
            if (right.isLeaf())
                System.out.print("("+right.getEnglish() + ", "+ right.getEspanol()+")\n");
            else
                right.inOrder();
    }
    
    /**
     * Recorrido post orden LFN
     */
    public void postOrder() {
        if (this == null)
            return;
        if (left != null)
            if (left.isLeaf())
                System.out.print(left.getEspanol() + " ");
            else
                left.postOrder();        
        if (right != null)
            if (right.isLeaf())
                System.out.print(right.getEspanol() + " ");
            else
                right.postOrder();
        System.out.print(getEspanol() + " ");
    }
    /**
     * Busca una palabra en ingles dentro del arbol y devuelve su traduccion si la hay
     * @param value la palabra a buscar
     * @return La traduccion al español de la palabra si existe en el arbol
     */
    public String search(String value){
        if (value.equals(this.value.getKey())){
            
            return this.value.theValue;
        }else if (value.compareTo(this.value.getKey()) < 0) {
            
            if (left == null){
                return "*" + value + "*";
            }else{
                return left.search(value);
            }
        }else if(value.compareTo(this.value.getKey()) > 0) {
            
            if (right == null){
                return "*" + value + "*";
            }else{
                return right.search(value);

            }
        }
        
        return "*" + value + "*";
    }
}

