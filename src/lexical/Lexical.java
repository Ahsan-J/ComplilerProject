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
                switch(line.charAt(i)){   
                        case '.':
                            if(has(temp)){
                            if(fc.ID(temp)){
                              fileWriter.println("(floatConst ,"+temp+" , "+num + " )");
                              temp = "";
                               i++;
                              // continue;
                            }
                            }
                            if(i<line.length()-1&&line.charAt(i+1)>='0'&&line.charAt(i+1)<='9'){
                            temp = temp + line.charAt(i);
                            i++;
                            continue;
                            }
                        case ')':
                        case '(':
                        case ' ':
                        case ',':
                        case '<':
                        case '/':
                        case '%':
                        case '>':
                        case '[':
                        case '+':
                        case ']':
                        case '!':
                        case '=':
                        case '{':
                        case '}':    
                        case '\'':
                        case ';':
                        case ':':
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
                    }
                    
                    else if(fc.ID(temp)){
                        fileWriter.println("(floatConst ,"+temp+" , "+num + " )");
                        temp = "";
                        
                    }
                    else
                    fileWriter.println("Invalid DataType Number "+temp);
                }
                
                    if(ch.ID(temp)){
                        temp = temp.replace('\'' , ' ');
                        temp.trim();
                        fileWriter.println("(charConst , "+temp+" , "+num + " )");
                        temp = "";
                        
                    }
                    
                    
                
                
                if(temp.matches("[\"][[a-z|A-Z|0-9|@|!|#|$|%}^|&}*|(|)]+[\\\"]]*[\"]")){
                    temp = temp.replace('\"', ' ');
                    temp = temp.trim();
                    fileWriter.println("(stringConst , "+temp+" , "+num + " )");
                    temp = "";
                    
                }
                if(id.ID(temp)){
                    
                    if(temp.equals("khalaa")){
                    fileWriter.println("(khalaa , - , "+num + " )");
                    temp = "";
                }
                    if(temp.equals("eham")){
                        fileWriter.println("(eham , - , "+num+" )");
                        temp = "";
                        
                    }
                    if(temp.equals("int")||temp.equals("string")||temp.equals("float")||temp.equals("boolean")||temp.equals("char")){
                        fileWriter.println("(DT , "+temp+" , "+num+" )");
                        temp = "";
                        
                    }
                    if(temp.equals("aam")||temp.equals("zati")||temp.equals("mehfuz")){
                        fileWriter.println("(AccMod , "+temp+" , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("naqsha")){
                        fileWriter.println("(naqsha , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("khyal")){
                        fileWriter.println("khyal , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("sakin")){
                        fileWriter.println("(sakin , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("ewaz")){
                        fileWriter.println("(ewaz , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("shuru")){
                        fileWriter.println("(shuru , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("khatam")){
                        fileWriter.println("(khatam , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("brhae")){
                        fileWriter.println("(brhae , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("karo")){
                        fileWriter.println("(karo , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("jbtk")){
                        fileWriter.println("(jbtk , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("isme")){
                        fileWriter.println("(isme , - , "+num+" )");
                        temp = "";
                    }

                    if(temp.equals("ye")){
                        fileWriter.println("(ye , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("lautao")){
                        fileWriter.println("(lautao , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("jehd")){
                        fileWriter.println("(jehd , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("pakro")){
                        fileWriter.println("(pakro , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("naya")){
                        fileWriter.println("(naya , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("brtr")){
                        fileWriter.println("(brtr , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("badlo")){
                        fileWriter.println("(badlo , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("warna")){
                        fileWriter.println("(warna , - , "+num+" )");
                        temp = "";
                    }
                    if(temp.equals("agr")){
                        fileWriter.println("(agr , - , "+num+" )");
                        temp = "";
                    }

                    if(temp.equals("warna-agr")){
                        fileWriter.println("(warna-agr , - , "+num+" )");
                        temp = "";
                    }
                    if(!temp.equals("")){
                    fileWriter.println("(ID ,"+temp+", "+num+" )");
                        temp = "";
                    }
//                      if(i<line.length()-1&&(line.charAt(i+1)>='a'&&line.charAt(i+1)<='z')||line.charAt(i+1)=='_'){
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
                    temp = temp.trim();
                    if(!temp.equalsIgnoreCase(""))
                    fileWriter.println("Invalid expression "+temp);
                }
               if(i<line.length()){
                    
                    if(line.charAt(i)=='('){
                        fileWriter.println("( OB , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==')'){
                        fileWriter.println("( CB , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='{'){
                        fileWriter.println("( { , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='}'){
                        fileWriter.println("( } , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='['){
                        fileWriter.println("( [ , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==']'){
                        fileWriter.println("( ] , - , "+num+" )");
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
                        fileWriter.println("(&&, - , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(&, - , "+num+" )");
                        }
                        continue;
                    }
                    if(line.charAt(i)=='|'){
                        temp = "";
                        i++;
                        if(line.charAt(i)=='|'){
                        fileWriter.println("(|| , - , "+num+" )");
                        i++;
                        }
                        else{
                            fileWriter.println("(|, - , "+num+" )");
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
                            fileWriter.println("( =, - , "+num+" )");
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
                            fileWriter.println("(DIVMUL, - , "+num+" )");
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
                            fileWriter.println("(DIVMUL , / , "+num+" )");
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
                    if(line.charAt(i)==':'){
                        fileWriter.println("( : , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==';'){
                        fileWriter.println("( ; , - , "+num+" )");
                        i++;
                        continue;
                    }
                    if(line.charAt(i)==','){
                        fileWriter.println("( comma , - , "+num+" )");
                        temp = "";
                        i++;
                        continue;
                    }
                    if(line.charAt(i)=='-'){
                        temp = "";
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
               temp="";
                i++;
            }
            temp ="";
            num++;
        }
        //End of Program
        fileWriter.close();
        bolReader.close(); 
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
