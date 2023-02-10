public abstract class FoodBaseInfo {	
	 
	protected String setid;//onlt set
	protected String name;   
	protected int num;    
	protected int price;
	protected String image; 
	public FoodBaseInfo(String name,int price){
		setName(name);
		setPrice(price);
	}
	//SERVICE METHOD
	public void setName(String name){	this.name=name;}
	public void setNum(int num){	this.num=num;}
	public void setPrice(int price){	this.price=price;}
	public void setImage(String image){	this.image=image;}
	public String getName(){	return name;}
	public int getNum(){	return num;}
	public int getPrice(){	return price;}
	public String getImage(){	return image;}

    public int totalPrice(){
    	return num * price;
    }
}
