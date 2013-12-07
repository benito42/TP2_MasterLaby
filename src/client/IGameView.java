package client;

public interface IGameView
{
	public void showGameView();
	public void updateBoard(String[][] newBoardPaths);
	public void updateNextTile(String nextTilePath);
	public void setLayout();
}
