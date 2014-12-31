
public class TestRectangle {

	public static void main(String[] args) {
		Rectangle r = new Rectangle(4,5);
		Square s = new Square(5);
		
		System.out.println("1." + r.toString()); // which toString does it use?
		System.out.println("2." + s.toString()); // which toString does it use?
		
		Rectangle r2 = s;
		System.out.println("3." + r2.toString());// which toString does it use?
		
		Rectangle t = new Square(6);
		System.out.println("4." + t.toString());
		
		//Square s2 = new Rectangle(7,8);  // why does compiler complain?  
		
		r = new Square(5);
		System.out.println("5." + r.toString());  // which toString does it use?
		
		System.out.println("6. width " + r.getWidth());  // why is this OK?
		
		//System.out.println(r.getSide()); // why does compiler complain?
				
		System.out.println(( (Square)r ).getSide()); // this is OK
		
	}

}
