/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

/**
 *
 * @author Lab User
 */
public class Identifiers {
    public int [][] TT = {
        {1,2,2},
        {1,1,1},
        {2,2,2}
                    };
    
    public boolean ID(String temp){
        int state= 0;
        int FS= 1;
        temp=temp.toLowerCase();
        for (int i=0;i<temp.length();i++ )
        {
            state= transID(state,temp.charAt(i));
        }
        if (state==FS)
        return true;
        else return false;
    }
    private int transID(int state, char ch)
    {
        if ((ch>='a' && ch<='z')||(ch>='A' && ch<='Z'))
                return TT [state][0];
        else if ((ch>='0'&&ch<='9'))
            return TT [state][1];
        else if ((ch>='_'))
            return TT [state][2];
        else return 2;
                
                
                
                
    }
}
