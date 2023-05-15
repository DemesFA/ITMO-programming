#include <functional>

template <typename Iterator>
bool none_of(Iterator begin,Iterator end, bool(*Predicate)(const auto)){}