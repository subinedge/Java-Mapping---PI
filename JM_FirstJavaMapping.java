
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;

public class JM_FirstJavaMapping extends AbstractTransformation{

	@Override
	public void transform(TransformationInput arg0, TransformationOutput arg1) throws StreamTransformationException {
		try{
			execute(arg0.getInputPayload().getInputStream(), arg1.getOutputPayload().getOutputStream());
		}catch(Exception ee){
			throw new StreamTransformationException(ee.toString());
		}
		
	}
	
	public void execute(InputStream in, OutputStream out) throws StreamTransformationException {
		try{
			int buffer;
			
			while((buffer = in.read()) != -1) {
				out.write(buffer);
			}
			
			out.flush();
			
		}catch(Exception ee){
			throw new StreamTransformationException(ee.toString());
		}
	}
	
	public static void main(String[] args) {
		try{
			FileInputStream fin = new FileInputStream("inputFile.txt");
			FileOutputStream fout = new FileOutputStream("outputFile1.txt");
			
			JM_FirstJavaMapping instance = new JM_FirstJavaMapping();
			instance.execute(fin, fout);
			
		}catch(Exception ee){
			ee.printStackTrace();
		}

	}

}
