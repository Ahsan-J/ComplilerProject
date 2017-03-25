/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

import java.io.IOException;

/**
 *
 * @author Xcalaiberz
 */
public class ProgramMain {
    public static void main(String[] args) throws IOException{
        Lexical.Start(); //Phase one
        Syntax.Start();  //Phase two
    }
}
