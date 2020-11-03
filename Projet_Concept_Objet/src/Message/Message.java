package Message;

public class Message {
	private String content;
	private int poids;
	
	public Message() {
		setContent("Hello!");
	}
	public Message (String content, int valeur) {
		setContent(content);
		setPoids(valeur);
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}
}
