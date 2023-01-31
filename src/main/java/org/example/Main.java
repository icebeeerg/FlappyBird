package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        char playerCharacter = 'O';
        Position player = new Position(10, 10);
        terminal.setCursorPosition(player.x, player.y);
        terminal.putCharacter(playerCharacter);

        boolean continueReadingInput = true;
        while (continueReadingInput) {
            KeyStroke keyStroke = null;

            do {
                keyStroke = terminal.pollInput();
            }
            while (keyStroke == null);

            Character c = keyStroke.getCharacter(); // used Character instead of char because it might be null
            if (c == Character.valueOf('q')) {
                continueReadingInput = false;
                System.out.println("quit");
            }

            Position oldPosition = new Position(player.x, player.y);

            switch (keyStroke.getKeyType()) {
                case ArrowDown:
                    player.y += 1;
                    break;
                case ArrowUp:
                    player.y -= 1;
                    break;
                case ArrowRight:
                    player.x += 1;
                    break;
                case ArrowLeft:
                    player.x -= 1;
                    break;
            }

            terminal.setCursorPosition(oldPosition.x, oldPosition.y);
            terminal.putCharacter(' ');
            terminal.setCursorPosition(player.x, player.y);
            terminal.putCharacter(playerCharacter);
            terminal.flush();
        }

    }
}