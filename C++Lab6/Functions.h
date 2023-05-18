#include <functional>


struct Functions{

    template <typename Iterator,typename Predicate>
    bool none_of(Iterator begin, Iterator end, Predicate predicate);

    template <typename Iterator,typename Compare>
    bool is_sorted(Iterator begin, Iterator end, Compare compare);

    template<typename Iterator, typename T>
    Iterator find_not(Iterator begin, Iterator end, T& value);
};
