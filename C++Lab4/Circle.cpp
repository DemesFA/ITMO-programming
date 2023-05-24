#include "Circle.h"
#include <cmath>
#include <iostream>

Circle::Circle(){
    this->Circle::initFromDialog();
};


double Circle::square() {
    return M_PI * (radius * radius);
}

double Circle::perimeter() {
    return 2 * M_PI * radius;
}

CVector2D Circle::position() {
    return center;
}

double Circle::mass() {
    return weight;
}

bool Circle::operator==(PhysObject &PhObj) {
    return mass() == PhObj.mass();
}

bool Circle::operator<(PhysObject &PhObj) {
    return mass() < PhObj.mass();
}

void Circle::initFromDialog() {
    std::cout << "Enter a radius of Circle" << std::endl;
    std::cin >> radius;
    std::cout << "Enter a center coordinates of Circle(dx,dy)" << std::endl;
    std::cin >> center.dx >> center.dy;
    std::cout << "Enter a weight of Circle" << std::endl;
    std::cin >> weight;
}

const char *Circle::classname() {
    return "Circle";
}

void Circle::draw() {
    std::cout << "Circle radius is:" << radius << std::endl;
    std::cout << "Circle center coordinates is:" << center.dx << " " << center.dy << std::endl;
    std::cout << "Circle weight is:" << weight << std::endl;
    std::cout << "Circle size is:" << Circle::size() << std::endl;
    std::cout << "Circle square is: " << Circle::square() << std::endl;
    std::cout << "Circle perimeter is: " << Circle::perimeter() << std::endl;
}

unsigned int Circle::size() {
    return sizeof(*this);
}