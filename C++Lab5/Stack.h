template <int N,typename T>
class Stack{
    T data;
    Stack* head;
    Stack* next;
public:
    Stack();
    ~Stack();
    int Stack_size();
    void push(T value);
    void pop();
    void print();
};