#include "Functions.h"

template<typename Iterator,typename Predicate>
bool Functions::none_of(Iterator begin, Iterator end, Predicate predicate) {

    for(Iterator it = begin; it != end; it++){
        if(predicate(*it)){
            return false;
        }
    }
    return true;
}


template<typename Iterator,typename Compare>
bool Functions::is_sorted(Iterator begin, Iterator end, Compare compare) {

        Iterator prev = begin;
        Iterator current = begin++;

        while (current != end){
            if(comp(*current, *prev)) {
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
