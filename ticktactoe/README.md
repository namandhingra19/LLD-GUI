# Tic Tac Toe LLD

A Java Swing based Tic Tac Toe project built for low-level design practice. The goal is to keep UI, controller, and game model responsibilities separated while building toward a complete playable game.

## Purpose

This project is mainly for practicing LLD concepts:

- Separating UI from game logic
- Using a controller as the UI-facing API
- Modeling players, pieces, board, and game result
- Supporting configurable board size
- Keeping game state inside the model layer

## Current Features

- Swing UI entry point
- Player setup screen
- Collects two player names
- Collects board size from the user
- Starts game through `UIController`
- Renders dynamic `N x N` board
- Calls controller `turn(row, column)` on cell click
- Shows current player name and piece
- Uses `getGameResult()` to show game-over or invalid-move popups

## Project Structure

```text
ticktactoe/
  src/
    Main.java
    controller/
      UIController.java
    enums/
      GameResultType.java
      Piece.java
    model/
      Board/
        Board.java
      GameResult/
        GameResult.java
      Player/
        Player.java
      PlayingPiece/
        PlayingPiece.java
        PlayingPieceX.java
        PlayingPieceO.java
      TickTacToeGame/
        TickTacToeGame.java
    ui/
      PlayerSetupPanel.java
      TicTacToeFrame.java
```

## Design Flow

```text
Main
  -> creates TickTacToeGame
  -> creates UIController
  -> opens TicTacToeFrame

TicTacToeFrame / PlayerSetupPanel
  -> read user input
  -> call UIController

UIController
  -> delegates to TickTacToeGame

TickTacToeGame
  -> manages Board, Player queue, current turn, and GameResult
```

## Requirements

- JDK 8 or later

## Compile

Run from the `lld` folder:

```bash
javac -d out ticktactoe/src/**/*.java ticktactoe/src/Main.java
```

## Run

Run from the `lld` folder after compiling:

```bash
java -cp out ticktactoe.src.Main
```

## Notes

- The UI should use `UIController` instead of directly modifying model classes.
- Game rules should stay in `TickTacToeGame`, `Board`, and related model classes.
- UI should only display state, collect input, and show messages.
- This project intentionally favors clear LLD boundaries over compact code.
