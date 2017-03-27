/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

import lexical.Syntax.Tokens;

/**
 *
 * @author Xcalaiberz
 */
public class CFG {
    private Tokens TS = null;
//    public CFG(Tokens TokenSet){
//        this.TS=TokenSet;
//    }
    public boolean AccMod(Tokens Token){
        if(Token.ClassPart.equalsIgnoreCase("AccMod")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean DT(Tokens Token){
        if(Token.ClassPart.equalsIgnoreCase("int")||Token.ClassPart.equalsIgnoreCase("float")||Token.ClassPart.equalsIgnoreCase("char")||Token.ClassPart.equalsIgnoreCase("string")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean M_ID(Tokens Token){
        if(Token.ClassPart.equalsIgnoreCase("comma")||Token.ClassPart.equalsIgnoreCase("=")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean ID(Tokens Token){
        if(Token.ClassPart.equalsIgnoreCase("indentifier")||Token.ClassPart.equalsIgnoreCase("identifiers")||Token.ClassPart.equalsIgnoreCase("ID")){
            return true;
        }
        else{
            return false;
        }
    }
}
