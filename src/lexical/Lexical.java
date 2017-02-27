/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Lab User
 */
public class Lexical {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        PrintWriter fileWriter = new PrintWriter(new BufferedWriter (new FileWriter("lexical_output.txt")));        
        
        Scanner bolReader = new Scanner (new BufferedReader (new FileReader("lexical.bol")));
        String temp = new String();
        int num = 1;
        Identifiers id = new Identifiers();
        Characters ch = new Characters();
        Int_const in = new Int_const();
        
        float_const fc = new float_const();
        
        while(bolReader.hasNextLine()){
            String line = bolReader.nextLine();
            int i=0;
           
            while(i<line.length()){
                temp = temp + line.charAt(i);
                temp = temp.toLowerCase();
                if(line.charAt(i)==32||line.charAt(i)==','){
                        i++;
                        temp = "";
                        continue;
                    }
                if(temp.charAt(0)>=48&&temp.charAt(0)<=57){
                    
                    if(i<line.length()-1 && (line.charAt(i+1)>='0' && line.charAt(i+1)<='9')||line.charAt(i+1)=='.' ||line.charAt(i+1)=='e'){
                        if(has(temp)&&line.charAt(i+1)=='.'){
                            if(fc.ID(temp)){
                              fileWriter.println("(float ,"+temp+" , "+num + " )");
                              temp = "";
                               i++;
                               continue;
                            }
                        }
                        i++;
                        continue;
                    }
                    if(in.ID(temp)){
                        fileWriter.println("(intConst , "+temp+" , "+num + " )");
                        temp = "";
                        i++;
                        continue;
                    }
                    
                    if(fc.ID(temp)){
                        fileWriter.println("(float ,"+temp+" , "+num + " )");
                        temp = "";
                        i++;
                        continue;
                    }
                    
                    fileWriter.println("Undefined Constant term");
                }
                
                if(i<line.length()-1&&(line.charAt(i)=='\'')){
                    temp = "";
                    i++;
                    temp = temp +  line.charAt(i);
                    
                    if(line.charAt(i)=='\\'){
                        if(line.charAt(i+1)=='\\'||line.charAt(i)=='n'||line.charAt(i)=='b'||line.charAt(i)=='t'||line.charAt(i)=='r'){
                            i++;
                            temp =temp +  line.charAt(i);
                        }
                        else{
                            
                            fileWriter.println("Undefine escape Char Constant "+temp +line.charAt(i+1)+" at line num "+num);
                            
                            continue;
                        }
                    }
                    if(line.charAt(i+1)=='\''){
                        i=i+2;
                        
                    }
                    else{
                        fileWriter.println("Undefined Char Constant "+temp+" at line num "+num +"\n");
                            
                        continue;
                        
                    }
                    
                    if(ch.ID(temp)){
                        fileWriter.println("(charConst , "+temp+" , "+num + " )");
                        
                    }
                    temp = "";
                    continue;
                    
                }
                temp = temp.trim();
                if(temp.matches("[\"][a-z|A-Z|0-9|!|#|$|%}^|&}*|(|)]*[\"]")){
                    temp = temp.replace('\"', ' ');
                    temp = temp.trim();
                    fileWriter.println("(String_const , "+temp+" , "+num + " )");
                    temp = "";
                    i++;
                    continue;
                }
                if(id.ID(temp)){
                    
                    if(temp.equals("khlaa")){
                    fileWriter.println("(khlaa_cls , - , "+num + " )");
                    temp = "";
                    i++;
                    continue;
                }
                    if(temp.equals("eham")){
                        fileWriter.println("(eham_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("aam")){
                        fileWriter.println("(AccMod , aam , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("zati")){
                        fileWriter.println("(AccMod , zati , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("mehfuz")){
                        fileWriter.println("(AccMod , mehfuz , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("naqsha")){
                        fileWriter.println("(naqsha_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("khyal")){
                        fileWriter.println("khyal_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("sakin")){
                        fileWriter.println("(sakin_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("ewaz")){
                        fileWriter.println("(ewaz_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("brhae")){
                        fileWriter.println("(brhae_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("kro")){
                        fileWriter.println("(kro_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("jbtk")){
                        fileWriter.println("(jbtk_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("isme")){
                        fileWriter.println("(isme_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }

                    if(temp.equals("ye")){
                        fileWriter.println("(ye_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("lautao")){
                        fileWriter.println("(lautao_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("jehd")){
                        fileWriter.println("(jehd_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("pakro")){
                        fileWriter.println("(pakro_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("naya")){
                        fileWriter.println("(naya_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("brtr")){
                        fileWriter.println("(brtr_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("badlo")){
                        fileWriter.println("(badlo_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("warna")){
                        fileWriter.println("(warna_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(temp.equals("agr")){
                        fileWriter.println("(agr_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }

                    if(temp.equals("warna-agr")){
                        fileWriter.println("(warna-agr_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
//                    if(i<line.length()-1&&(line.charAt(i+1)>='a'&&line.charAt(i+1)<='z')||line.charAt(i+1)=='_'){
//                        i++;
//                        continue;
//                    }
//                    else{
//                        fileWriter.println("(identifier ,"+temp+", "+num+" )");
//                        temp="";
//                        i++;
//                        continue;
//                    }
                    
                }
                    if(i<line.length()){
                    
                    if(line.charAt(i)==32||line.charAt(i)=='.'||line.charAt(i)==','||line.charAt(i)==' '){
                        i++;
                        temp = "";
                        continue;
                    }
                    if(line.charAt(i)=='('){
                        fileWriter.println("(Open.Paran , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==')'){
                        fileWriter.println("(Clse.Paran , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='{'){
                        fileWriter.println("(Open.Curvy , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='}'){
                        fileWriter.println("(Clse.Curvy , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='['){
                        fileWriter.println("(Open.Sqr , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==']'){
                        fileWriter.println("(Clse.Sqr , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='+'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='+'){
                        fileWriter.println("(INCDEC , ++ , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(ADDSUB , + , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='\\'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='\\'){
                        temp = "";
                        num++;
                        break;
                        }
                        continue;
                    }
                    if(line.charAt(i)=='<'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(RO , <= , "+num+" )");
                        i++;
                        }
                        else if(line.charAt(i)=='-'){
                            i++;
                            if(line.charAt(i)=='-'){
                                boolean breaker=false;
                                do{
                                    while(i<line.length()){
                                        if(line.charAt(i)=='-'){
                                            i++;
                                            if(line.charAt(i)=='-'&&i<line.length()-1){
                                                i++;
                                                if(line.charAt(i)=='>'&&i<line.length()-2){
                                                    breaker=true;
                                                }
                                            }
                                        }
                                        i++;
                                    }
                                    if(bolReader.hasNext()){
                                        line=bolReader.nextLine();
                                    }
                                    else{
                                        breaker=true;
                                    }
                                }
                                while(breaker==false);
                            }
                        }
                        else{
                            fileWriter.println("(RO , < , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='>'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(RO , >= , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(RO , > , "+num+" )");
                        }
                        continue;
                    }
                    
                        
                    if(line.charAt(i)=='!'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(RO , != , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(NOT , ! , "+num+" )");
                        }
                        continue;
                    }
                    
                    if(line.charAt(i)=='&'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='&'){
                        fileWriter.println("(AND , && , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(BIT , & , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='|'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='|'){
                        fileWriter.println("(OR , += , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(BIT , | , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='='){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(RO , == , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(EQ , = , "+num+" )");
                        }
                        continue;
                    }
                    
                    if(line.charAt(i)=='+'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(ASGN_OP , += , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(ADDSUB , + , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='*'){
                        temp = "";
                        i++;
                        if(i<line.length()&&line.charAt(i)=='='){
                        fileWriter.println("(ASGN_OP , *= , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(MULDIV , * , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='/'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(ASGN_OP , /= , "+num+" )");
                        i++;
                        }
                        else if(line.charAt(i)=='/'){
                            
                            break;
                        }
                        else{
                            fileWriter.println("(MULDIV , / , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='%'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(Arrow_cls , %= , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(Mod , % , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)==','){
                        fileWriter.println("(comma_cls , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='-'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='>'){
                        fileWriter.println("(Arrow_cls , -> , "+num+" )");
                        i++;
                        }
                        else if(line.charAt(i)=='-'){
                        fileWriter.println("(INCDEC , -- , "+num+" )");
                        i++;
                        }
                        else if(line.charAt(i)=='='){
                        fileWriter.println("(ASGN_OP , -= , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(ADDSUB , - , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)==';'||line.charAt(i)=='\\'){
                        temp = "";
                        num++;
                        break;
                    }
                    }
                i++;
            }
            temp ="";
            num++;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        //End of Program
        fileWriter.close();
        bolReader.close();
        
        
        
        Scanner fileReader = new Scanner (new BufferedReader (new FileReader("lexical_output.txt")));
        while(fileReader.hasNext()){
            System.out.println(fileReader.nextLine());
        }
        fileReader.close();
//        
    }
    static boolean has(String temp){
        char[] t = temp.toCharArray();
        for(int j =0;j<temp.length() ; j++){
                     
        if(t[j]=='.'){
            return true;
        }
        }
        return false;
    }
}
