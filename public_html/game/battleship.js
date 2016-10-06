/**
 * This is a basic javascript implementation of the game Battleship. When the
 * page is loaded, three ships are randomly placed on the board, either 
 * vertically or horizontally. The ships do not overlap.
 * 
 * @authors Roberto Loja, Denis Stepanov
 * @version March 25th, 2016.
 */

var area = [
    [0, 0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0, 0, 0], 
];

var letters = 'abcdefg';
var shotCount = 0;
var ships = 0;
var hits = 0;
var remainingSquares = 0;

message();
function message(){
confirm("Welcome to Batlleship!\n\n"+
      "Rules And Descrption:\n\n"+
      "PRESSING <ENTER> KEY WILL RESTART THE PAGE\n\n"+
      "1)\tShips are randomly placed by computer on the grid\n"+
      "2)\tThere are 3 ships with different sizes. You have to destroy them all!\n"+
      "3)\tTo hit a ship type one letter (A-G) and one number (0-6) in the box and bottom right corner\n"+
      "4)\tAfter you hit the ship, the space where you hit changes with appropriate picture, same goes with misses\n"+
      "5)\tAt the end of the game your accuracy will be shown\n\n"+
      "READY! AIM! FIRE!");
}
placeShip(3);
console.table(area);
document.getElementById('fireButton').onclick = fire;

/**
 * Places ships randomly on the board.
 * @param {Number} numShips The number of ships to place.
 */
function placeShip(numShips) {
  
  while (ships < numShips) {
    var row = Math.floor(Math.random() * 7);
    var column = Math.floor(Math.random() * 7);
    var orientation = Math.random() > 0.5 ? 1 : 0; // 1 = horizontal

    if (orientation === 1 && horizontalFree([column, row], ships + 2)) {
      for (let i = 0; i < ships + 2; i++) {
        area[row][column + i] = ships + 2;
        remainingSquares++;
      }
      ships++;
    } else if (orientation === 0 && verticalFree([column, row], ships + 2)) {
      for (let i = 0; i < ships + 2; i++) {
        area[row + i][column] = ships + 2;
        remainingSquares++;
      }
      ships++;
    }
  }
}


/**
 * Checks if the proposed placement of a ship is adjacent to any other ships,
 * which is not allowed in the rules of Battleship.
 * @param {Number[]} startCoordinate An array of coordinates in [x, y] format.
 * @param {Number} length The length of the ship to be checked.
 * @return {Boolean} True if ship can be placed, false otherwise.
 */
function horizontalFree(startCoordinate, length) {
  var x = startCoordinate[0];
  var y = startCoordinate[1];
  
  if (x + length > area.length - 1 || area[y][x] !== 0) {
    return false;
  }
  
  var test = area[y].slice(area[y][x - 1] !== undefined ? x - 1 : 0,
                           area[y][x + length + 1] !== undefined ? x + length + 1 : 0);
  
  for (var i = x; i < x + length; i++) {
    test.push(area[y - 1] ? area[y - 1][i] : 0);
    test.push(area[y + 1] ? area[y + 1][i] : 0);
  }
  return !test.filter(a => a > 0).length;
}


/**
 * Checks if the proposed placement of a ship is adjacent to any other ships.
 * @param {Number[]} startCoordinate An array of coordinates in [x, y] format.
 * @param {Number} length The length of the ship to be checked.
 * @return {Boolean} True if the ship can be placed, false otherwise.
 */
function verticalFree(startCoordinate, length) {
  var x = startCoordinate[0];
  var y = startCoordinate[1];
  
  if (y + length > area.length - 1) { //ship is too long to fit at this coord
    return false;
  }
  
  var test = [];
  
  test.push(area[y - 1] ? area[y - 1][x] : 0); // secure against 'undefined'
  for (var i = y; i < y + length; i++) {
    test.push(area[i][x - 1]);
    test.push(area[i][x]);
    test.push(area[i][x + 1]);
  }
  test.push(area[y + length] ? area[y + length][x] : 0);
  
  return !test.filter(a => a > 0).length;
}

/**
 * Takes input from webpage as the coordinate to fire on.
 */
function fire() {
  var inputBox = document.getElementById('guessInput');
  
  if (inputBox.value.length > 1) {
    var input = inputBox.value.split('');
    
    if (validInput(input)) {
      var inputString = letters.indexOf(input[0].toLowerCase()) + input[1];
      var coordinate = inputString.split('');
      
      var squareClicked = document.getElementById(inputString);
      
      if (hit(coordinate)) {
        squareClicked.classList.add('hit');
      } else {
        squareClicked.classList.add('miss');
      }
      shotCount++;
    } else {
      window.alert('Must enter a valid coordinate.');
    }
  }
    
  if (!remainingSquares) {
      finish(shotCount, hits);
  }
}


/**
 * Checks if the input is valid.
 * @param {String} input The input to be checked.
 * @return {Boolean} True if valid, false otherwise.
 */
function validInput(input) {
  var ret = false;
  
  if (letters.indexOf(input[0].toLowerCase()) !== -1 && (input[1] >= 0 && input[1] <= 6)) {
    ret = true;
  }
  return ret;
}


/**
 * Determines whether a shot hits a ship.
 * @param {Number[]} coordinate The coordinates on 'area' to check.
 * @return {Boolean} True if hit, false otherwise.
 */
function hit(coordinate) {
  var returnVal = false;
  
  if (area[coordinate[0]][coordinate[1]] > 1) {
    returnVal = true;

    alert('You hit a ship!');
    area[coordinate[0]][coordinate[1]] = -1;
    hits++;
    remainingSquares--;
  
  } else if (area[coordinate[0]][coordinate[1]] < 0) {
    returnVal = true;
    alert('This was already hit.');
  } else {
    alert('Miss!');
  }
  return returnVal;
}


/**
 * Function to finish the game and count accuracy
 * @param {Number} shots Tries taken to sunk all the ships
 * @param {Number} hits  Amount of squares hit where ships were placed
 */
function finish(shots, hits) {
  alert("Congratulations! You destroyed all ships");
  accuracy = (hits / shots) * 100;
  alert("Your took " + shots + " shots and your accuracy is " + 
  accuracy.toFixed(2) + " %\nPush ok to start again!");
  location.reload();
}
