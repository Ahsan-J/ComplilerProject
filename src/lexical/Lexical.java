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
    public static void Start() throws FileNotFoundException, IOException {
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
                if(i<line.length()){
                    if(line.charAt(i)=='('){
                        fileWriter.println("( ( , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==')'){
                        fileWriter.println("( ) , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='{'){
                        fileWriter.println("( { , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='}'){
                        fileWriter.println("( } , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='['){
                        fileWriter.println("( [ , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==']'){
                        fileWriter.println("( ] , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='+'){
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
                    if(line.charAt(i)=='/'){
                        i++;
                        if(line.charAt(i)=='/'){
                        temp = "";
                        num++;
                        break;
                        }
                        continue;
                    }
                    if(line.charAt(i)=='<'){
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
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(RO , != , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(! , - , "+num+" )");
                        }
                        continue;
                    }
                    
                    if(line.charAt(i)=='&'){
                        i++;
                        if(line.charAt(i)=='&'){
                        fileWriter.println("(&&, - , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(&, - , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='|'){
                        i++;
                        if(line.charAt(i)=='|'){
                        fileWriter.println("(OR , += , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(|, - , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='='){
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(RO , == , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("( =, - , "+num+" )");
                        }
                        continue;
                    }
                    
                    if(line.charAt(i)=='+'){
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
                        i++;
                        if(i<line.length()&&line.charAt(i)=='='){
                        fileWriter.println("(ASGN_OP , *= , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(*, - , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='/'){
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(ASGN_OP , /= , "+num+" )");
                        i++;
                        }
                        else if(line.charAt(i)=='/'){
                            
                            break;
                        }
                        else{
                            fileWriter.println("(DIVMULL , / , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='%'){
                        i++;
                        if(line.charAt(i)=='='){
                        fileWriter.println("(ASGN_OP , %= , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(MOD , % , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)==','){
                        fileWriter.println("( , , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='-'){
                        i++;
                        if(line.charAt(i)=='>'){
                        fileWriter.println("( ->, - , "+num+" )");
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
                }
                switch(line.charAt(i)){   
                        case '.':
                            if(has(temp)){
                            if(fc.ID(temp)){
                              fileWriter.println("(float ,"+temp+" , "+num + " )");
                              temp = "";
                               i++;
                               continue;
                            }
                            }
                            if(i<line.length()-1&&line.charAt(i+1)>='0'&&line.charAt(i+1)<='9'){
                            temp = temp + line.charAt(i);
                            i++;
                            continue;
                            }
                            
                        case ' ':
                        case ',':
                        case '<':
                        case '>':
                        case '[':
                        case ']':
                        case '!':
                        case '=':
                        case '{':
                        case '}':    
                        case '\'':
                            break;
                        case '-':
                        if (line.charAt(i+1) =='>'){
                            i++;
                            break;    
                        }
                        break;
                        case '|':
                        if (line.charAt(i+1) =='|'){
                         i++;
                         break;
                        }
                        break;
                        default:
                            temp = temp + line.charAt(i);
                            i++;
                            if(!(i<line.length())){
                                break;
                            }
                            continue;
                    }
                temp = temp.toLowerCase();
                temp = temp.trim();
//                System.out.println(temp);
               
                    
//                    if(i<line.length()-1 && (line.charAt(i+1)>='0' && line.charAt(i+1)<='9')||line.charAt(i+1)=='.' ||line.charAt(i+1)=='e'){
//                        
//                        i++;
//                        continue;
//                    }
                
                if(!temp.isEmpty()&&temp.charAt(0)>='0'&&temp.charAt(0)<='9'){
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
                    fileWriter.println("Invalid DataType Numer "+temp);
                }
                
                    if(ch.ID(temp)){
                        temp = temp.replace('\'' , ' ');
                        temp.trim();
                        fileWriter.println("(charConst , "+temp+" , "+num + " )");
                        temp = "";
                        i++;
                        continue;
                    }
                    
                    
                
                
                if(temp.matches("[\"][[a-z|A-Z|0-9|@|!|#|$|%}^|&}*|(|)]+[\\\"]]*[\"]")){
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
                    fileWriter.println("(Identifier ,"+temp+", "+num+" )");
                        temp = "";
                        i++;
                        continue;
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
                else{
                    fileWriter.println("Invalid expression "+temp);
                }
//               if(i<line.length()){
//                    
//                    if(line.charAt(i)=='('){
//                        fileWriter.println("( ( , - , "+num+" )");
//                        temp = "";
//                        i++;
//                        continue;
//                    }
//                    if(line.charAt(i)==')'){
//                        fileWriter.println("( ) , - , "+num+" )");
//                        temp = "";
//                        i++;
//                        continue;
//                    }
//                    if(line.charAt(i)=='{'){
//                        fileWriter.println("( { , - , "+num+" )");
//                        temp = "";
//                        i++;
//                        continue;
//                    }
//                    if(line.charAt(i)=='}'){
//                        fileWriter.println("( } , - , "+num+" )");
//                        temp = "";
//                        i++;
//                        continue;
//                    }
//                    if(line.charAt(i)=='['){
//                        fileWriter.println("( [ , - , "+num+" )");
//                        temp = "";
//                        i++;
//                        continue;
//                    }
//                    if(line.charAt(i)==']'){
//                        fileWriter.println("( ] , - , "+num+" )");
//                        temp = "";
//                        i++;
//                        continue;
//                    }
//                    if(line.charAt(i)=='+'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='+'){
//                        fileWriter.println("(INCDEC , ++ , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(ADDSUB , + , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='\\'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='\\'){
//                        temp = "";
//                        num++;
//                        break;
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='<'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='='){
//                        fileWriter.println("(RO , <= , "+num+" )");
//                        i++;
//                        }
//                        else if(line.charAt(i)=='-'){
//                            i++;
//                            if(line.charAt(i)=='-'){
//                                boolean breaker=false;
//                                do{
//                                    while(i<line.length()){
//                                        if(line.charAt(i)=='-'){
//                                            i++;
//                                            if(line.charAt(i)=='-'&&i<line.length()-1){
//                                                i++;
//                                                if(line.charAt(i)=='>'&&i<line.length()-2){
//                                                    breaker=true;
//                                                }
//                                            }
//                                        }
//                                        i++;
//                                    }
//                                    if(bolReader.hasNext()){
//                                        line=bolReader.nextLine();
//                                    }
//                                    else{
//                                        breaker=true;
//                                    }
//                                }
//                                while(breaker==false);
//                            }
//                        }
//                        else{
//                            fileWriter.println("(RO , < , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='>'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='='){
//                        fileWriter.println("(RO , >= , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(RO , > , "+num+" )");
//                        }
//                        continue;
//                    }
//                    
//                        
//                    if(line.charAt(i)=='!'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='='){
//                        fileWriter.println("(RO , != , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(! , - , "+num+" )");
//                        }
//                        continue;
//                    }
//                    
//                    if(line.charAt(i)=='&'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='&'){
//                        fileWriter.println("(&&, - , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(&, - , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='|'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='|'){
//                        fileWriter.println("(OR , += , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(|, - , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='='){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='='){
//                        fileWriter.println("(RO , == , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("( =, - , "+num+" )");
//                        }
//                        continue;
//                    }
//                    
//                    if(line.charAt(i)=='+'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='='){
//                        fileWriter.println("(ASGN_OP , += , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(ADDSUB , + , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='*'){
//                        temp = "";
//                        i++;
//                        if(i<line.length()&&line.charAt(i)=='='){
//                        fileWriter.println("(ASGN_OP , *= , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(*, - , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='/'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='='){
//                        fileWriter.println("(ASGN_OP , /= , "+num+" )");
//                        i++;
//                        }
//                        else if(line.charAt(i)=='/'){
//                            
//                            break;
//                        }
//                        else{
//                            fileWriter.println("(DIVMOD , / , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)=='%'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='='){
//                        fileWriter.println("(ASGN_OP , %= , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(DIVMOD , % , "+num+" )");
//                        }
//                        continue;
//                    }
//                    if(line.charAt(i)==','){
//                        fileWriter.println("( , , - , "+num+" )");
//                        temp = "";
//                        i++;
//                        continue;
//                    }
//                    if(line.charAt(i)=='-'){
//                        temp = "";
//                        i++;
//                        if(line.charAt(i)=='>'){
//                        fileWriter.println("( ->, - , "+num+" )");
//                        i++;
//                        }
//                        else if(line.charAt(i)=='-'){
//                        fileWriter.println("(INCDEC , -- , "+num+" )");
//                        i++;
//                        }
//                        else if(line.charAt(i)=='='){
//                        fileWriter.println("(ASGN_OP , -= , "+num+" )");
//                        i++;
//                        }
//                        else{
//                            fileWriter.println("(ADDSUB , - , "+num+" )");
//                        }
//                        continue;
//                    }
//                    }
               temp="";
                i++;
            }
            temp ="";
            num++;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        //End of Program
        fileWriter.close();
        bolReader.close();
        
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
