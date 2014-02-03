import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LongestCommonSequence {

    private static String calcLongestContinousSequence (String str1, String str2) {
        int size = 1;
        int start = 0;
        String longest = "";
        while (size != str1.length()) {
            System.out.printf("size: %d\tstart: %d\n", size, start);
            String str = str1.substring(start, start+size);
            if (str2.contains(str)) {
                size++;
                start = 0;
                longest = str;
                continue;
            }

            start++;

            if (start + size > str1.length()) {
                size++;
                start = 0;
                continue;
            }
        }

        return longest;
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

        if (input.length != 2 || input[0].length() == 0 || input[1].length() == 0) {
            System.out.printf("Input is invalid, array size = %d, length of str1 = %d, str2 = %d", input.length, input[0].length(), input[1].length());
            System.exit(1);
        }

        //get longest common sequence
        String longest = "";
        if (input[0].length() < input[1].length())
            longest = calcLongestContinousSequence(input[0], input[1]);
        else 
            longest = calcLongestContinousSequence(input[1], input[0]);

        System.out.println(longest);
    }

}
