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
            System.out.println(filesize);
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
        while(i<TS.length){
                if(TS[i].ClassPart.equalsIgnoreCase("shuru")){
                    i++;
                    if(TS[i].ClassPart.equalsIgnoreCase(":")){
                        i++;
                        if(TS[i].ClassPart.equalsIgnoreCase("{")){
                            i++;
                            M_st();
                            
                            
                            
                            
                            
                            
                            
                            if(TS[i].ClassPart.equalsIgnoreCase("}")){
                                i++;
                                if(TS[i].ClassPart.equalsIgnoreCase("khatam")){
                                    System.out.println("Build Success");
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println("Build Unsuccess");
                }
            i++;
        }
    }
    public static boolean M_st(){
        if(TS[i].ClassPart.equalsIgnoreCase("DT")){
            if(decl()){
                System.out.println(value + " Declared");
            }
            
        }
        
        return false;
    }
    public static boolean decl(){
        if(TS[i].ClassPart.equalsIgnoreCase("DT")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("ID")){
                value += TS[i].ValuePart;
                i++;
                list();
                if(TS[i].ClassPart.equalsIgnoreCase(";")){
                    value += TS[i].ValuePart;
                    return true;
                }
            }
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
                list();
                return true;
            }
        }
        else if(TS[i].ClassPart.equalsIgnoreCase("=")){
            value += TS[i].ValuePart;
            i++;
            if(TS[i].ClassPart.equalsIgnoreCase("Exp")){
                value += TS[i].ValuePart;
                i++;
                list();
                return true;
            }
        }
        return false;
    }
}