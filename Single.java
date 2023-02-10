
public class Single extends FoodBaseInfo{
	protected int id; 
	protected String type;    
	protected boolean breakfast=false;
	public Single(int id, String name,int price,String type,boolean breakfast){
		super(name,price);
		setID(id);
		setType(type);	
		setBF(breakfast);
	}
	//SERVICE METHOD
	public void setType(String type){	this.type=type;}
	public void setBF(boolean breakfast){	this.breakfast=breakfast;}
	public void setID(int id){	this.id=id;}
	public String getType(){	return type;}
	public boolean getBF(){	return breakfast;}
	public int getID(){	return id;}
	/*
	test
	*/
	public String toString(){
		return String.format("%2d\t%10s\t%d\t%d\t%5d",id,name,price,num,price*num);
	}

}
