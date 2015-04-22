package on2015_04.on2015_04_09_Single_Round_Match_655.BichromePainting;



public class BichromePainting {
    public String isThatPossible(String[] board, int k) {
		int n = board.length;
		boolean[][] canPut = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (board[i].charAt(j) == 'B') canPut[i][j] = true;
				if (i + k > n) continue;
				if (j + k > n) continue;
				boolean ok = true;
				for (int x = i; x - i < k; ++x) {
					for (int y = j; y - j < k; ++y) {
						if (board[x].charAt(y) == 'B') {
							ok = false;
							break;
						}
					}
					if (!ok) break;
				}
				if (ok) {
					for (int x = i; x - i < k; ++x) {
						for (int y = j; y - j < k; ++y) {
							canPut[x][y] = true;
						}
					}
				}
			}
		}
		boolean[][] used = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			if (i + k > n) continue;
			for (int j = 0; j < n; ++j) {
				if (j + k > n) continue;
				boolean ok = true;
				for (int x = i; x - i < k; ++x) {
					for (int y = j; y - j < k; ++y) {
						if (!canPut[x][y]) {
							ok = false;
							break;
						}
					}
					if (!ok) break;
				}
				if (!ok) continue;
				for (int x = i; x - i < k; ++x) {
					for (int y = j; y - j < k; ++y) {
						used[x][y] = true;
					}
				}
			}
		}
		for (int x = 0; x < n; ++x) {
			for (int y = 0; y < n; ++y) {
				if (!used[x][y] && board[x].charAt(y) == 'B') {
					return "Impossible";
				}
			}
		}
		return "Possible";
    }
}
