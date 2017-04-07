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
/**
 *
 * @author Xcalaiberz
 */
public class Syntax {
    public static class Tokens{
        public String ClassPart , ValuePart = null;
        public int LineNumber = 0;
        public Tokens(String C,String V,int L){
            this.ClassPart=C;
            this.ValuePart=V;
            this.LineNumber=L;
        }
        public String toString(){
            return "( "+this.ClassPart+" , "+this.ValuePart+" , "+this.LineNumber+" )";
        }
    }
    public static Tokens[] getTokens(){
        int filesize=0;
        String filename = "lexical_output.txt";
//        String filename = "lexical_output.txt";
        Tokens[] TS=null;
        try{
        Scanner reader = new Scanner (new BufferedReader (new FileReader(filename)));
        String Line = null;
        while(reader.hasNext()){
            Line = reader.nextLine();
            Line = Line.trim();
            if(Line.charAt(0)=='('){
            filesize++;
            }
            else{
//                System.out.println(Line);
            }
        }
        reader.close();
        }
        catch(Exception e){
            System.err.println("File Not Found \nError Says"+e.getMessage());
        }
        TS = new Tokens[filesize];
        try{
            String Line = null;
            Scanner reader = new Scanner (new BufferedReader (new FileReader(filename)));
            int indexSize=0;
            while(reader.hasNext()){
                Line = reader.nextLine();
                Line.trim();
                if(Line.charAt(0)=='('){
                    Line = Line.replace('(', ' ');
                    Line = Line.replace(')', ' ');
                    Line = Line.trim();
                    String [] TokenParts = Line.split(",");
                    for(int k=0;k<TokenParts.length;k++){
                        TokenParts[k]=TokenParts[k].trim();
                    }
                    if(TokenParts[1].equalsIgnoreCase("-")){
                        TokenParts[1]=TokenParts[0];
                    }
                    TS[indexSize++]=new Tokens(TokenParts[0],TokenParts[1],Integer.valueOf(TokenParts[2]));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
//            System.err.println("Problems at the Tokens or "+ e.getMessage());
        }
        return TS;
    }
    
    static int i=0;
    static Tokens [] TS;   
    static String value = new String();
    
    public static void Start() throws FileNotFoundException{
        TS = getTokens();
                if(TS[i].ClassPart.equalsIgnoreCase("shuru")){
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase(":")){
                        i++;
                        if(body()){                 
                            i++;
                            if(TS[i].ClassPart.equalsIgnoreCase("khatam")){
                                System.out.println("Build Success");
                                System.exit(0);
                            }
                        }
                    }
                }
                
        System.err.println("Build Unsuccess");
//        System.out.println(TS[i]);
                
    }
    public static boolean S_st(){
        if(TS[i].ClassPart.equalsIgnoreCase("DT")||TS[i].ClassPart.equalsIgnoreCase("ID")){
            if(decl()){
                System.out.println(value + " Declared");
                value = "";
                return true;
            }
            else if(arr_decl()){
                System.out.println(value + " Declared");
                value = "";
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("agr")){
            if(agr()){
                System.out.println(value);
                value = "";
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("jbtk")){
            if(jbtk()){
                System.out.println(value);
                value="";
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("karo")){
            if(karo()){
                System.out.println(value);
                value="";
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("")){
            
        }
        return false;
    }
    public static boolean karo(){
        if(TS[i].ClassPart.equalsIgnoreCase("karo")){
            value += TS[i].ValuePart;
            i++;
            if(body()){
                value += TS[i].ValuePart;
                i++;
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
                            value+=TS[i].ValuePart;
                            i++;
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
    public static boolean M_st(){
        if(S_st()){
            return M_st();
        }
        return true;
    }
    public static boolean body(){
        if(TS[i].ClassPart.equalsIgnoreCase("{")){
            i++;
            if(M_st()){
                if(TS[i].ClassPart.equalsIgnoreCase("}")){
                    return true;
                }
            }
//            System.out.println(TS[i]);
            return false;
        }
        return S_st();
    }
    public static boolean arr_decl(){
        if(TS[i].ClassPart.equalsIgnoreCase("DT")){
            value += TS[i].ValuePart;
            i++;
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
                        System.out.println(value+" Declared");
                        value="";
                        return true;
                    }
                }
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("ID")){
            value += TS[i].ValuePart;
            i++;
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
                        System.out.println(value+" Declared");
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
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("sakin")){
            i++;
        }
        if(TS[i].ClassPart.equalsIgnoreCase("naqsha")){
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("{")){
                    if(body()){
                        return true;
                    }
                }
            }
        }
        return false;
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
        if(TS[i].ClassPart.equalsIgnoreCase("DT")){    
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value += TS[i].ValuePart;
                i++;
                if(init()){
                    if(list()){
                        value += TS[i].ValuePart;
                        i++;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean init(){
        if(TS[i].ClassPart.equalsIgnoreCase("=")){
            value += TS[i].ValuePart;
            i++;
            return init1();
        }
        return true;
    }
    public static boolean init1(){
        if(TS[i].ClassPart.equalsIgnoreCase("ID")){
            i++;
            return init();
        }
        else if(Exp()){
            return true;
        }
        return false;
    }
    public static boolean list(){
        if(TS[i].ClassPart.equalsIgnoreCase("comma")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value += TS[i].ValuePart;
                i++;
                if(init()){
                    i++;
                    return list();
                }
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase(";")){
            return true;
        }
        return false;
    }
}