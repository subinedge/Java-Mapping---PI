import java.io.OutputStream;

import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;

public class JM_ParamerizedJavaMapping extends AbstractTransformation{
	
	public void transform(TransformationInput arg0, TransformationOutput arg1) throws StreamTransformationException {
		
		try {
			String Parameter1 = arg0.getInputParameters().getString("PARAM1");
			String Parameter2 = arg0.getInputParameters().getString("PARAM2");
			String Parameter3 = arg0.getInputParameters().getString("PARAM3");
			
			OutputStream out = (OutputStream) arg1.getOutputPayload().getOutputStream();
			
			out.write(("PARAM1 = " + Parameter1 + "\r\n").getBytes());
			out.write(("PARAM2 = " + Parameter2 + "\r\n").getBytes());
			out.write(("PARAM3 = " + Parameter3 + "\r\n").getBytes());
			
			out.flush();
		}
		
		catch(Exception ee) {
			throw new StreamTransformationException("Error while reading the parameterized mapping".concat(ee.getMessage()));
		}
		
	}

	public static void main(String[] args) {
	

	}

}
