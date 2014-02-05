import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LongestCommonSequence {

    private static Node<Character> buildGraph (String str) {
        if (str.length() == 1) {
            return new Node<Character>(str.charAt(0));
        }

        //create this node
        Node<Character> thisNode = new Node<Character>(str.charAt(0));

        //create node for each character
        List<Node<Character>> nodes = new ArrayList<Node<Character>>();
        thisNode.next = nodes;
        for (int i = 1; i < str.length(); i++) {
            nodes.add(buildGraph(str.substring(i)));
        }

        return thisNode;
    }

    private static Set<String> getAllCombinations (Node<Character> root) {
        Set<String> combs = new HashSet<String>();
        if (root.next == null) {
            combs.add(root.data.toString());
            return combs;
        }

        combs.add(root.data.toString());
        for (int i = 0; i < root.next.size(); i++) {
            combs.add(root.data.toString() + root.next.get(i).data.toString());
            Set<String> combs2 = getAllCombinations(root.next.get(i));
            Iterator<String> combs2i = combs2.iterator();
            while (combs2i.hasNext()) {
                String str = combs2i.next();
                combs.add(root.data.toString() + str);
                combs.add(str);
            }
        }
        return combs;
    }

    public static void main (String[] args) {
        String[] input = new String[2];

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            input = br.readLine().split(";");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Node<Character> n1 = buildGraph(input[0]);
        Node<Character> n2 = buildGraph(input[1]);

        Set<String> set1 = getAllCombinations(n1);
        Set<String> set2 = getAllCombinations(n2);

        set1.retainAll(set2);

        String longestSoFar = "";
        int max = -1;
        for (String s : set1) {
            int l = s.length();
            if(l > max){
                max = l;
                longestSoFar = s;
            }
        }
        System.out.println(longestSoFar);
    }

    private static class Node<T> {
        public T data;
        public List<Node<T>> next;

        public Node (T data) {
            this.data = data;
        }
        public Node (T data, List<Node<T>> next) {
            this.data = data;
            this.next = next;
        }
        @Override
        public String toString() {
            String next = "";
            for (int i = 0; i < next.length(); i++) {
                next += next.toString() + " ";
            }
            return data.toString() + " -> " + next;
        }
    }

}
