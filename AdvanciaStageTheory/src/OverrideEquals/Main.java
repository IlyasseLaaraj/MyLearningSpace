package OverrideEquals;

public class Main {

	public static void main(String[] args) {

		System.out.println(new Classe1("test").equals(new Classe2("test"))); // true
		System.out.println(new Classe1("test2").equals(new Classe2("test"))); // false
		System.out.println(new Classe1("test").equals(new Classe2("test2"))); // false
		System.out.println(new Classe1("test").equals(null)); // false
		System.out.println(new Classe1("test").equals(new String("test"))); // false
		System.out.println(new Classe2("test").equals(new String("test"))); // false
		

	}

}
