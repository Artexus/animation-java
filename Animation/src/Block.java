
public class Block {
	public int x = 0;
	public int y = 320;
	public int width = 50;
	public int height = 50;
	
	public int getCol(int x) {
		return 32 *x;
	}
	public int getRow(int x)
	{
		return 16 *x;
	}
}
