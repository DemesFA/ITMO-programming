#include <iostream>
#include "Functions.h"
#include <vector>
#include <algorithm>

template<typename Iterator,typename Predicate>
bool Functions::none_of(Iterator begin, Iterator end, Predicate predicate) {

    for(Iterator it = begin; it != end; it++){
        if(predicate(*it)){
            return false;
        }
    }
    return true;
}


template<typename Iterator, typename Compare>
bool Functions::is_sorted(Iterator begin, Iterator end, Compare compare) {
    if (begin == end)
        return true;

    Iterator prev = begin;
    Iterator current = begin;
    ++current;

    while (current != end) {
        if (compare(*current, *prev)) {
            return false;
        }
        ++prev;
        ++current;
    }
    return true;
}


template<typename Iterator,typename T>
Iterator Functions::find_not(Iterator begin, Iterator end, T &value) {

    for(Iterator it = begin; it != end; it++){
        if(*it != value){
            return it;
        }
    }
    return end;

}
template<typename T>
bool is_negative(T num) {
    return num < 0;
}


template<typename T>
struct Less {
    bool operator()(const T& a, const T& b) const {
        return a < b;
    }
};




int main() {

    int Size;
    std::cout << "Enter a size of vector" << std::endl;
    std::cin >> Size;
    std::vector<int> vec(Size);
    std::cout << "Enter elements in vector" << std::endl;
    for(int i = 0; i < vec.size(); i++){
        std::cin >> vec[i];
    }

    Functions functions;


    bool noneOfResult = functions.none_of(vec.begin(),vec.end(), is_negative<int>);
    std::cout << "none_of Result: " << (noneOfResult ? "true" : "false") << std::endl;

    bool isSortedResult = functions.is_sorted(vec.begin(), vec.end(), Less<int>());
    std::cout << "is_sorted Result: " << (isSortedResult ? "true" : "false") << std::endl;

    int number;
    std::cout << "Enter a element for func find_not" << std::endl;
    std::cin >> number;
    auto findNotResult = functions.find_not(vec.begin(), vec.end(), number);
    std::cout << "find_not Result: " << *findNotResult;

}
