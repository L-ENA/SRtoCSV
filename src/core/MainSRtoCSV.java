package core;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import trialAndOutcome.Trial;


//test123

public class MainSRtoCSV {
	

	
	public static void main(String[] args)  throws Exception{
		JOptionPane.showMessageDialog(null, "Instructions:\nPlease select one or more ReviewManager5 (.rm5) files in the next step. \n\nThe new file, called \"RAPTOR.xml\" will be saved there.\nYour original review file remains untouched.\nIf this folder already contains an XML created by RAPTOR, it will be overwritten.");
		
		
		Database d = new Database();
		d.makeList();//in Database class a list of trials in this review will be created
		
		if (d.reviewName == "") {//if reviewName is empty it indicates that only the default constructor for trial objects was called in the database class. Therefore, there are no trials included in the review that was analysed.
			System.out.println("No trials included");
		} else {
			
			 try {
				 
				 StringBuffer sb = new StringBuffer();
				 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("trial.csv"), "UTF-8"));
				 
	             sb.append("REVMAN_ID,REVIEW_TITLE,PERFBIAS_RISK,PERFBIAS_JUDGEMENT,YEAR");
	             bw.write(sb.toString());
	             sb.setLength(0);
	             bw.newLine();
		           
		           for (Trial trial : d.trialList){
		        	   System.out.println("hiiiii");
		               sb.append(d.adjust(trial.getRevManID()));
		               sb.append(",");
		               sb.append(d.adjust(trial.getReviewTitle()));
		               sb.append(",");
		               sb.append(d.adjust(trial.getCharObject().getPerformanceBiasRisk()));
		               sb.append(",");
		               sb.append(d.adjust(trial.getCharObject().getPerformanceBiasJudgement()));
		               sb.append(",");
		               sb.append(d.adjust(trial.getCharObject().getMainYear()));
		               bw.write(sb.toString());
		               sb.setLength(0);
		               bw.newLine();
		           }
		           bw.flush();
		           bw.close();
		       }
		        catch (UnsupportedEncodingException e) {}
		        catch (FileNotFoundException e){}
		        catch (IOException e){}
		    }
			
			String path = d.path + "\\RAPTOR.csv";
			//jaxbMarshaller.marshal(d, new File(path));	//puts database into a xml file that is saved according to path String
			
			System.out.println(d.path);
			System.out.println("File created successfully");
			System.out.println(Trial.counter);
			JOptionPane.showMessageDialog(null, "Extraction finished successfully");
		}
		
	
		
	
	
	
	
}

