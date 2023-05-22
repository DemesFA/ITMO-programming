#include <iostream>
#include <string>

class Array {
    int size;
    float* arr;
public:
    Array(int size_);
    Array();
    ~Array();
    std::string Print();
    bool IfTen();
    void operator+(int k);
    float geometricMean();
    float& operator[](int index);
};
