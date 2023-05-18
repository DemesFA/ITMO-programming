#include "IGeoFig.h"
#include "CVector2D.h"
#include "IPhysObject.h"
#include "IPrintable.h"
#include "Dialog.h"
#include "BaseObject.h"

class Circle : public GeoFig, public Printable, public PhysObject, public Dialog, public BaseObject, public CVector2D{
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