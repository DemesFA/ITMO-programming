#include <iostream>
#include "Circle.h"
#include "Rectangle.h"
#include <vector>
#include "Figure.h"


int main() {

    std::vector<Figure*> figures;
    figures.push_back(new Circle);
    figures.push_back(new Rectangle);

    std::vector<int> perimeters;
    std::vector<int> squares;
    std::vector<int> mass;

    for(auto& figure : figures) {
        int sumP = figure->perimeter();
        int sumS = figure->square();
        int weight = figure->mass();
        squares.push_back(sumS);
        perimeters.push_back(sumP);
        mass.push_back(weight);
    }

    int resultSquares = 0;
    for(int i = 0; i < squares.size(); i++) {
        resultSquares += squares[i];
    }

    int resultPerimeters = 0;
    for(int i = 0; i < perimeters.size(); i++) {
        resultPerimeters += perimeters[i];
    }

    std::cout << "SumPerimeters is: " << resultPerimeters << " " << "sumSquares is: " << resultSquares << std::endl;

    std::sort(mass.begin(), mass.end());

    for(int i = 0; i < mass.size(); i++) {
        std::cout << mass[i] << " ";
    }



    return 0;
}
