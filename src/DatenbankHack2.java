import java.util.Arrays;
import java.util.stream.IntStream;

class DatenbankHack2 {





}

class HashTable<T> {

    int size;
    HashFunction<T> hashFunction;
    T[] hashTable;

    public HashTable(int size, HashFunction<T> hashFunction) {
        this.size = size;
        this.hashFunction = hashFunction;
        hashTable = (T[]) new Object[size];
    }

    public void insertValue(T value) {
        int i = 0;
        for(; hashTable[hashFunction.hash(size, value, i)] != null ; i++);
        int key = hashFunction.hash(size,value,i);
    }

    public T getValue(T value) {
        int i = 0;
        for(; hashTable[hashFunction.hash(size, value, i)] != value ; i++);
        return value;
    }

    public int getValuePos(T value) {
        int i = 0;
        for(; hashTable[hashFunction.hash(size, value, i)] != value ; i++);
        return i;
    }

    public T getValueAtPos(int pos) {
        return hashTable[pos];
    }

    @Override
    public String toString() {
        return Arrays.deepToString(hashTable);
    }

    @FunctionalInterface
    interface HashFunction<T> {
        int hash(int tableSize, T value, int collisions);
    }

    public int linearScanHashFunction(int tableSize, T value, int collisions) {
        int hashCode = value.hashCode();
        return
    }
}

