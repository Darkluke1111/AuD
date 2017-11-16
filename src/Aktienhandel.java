import java.io.*;
import java.util.Arrays;

class Aktienhandel {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true)) {
            int length = readInt(br);
            int[] numbers = readIntArray(br);

            //int length = 1000;
            //int[] numbers = getRandomArray(length);
            //System.out.println(Arrays.toString(numbers));

            Heap buy = new Heap(length);
            Heap sell = new Heap(length);

            for(int i = 0 ; i < length ; i++) {
                if(numbers[i] < 0) {
                    sell.insert(numbers[i]);
                } else {
                    buy.insert(numbers[i]);
                }
                //System.out.println(buy + " " + sell);
                if(buy.getUsedSize() > 0 && sell.getUsedSize() > 0 && buy.getBiggest() >= -sell.getBiggest()) {
                    //System.out.print("Remove: " + buy.getBiggest() + " : " + sell.getBiggest());
                    buy.removeBiggest();
                    sell.removeBiggest();
                    pw.write("Handel in Schritt " + (i+1) + "\r\n");
                    //System.out.println(" -> " + buy + " " +  sell);
                }

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
        for(int i = 0 ; i < length ; i++) {
            while(arr[i] == 0) {
                arr[i] = (int) (Math.random() * 100) - 50;
            }
        }
        return arr;
    }


}

class Heap {
    private int[] heap;
    private int usedSize;

    Heap(int size) {
        heap = new int[size];
        usedSize = 0;
    }

    public int getElementAtPos(int i) {
        return heap[i - 1];
    }

    public void setElementAtPos(int i, int value) {
        heap[i - 1] = value;
    }

    public void insert(int number) {
        //Throw an exception if the maximum size is surpassed by the insert operation
        if(usedSize == heap.length) {
            throw new IndexOutOfBoundsException("The heap is already full! You can't add another element.");
        }

        int i = usedSize;
        heap[i] = number;
        usedSize++;
        while(i > 0 && heap[i] > heap[(i + 1)/2 - 1]) {
            switchElements(i,(i + 1)/2 - 1);
            i = (i+1)/2 - 1;
        }

    }

    public int getUsedSize() {
        return usedSize;
    }

    public int getBiggest() {
        return heap[0];
    }

    public int removeBiggest() {
        int out = heap[0];
        heap[0] = heap[usedSize - 1];
        heap[usedSize -1] = 0;
        usedSize--;
        heapify(0, usedSize - 1);
        return out;
    }

    private void heapify(int lo, int hi) {
        lo++; hi++;

        int k = 2 * lo;
        if(k > hi) {
            return;
        }
        if(k + 1 > hi) {
            if(heap[k - 1] > heap[lo - 1]) {
                switchElements(lo - 1,k - 1);
                return;
            }
        }
        if(heap[k - 1] < heap[k]) {
            k++;
        }
        if(heap[lo - 1] < heap[k - 1]) {
            switchElements(lo - 1, k - 1);
            heapify(k - 1,hi - 1);
        }
    }

    private void switchElements(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] =  heap[index2];
        heap[index2] =  temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }
}
