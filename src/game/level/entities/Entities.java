package game.level.entities;

import game.tools.Pos;
import game.level.Level;

import java.util.List;

public interface Entities {
    List<Pos> allEntities = null;

    Boolean isit(Pos p);
    static List<Pos> allPos (Level l) { return null; }
    List<Pos> getAllPos();

}
