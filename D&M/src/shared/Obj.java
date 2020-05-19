package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Obj implements Serializable {

    private static final long serialVersionUID = 1L;

    ArrayList<Player> arrayList = null;
    Object object;
    String str = null;
    int code;

    public Obj(Object object, int code) {
        this.object = object;
        this.code =code;

        if (object instanceof ArrayList)
            arrayList = (ArrayList<Player>) object;

        if (object instanceof String)
            str = (String) object;
    }

    public Obj(){

    }

    //code == 2
    public ArrayList<Player> getarray() {
        if (arrayList != null)
            return arrayList;
        else
            return null;
    }

    //code == 1
    public String getstr() {
        if (str != null)
            return str;
        else
            return "";
    }

    public int getcode(){
        return code;
    }


}
