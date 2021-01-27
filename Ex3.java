/*
*
* Author : Vadim Dolguintsev
*/

import java.util.*;

public class Ex3 {

    public static void main(String []args){

        System.out.println("Please enter initial state of the puzzle where plack pieces are on the left and white on the right");
        Scanner sc = new Scanner(System.in);
        String initialState = sc.nextLine();

        AStarSearch(initialState);
    }


    public static void AStarSearch(String initialState){

        Node root = new Node(initialState, hueCalc(initialState.toCharArray()));

        // override compare method
        PriorityQueue<Node> list = Node.getPriorityQueue();

        list.add(root);

        boolean foundPath = false;

        // As long as the goal condition isn't met, poll the best option node from the pq and expand it
        // Stop when a path for solution is found
        while(!list.isEmpty() && !foundPath){
            if(list.peek().getHeuristic() == 0) {
                foundPath = true;
            }
            else{
                findAll(list);
            }
        }
        if(list.isEmpty()) {
            System.out.println("No solution");
            System.exit(0);
        }
        pathReconstruction(list.poll());
    }


    // Node expansion function
    public static void findAll(PriorityQueue<Node> list)
    {
        Node current = list.poll();

        char []  temp;

        for (int i = 0; i < 7; i++)
        {
            if (i < 6)
                if (current.getState()[i] == 'B' && current.getState()[i + 1] == '0') {

                    temp = swap(current.getState().clone(), i, i + 1);

                    Node node = new Node(temp, current.getTotalCost() + 1,1, hueCalc(temp),current);
                    node.setStatement('B',i,i+1);

                    list.add(node);
                }

            if (i < 5)
                if (current.getState()[i] == 'B' && current.getState()[i + 2] == '0') {

                    temp = swap(current.getState().clone(), i, i + 2);

                    Node node = new Node(temp, current.getTotalCost() + 1,1, hueCalc(temp),current);
                    node.setStatement('B',i,i+2);

                    list.add(node);
                }
            if(i < 4)
                if (current.getState()[i] == 'B' && current.getState()[i + 3] == '0'){

                    temp = swap(current.getState().clone(), i, i + 3);

                    Node node = new Node(temp, current.getTotalCost() + 2,2, hueCalc(temp),current);
                    node.setStatement('B',i,i+3);

                    list.add(node);
                }

            if (i > 0)
                if (current.getState()[i] == 'W' && current.getState()[i - 1] == '0') {

                    temp = swap(current.getState().clone(), i, i - 1);
                    Node node = new Node(temp, current.getTotalCost() + 1,1, hueCalc(temp),current);
                    node.setStatement('W',i,i-1);

                    list.add(node);
                }

            if (i > 1)
                if (current.getState()[i] == 'W' && current.getState()[i - 2] == '0'){
                    temp = swap(current.getState().clone(), i, i - 2);

                    Node node = new Node(temp, current.getTotalCost() + 1,1, hueCalc(temp), current);
                    node.setStatement('W',i,i-2);

                    list.add(node);
                }

            if (i > 2){
                if (current.getState()[i] == 'W' && current.getState()[i - 3] == '0'){
                    temp = swap(current.getState().clone(), i, i - 3);

                    Node node = new Node(temp, current.getTotalCost() + 2,2, hueCalc(temp), current);
                    node.setStatement('W',i,i-3);

                    list.add(node);
                }
            }

        }

    }

    public static char [] swap (char [] temp_state, int i, int j){
        char temp = temp_state[i];
        temp_state[i] = temp_state[j];
        temp_state[j] = temp;
        return temp_state;
    }

    public static int hueCalc(char [] current){
        int h = 3;
        int i = 0;
        while(i<7 && current[i]!='B'){
            if (current[i]=='W')
                h--;
            i++;
        }
        return h;
    }

    public static void pathReconstruction(Node node){
        List<Node> path = new ArrayList<>();
        while(node!=null){
            path.add(node);
            node = node.getParent();
        }

        for(int i = path.size()-1;i>=0;i--){
            System.out.println(path.get(i).toString());
        }
        System.out.printf("Solution found, lowest possible cost: %d",path.get(0).getTotalCost());
    }
}

