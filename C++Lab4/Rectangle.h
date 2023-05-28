#ifndef Rectangle_h
#include "Figure.h"

class Rectangle : public Figure{

    double width;
    double height;
    int weight;
    CVector2D center;
public:
    Rectangle();
    Rectangle(double width_val, double height_val, int weight_val, CVector2D center_val);
    double square() override;
    double perimeter() override;
    CVector2D position() override;
    double mass() override;
    void initFromDialog() override;
    void draw() override;
    unsigned int size() override;
    bool operator==(PhysObject& PhObj) override;
    bool operator<(PhysObject& PhObj) override;
    const char * classname() override;
};

#endif