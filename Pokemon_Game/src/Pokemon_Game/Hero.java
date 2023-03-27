package Pokemon_Game;

import java.util.Vector;

public class Hero extends Unit {
	private int power;
	private int count;
	private int pY;
	private int pX;
	private Vector<Unit> friends;

	public Hero() {
		super.setPos(0);
		this.count = 100;
		this.power = Unit.r.nextInt(40) + 30;
		this.friends = new Vector<Unit>();
	}
	
	public int getpY() {
		return pY;
	}

	public int getpX() {
		return pX;
	}

	public void setpY(int pY) {
		this.pY = pY;
	}

	public void setpX(int pX) {
		this.pX = pX;
	}



	public int getPower() {
		return power;
	}

	public int getCount() {
		return count;
	}
	
	

	public Vector<Unit> getFriends() {
		return friends;
	}

	public void setFriends(Vector<Unit> friends) {
		this.friends = friends;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void attack(Unit enemy) {
		int damage = Unit.r.nextInt(this.power) + 1;
		if (enemy instanceof Boss) {
			Boss boss = (Boss) enemy;
			if (boss.getShield() != 0) {
				boss.setShield((boss.getShield() - damage) < 0 ? 0 : boss.getShield() - damage);
				Play.delay();
				System.out.println("[싸우는 중...]");
				Play.delay();
				System.err.printf("[히어로가 %d의 공격력으로 공격!]\n[현재 적의 shield : %d]\n", damage, boss.getShield());
				return;
			}
		}
		
		Play.delay();
		System.out.println("[싸우는 중...]");
		Play.delay();
		enemy.setHp((enemy.getHp() - damage) < 0 ? 0 : enemy.getHp() - damage);
		System.err.printf("[히어로가 %d의 공격력으로 공격!]\n[현재 적의 hp : %d]\n", damage, enemy.getHp());
	}

	public void recovery() {

	}
}
