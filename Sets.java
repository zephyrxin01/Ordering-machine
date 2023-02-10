public class Sets extends FoodBaseInfo{
	protected Single s;
	protected String setid; 
	protected Single food1,food2,food3;    
	public Sets(String setid, String name,int price,Single food1,Single food2,Single food3 ){
		super(name,price);
		setSetsID(setid);
		setFood1(food1);
		setFood2(food2);
		setFood3(food3);

	}
	//SERVICE METHOD
	// void setSingle(Single s){this.s=s;}
	public void setSetsID(String setid){this.setid=setid;}
	public void setFood1(Single food1){this.food1=food1;}
	public void setFood2(Single food2){this.food2=food2;}
	public void setFood3(Single food3){this.food3=food3;}
	//public Single getSingle(){return s;}
	public String getSetsID(){	return setid;}
	public Single getFood1(){return food1;}
	public Single getFood2(){return food2;}
	public Single getFood3(){return food3;}
	/*
	test
	*/
	public String toString(){
		return String.format("%2s\t%10s\t%d\t%d\t%5d",setid,name,price,num,price*num);
	}

}
