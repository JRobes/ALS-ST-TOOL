import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBExample {

	public static void main(String[] args) {

		//AutodocuModelTest customer = new AutodocuModelTest();
	
		  try {
			   	File file = new File("XML/autodocu-example.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(AutodocuModelTest.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				AutodocuModelTest customer = (AutodocuModelTest) jaxbUnmarshaller.unmarshal(file);
				System.out.println(customer.isCfrpStructureTables());

		      } catch (JAXBException e) {
		    	e.printStackTrace();
		      }
	}

}
