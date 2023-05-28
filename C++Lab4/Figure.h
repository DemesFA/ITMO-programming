#ifndef Figure_h

class GeoFig{
public:
    virtual double perimeter() = 0;
    virtual double square() = 0;
};


class Dialog {
public:
    virtual void initFromDialog() = 0;
};


class CVector2D{
public:
    double dx,dy;
};

class PhysObject{
public:
    virtual double mass() = 0;
    virtual CVector2D position() = 0;
    virtual bool operator==(PhysObject& PhObj) = 0;
    virtual bool operator<(PhysObject& PhObj) = 0;

};

class Printable{
public:
    virtual void draw() = 0;
};

class BaseObject{
    virtual const char* classname() = 0;
    virtual unsigned int size() = 0;
};


class Figure : public GeoFig, public Printable, public PhysObject, public Dialog, public BaseObject{};

#endif