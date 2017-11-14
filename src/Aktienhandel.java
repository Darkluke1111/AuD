import java.util.Arrays;

class Aktienhandel {
    public static void main(String[] args) {
        Heap heap = new Heap(5);
        heap.insert(4);
        heap.insert(2);
        heap.insert(8);
        heap.insert(7);
        System.out.println(heap);
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
