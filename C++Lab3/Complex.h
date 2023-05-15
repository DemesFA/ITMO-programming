class Complex{

    double real;
    double imaginary;
public:
    Complex();
    Complex(double real,double imaginary);
    Complex operator+(Complex& ComplexNumber);
    Complex operator*(Complex& ComplexNumber);
    Complex operator*(double number);
    operator double();
};