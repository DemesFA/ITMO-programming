#include "Complex.h"
#include <cmath>
Complex::Complex() {
    real = 0;
    imaginary = 0;
}

Complex::Complex(double real, double imaginary) : real(real), imaginary(imaginary) {}

Complex Complex::operator*(Complex &ComplexNumber) {
    double NewReal = real * ComplexNumber.real;
    double NewImaginary = imaginary * ComplexNumber.imaginary;
    return {NewReal,NewImaginary};
}

Complex Complex::operator+(Complex &ComplexNumber) {
    double NewReal = real + ComplexNumber.real;
    double NewImaginary = imaginary + ComplexNumber.imaginary;
    return {NewReal,NewImaginary};
}

Complex Complex::operator*(double number) {
    return {this->real * number, this->imaginary * number};
}
Complex::operator double(){
    return sqrt(real * real + imaginary * imaginary);
}
