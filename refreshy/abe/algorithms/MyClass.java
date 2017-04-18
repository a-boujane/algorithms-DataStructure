package refreshy.abe.algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MyClass {

	public static void main(String[] args) throws IOException, InterruptedException {

		// char[] hi = {'1','2','3','4','5','6','7','8','9'};
		// for(int i = 0;i<60;i++){
		// System.out.println(hi=nextString(hi));
		// }
		String yp = "abcdefghij";
		int n = yp.length();
		int fac = 1;
		while(n>0){
			fac*=n;
			n--;
		}
		final long start = System.currentTimeMillis();
		boolean found = false;
		String current=yp;
		List<String> r1 = new ArrayList<>(fac+1);
		while(!found){
			current = nextString(current);
			r1.add(current);
			if(current.compareTo(yp)==0)
				found=true;	
		}
		final long intermediate = System.currentTimeMillis();
		System.out.println("Here is the smart method : "+(intermediate-start));
		
		strPerm(yp);
		
		
		final long finaly = System.currentTimeMillis();
		System.out.println("Here is the dumb method : "+(finaly-intermediate));
		
	}

	public static String nextString(String input) {
		return String.valueOf(nextString(input.toCharArray()));
	}
	

	public static char[] nextString(char[] input) {
		int n = input.length;
		char[] result = new char[n];
		int pivotIndex = n - 1;
		int index = n - 1;
		if (input[n - 1] > input[n - 2])
			index = n - 1;
		else {
			while (index > 0 && input[index] <= input[index - 1])
				index--;
		}
		pivotIndex = index;
		if (pivotIndex < 1) {
			for(int i =0;i<n;i++)
				result[n-1-i]=input[i];
		} else {
			char winner = input[pivotIndex - 1];
			index = 0;
			while (index < pivotIndex - 1) {
				result[index] = input[index];
				index++;
			}
			index = n - 1;
			int helper = pivotIndex;
			boolean found = false;
			while (helper < n) {
				if (!found && input[index] > winner) {
					result[pivotIndex - 1] = input[index];
					input[index] = winner;
					found = true;
				} else {
					result[helper] = input[index];
					helper++;
					index--;
				}
			}
		}
		return result;
	}

	public static List<String> strPerm(String input) {
		int n = input.length();
		List<String> result = new ArrayList<>();

		List<String> candidate;

		result.add(String.valueOf(input.charAt(0)));
		int length = 1;
		for (int i = 1; i < n; i++) {
			candidate = new ArrayList<>(length * n * 2);
			length = result.size();
			for (int j = 0; j < length; j++) {
				for (int k = 0; k <= i; k++) {
					candidate.add(result.get(j).substring(0, k) + input.charAt(i) + result.get(j).substring(k));
				}
			}
			result = candidate;
		}
		return result;

	}

	static int wordLadder(String beginWord, String endWord, String[] wordList) {
		GraphNody startNode = new GraphNody(beginWord);
		startNode.depth = 0;
		GraphNody endNode = new GraphNody(endWord);
		endNode.depth = 0;

		GraphNody[] nodes = new GraphNody[wordList.length];
		for (int i = 0; i < wordList.length; i++) {
			nodes[i] = new GraphNody(wordList[i]);
		}
		System.out.println("Adding beginWOrd and endWord neighbors:\n");
		for (int i = 0; i < wordList.length; i++) {
			if (distanceOne(beginWord, wordList[i])) {
				System.out.println("\tAdding '" + wordList[i] + "' as a BeginWord Neighbor");
				startNode.neighbors.add(nodes[i]);
			}
			if (distanceOne(endWord, wordList[i])) {
				System.out.println("\tAdding '" + wordList[i] + "' as a EndWOrd Neighbor");
				endNode.neighbors.add(nodes[i]);
			}
		}
		System.out.println("\n\nAdding people's neighbors :\n");

		for (int i = 0; i < wordList.length; i++) {
			for (int j = i + 1; j < wordList.length; j++) {
				if (distanceOne(wordList[i], wordList[j])) {
					System.out.println("\tAdding '" + wordList[i] + "' as a '" + wordList[j] + "' Neighbor");
					nodes[i].neighbors.add(nodes[j]);
					nodes[j].neighbors.add(nodes[i]);
				}
			}
		}

		FastQueue<GraphNody> starter = new FastQueue<>();
		starter.add(startNode);
		FastQueue<GraphNody> ender = new FastQueue<>();
		ender.add(endNode);

		while (!starter.isEmpty() && !ender.isEmpty()) {
			GraphNody starty = starter.remove();
			starty.visited = 's';
			for (GraphNody node : starty.neighbors) {
				if (node.visited != 's') {
					if (node.visited == 'f') {
						return node.depth + starty.depth + 2;
					} else {
						node.visited = 's';
						node.depth = starty.depth + 1;
						starter.add(node);
					}
				}
			}
			GraphNody endy = ender.remove();
			endy.visited = 'f';
			for (GraphNody node : endy.neighbors) {
				if (node.visited != 'f') {
					if (node.visited == 's') {
						return node.depth + endy.depth + 2;
					} else {
						node.visited = 'f';
						node.depth = endy.depth + 1;
						ender.add(node);
					}
				}
			}

		}

		return -1;
	}

	static boolean distanceOne(String a, String b) {
		boolean result = false;
		int n = a.length() - 1;

		while (n >= 0) {
			if (result && a.charAt(n) != b.charAt(n)) {
				return false;
			}
			if (!result && a.charAt(n) != b.charAt(n)) {
				result = true;
			}
			n--;
		}

		return result;
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

	public static int longestIncreasingSubsequence(int[] seq) {
		int[] helper = new int[seq.length];
		int max = 1;
		helper[0] = 1;
		for (int i = 1; i < helper.length; i++) {
			int j = i - 1;
			int candidate = 1;
			while (j >= 0) {
				if (seq[i] > seq[j] && helper[j] + 1 > candidate) {
					candidate = helper[j] + 1;
				}
				if (candidate == max + 1) {
					max++;
					break;
				}
				j--;
			}
			helper[i] = candidate;
		}
		return max;
	}

	public static boolean isTree(GraphNode<Integer>[] nodes) {

		int n = nodes.length;

		for (int i = 0; i < n; i++) {
			GraphNode<Integer> current = nodes[i];
			if (!current.isVisited) {
				MyQueue<GraphNode<Integer>> q = new MyQueue<GraphNode<Integer>>();
				q.addElement(current);
				while (!q.isEmpty()) {
					current = q.removeElement();
					current.visit();
					for (GraphNode<Integer> item : current.getNeighbors()) {
						if (item.isVisited)
							return false;
						q.addElement(item);
					}
				}
			}
		}
		return true;
	}

	public static int firstCommonAncestor(TreeHack node1, TreeHack node2, boolean checkLeft, boolean checkRight) {
		if (node1 == node2)
			return node1.data;
		if (checkLeft) {
			if (node1.hasLeft())
				if (isGranpa(node1.lefty, node2))
					return node1.data;
		}
		if (checkRight) {
			if (node1.hasRight())
				if (isGranpa(node1.righty, node2))
					return node1.data;
		}
		if (node1.isLeftChild()) {
			return firstCommonAncestor(node1.parent, node2, false, true);
		}
		if (node1.isRightChild()) {
			return firstCommonAncestor(node1.parent, node2, true, false);
		}
		return 0;
	}

	public static boolean isGranpa(TreeHack granpa, TreeHack kiddo) {

		MyQueue<TreeHack> q = new MyQueue<TreeHack>();
		q.addElement(granpa);

		while (!q.isEmpty()) {
			TreeHack current = q.removeElement();
			if (current == kiddo)
				return true;
			if (current.hasLeft())
				q.addElement(current.lefty);
			if (current.hasRight())
				q.addElement(current.righty);
		}
		return false;
	}

	public static int numPaths(TreeHack node, int target, List<Integer> sumsSoFar) {
		int result = 0;
		int n = sumsSoFar.size();

		if (node.data == target)
			result++;

		for (int i = 0; i < n; i++) {
			if (node.data + sumsSoFar.get(i) == target)
				result++;
			sumsSoFar.set(i, node.data + sumsSoFar.get(i));
		}
		sumsSoFar.add(node.data);
		boolean gone = false;
		List<Integer> rightSum = new ArrayList<Integer>(sumsSoFar);
		if (node.lefty != null) {
			result += numPaths(node.lefty, target, sumsSoFar);
			gone = true;
		}
		if (node.righty != null) {
			if (gone)
				result += numPaths(node.righty, target, rightSum);
			else {
				result += numPaths(node.righty, target, sumsSoFar);
			}
		}

		return result;
	}

	public static void rotateMatrix(int[][] input) {
		int n = input.length;

		for (int i = 0; i <= n / 2; i++) {
			int start = i;
			int end = n - 1 - i;
			for (int j = start; j <= end - 1; j++) {
				int temp = input[start][j];
				input[start][j] = input[end - j][start];
				input[end - j][start] = input[end][end - j];
				input[end][end - j] = input[j][end];
				input[j][end] = temp;

			}

		}

	}

	public static String longestPalindrome(String input) {
		int n = input.length();
		for (int l = n; l > 0; l--) {
			for (int i = 0; i + l <= n; i++) {
				if (isPalindrome(input.substring(i, i + l))) {
					return input.substring(i, i + l);
				}
			}

		}

		return input.substring(0, 1);
	}

	public static boolean isPalindrome(String input) {
		int n = input.length();
		int i = n / 2;
		while (i >= 0) {
			if (input.charAt(i) != input.charAt(n - 1 - i))
				return false;
			i--;
		}
		return true;
	}

	public static void validParenthesis(String s, int n, int opened) {
		if (n == 0) {
			if (opened == 0)
				System.out.println(s);
			else {
				validParenthesis(s + ")", n, opened - 1);
			}
		} else {
			validParenthesis(s + "(", n - 1, opened + 1);
			if (opened > 0) {
				validParenthesis(s + ")", n, opened - 1);
			}
		}
	}

	public static void validateBST(BinaryTreeNode<Integer> root, int min, int max) throws StackOverflowError {

		int data = root.data;
		int kid;
		if (root.lefty != null) {
			kid = root.lefty.data;
			if (kid > max || kid < min)
				throw new StackOverflowError();
			else {
				System.out.println("validated " + root.lefty.data);
				validateBST(root.lefty, min, data);
			}
		}
		if (root.righty != null) {
			kid = root.righty.data;
			if (kid > max || kid < min)
				throw new StackOverflowError();
			else {
				System.out.println("validated " + root.righty.data);
				validateBST(root.righty, data, max);
			}
		}

	}

	public static List<ArrayList<Integer>> listArrays(BinaryTreeNode<Integer> root) {

		List<ArrayList<BinaryTreeNode<Integer>>> daList = new ArrayList<ArrayList<BinaryTreeNode<Integer>>>();

		MyQueue<BinaryTreeNode<Integer>> parents = new MyQueue<BinaryTreeNode<Integer>>();
		List<ArrayList<BinaryTreeNode<Integer>>> temporary;

		parents.addElement(root);
		daList.add(new ArrayList<BinaryTreeNode<Integer>>());
		daList.get(0).addAll(root.getKids());

		while (!parents.isEmpty()) {
			BinaryTreeNode<Integer> element = parents.removeElement();
			temporary = new ArrayList<ArrayList<BinaryTreeNode<Integer>>>();

			daList = temporary;
		}

		return null;
	}

	public static int numWays(int n) {

		int[] coins = { 1, 5, 10, 25 };
		Map<Integer, int[]> helper = new HashMap<Integer, int[]>();

		int[] coinHelper = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			coinHelper[i] = 1;
		}
		helper.put(coins[0], coinHelper);

		for (int i = 1; i < coins.length; i++) {
			int[] array = new int[n + 1];
			for (int j = 0; j < n + 1; j++) {
				int div = j / coins[i];
				int k = div;
				while (k >= 0) {
					array[j] += helper.get(coins[i - 1])[j - div * coins[i]];
					k--;
				}
			}
			helper.put(coins[i], array);
		}

		return helper.get(25)[n];
	}

	public static List<ArrayList<Long>> generateSubsets(List<Long> input) {
		int n = input.size();
		List<ArrayList<Long>> result = new ArrayList<ArrayList<Long>>();
		ArrayList<Long> candidate;
		for (int i = 0; i < n; i++) {
			candidate = new ArrayList<Long>();
			candidate.add(input.get(i));
			result.add(candidate);
		}
		int start = 0;
		int finish = result.size();
		while (finish - start > 0) {
			for (int j = start; j < finish; j++) {
				Long element = result.get(j).get(result.get(j).size() - 1);
				int index = input.indexOf(element);
				for (int k = index + 1; k < input.size(); k++) {
					candidate = new ArrayList<Long>(result.get(j));
					candidate.add(input.get(k));
					result.add(candidate);
				}
			}
			start = finish;
			finish = result.size();
		}
		return result;
	}

	public static List<Long> reduce(List<Long> input) {

		for (int i = 0; i < 64; i++) {
			long item = 0;
			int iterate = 0;
			int reference = -1;
			for (iterate = 0; iterate < input.size(); iterate++) {
				if (reference == -1 && ((input.get(iterate) >> i) & 1) == 1) {
					item = input.get(iterate);
					reference = iterate;
				} else if (reference != 1 && ((input.get(iterate) >> i) & 1) == 1) {
					item = input.get(iterate) | item;
					input.set(reference, item);
					input.remove(iterate);
				}
			}
		}
		return input;

	}

	public static int returnUnique(List<Long> input) {

		int result = 64;
		int i = 0;
		for (Long item : input) {
			i = 0;
			while (i < 64) {
				if ((item >> i & 1) == 1) {
					result--;
				}
				i++;
			}
			result++;
		}
		return result;
	}

	public static String combine(String a, String b) {

		StringBuilder strb = new StringBuilder();

		Stack<Character> sta = new Stack<Character>();
		Stack<Character> stb = new Stack<Character>();

		int al = a.length();
		int bl = b.length();

		for (int i = 0; i < al; i++)
			sta.push(a.charAt(al - i - 1));
		for (int i = 0; i < bl; i++)
			stb.push(b.charAt(bl - i - 1));

		while (!sta.isEmpty() || !stb.isEmpty()) {
			if (!sta.isEmpty() && !stb.isEmpty()) {
				if (sta.peek() < stb.peek())
					strb.append(sta.pop());
				else
					strb.append(stb.pop());
			} else if (!sta.isEmpty()) {
				strb.append(sta.pop());
			} else
				strb.append(stb.pop());
		}

		return strb.toString();
	}

	static void StairCase(int n) {
		String s = "#";
		String blank = "";
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			stb.append(blank);
		}
		blank = stb.toString();

		int i = n;
		while (i > 1) {
			System.out.println(blank.substring(n - i) + s);
			s = s + "#";
			i--;
		}
		System.out.print(s);

	}

	static List<LinkedList<BinaryTreeNode<Integer>>> listOfDepth(BinaryTreeNode<Integer> root) {
		List<LinkedList<BinaryTreeNode<Integer>>> result = new ArrayList<LinkedList<BinaryTreeNode<Integer>>>();
		LinkedList<BinaryTreeNode<Integer>> start = new LinkedList<BinaryTreeNode<Integer>>();
		start.add(root);
		result.add(start);
		int index = 0;
		for (index = 0; result.size() > index; index++) {
			LinkedList<BinaryTreeNode<Integer>> elt = new LinkedList<BinaryTreeNode<Integer>>();
			for (BinaryTreeNode<Integer> item : result.get(index)) {
				if (item.lefty != null)
					elt.add(item.lefty);
				if (item.righty != null)
					elt.add(item.righty);
			}
			if (elt.isEmpty())
				return result;
			else
				result.add(elt);
		}
		return result;
	}

	static boolean isThereaPath(GraphNode<Integer> start, GraphNode<Integer> end) {

		MyQueue<GraphNode<Integer>> Q = new MyQueue<GraphNode<Integer>>();

		GraphNode<Integer> current;

		Q.addElement(start);

		while (!Q.isEmpty()) {
			current = Q.removeElement();
			if (current == end)
				return true;
			current.visit();
			for (GraphNode<Integer> item : current.getNeighbors())
				if (!item.isVisited)
					Q.addElement(item);
		}
		return false;
	}

	static int searchSubStringInDictionary(String input, String[] dict) {
		int result = 0;
		TrieNode head = new TrieNode();
		for (String s : dict)
			head.addString(s);

		TrieNode current = new TrieNode();
		int l = input.length();
		for (int i = 0; i < l; i++) {
			int index = i;
			current = head.getKid(input.charAt(index));
			while (current != null && index < l - 1) {
				if (current.isEnd)
					result++;
				current = current.getKid(input.charAt(index + 1));
				index++;
			}
			if (current != null && current.isEnd && index == l - 1) {
				{
					result++;
				}
			}
		}
		return result;
	}

	static LinkedListNode isLooped(LinkedListNode head) {
		Set<LinkedListNode> set = new HashSet<LinkedListNode>();
		int i = 0;
		while (!set.contains(head)) {
			i++;
			set.add(head);
			head = head.next;
		}
		System.out.println(i);
		return head;
	}

	static void deleteMiddle(LinkedListNode head, LinkedListNode middle) {
		while (head.next != middle)
			head = head.next;
		head.next = middle.next;
	}

	static LinkedListNode kth(LinkedListNode input, int k) {
		LinkedListNode fast = input;
		while (k > 1) {
			k--;
			fast = fast.next;
		}
		LinkedListNode result = input;
		while (fast.next != null) {
			fast = fast.next;
			result = result.next;
		}
		return result;
	}

	static void printArray(int[][] input) {
		for (int[] item : input) {
			for (int haha : item)
				System.out.print(haha + " ");
			System.out.print("\n");
		}
	}

	static void ex8(int[][] input) {
		Set<Integer> rows = new HashSet<Integer>();
		Set<Integer> columns = new HashSet<Integer>();
		int m = input.length;
		int n = input[0].length;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (input[i][j] == 0) {
					rows.add(i);
					columns.add(j);
				}
		for (Integer i : rows)
			for (int j = 0; j < n; j++)
				input[i][j] = 0;
		for (Integer j : columns)
			for (int i = 0; i < m; i++)
				input[i][j] = 0;

	}

	static boolean amazonSubset(int[] input) {
		int sum = 0;
		Arrays.sort(input);
		for (int i : input) {
			System.out.print(i + ", ");
			sum += i;
		}
		System.out.println("\n" + sum / 2);
		return false;
	}

	static int p(int x) {
		if (x <= 10)
			return x - 1;
		char[] s = ("" + x).toCharArray();
		int l = s.length;
		int r = t(s);
		if (r < x)
			return r;
		else {
			int j = (int) Math.pow(10, (l / 2));
			s = ("" + (x - j)).toCharArray();
			int k = s.length;
			if (k != l)
				s[k / 2] = '9';
			return t(s);
		}
	}

	static int t(char[] s) {
		int l = s.length;
		for (int i = 0; i < l / 2; i++) {
			s[l - 1 - i] = s[i];
		}
		return Integer.parseInt(String.copyValueOf(s));
	}

	static String clockRadian(String time) {
		String helper[] = time.split(":");
		int a = Integer.valueOf(helper[0]) * 5;
		int b = Integer.valueOf(helper[1]);
		int c = (b - a + 60) % 60;
		c *= 2;
		int i;
		for (i = 1; i < 61; i++)
			if ((c * i) % 60 == 0)
				break;
		c = c * i / 60;
		return i == 1 ? "pi" : c + "pi/" + i;
	}

	static boolean stringsRearrangement(String[] inputArray) {
		int[] a;
		int count = 0;
		for (int i = 0; i < inputArray[0].length(); i++) {
			a = new int[128];
			for (int j = 0; j < inputArray.length; j++) {
				if (a[inputArray[j].charAt(i)] == 0) {
					count++;
					a[inputArray[j].charAt(i)] = 1;
				}
			}
			count--;
		}
		return (count + 1) == inputArray.length;
	}

	static String removeDuplicateAdjacent(String s) {
		char[] a = s.toCharArray();
		boolean found = false;
		StringBuilder stbl = new StringBuilder();
		char buffer = a[0];
		;
		stbl.append(buffer);
		for (int i = 1; i < a.length; i++) {
			found = false;
			buffer = a[i];
			while (i + 1 < a.length && buffer == a[i + 1]) {
				i++;
				found = true;
			}
			if (buffer == stbl.charAt(stbl.length() - 1)) {
				found = true;
				stbl.deleteCharAt(stbl.length() - 1);
			}
			if (!found)
				stbl.append(buffer);

		}
		return stbl.toString();
	}

	static String reverseVowelsOfString(String s) {
		boolean[] a = new boolean[128];
		a['a'] = true;
		a['e'] = true;
		a['i'] = true;
		a['o'] = true;
		a['u'] = true;
		char[] st = s.toCharArray();
		int i = 0;
		int j = st.length - 1;
		char temp;
		while (i < j) {
			if (a[st[i]]) {
				while (!a[st[j]])
					j--;
				if (i < j) {
					temp = st[i];
					st[i] = st[j];
					st[j] = temp;
					j--;
				}
			}
			i++;
		}
		return String.copyValueOf(st);
	}

	static int brainex2(String input) {

		return 0;
	}

	static int brainex1(int n) {
		int[] memo = new int[n + 1];
		memo[0] = 0;
		memo[1] = 1;
		memo[2] = 2;
		memo[3] = 4;
		for (int i = 4; i < n + 1; i++)
			memo[i] = memo[i - 3] + memo[i - 2] + memo[i - 1];
		return memo[n];
	}

	static String ex6(String a) {
		StringBuilder strbld = new StringBuilder();
		char lastChar = a.charAt(0);
		int counter = 1;
		for (int i = 1; i < a.length(); i++) {
			if (a.charAt(i) == lastChar) {
				counter++;
			} else {
				strbld.append(lastChar);
				strbld.append(counter);
				lastChar = a.charAt(i);
				counter = 1;
			}
		}
		strbld.append(lastChar);
		strbld.append(counter);

		String result = strbld.toString();
		return result.length() > a.length() ? a : result;
	}

	static boolean ex5(String a, String b) {
		int h = a.length() - b.length();
		if (h * h > 1)
			return false;
		else if (h == 0)
			return ex5SameLength(a, b);

		return h == 1 ? ex5DifferentLength(a, b) : ex5DifferentLength(b, a);
	}

	static boolean ex5SameLength(String a, String b) {
		boolean strike = false;
		for (int i = 0; i < a.length(); i++)
			if (a.charAt(i) != b.charAt(i)) {
				if (strike)
					return false;
				else
					strike = true;
			}
		return true;
	}

	static boolean ex5DifferentLength(String longer, String shorter) {
		int buddy = 0;
		for (int i = 0; i < longer.length(); i++) {
			try {
				if (longer.charAt(i) != shorter.charAt(i - buddy)) {
					if (buddy == 1)
						return false;
					else
						buddy++;
				}
			} catch (StringIndexOutOfBoundsException e) {
				return true;
			}
		}
		return true;
	}

	static boolean ex4(String a) {
		String[] b = a.split(" ");
		StringBuilder stb = new StringBuilder();
		for (String s : b)
			stb.append(s);
		a = stb.toString();
		a = a.toLowerCase();
		int helper[] = new int[128];
		for (char c : a.toCharArray())
			helper[c] += 1;
		boolean result = false;
		for (int h : helper) {
			if (h % 2 != 0)
				if (result)
					return false;
				else
					result = true;

		}
		return true;
	}

	static char[] ex3(char[] input, int length) {
		int numSpaces = 0;
		for (int i = length - 1; i > -1; i--)
			if (input[i] == ' ')
				numSpaces += 1;
		for (int index = length - 1; index > -1; index--) {
			if (input[index] != ' ') {
				input[index + 2 * numSpaces] = input[index];
			} else {
				input[index + 2 * numSpaces] = '0';
				input[index + 2 * numSpaces - 1] = '2';
				input[index + 2 * numSpaces - 2] = '%';
				numSpaces--;
			}
		}
		return input;
	}

	static boolean ex2(String a, String b) {
		if (a.length() != b.length())
			return false;
		int[] helper = new int[128];
		for (char index : a.toCharArray()) {
			helper[index] += 1;
		}
		for (char index : b.toCharArray()) {
			helper[index] -= 1;
		}
		for (int fin : helper)
			if (fin != 0)
				return false;
		return true;
	}

}
