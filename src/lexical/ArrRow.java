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
public class ArrRow extends Row {
    int size;
    public ArrRow(String n, String t, Stack<String> s) {
        super(n, t, s);
    }
    public ArrRow(String n, String t, Stack<String> s,int size) {
        super(n,t,s);
        this.size=size;
    }
    
}
