package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    String name;
    PlayingPiece playingPiece;

    public Player(String name, PlayingPiece playingPiece) {
        this.name = name;
        this.playingPiece = playingPiece;
    }
}
