public class ListOfAcidente {
    private class Node {
        public Integer element;
        public Node next;

        public Node(Integer element) {
            this.element = element;
            next = null;
        }

        public Node(Integer element, Node next) {
            this.element = element;
            this.next = next;
        }
    }


    // Referência para o primeiro elemento da lista encadeada.
    private Node head;
    // Referência para o último elemento da lista encadeada.
    private Node tail;
    // Contador para a quantidade de elementos que a lista contem.
    private int count;


    /**
     * Construtor da lista.
     */
    public ListOfAcidente() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

//  1. Rua/av/trav na qual ocorreram mais acidentes;
    public String logComMaisAcidente(){
        return "";
    }

//  2. Identificar para uma determinada rua/av/trav o dia da semana em que mais ocorreram acidentes;
    public String diaComMaisAcidentesNaRua(String rua){
        return "";
    }

//  3. Apresentar o total de acidentes envolvendo moto;
    public int acidentesComMoto(){
        return 0;
    }

//  4. Permitir navegar pelos acidentes ordenados por rua/av/trav, isto é, avançar e retroceder, apresentando
//  também o total de acidentes desta rua/av/trav.
    
}
