import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class Aktienhandel2 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true)) {
            int length = readInt(br);
            Queue<Integer> buy = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Integer> sell = new PriorityQueue<>(Collections.reverseOrder());
            int i = 1;
            int[] arr = readIntArray(br);

            for(int number : arr) {
                if(number > 0) {
                    buy.add(number);
                } else {
                    sell.add(number);
                }

                if(!buy.isEmpty() && !sell.isEmpty() && buy.peek() >= -sell.peek()) {

                    buy.poll();
                    sell.poll();
                    pw.write("Handel in Schritt " + i + "\r\n");
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static int readInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private static int[] readIntArray(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();
    }

    private static int[] getRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            while (arr[i] == 0) {
                arr[i] = (int) (Math.random() * 100) - 50;
            }
        }
        return arr;
    }
}
