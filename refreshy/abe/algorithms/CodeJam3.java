package refreshy.abe.algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.PriorityQueue;

public class CodeJam3 {

	public static void main(String[] args) throws IOException{
	
		Path questions = FileSystems.getDefault().getPath("questions", "sub-9.log");
		Path answers = FileSystems.getDefault().getPath("answers", "sub-9-answer.log");
		BufferedReader reader = Files.newBufferedReader(questions);
		BufferedWriter writer = Files.newBufferedWriter(answers);
		int t = Integer.parseInt(reader.readLine());

		for (int i = 1; i < t + 1; i++) {
			String[] current = (reader.readLine()).split(" ");
			long[] result = bathroomStalls(Long.parseLong(current[0]), Long.parseLong(current[1]));
			String finalo = "Case #" + i + ": " + result[0] + " "+result[1]+"\n";
			System.out.println(i);
			writer.write(finalo);
		}
		reader.close();
		writer.close();
		
		
	}
	
	public static long[] bathroomStalls(long N, long K){
		long[] result = new long[2];
		PriorityQueue<Long> Q = new PriorityQueue<Long>(11,Collections.reverseOrder());
		Q.add(N);
		
		while(!Q.isEmpty() && K>0){
			long current = Q.remove()-1;
			if(current>0){
			Q.add(current-current/2);
			Q.add(current/2);
			result[0]=current-current/2;
			result[1]=current/2;
			}
			if(current<=0){
				result[0]=0;
				result[1]=0;
				break;

			}
			K--;
		}
		return result;
	}
	
}
