
import java.util.*;

public class Ex3 {

    public static void main(String []args){

        // Receiving valid possible input from user
        Scanner sc = new Scanner(System.in);
        String initial_state = sc.nextLine();

        // Initializing tree root and the pq
        Node root = new Node(initial_state,hue_calc(initial_state.toCharArray()));
        PriorityQueue <Node> list = new PriorityQueue<>();
        list.add(root);

        // The goal state is when all the W's are on the left of the B's
        // Examples: WWW0BBB, WW0WBBB, WWWB0BB etc.
        // The given heuristic is the number of W's that yet passed to the left of all B's
        boolean found_path = false;

        // As long as the goal condition, poll the best option node from the pq and expand it
        // Stop when a path for solution is found
        while(!list.isEmpty() && found_path!=true){
            if(list.peek().hueristic == 0) {
                found_path = true;
                System.out.println("path found");
            }
            else{
                FindAll(list);
            }
        }
        if(list.isEmpty()) {
            System.out.println("No solution");
            System.exit(0);
        }
        path_reconstruction(list.poll());
    }

    // Node expansion function
    public static void FindAll(PriorityQueue<Node> list)
    {
        Node current = list.poll();

        char []  temp;

        for (int i = 0; i < 7; i++)
        {
            if (i < 6)
                if (current.state[i] == 'B' && current.state[i + 1] == '0') {

                    temp = swap(current.state.clone(), i, i + 1);

                    Node node = new Node(temp, current.total_cost + 1,1, hue_calc(temp),current);
                    node.set_statement('B',i,i+1);

                    list.add(node);
                }

            if (i < 5)
                if (current.state[i] == 'B' && current.state[i + 1] == 'W' && current.state[i + 2] == '0') {

                    temp = swap(current.state.clone(), i, i + 2);

                    Node node = new Node(temp, current.total_cost + 2,2, hue_calc(temp),current);
                    node.set_statement('B',i,i+2);

                    list.add(node);
                }

            if (i > 0)
                if (current.state[i] == 'W' && current.state[i - 1] == '0') {

                    temp = swap(current.state.clone(), i, i - 1);
                    Node node = new Node(temp, current.total_cost + 1,1, hue_calc(temp),current);
                    node.set_statement('W',i,i-1);

                    list.add(node);
                }

            if (i > 1)
                if (current.state[i] == 'W' && current.state[i - 1] == 'B' && current.state[i - 2] == '0') {
                    temp = swap(current.state.clone(), i, i - 2);

                    Node node = new Node(temp, current.total_cost + 2,2, hue_calc(temp), current);
                    node.set_statement('W',i,i-2);

                    list.add(node);
                }

        }

    }

    // char swapping function
    public static char [] swap (char [] temp_state, int i, int j){
        char temp = temp_state[i];
        temp_state[i] = temp_state[j];
        temp_state[j] = temp;
        return temp_state;
    }

    // calculating heuristic
    public static int hue_calc(char [] current){
        int h = 3;
        int i = 0;
        while(i<7 && current[i]!='B'){
            if (current[i]=='W')
                h--;
            i++;
        }
        return h;
    }

    public static void path_reconstruction(Node node){
        Vector<Node> path = new Vector<>();
        while(node!=null){
            path.add(node);
            node = node.parent;
        }

        for(int i = path.size()-1;i>=0;i--){
            System.out.println(path.elementAt(i).toString());
        }
        System.out.printf("Solution found, lowest possible cost: %d",path.elementAt(0).total_cost);
    }
}

