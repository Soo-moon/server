package shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    public String teamname, name, position, position_type;
    public int season, power, Condition, speed, health, Control, ballspeed;


    public Player(String teamname, String name, String position, int season, int power, int Condition, int speed, int health, int Control, int ballspeed, String position_type) {
        this.teamname = teamname;
        this.name = name;
        this.position = position;
        this.season = season;
        this.power = power;
        this.Condition = Condition;
        this.speed = speed;
        this.health = health;
        this.Control = Control;
        this.ballspeed = ballspeed;
        this.position_type = position_type;
    }

    public int stat() {
        return power + Condition + speed + health + Control + ballspeed;
    }

    public int astat() {
        return power + Condition + speed;
    }

    public int dstat() {
        return health + Control + ballspeed;
    }

    public String getType() {
        return position_type;
    }

    public Player Success(Player player) {
        if (player.position_type.equals("타자") || player.position_type.equals("포수")) {
            player.power++;
            player.Condition++;
            player.speed++;
        } else {
            player.health++;
            player.Control++;
            player.ballspeed++;
        }
        return player;
    }
    
    public Player Fail(Player player) {
        if (player.position_type.equals("타자") || player.position_type.equals("포수")) {
            player.power--;
            player.Condition--;
            player.speed--;
        } else {
            player.health--;
            player.Control--;
            player.ballspeed--;
        }
        return player;
    }

   
	public ArrayList addlist(ArrayList arrayList){
        arrayList.add("팀: "+this.teamname);
        arrayList.add("시즌: "+this.season);
        if (this.position_type.equals("투수")){
            arrayList.add("체력: "+ this.health);
            arrayList.add("제구: "+this.Control);
            arrayList.add("구속: "+this.ballspeed);
        }
        else{
            arrayList.add("파워: "+ this.power);
            arrayList.add("컨디션: "+this.Condition);
            arrayList.add("속도: "+this.speed);
        }
        return arrayList;
    }


    @Override
    public String toString() {
        return teamname + "//" + season + "//" + name;
    }
}
