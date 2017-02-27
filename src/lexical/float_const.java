
package lexical;

/**
 *
 * @author Lab User
 */
public class float_const {
    int [][] TT = {
      /*0*/  {2,1,1,8,3},
      /*1*/  {2,8,8,8,3},
      /*2*/  {2,8,8,5,3},
      /*3*/  {4,8,8,8,8},
      /*4*/  {4,8,8,5,8},
      /*5*/  {7,6,6,8,8},
      /*6*/  {7,8,8,8,8},
      /*7*/  {7,8,8,8,8},
      /*8*/  {8,8,8,8,8},
                    };
    
    public boolean ID(String temp){
        int state= 0;
        int FS1= 2;
        int FS2= 4;
        int FS3= 7;
        temp=temp.toLowerCase();
        
        for (int i=0;i<temp.length();i++ )
        {
            state= transID(state,temp.charAt(i));
        }
        
        if (state==FS1||state==FS2||state==FS3)
        return true;
        else return false;
    }
    int transID(int state, char ch)
    {
        if (ch>='0' && ch<='9')
            return TT [state][0];
        if (ch=='+')
            return TT [state][1];
        if (ch=='-')
            return TT [state][2];
        if (ch=='e')
            return TT [state][3];
        if (ch=='.')
        return TT [state][4];
        else return 8;
    }
}
