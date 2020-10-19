package application;
class Intensity {
int r, g, b; // RGB values at a position
}
class Position {
int x, y; // pixel position
}
class Pixel { // pixel position with RGB values
Intensity intensity;
Position position;
}

public interface DrawShapes {
	// parameter: Pixel center - center position with intensity for circle
	// radius - radius of circle
	void draw_circle(Pixel center, float radius);
	// makes an object for square parallel to the whiteBoard
	// parameter: Pixel start - start position for square with RGB values in whiteBoard
	// float length - length of the sqaure
	void draw_square(Pixel start, float length); // makes an object for rectangle parallel to the whiteBoard
	// parameter: Pixel start - top left position with RGB values for rectangle in whiteBoard
	// Pixel end - bottom right position with RGB values for rectangle in whiteBoard
	void draw_rectangle(Pixel start, Pixel end);
	// makes an object for a line segment parallel to the whiteBoard
	// parameter: Pixel start - start position with RGB values
	// Pixel end - end position with RGB values
	void draw_line(Pixel start, Pixel end); // start point, end point
}
