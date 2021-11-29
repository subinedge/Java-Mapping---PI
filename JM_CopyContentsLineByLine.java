import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;

public class JM_CopyContentsLineByLine extends AbstractTransformation{
	
	public void transform(TransformationInput arg0, TransformationOutput arg1) throws StreamTransformationException {
		try {		
			execute(arg0.getInputPayload().getInputStream(), arg1.getOutputPayload().getOutputStream());
		}catch(Exception ee) {
			throw new StreamTransformationException(ee.toString());
		}
	}
	
	public void execute(InputStream in, OutputStream out) throws StreamTransformationException{
		try {
			
			StringBuffer sourcePayload = new StringBuffer();
			BufferedReader docInput = new BufferedReader(new InputStreamReader(in));			
			
			String inputLine = "";
			
			while( (inputLine = docInput.readLine() ) != null ) {
				sourcePayload.append(inputLine + "\r\n");
			}
			
			out.write( new String(sourcePayload).getBytes("UTF-8") );
			out.flush();			
			
		}catch(Exception ee) {
			throw new StreamTransformationException(ee.toString());
		}		
	}

	public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream("inputfile.txt");
			FileOutputStream fout = new FileOutputStream("outputfile.txt");			
			
			JM_CopyContentsLineByLine instance = new JM_CopyContentsLineByLine();
			instance.execute(fin, fout);
			
		}catch(Exception ee) {
			ee.printStackTrace();
		}

	}

}
