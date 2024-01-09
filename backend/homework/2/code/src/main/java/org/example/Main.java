package org.example;

import org.slf4j.Logger;

import static org.example.StudentUtil.calculateGPA;
import static org.example.StudentUtil.getStudentsByGPA;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[] studentid={1001,1002};
        char[][] studentgrades={{'A','A','A','B'},{'A','B','B'}};
        double[] ans=calculateGPA(studentid,studentgrades);
        int[] ans2=getStudentsByGPA(3.2,3.5,studentid,studentgrades);
        for(double itr:ans) {
            System.out.println(itr);
        }
        for(int itr:ans2) {
            System.out.println(itr);
        }
        }
    }