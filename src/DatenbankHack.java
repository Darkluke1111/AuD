import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class DatenbankHack {
  public static void main(String args[]) {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int concernNumber = readInt(br);
      for(int k = 0 ; k < concernNumber ; k++) {
        ConcernData concernData = readConcernData(br);

        int[] normalHash = IntStream.generate(() -> -1).limit(concernData.keyNumber).toArray();
        int[] doubleHash = IntStream.generate(() -> -1).limit(concernData.keyNumber).toArray();

        for(int key : concernData.keys) {

          //normal Hashing
          {
            int i = 0;
            for (; normalHash[normalHash(concernData.tableSize, i, key)] != -1; i++) ;
            normalHash[normalHash(concernData.tableSize, i, key)] = key;
          }


          //double Hashing
          {
            int i = 0;
            for (; doubleHash[doubleHash(concernData.tableSize, i, key)] != -1; i++) ;
            doubleHash[doubleHash(concernData.tableSize, i, key)] = key;
          }
        }

        {
          int i = 0;
          for (; normalHash[normalHash(concernData.tableSize, i, concernData.bossKey)] != concernData.bossKey; i++);
          System.out.println(normalHash(concernData.tableSize, i, concernData.bossKey));
        }

        {
          int i = 0;
          for (; doubleHash[doubleHash(concernData.tableSize, i, concernData.bossKey)] !=  concernData.bossKey; i++) ;
          System.out.println(doubleHash(concernData.tableSize, i, concernData.bossKey));
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static ConcernData readConcernData(BufferedReader br) throws IOException {
    ConcernData concernData = new ConcernData();
    int[] tmp = readIntArray(br);
    concernData.tableSize = tmp[0];
    concernData.keyNumber = tmp[1];
    concernData.keys = new int[concernData.keyNumber];
    concernData.bossKey = tmp[2];
    for(int i = 0 ; i < concernData.keyNumber ; i++) {
      concernData.keys[i] = readInt(br);
    }


    return concernData;

  }

  private static int normalHash(int tablesize, int i, int key) {
    return ((key + i)% tablesize);
  }

  private static int doubleHash(int tablesize, int i, int key) {
    return ((key + i * (1 + (key % (tablesize-1)))) % tablesize);
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

  private static class ConcernData {
    int tableSize;
    int[] keys;
    int bossKey;
    int keyNumber;
  }
}
