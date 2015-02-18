package whatbase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Whatbase {

	public static void main(String[] args) {
		Whatbase wb=new Whatbase();
		int n;
		File in=new File("whatbase.in");
		File out=new File("whatbase.out");
        BufferedReader inbr=null;
        BufferedWriter outbw=null;
        try {
            inbr=new BufferedReader(new FileReader(in));
            outbw=new BufferedWriter(new FileWriter(out));
            String line=null;
            String[] numbers=null;
            line = inbr.readLine();
            n=Integer.parseInt(line);
            int result[]=new int[2];
            for(int i=0;i<n;i++) {
            	line = inbr.readLine();
            	numbers=line.split(" ");
            	int num[]=new int[2];
            	num[0]=Integer.parseInt(numbers[0]);
            	num[1]=Integer.parseInt(numbers[1]);
            	result=wb.solve(num);
            	outbw.write(Integer.toString(result[0])+" "+Integer.toString(result[1]));
            	outbw.newLine();
            }
            //System.out.println(result);
            //if(result==0)outbw.write("impossible");
    		//else outbw.write(Integer.toString(result));
            outbw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
        	try{
        		inbr.close();
        		outbw.close();
        	}catch (IOException e1){
                	
        	}
        }
	}
	
	private int[] solve(int num[]){
		int[][] digits= new int[2][3];
		digits[0][0]=num[0]%10;
		digits[0][1]=((int)(num[0]/10))%10;
		digits[0][2]=((int)(num[0]/100));
		digits[1][0]=num[1]%10;
		digits[1][1]=((int)(num[1]/10))%10;
		digits[1][2]=((int)(num[1]/100));
		int[] result=new int[2];
		double tgt;
		for(int i=10;i<=15000;i++){
			tgt=digits[0][0]+digits[0][1]*i+digits[0][2]*i*i;
			double delta=Math.sqrt(digits[1][1]*digits[1][1]-4*digits[1][2]*(digits[1][0]-tgt));
			double root=(0-digits[1][1]+delta)/(2*digits[1][2]);
			/*if(i==47){
				System.out.println("tgt:"+tgt);
				System.out.println("delta:"+delta);
				System.out.println("root:"+root);
			}*/
			if(root==Math.floor(root)){
				if(root>=10&&root<=15000){
					result[0]=i;result[1]=(int)root;
					return result;//assert there has one and only one solution
				}
			}
		}
		return null;//could not happen
	}
}
