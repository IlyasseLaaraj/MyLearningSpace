import java.util.Objects;

public class Classe2 {

	private String stringa;

	public Classe2(String content) {
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
		return (object != null && object instanceof Classe1)
				? Objects.equals(this.stringa, ((Classe1) object).getStringa())
				: super.equals(object);
	}
}
