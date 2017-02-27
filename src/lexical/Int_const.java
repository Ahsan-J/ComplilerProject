
package lexical;

/**
 *
 * @author Lab User
 */
public class Int_const {
    int [][] TT = {
        {1,1,1,2},
        {3,3,3,2},
        {3,3,3,2},
        {3,3,3,3}    
                    };
    
    public boolean ID(String temp){
        int state= 0;
        int FS= 2;
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
        if ((ch=='+'))
                return TT [state][0];
        if ((ch=='-'))
        return TT [state][1];
        else if ((ch=='_'))
        return TT [state][2];

        else if ((ch>='0'&&ch<='9'))
        return TT [state][3];
        else return 3;
    }
}
