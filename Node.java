import java.util.PriorityQueue;

class Node {

    private char [] state;
    private int heuristic;
    private int totalCost;
    private int individualCost;
    private Node parent = null;
    private String switchStatement;

    public char[] getState() {
        return state;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public Node getParent() {
        return parent;
    }

    Node(String state,int heuristic){
        this.state = state.toCharArray();
        this.totalCost = 0;
        this.individualCost =0;
        this.heuristic = heuristic;
    }

    Node(char [] state, int cost, int inCost, int hue, Node parent){
        this.state = state;
        this.totalCost = cost;
        this.heuristic = hue;
        this.parent = parent;
        this.individualCost = inCost;
    }

    // for printing purposes
    public void setStatement(char c, int i, int j){
        this.switchStatement = "Switching "+ c +" in index " + i + " and blank in index "+ j;
    }

    public String toString(){
        String s = String.valueOf(this.state);
        return  s +
                "\n" +this.switchStatement +
                "\n" +"Cost of this action:" + individualCost +
                "\nTotal cost so far: "+ totalCost +"\n";
    }

    public static PriorityQueue<Node> getPriorityQueue(){
        return new PriorityQueue<>(20, (n1, n2) -> {
            if (n1.getTotalCost() + n1.getHeuristic() > n2.getTotalCost() + n2.getHeuristic()) {
                return 1;
            }
            else if (n1.getTotalCost() + n1.getHeuristic() < n2.getTotalCost() + n2.getHeuristic()) {
                return -1;
            }
            else {
                return 0;
            }
        });
    }

}
