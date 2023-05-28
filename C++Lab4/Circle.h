#ifndef Circle_h
#include "Figure.h"


class Circle : public Figure{
    double radius;
    CVector2D center;
    int weight;
public:
    Circle();
    Circle(double radius_val, CVector2D center_val, int weight_val);
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