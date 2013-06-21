
public class Player {
	public int goals;
	public String teamName;
	public String name;
	public String playerUrl;
	public String teamUrl;
	
	public Player(){
		goals=0;
		teamName=name=playerUrl=teamUrl="";
	}
	public int compareTo(Player p2) {		 
		return p2.goals-this.goals; 
	}	
}
