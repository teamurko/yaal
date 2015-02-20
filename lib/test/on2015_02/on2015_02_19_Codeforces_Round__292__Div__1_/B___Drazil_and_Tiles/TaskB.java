package on2015_02.on2015_02_19_Codeforces_Round__292__Div__1_.B___Drazil_and_Tiles;



import net.egork.collections.Pair;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.LinkedList;
import java.util.Queue;

public class TaskB {
	final int[] dx = {0, 0, 1, -1};
	final int[] dy = {1, -1, 0, 0};
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int height = in.readInt();
		int width = in.readInt();
		char[][] board = new char[height][];
		int oddCount = 0;
		int evenCount = 0;
		for (int row = 0; row < height; ++row) {
			String s = in.readString();
			board[row] = s.toCharArray();
			for (int column = 0; column < width; ++column) {
				if (board[row][column] == '.') {
					if ((row + column) % 2 == 0) {
						++evenCount;
					} else {
						++oddCount;
					}
				}
			}
		}
		if (oddCount != evenCount) {
			out.printLine("Not unique");
			return;
		}

		Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
		int[][] numNeighbors = new int[height][width];
		int[][] anyNeighborDirection = new int[height][width];
		for (int row = 0; row < height; ++row) {
			for (int column = 0; column < width; ++column) {
				if (board[row][column] == '.') {
					numNeighbors[row][column] = calcNumNeighbors(board, anyNeighborDirection, row, column);
					if (numNeighbors[row][column] == 1) {
						queue.add(Pair.makePair(row, column));
					}
				}
			}
		}

		while (!queue.isEmpty()) {
			Pair<Integer, Integer> candidate = queue.poll();
			int row = candidate.first;
			int column = candidate.second;
			if (board[row][column] != '.') continue;
			int neighborDirection = anyNeighborDirection[row][column];
			int x = row + dx[neighborDirection];
			int y = column + dy[neighborDirection];
			if (board[x][y] != '.') continue;
			if (x == row) {
				board[x][Math.min(y, column)] = '<';
				board[x][Math.max(y, column)] = '>';
			} else {
				board[Math.min(x, row)][y] = '^';
				board[Math.max(x, row)][y] = 'v';
			}
			for (int i = 0; i < dx.length; ++i) {
				int xx = x + dx[i];
				int yy = y + dy[i];
				if (isInside(board, xx, yy) && board[xx][yy] == '.') {
					numNeighbors[xx][yy] = calcNumNeighbors(board, anyNeighborDirection, xx, yy);
					if (numNeighbors[xx][yy] == 1) {
						queue.add(Pair.makePair(xx, yy));
					}
				}
			}
		}

		boolean isComplete = true;
		for (int row = 0; row < height; ++row) {
			for (int column = 0; column < width; ++column) {
				if (board[row][column] == '.') {
					isComplete = false;
					break;
				}
			}
			if (!isComplete) break;
		}
		if (!isComplete) {
			out.printLine("Not unique");
		} else {
			for (int row = 0; row < height; ++row) {
				out.printLine(new String(board[row]));
			}
		}
    }

	private int calcNumNeighbors(char[][] board, int[][] anyNeighborDirection, int row, int column) {
		int result = 0;
		for (int i = 0; i < dx.length; ++i) {
			int x = row + dx[i];
			int y = column + dy[i];
			if (isInside(board, x, y)) {
				if (board[x][y] == '.') {
					anyNeighborDirection[row][column] = i;
					++result;
				}
			}
		}
		return result;
	}

	private boolean isInside(char[][] board, int x, int y) {
		return x >= 0 && y >= 0 && x < board.length && y < board[0].length;
	}
}
