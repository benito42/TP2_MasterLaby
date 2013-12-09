package client;

import server.Tile;

public interface IGameView
{
	public void showGameView();
	public void updateBoard(Tile[][] newBoardPaths);
	public void updateNextTile(Tile nextTilePath);
	public void updateNoPlayer(int noPlayer);
	public void updateTurnChange(int noActivePlayer);
}
