/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

/**
 *
 * @author Xcalaiberz
 */
public class Tokens {
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
