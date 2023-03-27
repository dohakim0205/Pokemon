package Pokemon_Game;

import java.util.Random;

abstract class Unit {
	private int pos;
	private int hp;
	private int max;
	public static final Random r = new Random();
	
	public Unit() {
		this.pos = r.nextInt(10)+1;
		this.hp = 10 * (r.nextInt(21)+10);
		this.max = this.hp;
	}
	
	public int getPos() {
		return pos;
	}
	public int getHp() {
		return hp;
	}
	public int getMax() {
		return max;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void move() {
		pos ++;
	}
	
	public abstract void attack(Unit unit) ;
}
