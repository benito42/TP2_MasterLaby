package client;

import server.Tile;

public interface IGameView
{
	public void showGameView();
	public void updateBoard(Tile[][] newBoard);
	public void updateNextTile(Tile nextTile);
}
