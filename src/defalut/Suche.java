package defalut;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Suche {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //String words = br.readLine();


        //String[] wordArr = words.split(" |\\.");
        //int[] numbers = excludeText(wordArr);
        int[] numbers = new int[]{8,6,4,2, 7, 5,3};
        System.out.println(Arrays.toString(numbers));
        bw.write(Integer.toString(selectElementBySize(2,numbers)));
        br.close();
        bw.close();
    }

    private static int[] excludeText(String[] text) {
        Set<Integer> out = new HashSet<>();
        int[] arr;
        for(String word : text) {
            if(word.matches("\\d+")) {
                out.add(Integer.parseInt(word));
            }
        }
        arr = new int[out.size()];
        int i = 0;
        for(Integer number : out) {
            arr[i] = number;
            i++;
        }
        return arr;
    }

    private static int selectElementBySize(int k, int[] numbers) {
        return recSelect(0, numbers.length - 1, k, numbers);
    }

    private static int recSelect(int lo, int up, int k, int[] numbers) {
        int pivot = numbers[up];
        int i = lo;
        int j = up -1;
        while(i < j) {

            while(numbers[i] < pivot) {
                i++;
            }
            while(numbers[j] >= pivot && i < j) {
                j--;
            }
            System.out.println(numbers[i] + " " + numbers[j]);
            if(i<j) {
                swapElements(i, j, numbers);
                System.out.println(Arrays.toString(numbers));
            }
        }
        swapElements(up,i,numbers);
        System.out.println(Arrays.toString(numbers));
        if(i == k) {
            return numbers[i];
        }
        if(i > k) {
            return recSelect(lo, i-1, k, numbers);

        }
        if(i < k) {
            return recSelect(i+1,up,k - i,numbers);
        }
        return -1;
    }

    private static void swapElements(int index1, int index2, int[] arr) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
