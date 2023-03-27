package Zombie_Game;

class Zombie extends Unit {
	private int power;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public void attack(Unit hero) {
		int damage = Unit.r.nextInt(this.power) + 1;
		hero.setHp((hero.getHp() - damage) < 0 ? 0 : hero.getHp() - damage);
		System.err.printf("[%s가 %d의 공격력으로 공격!]\n[현재 Hero의 hp : %d]\n", this.name, damage, hero.getHp());
	}
}

class ggobuk extends Zombie {
	public ggobuk() {
		super.setName("꼬부기");
		super.setPower(10);
	}
}

class pairi extends Zombie {
	public pairi() {
		super.setName("파이리");
		super.setPower(20);
	}
}

class ggomadol extends Zombie {
	public ggomadol() {
		super.setName("꼬마돌");
		super.setPower(30);
	}
}

class mazayong extends Zombie {
	public mazayong() {
		super.setName("마자용");
		super.setPower(30);
	}
}

class togephi extends Zombie {
	public togephi() {
		super.setName("토게피");
		super.setPower(0);
	}
}

class gorapaduck extends Zombie {
	public gorapaduck() {
		super.setName("고라파덕");
		super.setPower(0);
	}
}
