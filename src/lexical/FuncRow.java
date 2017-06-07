/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

import java.util.Stack;

/**
 *
 * @author Xcalaiberz
 */
public class FuncRow extends Row {
    String ReturnType;
    String ParamList;
    public FuncRow(String n, String t, Stack<String> s) {
        super(n, t, s);
        String temp[] = t.split("->");
        ReturnType = temp[0];
        ParamList=temp[1];
    }
    
}
