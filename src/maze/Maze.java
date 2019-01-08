import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.terminal.ansi.*;
import com.googlecode.lanterna.TextColor.*;
import com.googlecode.lanterna.Symbols.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.*;
import java.io.IOException;


public class Maze{

private Tileable[][] maze;

public Maze(){
	maze = new Tileable[100][30];
	for (int x = 0; x < 100; x++){
		for (int y = 0; y < 30; y++){
			// initally make everything air
			maze[x][y] = new Air(x, y);
		}
	}
}

public Tileable[][] getMaze(){
	return maze;
}

public void setTile(int x, int y, Tileable toSet){
	maze[x][y] = toSet;
}

public void printMaze(TerminalScreen t) throws IOException{
	for (int x = 0; x < 100; x++){
		for (int y = 0; y < 30; y++){
			// t.setCursorPosition(x, y + 5);
			// char c = maze[x][y].getSprite();
			// t.putCharacter(c);
			t.setCharacter(x, y + 5, new TextCharacter(maze[x][y].getSprite(), ANSI.DEFAULT, ANSI.DEFAULT));
		}
	}
}

public void calcGenerated(Generation g, Maze maze){
	g.generate();
	char[][] gen = g.getGenerated();
	for(int x = 0; x < 100; x++){
		for(int y = 0; y < 30; y++){
			if (gen[x][y] == '0'){
				maze.setTile(x, y, new Wall(x, y, maze));
			}
			else{
				maze.setTile(x, y, new Air(x, y));
			}
		}
	}
}

}
