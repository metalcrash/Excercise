package meet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Meet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Meet meet=new Meet();
		int n;
		int m;
		int paths[][];
		File in=new File("meeting.in");
		File out=new File("meeting.out");
        BufferedReader inbr=null;
        BufferedWriter outbw=null;
        try {
            inbr=new BufferedReader(new FileReader(in));
            outbw=new BufferedWriter(new FileWriter(out));
            String line=null;
            String[] elements=null;
            line=inbr.readLine();
            elements=line.split(" ");
            n=Integer.parseInt(elements[0]);
            m=Integer.parseInt(elements[1]);
            paths=new int[m][];
            int count=0;
            while ((line = inbr.readLine()) != null){
            	elements=line.split(" ");
            	int path[]=new int[4];
            	path[0]=Integer.parseInt(elements[0]);
            	path[1]=Integer.parseInt(elements[1]);
            	path[2]=Integer.parseInt(elements[2]);
            	path[3]=Integer.parseInt(elements[3]);
            	paths[count++]=path;
            }           
            int result=meet.solve(n,m,paths);
            System.out.println(result);
            if(result==0)outbw.write("impossible");
    		else outbw.write(Integer.toString(result));
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
	
	public int solve(int n,int m,int[][] paths){
		QSort sorter=new QSort();
		sorter.sort(paths);
		/*for(int i=0;i<paths.length;i++){
			System.out.println(paths[i][0]+" "+paths[i][1]+" "+paths[i][2]+" "+paths[i][3]);
		}*/
		LinkedList<int[]> trace[]=new LinkedList[n];
		trace[0]=new LinkedList<int[]>();
		trace[0].add(new int[]{0,0});
		int count=0;
		for(int f=1;f<n;f++){
			int num=0;
			while(count+num<paths.length&&paths[count+num][1]==f+1)num++;
			LinkedList<int[]> p2f=new LinkedList<int[]>();
			for(int i=0;i<num;i++){
				int start=paths[count+i][0];
				int l=paths[count+i][2];
				int r=paths[count+i][3];
				Iterator<int[]> traceIt=trace[start-1].iterator();
				while(traceIt.hasNext()){
					int[] curP=traceIt.next();
					int[] newP=new int[]{curP[0]+l,curP[1]+r};
					p2f.add(newP);
				}
			}
			trace[f]=p2f;
			count+=num;
		}
		int result=check(trace[n-1]);
		return result;
		/*for(int f=0;f<n;f++){
			System.out.println("====="+f+"=====");
			LinkedList<Pair> path=trace[f];
			Iterator<Pair> pathIt=path.iterator();
			while(pathIt.hasNext()){
				Pair curP=pathIt.next();
				System.out.println(curP.l+" "+curP.r);
			}
		}*/
	}
	
	public int check(LinkedList<int[]> path){
		Iterator<int[]> pathIt=path.iterator();
		HashSet<Integer> lset=new HashSet<Integer>();
		boolean exist=false;
		int minT=0;
		while(pathIt.hasNext()){
			int[] curP=pathIt.next();
			if(!lset.contains(curP[0]))lset.add(curP[0]);
		}
		pathIt=path.iterator();
		while(pathIt.hasNext()){
			int[] curP=pathIt.next();
			if(exist){
				if(lset.contains(curP[1])&&curP[1]<minT)minT=curP[1];
			}else{
				if(lset.contains(curP[1])){
					minT=curP[1];
					exist=true;
				}
			}
		}
		return minT;
	}
}