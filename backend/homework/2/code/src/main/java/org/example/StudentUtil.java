package org.example;
import java.util.*;
public class StudentUtil {
    public static double[] calculateGPA(int[] studentList,char[][] studentsGrades){

    int n=studentList.length;
    double[] arr = new double[n];
    Map<Character,Integer> mp=new HashMap<>();
    mp.put('A',4);
    mp.put('B',3);
    mp.put('C',2);


    for(int i=0;i<n;i++) {
        int num = studentList[i];
        char[] chArray = studentsGrades[i];
        int sum = 0;
        for (char ch : chArray) {
            sum = sum + mp.get(ch);
        }
        int size = chArray.length;
        double ans = sum /(double)size;
        arr[i] = ans;
    }
    return arr;
}

    public static int[] getStudentsByGPA(double lower, double higher,
                                         int[] studentIdList,
                                         char[][] studentsGrades){
        if(lower<0 || higher<0 || lower > higher) return null;  //parameter validation

        int n=studentsGrades.length;

        int count=0;
        double[] gpa=calculateGPA(studentIdList,studentsGrades);
        for(double itr:gpa){
            if(lower<=itr && itr<=higher){
                count++;
            }
        }
        int[] arr=new int[count];
        int index=0;
        for(int i=0;i<n;i++){
            double itr=gpa[i];
            if(lower<=itr && itr<=higher){
                arr[index++]=studentIdList[i];
            }
        }
        return  arr;
    }
}
