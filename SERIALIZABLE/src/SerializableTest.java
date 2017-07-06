import java.io.File;
import java.net.URL;
import java.util.List;

public class SerializableTest {
    private List<MyObject> listaObj;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//URL url = Platform.getInstanceLocation().getURL();
		String AbsolutePath = new File("").getAbsolutePath();

		File f = new File(AbsolutePath+"\\miarchivo.ser");
		if(f.exists() && !f.isDirectory()) { 
			System.out.println("Existe");
		}
		else{
			System.out.println("No Existe");
			MyObject aa = new MyObject("AAA", Math.random());
		}
				
		System.out.println(AbsolutePath);
	}

}
