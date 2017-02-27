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
public class String_const
{
    int [][] TT = {
        {1,2,1},
        {1,2,1},
        {3,1,1},
        {3,3,3}    
                    };
    
    public boolean ID(java.lang.String temp){
        int state= 0;
        int FS1= 1;
        int FS2= 0;
        temp=temp.toLowerCase();
        for (int i=0;i<temp.length();i++ )
        {
           
            state= transID(state,temp.charAt(i));

        }
        
        if (state==FS1||state == FS2)
        return true;
        else return false;
    }
    int transID(int state, char ch)
    {
        if ((ch>='a' && ch<='z')||ch==' ')
                return TT [state][0];
        if ((ch>='0' && ch<='9'))
                return TT [state][0];
        else if ((ch=='\\'))
            return TT [state][1];
        else if ((ch=='b')||(ch=='n')||(ch=='t')||(ch=='r')||(ch=='f')||(ch=='\'')||(ch=='\"')||(ch=='\\'))
            return TT [state][2];
        else if ((ch=='!')||(ch=='@')||(ch=='$')||(ch=='#')||(ch=='%')||(ch=='^')||(ch=='&')||(ch=='*')||(ch=='(')||(ch==')')||(ch=='{')||(ch=='}')||(ch=='[')||(ch==']')||(ch=='+')||(ch=='-')||(ch=='/')||(ch=='<')||(ch=='>')||(ch==',')||(ch=='.')||(ch=='?')||(ch=='~')||(ch==':')||(ch=='=')||(ch==';')||(ch=='.')||(ch=='`'))
            return TT [state][0];
        
        
        else return 3;
    }    

}
    

