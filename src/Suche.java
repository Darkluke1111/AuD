

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Suche {
    public static void main(String[] args) throws IOException {

        //Reader/Writer initialisieren
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String words = br.readLine();


        String[] wordArr = words.split(" |\\.");
        int[] numbers = excludeText(wordArr);
        if(numbers.length <= 1) {
            bw.write("undefined");
        } else {
            int result = selectElementBySize(numbers.length - 2, numbers);
            bw.write(Integer.toString(result));
        }
        br.close();
        bw.close();
    }

    /*
    Removes textwords and words with numbers and letters and returns the rest as an integer array.
     */
    public static int[] excludeText(String[] text) {
        Set<Integer> out = new HashSet<>();
        int[] arr;
        for(String word : text) {
            //test for only digits in the word
            if(word.matches("\\d+")) {
                //Parse to integer and add to the set
                out.add(Integer.parseInt(word));
            }
        }
        //Convert the set to an integer Array and return it
        arr = new int[out.size()];
        int i = 0;
        for(Integer number : out) {
            arr[i] = number;
            i++;
        }
        return arr;
    }

    public static int selectElementBySize(int k, int[] numbers) {
        return recSelect(0, numbers.length - 1, k, numbers);
    }

    /*
    Recursive select method. Selects the k-th smallest element from the partition from index lo to index up in the numbers array.
     */
    private static int recSelect(int lo, int hi, int k, int[] numbers) {
        int i = reorderArray(numbers, lo, hi);
        //System.out.println("k=" + k + " i=" + i + " lo=" + lo);

        switch(Integer.compare(i-lo, k)) {
            case 0:
                return numbers[i];
            case 1:
                return recSelect(lo, i-1, k, numbers);
            case -1:
                return recSelect(i+1,hi,k + lo - i - 1,numbers);
            default:
                System.out.println("Ein Fehler ist aufgetreten!");
                System.exit(-1);
                return -1;
        }
    }

    private static int reorderArray(int[] numbers, int lo, int hi) {
        int pivot = numbers[hi];
        //System.out.println("P=" + pivot + " Range: " + lo + "-" + hi);
        int i = lo - 1;
        int j = hi;

        while(i < j) {

            do {
                i++;
            } while(numbers[i] < pivot);

            do {
                j--;
            } while(i < j && numbers[j] >= pivot);

            if(i<j) {
                swapElements(i, j, numbers);
            }
        }
        swapElements(hi,i,numbers);
        return i;
    }

    private static void swapElements(int index1, int index2, int[] arr) {
        //debugLog(index1,index2,arr);
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


}
