
package lexical;

/**
 *
 * @author Lab User
 */
public class Characters {
            
    int [][] TT = {
        {1,5,5,5},
        {5,2,3,2},
        {4,5,5,5},
        {5,5,2,2},
        {5,5,5,5},
        {5,5,5,5}    
                    };
    
    public boolean ID(String temp){
        int state= 0;
        int FS= 4;
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
        if (ch=='\'')
                return TT [state][0];
        if ((ch>='a' && ch<='z')||(ch>='A' && ch<='Z'))
                return TT [state][1];
        if ((ch>='0' && ch<='9'))
                return TT [state][1];
        else if ((ch=='\\'))
            return TT [state][2];
        else if ((ch=='b')||(ch=='n')||(ch=='t')||(ch=='r')||(ch=='f')||(ch=='\'')||(ch=='\"')||(ch=='\\'))
            return TT [state][3];
        return 5;
    }  
}
