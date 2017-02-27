/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

/**
 *
 * @author 123
 */
public class String_const1
{
    int [][] TT = {
        {0,1,0},
        {2,0,0},
        {2,2,2}    
                    };
    
    public boolean ID(java.lang.String temp){
        int state= 0;
        int FS= 0;
        temp=temp.toLowerCase();
        for (int i=0;i<temp.length();i++ )
        {
            state= transID(state,temp.charAt(i));
        }
        
        if (state==FS)
        return true;
        else return false;
    }
    int transID(int state, char ch)
    {
        if ((ch=='\\'))
            return TT [state][1];
        if ((ch=='b')||(ch=='n')||(ch=='t')||(ch=='r')||(ch=='f')||(ch=='\'')||(ch=='\"')||(ch=='\\'))
            return TT [state][2];
        
        return 0;
    }    

}
    

