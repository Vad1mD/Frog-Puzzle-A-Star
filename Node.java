
class Node implements Comparable<Node> {
    char [] state;
    int hueristic;
    int total_cost;
    int individual_cost;
    Node parent = null;
    String switch_statement;

    Node(String state,int hueristic){
        this.state = state.toCharArray();
        total_cost = 0;
        individual_cost=0;
        this.hueristic = hueristic;
    }

    Node(char [] state, int cost, int in_cost, int hue, Node parent){
        this.state = state;
        this.total_cost = cost;
        this.hueristic = hue;
        this.parent = parent;
        this.individual_cost = in_cost;
    }

    // for printing purposes
    void set_statement(char c, int i,int j){
        this.switch_statement = "Switching "+ c +" in index " + i + " and blank in index "+ j;
    }

    // comparator overload to compare the objects in the pq by the function value
    public int compareTo(Node node){
        if(this.total_cost + this.hueristic < node.hueristic + node.total_cost)
            return 1;
        else if(this.total_cost + this.hueristic > node.hueristic + node.total_cost)
            return -1;
        else
            return 0;
    }

    // toString to print the object
    public String toString(){
        String s = String.valueOf(this.state);
        return  s +
                "\n" +this.switch_statement +
                "\n" +"Cost of this action:" + individual_cost +
                "\nTotal cost so far: "+total_cost+"\n";
    }

}
