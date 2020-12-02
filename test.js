/*
	수작업 테트리스
	20.12.02,
	auther:이종빈
*/

const KEY_S =115,
KEY_X = 120,
KEY_Z = 122,
KEY_C = 99;

let GameCursorX=0, GameCursorY=0;

/* 스틱들 */
let sticks ={
	L: [
		[
			[0,0,1,0], [0,0,1,0], [0,1,1,0], [0,0,0,0]
		],
		[
			[0,0,0,0], [0,1,0,0], [0,1,1,1], [0,0,0,0]
		],
		[
			[0,0,0,0], [0,1,1,0], [0,0,1,0], [0,0,1,0]
		],
		[
			[0,0,0,0], [1,1,1,0], [0,0,1,0], [0,0,0,0]
		]
	],
	I:[
		[
			[0,0,1,0], [0,0,1,0], [0,0,1,0], [0,0,1,0]
		],
		[
			[0,0,0,0], [0,0,0,0], [1,1,1,1], [0,0,0,0]
		],
		[
			[0,0,1,0], [0,0,1,0], [0,0,1,0], [0,0,1,0]
		],
		[
			[0,0,0,0], [1,1,1,1], [0,0,0,0], [0,0,0,0]
		]
	],
	O:[
		[
			[1,1], [1,1]
		],
		[
			[1,1], [1,1]
		],
		[
			[1,1], [1,1]
		],
		[
			[1,1], [1,1]
		]
	],
	T:[
		[
			[1,1,1], [0,1,0], [0,0,0]
		],
		[
			[0,0,1], [0,1,1], [0,0,1]
		],
		[
			[0,0,0], [0,1,0], [1,1,1]
		],
		[
			[1,0,0], [1,1,0], [1,0,0]
		]
	]
}


/* 10 * 20 게임판 */
let Board = create2DArray(20, 10, 'black');

onkeypress = function() {
	console.log(event.keyCode);

	var keyCode = event.keyCode;

	switch(keyCode) {
		case KEY_S:
			if(GameCursorY>0)
				GameCursorY-=1;
			break;

		case KEY_X:
			if(GameCursorY<20-1)
				GameCursorY+=1;
			break;

		case KEY_Z:
			if(GameCursorX>0)
				GameCursorX-=1;
			break;

		case KEY_C:
			if(GameCursorX<10-1)
				GameCursorX+=1;
			break;
	}

	console.log(GameCursorX + ', ' + GameCursorY);
};

gameFrequencyLoop = setInterval( function() {
	for (var i=0; i<Board.length; i++) {
		for (var j=0; j<Board[i].length; j++) {
			if (j==GameCursorX&&
				i==GameCursorY) {
				Board[i][j].style.backgroundColor='black';
			}else {
				Board[i][j].style.backgroundColor='blue';
			}
		}
	}
}, 100);

stickFallingLoop = setInterval( function() {
	if(GameCursorY>=20-1) {

	}else {
		GameCursorY+=1;
	}
}, 1000);

function create2DArray(rows, columns) {
	var matrix = new Array(rows);
	for (var i=0; i<rows; i++) {
		matrix[i] = new Array(columns);

		for (var j=0; j<columns; j++) {
			matrix[i][j]= document.createElement('div');
			matrix[i][j].setAttribute('class', 'gameBlock');

			matrix[i][j].style.backgroundColor='blue';
			matrix[i][j].style.top= 40 * i + 3 +'px'; 
			matrix[i][j].style.left= 60 * j + 3 + 'px';

			document.getElementById('GameBase').appendChild(matrix[i][j]);
		}
	}

	return matrix;
}

// var makeButton = document.getElementById('makeButton');

// makeButton.addEventListener('click', function() {
	
// 	var testDivBox = document.createElement('div');
// 	testDivBox.setAttribute('class', 'testBox');

// 	document.body.appendChild(testDivBox)
// });
