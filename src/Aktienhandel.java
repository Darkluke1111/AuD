import java.util.Arrays;
import java.util.Scanner;

class Aktienhandel {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Heap heap = new Heap(5);
        heap.insert(4);
        heap.insert(2);
        heap.insert(8);
        heap.insert(7);
        System.out.println(heap);
        System.out.println(heap.removeBiggest());
        System.out.println(heap);
    }

    private static int readInt() {

    }


}

class Heap {
    private int[] heap;
    private int usedSize;

    Heap(int size) {
        heap = new int[size];
        usedSize = 0;
    }

    public void insert(int number) {
        //Throw an exception if the maximum size is surpassed by the insert operation
        if(usedSize == heap.length) {
            throw new IndexOutOfBoundsException("The heap is already full! You can't add another element.");
        }

        int i = usedSize;
        heap[i] = number;
        while(i > 0 && heap[i] > heap[i/2]) {
            switchElements(i,i/2);
            i /= 2;
        }
        usedSize++;
    }

    public int removeBiggest() {
        int out = heap[0];
        heap[0] = heap[usedSize - 1];
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
