package refreshy.abe.algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodeJam {

	public static void main(String[] args) throws IOException, InterruptedException {


		Path questions = FileSystems.getDefault().getPath("questions", "ex2question.log");
		Path answers = FileSystems.getDefault().getPath("answers", "ex2.log");
		BufferedReader reader = Files.newBufferedReader(questions);
		BufferedWriter writer = Files.newBufferedWriter(answers);
		int t = Integer.parseInt(reader.readLine());

		for (int i = 1; i < t + 1; i++) {
			String current = (reader.readLine());
			String w = fixTidy(indexFuckedUp(current), current);
			String result = "Case #" + i + ": " + w + "\n";
			writer.write(result);
		}
		reader.close();
		writer.close();
	}

	public static int indexFuckedUp(String number) {
		if (number != null) {
			int i = 0;

			while (i < number.length() - 1) {
				if (number.charAt(i) > number.charAt(i + 1))
					return i;
				i++;
			}
		}
		return -1;
	}

	public static String fixTidy(int index, String number) {
		if (index == -1)
			return number;
		char[] input = number.toCharArray();
		int n = number.length();
		int i = index;

		input[index]--;

		while (i > 0) {
			if (input[i - 1] <= input[i])
				break;
			else {
				i--;
				input[i]--;
			}
		}

		while (i < n - 1) {
			input[i + 1] = '9';
			i++;
		}

		return input[0] == '0' ? String.valueOf(input).substring(1) : String.valueOf(input);
	}

}
