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
                            }
                        }
                    }
                }
                else{
                    System.out.println("Build Unsuccess");
                }
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
                
            }
        }
        return false;
    }
    public static boolean karo(){
        if(TS[i].ClassPart.equalsIgnoreCase("karo")){
            
        }
        return false;
    }
    public static boolean jbtk(){
        if(TS[i].ClassPart.equalsIgnoreCase("jbtk")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("(")){
                value += TS[i].ValuePart;
                i++;
                if(cond()){
                    value += TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase(")")){
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
            if(TS[i].ClassPart.equalsIgnoreCase("(")){
                value+=TS[i].ValuePart;
                i++;
                if(cond()){
                    value+=TS[i].ValuePart;
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase(")")){
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
            value+=TS[i].ValuePart;
            i++;
            if(M_st()){
                value+=TS[i].ValuePart;
                i++;
                if(TS[i].ClassPart.equalsIgnoreCase("}")){
                    value+=TS[i].ValuePart;
                    i++;
                    return true;
                }
            }
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
    public static boolean cond(){
        return true;
                }                                  //Incomplete
    public static boolean Exp(){
        return true;
    }                                   //Incomplete
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
                    value += TS[i].ValuePart;
                    i++;
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
                return init()&&list();
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase(";")){
            return true;
        }
        return false;
    }
}