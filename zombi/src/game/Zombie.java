package game;

public class Zombie extends Unit {
	private int power;
	
	public Zombie(int pos,int hp,int max) {
		super(pos,hp,max);
	}
	
	public void attack(Unit hero) {
		power = r.nextInt(max)+1;
		
		hero.setHp(hero.getHp()-power);
		if(hero.getHp() <= 0) {
			hero.setHp(0);
		}
		
		this.setHp(this.getHp()+power/2);
		
		System.out.println("좀비가 "+ power + "의 공격력으로 공격 :"
				+ " 현재 Hero hp : "+ hero.getHp()+",좀비 체력 회복 " + this.getHp()); 
	}
}