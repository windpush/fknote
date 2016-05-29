package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.EditActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/2/24.
 */
public class UndoOrRedo {
    public ArrayList<String> saveDoList(String vaule,ArrayList<String> listNow){
        if (listNow.size()==20){
            ArrayList<String> listNew =new ArrayList<>();
            listNew.addAll(listNow.subList(1,19));
            listNew.add(vaule);
            return listNew;
        }else {
            listNow.add(vaule);
            return listNow;
        }
    }
}
