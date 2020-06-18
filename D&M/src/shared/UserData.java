package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    ArrayList<Player> playerArrayList = new ArrayList<>();
    String inrto;
    String bitmap;


    public UserData(){}

    public void setid(String id) { this.id = id; }

    public String getid(){ return id; }

    public void setTeamdata(ArrayList arrayList){ this.playerArrayList = arrayList; }

    public ArrayList getTeamdata(){ return playerArrayList; }

    public void setintro(String intro){
        this.inrto = intro;
    }
    
    public String getInrto(){
        return inrto;
    }
    
    public void setBitmap(String bitmap){
        this.bitmap = bitmap;
    }

    public String getBitmap(){
        return bitmap;
    }
    
    public int Teampoint() {
    	int teampoint = 0;
    	for(int i = 0 ; i< playerArrayList.size(); i++) {
    		teampoint += playerArrayList.get(i).stat();
    	}    	
    	return teampoint;
    }
}
