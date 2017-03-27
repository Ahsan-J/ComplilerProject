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
        Tokens[] TS=null;
        try{
        Scanner reader = new Scanner (new BufferedReader (new FileReader("lexical_output.txt")));
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
//            System.out.println(filesize);
        }
        catch(Exception e){
            System.err.println("File Not Found \nError Says"+e.getMessage());
        }
        TS = new Tokens[filesize];
        try{
            String Line = null;
            Scanner reader = new Scanner (new BufferedReader (new FileReader("lexical_output.txt")));
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
    static CFG grammer = new CFG();
    
    public static void Start() throws FileNotFoundException{
        TS = getTokens();
        
    }
    public static void decl(){
        if(grammer.DT(TS[i])){
            i++;
            if(grammer.ID(TS[i])){
                i++;
                if(grammer.M_ID(TS[i])){
                    i++;
                }
            }
        }
    }

}