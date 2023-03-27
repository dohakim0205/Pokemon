package Zombie_Game;

import java.util.ArrayList;
import java.util.Vector;

class Play {

	private Hero hero;
	private Zombie zombie;
	private Boss boss;
	private int[][] map;
	private final int SIZE;
	private final int ROAD;
	private final int PLAYER;
	private final int WALL;
	private ArrayList<Zombie> monster;

	public Play() {
		this.monster = new ArrayList<Zombie>();
		this.boss = new Boss();
		makeZombie();
		System.out.println(this.monster.get(0).getName());
		this.zombie = this.monster.get(this.monster.size() - 1);
		this.SIZE = 10;
		this.ROAD = 0;
		this.PLAYER = 1;
		this.WALL = 2;
		this.hero = new Hero();
		if (this.zombie.getPos() == this.boss.getPos()) {
			zombie = new Zombie();
		}
		this.map = new int[this.SIZE][this.SIZE];
		setMap();
	}

	private void setMap() {
		int wallCount = Unit.r.nextInt(7) + 7;
		for (int i = 0; i < wallCount; i++) {
			int x = Unit.r.nextInt(SIZE);
			int y = Unit.r.nextInt(SIZE);
			if (map[y][x] == ROAD) {
				map[y][x] = WALL;
			}

			else {
				i--;
			}
		}

		while (true) {
			int rY = Unit.r.nextInt(SIZE - 2) + 1;
			int rX = Unit.r.nextInt(SIZE - 2) + 1;

			if (map[rY][rX] == ROAD) {
				hero.setpY(rY);
				hero.setpX(rX);
				map[rY][rX] = PLAYER;
				break;
			}
		}
	}

	private void printMap() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (map[i][j] == ROAD) {
					System.out.print("  ▪ ");
				}

				if (map[i][j] == WALL) {
					System.out.print(" 🥦 ");
				}

				if (map[i][j] == PLAYER) {
					System.out.print(" 🏃‍♂️ ");
				}
			}
			System.out.println();
		}
	}

	private void move() {
		printMap();
		System.out.println("이동(awsd) : ");
		String move = Main.scan.next();
		if (move.equals("a") || move.equals("w") || move.equals("s") || move.equals("d")) {

			int ud = 0;
			int lr = 0;

			if (move.equals("w")) {
				ud--;
			}

			else if (move.equals("s")) {
				ud++;
			}

			else if (move.equals("a")) {
				lr--;
			}

			else if (move.equals("d")) {
				lr++;
			}

			int tempX = hero.getpX() + lr;
			int tempY = hero.getpY() + ud;

			if (tempX >= SIZE || tempX < 0 || tempY >= SIZE || tempY < 0 || map[tempY][tempX] == WALL) {
				System.out.println("여긴 벽이구나");
				return;
			}

			map[hero.getpY()][hero.getpX()] = ROAD;
			hero.setpY(tempY);
			hero.setpX(tempX);
			map[hero.getpY()][hero.getpX()] = PLAYER;
			hero.setPos(hero.getPos() + 1);
		}
	}

	private void addFriend() {
		Vector<Unit> temp = this.hero.getFriends();
		temp.add(this.zombie);
		this.hero.setFriends(temp);
	}

	private void meetGorapaduck() {
		System.out.println("[으악....!]");
		Play.delay();
		System.out.println("[너무 바보 같아...]");
		Play.delay();
		System.out.println("[고라파덕이 따라 온다]");
		Play.delay();
		addFriend();
		System.out.println("동료가 생겼습니다 ! - 고라파덕");
	}

	private void meetTogephy() {
		System.out.println("[으악....!]");
		Play.delay();
		System.out.println("[너무 귀여워...]");
		Play.delay();
		System.out.println("[토게피를 안고 가기로 했다]");
		Play.delay();
		addFriend();
		System.out.println("동료가 생겼습니다 ! - 귀여운 토게피");
	}

	private Unit ismatchEnemy() {
		if (this.hero.getPos() == this.zombie.getPos()) {
			System.out.printf("[!%s이(가) 나타났다!]\n", this.zombie.getName());
			if (this.zombie.getName().equals("귀여운 토게피")) {
				meetTogephy();
				return null;
			}

			if (this.zombie.getName().equals("고라파덕")) {
				meetGorapaduck();
				return null;
			}
			return this.zombie;
		}

		else if (this.hero.getPos() == this.boss.getPos()) {
			System.out.printf("[!%s이(가) 나타났다!]\n", this.boss.getName());
			return this.boss;
		}

		return null;
	}

	private void fightOrHeal(Unit enemy) {
		while (true) {
			delay();
			System.out.println("1) 공격하기 2) 포션 마시기 3) *자동전투*");
			int input = Main.CheckInt();
			if (input == 1) {
				if (fight(enemy))
					return;
			}

			else if (input == 2) {
				heal();
			}

			else if (input == 3) {
				while (!fight(enemy)) {
					if (this.hero.getHp() < 50) {
						heal();
					}
				}

				return;
			}
		}
	}

	private boolean fight(Unit enemy) {
		enemy.attack(this.hero);
		this.hero.attack(enemy);
		if (enemy.getHp() <= 0) {
			heroWin();
			return true;
		}

		if (this.hero.getHp() <= 0) {
			enemyWin();
			return true;
		}

		return false;
	}

	public static void delay() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void heroWin() {
		delay();
		System.out.println("히어로가 이겼습니다!");
		delay();
		System.out.println("히어로의 공격력이 5 늘어납니다.");
		this.hero.setPower(this.hero.getPower() + 5);
		System.out.println("[공격력 : " + this.hero.getPower() + "]");
		if (this.boss.getHp() <= 0) {
			this.boss = new Boss();
		}

		else if (this.zombie.getHp() <= 0) {
			makeZombie();
		}
		this.hero.setPos(0);
	}

	private void makeZombie() {
		String[] monsters = new String[] { "ggobuk", "pairi", "ggomadol", "mazayong", "togephi", "gorapaduck" };
		String className = "Zombie_Game." + monsters[Unit.r.nextInt(monsters.length)];

		try {
			Class<?> clazz = Class.forName(className);
			Object object = clazz.getDeclaredConstructor().newInstance();
			if (object instanceof Zombie) {
				Zombie zombiee = (Zombie) object;
				this.monster.add(zombiee);
				this.zombie = this.monster.get(this.monster.size() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (this.boss.getPos() == this.zombie.getPos()) {
			makeZombie();
		}
	}

	private void enemyWin() {
		delay();
		System.out.println("히어로가 죽었습니다... GAME OVER");
	}

	private void heal() {
		if (this.hero.getCount() == 0) {
			System.out.println("더이상 먹을 수 있는 포션이 없습니다");
			return;
		}
		this.hero.setHp(this.hero.getHp() + 100);
		this.hero.setCount(this.hero.getCount() - 1);
		System.out.println("포션을 먹었습니다. 현재 체력 : " + this.hero.getHp() + ", 남은 포션 : " + this.hero.getCount());
	}

	public void run() {

		while (true) {
			move();
			Unit enemy = ismatchEnemy();
			if (enemy != null) {
				fightOrHeal(enemy);
				if (enemy.getHp() > 0)
					break;
			}
		}
	}
}
