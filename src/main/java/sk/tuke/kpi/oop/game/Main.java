package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.WindowSetup;
import sk.tuke.kpi.gamelib.World;
import sk.tuke.kpi.gamelib.Game;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.gamelib.Input;

import sk.tuke.kpi.gamelib.inspector.InspectableScene;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;
//import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);



        Game game = new GameApplication(windowSetup, new LwjglBackend()); // in case of Mac OS system use "new Lwjgl2Backend()" as the second parameter



        Scene escapeRoom = new InspectableScene(new World("escape-room", "maps/escape-room.tmx", new EscapeRoom.Factory()), List.of("sk.tuke.kpi"));

        game.addScene(escapeRoom);

        escapeRoom.addListener(new EscapeRoom());

        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);

        game.start();
    }
}


