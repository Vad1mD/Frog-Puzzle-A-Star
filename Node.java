// Vadim Dolguintsev 319479531 Omer Sirpad 304803018

class Node {
    char [] state;
    int hueristic;
    int total_cost;
    int individual_cost;
    Node parent = null;
    String switch_statement;

    Node(String state,int hueristic){
        this.state = state.toCharArray();
        this.total_cost = 0;
        this.individual_cost=0;
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

    // toString to print the object
    public String toString(){
        String s = String.valueOf(this.state);
        return  s +
                "\n" +this.switch_statement +
                "\n" +"Cost of this action:" + individual_cost +
                "\nTotal cost so far: "+total_cost+"\n";
    }

}
