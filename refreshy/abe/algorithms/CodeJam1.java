package refreshy.abe.algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodeJam1 {
	public static void main(String[] args) throws IOException {
		 Path questions = FileSystems.getDefault().getPath("questions",
		 "ex1question.log");
		 Path answers = FileSystems.getDefault().getPath("answers",
		 "ex1.log");
		 BufferedReader reader = Files.newBufferedReader(questions);
		 BufferedWriter writer = Files.newBufferedWriter(answers);
		 int t = Integer.parseInt(reader.readLine());
		
		 for (int i = 1; i < t + 1; i++) {
		 String[] input = (reader.readLine()).split(" ");
		 
		 writer.write("Case #" + i + ": " + numberFlips(input[0],Integer.parseInt(input[1]))+"\n");
		 }
		 reader.close();
		 writer.close();

	}

	public static String numberFlips(String input, int k) {

		char[] array = input.toCharArray();
		int i = 0;
		int n = input.length();
		int result = 0;
		while (i <= n - k) {
			if (array[i] == '-') {
				result++;
				for (int j = i; j < i + k; j++) {
					if (array[j] == '-') {
						array[j] = '+';
					} else {
						array[j] = '-';
					}
				}
			}
			i++;
		}
		while(i<n){
			if(array[i]=='-')
				return "IMPOSSIBLE";
			i++;
		}
		return String.valueOf(result);
	}

}
