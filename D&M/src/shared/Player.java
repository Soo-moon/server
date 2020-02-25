package shared;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	

	public String teamname,name,position;
    public int season,power,Condition,speed,health,Control;


	public int ballspeed;

	
	public Player(String teamname , String name , String position ,int season , int power ,int Condition ,int speed , int health, int Control ,int ballspeed ){
        this.teamname = teamname;
        this.name = name;
        this.position=position;
        this.season=season;
        this.power=power;
        this.Condition=Condition;
        this.speed=speed;
        this.health=health;
        this.Control=Control;
        this.ballspeed=ballspeed;
    }

    public int stat(){
        return power+Condition+speed+health+Control+ballspeed;
    }

    public int astat(){
        return power+Condition+speed;
    }

    public  int dstat(){
        return health+Control+ballspeed;
    }
    @Override
    public String toString(){
        return teamname+"//"+season+"//"+name;
    }
}
