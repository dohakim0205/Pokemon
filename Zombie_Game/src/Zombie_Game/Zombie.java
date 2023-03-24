package Zombie_Game;

class Zombie extends Unit {
	private int power;
	private String name;
	private String[] names = {"꼬부기", "파이리", "꼬마돌", "마자용", "귀여운 토게피", "고라파덕"};
	
	public Zombie() {
		this.name = this.names[Unit.r.nextInt(6)];
		this.power = Unit.r.nextInt(30)+20;
	}
	
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
		hero.setHp((hero.getHp() - damage)< 0 ? 0 : hero.getHp() - damage);
		System.err.printf("[%s가 %d의 공격력으로 공격!]\n[현재 Hero의 hp : %d]\n", this.name, damage, hero.getHp());
	}
}
