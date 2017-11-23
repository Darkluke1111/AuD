import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DatenbankHack {
  public static void main(String args[]) {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int concernNumber = readInt(br);

      for(int k = 0 ; k < concernNumber ; k++) {
        ConcernData concernData = readConcernData(br);
        boolean[] normalHash = new boolean[concernData.tableSize];
        boolean[] doubleHash = new boolean[concernData.tableSize];
        for(int key : concernData.keys) {

          //normal Hashing
          {
            int i = 0;
            for (; normalHash[normalHash(concernData.tableSize, i, key)]; i++) ;
            normalHash[normalHash(concernData.tableSize, i, key)] = true;
          }

          //double Hashing
          {
            int i = 0;
            for (; doubleHash[doubleHash(concernData.tableSize, i, key)]; i++) ;
            doubleHash[doubleHash(concernData.tableSize, i, key)] = true;
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static ConcernData readConcernData(BufferedReader br) throws IOException {
    ConcernData concernData = new ConcernData();
    concernData.tableSize = readInt(br);
    concernData.keys = readIntArray(br);
    concernData.bossKey = readInt(br);
    return concernData;

  }

  private static int normalHash(int tablesize, int i, int key) {
    return (key + i)% tablesize;
  }

  private static int doubleHash(int tablesize, int i, int key) {
    return (key + i * (1 + (key % (tablesize-1))) % tablesize);
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
  }
}
