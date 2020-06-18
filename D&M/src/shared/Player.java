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
        if (player.position_type.equals("Ÿ��") || player.position_type.equals("����")) {
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
        if (player.position_type.equals("Ÿ��") || player.position_type.equals("����")) {
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
        arrayList.add("��: "+this.teamname);
        arrayList.add("����: "+this.season);
        if (this.position_type.equals("����")){
            arrayList.add("ü��: "+ this.health);
            arrayList.add("����: "+this.Control);
            arrayList.add("����: "+this.ballspeed);
        }
        else{
            arrayList.add("�Ŀ�: "+ this.power);
            arrayList.add("�����: "+this.Condition);
            arrayList.add("�ӵ�: "+this.speed);
        }
        return arrayList;
    }


    @Override
    public String toString() {
        return teamname + "//" + season + "//" + name;
    }
}
