import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sap.aii.mapping.api.AbstractTrace;
import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;

public class JM_CopyContentsLineByLine_WithTrace extends AbstractTransformation{
	
	public static AbstractTrace trace;
	
	public void transform(TransformationInput arg0, TransformationOutput arg1) throws StreamTransformationException {
		trace = (AbstractTrace) getTrace();
		
		trace.addInfo("Info trace - Started");
		try {		
			execute(arg0.getInputPayload().getInputStream(), arg1.getOutputPayload().getOutputStream());
		}catch(Exception ee) {
			throw new StreamTransformationException(ee.toString());
		}
		
		trace.addInfo("Info trace - Ended");
	}
	
	public void execute(InputStream in, OutputStream out) throws StreamTransformationException{
		try {
			
			trace.addDebugMessage("Debug trace - Started");
			
			StringBuffer sourcePayload = new StringBuffer();
			BufferedReader docInput = new BufferedReader(new InputStreamReader(in));			
			
			String inputLine = "";
			
			trace.addDebugMessage("Debug trace - Reading the file");
			
			while( (inputLine = docInput.readLine() ) != null ) {
				sourcePayload.append(inputLine + "\r\n");
			}
			
			trace.addDebugMessage("Debug trace - Writing file");
			
			out.write( new String(sourcePayload).getBytes("UTF-8") );
			out.flush();
			
			trace.addDebugMessage("Debug trace - Completed");
			
		}catch(Exception ee) {
			
			trace.addWarning("Warning trace - Started");
			throw new StreamTransformationException(ee.toString());
		}
		
	}

	public static void main(String[] args) {
		try {
			FileInputStream fin = new FileInputStream("inputfile.txt");
			FileOutputStream fout = new FileOutputStream("outputfile.txt");			
			
			JM_CopyContentsLineByLine_WithTrace instance = new JM_CopyContentsLineByLine_WithTrace();
			instance.execute(fin, fout);
			
		}catch(Exception ee) {
			ee.printStackTrace();
		}

	}

}
