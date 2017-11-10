

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestSuche {

  public static void main(String[] args) {
    int testCount = 1000000;
    int arrayLength = 100;
    for(int i = 0 ; i < testCount; i++) {
      int k = (int) (Math.random() * arrayLength);
      int[] numbers = generateArray(arrayLength);
      int result = Suche.selectElementBySize(k,numbers);
      boolean suc = testFunctionality(numbers,result,k);
      if(!suc) {
        System.out.println("Test failed with k=" + k + "and with array:");
        System.out.println(Arrays.toString(numbers));
        System.out.println("The result was: " + result);
        return;
      }
    }
    System.out.println("Test was passed successfully!");

  }

  private static void debugLog(int index1, int index2, int[] arr) {
    char[] markerArr = new char[arr.length];
    for(int i = 0; i < arr.length ; i++) {
      if(i == index1 || i == index2) {
        markerArr[i] = 'X';
      } else {
        markerArr[i] = ' ';
      }
    }
    System.out.println(Arrays.toString(arr));
    System.out.println(Arrays.toString(markerArr));
  }

  private static int[] generateArray(int size) {
    int[] arr = new int[size];
    Set<Integer> set = new HashSet<>();
    while(set.size() < size) {
      set.add((int) (Math.random() * 100));
    }

  int i = 0;
    for(Integer num : set) {
      arr[i] = num;
      i++;
    }
    return arr;
  }

  private static boolean testFunctionality(int[] array, int number, int k) {
    int counter = 0;
    for(int temp : array) {
      if(number > temp) counter++;
    }
    return (counter == k);
  }
}
