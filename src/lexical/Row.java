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
public class Row {
        String Type;
        String Name;
        Stack<String> scope;
    
    public Row(String n,String t,Stack<String> s){
        this.Type=t;
        this.Name=n;
        this.scope=s;
    }
}
