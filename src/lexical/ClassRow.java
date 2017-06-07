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
public class ClassRow extends Row{
    String TypeModifier,AccessModifier,Parent;
    Stack<ArrRow> ArrTable = new Stack<ArrRow>();
    Stack<FuncRow> FuncTable = new Stack<FuncRow>();
    Stack<Row> varTable = new Stack<Row>();
    
    public ClassRow(String n, String t, Stack<String> s) {
        super(n, t, s);
        AccessModifier="Default";
        TypeModifier = "null";
        Parent = "null";
    }
    public ClassRow(String AM, String TM, String n, String t, Stack<String> s) {
        super(n, t, s);
        AccessModifier=AM;
        TypeModifier = TM;
        Parent="null";
    }
    public ClassRow(String AM, String TM, String n, String t, Stack<String> s,String P) {
        super(n, t, s);
        AccessModifier=AM;
        TypeModifier = TM;
        Parent = P;
    }
}
