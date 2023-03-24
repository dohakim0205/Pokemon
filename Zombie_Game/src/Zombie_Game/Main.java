package Zombie_Game;

import java.util.InputMismatchException;

// random, scanner 는 메인에서 static final 주고 끝날 때까지 불러와서 써도 됨
// 자식 클래스는 부모 클래스의 특정 기능을 받는 특이 케이스가 아닌 이상 파라미터로 부모 클래스를 적어줘도 됨
// 자주 쓰는 부모 기능은 public을 줘도 되고, private 일 때는 get으로 가져오기!

import java.util.Scanner;
public class Main {
	public static final Scanner scan = new Scanner(System.in);
	public static int CheckInt() {
		int num = -1;
		while(true) {
			try {
				num = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자를 입력하세요");
				scan.nextLine();
				continue;
			}
			
			if(num != -1)
			return num;
		}
		
	}
	
	public static void main(String[] args) {
		Play playGame = new Play();
		playGame.run();
	}
}
