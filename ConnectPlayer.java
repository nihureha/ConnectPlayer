import java.util.Scanner;

public class ConnectPlayer {
  public static void main(String[] args) {
    /* ------ DO NOT CHANGE START ------ */

    Scanner scanner;
    scanner = new Scanner(System.in);

    int numRows, numCols;
    
    System.out.println("Welcome to Connect Four!");
    System.out.println("Enter board width and height: ");
    System.out.print("  Number of rows (4 to 9): "); // NOTE: different from println
    while(!scanner.hasNextInt()) scanner.next();
    numRows = scanner.nextInt();
    System.out.print("  Number of columns (4 to 9): ");
    while(!scanner.hasNextInt()) scanner.next();
    numCols = scanner.nextInt();
    System.out.println("  Requested board width and height: " + numRows + " x " + numCols);
    System.out.println(" -------------------------------------------------------------------------- ");
    // minimum is 4, maximum is 9
    while(numRows < 4 || numRows > 9 ||
      numCols < 4 || numCols > 9) {
      System.out.println("Minimum number of rows/columns is 4 and max is 9.");
      System.out.println("Enter new width and height: ");
      System.out.print("  Number of rows (4 to 9): ");
      while(!scanner.hasNextInt()) scanner.next();
      numRows = scanner.nextInt();
      System.out.print("  Number of columns (4 to 9): ");
      while(!scanner.hasNextInt()) scanner.next();
      numCols = scanner.nextInt();
      System.out.println("  Requested board width and height: " + numRows + " x " + numCols);
      System.out.println(" -------------------------------------------------------------------------- ");
    }
    System.out.print("Play against computer? 0 for no, != 0 for yes: ");
    int compInt = scanner.nextInt();
    boolean comp = (compInt != 0);
    boolean compTurn = false;
    if(comp) {
      System.out.print("Computer goes first? 0 for no, != 0 for yes: ");
      int compTurnInt = scanner.nextInt();
      compTurn = (compTurnInt != 0);
    }

    System.out.println("Settings:");
    System.out.print("  Play against computer: ");
    if(comp) {
      System.out.println("YES");
      System.out.print("    Computer goes first: ");
      if(compTurn) { System.out.println("YES"); }
      else { System.out.println("NO"); }
    } else {
      System.out.println("NO");
      System.out.println("    Computer goes first: NOT APPLICABLE");
    }
    System.out.println(" -------------------------------------------------------------------------- ");

    /* ------ DO NOT CHANGE END ------ */

    int rowNum, colNum, savedNumCols, numRC;
    int array[]; // Declare int arrays separate from declaring ints

    numRC = numCols * numRows;

    

    int slotNum, selectedRow, selectedCol, findRow;
    int turn, win;
    turn = 1;
    win = 0;
    numRC = numCols * numRows;
    array = new int[numRC]; // "array" is now set to an address
    printBoard(array, numCols, numRows); // "array" does not have a value yet
    int checkIfValid[];
    checkIfValid = new int[numRC];      

    int firstNumber = 0;

    int first = 0;

    int filled = 0;

    while (win == 0) {


      // Comp true = You are playing a computer.
      // Comp false = You are NOT playing a computer.
      // compTurn true = Comp first move. 
      // compTurn false = Comp last move.

      /*
      
      (firstNum(firstNumber, numCols, numRows, array) == !numRC)

      */

      if (detectWin(array, numCols, numRows) == 1) {
        win = 1;
      } else if (detectWin(array, numCols, numRows) == -1) {
        win = -1;
      } else if (detectWin(array, numCols, numRows) == 2) {
        win = 2;
      }
  
      if (comp && compTurn && win == 0 && filled == 0) {
        System.out.println("------------------------------------");
        compFirst(comp, compTurn, array, first, turn, numCols, numRows, checkIfValid);
        turn = -1;
        System.out.println("The AI has made their move.");
      }

      if (detectWin(array, numCols, numRows) == 1) {
        win = 1;
      } else if (detectWin(array, numCols, numRows) == -1) {
        win = -1;
      } else if (detectWin(array, numCols, numRows) == 2) {
        win = 2;
      }
      if (compTurn) {
        turn = -1;
      } else if (!compTurn && comp) {
        turn = 1;
      }
      if ((win == 0) && (filled == 0)) {
        array = getNum(numCols, numRows, checkIfValid, array, turn, scanner, comp, compTurn, win);
      }
      
      if (detectWin(array, numCols, numRows) == 1) {
        win = 1;
      } else if (detectWin(array, numCols, numRows) == -1) {
        win = -1;
      } else if (detectWin(array, numCols, numRows) == 2) {
        win = 2;
      }
      if (comp && !compTurn && win == 0 && filled == 0) {
        
        compLast(comp, compTurn, array, first, turn, numCols, numRows, checkIfValid);
        turn = 1;
        System.out.println("------------------------------------");
        System.out.println("The AI has made their move.");
      }
      printBoard(array, numCols, numRows);
      if (turn == 1) {
        turn = -1;
      } else {
        turn = 1;
      }
      // Find a way to win, and also use using selectedCol, and selectedRow. Also, make a plylist in Spotify so that I can listen to all the album mixes.
      
      if (detectWin(array, numCols, numRows) == 1) {
        System.out.println("X wins");
        win = 1;
      } else if (detectWin(array, numCols, numRows) == -1) {
        System.out.println("O wins");
        win = -1;
      } else if (detectWin(array, numCols, numRows) == 2) {
        System.out.println("It is a tie.");
        win = 2;
      }
    }
    
    System.out.println("");
    System.out.println("Completed");
    
  }
  public static void printBoard(int[] array, int numCols, int numRows) {
    int rowNum = 0;

    int colNum = 0;

    int savedNumCols = numCols;

    int helpNum = 0;

    System.out.print(" ");

    while (helpNum < numCols) {
      helpNum = helpNum + 1;
      System.out.print(helpNum + " ");
    }

    System.out.println("");

    helpNum = -1;

    while (rowNum < numRows) {
      while (colNum < savedNumCols) {
        if (array[colNum] == 1) {
          System.out.print("|X");
        } else if (array[colNum] == -1) {
          System.out.print("|O");
        } else {
          System.out.print("| ");
        }
        colNum = colNum + 1;
      }
      savedNumCols = savedNumCols + numCols;
      System.out.print("|");
      System.out.println();
      rowNum = rowNum + 1;
    }
  }
  public static int detectWin(int[] array, int numCols, int numRows) {
    int[] arrWin = array;
    int loop, across, stopAcross, downwards, stopDownwards, numColsf, numRowsf, diagonalRight, stopDiagonalRight, diagonalLeft, stopDiagonalLeft;
    across = 0;
    stopAcross = numCols - 4;
    downwards = 0;
    numColsf = 0;
    stopDownwards = (numRows - 4) * numCols;
    diagonalRight = 0;
    stopDiagonalRight = numCols - 4;
    diagonalLeft = 3;
    stopDiagonalLeft = numCols - 1;
    loop = 0;
    numRowsf = 0;
    int win = 0;
    // across
    while (numRows > loop) {
      while (stopAcross >= across) {
        if ((arrWin[across] == 1) && (arrWin[across + 1] == 1) && (arrWin[across + 2] == 1) && (arrWin[across + 3] == 1)) {
          win = 1;
        }
        if ((arrWin[across] == -1) && (arrWin[across + 1] == -1) && (arrWin[across + 2] == -1) && (arrWin[across + 3] == -1)) {
          win = -1;
        }
        across = across + 1;
      }
      stopAcross = stopAcross + numCols;
      numRowsf = numRowsf + numCols;
      across = numRowsf;
      loop = loop + 1;
    }
    // down
    loop = 0;
    while (numCols > loop) {
      while (stopDownwards >= downwards) {
        if ((arrWin[downwards] == 1) && (arrWin[downwards + numCols] == 1) && (arrWin[downwards + numCols + numCols] == 1) && (arrWin[downwards + numCols + numCols + numCols] == 1)) {
          win = 1;
        }
        if ((arrWin[downwards] == -1) && (arrWin[downwards + numCols] == -1) && (arrWin[downwards + numCols + numCols] == -1) && (arrWin[downwards + numCols + numCols + numCols] == -1)) {
          win = -1;
        }
        downwards = downwards + numCols;
      }
      stopDownwards = stopDownwards + 1;
      numColsf = numColsf + 1;
      downwards = numColsf;
      loop = loop + 1;
    }
    
    // (A && B && C && D)
    // diagonalRight = A is inside bounds
    // diagonalRight + numCols + 1 = B is inside bounds
    // diagonalRight + numCols + numCols + 2 = C is out of bounds
    // diagonalRight + numCols + numCols + numCols + 3 = D is out of bounds
    loop = 0;
    numColsf = 0;
    while (numRows - 3 > loop) {
      while (stopDiagonalRight >= diagonalRight) {
        if ((arrWin[diagonalRight] == 1) && (arrWin[diagonalRight + 1 + numCols] == 1) && (arrWin[diagonalRight + 2 + numCols + numCols] == 1) && (arrWin[diagonalRight + 3 + numCols + numCols + numCols] == 1)) {
          win = 1;
        }
        if ((arrWin[diagonalRight] == -1) && (arrWin[diagonalRight + 1 + numCols] == -1) && (arrWin[diagonalRight + 2 + numCols + numCols] == -1) && (arrWin[diagonalRight + 3 + numCols + numCols + numCols] == -1)) {
          win = -1;
        }
        diagonalRight = diagonalRight + 1;
      }
      stopDiagonalRight = stopDiagonalRight + numCols;
      numColsf = numColsf + numCols;
      diagonalRight = numColsf;
      loop = loop + 1;
    }
    // Copy and paste diagonalRight to diagonalLeft.
    loop = 0;
    numColsf = 3;
    while (numRows - 3 > loop) {
      while (stopDiagonalLeft >= diagonalLeft) {
        if ((arrWin[diagonalLeft] == 1) && (arrWin[diagonalLeft - 1 + numCols] == 1) && (arrWin[diagonalLeft - 2 + numCols + numCols] == 1) && (arrWin[diagonalLeft - 3 + numCols + numCols + numCols] == 1)) {
          win = 1;
        }
        if ((arrWin[diagonalLeft] == -1) && (arrWin[diagonalLeft - 1  + numCols] == -1) && (arrWin[diagonalLeft - 2 + numCols + numCols] == -1) && (arrWin[diagonalLeft - 3 + numCols + numCols + numCols] == -1)) {
          win = -1;
        }
        diagonalLeft = diagonalLeft + 1;
      }
      stopDiagonalLeft = stopDiagonalLeft + numCols;
      numColsf = numColsf + numCols;
      diagonalLeft = numColsf;
      loop = loop + 1;
    }
    if (win == 1) {
      return 1;
    }
    if (win == -1) {
      return -1;
    }
    int tieDecider;
    tieDecider = 0;
    while ((tieDecider < arrWin.length) && ((arrWin[tieDecider] == 1) || (arrWin[tieDecider] == -1))) {
      tieDecider = tieDecider + 1;
    }
    // win = 2 means tie.
    if ((arrWin.length == tieDecider) && (win == 0)) {
      win = 2;
      return 2;
    }
    // win = 2 means tie.
    

    /*
    Across: if selectedRow is the same all the time, and selectedCol is increasing and reaches 4, then a win.
    Down: if selectedCol is the same all the time, and selectedRow is increasing and reaches 4, then a win. 
    Diagonal: If num in (numCols - num) is increasing, then it is a win.                              
    */
    return 0;
  }
  public static int[] getNum(int numCols, int numRows, int[] checkIfValid, int[] array, int turn, Scanner scanner, boolean comp, boolean compTurn, int win) {
    int rowCheck;
    int selectedCol;
    int selectedRow;
    int first = 0;
    /*
    
    comp = if you want to play against computer.
    compTurn = If comp go first. If true computer goes first, else computer goes second

     */
    System.out.println("What column do you want to place at?");
    if (turn == 1) {
      System.out.println("(X's turn)");
    } else if (turn == -1) {
      System.out.println("(O's turn)");
    }
    while(!scanner.hasNextInt()) scanner.next();
    selectedCol = scanner.nextInt();
    System.out.println("you chose " + selectedCol);
    selectedCol = selectedCol - 1;
    selectedRow = numRows - 1;
    rowCheck = numRows * numCols - numCols + selectedCol;
    while ((rowCheck >= 0) && (checkIfValid.length > rowCheck) && (rowCheck - numCols >= 0) && ((checkIfValid[rowCheck] == 1) || (checkIfValid[rowCheck] == -1))) {
      rowCheck = rowCheck - numCols;
      selectedRow = selectedRow - 1;
    }
    while (selectedCol >= numCols || (0 > rowCheck) || (selectedCol < 0) || (selectedCol > checkIfValid.length) ||  (rowCheck >= checkIfValid.length) || (checkIfValid[rowCheck] == 1) || (checkIfValid[rowCheck] == -1)) {
      System.out.println("Please choose a different number.");
      if (turn == 1) {
        System.out.println("(X's turn)");
      } else if (turn == -1) {
        System.out.println("(O's turn)");
      }
      while(!scanner.hasNextInt()) scanner.next();
      selectedCol = scanner.nextInt();
      System.out.println("you chose " + selectedCol);
      selectedCol = selectedCol - 1;
      selectedRow = numRows - 1;
      rowCheck = numRows * numCols - numCols + selectedCol;
      while ((rowCheck >= 0) && (checkIfValid.length > rowCheck) && (rowCheck - numCols > 0) && ((checkIfValid[rowCheck] == 1) || (checkIfValid[rowCheck] == -1))) {
        rowCheck = rowCheck - numCols;
        selectedRow = selectedRow - 1;
      }
    }

    if (turn == 1) {
      checkIfValid[rowCheck] = 1;
    } else {
      checkIfValid[rowCheck] = -1;
    }

    selectedRow = selectedRow + 1;

    int slotNum = numCols * (selectedRow - 1) + selectedCol;

    checkIfValid[slotNum] = 1;
    if (array[slotNum] == 0) {
      if (turn == 1) {
        array[slotNum] = 1;
      } else {
        array[slotNum] = -1;
      }
    }
    return array;
  }
  public static int firstNum(int firstNumber, int numCols, int numRows, int[] array) {
    firstNumber = numRows * numCols - numCols;
    int firstNumberSaved = firstNumber;
    int lastSaved = 0;
    while ((firstNumber < array.length) && (array[firstNumber] != 0)) {
      if (firstNumber > numCols - 1) {
        firstNumber = firstNumber - numCols;
      } else {
        if (lastSaved == 0) {
          lastSaved = lastSaved + 1;
        } else {
          firstNumber = firstNumberSaved + 1;
          firstNumberSaved = firstNumberSaved + 1;
          lastSaved = 0;
        }
        
      }
    }
    if (firstNumber > array.length) {
      return numCols * numRows;
      /*
      This is for when nothing works, and we can detect when it is true.
      IF it is true, we say the board is filled already.
       */
    } else {
      return firstNumber;
    }
  }
  public static void compFirst(boolean comp, boolean compTurn, int[] array, int first, int turn, int numCols, int numRows, int[] checkIfValid) {
    if (comp && compTurn) {
      
      first = firstNum(first, numCols, numRows, array);
      if (first != numRows * numCols) {
        array[first] = 1;
        checkIfValid[first] = 1;
        printBoard(array, numCols, numRows);
      } else {
        int filled = 1;
      }
      turn = -1;
    }
  }
  public static void compLast(boolean comp, boolean compTurn, int[] array, int first, int turn, int numCols, int numRows, int[] checkIfValid) {
    if (comp && !compTurn) {
      
      first = firstNum(first, numCols, numRows, array);
      turn = 1;
      if (first != numRows * numCols) {
        printBoard(array, numCols, numRows);
        array[first] = -1;
        checkIfValid[first] = -1;
        
      } else {
        int filled = 1;
      }
      turn = 1;
    }
  }
  /*
  public static blockAcross(int numCols) {
    across = 0;
    stopAcross = numCols - 4;
    loop = 0;
    numRowsf = 0;
    while (numRows > loop) {
      while (stopAcross >= across) {
        if ((arrWin[across] == 1) && (arrWin[across + 1] == 1) && (arrWin[across + 2] == 1) && (arrWin[across + 3] == 1)) {
          win = 1;
        }
        if ((arrWin[across] == -1) && (arrWin[across + 1] == -1) && (arrWin[across + 2] == -1) && (arrWin[across + 3] == -1)) {
          win = -1;
        }
        across = across + 1;
      }
      stopAcross = stopAcross + numCols;
      numRowsf = numRowsf + numCols;
      across = numRowsf;
      loop = loop + 1;
    }
  }
  */
}
