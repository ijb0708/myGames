package tetris;

import java.awt.Color;

public enum TypeShapes {
	T(3, Color.cyan, new boolean[][][]{
		{
			{	false,	true,	false,	},
			{	true,	true,	true,	},
			{	false,	false,	false,	},
		},
		{
			{	false,	true,	false,	},
			{	false,	true,	true,	},
			{	false,	true,	false,	},
		},
		{
			{	false,	false,	false,	},
			{	true,	true,	true,	},
			{	false,	true,	false,	},
		},
		{
			{	false,	true,	false,	},
			{	true,	true,	false,	},
			{	false,	true,	false,	},
		},
	}),
	
	J(3, Color.blue, new boolean[][][]{
		{
			{	false,	true,	false,	},
			{	false,	true,	false,	},
			{	true,	true,	false,	},
		},
		{
			{	true,	false,	false,	},
			{	true,	true,	true,	},
			{	false,	false,	false,	},
		},
		{
			{	false,	true,	true,	},
			{	false,	true,	false,	},
			{	false,	true,	false,	},
		},
		{
			{	false,	false,	false,	},
			{	true,	true,	true,	},
			{	false,	false,	true,	},
		},
	}),
	
	L(3, Color.yellow, new boolean[][][]{
		{
			{	false,	true,	false,	},
			{	false,	true,	false,	},
			{	false,	true,	true,	},
		},
		{
			{	false,	false,	false,	},
			{	true,	true,	true,	},
			{	true,	false,	false,	},
		},
		{
			{	true,	true,	false,	},
			{	false,	true,	false,	},
			{	false,	true,	false,	},
		},
		{
			{	false,	false,	true,	},
			{	true,	true,	true,	},
			{	false,	false,	false,	},
		},
	}),
	
	O(2, Color.green, new boolean[][][]{
		{
			{	true,	true,	},
			{	true,	true,	},
		},
		{
			{	true,	true,	},
			{	true,	true,	},
		},
		{
			{	true,	true,	},
			{	true,	true,	},
		},
		{
			{	true,	true,	},
			{	true,	true,	},
		},
	}),

	I(4, Color.red, new boolean[][][]{
		{
			{	false,	true,	false,	false },
			{	false,	true,	false,	false },
			{	false,	true,	false,	false },
			{	false,	true,	false,	false }
		},
		{
			{	false,	false,	false,	false	},
			{	true,	true,	true,	true	},
			{	false,	false,	false,	false	},
			{	false,	false,	false,	false	}
		},
		{
			{	false,	false,	true,	false	},
			{	false,	false,	true,	false	},
			{	false,	false,	true,	false	},
			{	false,	false,	true,	false	}
		},
		{
			{	false,	false,	false,	false	},
			{	false,	false,	false,	false	},
			{	true,	true,	true,	true	},
			{	false,	false,	false,	false	}
		},
	});
	
	private final boolean[][][] stick;
	private final Color color;
	private final int len;
	
	TypeShapes (int wid, Color color, boolean[][][] stick) {
		this.stick=stick;
		this.color=color;
		this.len=wid;
	}
	
	boolean isVisible(int lotation, int x, int y) {
		return stick[lotation][y][x]==true?true:false;
	}
	
	int edgeTop(int lotation) {
		
		for (int i=0; i<len; i++) {
			for (int j=0; j<len; j++) {
				if(stick[lotation][i][j]==true) return i;
			}
		}
		
		return -1;
	}
	
	int edgeBottom(int lotation) {
		
		for (int i=0; i<len; i++) {
			for (int j=0; j<len; j++) {
				if(stick[lotation][i][j]==true) return i;
			}
		}
		
		return -1;
	}
	
	int edgeLeft(int lotation) {
		
		for (int i=0; i<len; i++) {
			for (int j=0; j<len; j++) {
				if(stick[lotation][j][i]==true) return i;
			}
		}
		
		return -1;
	}
	
	int edgeRight(int lotation) {
		
		for (int i=len-1; i>=0; i--) {
			for (int j=0; j<len; j++) {
				if(stick[lotation][j][i]==true) return i;
			}
		}
		
		return -1;
	}
	
	int getLengh() {
		return this.len;
	}
	
	Color getColor() {
		return this.color;
	}
}
