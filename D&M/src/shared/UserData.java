package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    ArrayList<Player> playerArrayList = new ArrayList<>();


    public UserData(){}

    public void setid(String id) { this.id = id; }

    public String getid(){ return id; }

    public void setTeamdata(ArrayList arrayList){ this.playerArrayList = arrayList; }

    public ArrayList getTeamdata(){ return playerArrayList; }

}
