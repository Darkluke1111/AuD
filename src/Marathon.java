import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Marathon {
    public static void main(String args[]) {
        /*
        int[] startPos = new int[] {1,2,4,3,5};
        int[] endPos = new int[] {2,1,3,4,5};
        int runners = startPos.length;
        endPos = substitute(runners, startPos, endPos);
        System.out.println(Arrays.toString(endPos));
        System.out.println(calcInversions(endPos));
        */

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int marathons = readInt(br);
            for(int i = 0 ;  i < marathons ; i++) {
                int runners = readInt(br);
                int[] startPos = readIntArray(br);
                int[] endPos = readIntArray(br);
                System.out.println("at least " + calcInversions(substitute(runners,startPos,endPos)) + " overtaking(s)");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readIntArray(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
    }

    public static int readInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public static int[] substitute(int runners, int[] startPos, int[] endPos) {
        int[] newEndPos = new int[runners];
        Map<Integer, Integer> substitution = new HashMap<>();
        for(int i = 0; i < runners ; i++) {
            substitution.put(startPos[i], i);
        }
        for(int i = 0 ; i < runners ; i++) {
            newEndPos[i] = substitution.get(endPos[i]);
        }
        return newEndPos;
    }

    public static int calcInversions(int[] permutation) {
        return recCalcInversions(permutation).inversions;
    }

    private static ReturnType recCalcInversions(int[] permutation) {
        //System.out.println(Arrays.toString(permutation));


        if(permutation.length <= 1) {
            return new ReturnType(permutation,0);
        }

        int mid = permutation.length/2;
        ReturnType rt1 = recCalcInversions(copyArrayPart(0,mid,permutation));
        ReturnType rt2 = recCalcInversions(copyArrayPart(mid,permutation.length, permutation));

        int j = 0;
        int k = 0;
        int inversions = 0;

        while(j + k < permutation.length) {
            //System.out.println(j + " " + k);
            if(rt1.returnArray.length <= j) {
                permutation[j + k] =  rt2.returnArray[k];
                k++;
                continue;
            }
            if(rt2.returnArray.length <= k) {
                permutation[j + k] = rt1.returnArray[j];
                j++;
                continue;
            }
            if(rt1.returnArray[j] <= rt2.returnArray[k]) {
                permutation[j + k] = rt1.returnArray[j];
                j++;
            } else {
                permutation[j + k] =  rt2.returnArray[k];
                k++;
                inversions += rt1.returnArray.length - j;
            }
        }
        inversions += rt1.inversions;
        inversions += rt2.inversions;

        return new ReturnType(permutation,inversions);
    }

    private static int[] copyArrayPart(int lo, int hi, int[] arr) {
        int[] out = new int[hi-lo];
        for(int i = 0 ; i < hi-lo ; i++) {
            out[i] = arr[lo + i];
        }
        return out;
    }

    static final class ReturnType {
        final int[] returnArray;
        final int inversions;

        ReturnType(int[] returnArray, int inversions) {
            this.returnArray = returnArray;
            this.inversions = inversions;
        }
    }
}

