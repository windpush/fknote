package com.example.administrator.daiylywriting.AlgorithmTest;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/3/13.
 */

public class AlgorithmTest  {

    public   void SortTest(int[] a){
        int size=a.length,temp,j;
        for(int i=1;i<size;i++){
            temp=a[i];
            j=i-1;
            for (;j>=0&&temp<a[j];j--){
                a[j+1]=a[j];
            }
            a[j+1]=temp;
            System.out.print(j+1);
        }
            for(int aSun:a){
                System.out.print(aSun+" ");
            }
    }
}
