#include "CVector2D.h"
#ifndef IPhysObject_h
#define IPhysObject_h

class PhysObject{
public:
    virtual double mass() = 0;
    virtual CVector2D position() = 0;
    virtual bool operator==(PhysObject& PhObj) = 0;
    virtual bool operator<(PhysObject& PhObj) = 0;


};
#endif