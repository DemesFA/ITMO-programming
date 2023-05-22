#include "Array.h"
#include <cmath>

Array::Array(int size_) : size(size_) {
    arr = new float[size];
}

Array::Array() : size(0), arr(nullptr) {}

Array::~Array() {
    delete[] arr;
}

bool Array::IfTen() {
    float count = 0;
    for (int i = 0; i < size; i++) {
        count += arr[i];
    }
    if (count == 10.0){
        return true;
    }
    return false;
}


float &Array::operator[](int index) {
    return arr[index];
}

void Array::operator+(int k) {
    for (int i = 0; i < size; i++) {
        arr[i] += k;
    }
}

float Array::geometricMean() {
    float positiveProduct = 1.0;
    int positiveCount = 0;
    for (int i = 0; i < size; i++) {
        if (arr[i] > 0) {
            positiveProduct *= arr[i];
            positiveCount++;
        }
    }
    if (positiveCount > 0) {
        float geometricMean = std::pow(positiveProduct, 1.0 / positiveCount);
        return geometricMean;
    } else {
        return 0.0;
    }
}
