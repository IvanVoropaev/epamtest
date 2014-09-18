package voropaev.epamtest.entity;

public class Pair {
	
	private String name;
	private String value;
	
	public Pair() {		
	}
	
	public Pair(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Pair: + [Name: " + name + ", value: " + value +".]";
	}
	
	@Override
	public boolean equals(Object obj){
	    if (this == obj)
	        return true;
	    if (!(obj instanceof Pair))
	        return false;
	    Pair pair = (Pair) obj;
	    return ( name.equals(pair.getName()) && value.equals(pair.getValue()));
	}
}
