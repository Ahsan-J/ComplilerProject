/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author Xcalaiberz
 */
public class Semantic {
    
    static Stack<Row> Table = new Stack<Row>();
    static int ScopeIndex=0;
    static int i=0;
    static Tokens [] TS;
    static String value = new String();
    static Stack<String> scope = new Stack<String>();
    
    public static String lookup(String n,Stack<String> s){
        if(!Table.empty()){
        for(int q=0;q<Table.size();q++){
            if(n.equals(Table.elementAt(q).Name)){
                Object a[] = Table.elementAt(q).scope.toArray();
                Object var = s.peek();
                for(int p=0;p<a.length;p++){
                    if(var.equals(a[p])){
                        return Table.elementAt(q).Type;
                    }
                }
            }
        }
        }
            return "null";
        
    }
    
    public static boolean Start() throws FileNotFoundException{
        TS = Syntax.returnTokens();
        
        if(naqsha()){
//            System.out.println(value);
            value="";
        }
        if(TS[i].ClassPart.equalsIgnoreCase("shuru")){
            i++;
            scope.push("S"+ScopeIndex++);
            if(TS[i].ClassPart.equalsIgnoreCase(":")){
                i++;
                if(body()){                 
                    if(TS[i].ClassPart.equalsIgnoreCase("khatam")){
                        System.out.println("Build Success");
                        scope.pop();
                        return true;
                    }
                }
            }
        }
                
        System.err.println("Build Unsuccess");
//        System.out.println(TS[i]);
        return false;
    }
    public static boolean S_st(){
        if(TS[i].ClassPart.equalsIgnoreCase("DT")||TS[i].ClassPart.equalsIgnoreCase("ID")){
            value+=TS[i].ValuePart;
            i++;
            if(decl()){
//                System.out.println(value + " Declared");
                value = "";
                return true;
            }
            else if(arr_decl()){
//                System.out.println(value + " Declared");
                value = "";
                return true;
            }
            else if(func_defs()){
//                System.out.println(value + " Declared");
                value = "";
                return true;
            }
            else if(TS[i-1].ClassPart.equalsIgnoreCase("ID")){
                if(func_call()){
//                    System.out.println(value + " Declared");
                    value = "";
                    return true;
                }
                else if(Obj()){
//                    System.out.println(value + " Declared");
                    value = "";
                    return true;
                }
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("khlaa")){
            if(func_defs()){
//                System.out.println(value + " Declared");
                value = "";
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("agr")){
            if(agr()){
//                System.out.println(value);
                value = "";
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("jbtk")){
            if(jbtk()){
//                System.out.println(value);
                value="";
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("karo")){
            if(karo()){
//                System.out.println(value);
                value="";
                return true;
            }
        }
        else if(naqsha()){
//            System.out.println(value);
            value="";
            return true;
        }
        return false;
    }
    public static boolean karo(){
        if(TS[i].ClassPart.equalsIgnoreCase("karo")){
            value += TS[i].ValuePart;
            i++;
            if(body()){
                if(TS[i].ClassPart.equalsIgnoreCase("jbtk")){
                    value += TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase("OB")){
                        value += TS[i].ValuePart;
                        i++;
                        if(Exp()){
                            if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                                value+= TS[i].ValuePart;
                                i++;
                                if(TS[i].ClassPart.equalsIgnoreCase(";")){
                                    value += TS[i].ValuePart;
                                    i++;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean jbtk(){
        if(TS[i].ClassPart.equalsIgnoreCase("jbtk")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("OB")){
                value += TS[i].ValuePart;
                i++;
                if(Exp()){
                    if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                        value +=TS[i].ValuePart;
                        i++;
                        return body();
                    }
                }
            }
        }
        return false;
    }
    public static boolean agr(){
        if(TS[i].ClassPart.equalsIgnoreCase("agr")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("OB")){
                value+=TS[i].ValuePart;
                i++;
                if(Exp()){
                    if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                        value+=TS[i].ValuePart;
                        i++;
                        if(body()){
                            return warna();
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean warna(){
        if(TS[i].ClassPart.equalsIgnoreCase("warna")){
            value += TS[i].ValuePart;
            i++;
            return body();
        }
        return true;
    }
    public static boolean M_st()  {
        if(S_st()){
            return M_st();
        }
        return true;
    }
    public static boolean body(){
        if(TS[i].ClassPart.equalsIgnoreCase("{")){
            value+=TS[i].ValuePart;
            i++;
            if(M_st()){
                if(TS[i].ClassPart.equalsIgnoreCase("}")){
                    value+=TS[i].ValuePart;
                    i++;
                    return true;
                }
            }
//            System.out.println(TS[i]);
            return false;
        }
        return S_st();
    }
    public static boolean arr_decl(){
        if(TS[i-1].ClassPart.equalsIgnoreCase("DT")){
            if(TS[i].ClassPart.equalsIgnoreCase("[")){
                value += TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("]")){
                    value += TS[i].ValuePart;
                    i++;
                    if(arr()){
                        if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                            value+=TS[i].ValuePart;
                            i++;
                            if(TS[i].ClassPart.equalsIgnoreCase("=")){
                                value+=TS[i].ValuePart;
                                i++;
                                if(TS[i].ClassPart.equalsIgnoreCase("naya")){
                                    value+=TS[i].ValuePart;
                                    i++;
                                    if(TS[i].ClassPart.equalsIgnoreCase("DT")){
                                        value+=TS[i].ValuePart;
                                        i++;
                                        if(arr_init()){
                                            value+=TS[i].ValuePart;
                                            i++;
                                            if(TS[i].ClassPart.equalsIgnoreCase(";")){
                                                System.out.println(value + " Declared");
                                                value = "";
                                                return true;
                                            }
                                        }
                                    }                             
                                }
                            }
                        }
                    }
                }
            }
            else if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value+=TS[i].ValuePart;
                i++;
                if(arr()){
                    value+=TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase(";")){
//                        System.out.println(value+" Declared");
                        value="";
                        return true;
                    }
                }
            }
        }
        else if(TS[i-1].ClassPart.equalsIgnoreCase("ID")){
            if(TS[i].ClassPart.equalsIgnoreCase("[")){
                value += TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("]")){
                    value += TS[i].ValuePart;
                    i++;
                    if(arr()){
                        value+= TS[i].ValuePart;
                        i++;
                        if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                            value+=TS[i].ValuePart;
                            i++;
                            if(TS[i].ClassPart.equalsIgnoreCase("=")){
                                value+=TS[i].ValuePart;
                                i++;
                                if(TS[i].ClassPart.equalsIgnoreCase("naya")){
                                    value+=TS[i].ValuePart;
                                    i++;
                                    if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                                        value+=TS[i].ValuePart;
                                        i++;
                                        if(arr_init()){
                                            value+=TS[i].ValuePart;
                                            i++;
                                            if(TS[i].ClassPart.equalsIgnoreCase(";")){
//                                                System.out.println(value + " Declared");
                                                value = "";
                                                return true;
                                            }
                                        }
                                    }                             
                                }
                            }
                        }
                    }
                }
            }
            else if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value+=TS[i].ValuePart;
                i++;
                if(arr()){
                    value+=TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase(";")){
//                        System.out.println(value+" Declared");
                        value="";
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    public static boolean arr_init(){
        if(TS[i].ClassPart.equalsIgnoreCase("[")){
            value += TS[i].ValuePart;
            i++;
            if(Exp()){
                value += TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("]")){
                    value+=TS[i].ValuePart;
                    i++;
                    return arr_init();
                }
            }
            return false;
        }
        return true;
    }
    public static boolean naqsha(){
        if(TS[i].ClassPart.equalsIgnoreCase("AccMod")){
            value+= TS[i].ValuePart;
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("aakhri")){
            value += TS[i].ValuePart;
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("sakin")){
            value += TS[i].ValuePart;
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("naqsha")){
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("barhao")){
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                        i++;
                    }
                    else{
                        return false;
                    }
                }
                if(TS[i].ClassPart.equalsIgnoreCase("{")){
                    if(class_body()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean class_body(){
//        scope.push("S"+ScopeIndex);
        if(TS[i].ClassPart.equalsIgnoreCase("AccMod")){
            value+= TS[i].ValuePart;
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("aakhri")){
            value+= TS[i].ValuePart;
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("sakin")){
            value+= TS[i].ValuePart;
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("DT")||TS[i].ClassPart.equalsIgnoreCase("ID")){
            if(decl()){
                return class_body();
            }
            else if(func_defs()){
                return class_body();
            }
        }
        return true;
    }
    public static boolean Exp(){
        if(TS[i].ClassPart.equalsIgnoreCase("INCDEC")||TS[i].ClassPart.equalsIgnoreCase("OB")||TS[i].ClassPart.equalsIgnoreCase("NOT")||TS[i].ClassPart.equalsIgnoreCase("ID")||TS[i].ClassPart.equalsIgnoreCase("intConst")||TS[i].ClassPart.equalsIgnoreCase("floatConst")||TS[i].ClassPart.equalsIgnoreCase("stringConst")||TS[i].ClassPart.equalsIgnoreCase("charConst")){
            if(G()){
                return F1();
            }
            return false;
        }
        return true;
    }   
    public static boolean F1(){
        if(TS[i].ClassPart.equalsIgnoreCase("||")){
            value+=TS[i].ValuePart;
            i++;
            if(G()){
                return F1();
            }
            return false;
        }
        return true;
    }
    public static boolean G(){
        if(H()){
            return G1();
        }
        return false;
    }
    public static boolean G1(){
        if(TS[i].ClassPart.equalsIgnoreCase("&&")){
            value+=TS[i].ValuePart;
            i++;
            if(H()){
                return G1();
            }
            return false;
        }
        return true;
    }
    public static boolean H(){
        if(I()){
            return H1();
        }
        return false;
    }
    public static boolean H1(){
        if(TS[i].ClassPart.equalsIgnoreCase("RO")){
            value+=TS[i].ValuePart;
            i++;
            if(I()){
                return H1();
            }
            return false;
        }
        return true;
    }
    public static boolean I(){
        if(J()){
            return I1();
        }
        return false;
    }
    public static boolean I1(){
        if(TS[i].ClassPart.equalsIgnoreCase("ADDSUB")){
            value+=TS[i].ValuePart;
            i++;
            if(J()){
                return I1();
            }
            return false;
        }
        return true;
    }
    public static boolean J(){
        if(K()){
            return J1();
        }
        return false;
    }
    public static boolean J1(){
        if(TS[i].ClassPart.equalsIgnoreCase("DIVMUL")||TS[i].ClassPart.equalsIgnoreCase("MOD")){
            value+=TS[i].ValuePart;
            i++;
            if(K()){
                return J1();
            }
            return false;
        }
        return true;
    }
    public static boolean K(){
        if(L()){
            return true;
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("INCDEC")){
            value+=TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value+=TS[i].ValuePart;
                i++;
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("OB")){
            value+=TS[i].ValuePart;
            i++;
            if(Exp()){
                if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                    value+=TS[i].ValuePart;
                    i++;
                    return true;
                }
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("NOT")){
            value+=TS[i].ValuePart;
            i++;
            return Exp();
        }
        return false;
    }
    public static boolean L(){
        if(TS[i].ClassPart.equalsIgnoreCase("ID")){
            value+=TS[i].ValuePart;
            i++;
            return D();
        }
        return C();
    }
    public static boolean C(){
        if(TS[i].ClassPart.equalsIgnoreCase("intConst")||TS[i].ClassPart.equalsIgnoreCase("floatConst")||TS[i].ClassPart.equalsIgnoreCase("stringConst")||TS[i].ClassPart.equalsIgnoreCase("charConst")){
            value += TS[i].ValuePart;
            i++;
            return true;
        }
        else
        return false;
    }
    public static boolean D(){
        if(TS[i].ClassPart.equalsIgnoreCase("INCDEC")){
            value+=TS[i].ValuePart;
            i++;
            return true;
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("->")){
            value+=TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value+=TS[i].ValuePart;
                i++;
                return true;
            }
            return false;
        }
//        else if(func_call()){
//            return true;
//        }
        return true;
    }
    public static boolean func_call(){
        if(TS[i-1].ClassPart.equalsIgnoreCase("ID")){
            if(TS[i].ClassPart.equalsIgnoreCase("OB")){
                value+=TS[i].ValuePart;
                i++;
                if(args()){
                    if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                        value+=TS[i].ValuePart;
                        i++;
                        if(TS[i].ClassPart.equalsIgnoreCase(";")){
                            value+=TS[i].ValuePart;
                            i++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean args(){
        if(Exp()){
            return N1();
        }
        return true;
    }
    public static boolean N1(){
        if(TS[i].ClassPart.equalsIgnoreCase("comma")){
            value+=TS[i].ValuePart;
            i++;
            if(Exp()){
                return N1();
            }
        }
        return true;
    }
    public static boolean Obj(){
        if(TS[i-1].ClassPart.equalsIgnoreCase("ID")){
            
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value+= TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("=")){
                    value+= TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase("naya")){
                        value+= TS[i].ValuePart;
                        i++;
                        if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                            value+= TS[i].ValuePart;
                            i++;
                            if(TS[i].ClassPart.equalsIgnoreCase("OB")){
                                value+= TS[i].ValuePart;
                                i++;
                                if(param()){
                                    if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                                        value += TS[i].ValuePart;
                                        i++;
                                        if(TS[i].ClassPart.equalsIgnoreCase(";")){
                                            value += TS[i].ValuePart;
                                            i++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean func_defs(){
        if(TS[i-1].ClassPart.equalsIgnoreCase("DT")||TS[i-1].ClassPart.equalsIgnoreCase("ID")||TS[i-1].ClassPart.equalsIgnoreCase("khlaa")){
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value+=TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("OB")){
                    value+=TS[i].ValuePart;
                    i++;
                    if(param()){
                        value+=TS[i].ValuePart;
                        i++;
                        if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                            value+= TS[i].ValuePart;
                            i++;
                            if(TS[i].ClassPart.equalsIgnoreCase("{")){
                                value+= TS[i].ValuePart;
                                i++;
                                if(M_st()){
                                    if(TS[i].ClassPart.equalsIgnoreCase("}")){
                                        value+= TS[i].ValuePart;
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{
                if(TS[i].ClassPart.equalsIgnoreCase("[")){
                    value += TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase("]")){
                        value += TS[i].ValuePart;
                        i++;
                        if(arr()){
                            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                                value += TS[i].ValuePart;
                                i++;
                                if(TS[i].ClassPart.equalsIgnoreCase("OB")){
                                    value+=TS[i].ValuePart;
                                    i++;
                                    if(param()){
                                        value+=TS[i].ValuePart;
                                        i++;
                                        if(TS[i].ClassPart.equalsIgnoreCase("CB")){
                                            value+= TS[i].ValuePart;
                                            i++;
                                            if(TS[i].ClassPart.equalsIgnoreCase("{"))
                                                value+= TS[i].ValuePart;
                                                i++;
                                                if(M_st()){
                                                    if(TS[i].ClassPart.equalsIgnoreCase("}")){
                                                        value+= TS[i].ValuePart;
                                                        i++;
                                                        return true;
                                                    }
                                                }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean param(){
        if(TS[i].ClassPart.equalsIgnoreCase("DT")||TS[i].ClassPart.equalsIgnoreCase("ID")){
            value+=TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value+=TS[i].ValuePart;
                i++;
                return next();
            }
            else if(TS[i].ClassPart.equalsIgnoreCase("[")){
                value+=TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("]")){
                    value+=TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                        value+=TS[i].ValuePart;
                        i++;
                        return next();                            
                    }
                }
            }
        }
        return true;
    }
    public static boolean next(){
        if(TS[i].ClassPart.equalsIgnoreCase("comma")){
            value+=TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")||TS[i].ClassPart.equalsIgnoreCase("DT")){
                value+=TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                    return next();
                }
                else{
                    if(TS[i].ClassPart.equalsIgnoreCase("[")){
                        value+=TS[i].ValuePart;
                        i++;
                        if(TS[i].ClassPart.equalsIgnoreCase("]")){
                            value+=TS[i].ValuePart;
                            i++;
                            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                                value+=TS[i].ValuePart;
                                i++;
                                return next();
                            }
                        } 
                    }
                }
            }
        }
        return true;
    }
    public static boolean arr(){
        if(TS[i].ClassPart.equalsIgnoreCase("[")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("]")){
                value += TS[i].ValuePart;
                i++;
                return arr();
            }
            return false;
        }
        return true;
    }
    public static boolean decl(){
        if(TS[i-1].ClassPart.equalsIgnoreCase("DT")){
            String type = TS[i-1].ValuePart;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value += TS[i].ValuePart;
                String Name = TS[i].ValuePart;
                if(lookup(Name,scope).equals("null")){
                    Table.push(new Row(Name,type,scope));
                    System.out.println("Declared "+Name);
                }
                else{
                    System.out.println("Redeclaration Error");
                }
                i++;
                if(init(type)){
                    if(list(type)){
                        value += TS[i].ValuePart;
                        i++;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean init(String Type){
        if(TS[i].ClassPart.equalsIgnoreCase("=")){
            value += TS[i].ValuePart;
            i++;
            if(Exp()){
                return true;
            }
            return false;
        }
        return true;
    }
    public static boolean list(String Type){
        if(TS[i].ClassPart.equalsIgnoreCase("comma")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value += TS[i].ValuePart;
                String Name = TS[i].ValuePart;
                if(lookup(Name,scope).equals("null")){
                    Table.push(new Row(Name,Type,scope));
                    System.out.println("Declared "+Name);
                }
                else{
                    System.out.println("Redeclaration Error");
                }
                i++;
                if(init(Type)){
                    return list(Type);
                }
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase(";")){
            return true;
        }
        return false;
    }
}