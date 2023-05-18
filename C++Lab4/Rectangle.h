#include "IGeoFig.h"
#include "CVector2D.h"
#include "IPhysObject.h"
#include "IPrintable.h"
#include "Dialog.h"
#include "BaseObject.h"

class Rectangle : public GeoFig, public Printable, public PhysObject, public Dialog, public BaseObject, public CVector2D{

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
