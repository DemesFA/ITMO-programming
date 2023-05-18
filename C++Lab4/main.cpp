#include <iostream>
#include "Circle.h"
#include "Rectangle.h"


int main() {
    Circle circle;
    circle.initFromDialog();
    circle.draw();
    Rectangle rectangle;
    rectangle.initFromDialog();
    rectangle.draw();

}
