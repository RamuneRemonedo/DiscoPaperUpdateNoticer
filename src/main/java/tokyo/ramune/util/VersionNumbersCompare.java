package tokyo.ramune.util;

import java.util.Comparator;

public class VersionNumbersCompare implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String[] arr1 = String.valueOf(o1).split("\\.");
        String[] arr2 = String.valueOf(o2).split("\\.");

        int i=0;
        while(i<arr1.length || i<arr2.length){
            if(i<arr1.length && i<arr2.length){
                if(Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])) return -1;
                else if(Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])) return 1;
            } else if(i<arr1.length){
                if(Integer.parseInt(arr1[i]) != 0) return 1;
            } else if(i<arr2.length){
                if(Integer.parseInt(arr2[i]) != 0) return -1;
            }
            i++;
        }
        return 0;
    }
}
