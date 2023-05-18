#include "Rectangle.h"
#include <iostream>

Rectangle::Rectangle(double width_val, double height_val, int weight_val, CVector2D center_val) :
    width(width_val), height(height_val), weight(weight_val), center(center_val) {};

Rectangle::Rectangle() {
    this->Rectangle::initFromDialog();
}

double Rectangle::square() {
    return width * height;
}

double Rectangle::perimeter() {
    return 2*(width * height);
}

CVector2D Rectangle::position() {
    return center;
}

double Rectangle::mass() {
    return weight;
}

bool Rectangle::operator==(PhysObject &PhObj) {
    return mass() == PhObj.mass();
}

bool Rectangle::operator<(PhysObject &PhObj) {
    return mass() < PhObj.mass();
}

void Rectangle::initFromDialog() {
    std::cout << "Enter a width of Rectangle" << std::endl;
    std::cin >> width;
    std::cout << "Enter a height of Rectangle" << std::endl;
    std::cin >> height;
    std::cout << "Enter a center coordinates of Rectangle(dx,dy)" << std::endl;
    std::cin >> center.dx >> center.dy;
    std::cout << "Enter a weight of Rectangle" << std::endl;
    std::cin >> weight;
}

const char *Rectangle::classname() {
    return "Rectangle";
}

void Rectangle::draw() {
    std::cout << "Rectangle wight is:" << width << std::endl;
    std::cout << "Rectangle center coordinates is:" << center.dx << " " << center.dy << std::endl;
    std::cout << "Rectangle weight is:" << weight << std::endl;
    std::cout << "Rectangle size is:" << Rectangle::size() << std::endl;
}

unsigned int Rectangle::size() {
    return sizeof(*this);
}