package adt;

import adt.linkedList.RecursiveSingleLinkedListImpl;

public class Main {

    public static void main(String[] args){
        RecursiveSingleLinkedListImpl<Integer> qi = new RecursiveSingleLinkedListImpl<>();

        qi.insert(1);
        qi.insert(2);
        qi.insert(3);

        System.out.println(qi.indexOf(4));
    }
}
