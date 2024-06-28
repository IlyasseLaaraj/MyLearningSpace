import java.util.Objects;

public class Classe1 {
	private String stringa = "stringa";

	public Classe1(String content) {
		this.stringa = content;
	}

	public String getStringa() {
		return stringa;
	}

	public void setStringa(String stringa) {
		this.stringa = stringa;
	}

	@Override
	public String toString() {
		return " [stringa=" + stringa + "]";
	}

	@Override
	public boolean equals(Object object) {
		return (object != null && object instanceof Classe2)
				? Objects.equals(this.stringa, ((Classe2) object).getStringa())
				: super.equals(object);
	}
}
