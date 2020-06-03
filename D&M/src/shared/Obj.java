package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Obj implements Serializable {

    private static final long serialVersionUID = 1L;

    ArrayList<Player> arrayList = null;
    Object object;
    String Message = "";
    int code;
    String id;
    UserData userData = new UserData();

    public Obj(Object object, int code) {
        this.object = object;
        this.code = code;

        if (object instanceof ArrayList)
            arrayList = (ArrayList<Player>) object;

        else if (object instanceof String)
            Message = (String) object;

        else if(object instanceof UserData)
            userData = (UserData) object;
    }
    
    public UserData getUserData() {return userData;}
    
    public ArrayList<Player> getarray() {return arrayList;}
  
    public String getstr() {return Message;}

    public String getid() { return userData.getid();}

    public int getcode() {return code;}


}
